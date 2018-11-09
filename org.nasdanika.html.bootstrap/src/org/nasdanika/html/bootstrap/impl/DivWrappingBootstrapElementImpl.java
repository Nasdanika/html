package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * Bootstrap element which wraps DIV.
 * @author Pavel Vlasov
 *
 */
class DivWrappingBootstrapElementImpl extends WrappingBootstrapElementImpl<Tag> {

	protected DivWrappingBootstrapElementImpl(BootstrapFactory factory) {
		super(factory, factory.getHTMLFactory().div());
	}

}
