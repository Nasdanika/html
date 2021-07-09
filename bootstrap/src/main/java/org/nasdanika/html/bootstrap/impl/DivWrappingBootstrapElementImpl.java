package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * Bootstrap element which wraps DIV.
 * @author Pavel Vlasov
 *
 */
public class DivWrappingBootstrapElementImpl<B extends BootstrapElement<Tag,B>> extends WrappingBootstrapElementImpl<Tag,B> {

	protected DivWrappingBootstrapElementImpl(BootstrapFactory factory, boolean nonEmpty) {
		super(factory, nonEmpty ? factory.getHTMLFactory().nonEmptyDiv() : factory.getHTMLFactory().div());
	}

	protected DivWrappingBootstrapElementImpl(BootstrapFactory factory) {
		this(factory, false);
	}
	
}
