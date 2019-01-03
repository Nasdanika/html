package org.nasdanika.html.app.impl;

import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;

/**
 * Generates a two-column table with property names in one column and property values in the other.
 * Categories are generated as cards with property tables inside.   
 * @author Pavel Vlasov
 *
 */
public class ViewSingleValuePropertySourceViewPart implements ViewPart {

	private SingleValuePropertySource propertySource;

	public ViewSingleValuePropertySourceViewPart(SingleValuePropertySource propertySource) {
		this.propertySource = propertySource;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator) {
		// Categories, category color as border.
		return "TODO - table from "+propertySource;
	}

}
