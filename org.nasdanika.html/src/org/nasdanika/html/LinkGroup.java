package org.nasdanika.html;

public interface LinkGroup extends UIElement<LinkGroup> {
	
	Tag item(Object content, Object href, Bootstrap.Style style, boolean active);
	
	/**
	 * Creates an item and returns it for further configuration.
	 * @param content
	 * @param style
	 * @param active
	 * @return
	 */
	Tag item(Object content, Bootstrap.Style style, boolean active);
	
	int length();

}
