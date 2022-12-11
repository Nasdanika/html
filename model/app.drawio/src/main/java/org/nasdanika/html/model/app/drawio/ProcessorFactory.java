package org.nasdanika.html.model.app.drawio;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo; 

class ProcessorFactory implements NopEndpointProcessorFactory<ElementProcessor, Void> {
	
	private ResourceFactory resourceFactory;
	private URI uri;

	public ProcessorFactory(URI uri, ResourceFactory resourceFactory) {
		this.uri = uri;
		this.resourceFactory = resourceFactory;
	}
	
	@Override
	public ProcessorInfo<ElementProcessor> createProcessor(ProcessorConfig<ElementProcessor> config, ProgressMonitor progressMonitor) {
		return ProcessorInfo.of(config, resourceFactory.createProcessor(uri, config, progressMonitor), null);
	}
	
	@Override
	public boolean isPassThrough(Connection connection) {
		return false;
	}

};
