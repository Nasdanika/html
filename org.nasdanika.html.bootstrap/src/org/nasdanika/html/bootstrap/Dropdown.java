package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface Dropdown extends BootstrapElement<Tag,Dropdown> {
	
	Dropdown item(Object href, boolean active, boolean disabled, Object... content);
	
	Dropdown header(Object... content);
	
	Dropdown divider();

	Dropdown form(org.nasdanika.html.Form form); 
	
	// TODO - text, alignment, data-offset, data-reference
}
