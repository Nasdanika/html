package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Markup;

public interface Dropdown extends Markup {
	
	Dropdown item(Object href, boolean active, boolean disabled, Object... content);
	
	Dropdown header(Object... content);
	
	Dropdown divider();

	Dropdown form(org.nasdanika.html.Form form); 
	
	// TODO - text, alignment, data-offset, data-reference
}
