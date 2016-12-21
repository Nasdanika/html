package org.nasdanika.html;


public interface Form extends UIElement<Form>, FieldContainer<Form> {
	
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
	
	// Standard form attributes
	
	Form action(Object action);
	
	Form autocomplete();
	Form autocomplete(boolean autocomplete);
	
	enum EncType {
		plain("text/plain"),
		multipart("multipart/form-data"),
		urlencoded("application/x-www-form-urlencoded");
		
		public final String literal;
		
		private EncType(String literal) {
			this.literal = literal;		
		}
	}

	Form enctype(EncType enctype);
	
	Form acceptCharset(String charset);

	enum Method { get, post }
	
	Form method(Method method);
	
	Form name(String name);
	
	Form novalidate();
	Form novalidate(boolean novalidate);
	
	Form target(String target);		
	
	/**
	 * Adds AngularJS submit attribute.
	 * @param handler
	 * @return
	 */
	Form ngSubmit(Object handler);
	
}

