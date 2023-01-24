package org.nasdanika.html.emf;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Document;
import org.nasdanika.emf.persistence.TextResourceFactory;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.ncore.Documented;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.util.NcoreUtil;
import org.xml.sax.SAXException;

/**
 * Handles Ncore-specific cases - {@link NamedElement} and {@link Documented}
 * @author Pavel
 *
 * @param <T>
 */
public class NcoreActionBuilder<T extends EObject> extends EObjectActionBuilder<T> {
	
	public NcoreActionBuilder(T target, Context context) {
		super(target, context);		
	}
	
	@Override
	protected Action buildAction(
			Action action,
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) {
		Action ret = super.buildAction(action, registry, resolveConsumer, progressMonitor);		
		
		T semanticElement = getTarget();
		
		List<URI> uris = NcoreUtil.getUris(semanticElement);
		String id = uris.isEmpty() ? null : uris.get(0).toString();
		
		if (semanticElement instanceof ModelElement) {
			ModelElement modelElement = (ModelElement) semanticElement;
			ret.setTooltip(modelElement.getDescription()); // Escape?
			
			if (id == null) {
				id = modelElement.getUuid();
			}
		}

		if (!Util.isBlank(id)) {
			try {
				String digest = Hex.encodeHexString(MessageDigest.getInstance("SHA-256").digest(id.getBytes(StandardCharsets.UTF_8)));
				ret.setId(digest);
			} catch (NoSuchAlgorithmException e) {
	 		}
		}
		
		BiSupplier<EObject, String> cPath = NcoreUtil.containmentPath(semanticElement);
		if (cPath == null || Util.isBlank(cPath.getSecond())) {
			ret.setLocation("${base-uri}index." + getHtmlExtension());
		} else {
			ret.setLocation(cPath.getSecond() + "/index." + getHtmlExtension());
		}		
		
		if (semanticElement instanceof NamedElement) {
			ret.setText(((NamedElement) semanticElement).getName()); // Escape?
		}
		
		return ret;
	}
	
	@Override
	protected void resolve(Action action, org.nasdanika.html.emf.EObjectActionResolver.Context context,	ProgressMonitor progressMonitor) {
		super.resolve(action, context, progressMonitor);
		
		T semanticElement = getTarget();
		if (semanticElement instanceof Documented) {
			List<EObject> documentation = ((Documented) semanticElement).getDocumentation();
			action.getContent().addAll(EcoreUtil.copyAll(documentation)); // TODO - wrap into a group in order to inject uri's properties, optionally save into its own resource.
		}
		
		if (semanticElement instanceof ModelElement) {
			for (Entry<String, String> semanticRepresentationEntry: ((ModelElement) semanticElement).getRepresentations()) {
				String actionRepresentation = processRepresentation(semanticRepresentationEntry.getValue(), action, context, progressMonitor);
				if (!Util.isBlank(actionRepresentation)) {
					action.getRepresentations().put(semanticRepresentationEntry.getKey(), actionRepresentation);
				}
			}
		}
	}
	
	/**
	 * Processes semantic element representations in order to add them to the action.  
	 * @return Processed representation or null if the representation shall not be stored at the action.
	 */
	protected String processRepresentation(
			String representation, 
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context, 
			ProgressMonitor progressMonitor) {
		
		// Processing drawio data URI's
		URI representationURI = URI.createURI(representation);
		if (Document.isDataURI(representationURI)) {
			try {
				Document document = Document.load(representationURI);				
				Document processedDocument = processDrawioRepresentation(document, action, context, progressMonitor);
				if (processedDocument == null) {
					return null;
				}
				return processedDocument.toDataURI(true).toString();
			} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
				throw new NasdanikaException("Error loading drawio document: " + e, e);
			}
		}
		
