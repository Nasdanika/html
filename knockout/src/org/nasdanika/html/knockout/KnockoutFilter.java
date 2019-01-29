package org.nasdanika.html.knockout;

import java.util.Collection;

import org.nasdanika.html.HTMLElement;

public class KnockoutFilter<H extends HTMLElement<?>> implements Knockout<H> {
	
	private Knockout<H> target;

	public KnockoutFilter(Knockout<H> target) {
		this.target = target;
	}

	@Override
	public Knockout<H> visible(Object expression) {
		return target.visible(expression);
	}

	@Override
	public Knockout<H> visible(Object expression, Object initialValue) {
		return target.visible(expression, initialValue);
	}

	@Override
	public Knockout<H> foreach(Object expression) {
		return target.foreach(expression);
	}

	@Override
	public Knockout<H> foreach(Object expression, Object initialValue) {
		return target.foreach(expression, initialValue);
	}

	@Override
	public Knockout<H> text(Object expression) {
		return target.text(expression);
	}

	@Override
	public Knockout<H> text(Object expression, Object initialValue) {
		return target.text(expression, initialValue);
	}

	@Override
	public Knockout<H> if_(Object expression) {
		return target.if_(expression);
	}

	@Override
	public Knockout<H> html(Object expression) {
		return target.html(expression);
	}

	@Override
	public Knockout<H> if_(Object expression, Object initialValue) {
		return target.if_(expression, initialValue);
	}

	@Override
	public Knockout<H> html(Object expression, Object initialValue) {
		return target.html(expression, initialValue);
	}

	@Override
	public Knockout<H> ifnot(Object expression) {
		return target.ifnot(expression);
	}

	@Override
	public Knockout<H> css(Object expression) {
		return target.css(expression);
	}

	@Override
	public Knockout<H> ifnot(Object expression, Object initialValue) {
		return target.ifnot(expression, initialValue);
	}

	@Override
	public Knockout<H> style(Object expression) {
		return target.style(expression);
	}

	@Override
	public Knockout<H> with(Object expression) {
		return target.with(expression);
	}

	@Override
	public Knockout<H> attr(Object expression) {
		return target.attr(expression);
	}

	@Override
	public Knockout<H> component(Object expression) {
		return target.component(expression);
	}

	@Override
	public Knockout<H> click(Object expression) {
		return target.click(expression);
	}

	@Override
	public Knockout<H> event(Object expression) {
		return target.event(expression);
	}

	@Override
	public Knockout<H> bind(String binding, Object expression, Object initialValue) {
		return target.bind(binding, expression, initialValue);
	}

	@Override
	public Knockout<H> submit(Object expression) {
		return target.submit(expression);
	}

	@Override
	public Knockout<H> enable(Object expression) {
		return target.enable(expression);
	}

	@Override
	public Knockout<H> enable(Object expression, Object initialValue) {
		return target.enable(expression, initialValue);
	}

	@Override
	public Knockout<H> disable(Object expression) {
		return target.disable(expression);
	}

	@Override
	public Knockout<H> disable(Object expression, Object initialValue) {
		return target.disable(expression, initialValue);
	}

	@Override
	public Knockout<H> value(Object expression) {
		return target.value(expression);
	}

	@Override
	public String generateObservables(String... excludes) {
		return target.generateObservables(excludes);
	}

	@Override
	public Knockout<H> value(Object expression, Object initialValue) {
		return target.value(expression, initialValue);
	}

	@Override
	public Knockout<H> textInput(Object expression) {
		return target.textInput(expression);
	}

	@Override
	public Knockout<H> textInput(Object expression, Object initialValue) {
		return target.textInput(expression, initialValue);
	}

	@Override
	public Knockout<H> hasFocus(Object expression) {
		return target.hasFocus(expression);
	}

	@Override
	public Knockout<H> hasFocus(Object expression, Object initialValue) {
		return target.hasFocus(expression, initialValue);
	}

	@Override
	public Knockout<H> checked(Object expression) {
		return target.checked(expression);
	}

	@Override
	public Knockout<H> checked(Object expression, Object initialValue) {
		return target.checked(expression, initialValue);
	}

	@Override
	public Knockout<H> options(Object expression) {
		return target.options(expression);
	}

	@Override
	public Knockout<H> options(Object expression, Object initialValue) {
		return target.options(expression, initialValue);
	}

	@Override
	public Knockout<H> selectedOptions(Object expression) {
		return target.selectedOptions(expression);
	}

	@Override
	public Knockout<H> selectedOptions(Object expression, Object initialValue) {
		return target.selectedOptions(expression, initialValue);
	}

	@Override
	public Collection<Binding> getAllBindings() {
		return target.getAllBindings();
	}

	@Override
	public Knockout<H> uniqueName(Object expression) {
		return target.uniqueName(expression);
	}

	@Override
	public Knockout<H> template(Object expression) {
		return target.template(expression);
	}

	@Override
	public H toHTMLElement() {
		return target.toHTMLElement();
	}
	
}
