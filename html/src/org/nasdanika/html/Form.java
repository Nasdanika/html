package org.nasdanika.html;


public interface Form extends HTMLElement<Form>, FieldContainer<Form> {
	
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
	
}

