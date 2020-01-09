package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface Breadcrumbs extends BootstrapElement<Tag, Breadcrumbs> {
	
	boolean isEmpty();

	/**
	 * Adds a breadcrumb item. 
	 * @param active - whether the item shall be marked as active.
	 * @param content
	 * @return
	 */
	Tag item(boolean active, Object... content);
	
}
