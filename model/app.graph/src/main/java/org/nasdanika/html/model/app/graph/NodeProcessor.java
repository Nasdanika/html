package org.nasdanika.html.model.app.graph;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;

public interface NodeProcessor<I> extends LabelFactory {
	
	interface Factory<I> {
		
		NodeProcessor<I> create(NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<I>> config, Context context, ProgressMonitor progressMonitor);
		
	}	

}