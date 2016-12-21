package org.nasdanika.html;

public interface Accordion extends UIElement<Accordion>, NamedItemsContainer<UIElement<?>, Accordion> {
	
	/**
	 * 
	 * @param title
	 * @param style
	 * @param initial
	 * @param id Item id. If null, id is auto-generated. 
	 * @param content
	 * @return
	 */
	UIElement<?> item(Object title, Bootstrap.Style style, boolean initial, Object id, Object content);
	
	/**
	 * 
	 * @param title
	 * @param style
	 * @param id Item id. If null, id is auto-generated.
	 * @param location
	 * @return
	 */
	UIElement<?> ajaxItem(Object title, Bootstrap.Style style, Object id, Object location);	
	
	/**
	 * Style to apply to items which don't explicitly set their own style.
	 * @param style
	 * @return
	 */
	Accordion style(Bootstrap.Style style);

}
