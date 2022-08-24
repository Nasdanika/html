package org.nasdanika.html.model.app.drawio;

import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.DynamicProxyProcessorFactory;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo; 

class ProcessorFactory implements NopEndpointProcessorFactory<ElementProcessor, Handler>, DynamicProxyProcessorFactory<ElementProcessor, Handler, Handler> {
	
	private ResourceFactory resourceFactory;
	private URI uri;

	public ProcessorFactory(URI uri, ResourceFactory resourceFactory) {
		this.uri = uri;
		this.resourceFactory = resourceFactory;
	}
	
	@Override
	public ProcessorInfo<ElementProcessor> createProcessor(ProcessorConfig<ElementProcessor> config,
			Consumer<Consumer<ProcessorInfo<ElementProcessor>>> parentProcessorInfoCallbackConsumer,
			Consumer<Consumer<Map<Element, ProcessorInfo<ElementProcessor>>>> registryCallbackConsumer) {
		return ProcessorInfo.of(config, resourceFactory.createProcessor(uri, config, parentProcessorInfoCallbackConsumer, registryCallbackConsumer));
	}

	@Override
	public Class<?> getHandlerInterface(Connection connection, HandlerType type) {
		return Handler.class;
	}
	
	@Override
	public boolean isPassThrough(Connection connection) {
		return false;
	}

};
