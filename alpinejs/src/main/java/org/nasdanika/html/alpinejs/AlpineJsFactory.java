package org.nasdanika.html.alpinejs;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.alpinejs.impl.DefaultAlpineJsFactory;

public interface AlpineJsFactory {
	
	AlpineJsFactory INSTANCE = new DefaultAlpineJsFactory(HTMLFactory.INSTANCE);
	
	<H extends HTMLElement<?>> AlpineJs<H> from(H htmlElement);
	
	HTMLFactory getHTMLFactory();
	
	/**
	 * Adds CDN script declaration to the page head. 
	 * @param page
	 * @return
	 */
	<P extends HTMLPage> P cdn(P page);
	
	/**
	 * Wraps the argument element into a template tag with a given teleport selector
	 * @param selector
	 * @param element
	 * @return
	 */
	Tag teleport(Object selector, HTMLElement<?> element);
	
	/**
	 * Wraps the argument element into a template tag with a given for expression
	 * @param selector
	 * @param element
	 * @return
	 */
	Tag _for(Object expression, HTMLElement<?> element);
	
	/**
	 * Wraps the argument element into a template tag with a given if condition
	 * @param selector
	 * @param element
	 * @return
	 */	
	Tag _if(Object condition, HTMLElement<?> element);	

}
