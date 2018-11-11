package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;

abstract class BootstrapElementImpl<H extends HTMLElement<?>> implements BootstrapElement<H> {
	
	private BootstrapFactory factory;

	protected BootstrapElementImpl(BootstrapFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public BootstrapFactory getFactory() {
		return factory;
	}
	
	@Override
	public String toString() {
		return toHTMLElement().toString();
	}
	
	@Override
	public Object produce(int indent) {
		return toHTMLElement().produce(indent);
	}

}
