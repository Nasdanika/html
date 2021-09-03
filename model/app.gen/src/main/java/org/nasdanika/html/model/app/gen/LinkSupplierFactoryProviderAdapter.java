package org.nasdanika.html.model.app.gen;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.html.Tag;

public class LinkSupplierFactoryProviderAdapter<M extends Link> extends LabelSupplierFactoryProviderAdapter<M> {

	public LinkSupplierFactoryProviderAdapter(M link) {
		super(link);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> SupplierFactory<T> getFactory(Class<T> type) {
		if (type.isAssignableFrom(Tag.class)) {
			return (SupplierFactory<T>) new LinkTagSupplierFactoryAdapter<>((M) getTarget());
		}

		// TODO - Card
		
		if (type.isAssignableFrom(JsTreeNode.class)) {
			return (SupplierFactory<T>) new LinkJsTreeNodeSupplierFactoryAdapter<>((M) getTarget());
		}
		
		return super.getFactory(type);
	}

}
