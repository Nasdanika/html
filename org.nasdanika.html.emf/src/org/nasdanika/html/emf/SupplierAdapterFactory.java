package org.nasdanika.html.emf;

import java.util.function.Supplier;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

/**
 * Adapter factory delegating to {@link Supplier}, which can be a constructor.
 * @author Pavel Vlasov
 *
 */
public class SupplierAdapterFactory<T> extends ComposeableAdapterFactoryImpl {
	
	private Class<T> type;
	private Supplier<T> supplier;

	public SupplierAdapterFactory(Class<T> type, Supplier<T> supplier) {
		this.type = type;
		this.supplier = supplier;
	}
	
	@Override
	public boolean isFactoryForType(Object type) {
		return this.type == type;
	}
		
	@Override
	protected Adapter createAdapter(Notifier target) {
		return (Adapter) supplier.get();
	}

}
