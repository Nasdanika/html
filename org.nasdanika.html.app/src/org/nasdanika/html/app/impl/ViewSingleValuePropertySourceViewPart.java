package org.nasdanika.html.app.impl;

import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

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
		if (propertySource == null) {
			return "";
		}
		
		Fragment ret = viewGenerator.getHTMLFactory().fragment();		
		for(Entry<Label, List<PropertyDescriptor>> descriptorGroup: Util.groupByCategory(propertySource.getPropertyDescriptors())) {
			
			Table propertyTable = viewGenerator.getBootstrapFactory().table();
			propertyTable.toHTMLElement().col().style("width", "10%").style().whiteSpace().nowrap();
			propertyTable.toHTMLElement().col();
			propertyTable.bordered();
			for (PropertyDescriptor pd: descriptorGroup.getValue()) {
				Row propertyRow = propertyTable.row();
				Cell nameHeader = propertyRow.header(viewGenerator.labelFragment(pd));
				nameHeader.toHTMLElement().style("width", "10%").style().whiteSpace().nowrap();
				propertyRow.cell(pd.getDisplayValue(propertySource.getValue()));
			}
			
			Label category = descriptorGroup.getKey();
			if (category == null) {
				ret.content(propertyTable);
			} else {
				Card propertyCard = viewGenerator.getBootstrapFactory().card();
				propertyCard.border(category.getColor());
				viewGenerator.label(category, propertyCard.getTitle().toHTMLElement());
				propertyCard.getBody().toHTMLElement().content(propertyTable);
				ret.content(propertyCard);
			}
		}
		return ret;
	}

}
