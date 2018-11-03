package org.nasdanika.html;

public interface FieldContainer<T extends FieldContainer<T>> extends Container<T> {
			
	Button button(Object... content);
	
	FieldSet fieldset();
	
	/**
	 * Creates a new fragment backed by this container configuration. This is a factory method - the new fragment
	 * is not added to this container. This method can be used for wrapping of a group of fields
	 * into a container, e.g. a panel - create fragment, add fields, create a panel from 
	 * the fragment, add panel to this container.
	 * @return
	 */
	FormFragment formFragment();

}
