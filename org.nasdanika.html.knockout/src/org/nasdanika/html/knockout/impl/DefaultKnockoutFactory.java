package org.nasdanika.html.knockout.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.knockout.Knockout;
import org.nasdanika.html.knockout.KnockoutFactory;
import org.nasdanika.html.knockout.KnockoutVirtualElement;

public class DefaultKnockoutFactory implements KnockoutFactory {
	
	private HTMLFactory htmlFactory;

	public DefaultKnockoutFactory(HTMLFactory htmlFactory) {
		this.htmlFactory = htmlFactory;
	}
	
	@Override
	public HTMLFactory getHTMLFactory() {
		return htmlFactory;
	}
	
	@Override
	public <H extends HTMLElement<?>> Knockout<H> from(H htmlElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KnockoutVirtualElement virtualElement(Object... content) {
		return new KnockoutVirtualElementImpl(this, content);
	}	

}
