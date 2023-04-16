package org.nasdanika.html.model.app.graph;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;

/**
 * A convenince class binding the identity type parameter to URI. 
 * @author Pavel
 *
 */
public interface URINodeProcessor extends NodeProcessor<URI> {
	
	interface Factory extends NodeProcessor.Factory<URI> {
	
		@Override
		URINodeProcessor create(NodeProcessorConfig<Processor, LabelFactory, LabelFactory, Registry<URI>> config, Context context, ProgressMonitor progressMonitor);
		
	}	

}
