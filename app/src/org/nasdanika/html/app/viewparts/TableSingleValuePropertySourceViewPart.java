package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.ActionProvider;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;

/**
 * Generates a two-column table with property names in one column and property values or controls in the other.
 * Categories are generated as cards with property tables inside.   
 * @author Pavel Vlasov
 *
 */
public abstract class TableSingleValuePropertySourceViewPart implements ViewPart {

	protected SingleValuePropertySource propertySource;
	protected boolean isEdit;

	protected TableSingleValuePropertySourceViewPart(SingleValuePropertySource propertySource, boolean isEdit) {
		this.propertySource = propertySource;
		this.isEdit = isEdit;
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
				ActionProvider actionProvider = pd.getActionProvider(propertySource.getValue());
				if (!(isEdit ? actionProvider.getEditActions() : actionProvider.getViewActions()).isEmpty()) {
					hasActions = true;
					break;
				}
			}			
			for (PropertyDescriptor pd: descriptorGroup.getValue()) {
				Row propertyRow = propertyTable.row();
				Cell nameHeader = propertyRow.header(viewGenerator.labelFragment(pd));
				viewGenerator.decorate(nameHeader, pd);
				nameHeader.toHTMLElement().style("width", "10%").style().whiteSpace().nowrap();
				propertyRow.cell(viewGenerator.processViewPart(generateValueView(propertySource.getValue(), pd, viewGenerator, progressMonitor.split("Generating value view of "+pd.getPropertyName(), 1, propertySource.getValue(), pd))));
				if (hasActions) {
					ActionProvider actionProvider = pd.getActionProvider(propertySource.getValue());
					ButtonToolbar buttonToolbar = viewGenerator.buttonToolbar(isEdit ? actionProvider.getEditActions() : actionProvider.getViewActions());
					buttonToolbar.margin().top(Breakpoint.DEFAULT, Size.S1).bottom(Breakpoint.DEFAULT, Size.S1);
					propertyRow.cell(buttonToolbar);
				}
			}
			
			Label category = descriptorGroup.getKey();
			if (category == null) {
				ret.content(propertyTable);
			} else {
				Card propertyCard = viewGenerator.get(BootstrapFactory.class).card();
				propertyCard.border(category.getColor());
				viewGenerator.label(category, propertyCard.getHeader().toHTMLElement());
				propertyCard.getBody().toHTMLElement().content(propertyTable);
				ret.content(propertyCard);
			}
		}
		
		ActionProvider actionProvider = propertySource.getActionProvider(propertySource.getValue());
		ret.content(viewGenerator.buttonToolbar(isEdit ? actionProvider.getEditActions() : actionProvider.getViewActions()));
		return ret;
	}
	
	
	/**
	 * Generates value view, e.g. text or input control. 
	 * @param viewGenerator
	 * @param progressMonitor
	 * @return
	 */
	protected abstract Object generateValueView(
			Object obj, 
			PropertyDescriptor propertyDescriptor, 
			ViewGenerator viewGenerator, 
			ProgressMonitor progressMonitor);

}

