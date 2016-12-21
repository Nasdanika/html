package org.nasdanika.html;

public interface Navbar extends UIElement<Navbar> {

	/**
	 * Adds item to the navbar
	 * @param item Item 
	 * @param active Active state flag
	 * @param right If true the item is added to the right part of the navbar
	 * @return
	 */
	Navbar item(Object item, boolean active, boolean right);
	
	Dropdown<?> dropdown(Object name, boolean right);
	
	Form form(boolean right);
	
}
