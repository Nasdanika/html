package org.nasdanika.html.app.impl;

import java.util.List;
import java.util.Map;

import org.nasdanika.html.app.MultiValuePropertySource;

public class MultiValuePropertySourceFilter<T extends MultiValuePropertySource> extends PropertySourceFilter<T> implements MultiValuePropertySource {

	public MultiValuePropertySourceFilter(T target) {
		super(target);
	}

	@Override
	public List<Object> getValues() {
		return target.getValues();
	}

	@Override
	public List<Object> getValues(Map<Object, String> filter, Map<Object, Boolean> sort) {
		return target.getValues(filter, sort);
	}
	
}
