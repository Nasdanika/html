package org.nasdanika.html;

public interface Button extends HTMLElement<Button>, Container<Button> {
	
	enum Type { BUTTON, SUBMIT, RESET }
	
	Button type(Type type);

	Button disabled(boolean disabled);
	
	Button disabled();

	/* TODO - other attributes.
	
	autofocus	autofocus	Specifies that a button should automatically get focus when the page loads
	form	form_id	Specifies one or more forms the button belongs to
	formaction	URL	Specifies where to send the form-data when a form is submitted. Only for type="submit"
	formenctype	application/x-www-form-urlencoded
	multipart/form-data
	text/plain	Specifies how form-data should be encoded before sending it to a server. Only for type="submit"
	formmethod	get
	post	Specifies how to send the form-data (which HTTP method to use). Only for type="submit"
	formnovalidate	formnovalidate	Specifies that the form-data should not be validated on submission. Only for type="submit"
	formtarget	_blank
	_self
	_parent
	_top
	framename	Specifies where to display the response after submitting the form. Only for type="submit"
	name	name	Specifies a name for the button
	value	text
	*/	

}
