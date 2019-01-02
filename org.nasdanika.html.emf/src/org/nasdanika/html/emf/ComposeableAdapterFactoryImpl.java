package org.nasdanika.html.emf;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

/**
 * Base class for composeable adapter factories.
 * @author Pavel Vlasov
 *
 */
public class ComposeableAdapterFactoryImpl extends AdapterFactoryImpl implements ComposeableAdapterFactory {
	
	private ComposedAdapterFactory parentAdapterFactory;

	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;		
	}

}
