package org.nasdanika.html.model.app.graph;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;

public interface ConnectionProcessor<I> extends Processor {
	
	interface Factory<I> {
		
		ConnectionProcessor<I> create(ConnectionProcessorConfig<Processor, LabelFactory, LabelFactory, Registry<I>> config, Context context, ProgressMonitor progressMonitor);
		
	}
	
}
	