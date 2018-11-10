package org.nasdanika.html.knockout;

import org.nasdanika.html.HTMLElement;

/**
 * Knockout.js interface.
 * @author Pavel Vlasov
 *
 */
public interface Knockout<H extends HTMLElement<?>> extends KnockoutControlFlow<Knockout<H>> {
	
	Knockout<H> visible(Object expression);
	Knockout<H> visible(Object expression, Object initialValue);
	
	Knockout<H> text(Object expression);
	Knockout<H> text(Object expression, Object initialValue);
	
	Knockout<H> html(Object expression);
	Knockout<H> html(Object expression, Object initialValue);
	
	Knockout<H> css(Object expression);
	Knockout<H> style(Object expression);
	Knockout<H> attr(Object expression);
	Knockout<H> click(Object expression);
	Knockout<H> event(Object expression);
	Knockout<H> submit(Object expression);

	Knockout<H> enable(Object expression);
	Knockout<H> enable(Object expression, Object initialValue);
	
	Knockout<H> disable(Object expression);
	Knockout<H> disable(Object expression, Object initialValue);
	
	Knockout<H> value(Object expression);
	Knockout<H> value(Object expression, Object initialValue);
	
	Knockout<H> textInput(Object expression);
	Knockout<H> textInput(Object expression, Object initialValue);
	
	Knockout<H> hasFocus(Object expression);
	Knockout<H> hasFocus(Object expression, Object initialValue);
	
	Knockout<H> checked(Object expression);
	Knockout<H> checked(Object expression, Object initialValue);
	
	Knockout<H> options(Object expression);
	Knockout<H> options(Object expression, Object initialValue);
	
	Knockout<H> selectedOptions(Object expression);
	Knockout<H> selectedOptions(Object expression, Object initialValue);
	
	Knockout<H> uniqueName(Object expression);
	Knockout<H> template(Object expression);
	
	H toHTMLElement();

}
