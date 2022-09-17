package org.nasdanika.html.model.app.drawio;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.DrawioResource;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.drawio.comparators.AngularNodeComparator;
import org.nasdanika.drawio.comparators.LabelModelElementComparator;
import org.nasdanika.drawio.comparators.PropertyModelElementComparator;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.xml.sax.SAXException;

public class ResourceFactory implements Factory {
	
	private ResourceSet resourceSet;
	private ConnectionBase connectionBase;
	
	public ResourceFactory(ConnectionBase connectionBase, ResourceSet resourceSet) {
		this.connectionBase = connectionBase;
		this.resourceSet = resourceSet;
	}
	

	@Override
	public Resource createResource(URI uri) {
		return new DrawioResource<ElementProcessor>(uri, new ProcessorFactory(uri, this)) {

			@Override
			protected Stream<EObject> getSemanticElements(Map<Element,ProcessorInfo<ElementProcessor>> registry) {
				return ResourceFactory.this.getSemanticElements(registry);
			}
			
			protected Document loadDocument(InputStream inputStream) throws IOException, ParserConfigurationException, SAXException {
				return ResourceFactory.this.loadDocument(inputStream, getURI());
			}			
			
		};
	}
	
	protected Document loadDocument(InputStream inputStream, URI uri) throws IOException, ParserConfigurationException, SAXException {
		return Document.load(inputStream, uri);
	}	
	
	protected Stream<EObject> getSemanticElements(Map<Element,ProcessorInfo<ElementProcessor>> registry) {		
		return registry
			.values()
			.stream()
			.map(ProcessorInfo::getProcessor)
			.filter(DocumentProcessor.class::isInstance)
			.map(DocumentProcessor.class::cast)
			.flatMap(DocumentProcessor::build);
	}
	
	/**
	 * Base URI for resolving action relative references. This implementation returns a random absolute UR.
	 * @return
	 */
	protected URI getBaseURI(URI resourceURI) {
		return URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/");
	}

	protected ElementProcessor createProcessor(
			URI uri,
			ProcessorConfig<ElementProcessor> config,
			Consumer<Consumer<ProcessorInfo<ElementProcessor>>> parentProcessorInfoCallbackConsumer,
			Consumer<Consumer<Map<Element, ProcessorInfo<ElementProcessor>>>> registryCallbackConsumer) {
		
		URI baseURI = getBaseURI(uri);
		ElementProcessor processor;
		if (config.getElement() instanceof Document) {
			processor = createDocumentProcessor(uri, config, baseURI);
		} else if (config.getElement() instanceof Page) {
			processor = createPageProcessor(uri, config, baseURI);
		} else if (config.getElement() instanceof Model) {
			processor = createModelProcessor(uri, config, baseURI);
		} else if (config.getElement() instanceof Root) {
			processor = createRootProcessor(uri, config, baseURI);
		} else if (config.getElement() instanceof Node) {
			processor = createNodeProcessor(uri, config, baseURI);
		} else if (config.getElement() instanceof org.nasdanika.drawio.Connection) {
			processor = createConnectionProcessor(uri, config, baseURI);
		} else if (config.getElement() instanceof Layer) {
			processor = createLayerProcessor(uri, config, baseURI);
		} else if (config.getElement() instanceof Model) {
			processor = createModelProcessor(uri, config, baseURI);			
		} else {
			processor = null;
		}
		
		if (processor != null) {
			parentProcessorInfoCallbackConsumer.accept(processor::setParentInfo);
			registryCallbackConsumer.accept(processor::setRegistry);
		}

		return processor;
	}

	protected ElementProcessor createLayerProcessor(URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		return new LayerProcessor(this, uri, config, baseURI);
	}

	protected ElementProcessor createConnectionProcessor(URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		return new ConnectionProcessor(this, uri, config, baseURI);
	}

	protected ElementProcessor createDocumentProcessor(URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		return new DocumentProcessor(this, uri, config, baseURI);
	}

	protected ElementProcessor createNodeProcessor(URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		return new NodeProcessor(this, uri, config, baseURI);
	}

	protected ElementProcessor createRootProcessor(URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		return new RootProcessor(this, uri, config, baseURI);
	}

	protected ElementProcessor createModelProcessor(URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		return new ModelProcessor(this, uri, config, baseURI);
	}

	protected ElementProcessor createPageProcessor(URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		return new PageProcessor(this, uri, config, baseURI);
	}
	
