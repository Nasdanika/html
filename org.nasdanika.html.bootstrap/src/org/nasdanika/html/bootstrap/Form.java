package org.nasdanika.html.bootstrap;


public interface Form extends BootstrapElement<org.nasdanika.html.Form>, FieldContainer<Form> {
	
	/**
	 * Sets the form as horizontal.
	 * @param deviceSize Device size code to use in column width specifications.
	 * @param labelWidth Label column width.
	 * @return
	 */
	Form horizontal(Bootstrap.DeviceSize deviceSize, int labelWidth);
	
	/**
	 * 
	 * @param inline Inline form if true.
	 * @param hideLabels Adds "sr-only" class to labels if true.
	 * @return
	 */
	Form inline(boolean inline, boolean hideLabels);
	
	/**
	 * Same as inline(true, true);
	 * @return
	 */
	Form inline();	

}

