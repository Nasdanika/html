package org.nasdanika.html.model.app.drawio;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.HandlerType;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo; 

class ProcessorFactory implements NopEndpointProcessorFactory<ElementProcessor, Void> {
	
	private ResourceFactory resourceFactory;

	public ProcessorFactory(URI uri, ResourceFactory resourceFactory) {
		this.resourceFactory = resourceFactory;
	}
	
	@Override
	public ProcessorInfo<ElementProcessor> createProcessor(ProcessorConfig<ElementProcessor> config,
			Consumer<Consumer<ProcessorInfo<ElementProcessor>>> parentProcessorInfoCallbackConsumer,
			Consumer<Consumer<Map<Element, ProcessorInfo<ElementProcessor>>>> registryCallbackConsumer) {
		return ProcessorInfo.of(config, resourceFactory.createProcessor(config, parentProcessorInfoCallbackConsumer, registryCallbackConsumer));
	}

	@Override
	public Void createHandlerProxy(Connection connection, Supplier<Void> handlerSupplier, HandlerType type) {
		return null;
	}

};
