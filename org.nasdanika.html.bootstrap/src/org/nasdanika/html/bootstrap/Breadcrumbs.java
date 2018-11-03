package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface Breadcrumbs extends BootstrapElement<Tag> {
	
	/**
	 * Adds breadcrumbs entry
	 * @param href Reference, if null, then given item is considered active.
	 * @param content entry content.
	 * @return
	 */
	Breadcrumbs item(Object href, Object... content);
	
	boolean isEmpty();

}
