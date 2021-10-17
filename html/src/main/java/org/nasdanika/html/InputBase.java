package org.nasdanika.html;

/**
 * Base interface for Input, Select, and TextArea
 * @author Pavel
 *
 * @param <T>
 */
public interface InputBase<T extends InputBase<T>> extends HTMLElement<T> {
	
	T autofocus(boolean autofocus);
	T autofocus();

	T name(Object name);
	
	T required(boolean required);
	T required();
	
	T disabled(boolean disabled);
	T disabled();
	
	T form(Form... form);	
			
}
