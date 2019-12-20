package org.nasdanika.html.bootstrap;

import org.nasdanika.html.NamedItemsContainer;
import org.nasdanika.html.Tag;

/**
 * Navs with content. toHtmlElement returns the nav element.
 * @author Pavel Vlasov
 *
 */
public interface Navs extends BootstrapElement<Tag,Navs>, NamedItemsContainer, Card.Navs {
	
	/**
	 * Adds an item with content. 
	 * @param name
	 * @param active
	 * @param disabled
	 * @param contentId
	 * @param content
	 * @return
	 */
	Navs item(Object name, boolean active, boolean disabled, Object contentId, Object... content);
	
	/**
	 * Adds a link item without content.
	 * @param name
	 * @param href
	 * @param active
	 * @param disabled
	 * @return Navigation link tag.
	 */
	Tag item(Object name, Object href, boolean active, boolean disabled);
	
	Dropdown dropdown(boolean active, Object... name); 	
	
	/**
	 * Navs outputs nav container and content div one after another which works fine for horizontal tabs/pills.
	 * However, for vertial pills it outputs the pills above the content. Use this method and toHTMLElement() to
	 * output navs and content divs independently, e.g. each into a col in a row.
	 * @return
	 */
	Tag getContentDiv();
	
	/**
	 * Styles navs as tabs.
	 * @param tabs
	 * @return
	 */
	Navs tabs(boolean tabs);
	
	/**
	 * @return tabs(true)
	 */
	default Navs tabs() {
		return tabs(true);
	}
		
	/**
	 * Styles navs as tabs.
	 * @param tabs
	 * @return
	 */
	Navs pills(boolean pills);
	
	/**
	 * @return tabs(true)
	 */
	default Navs pills() {
		return pills(true);
	}
}
