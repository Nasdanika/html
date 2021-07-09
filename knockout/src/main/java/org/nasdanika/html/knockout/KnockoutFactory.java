package org.nasdanika.html.knockout;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.knockout.impl.DefaultKnockoutFactory;

public interface KnockoutFactory {
	
	KnockoutFactory INSTANCE = new DefaultKnockoutFactory(HTMLFactory.INSTANCE);
	
	<H extends HTMLElement<?>> Knockout<H> from(H htmlElement);

	KnockoutVirtualElement virtualElement(Object... content);
	
	HTMLFactory getHTMLFactory();
	
	/**
	 * Adds CDN KnockoutJS script declaration to the page head. 
	 * @param page
	 * @return
	 */
	<P extends HTMLPage> P cdn(P page);

}
