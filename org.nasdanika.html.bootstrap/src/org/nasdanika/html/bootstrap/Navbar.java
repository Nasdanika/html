package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface Navbar extends BootstrapElement<Tag,Navbar> {
	
	/**
	 * Creates a navbar items - a tag. Returns the tag so it can be additionally styled or
	 * more content can be added.
	 * @param href
	 * @param active
	 * @param disabled
	 * @param content
	 * @return
	 */
	Tag item(Object href, boolean active, boolean disabled, Object... content);
	
	Dropdown dropdown(boolean active, Object... name); 

	Navbar form(org.nasdanika.html.Form form);
	
	/**
	 * Creates navbar text (span) and returns it.
	 * @param content
	 * @return
	 */
	Tag navbarText(Object... content);

}
