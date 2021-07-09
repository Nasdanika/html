package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;

public interface Dropdown extends BootstrapElement<Tag,Dropdown> {
	
	/**
	 * Creates an item. Returns the item (tag) for additional styling or adding more content.
	 * @param href
	 * @param active
	 * @param disabled
	 * @param content
	 * @return
	 */
	Dropdown item(HTMLElement<?> item, boolean active, boolean disabled);
	
	/**
	 * 
	 * @param content
	 * @return Header tag so it can be customized if needed.
	 */
	Tag header(Object... content);
	
	/**
	 * 
	 * @return Divider tag so it can be customized if needed
	 */
	Tag divider();

	Dropdown form(org.nasdanika.html.Form form); 
	
	// TODO - text, alignment, data-offset, data-reference
	
	/**
	 * @return Toggle element for customization.
	 */
	BootstrapElement<?, ?> getToggle();
	
}
