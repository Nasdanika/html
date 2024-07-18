package org.nasdanika.html.model.app.graph.emf;

import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class EObjectReflectiveProcessorFactoryProvider extends ReflectiveProcessorFactoryProvider<Object, WidgetFactory, WidgetFactory> {

	public EObjectReflectiveProcessorFactoryProvider(Object... targets) {
		super(targets);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object createProcessor(
			ProcessorConfig config,
			boolean parallel,
			BiConsumer<Element, BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer, 
			ProgressMonitor progressMonitor) {
		
		Object processor = super.createProcessor(config, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);
		if (processor == null && config instanceof ConnectionProcessorConfig) {
			return new ConnectionProcessor((ConnectionProcessorConfig<WidgetFactory, WidgetFactory>) config, isCompactPath(config));
		}
			
		return processor;
	}
	
	/**
	 * Override to return true for compact reference and operation paths
	 * @return
	 */
	protected boolean isCompactPath(ProcessorConfig config) {
		return false;
	}

}
