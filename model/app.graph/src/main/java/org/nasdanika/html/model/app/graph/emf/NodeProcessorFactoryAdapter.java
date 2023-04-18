package org.nasdanika.html.model.app.graph.emf;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.NodeProcessor;
import org.nasdanika.html.model.app.graph.Registry;

/**
 * Base class for {@link NodeProcessor}.Factory adapters
 * @author Pavel
 *
 */
public class NodeProcessorFactoryAdapter<T extends Notifier> extends AdapterImpl implements NodeProcessor.Factory<URI> {
	
	public NodeProcessorFactoryAdapter(T target) {
		setTarget(target);
	}
	
	/**
	 * Convenience 
	 */
	@SuppressWarnings("unchecked")	
	@Override
	public T getTarget() {
		return (T) target;
	}

	@Override
	public NodeProcessor<URI> create(NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context, ProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
