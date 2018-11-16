package org.nasdanika.html.fontawesome;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.fontawesome.impl.DefaultFontAwesomeFactory;

public interface FontAwesomeFactory {
	
	FontAwesomeFactory INSTANCE = new DefaultFontAwesomeFactory(HTMLFactory.INSTANCE);	
	
	Icon<Tag> icon(String icon, Icon.Style style);
	
	/**
	 * Shortcut for <code>factory.span().fontAwesome();</code>
	 * @return
	 */
	<T extends HTMLElement<?>> Icon<T> from(String icon, Icon.Style style, T htmlElement);
	
	Icon.Stack stack();
	
	HTMLFactory getHTMLFactory();
	
	/**
	 * Adds Fontawesome stylesheet declaration pointing to CDN.
	 * @param page
	 * @return
	 */
	<P extends HTMLPage> P cdn(P page);
	
}
