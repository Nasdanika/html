package org.nasdanika.html.model.app.gen;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.model.app.PagePart;
import org.nasdanika.html.model.bootstrap.gen.BootstrapElementConsumerFactoryAdapter;

public class PagePartConsumerFactoryAdapter<M extends PagePart> extends BootstrapElementConsumerFactoryAdapter<M, BootstrapElement<?,?>> {

	protected PagePartConsumerFactoryAdapter(M pagePart, AdapterFactory adapterFactory) {
		super(pagePart, adapterFactory);
	}

}
