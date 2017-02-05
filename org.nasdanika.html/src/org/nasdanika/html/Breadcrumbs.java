package org.nasdanika.html;

public interface Breadcrumbs extends UIElement<Breadcrumbs> {
	
	/**
	 * Adds breadcrumbs entry
	 * @param href Reference, if null, then given item is considered active.
	 * @param content entry content.
	 * @return
	 */
	Breadcrumbs item(Object href, Object... content);
	
	boolean isEmpty();

}
