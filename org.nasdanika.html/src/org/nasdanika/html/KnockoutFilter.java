package org.nasdanika.html;

import java.util.Collection;

public class KnockoutFilter<T> implements Knockout<T> {
	
	private Knockout<T> target;

	public KnockoutFilter(Knockout<T> target) {
		this.target = target;
	}

	@Override
	public T visible(Object expression) {
		return target.visible(expression);
	}

	@Override
	public T visible(Object expression, Object initialValue) {
		return target.visible(expression, initialValue);
	}

	@Override
	public T foreach(Object expression) {
		return target.foreach(expression);
	}

	@Override
	public T foreach(Object expression, Object initialValue) {
		return target.foreach(expression, initialValue);
	}

	@Override
	public T text(Object expression) {
		return target.text(expression);
	}

	@Override
	public T text(Object expression, Object initialValue) {
		return target.text(expression, initialValue);
	}

	@Override
	public T if_(Object expression) {
		return target.if_(expression);
	}

	@Override
	public T html(Object expression) {
		return target.html(expression);
	}

	@Override
	public T if_(Object expression, Object initialValue) {
		return target.if_(expression, initialValue);
	}

	@Override
	public T html(Object expression, Object initialValue) {
		return target.html(expression, initialValue);
	}

	@Override
	public T ifnot(Object expression) {
		return target.ifnot(expression);
	}

	@Override
	public T css(Object expression) {
		return target.css(expression);
	}

	@Override
	public T ifnot(Object expression, Object initialValue) {
		return target.ifnot(expression, initialValue);
	}

	@Override
	public T style(Object expression) {
		return target.style(expression);
	}

	@Override
	public T with(Object expression) {
		return target.with(expression);
	}

	@Override
	public T attr(Object expression) {
		return target.attr(expression);
	}

	@Override
	public T component(Object expression) {
		return target.component(expression);
	}

	@Override
	public T click(Object expression) {
		return target.click(expression);
	}

	@Override
	public T event(Object expression) {
		return target.event(expression);
	}

	@Override
	public T bind(String binding, Object expression, Object initialValue) {
		return target.bind(binding, expression, initialValue);
	}

	@Override
	public T submit(Object expression) {
		return target.submit(expression);
	}

	@Override
	public T enable(Object expression) {
		return target.enable(expression);
	}

	@Override
	public T enable(Object expression, Object initialValue) {
		return target.enable(expression, initialValue);
	}

	@Override
	public T disable(Object expression) {
		return target.disable(expression);
	}

	@Override
	public T disable(Object expression, Object initialValue) {
		return target.disable(expression, initialValue);
	}

	@Override
	public T value(Object expression) {
		return target.value(expression);
	}

	@Override
	public String generateObservables(String... excludes) {
		return target.generateObservables(excludes);
	}

	@Override
	public T value(Object expression, Object initialValue) {
		return target.value(expression, initialValue);
	}

	@Override
	public T textInput(Object expression) {
		return target.textInput(expression);
	}

	@Override
	public T textInput(Object expression, Object initialValue) {
		return target.textInput(expression, initialValue);
	}

	@Override
	public T hasFocus(Object expression) {
		return target.hasFocus(expression);
	}

	@Override
	public T hasFocus(Object expression, Object initialValue) {
		return target.hasFocus(expression, initialValue);
	}

	@Override
	public T checked(Object expression) {
		return target.checked(expression);
	}

	@Override
	public T checked(Object expression, Object initialValue) {
		return target.checked(expression, initialValue);
	}

	@Override
	public T options(Object expression) {
		return target.options(expression);
	}

	@Override
	public T options(Object expression, Object initialValue) {
		return target.options(expression, initialValue);
	}

	@Override
	public T selectedOptions(Object expression) {
		return target.selectedOptions(expression);
	}

	@Override
	public T selectedOptions(Object expression, Object initialValue) {
		return target.selectedOptions(expression, initialValue);
	}

	@Override
	public Collection<Binding> getAllBindings() {
		return target.getAllBindings();
	}

	@Override
	public T uniqueName(Object expression) {
		return target.uniqueName(expression);
	}

	@Override
	public T template(Object expression) {
		return target.template(expression);
	}
	
}
