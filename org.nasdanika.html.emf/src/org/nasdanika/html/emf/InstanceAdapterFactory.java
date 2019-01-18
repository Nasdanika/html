package org.nasdanika.html.emf;

import org.eclipse.emf.common.notify.Notifier;

/**
 * Adapter factory delegating to a single shared adapter instance for all notifiers.
 * @author Pavel Vlasov
 *
 */
public class InstanceAdapterFactory<T> extends DelegatingAdapterFactory<T> {
	
	private T adapter;

	public InstanceAdapterFactory(Class<T> type, ClassLoader proxyClassLoader, T adapter) {
		super(type, proxyClassLoader);
		this.adapter = adapter;
	}

	@Override
	protected T doCreateAdapter(Notifier target) {
		return adapter;
	}	

}
