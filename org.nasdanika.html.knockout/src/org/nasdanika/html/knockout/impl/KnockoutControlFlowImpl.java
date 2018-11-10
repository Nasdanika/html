package org.nasdanika.html.knockout.impl;

import org.nasdanika.html.knockout.KnockoutControlFlow;

abstract class KnockoutControlFlowImpl<T> implements KnockoutControlFlow<T> {
		
	@Override
	public T component(Object expression) {
		return bind(KnockoutBindingInfo.COMPONENT.name, expression, null);
	}
	
	@Override
	public T foreach(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.FOREACH.name, expression, initialValue);
	}

	@Override
	public T foreach(Object expression) {
		return foreach(expression, null);
	}
	
	@Override
	public T if_(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.IF.name, expression, initialValue);
	}
	
	@Override
	public T if_(Object expression) {
		return if_(expression, null);
	}

	@Override
	public T ifnot(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.IFNOT.name, expression, initialValue);
	}
	
	@Override
	public T ifnot(Object expression) {
		return ifnot(expression, null);
	}

	@Override
	public T with(Object expression) {
		return bind(KnockoutBindingInfo.WITH.name, expression, null);
	}		

}
