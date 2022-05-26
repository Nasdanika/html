package org.nasdanika.html.model.app.gen;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.html.Tag;

public class LabelSupplierFactoryProviderAdapter<M extends Label> extends AdapterImpl implements SupplierFactory.Provider {

	protected AdapterFactory adapterFactory;

	public LabelSupplierFactoryProviderAdapter(M label, AdapterFactory adapterFactory) {
		setTarget(label);
		this.adapterFactory = adapterFactory;
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.Provider.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> SupplierFactory<T> getFactory(Class<T> type) {
		if (type.isAssignableFrom(Tag.class)) {
			return (SupplierFactory<T>) new LabelTagSupplierFactoryAdapter<>((M) getTarget(), adapterFactory);
		}
		// TODO Card
		
		if (type.isAssignableFrom(JsTreeNode.class)) {
			return (SupplierFactory<T>) new LabelJsTreeNodeSupplierFactoryAdapter<>((M) getTarget());
		}
		
		return null;
	}

}
