package org.nasdanika.html.model.app.gen;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.html.Tag;

public class LinkSupplierFactoryProviderAdapter<M extends Link> extends LabelSupplierFactoryProviderAdapter<M> {

	public LinkSupplierFactoryProviderAdapter(M link, AdapterFactory adapterFactory) {
		super(link, adapterFactory);
		if (link instanceof Action) {
			throw new IllegalArgumentException("Actions must be converted to links first: " + link.getText());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> SupplierFactory<T> getFactory(Class<T> type) {
		if (type.isAssignableFrom(Tag.class)) {
			return (SupplierFactory<T>) new LinkTagSupplierFactoryAdapter<>((M) getTarget(), adapterFactory);
		}

		// TODO - Card
		
		if (type.isAssignableFrom(JsTreeNode.class)) {
			return (SupplierFactory<T>) new LinkJsTreeNodeSupplierFactoryAdapter<>((M) getTarget());
		}
		
		return super.getFactory(type);
	}

}
