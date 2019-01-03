package org.nasdanika.html.app.impl;

import java.util.Map;

import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.app.ViewPart;

public abstract class ViewSingleValuePropertySourceAction extends PropertySourceAction<SingleValuePropertySource> {
	
	public ViewSingleValuePropertySourceAction(SingleValuePropertySource propertySource) {
		super(propertySource);
	}
	
	@Override
	protected ViewPart getViewPart(Map<String, Object> input) {
		return new ViewSingleValuePropertySourceViewPart(target);
	}

}
