package org.nasdanika.html.model.app.graph;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;

/**
 * A convenience class binding the identity type parameter to URI. 
 * @author Pavel
 *
 */
public interface URINodeProcessor extends NodeProcessor<URI> {
	
	interface Factory extends NodeProcessor.Factory<URI> {
	
		@Override
		URINodeProcessor create(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, Context context, ProgressMonitor progressMonitor);
		
	}	

}