		return representation;
	}
	
	protected Document processDrawioRepresentation(
			Document document, 
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context, 
			ProgressMonitor progressMonitor) {
		
		document.accept(element -> processDrawioElement(element, action, context, progressMonitor));
		
		return document;
	}

	protected void processDrawioElement(
			org.nasdanika.graph.Element element, 
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context, 
			ProgressMonitor progressMonitor) {

		if (element instanceof org.nasdanika.drawio.ModelElement) {
			org.nasdanika.drawio.ModelElement modelElement = (org.nasdanika.drawio.ModelElement) element;
			String semanticUUID = modelElement.getProperty("semantic-uuid");
			if (Util.isBlank(semanticUUID)) {
				String targetURI = modelElement.getProperty("target-uri");
				if (!Util.isBlank(targetURI)) {
					URI tURI = URI.createURI(targetURI);
					EObject uriTarget = findByURI(tURI, getTarget());
					if (uriTarget != null) {
						if (uriTarget != null) {
							Action uriTargetAction = context.getAction(uriTarget);
							if (uriTargetAction != null) {
								String uriTargetActionUUID = uriTargetAction.getUuid();
								if (!Util.isBlank(uriTargetActionUUID)) {
									modelElement.setProperty("action-uuid", uriTargetActionUUID);
								}
								if (Util.isBlank(modelElement.getTooltip())) {
									String uriTargetActionTooltip = uriTargetAction.getTooltip();
									if (!Util.isBlank(uriTargetActionTooltip)) {
										modelElement.setTooltip(uriTargetActionTooltip);
									}									
								}
							}
						}						
					}
				}				
			} else {
				ModelElement semanticModelElement = findByUUID(semanticUUID, getTarget());
				if (semanticModelElement != null) {
					Action semanticModelElementAction = context.getAction(semanticModelElement);
					if (semanticModelElementAction != null) {
						String semanticModelElementActionUUID = semanticModelElementAction.getUuid();
						if (!Util.isBlank(semanticModelElementActionUUID)) {
							modelElement.setProperty("action-uuid", semanticModelElementActionUUID);
						}
						if (Util.isBlank(modelElement.getTooltip())) {
							String semanticModelElementDescription = semanticModelElementAction.getDescription();
							if (!Util.isBlank(semanticModelElementDescription)) {
								modelElement.setTooltip(semanticModelElementDescription);
							}									
						}
					}
				}
			}			
		}		
	}
	
	/**
	 * @return all contents iterator at the highest available level - resource set, resource, or the semantic element.
	 */
	private static TreeIterator<? extends Notifier> getAllContents(EObject semanticElement) {
		Resource resource = semanticElement.eResource();
		if (resource == null) {
			return semanticElement.eAllContents();
		}
		
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			return resource.getAllContents();
		}		
		
		return resourceSet.getAllContents();
	}
	
	private static ModelElement findByUUID(String uuid, EObject semanticElement) {
		if (Util.isBlank(uuid)) {
			return null;			
		}
		
		if (semanticElement instanceof ModelElement) {
			String semanticElementUUID = ((ModelElement) semanticElement).getUuid();
			if (uuid.equals(semanticElementUUID)) {
				return (ModelElement) semanticElement;
			}
		}
		
		TreeIterator<? extends Notifier> rscit = getAllContents(semanticElement);
		while (rscit.hasNext()) {
			Notifier next = rscit.next();
			if (next instanceof ModelElement) {
				String nextUUID = ((ModelElement) next).getUuid();
				if (uuid.equals(nextUUID)) {
					return (ModelElement) next;
				}
			}
		}
		
		return null;
	}
	
	private static EObject findByURI(URI uri, EObject semanticElement) {		
		for (URI semanticURI: NcoreUtil.getUris(semanticElement)) {
			if (uri.isRelative() && !semanticURI.isRelative() && semanticURI.isHierarchical()) {
				uri = uri.resolve(semanticURI);
			}
			if (Objects.equals(semanticURI, uri)) {
				return semanticElement;
			}				
		}
		
		TreeIterator<? extends Notifier> rscit = getAllContents(semanticElement);
		while (rscit.hasNext()) {
			Notifier next = rscit.next();
			if (next instanceof EObject) {
				EObject nextEObject = (EObject) next;
				for (URI nextUri: NcoreUtil.getUris(nextEObject)) {
					if (nextUri != null && nextUri.equals(uri)) {
						return nextEObject;
					}
				}
				if (next instanceof ModelElement) {
					String uuid = ((ModelElement) next).getUuid();
					if (!Util.isBlank(uuid)) {
						URI uuidUri = URI.createURI("uuid:" + uuid);
						if (uuidUri.equals(uri)) {
							return nextEObject;
						}
					}
				}				
			}
		}
		
		return null;
	}
	
	/**
	 * For drawio representations resolves and sets links for model elements with action-uuid or action-uri property and empty link. Returns a map of representation keys to corresponding drawio documents.
	 * @param action
	 * @param uriResolver
	 * @param progressMonitor
	 * @return
	 */
	public static Map<String, Object> resolveRepresentationLinks(Action action, BiFunction<Label, URI, URI> uriResolver, ProgressMonitor progressMonitor) {
		Map<String, Object> ret = new LinkedHashMap<>();
		for (Entry<String, String> actionRepresentationEntry: action.getRepresentations()) {
			URI representationURI = URI.createURI(actionRepresentationEntry.getValue());
			if (Document.isDataURI(representationURI)) {
				try {
					Document document = Document.load(representationURI);
					document.accept(element -> resolveLink(element, action, uriResolver, progressMonitor));
					ret.put(actionRepresentationEntry.getKey(), document);
				} catch (ParserConfigurationException | SAXException | IOException e) {
					throw new NasdanikaException("Error loading drawio document: " + e, e);
				}
			} else if (TextResourceFactory.isDataURI(representationURI)) {
				Resource textResource = new TextResourceFactory().createResource(representationURI);
				try {
					textResource.load(null);
				} catch (IOException e) {
					throw new NasdanikaException("Error loading text resource from data URI: " + e, e);
				}
				StringBuilder textBuilder = new StringBuilder();
				for (EObject eObj: textResource.getContents()) {
					if (eObj instanceof org.nasdanika.ncore.String) {
						String text = ((org.nasdanika.ncore.String) eObj).getValue();
						if (text != null) {
							textBuilder.append(text);
						}
					} else {
						throw new UnsupportedOperationException("Unsupported contents: " + eObj);
					}
				}
				
				MutableContext context = Context.EMPTY_CONTEXT.fork();
				
				PropertyComputer actionUUIDPropertyComputer = new PropertyComputer() {
					
					@SuppressWarnings("unchecked")
					@Override
					public <T> T compute(Context propertyComputerContext, String key, String path, Class<T> type) {
						if (type == null || type.isAssignableFrom(String.class)) {
							URI ret = resolveActionUUIDLink(path, action, uriResolver);
							return ret == null ? null : (T) ret.toString();
						}
						return null;
					}
				};
				
				context.put("action-uuid", actionUUIDPropertyComputer);
				
				PropertyComputer actionURIPropertyComputer = new PropertyComputer() {
					
					@SuppressWarnings("unchecked")
					@Override
					public <T> T compute(Context propertyComputerContext, String key, String path, Class<T> type) {
						if (type == null || type.isAssignableFrom(String.class)) {
							URI ret = resolveActionURILink(path, action, uriResolver);
							return ret == null ? null : (T) ret.toString();
						}
						return null;
					}
				};
				
				context.put("action-uri", actionURIPropertyComputer);								
				
				ret.put(actionRepresentationEntry.getKey(), context.computingContext().interpolateToString(textBuilder.toString()));
			}
		}
		return ret;
	}
	
	private static URI resolveActionUUIDLink(
			String actionUUID,
			Action action, 
			BiFunction<Label, URI, URI> uriResolver) {

		if (!Util.isBlank(actionUUID)) {
			ModelElement targetAction = findByUUID(actionUUID, action);
			if (targetAction instanceof Label) {
				URI actionURI = uriResolver.apply(action, (URI) null);
				URI targetURI = uriResolver.apply((Label) targetAction, actionURI);
				if (targetURI != null) {
					return targetURI;
				}
			}
		}
		
		return null;
	}
		
	private static URI resolveActionURILink(
			String aURI,
			Action action, 
			BiFunction<Label, URI, URI> uriResolver) {

		if (!Util.isBlank(aURI)) {
			EObject targetAction = findByURI(URI.createURI(aURI), action);
			if (targetAction instanceof Label) {
				URI actionURI = uriResolver.apply(action, (URI) null);
				URI targetURI = uriResolver.apply((Label) targetAction, actionURI);
				if (targetURI != null) {
					return targetURI;
				}
			}
		}
		
		return null;
	}

	private static void resolveLink(
			org.nasdanika.graph.Element element, 
			Action action, 
			BiFunction<Label, URI, URI> uriResolver, 
			ProgressMonitor progressMonitor) {

		if (element instanceof org.nasdanika.drawio.ModelElement) {
			org.nasdanika.drawio.ModelElement modelElement = (org.nasdanika.drawio.ModelElement) element;
			String actionUUID = modelElement.getProperty("action-uuid");			
			if (Util.isBlank(modelElement.getLink()) && !Util.isBlank(actionUUID)) {
				ModelElement targetAction = findByUUID(actionUUID, action);
				if (targetAction instanceof Label) {
					URI actionURI = uriResolver.apply(action, (URI) null);
					URI targetURI = uriResolver.apply((Label) targetAction, actionURI);
					if (targetURI != null) {
						modelElement.setLink(targetURI.toString());
						if (Util.isBlank(modelElement.getTooltip())) {
							String actionTooltip = ((Label) targetAction).getTooltip();
							if (!Util.isBlank(actionTooltip)) {
								modelElement.setTooltip(actionTooltip);
							}
						}
					}
				}
			}
			
			URI aURI = resolveActionURI(modelElement);
			if (Util.isBlank(modelElement.getLink()) && aURI != null) {
				EObject targetAction = findByURI(aURI, action);
				if (targetAction instanceof Label) {
					URI actionURI = uriResolver.apply(action, (URI) null);
					URI targetURI = uriResolver.apply((Label) targetAction, actionURI);
					if (targetURI != null) {
						modelElement.setLink(targetURI.toString());
						if (Util.isBlank(modelElement.getTooltip())) {
							String actionTooltip = ((Label) targetAction).getTooltip();
							if (!Util.isBlank(actionTooltip)) {
								modelElement.setTooltip(actionTooltip);
							}
						}
					}
				}
			}
			
		}		
	}
	
	private static URI resolveActionURI(org.nasdanika.drawio.ModelElement modelElement) {
		String aURI = modelElement.getProperty("action-uri");
		if (Util.isBlank(aURI)) {
			return null;
		}
		URI actionURI = URI.createURI(aURI);
		if (actionURI.isRelative()) {
			URI actionBaseURI = resolveActionBaseURI(modelElement.getParent());
			if (actionBaseURI != null && !actionBaseURI.isRelative() && actionBaseURI.isHierarchical()) {
				return actionURI.resolve(actionBaseURI);
			}
		}
		return actionURI;
	}
	
	private static URI resolveActionBaseURI(org.nasdanika.drawio.ModelElement modelElement) {
		if (modelElement == null) {
			return null;
		}
		String aURI = modelElement.getProperty("action-uri");
		return Util.isBlank(aURI) ? resolveActionBaseURI(modelElement.getParent()) : resolveActionURI(modelElement);
	}
	
}