	protected EReference resolveRole(String roleName) {
		switch (roleName) {
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
			throw new IllegalArgumentException("Unsupported connection role: " + roleName);
		}
	}		

	protected String getPathProperty() {
		return "path";
	}

	protected String getIconProperty() {
		return "icon";
	}

	protected String getRoleProperty() {
		return "role";
	}

	protected String getTargetRoleProperty() {
		return "target-role";
	}

	protected String getDefaultConnectionTargetRoleProperty() {
		return "default-connection-target-role";
	}
	
	protected String getDefaultConnectionRoleProperty() {
		return "default-connection-role";
	}
	
	protected ConnectionBase getConnectionBase() {
		return connectionBase;
	}
	
	protected String getSelectorProperty() {
		return "selector";
	}

	/**
	 * @return URL of the diagram viewer. If not null then a digram initialization script is added to the page. Otherwise the diagram shall be initialized by some other means.
	 */
	protected String getDiagramViewer() {
		return "https://cdn.jsdelivr.net/gh/Nasdanika/drawio@dev/src/main/webapp/js/viewer-static.min.js";
	}	


	protected EReference getConnectionsActionContainmentReference(Node node) {
		return AppPackage.Literals.ACTION__SECTIONS;
	}
	
	protected String getListOpenTag(Element element) {
		return "<ul>";
	}
	
	protected String getListCloseTag(Element element) {
		return "</ul>";
	}

	protected String getPageElementFlagProperty() {
		return "page-element";
	}
	
	protected String getDefaultSort() {
		return "label";
	}

	protected String getSortProperty() {
		return "sort";
	}
		
	protected String getDocumentationProperty() {
		return "documentation";
	}	
		
	protected Action createDocumentAction(Document document) {
		return null;
	}
	
	protected Comparator<Page> getPageComparator(Document document) {
		return null;
	}
	
	protected String getRoleName(ModelElement modelElement) {
		String roleProperty = getRoleProperty();
		if (Util.isBlank(roleProperty)) {
			return null;
		}
		return modelElement.getProperty(roleProperty);
	}
	
	protected EReference getRole(ModelElement modelElement) {
		String roleName = getRoleName(modelElement);
		if (Util.isBlank(roleName)) {
			return modelElement instanceof Connection ? AppPackage.Literals.ACTION__ANONYMOUS : AppPackage.Literals.LABEL__CHILDREN; 
		}
		return resolveRole(roleName); 
	}
	
	protected String getRoleName(Page page) {
		return getRoleName(page.getModel().getRoot());
	}
	
