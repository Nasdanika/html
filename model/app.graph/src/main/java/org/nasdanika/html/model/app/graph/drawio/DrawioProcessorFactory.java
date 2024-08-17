package org.nasdanika.html.model.app.graph.drawio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.DocumentationFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class DrawioProcessorFactory {
	
	protected CapabilityLoader capabilityLoader;
	
	public DrawioProcessorFactory() {
		this(new CapabilityLoader());
	}
	
	public DrawioProcessorFactory(CapabilityLoader capabilityLoader) {
		this.capabilityLoader = capabilityLoader;
	}
	
	private Collection<DocumentationFactory> documentationFactories;
	
	protected Collection<DocumentationFactory> getDocumentationFactories(ProgressMonitor progressMonitor) {
		if (documentationFactories == null) {
			documentationFactories = new ArrayList<>();
			if (capabilityLoader != null) {
				Requirement<Object, DocumentationFactory> requirement = ServiceCapabilityFactory.createRequirement(DocumentationFactory.class);
				Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(requirement, progressMonitor);
				for (CapabilityProvider<Object> cp: cpi) {				
					cp.getPublisher().subscribe(df -> documentationFactories.add((DocumentationFactory) df));
				}
			}
		}
		return documentationFactories;
	}
	
	public String getIconProperty() {
		return "icon";
	}	
	
	public String getTitleProperty() {
		return "title";
	}	
		
	public String getDocumentationProperty() {
		return "documentation";
	}	
		
	public String getDocRefProperty() {
		return "doc-ref";
	}	
	
	public String getDocFormatProperty() {
		return "doc-format"; 
	}		
				
	@Processor(type = org.nasdanika.drawio.Document.class)
	public WidgetFactory createDocumentProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new DocumentProcessor(this);
	}
				
	@Processor(type = org.nasdanika.drawio.Page.class)
	public WidgetFactory createPageProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new PageProcessor(this);
	}
				
	@Processor(type = org.nasdanika.drawio.Root.class)
	public WidgetFactory createRootProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new RootProcessor(this);
	}
				
	@Processor(
			type = org.nasdanika.drawio.Layer.class,
			value = "visible = true")
	public WidgetFactory createLayerProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new LayerProcessor(this);
	}
	
	@Processor(
			type = org.nasdanika.drawio.Node.class,
			value = "visible = true")
	public WidgetFactory createNodeProcessor(
		NodeProcessorConfig<?,?> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new NodeProcessor(this);
	}
	
	@Processor(
			type = org.nasdanika.drawio.Connection.class,
			value = "visible = true")
	public WidgetFactory createConnectionProcessor(
		ConnectionProcessorConfig<?,?> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new ConnectionProcessor(this);
	}
	
	/**
	 * Override to implement filtering of representation elements
	 * @param representationElement
	 * @param registry
	 * @param progressMonitor
	 */
	public void filterRepresentationElement(
			Element representationElement,
			Map<org.nasdanika.drawio.Element, ProcessorInfo<WidgetFactory>> registry,
			ProgressMonitor progressMonitor) {
		
	}

	/**
	 * Override to customize viewer.
	 * @return
	 */
	public String getViewer() {
		return DiagramGenerator.JSDELIVR_DRAWIO_VIEWER;
	}
		
	/**
	 * Default base URI for the Drawio application to resolve library relative URL's.
	 */
	public static final URI DEFAULT_APP_BASE = URI.createURI("https://app.diagrams.net/");
	
	/**
	 * Application base for resolving relative image URL's. 
	 * This implementation returns DEFAULT_APP_BASE. 
	 * Override to customize for different (e.g. intranet) installations.
	 * App sources - https://github.com/jgraph/drawio/tree/dev/src/main/webapp.
	 * For the purposes of serving images and a diagram editor the web app can be deployed as a static site.
	 * It can also be deployed as a Docker container - https://www.drawio.com/blog/diagrams-docker-app, https://hub.docker.com/r/jgraph/drawio 
	 * @return
	 */
	public URI getAppBase() {
		return DEFAULT_APP_BASE;
	}
	
	/**
	 * This implementation returns the argument. 
	 * Override to rewrite URL's before conversion to icons. For example, read representations from a file system and convert to data URL's.
	 * @param imageRepr
	 * @return
	 */
	public String rewriteImage(String imageRepr, ProgressMonitor progressMonitor) {
		return imageRepr;
	}
	
	/**
	 * Icons size for UI generation - jsTree displays icons up to 24x24 pixels, leaving 4 pixes for padding
	 */
	public static final int ICON_SIZE = 20;
	
	/**
	 * Icon size to scale image representations to
	 * @return
	 */
	protected int getIconSize() {
		return ICON_SIZE;
	}

}
