package org.nasdanika.html.model.app.graph.drawio;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class DrawioProcessorFactory {
				
	@Processor(type = org.nasdanika.drawio.Document.class)
	public WidgetFactory createDocumentProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new DocumentProcessor();
	}
				
	@Processor(type = org.nasdanika.drawio.Page.class)
	public WidgetFactory createPageProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new PageProcessor();
	}
				
	@Processor(type = org.nasdanika.drawio.Root.class)
	public WidgetFactory createRootProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new RootProcessor();
	}
				
	@Processor(type = org.nasdanika.drawio.Layer.class)
	public WidgetFactory createLayerProcessor(
		ProcessorConfig config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new LayerProcessor();
	}
	
	@Processor(type = org.nasdanika.drawio.Node.class)
	public WidgetFactory createNodeProcessor(
		NodeProcessorConfig<?,?> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new NodeProcessor();
	}
	
	@Processor(type = org.nasdanika.drawio.Connection.class)
	public WidgetFactory createConnectionProcessor(
		ConnectionProcessorConfig<?,?> config, 
		boolean parallel, 
		BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
		Function<ProgressMonitor, Object> next,		
		ProgressMonitor progressMonitor) {
		
		return new ConnectionProcessor();
	}

}
