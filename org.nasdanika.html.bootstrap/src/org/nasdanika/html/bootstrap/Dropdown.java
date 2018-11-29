package org.nasdanika.html.bootstrap;

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
	Tag item(Object href, boolean active, boolean disabled, Object... content);
	
	Dropdown header(Object... content);
	
	Dropdown divider();

	Dropdown form(org.nasdanika.html.Form form); 
	
	// TODO - text, alignment, data-offset, data-reference
}
