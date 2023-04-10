package org.nasdanika.html.model.app.drawio;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo; 

class ProcessorFactory implements NopEndpointProcessorFactory<ElementProcessor, Void, Registry> {
	
	private ResourceFactory resourceFactory;
	private URI uri;

	public ProcessorFactory(URI uri, ResourceFactory resourceFactory) {
		this.uri = uri;
		this.resourceFactory = resourceFactory;
	}
	
	@Override
	public ProcessorInfo<ElementProcessor, Registry> createProcessor(ProcessorConfig<ElementProcessor, Registry> config, ProgressMonitor progressMonitor) {
		return ProcessorInfo.of(config, resourceFactory.createProcessor(uri, config, progressMonitor), null);
	}
	
	@Override
	public boolean isPassThrough(Connection connection) {
		return false;
	}

	@Override
	public Registry createRegistry(Map<Element, ProcessorInfo<ElementProcessor, Registry>> registry) {
		return new Registry(registry);
	}

};
