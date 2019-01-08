package org.nasdanika.html.emf;

import java.util.function.Supplier;

import org.eclipse.emf.common.notify.Notifier;

/**
 * Adapter factory delegating to {@link Supplier}, which can be a constructor.
 * @author Pavel Vlasov
 *
 */
public class SupplierAdapterFactory<T> extends DelegatingAdapterFactory<T> {
	
	private Supplier<T> supplier;

	public SupplierAdapterFactory(Class<T> type, ClassLoader proxyClassLoader, Supplier<T> supplier) {
		super(type, proxyClassLoader);
		this.supplier = supplier;
	}

	@Override
	protected T doCreateAdapter(Notifier target) {
		return supplier.get();
	}	

}
