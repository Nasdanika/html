package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface Breadcrumb extends BootstrapElement<Tag, Breadcrumb> {
	
	boolean isEmpty();

	/**
	 * Adds a breadcrumb item. 
	 * @param active - whether the item shall be marked as active.
	 * @param content
	 * @return
	 */
	Tag item(boolean active, Object... content);
	
}
