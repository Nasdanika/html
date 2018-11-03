package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface Accordion extends BootstrapElement<Tag>, NamedItemsContainer<Tag> {
	
	/**
	 * 
	 * @param title
	 * @param style
	 * @param initial
	 * @param id Item id. If null, id is auto-generated. 
	 * @param content
	 * @return
	 */
	Tag item(Object title, Bootstrap.Style style, boolean initial, Object id, Object content);
	
	/**
	 * 
	 * @param title
	 * @param style
	 * @param id Item id. If null, id is auto-generated.
	 * @param location
	 * @return
	 */
	Tag ajaxItem(Object title, Bootstrap.Style style, boolean initial, Object id, Object location);	
	
	/**
	 * Style to apply to items which don't explicitly set their own style.
	 * @param style
	 * @return
	 */
	Accordion style(Bootstrap.Style style);

}
