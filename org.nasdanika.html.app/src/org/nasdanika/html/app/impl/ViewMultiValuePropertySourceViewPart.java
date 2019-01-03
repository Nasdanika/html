package org.nasdanika.html.app.impl;

import org.nasdanika.html.app.MultiValuePropertySource;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;

/**
 * Generates a table with property names as headers and property values in rows.
 * Categories are generated as header cells spanning several columns.   
 * @author Pavel Vlasov
 *
 */
public class ViewMultiValuePropertySourceViewPart implements ViewPart {

	private MultiValuePropertySource propertySource;

	public ViewMultiValuePropertySourceViewPart(MultiValuePropertySource propertySource) {
		this.propertySource = propertySource;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator) {
		// Categories, category color as cell border and category name background, property descriptor color as cell background. 
		return "TODO - table from "+propertySource;
	}

}
