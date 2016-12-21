package org.nasdanika.html;

public interface NamedItemsContainer<I, T extends NamedItemsContainer<I, ?>> {

	I item(Object name, Object content);
	
	I ajaxItem(Object name, Object location);
	
	boolean isEmpty();
	
}
