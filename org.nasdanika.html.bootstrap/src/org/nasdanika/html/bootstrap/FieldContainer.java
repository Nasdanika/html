package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Container;
import org.nasdanika.html.HTMLElement;

/**
 * Bootstrap field container.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface FieldContainer<T extends FieldContainer<T>> extends Container<T> {
		
	/**
	 * Creates a form group with a label.
	 * @param label
	 * @param controlId
	 * @param control
	 * @return
	 */
	FormGroup<?> formGroup(Object label, Object controlId, Object control, Object helpText);
	
	FormGroup<?> formGroup(Object label, HTMLElement<?> control, Object helpText);
	
	InputGroup<?> inputGroup(Object control);
	
	FormInputGroup formInputGroup(Object label, Object controlId, Object control, Object helpText);
	
	FormInputGroup formInputGroup(Object label, HTMLElement<?> control, Object helpText);
	
	/**
	 * Creates a checkbox control with a label.
	 * @param label
	 * @param checkboxDefinition
	 * @param inline
	 * @return
	 */
	T checkbox(Object label, Object checkboxControl, boolean inline);
	
	/**
	 * Creates a radio control with a label.
	 * @param label
	 * @param checkboxDefinition
	 * @return
	 */
	T radio(Object label, Object radioControl, boolean inline);
	
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
