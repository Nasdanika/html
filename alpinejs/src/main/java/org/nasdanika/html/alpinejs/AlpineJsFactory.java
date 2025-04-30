package org.nasdanika.html.alpinejs;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.alpinejs.impl.DefaultAlpineJsFactory;

public interface AlpineJsFactory {
	
	AlpineJsFactory INSTANCE = new DefaultAlpineJsFactory(HTMLFactory.INSTANCE);
	
	<H extends HTMLElement<?>> AlpineJs<H> from(H htmlElement);
	
	HTMLFactory getHTMLFactory();
	
	/**
	 * Adds CDN KnockoutJS script declaration to the page head. 
	 * @param page
	 * @return
	 */
	<P extends HTMLPage> P cdn(P page);

}
