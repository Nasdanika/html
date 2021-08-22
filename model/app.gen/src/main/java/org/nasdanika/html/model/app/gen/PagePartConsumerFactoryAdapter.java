package org.nasdanika.html.model.app.gen;

import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.model.app.PagePart;
import org.nasdanika.html.model.bootstrap.gen.BootstrapElementConsumerFactoryAdapter;

public class PagePartConsumerFactoryAdapter<M extends PagePart> extends BootstrapElementConsumerFactoryAdapter<M, BootstrapElement<?,?>> {

	protected PagePartConsumerFactoryAdapter(M pagePart) {
		super(pagePart);
	}

}
