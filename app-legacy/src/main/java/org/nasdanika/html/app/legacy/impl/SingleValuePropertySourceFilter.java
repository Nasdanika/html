package org.nasdanika.html.app.impl;

import org.nasdanika.html.app.SingleValuePropertySource;

public class SingleValuePropertySourceFilter<T extends SingleValuePropertySource> extends PropertySourceFilter<T> implements SingleValuePropertySource {

	public SingleValuePropertySourceFilter(T target) {
		super(target);
	}

	@Override
	public Object getValue() {
		return target.getValue();
	}
	
}
