package org.nasdanika.html.bootstrap;

/**
 * Generates input group nested inside form group, in accordance with recommendation of not mixing the two.
 * @author Pavel
 *
 */
public interface FormInputGroup extends FormGroup<FormInputGroup>, InputGroup<FormInputGroup> {
	
	/**
	 * @return Wrapped input group.
	 */
	InputGroup<FormInputGroup> getInputGroup();

}
