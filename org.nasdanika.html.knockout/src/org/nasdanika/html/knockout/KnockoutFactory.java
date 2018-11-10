package org.nasdanika.html.knockout;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.knockout.impl.DefaultKnockoutFactory;

public interface KnockoutFactory {
	
	KnockoutFactory INSTANCE = new DefaultKnockoutFactory(HTMLFactory.INSTANCE);
	
	<H extends HTMLElement<?>> Knockout<H> from(H htmlElement);

	KnockoutVirtualElement virtualElement(Object... content);
	
	HTMLFactory getHTMLFactory();

}
