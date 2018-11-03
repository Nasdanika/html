package org.nasdanika.html;

public interface Button extends HTMLElement<Button>, Container<Button> {
	
	enum Type { BUTTON, SUBMIT, RESET }
	
	Button type(Type type);

	// TODO - other attributes.

}
