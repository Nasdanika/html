package org.nasdanika.html;

public interface Dropdown<T extends Dropdown<T>> extends UIElement<T> {

	T item(Object... item);
	
	T divider();
	
	T header(Object header);
	
	boolean isDropdownEmpty();
	
}
