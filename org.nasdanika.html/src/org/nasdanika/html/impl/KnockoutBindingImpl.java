package org.nasdanika.html.impl;

import org.nasdanika.html.KnockoutBindingsSource.Binding;

class KnockoutBindingImpl implements Binding {

	private String name;
	private boolean isArray;
	private Object initialValue;

	public KnockoutBindingImpl(
			String name, 
			boolean isArray,
			Object initialValue) {
		
		this.name = name;
		this.isArray = isArray;
		this.initialValue = initialValue;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Object getInitialValue() {
		return initialValue;
	}

	@Override
	public boolean isArray() {
		return isArray;
	}
	
	void setInitialValue(Object initialValue) {
		this.initialValue = initialValue;
	}

}
