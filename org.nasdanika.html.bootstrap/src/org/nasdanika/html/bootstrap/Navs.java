package org.nasdanika.html.bootstrap;

import org.nasdanika.html.NamedItemsContainer;
import org.nasdanika.html.Tag;

/**
 * Navs with content. toHtmlElement returns the nav element.
 * @author Pavel Vlasov
 *
 */
public interface Navs extends BootstrapElement<Tag>, NamedItemsContainer {
	
	/**
	 * Adds 
	 * @param name
	 * @param active
	 * @param disabled
	 * @param contentId
	 * @param content
	 * @return
	 */
	Navs item(Object name, boolean active, Object contentId, Object... content);
	

}
