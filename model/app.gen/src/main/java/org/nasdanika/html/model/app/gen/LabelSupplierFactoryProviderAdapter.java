package org.nasdanika.html.model.app.gen;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.html.Tag;

public class LabelSupplierFactoryProviderAdapter<M extends Label> extends AdapterImpl implements SupplierFactory.Provider {

	public LabelSupplierFactoryProviderAdapter(M label) {
		setTarget(label);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.Provider.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> SupplierFactory<T> getFactory(Class<T> type) {
		if (type.isAssignableFrom(Tag.class)) {
			return (SupplierFactory<T>) new LabelTagSupplierFactoryAdapter<>((M) getTarget());
		}
		// TODO Card
		
		if (type.isAssignableFrom(JsTreeNode.class)) {
			return (SupplierFactory<T>) new LabelJsTreeNodeSupplierFactoryAdapter<>((M) getTarget());
		}
		
		return null;
	}

}
