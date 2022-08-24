package org.nasdanika.html.model.app.drawio;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
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
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.DrawioResource;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;
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
		// Default connection role
		registry
			.values()
			.stream()
			.map(ProcessorInfo::getProcessor)
			.filter(Objects::nonNull)
			.forEach(ElementProcessor::setDefaultConnectionRole);
		
		// Semantic parent
		registry
			.values()
			.stream()
			.map(ProcessorInfo::getProcessor)
			.filter(Objects::nonNull)
			.forEach(ElementProcessor::setSemanticParent);
		
		// Create element
		registry
			.values()
			.stream()
			.map(ProcessorInfo::getProcessor)
			.filter(Objects::nonNull)
			.forEach(ElementProcessor::createSemanticElements);
		
		// Resolve
		registry
			.values()
			.stream()
			.map(ProcessorInfo::getProcessor)
			.filter(Objects::nonNull)
			.forEach(ep -> ep.resolveSemanticElements(getBaseURI()));		
		
		// Returning document semantic elements
		return registry
				.entrySet()
				.stream()
				.filter(e -> e.getKey() instanceof Document)
				.map(Map.Entry::getValue)
				.map(ProcessorInfo::getProcessor)
				.filter(Objects::nonNull)
				.map(ElementProcessor::getSemanticElements)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.distinct();
	}
	
	protected URI baseURI = URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/");

	/**
	 * Base URI for resolving action relative references. This implementation returns a random absolute UR.
	 * @return
	 */
	protected URI getBaseURI() {
		return null;
	}

	protected ElementProcessor createProcessor(
			URI uri,
			ProcessorConfig<ElementProcessor> config,
			Consumer<Consumer<ProcessorInfo<ElementProcessor>>> parentProcessorInfoCallbackConsumer,
			Consumer<Consumer<Map<Element, ProcessorInfo<ElementProcessor>>>> registryCallbackConsumer) {
		
		ElementProcessor processor;
		if (config.getElement() instanceof Document) {
			processor = createDocumentProcessor(uri, config);
		} else if (config.getElement() instanceof Page) {
			processor = createPageProcessor(uri, config);
		} else if (config.getElement() instanceof Model) {
			processor = createModelProcessor(uri, config);
		} else if (config.getElement() instanceof Root) {
			processor = createRootProcessor(uri, config);
		} else if (config.getElement() instanceof Node) {
			processor = createNodeProcessor(uri, config);
		} else if (config.getElement() instanceof org.nasdanika.drawio.Connection) {
			processor = createConnectionProcessor(uri, config);
		} else if (config.getElement() instanceof Layer) {
			processor = createLayerProcessor(uri, config);
		} else if (config.getElement() instanceof Model) {
			processor = createModelProcessor(uri, config);			
		} else {
			processor = null;
		}
		
		if (processor != null) {
			parentProcessorInfoCallbackConsumer.accept(processor::setParentInfo);
			registryCallbackConsumer.accept(processor::setRegistry);
		}

		return processor;
	}

	protected ElementProcessor createLayerProcessor(URI uri, ProcessorConfig<ElementProcessor> config) {
		return new LayerProcessor(this, uri, config);
	}

	protected ElementProcessor createConnectionProcessor(URI uri, ProcessorConfig<ElementProcessor> config) {
		return new ConnectionProcessor(this, uri, config);
	}

	protected ElementProcessor createDocumentProcessor(URI uri, ProcessorConfig<ElementProcessor> config) {
		return new DocumentProcessor(this, uri, config);
	}

	protected ElementProcessor createNodeProcessor(URI uri, ProcessorConfig<ElementProcessor> config) {
		return new NodeProcessor(this, uri, config);
	}

	protected ElementProcessor createRootProcessor(URI uri, ProcessorConfig<ElementProcessor> config) {
		return new RootProcessor(this, uri, config);
	}

	protected ElementProcessor createModelProcessor(URI uri, ProcessorConfig<ElementProcessor> config) {
		return new ModelProcessor(this, uri, config);
	}

	protected ElementProcessor createPageProcessor(URI uri, ProcessorConfig<ElementProcessor> config) {
		return new PageProcessor(this, uri, config);
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
	
	protected String getDefaultConnectionRoleProperty() {
		return "default-connection-role";
	}
	
	protected ConnectionBase getConnectionBase() {
		return connectionBase;
	}
	
	protected String getCrossReferenceProperty() {
		return "xref";
	}
		
//	/**
//	 * @param modelElement
//	 * @return true if a link shall be created for a given model element. This implementation returns true for {@link Node}s and {@link Connection}s without a cross-reference or a pre-existing link which is not a page link.
//	 */
//	protected boolean shallCreateLink(ModelElement modelElement) {
//		return (modelElement instanceof Node || modelElement instanceof Connection) && (Util.isBlank(modelElement.getLink()) || modelElement.isPageLink());
//	}
//	
//	/**
//	 * @return If true, a {@link Page} diagram is added to the content of the {@link Action} created from that page. This implementation returns true.
//	 */
//	protected boolean isEmbedDiagram(Resource resource, Element element, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
//		return !hasDocumentation(resource, element, childEntries);
//	}
//		
//	/**
//	 * @return If true, a table of contents is generated for a {@link Page} diagram. This implementation returns true;
//	 */
//	protected boolean isGenerateTableOfContents(Resource resource, Element element, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
//		return !hasDocumentation(resource, element, childEntries);
//	}	

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
	
	protected String getRoleName(Page page) {
		return getRoleName(page.getModel().getRoot());
	}
	
	protected EReference getPageRole(Page page) {
		String roleName = getRoleName(page);
		return Util.isBlank(roleName) ? AppPackage.Literals.LABEL__CHILDREN : resolveRole(roleName); 
	}
	
}
