package org.nasdanika.html.model.app.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Function;

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
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.ncore.util.NcoreUtil;

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
						resource.getContents().addAll(referenceEntry.getValue());
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
			
		Map<EReference, List<Action>> accumulator = new HashMap<>();
		for (ElementEntry<Map<EReference, List<Action>>> childEntry: childEntries.values()) {
			for (Entry<EReference, List<Action>> referenceEntry: childEntry.getSemanticElement().entrySet()) {
				accumulator.computeIfAbsent(referenceEntry.getKey(), r -> new ArrayList<>()).addAll(referenceEntry.getValue());
			}
		}
		
		for (Entry<EReference, List<Action>> re: accumulator.entrySet()) {
			List<Action> actions = re.getValue();
			Comparator<Action> comparator = getActionComparator(re.getKey());
			if (comparator != null) {
				actions.sort(comparator);
			}
			((Collection<Action>) action.eGet(re.getKey())).addAll(actions);
		}
		
		return Collections.singletonMap(containmentReference, Collections.singletonList(action));
	}
	
	protected Comparator<Action> getActionComparator(EReference containmentReference) {
		return new Comparator<Action>() {

			@Override
			public int compare(Action a, Action b) {
				if (a == b) {
					return 0;
				}
				if (a == null) {
					return 1;
				}
				if (b == null) {
					return -1;
				}
				if (Util.isBlank(a.getText())) {
					if (Util.isBlank(b.getText())) {
						URI aUri = NcoreUtil.getUri(a);
						URI bUri = NcoreUtil.getUri(b);
						if (aUri == null) {
							if (bUri == null) {
								return a.hashCode() - b.hashCode();
							}
							return 1;
						}
						if (bUri == null) {
							return -1;
						}
						return aUri.toString().compareTo(bUri.toString());
					}
					
					return 1;
				}
				
				if (Util.isBlank(b.getText())) {
					return -1;
				}
				
				return a.getText().compareTo(b.getText());
			}

		};
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
		Action ret = createAction(resource, page);
		ret.setText(page.getName());	
		ret.setId(page.getId());
		ret.setLocation("pages/" + page.getId() + "/index.html");
		
		// Embedded diagram
		try {
			Document toEmbed = Document.create(true);
			toEmbed.getPages().add(page);
			// TODO - cross-linking - at resolve or even later?
			
			String embeddedDiagram = toEmbed.toHtml(null, getDiagramViewer());
			System.out.println(embeddedDiagram);
			addContent(ret, embeddedDiagram);
		} catch (Exception  e) {
			addContent(ret, "Error embedding diagram: " + e);
			e.printStackTrace();
		}
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
		return createModelElementAction(resource, layer, childEntries, containmentReference);
	}
	
	protected Action createModelElementAction(Resource resource, ModelElement modelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries, EReference containmentReference) {
		String label = modelElement.getLabel();
		if (Util.isBlank(label)) {
			return null;
		}
		Action ret = createAction(resource, modelElement);
		ret.setId(modelElement.getId());
		ret.setText(Jsoup.parse(label).text());
		String link = modelElement.getLink();
		if (Util.isBlank(link)) {
			ret.setLocation(containmentReference.getName() + "/" + getPath(modelElement) + "/index.html");
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
	
	@Override
	protected void resolve(
			Resource resource, 
			Element element, 
			Map<EReference, List<Action>> semanticElement,
			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,
			Function<Element, Map<EReference, List<Action>>> resolver) {
		// Content - add an inline diagram, cross-referencing in the diagram
		
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
				return prototype;
			}
		}
		return AppFactory.eINSTANCE.createAction();
	}

}
