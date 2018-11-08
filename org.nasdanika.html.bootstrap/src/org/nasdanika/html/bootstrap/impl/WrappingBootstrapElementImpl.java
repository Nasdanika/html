package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * Bootstrap element which wraps {@link HTMLElement}.
 * @author Pavel Vlasov
 *
 * @param <H>
 */
class WrappingBootstrapElementImpl<H extends HTMLElement<?>> extends BootstrapElementImpl<H> {

	protected H htmlElement;

	protected WrappingBootstrapElementImpl(BootstrapFactory factory, H htmlElement) {
		super(factory);
		this.htmlElement = htmlElement;
	}

	@Override
	public H toHTMLElement() {
		return htmlElement;
	}

	@Override
	public void close() throws Exception {
		htmlElement.close();		
	}

	@Override
	public Object produce(int indent) {
		return htmlElement.produce(indent);
	}

}
