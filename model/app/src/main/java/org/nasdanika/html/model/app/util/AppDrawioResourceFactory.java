package org.nasdanika.html.model.app.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jsoup.Jsoup;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.DrawioResourceFactory;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ElementAdapter;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.ExecFactory;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableRow;

/**
 * Creates an action model from Drawio documents
 * @author Pavel
 *
 */
public class AppDrawioResourceFactory extends DrawioResourceFactory<Map<EReference,List<Action>>> {

	private ResourceSet resourceSet;
		
	protected Map<Connection, EReference> connectionRoles = new LinkedHashMap<>();
	protected Map<ModelElement, ModelElement> semanticParents = new LinkedHashMap<>();

	public AppDrawioResourceFactory(ConnectionBase connectionBase, ResourceSet resourceSet) {
		super(connectionBase);
		this.resourceSet = resourceSet;
	}

	@Override
	protected Map<EReference, List<Action>> createSemanticElement(
			Resource resource, 
			Element element,
			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
		
		EReference containmentReference = getContainmentReference(resource, element);
		Action action = null;
		
		if (element instanceof Document) {
			action = createDocumentAction(resource, (Document) element, childEntries);
			if (action == null) {
				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) {
					for (Entry<EReference, List<Action>> referenceEntry: childEntry.getValue().getSemanticElement().entrySet()) {						
						for (Action rootAction: referenceEntry.getValue()) {
							if (isRootAction(childEntry.getKey(), rootAction)) {
								resource.getContents().add(rootAction);
							}
						}
					}
				}				
			} else {
				resource.getContents().add(action);
			}			
		} else if (element instanceof Page) {
			action = createPageAction(resource, (Page) element, childEntries);
		} else if (element instanceof Node) {
			action = createNodeAction(resource, (Node) element, childEntries, containmentReference);
		} else if (element instanceof Layer) {
			action = createLayerAction(resource, (Layer) element, childEntries, containmentReference);
		} else if (element instanceof Connection) {
			action = createConnectionAction(resource, (Connection) element, childEntries, containmentReference);
		}
		
		if (action == null) {
			// Pass-through with grouping
			Map<EReference, List<Action>> ret = new HashMap<>();				
			for (ElementEntry<Map<EReference, List<Action>>> childEntry: childEntries.values()) {
				for (Entry<EReference, List<Action>> referenceEntry: childEntry.getSemanticElement().entrySet()) {						
					ret.computeIfAbsent(referenceEntry.getKey(), ref -> new ArrayList<>()).addAll(referenceEntry.getValue());	
				}
			}
			return ret;
		}
		
