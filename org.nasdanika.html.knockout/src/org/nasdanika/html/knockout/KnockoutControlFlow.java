package org.nasdanika.html.knockout;

/**
 * Knockout bindings which can be used on regular and virtual elements.
 * @author Pavel
 *
 */
public interface KnockoutControlFlow<T> extends KnockoutBindingsSource {		

	T foreach(Object expression);
	T foreach(Object expression, Object initialValue);
	
	T if_(Object expression);
	T if_(Object expression, Object initialValue);
	
	T ifnot(Object expression);
	T ifnot(Object expression, Object initialValue);
	
	T with(Object expression);
	T component(Object expression);

	/**
	 * Custom binding
	 * @param binding
	 * @param expression
	 * @param initialValue Initial value to use in generated observables or null.
	 * @return
	 */
	T bind(String binding, Object expression, Object initialValue);
	
	/**
	 * Generates observable values and arrays for this and 
	 * contained elements.
	 * @param excludes Bindings to exclude, e.g. computed or pure computed bindings.
	 * @return List of <code> var varName = ko.observable();</code>
	 */
	String generateObservables(String... excludes);
		
}
