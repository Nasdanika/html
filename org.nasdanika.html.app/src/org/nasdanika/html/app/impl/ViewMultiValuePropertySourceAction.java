package org.nasdanika.html.app.impl;

import java.util.Map;

import org.nasdanika.html.app.MultiValuePropertySource;
import org.nasdanika.html.app.ViewPart;

public abstract class ViewMultiValuePropertySourceAction extends PropertySourceAction<MultiValuePropertySource> {
	
	public ViewMultiValuePropertySourceAction(MultiValuePropertySource propertySource) {
		super(propertySource);
	}
	
	@Override
	protected ViewPart getViewPart(Map<String, Object> input) {
		return new ViewMultiValuePropertySourceViewPart(target);
	}

}