		return Collections.singletonMap(containmentReference, Collections.singletonList(action));
	}
	
	protected boolean isRootAction(Element element, Action action) {
		if (action == null) {
			return false;
		}
		Object elementAdapter = EcoreUtil.getRegisteredAdapter(action, ElementAdapter.class);					
		if (elementAdapter instanceof ElementAdapter) {
			 Element actionElement = ((ElementAdapter) elementAdapter).getElement();
			 if (actionElement instanceof ModelElement) {
				 String rootActionFlag = ((ModelElement) actionElement).getProperty(getRootActionFlagProperty());
				 return Util.isBlank(rootActionFlag) || !"false".equals(rootActionFlag);
			 }
		}
		
		return true;
	}
	
	protected String getRootActionFlagProperty() {
		return "root-action";
	}
	
	/**
	 * @param resource
	 * @param element
	 * @return Containment reference for this action. This implementation returns anonymous for connections and children otherwise.
	 */
	protected EReference getContainmentReference(Resource resource, Element element) {		
		return element instanceof Connection ? AppPackage.Literals.ACTION__ANONYMOUS : AppPackage.Literals.LABEL__CHILDREN;
	}
	
	protected Action createDocumentAction(Resource resource, Document document, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
		return null;
	}
	
	protected String getPathProperty() {
		return "path";
	}
	
	protected String getPath(ModelElement modelElement) {
		String pathProperty = getPathProperty();
		if (!Util.isBlank(pathProperty)) {
			String path = modelElement.getProperty(pathProperty);
			if (!Util.isBlank(path)) {
				return path;
			}
		}
		if (modelElement instanceof Root) {
			return ((Root) modelElement).getModel().getPage().getId();
		}
		return modelElement.getId();
	}
	
	protected String getIconProperty() {
		return "icon";
	}
	
	protected String getIcon(ModelElement modelElement) {
		String iconProperty = getIconProperty();
		return Util.isBlank(iconProperty) ? null : modelElement.getProperty(iconProperty);
	}
	
	protected String getDocumentationProperty() {
		return "documentation";
	}
	
	protected boolean hasDocumentation(Resource resource, Element element, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
		if (element instanceof Page) {
			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, (Page) element, childEntries);
			return hasDocumentation(resource, pageElementEntry.getKey(), childEntries);
		}
		if (element instanceof ModelElement) {
			String documentationProperty = getDocumentationProperty();
			if (Util.isBlank(documentationProperty)) {
				return false;
			}
			
			String docProp = ((ModelElement) element).getProperty(documentationProperty);
			return !Util.isBlank(docProp);
		}
		
		return false;
	}
		
	protected EObject getDocumentation(
			Resource resource, 
			Element element, 
			String embeddedDiagram,
			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,			
			String tableOfContents) {
		if (element instanceof Page) {
			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, (Page) element, childEntries);
			return getDocumentation(resource, pageElementEntry.getKey(), embeddedDiagram, childEntries, tableOfContents);
		}
		if (element instanceof ModelElement) {
			String documentationProperty = getDocumentationProperty();
			if (Util.isBlank(documentationProperty)) {
				return null;
			}
			
			String docProp = ((ModelElement) element).getProperty(documentationProperty);
			if (Util.isBlank(docProp)) {
				return null;
			}
			
			Markdown markdown = ContentFactory.eINSTANCE.createMarkdown();
			URI docURI = URI.createURI(docProp);			
			URI base = resolveDocumentationBaseURI(resource, getSemanticParent((ModelElement) element));
			if (base != null) {
				docURI = docURI.resolve(base);
			}
			org.nasdanika.exec.content.Resource docResource = ContentFactory.eINSTANCE.createResource();
			docResource.setLocation(docURI.toString());
			markdown.setSource(docResource);						
			markdown.setStyle(true);

			Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();			
			interpolator.setSource(markdown);
			
			if (Util.isBlank(embeddedDiagram) && Util.isBlank(tableOfContents)) {
				return interpolator;
			}
			
			Configurator configurator = ExecFactory.eINSTANCE.createConfigurator();
			configurator.setTarget(interpolator);
			
			EMap<String, EObject> properties = configurator.getProperties();
			if (!Util.isBlank(embeddedDiagram)) {
				Text diagramText = ContentFactory.eINSTANCE.createText();
				diagramText.setContent(embeddedDiagram);
				properties.put("diagram", diagramText);
			}
			
			if (!Util.isBlank(tableOfContents)) {
				Text tocText = ContentFactory.eINSTANCE.createText();
				tocText.setContent(tableOfContents);
				properties.put("toc", tocText);
			}
			
			return configurator;			
		}
		
		return null;
	}
	
	protected URI resolveDocumentationBaseURI(Resource resource, ModelElement modelElement) {
		if (modelElement == null) {
			return resource.getURI();
		}
		String documentationProperty = getDocumentationProperty();
		if (Util.isBlank(documentationProperty)) {
			return resolveDocumentationBaseURI(resource, getSemanticParent(modelElement));
		}
		String docProp = modelElement.getProperty(documentationProperty);
		if (Util.isBlank(docProp)) {
			return resolveDocumentationBaseURI(resource, getSemanticParent(modelElement));
		}
		
		URI base = resolveDocumentationBaseURI(resource, getSemanticParent(modelElement));
		URI docURI = URI.createURI(docProp);
		return base == null ? docURI : docURI.resolve(base);
	}
	
	protected ModelElement getSemanticParent(ModelElement element) {
		return semanticParents.computeIfAbsent(element, super::getSemanticParent);
	}
			
	protected Action createPageAction(Resource resource, Page page, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
		Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, page, childEntries);
		Action ret =  pageElementEntry == null ? createAction(resource, page) : createModelElementAction(resource, pageElementEntry.getKey(), pageElementEntry.getValue(), null);
		if (Util.isBlank(ret.getText())) {
			ret.setText(page.getName());
		}
		ret.setLocation("pages/" + page.getId() + "/index.html");
		return ret;
	}
	
	/**
	 * @param modelElement
	 * @return true if this element is the main element on the page which "represents" the page. 
	 */
	protected boolean isPageElement(ModelElement modelElement) {
		String pageElementFlagProperty = getPageElementFlagProperty();
		if (Util.isBlank(pageElementFlagProperty)) {
			return false;
		}
		String pageElementProp = modelElement.getProperty(pageElementFlagProperty);
		return "true".equals(pageElementProp);
	}

	protected String getPageElementFlagProperty() {
		return "page-element";
	}
	
	protected Map.Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> findPageElementEntry(
			Resource resource, 
			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
				
		if (childEntries == null) {
			return null;
		}
		
		for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) {
			if (childEntry.getKey() instanceof ModelElement && isPageElement((ModelElement) childEntry.getKey())) {
				return new Map.Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>>() {

					@Override
					public ModelElement getKey() {
						return (ModelElement) childEntry.getKey();
					}

					@Override
					public Map<Element, ElementEntry<Map<EReference, List<Action>>>> getValue() {
						return childEntry.getValue().getChildEntries();
					}

					@Override
					public Map<Element, ElementEntry<Map<EReference, List<Action>>>> setValue(Map<Element, ElementEntry<Map<EReference, List<Action>>>> value) {
						throw new UnsupportedOperationException();
					}

				};				
			}
			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> ret = findPageElementEntry(resource, childEntry.getValue().getChildEntries());
			if (ret != null) {
				return ret;
			}
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param resource
	 * @param page
	 * @param childEntries
	 * @return The "main" element of the page. This implementation returns the root. TODO: Mind-maps - auto-detection (no inbound connections), property to mark the root element.  
	 */
	protected Map.Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> getPageElementEntry(
			Resource resource, 
			Page page, 
			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
				
		Model model = page.getModel();
		Map<Element, ElementEntry<Map<EReference, List<Action>>>> modelChildEntries = childEntries.get(model).getChildEntries();
		Root root = model.getRoot();
		Map<Element, ElementEntry<Map<EReference, List<Action>>>> rootChildEntries = modelChildEntries.get(root).getChildEntries();
		
		Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> ret = findPageElementEntry(resource, rootChildEntries);
		if (ret != null) {
			return ret;
		}

		return new Map.Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>>() {

			@Override
			public ModelElement getKey() {
				return root;
			}

			@Override
			public Map<Element, ElementEntry<Map<EReference, List<Action>>>> getValue() {
				return rootChildEntries;
			}

			@Override
			public Map<Element, ElementEntry<Map<EReference, List<Action>>>> setValue(Map<Element, ElementEntry<Map<EReference, List<Action>>>> value) {
				throw new UnsupportedOperationException();
			}

		};
		
	}

	/**
	 * @return URL of the diagram viewer. If not null then a digram initialization script is added to the page. Otherwise the diagram shall be initialized by some other means.
	 */
	protected String getDiagramViewer() {
		return "https://cdn.jsdelivr.net/gh/Nasdanika/drawio@dev/src/main/webapp/js/viewer-static.min.js";
	}

	/**
	 * Adds textual content.
	 * @param content
	 */
	protected static void addContent(Action action, String content) {
		if (!Util.isBlank(content)) {
			Text text = createText(content);
			action.getContent().add(text);
		}
	}
	
	/**
	 * Convenience method to create Text and set content in one shot.
	 * @param content
	 * @return
	 */
	protected static Text createText(String content) {
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(content);
		return text;
	}	
	
	protected Action createNodeAction(Resource resource, Node node, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries, EReference containmentReference) {
		return createLayerAction(resource, node, childEntries, containmentReference);
	}
	
	protected Action createLayerAction(Resource resource, Layer layer, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries, EReference containmentReference) {
		Action action = createModelElementAction(resource, layer, childEntries, containmentReference);
		return action == null || Util.isBlank(action.getText()) ? null : action;
	}
	
	protected String getModelElementLabel(ModelElement modelElement) {
		if (modelElement == null) {
			return null;
		}
		String label = modelElement.getLabel();
		return Util.isBlank(label) ? null : Jsoup.parse(label).text(); 		
	}	
	
	@SuppressWarnings("unchecked")
	protected Action createModelElementAction(Resource resource, ModelElement modelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries, EReference containmentReference) {
		if (!shallCreateAction(resource, modelElement, childEntries, containmentReference)) {
			return null;
		}
		Action ret = createAction(resource, modelElement);
		ret.setId(modelElement.getId());
		ret.setText(getModelElementLabel(modelElement));
		String link = modelElement.getLink();
		if (Util.isBlank(link) || modelElement.isPageLink()) {
			String path = Objects.requireNonNull(getPath(modelElement), "Path is null for " + modelElement.getLabel() + " / " + modelElement.getId());
			if (containmentReference == null) {
				ret.setLocation(path + "/index.html");
			} else {
				ret.setLocation(containmentReference.getName() + "/" + path + "/index.html");
			}
		} else {
			ret.setLocation(link);
		}
		ret.setDescription(modelElement.getTooltip());
		String icon = getIcon(modelElement);
		if (!Util.isBlank(icon)) {
			ret.setIcon(icon);
		}
				
		Map<EReference, List<Action>> accumulator = new HashMap<>();
		for (ElementEntry<Map<EReference, List<Action>>> childEntry: childEntries.values()) {
			for (Entry<EReference, List<Action>> referenceEntry: childEntry.getSemanticElement().entrySet()) {
				accumulator.computeIfAbsent(referenceEntry.getKey(), r -> new ArrayList<>()).addAll(referenceEntry.getValue());
			}
		}
		
		for (Entry<EReference, List<Action>> re: accumulator.entrySet()) {
			((Collection<Action>) ret.eGet(re.getKey())).addAll(re.getValue());
		}
		
		return ret;
	}	
	
	/**
	 * Creates actions for documented connections.
	 * @param resource
	 * @param connection
	 * @param childEntries
	 * @param containmentReference
	 * @return
	 */
	protected Action createConnectionAction(Resource resource, Connection connection, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries, EReference containmentReference) {
		if (!shallCreateAction(resource, connection, childEntries, containmentReference)) {
			return null;			
		}
		Action ret = createAction(resource, connection);
		ret.setId(connection.getId());
		String label = getModelElementLabel(connection);
		if (Util.isBlank(label)) {
			Node source = connection.getSource();
			String sourceLabel = getModelElementLabel(source);
			
			Node target = connection.getTarget();
			String targetLabel = getModelElementLabel(target);
			
			if (Util.isBlank(sourceLabel) && Util.isBlank(targetLabel)) {
				ret.setText("(unlabeled)");				
			} else {
				StringBuilder labelBuilder = new StringBuilder();
				if (!Util.isBlank(sourceLabel)) {
					labelBuilder.append(sourceLabel).append(" ");
				}
				labelBuilder.append("->");
				if (!Util.isBlank(targetLabel)) {
					labelBuilder.append(" ").append(targetLabel);
				}
				ret.setText(labelBuilder.toString());
			}
		} else {
			ret.setText(label);
		}
		String link = connection.getLink();
		if (Util.isBlank(link) || connection.isPageLink()) {
			String path = Objects.requireNonNull(getPath(connection), "Path is null for " + connection.getLabel() + " / " + connection.getId());
			if (containmentReference == null) {
				ret.setLocation(path + "/index.html");
			} else {
				ret.setLocation(containmentReference.getName() + "/" + path + "/index.html");
			}
		} else {
			ret.setLocation(link);
		}
		ret.setDescription(connection.getTooltip());
		String icon = getIcon(connection);
		if (!Util.isBlank(icon)) {
			ret.setIcon(icon);
		}
		
		return ret;				
	}	
	
	/**
	 * Adapter for {@link Action}s to resolve cross-references.
	 * @author Pavel
	 *
	 */
	public interface URIResolverAdapter extends Adapter {
		
		/**
		 * @param base Base action. Can be null.
		 * @return URI to the target action of this adapter relative to the base action.
		 */
		URI resolve(Action base);
		
	}
	
	protected boolean isActionForElement(Element element, Action action) {
		Object elementAdapter = EcoreUtil.getRegisteredAdapter(action, ElementAdapter.class);					
		return elementAdapter instanceof ElementAdapter && element.equals(((ElementAdapter) elementAdapter).getElement());			
	}
		
	protected void addURIResolverAdapters(Action root) {
		BiFunction<Label, URI, URI> uriResolver = org.nasdanika.html.model.app.util.Util.uriResolver(root);
		class URIResolverAdapterImpl extends AdapterImpl implements URIResolverAdapter {
						
			@Override
			public boolean isAdapterForType(Object type) {
				return URIResolverAdapter.class == type;
			}

			@Override
			public URI resolve(Action base) {
				Action targetAction = (Action) getTarget();
				if (targetAction == base) {
					return null;
				}				
				URI baseURI = base == null ? null : uriResolver.apply(base, null);
				return uriResolver.apply(targetAction, baseURI);
			}

		}
		
		root.eAdapters().add(new URIResolverAdapterImpl());
		TreeIterator<EObject> cit = root.eAllContents();
		while (cit.hasNext()) {
			EObject next = cit.next();
			if (next instanceof Action /* && EcoreUtil.getRegisteredAdapter(next, URIResolverAdapter.class) == null */) {
				next.eAdapters().add(new URIResolverAdapterImpl());
			}
		}			
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void resolve(
			Resource resource, 
			Element element, 
			Map<EReference, List<Action>> semanticElement,
			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,
			Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
		
		if (element instanceof Document) {
			// Resolving connection default roles by traversing from all nodes with default connection property set
			String defaultConnectionRoleProperty = getDefaultConnectionRoleProperty();
			if (!Util.isBlank(defaultConnectionRoleProperty)) {
				element.stream(connectionBase)
					.filter(Node.class::isInstance)
					.map(Node.class::cast)
					.forEach(node -> setDefaultConnectionRole(node, defaultConnectionRoleProperty, null, new HashSet<>()));
			}
			
			// Resolving assigned connection roles.
			String connectionRoleProperty = getConnectionRoleProperty();
			if (!Util.isBlank(connectionRoleProperty)) {
				element.stream(connectionBase)
					.filter(Connection.class::isInstance)
					.map(Connection.class::cast)
					.forEach(connection -> {
						String connectionRoleName = connection.getProperty(connectionRoleProperty);
						if (!Util.isBlank(connectionRoleName)) {
							EReference connectionRole = resolveConnectionRole(connectionRoleName);
							if (connectionRole == null) {
								connectionRoles.remove(connection);
							} else {
								connectionRoles.put(connection, connectionRole);
							}
						}
					});				
			}
			
			// Resolving semantic parents for nodes
			element.stream(connectionBase)
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.forEach(node -> {
					for (Connection inboundConnection: node.getInboundConnections()) {
						if (connectionRoles.containsKey(inboundConnection)) {
							Node candidate = inboundConnection.getSource();
							if (candidate != null && !node.equals(candidate) && !isSemanticAncestor(node, candidate, new HashSet<>())) {
								semanticParents.put(node, candidate);
								break;
							}
						}
					}					
				});
			
			// Rearranging actions for connections with role
			for (Connection connection: element.stream(connectionBase).filter(Connection.class::isInstance).map(Connection.class::cast).collect(Collectors.toList())) {
	            EReference connectionRole = getConnectionRole(connection);
	            if (connectionRole != null) {
		            Node connectionSource = connection.getSource();
		            if (connectionSource != null) {
			            Element toResolve = connectionSource;
			            if (isPageElement(connectionSource)) {
			            	String rootActionFlag = connectionSource.getModel().getRoot().getProperty(getRootActionFlagProperty());
			            	if (Util.isBlank(rootActionFlag) || !"false".equals(rootActionFlag)) {
			            		toResolve = connectionSource.getModel().getPage();
			            	}
			            }
			            
			            Optional<Action> sourceAction = resolver.apply(toResolve::equals).values().stream().flatMap(Collection::stream).findFirst();
			            if (sourceAction.isPresent()) {
			                Node connectionTarget = connection.getTarget();
			                if (connectionTarget != null && !connectionTarget.equals(connectionSource) && connectionSource.equals(getSemanticParent(connectionTarget))) {
			        			Comparator<Element> childComparator = loadChildComparator(connectionSource, getDefaultSort());
			        			List<Action> childActions = ((List<Action>) sourceAction.get().eGet(connectionRole));
								for (Action targetAction: resolver.apply(connectionTarget::equals).values().stream().flatMap(Collection::stream).collect(Collectors.toList())) {
									Object targetActionElementAdapter = EcoreUtil.getRegisteredAdapter(targetAction, ElementAdapter.class);					
									if (childComparator == null || targetActionElementAdapter == null || childActions.isEmpty()) {
										childActions.add(targetAction);
									} else {
										Element targetElement = ((ElementAdapter) targetActionElementAdapter).getElement();
										ListIterator<Action> listIterator = childActions.listIterator();
										while (listIterator.hasNext()) {
											Action next = listIterator.next();
											Object nextElementAdapter = EcoreUtil.getRegisteredAdapter(next, ElementAdapter.class);					
											if (nextElementAdapter instanceof ElementAdapter) {
												 Element nextElement = ((ElementAdapter) nextElementAdapter).getElement();
												 if (childComparator.compare(targetElement, nextElement) < 0) {
													 listIterator.previous(); // Going back so it target is inserted before next.
													 break;
												 }
											}
										}
										listIterator.add(targetAction);
									}
				                }
			                }
			            }
		            }
	            }				
			}
			
			// Rearranging actions for linked pages
			for (ModelElement modelElement: element.stream(connectionBase).filter(ModelElement.class::isInstance).map(ModelElement.class::cast).filter(ModelElement::isPageLink).collect(Collectors.toList())) {
	            Optional<Action> linkingElementAction = resolver.apply(modelElement::equals).values().stream().flatMap(Collection::stream).findFirst();
	            if (linkingElementAction.isPresent()) {
	            	Page linkedPage = modelElement.getLinkedPage();
	            	Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, linkedPage, childEntries.get(linkedPage).getChildEntries());
		            Optional<Action> linkedElementAction = resolver.apply(pageElementEntry.getKey()::equals).values().stream().flatMap(Collection::stream).findFirst();
		            if (linkedElementAction.isPresent()) {
		            	linkingElementAction.get().getAnonymous().addAll(linkedElementAction.get().getAnonymous());
		            	linkingElementAction.get().getChildren().addAll(linkedElementAction.get().getChildren());
		            	linkingElementAction.get().getNavigation().addAll(linkedElementAction.get().getNavigation());
		            	linkingElementAction.get().getSections().addAll(linkedElementAction.get().getSections());
		            }
	            }
			}
						
			// Adding URI resolver adapters
			for (EObject root: resource.getContents()) {
				if (root instanceof Action) {
					addURIResolverAdapters((Action) root);
				}
			}
		} else if (element instanceof Page) {
			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, (Page) element, childEntries);

			for (Action action: semanticElement.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(pageElementEntry.getKey(), a)).collect(Collectors.toList())) {
				// Embedded diagram
				String embeddedDiagram = generateEmbeddedDiagram(element, action, resolver);
				if (isEmbedDiagram(resource, element, childEntries)) {															
					addContent(action, embeddedDiagram);						
				}

				// TOC
				StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: pageElementEntry.getValue().entrySet()) { 
					tocBuilder.append(generateTableOfContents(action, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
				}					
				tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator());
				
				if (isGenerateTableOfContents(resource, element, childEntries)) {
					addContent(action, tocBuilder.toString());
				}
				
				EObject doc = getDocumentation(resource, element, embeddedDiagram, childEntries, tocBuilder.toString());
				if (doc == null) {
					String rootTooltip = pageElementEntry.getKey().getTooltip();
					if (!Util.isBlank(rootTooltip)) {
						addContent(action, rootTooltip);
					}
				} else {
					action.getContent().add(doc);
				}					
			}			
		} else if (element instanceof ModelElement) {
			for (Action action: semanticElement.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(element, a)).collect(Collectors.toList())) {
				if (element instanceof Connection) {
					// Source and target
					Table table = BootstrapFactory.eINSTANCE.createTable();
					table.getAttributes().put("style", createText("width:auto"));
					
					TableRow sourceRow = BootstrapFactory.eINSTANCE.createTableRow();
					table.getRows().add(sourceRow);
					
					TableCell sourceHeader = BootstrapFactory.eINSTANCE.createTableCell();
					sourceHeader.setHeader(true);
					sourceRow.getCells().add(sourceHeader);
					sourceHeader.getContent().add(createText("Source"));
										
					TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
					sourceRow.getCells().add(sourceCell);
					
					Node source = ((Connection) element).getSource();
					String sourceLabel = getModelElementLabel(source);
					if (Util.isBlank(sourceLabel)) {
						sourceLabel = "(unlabeled)";
					} 
					String sourceLink = getModelElementLink(source, action, resolver);
					if (sourceLink == null) {
						sourceCell.getContent().add(createText(sourceLabel));						
					} else {
						sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
					}
					
					TableRow targetRow = BootstrapFactory.eINSTANCE.createTableRow();
					table.getRows().add(targetRow);
					
					TableCell targetHeader = BootstrapFactory.eINSTANCE.createTableCell();
					targetHeader.setHeader(true);
					targetRow.getCells().add(targetHeader);
					targetHeader.getContent().add(createText("Target"));
										
					TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
					targetRow.getCells().add(targetCell);
					
					Node target = ((Connection) element).getTarget();
					String targetLabel = getModelElementLabel(target);
					if (Util.isBlank(targetLabel)) {
						targetLabel = "(unlabeled)";
					} 
					String targetLink = getModelElementLink(target, action, resolver);
					if (targetLink == null) {
						targetCell.getContent().add(createText(targetLabel));						
					} else {
						targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
					}					

					action.getContent().add(table);
				} else if (element instanceof Node) {
					Node node = (Node) element;
					EReference connectionsActionContainmentReference = getConnectionsActionContainmentReference(node);
					if (connectionsActionContainmentReference != null) {
						List<Connection> inboundConnections = node.getInboundConnections().stream().filter(c -> getConnectionRole(c) == null).collect(Collectors.toList());
						if (!inboundConnections.isEmpty()) {
							Table table = BootstrapFactory.eINSTANCE.createTable();
							table.setBordered(true);
							table.getAttributes().put("style", createText("width:auto"));
							
							TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
							table.getRows().add(headerRow);
												
							TableCell labelHeader = BootstrapFactory.eINSTANCE.createTableCell();
							labelHeader.setHeader(true);
							headerRow.getCells().add(labelHeader);
							labelHeader.getContent().add(createText("Label"));
	
							TableCell sourceHeader = BootstrapFactory.eINSTANCE.createTableCell();
							sourceHeader.setHeader(true);
							headerRow.getCells().add(sourceHeader);
							sourceHeader.getContent().add(createText("Source"));
	
							TableCell tooltipHeader = BootstrapFactory.eINSTANCE.createTableCell();
							tooltipHeader.setHeader(true);
							headerRow.getCells().add(tooltipHeader);
							tooltipHeader.getContent().add(createText("Tooltip"));
							
							for (Connection inboundConnection: inboundConnections) { // TODO - sorting
								TableRow connectionRow = BootstrapFactory.eINSTANCE.createTableRow();
								table.getRows().add(connectionRow);
													
								TableCell labelCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(labelCell);
								String connectionLabel = getModelElementLabel(inboundConnection);
								if (Util.isBlank(connectionLabel) && hasDocumentation(resource, inboundConnection, childEntries)) {
									connectionLabel = "(unlabeled)";
								}
								if (!Util.isBlank(connectionLabel)) {
									String connectionLink = getModelElementLink(inboundConnection, action, resolver);
									if (connectionLink == null) {
										labelCell.getContent().add(createText(connectionLabel));						
									} else {
										labelCell.getContent().add(createText("<a href=\"" + connectionLink + "\">" + connectionLabel + "</a>"));												
									}
								}
		
								TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(sourceCell);

								Node source = inboundConnection.getSource();
								String sourceLabel = getModelElementLabel(source);
								if (Util.isBlank(sourceLabel)) {
									sourceLabel = "(unlabeled)";
								} 
								String sourceLink = getModelElementLink(source, action, resolver);
								if (sourceLink == null) {
									sourceCell.getContent().add(createText(sourceLabel));						
								} else {
									sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
								}
		
								TableCell tooltipCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(tooltipCell);
								String connectionTooltip = inboundConnection.getTooltip();
								if (!Util.isBlank(connectionTooltip)) {
									tooltipCell.getContent().add(createText(connectionTooltip));
								}								
							}							
							
							Action inboundConnectionsAction = AppFactory.eINSTANCE.createAction();
							inboundConnectionsAction.setText("Inbound connections");
							if (connectionsActionContainmentReference == AppPackage.Literals.ACTION__SECTIONS) {
								inboundConnectionsAction.setName("inbound-connections");
							} else {
								inboundConnectionsAction.setLocation("inbound-connections.html");								
							}
							inboundConnectionsAction.getContent().add(table);
							((Collection<Action>) action.eGet(connectionsActionContainmentReference)).add(inboundConnectionsAction);
						}
						
						List<Connection> outboundConnections = node.getOutboundConnections().stream().filter(c -> getConnectionRole(c) == null).collect(Collectors.toList());
						if (!outboundConnections.isEmpty()) {
							Table table = BootstrapFactory.eINSTANCE.createTable();
							table.setBordered(true);
							table.getAttributes().put("style", createText("width:auto"));
							
							TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
							table.getRows().add(headerRow);
												
							TableCell labelHeader = BootstrapFactory.eINSTANCE.createTableCell();
							labelHeader.setHeader(true);
							headerRow.getCells().add(labelHeader);
							labelHeader.getContent().add(createText("Label"));
	
							TableCell targetHeader = BootstrapFactory.eINSTANCE.createTableCell();
							targetHeader.setHeader(true);
							headerRow.getCells().add(targetHeader);
							targetHeader.getContent().add(createText("Target"));
	
							TableCell tooltipHeader = BootstrapFactory.eINSTANCE.createTableCell();
							tooltipHeader.setHeader(true);
							headerRow.getCells().add(tooltipHeader);
							tooltipHeader.getContent().add(createText("Tooltip"));
							
							for (Connection outboundConnection: outboundConnections) { // TODO - sorting
								TableRow connectionRow = BootstrapFactory.eINSTANCE.createTableRow();
								table.getRows().add(connectionRow);
													
								TableCell labelCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(labelCell);
								String connectionLabel = getModelElementLabel(outboundConnection);
								if (Util.isBlank(connectionLabel) && hasDocumentation(resource, outboundConnection, childEntries)) {
									connectionLabel = "(unlabeled)";
								}
								if (!Util.isBlank(connectionLabel)) {
									String connectionLink = getModelElementLink(outboundConnection, action, resolver);
									if (connectionLink == null) {
										labelCell.getContent().add(createText(connectionLabel));						
									} else {
										labelCell.getContent().add(createText("<a href=\"" + connectionLink + "\">" + connectionLabel + "</a>"));												
									}
								}
		
								TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(targetCell);

								Node target = outboundConnection.getTarget();
								String targetLabel = getModelElementLabel(target);
								if (Util.isBlank(targetLabel)) {
									targetLabel = "(unlabeled)";
								} 
								String targetLink = getModelElementLink(target, action, resolver);
								if (targetLink == null) {
									targetCell.getContent().add(createText(targetLabel));						
								} else {
									targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
								}
		
								TableCell tooltipCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(tooltipCell);
								String connectionTooltip = outboundConnection.getTooltip();
								if (!Util.isBlank(connectionTooltip)) {
									tooltipCell.getContent().add(createText(connectionTooltip));
								}								
							}							
							
							Action outboundConnectionsAction = AppFactory.eINSTANCE.createAction();
							outboundConnectionsAction.setText("Outbound connections");
							if (connectionsActionContainmentReference == AppPackage.Literals.ACTION__SECTIONS) {							
								outboundConnectionsAction.setName("outbound-connections");
							} else {
								outboundConnectionsAction.setLocation("outbound-connections.html");								
							}
							outboundConnectionsAction.getContent().add(table);
							((Collection<Action>) action.eGet(connectionsActionContainmentReference)).add(outboundConnectionsAction);
						}
					}
				}
				
				// Embedded diagram
				String embeddedDiagram = null;
				
				Page linkedPage = ((ModelElement) element).getLinkedPage();
				if (linkedPage != null) {
					embeddedDiagram = generateEmbeddedDiagram(linkedPage, action, resolver);
					if (isEmbedDiagram(resource, element, childEntries)) {															
						addContent(action, embeddedDiagram);						
					}
				}								
				
				StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) { 						
					Element child = childEntry.getKey();
					if (child instanceof ModelElement) {
						tocBuilder.append(generateTableOfContents(action, (ModelElement) child, childEntry.getValue().getChildEntries(), resolver));
					}
				}								
				tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator());
				
				if (isGenerateTableOfContents(resource, element, childEntries)) {
					addContent(action, tocBuilder.toString());
				}
				
				EObject doc = getDocumentation(resource, element, embeddedDiagram, childEntries, tocBuilder.toString());
				if (doc == null) {
					String tooltip = ((ModelElement) element).getTooltip();
					if (!Util.isBlank(tooltip)) {
						addContent(action, tooltip);
					}
				} else {					
					action.getContent().add(doc);
				}
			}
		}
	}

	/**
	 * @param node
	 * @param candidate
	 * @param hashSet
	 * @return true if there is a semantic parent path from the descendant to the ancestor
	 */
	protected boolean isSemanticAncestor(ModelElement ancestor, ModelElement descendant, HashSet<ModelElement> traversed) {
		if (descendant == null || ancestor == null) {
			return false;
		}
		if (traversed.add(descendant)) {
			if (descendant.equals(ancestor)) {
				return true; // loop
			}
			ModelElement semanticParent = semanticParents.get(descendant);
			if (semanticParent == null) {
				return false;
			}
			if (semanticParent.equals(ancestor)) {
				return true;
			}
			return isSemanticAncestor(ancestor, semanticParent, traversed);
		}
		return false;
	}

	/**
	 * 
	 * @param node
	 * @param defaultConnectionRoleProperty
	 */
	protected void setDefaultConnectionRole(Node node, String defaultConnectionRoleProperty, EReference defaultConnectionRole, Set<Node> traversed) {
		if (traversed.add(node)) {
			String defaultConnectionRoleName = node.getProperty(defaultConnectionRoleProperty);			
			if (defaultConnectionRole == null) { // Process only nodes with default connection property set
				if (Util.isBlank(defaultConnectionRoleName)) {
					return;
				}
				defaultConnectionRole = resolveConnectionRole(defaultConnectionRoleName);
			} else if (!Util.isBlank(defaultConnectionRoleName)) {
				return;
			}
				
			for (Connection outboundConnection: node.getOutboundConnections()) {
				connectionRoles.put(outboundConnection, defaultConnectionRole);
				Node target = outboundConnection.getTarget();
				if (target != null && !target.equals(node)) {
					setDefaultConnectionRole(target, defaultConnectionRoleProperty, defaultConnectionRole, traversed);								
				}
			}
			
			Page linkedPage = node.getLinkedPage();
			if (linkedPage != null) {
				// TODO - getPageElementEntry(null, linkedPage, null)
			}
		}
	}	
	
	protected EReference resolveConnectionRole(String connectionRole) {
		switch (connectionRole) {
		case "none": 
			return null;
		case "child":
			return AppPackage.Literals.LABEL__CHILDREN;
		case "anonymous":
			return AppPackage.Literals.ACTION__ANONYMOUS;
		case "navigation":
			return AppPackage.Literals.ACTION__NAVIGATION;
		case "section":
			return AppPackage.Literals.ACTION__SECTIONS;
		default: 
			throw new IllegalArgumentException("Unsupported connection role: " + connectionRole);
		}
	}		

	protected String getConnectionRoleProperty() {
		return "role";
	}
	
	protected String getDefaultConnectionRoleProperty() {
		return "default-connection-role";
	}

	protected EReference getConnectionRole(Connection connection) {
		return connectionRoles.get(connection);
	}	

	protected EReference getConnectionsActionContainmentReference(Node node) {
		return AppPackage.Literals.ACTION__SECTIONS;
	}

	protected String generateEmbeddedDiagram(
			Element element, 
			Action action,			
			Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
		try {
			Document toEmbed = Document.create(true, null);
			Page page = (Page) element;
			toEmbed.getPages().add(page);
			
			Consumer<Element> visitor = e -> processEmbeddedDiagramElement(e, action, resolver);			
			toEmbed.accept(visitor, connectionBase);			
			return toEmbed.toHtml(null, getDiagramViewer());
		} catch (Exception  e) {
			return "Error embedding diagram: " + e;
		}
	}
	
	/**
	 * Processes an element of the embedded diagram. 
	 * This implementation adds a link. Override for additional behavior, e.g. change background color based on external information.
	 * @param element
	 * @param action
	 * @param resolver
	 */
	protected void processEmbeddedDiagramElement(
			Element element, 
			Action action,			
			Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
		
		if (element instanceof ModelElement) {
			ModelElement modelElement = (ModelElement) element;
			String link = getModelElementLink(modelElement, action, resolver);
			if (!Util.isBlank(link)) {
				modelElement.setLink(link);
			}
		}		
	}
	
	protected String getListOpenTag(Element element) {
		return "<ul>";
	}
	
	protected String getListCloseTag(Element element) {
		return "</ul>";
	}	
	
	protected String generateTableOfContents(
			Action pageAction, 
			ModelElement modelElement, 
			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,
			Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {		
		
		String label = getModelElementLabel(modelElement);
		StringBuilder ret = new StringBuilder();
		if (Util.isBlank(label)) {
			if (modelElement instanceof Layer && childEntries != null) {
				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) {
					ret.append(generateTableOfContents(pageAction, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
				}
			}
		} else {		
			ret.append("<li>").append(System.lineSeparator());
			String link = getModelElementLink(modelElement, pageAction, resolver);
			if (Util.isBlank(link)) {
				ret.append(label);
			} else {
				ret
					.append("<a href=\"")
					.append(link)
					.append("\">")
					.append(label)
					.append("</a>");				
			}			
			
			String tooltip = modelElement.getTooltip();
			if (!Util.isBlank(tooltip)) {
				ret.append(" - ").append(tooltip).append(System.lineSeparator());
			}

			if (modelElement instanceof Layer) {
				if (childEntries != null && !childEntries.isEmpty()) {
					ret.append(getListOpenTag(modelElement)).append(System.lineSeparator());
					for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) {
						ret.append(generateTableOfContents(pageAction, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
					}
					ret.append(getListCloseTag(modelElement)).append(System.lineSeparator());
				}
			}
			ret.append("</li>").append(System.lineSeparator());
		}
		
		return ret.toString();
	}

	/**
	 * Resolves link to the model element from this action.
	 * @param modelElement Model element
	 * @param action Source action 
	 * @param resolver Resolver
	 * @return
	 */
	protected String getModelElementLink(ModelElement modelElement, Action action, Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
		if (shallCreateLink(modelElement)) {
			// Link by cross-reference
			String crossReferenceProperty = getCrossReferenceProperty();
			if (!Util.isBlank(crossReferenceProperty)) {
				String crossReferencePropertyValue = modelElement.getProperty(crossReferenceProperty);
				if (!Util.isBlank(crossReferencePropertyValue)) {
					int colonIdx = crossReferencePropertyValue.indexOf(":");
					if (colonIdx != -1) {
						Predicate<Element> predicate = e -> {
							if (e instanceof ModelElement) {
								String prop = ((ModelElement) e).getProperty(crossReferencePropertyValue.substring(0, colonIdx));
								if (!Util.isBlank(prop)) {
									return prop.equals(crossReferencePropertyValue.substring(colonIdx + 1));
								}
							}
							return false;
						};
						Map<EReference, List<Action>> actionMap = resolver.apply(predicate);
						for (Action eAction: actionMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList())) {
							Object uriResolverAdapter = EcoreUtil.getRegisteredAdapter(eAction, URIResolverAdapter.class);
							if (uriResolverAdapter instanceof URIResolverAdapter) {
								URI eURI = ((URIResolverAdapter) uriResolverAdapter).resolve(action);
								if (eURI != null) {
									return eURI.toString();
								}
							}
						}
					}
				}
			}
			
			// Link by equality
			Map<EReference, List<Action>> actionMap = resolver.apply(modelElement::equals);
			for (Action eAction: actionMap.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(modelElement, a)).collect(Collectors.toList())) {
				Object uriResolverAdapter = EcoreUtil.getRegisteredAdapter(eAction, URIResolverAdapter.class);
				if (uriResolverAdapter instanceof URIResolverAdapter) {
					URI eURI = ((URIResolverAdapter) uriResolverAdapter).resolve(action);
					if (eURI != null) {
						return eURI.toString();
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * @param modelElement
	 * @return true if an action shall be created for a given model element. This implementation returns true for elements without external links and without cross-references.
	 * False is returned for undocumented connections.
	 */
	protected boolean shallCreateAction(Resource resource, ModelElement modelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries, EReference containmentReference) {
		return (Util.isBlank(modelElement.getLink()) || modelElement.isPageLink())
				&& (Util.isBlank(getCrossReferenceProperty()) || Util.isBlank(modelElement.getProperty(getCrossReferenceProperty())))
				&& !(modelElement instanceof Connection && !hasDocumentation(resource, modelElement, childEntries)); // No actions for undocumented connections
	}
	
	protected String getCrossReferenceProperty() {
		return "xref";
	}
		
	/**
	 * @param modelElement
	 * @return true if a link shall be created for a given model element. This implementation returns true for {@link Node}s and {@link Connection}s without a cross-reference or a pre-existing link which is not a page link.
	 */
	protected boolean shallCreateLink(ModelElement modelElement) {
		return (modelElement instanceof Node || modelElement instanceof Connection) && (Util.isBlank(modelElement.getLink()) || modelElement.isPageLink());
	}
	
	/**
	 * @return If true, a {@link Page} diagram is added to the content of the {@link Action} created from that page. This implementation returns true.
	 */
	protected boolean isEmbedDiagram(Resource resource, Element element, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
		return !hasDocumentation(resource, element, childEntries);
	}
		
	/**
	 * @return If true, a table of contents is generated for a {@link Page} diagram. This implementation returns true;
	 */
	protected boolean isGenerateTableOfContents(Resource resource, Element element, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
		return !hasDocumentation(resource, element, childEntries);
	}	
	
	/**
	 * Creates a new action using factory. Override to, say, use a prototype action.
	 * @param resource
	 * @param element
	 * @return
	 */
	protected Action createAction(Resource resource, Element element) {
		if (element instanceof ModelElement) {
			String actionProperty = ((ModelElement) element).getProperty("action");
			if (!Util.isBlank(actionProperty)) {
				URI actionURI = URI.createURI(actionProperty);
				actionURI = actionURI.resolve(resource.getURI());
				Action prototype = EcoreUtil.copy((Action) resourceSet.getEObject(actionURI, true));
				prototype.setId(UUID.randomUUID().toString());
				prototype.eAdapters().add(createElementAdapter(element));
				return prototype;
			}
		}
		Action action = AppFactory.eINSTANCE.createAction();
		action.eAdapters().add(createElementAdapter(element));		
		return action;
	}
	
	@Override
	protected Map<Element, ElementEntry<Map<EReference, List<Action>>>> getLinkedPageChildMappings(
			Resource resource,
			Page linkedPage,
			ElementEntry<Map<EReference, List<Action>>> linkedPageEntry) {
		
		if (linkedPage == null) {
			return super.getLinkedPageChildMappings(resource, linkedPage, linkedPageEntry);
		}
		
		Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> linkedPageElementEntry = getPageElementEntry(resource, linkedPage, linkedPageEntry.getChildEntries());
		return linkedPageElementEntry.getValue(); // Child entries of the page element.
	}

}
