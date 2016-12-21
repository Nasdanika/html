package org.nasdanika.html;

/**
 * Knockout.js interface.
 * @author Pavel Vlasov
 *
 */
public interface Knockout<T> extends KnockoutControlFlow<T> {
	
	T visible(Object expression);
	T visible(Object expression, Object initialValue);
	
	T text(Object expression);
	T text(Object expression, Object initialValue);
	
	T html(Object expression);
	T html(Object expression, Object initialValue);
	
	T css(Object expression);
	T style(Object expression);
	T attr(Object expression);
	T click(Object expression);
	T event(Object expression);
	T submit(Object expression);

	T enable(Object expression);
	T enable(Object expression, Object initialValue);
	
	T disable(Object expression);
	T disable(Object expression, Object initialValue);
	
	T value(Object expression);
	T value(Object expression, Object initialValue);
	
	T textInput(Object expression);
	T textInput(Object expression, Object initialValue);
	
	T hasFocus(Object expression);
	T hasFocus(Object expression, Object initialValue);
	
	T checked(Object expression);
	T checked(Object expression, Object initialValue);
	
	T options(Object expression);
	T options(Object expression, Object initialValue);
	
	T selectedOptions(Object expression);
	T selectedOptions(Object expression, Object initialValue);
	
	T uniqueName(Object expression);
	T template(Object expression);

}
