package org.nasdanika.html.model.app.graph.emf;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.html.model.app.graph.WidgetFactory;

/**
 * Base class for node factory adapters 
 * @author Pavel
 *
 */
public abstract class NodeProcessorFactoryAdapter<T extends Notifier> extends AdapterImpl implements org.nasdanika.graph.processor.NodeProcessorInfo.Factory<Object, WidgetFactory, WidgetFactory> {
	
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

}
