package org.nasdanika.html.model.app.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
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
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;

/**
 * Creates an action model from Drawio documents
 * @author Pavel
 *
 */
public class AppDrawioResourceFactory extends DrawioResourceFactory<Map<EReference,List<Action>>> {

	private ResourceSet resourceSet;

	public AppDrawioResourceFactory(ConnectionBase connectionBase, ResourceSet resourceSet) {
		super(connectionBase);
		this.resourceSet = resourceSet;
	}

	@SuppressWarnings("unchecked")
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
				for (ElementEntry<Map<EReference, List<Action>>> childEntry: childEntries.values()) {
					for (Entry<EReference, List<Action>> referenceEntry: childEntry.getSemanticElement().entrySet()) {						
						for (Action rootAction: referenceEntry.getValue()) {
							resource.getContents().add(rootAction);
							addURIResolverAdapters(rootAction);
						}
					}
				}				
			} else {
				resource.getContents().add(action);
				addURIResolverAdapters(action);
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
			
		Map<EReference, List<Action>> accumulator = new HashMap<>();
		for (ElementEntry<Map<EReference, List<Action>>> childEntry: childEntries.values()) {
			for (Entry<EReference, List<Action>> referenceEntry: childEntry.getSemanticElement().entrySet()) {
				accumulator.computeIfAbsent(referenceEntry.getKey(), r -> new ArrayList<>()).addAll(referenceEntry.getValue());
			}
		}
		
		for (Entry<EReference, List<Action>> re: accumulator.entrySet()) {
			((Collection<Action>) action.eGet(re.getKey())).addAll(re.getValue());
		}
		
		return Collections.singletonMap(containmentReference, Collections.singletonList(action));
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
			if (next instanceof Action) {
				next.eAdapters().add(new URIResolverAdapterImpl());
			}
		}
			
	}
	
	protected EReference getContainmentReference(Resource resource, Element element) {
		return AppPackage.Literals.LABEL__CHILDREN;
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
	
	protected EObject getDocumentation(Resource resource, ModelElement modelElement) {
		String documentationProperty = getDocumentationProperty();
		if (Util.isBlank(documentationProperty)) {
			return null;
		}
		
		String docProp = modelElement.getProperty(documentationProperty);
		if (Util.isBlank(docProp)) {
			return null;
		}
		
		Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		URI docURI = URI.createURI(docProp);
		docURI = docURI.resolve(resource.getURI());
		org.nasdanika.exec.content.Resource docResource = ContentFactory.eINSTANCE.createResource();
		docResource.setLocation(docURI.toString());
		interpolator.setSource(docResource);
		ret.setSource(interpolator);
		ret.setStyle(true);
		
		return ret;
	}
		
	protected Action createPageAction(Resource resource, Page page, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
		Model model = page.getModel();
		Map<Element, ElementEntry<Map<EReference, List<Action>>>> modelChildEntries = childEntries.get(model).getChildEntries();
		Root root = model.getRoot();
		Map<Element, ElementEntry<Map<EReference, List<Action>>>> rootChildEntries = modelChildEntries.get(root).getChildEntries();

		Action ret = createModelElementAction(resource, root, rootChildEntries, null);
		ret.setText(page.getName());	
		ret.setLocation("pages/" + page.getId() + "/index.html");
		return ret;
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
		return Util.isBlank(action.getText()) ? null : action;
	}
	
	protected Action createModelElementAction(Resource resource, ModelElement modelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries, EReference containmentReference) {
		Action ret = createAction(resource, modelElement);
		ret.setId(modelElement.getId());
		String label = modelElement.getLabel();
		if (!Util.isBlank(label)) {
			ret.setText(Jsoup.parse(label).text());
		}
		String link = modelElement.getLink();
		if (Util.isBlank(link)) {
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
		
		EObject doc = getDocumentation(resource, modelElement);
		if (doc != null) {
			ret.getContent().add(doc);
		}
		return ret;
	}	
	
	protected Action createConnectionAction(Resource resource, Connection connection, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries, EReference containmentReference) {
		return null;
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
	
	@Override
	protected void resolve(
			Resource resource, 
			Element element, 
			Map<EReference, List<Action>> semanticElement,
			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,
			Function<Element, Map<EReference, List<Action>>> resolver) {

		if (element instanceof Page) {
			if (isEmbedDiagram()) {				
				// Embedded diagram
				Root root = ((Page) element).getModel().getRoot();
				for (Action action: semanticElement.values().stream().flatMap(Collection::stream).collect(Collectors.toList())) {
					Object elementAdapter = EcoreUtil.getRegisteredAdapter(action, ElementAdapter.class);					
					if (elementAdapter instanceof ElementAdapter && root.equals(((ElementAdapter) elementAdapter).getElement())) {
						try {
							Document toEmbed = Document.create(true, null);
							Page page = (Page) element;
							toEmbed.getPages().add(page);
							
							Consumer<Element> visitor = e -> {
								if (e instanceof ModelElement) {
									String link = getModelElementLink((ModelElement) e, action, resolver);
									if (!Util.isBlank(link)) {
										((ModelElement) e).setLink(link);
									}
								}
							};
							
							toEmbed.accept(visitor, null);
							
							String embeddedDiagram = toEmbed.toHtml(null, getDiagramViewer());
							System.out.println(embeddedDiagram);
							addContent(action, embeddedDiagram);
						} catch (Exception  e) {
							addContent(action, "Error embedding diagram: " + e);
							e.printStackTrace();
						}
					}
				}
			}
			
			if (isGenerateTableOfContents()) {
				for (Action action: semanticElement.values().stream().flatMap(Collection::stream).collect(Collectors.toList())) {
					StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
					Model model = ((Page) element).getModel();
					Map<Element, ElementEntry<Map<EReference, List<Action>>>> modelChildEntries = childEntries.get(model).getChildEntries();
					Root root = model.getRoot();
					Map<Element, ElementEntry<Map<EReference, List<Action>>>> rootChildEntries = modelChildEntries.get(root).getChildEntries();
					for (Layer layer: root.getLayers()) { 
						tocBuilder.append(generateTableOfContents(action, layer, rootChildEntries.get(layer).getChildEntries(), resolver));
					}									
					
					addContent(action, tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator()).toString());
				}				
			}
		}
		
		if (element instanceof Layer) {
			if (isGenerateTableOfContents()) {
				for (Action action: semanticElement.values().stream().flatMap(Collection::stream).collect(Collectors.toList())) {
					StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
					for (ModelElement modelElement: ((Layer) element).getElements()) { 
						tocBuilder.append(generateTableOfContents(action, modelElement, childEntries, resolver));
					}									
					
					addContent(action, tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator()).toString());
				}				
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
			Function<Element, Map<EReference, List<Action>>> resolver) {		
		
		String label = modelElement.getLabel();
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
				ret.append(Jsoup.parse(label).text());
			} else {
				ret
					.append("<a href=\"")
					.append(link)
					.append("\">")
					.append(Jsoup.parse(label).text())
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
	protected String getModelElementLink(ModelElement modelElement, Action action, Function<Element, Map<EReference, List<Action>>> resolver) {
		if (shallCreateLink(modelElement)) {
			Map<EReference, List<Action>> actionMap = resolver.apply(modelElement);
			for (Action eAction: actionMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList())) {
				Object eAdapter = EcoreUtil.getRegisteredAdapter(eAction, ElementAdapter.class);					
				if (eAdapter instanceof ElementAdapter && modelElement.equals(((ElementAdapter) eAdapter).getElement())) {
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
		return null;
	}
		
	/**
	 * @param modelElement
	 * @return true if a link shall be created for a given model element. This implementation returns true for {@link Node}s and {@link Connection}s without a pre-existing link.
	 */
	protected boolean shallCreateLink(ModelElement modelElement) {
		return (modelElement instanceof Node || modelElement instanceof Connection) && Util.isBlank(modelElement.getLink());
	}
	
	/**
	 * @return If true, a {@link Page} diagram is added to the content of the {@link Action} created from that page. This implementation returns true.
	 */
	protected boolean isEmbedDiagram() {
		return true;
	}
		
	/**
	 * @return If true, a table of contents is generated for a {@link Page} diagram. This implementation returns true;
	 */
	protected boolean isGenerateTableOfContents() {
		return true;
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

}
