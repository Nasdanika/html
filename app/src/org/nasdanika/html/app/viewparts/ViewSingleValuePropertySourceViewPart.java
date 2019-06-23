package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;

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
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		if (propertySource == null) {
			return "";
		}
		
		Fragment ret = viewGenerator.get(HTMLFactory.class).fragment();		
		for(Entry<Label, List<PropertyDescriptor>> descriptorGroup: Util.groupByCategory(propertySource.getPropertyDescriptors())) {			
			Table propertyTable = viewGenerator.get(BootstrapFactory.class).table();
			propertyTable.bordered();
			boolean hasActions = false;
			for (PropertyDescriptor pd: descriptorGroup.getValue()) {
				if (!pd.getActionProvider(propertySource.getValue()).getViewActions().isEmpty()) {
					hasActions = true;
					break;
				}
			}			
			for (PropertyDescriptor pd: descriptorGroup.getValue()) {
				Row propertyRow = propertyTable.row();
				Cell nameHeader = propertyRow.header(viewGenerator.labelFragment(pd));
				nameHeader.toHTMLElement().style("width", "10%").style().whiteSpace().nowrap();
				propertyRow.cell(viewGenerator.processViewPart(pd.getDisplayValue(propertySource.getValue())));
				if (hasActions) {
					ButtonToolbar buttonToolbar = viewGenerator.buttonToolbar(pd.getActionProvider(propertySource.getValue()).getViewActions());
					buttonToolbar.margin().top(1).bottom(1);
					propertyRow.cell(buttonToolbar);
				}
			}
			
			Label category = descriptorGroup.getKey();
			if (category == null) {
				ret.content(propertyTable);
			} else {
				Card propertyCard = viewGenerator.get(BootstrapFactory.class).card();
				propertyCard.border(category.getColor());
				viewGenerator.label(category, propertyCard.getTitle().toHTMLElement());
				propertyCard.getBody().toHTMLElement().content(propertyTable);
				ret.content(propertyCard);
			}
		}
		return ret;
	}

}
