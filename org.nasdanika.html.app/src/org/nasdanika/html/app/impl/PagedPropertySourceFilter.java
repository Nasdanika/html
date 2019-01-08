package org.nasdanika.html.app.impl;

import java.util.Map;

import org.nasdanika.html.app.PagedPropertySource;

public class PagedPropertySourceFilter<T extends PagedPropertySource> extends PropertySourceFilter<T> implements PagedPropertySource {

	public PagedPropertySourceFilter(T target) {
		super(target);
	}

	@Override
	public Pages getPages() {
		return target.getPages();
	}

	@Override
	public Pages getPages(Map<Object, String> filter, Map<Object, Boolean> sort) {
		return target.getPages(filter, sort);
	}
	
}