	protected EReference getPageRole(Page page) {
		String roleName = getRoleName(page);
		return Util.isBlank(roleName) ? AppPackage.Literals.LABEL__CHILDREN : resolveRole(roleName); 
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
	
	/**
	 * @param page
	 * @return Element with page-element property set to true or root if there is no such element.
	 */
	protected ModelElement getPageElement(Page page) {
		Root root = page.getModel().getRoot();
		return root.stream()
			.filter(ModelElement.class::isInstance)
			.map(ModelElement.class::cast)
			.filter(this::isPageElement)
			.findFirst()
			.orElse(root);
	}
	
	/**
	 * Creates comparators from type and config.
	 * This implementation creates comparators from <code>org.nasdanika.drawio.comparators</code> package:
	 * <ul>
	 * <li>{@link AngularNodeComparator} - <code>clockwise</code> and <code>counterclockwise</code> types with the base angle in degrees as configuration with 90 as default value.</li>
	 * <li>{@link LabelModelElementComparator} - <code>label</code> with optional <code>descending</code> configuration - compares label HTML plain text ignoring markup.</li>
	 * <li>{@link PropertyModelElementComparator} - <code>property</code> with property name as required configuration. Compares using property values.
	 * </ul>
	 * 
	 * Override to add support of additional sort types.
	 * @param parent Semantic parent of nodes to sort.  
	 * @param sortType
	 * @param config
	 * @return
	 */
	protected Comparator<Element> createComparator(Element semanticParent, String sortType, String config) {
		if (!Util.isBlank(sortType)) {
			switch (sortType) {
			case "clockwise":
			case "counterclockwise":
				if (semanticParent instanceof Node) {
					AngularNodeComparator nodeComparator = new AngularNodeComparator((Node) semanticParent, "clockwise".equals(sortType), Util.isBlank(config) ? null : Double.parseDouble(config));
					return (a,b) -> {
						if (a instanceof Node && b instanceof Node) {
							return nodeComparator.compare((Node) a, (Node) b);
						}
						if (a == null) {
							return b == null ? 0 : 1; 
						}
						if (b == null) {
							return a == null ? 0 : -11; 
						}
						return a.hashCode() - b.hashCode();
					};
				}
				return null;
			case "label":
				LabelModelElementComparator labelComparator = new LabelModelElementComparator("descending".equals(config));
				return (a,b) -> {
					if (a instanceof ModelElement && b instanceof ModelElement) {
						return labelComparator.compare((ModelElement) a, (ModelElement) b);
					}
					if (a == null) {
						return b == null ? 0 : 1; 
					}
					if (b == null) {
						return a == null ? 0 : -11; 
					}
					return a.hashCode() - b.hashCode();
				};
			case "property":
				PropertyModelElementComparator propertyComparator = new PropertyModelElementComparator(config, false);
				return (a,b) -> {
					if (a instanceof Node && b instanceof ModelElement) {
						return propertyComparator.compare((ModelElement) a, (ModelElement) b);
					}
					if (a == null) {
						return b == null ? 0 : 1; 
					}
					if (b == null) {
						return a == null ? 0 : -11; 
					}
					return a.hashCode() - b.hashCode();
				};
			}				
		}
		return null;		
	}
	
	/**
	 * @param modelElement
	 * @return true if a link shall be created for a given model element. This implementation returns true for {@link Layers}s, {@link Node}s and {@link Connection}s without a cross-reference or a pre-existing link which is not a page link.
	 */
	protected boolean shallCreateLink(ModelElement modelElement) {
		return (modelElement instanceof Layer || modelElement instanceof Connection) && (Util.isBlank(modelElement.getLink()) || modelElement.isPageLink());
	}
	
	/**
	 * For cross-reference resolution.
	 * @param element
	 * @param expression
	 * @return
	 */
	protected boolean match(Element element, String expression) {
		if (Util.isBlank(expression)) {
			return true;
		}
		
		try {			
			ExpressionParser parser = new SpelExpressionParser();
			Expression exp = parser.parseExpression(expression);
			EvaluationContext evaluationContext = getEvaluationContext();
			return evaluationContext == null ? exp.getValue(element, Boolean.class) : exp.getValue(evaluationContext, element, Boolean.class);
		} catch (ParseException e) {
			throw new NasdanikaException("Error parsing expression '" + expression + "' in " + element + ": " + e, e);
		} catch (EvaluationException e) {
			return false;
		}
	}
	
	protected EvaluationContext getEvaluationContext() {
		return null;
	}
	
	/**
	 * @param modelElement
	 * @return true if an action shall be created for a given model element. This implementation returns true for elements without external links and without cross-references.
	 * False is returned for undocumented connections.
	 */
	protected boolean shallCreateAction(ModelElement modelElement) {
		return (Util.isBlank(modelElement.getLink()) || modelElement.isPageLink())
				&& (Util.isBlank(getSelectorProperty()) || Util.isBlank(modelElement.getProperty(getSelectorProperty())))
				&& !(modelElement instanceof Connection && !hasDocumentation(modelElement)); // No actions for undocumented connections
	}
	
	/**
	 * Migrating old stuff, remove if not used. Otherwise make protected.
	 * @return
	 */
	private boolean hasDocumentation(ModelElement modelElement) {
		String documentationProperty = getDocumentationProperty();
		if (Util.isBlank(documentationProperty)) {
			return false;
		}
		
		String docProp = modelElement.getProperty(documentationProperty);
		return !Util.isBlank(docProp);
	}
	
	protected ResourceSet getResourceSet() {
		return resourceSet;
	}

	public boolean isRootPage(Page page) {
		String rootPageFlagProperty = getRootPageFlagProperty();
		if (Util.isBlank(rootPageFlagProperty)) {
			return true;
		}
		return !"false".equals(page.getModel().getRoot().getProperty(rootPageFlagProperty));
	}
	
	protected String getRootPageFlagProperty() {
		return "root-page";
	}

	/**
	 * @param sourceProcessor
	 * @param target
	 * @return Link to the target from the source. This implementation returns null. Override to resolve external references.
	 */
	protected String getExternalReference(ProcessorInfo<ElementProcessor> sourceInfo, ModelElement target) {
		return null;
	}
	
	
}
