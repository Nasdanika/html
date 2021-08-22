package org.nasdanika.html.model.html.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

public abstract class HtmlElementSupplierFactoryAdapter<M extends org.nasdanika.html.model.html.HtmlElement, T extends org.nasdanika.html.HTMLElement<?>> extends HtmlElementAdapter<M,T> implements SupplierFactory<T> {
	
	protected HtmlElementSupplierFactoryAdapter(M htmlElement) {
		super(htmlElement);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
		
	/**
	 * Creates element from content.
	 * @param context
	 * @return
	 */
	protected abstract Supplier<T> createElementSupplier(Context context) throws Exception;
	
	@Override
	public Supplier<T> create(Context context) throws Exception {
		SupplierFactory<T> elementSupplierFactory = this::createElementSupplier;
		return elementSupplierFactory.then(this::createConfigureFunction).create(context);
	}	
	
}
