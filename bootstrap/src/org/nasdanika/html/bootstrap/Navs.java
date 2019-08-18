package org.nasdanika.html.bootstrap;

import org.nasdanika.html.NamedItemsContainer;
import org.nasdanika.html.Tag;

/**
 * Navs with content. toHtmlElement returns the nav element.
 * @author Pavel Vlasov
 *
 */
public interface Navs extends BootstrapElement<Tag,Navs>, NamedItemsContainer {
	
	/**
	 * Adds 
	 * @param name
	 * @param active
	 * @param disabled
	 * @param contentId
	 * @param content
	 * @return
	 */
	Navs item(Object name, boolean active, boolean disabled, Object contentId, Object... content);
	
	Dropdown dropdown(boolean active, Object... name); 	
	
	/**
	 * Navs outputs nav container and content div one after another which works fine for horizontal tabs/pills.
	 * However, for vertial pills it outputs the pills above the content. Use this method and toHTMLElement() to
	 * output navs and content divs independently, e.g. each into a col in a row.
	 * @return
	 */
	Tag getContentDiv();
	
}
