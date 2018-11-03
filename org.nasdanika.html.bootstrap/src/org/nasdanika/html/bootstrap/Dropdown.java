package org.nasdanika.html.bootstrap;

public interface Dropdown<T extends Dropdown<T>> {

	T item(Object... item);
	
	T divider();
	
	T header(Object header);
	
	boolean isDropdownEmpty();
	
}
