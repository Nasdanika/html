package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface LinkGroup extends BootstrapElement<Tag> {
	
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
