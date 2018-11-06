package org.nasdanika.html.bootstrap;

public interface NamedItemsContainer<I> {

	/**
	 * The first item is active the rest is not.
	 * @param name
	 * @param content
	 * @return
	 */
	default I item(Object name, Object content) {
		return item(name, content, this.isEmpty());
	}
	
	default I ajaxItem(Object name, Object location) {
		return ajaxItem(name, location, this.isEmpty());
	}
	
	/**
	 * Explicitly set active item state.
	 * @param name
	 * @param content
	 * @param active
	 * @return
	 */
	I item(Object name, Object content, boolean active);
	
	I ajaxItem(Object name, Object location, boolean active);
	
	boolean isEmpty();
	
}