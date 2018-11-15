package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * Bootstrap element which wraps DIV.
 * @author Pavel Vlasov
 *
 */
class DivWrappingBootstrapElementImpl<B extends BootstrapElement<Tag,B>> extends WrappingBootstrapElementImpl<Tag,B> {

	protected DivWrappingBootstrapElementImpl(BootstrapFactory factory) {
		super(factory, factory.getHTMLFactory().div());
	}

}
