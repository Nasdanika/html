package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.MultiValuePropertySource;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.Table;

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
		List<Object> values = propertySource.getValues();
		boolean hasActions = false;
		for (Object value: values) {
			if (!propertySource.getActions(value).isEmpty()) {
				hasActions = true;
				break;
			}
		}
		List<Entry<Label, List<PropertyDescriptor>>> categories = Util.groupByCategory(propertySource.getPropertyDescriptors());
		boolean hasCategoryRow = categories.size() > 1 || categories.size() == 1 && categories.get(0).getKey() != null;
		Table table = createTable(viewGenerator);
		if (hasCategoryRow) {
			Row categoryRow = table.row();			
			for (Entry<Label, List<PropertyDescriptor>> categoryEntry: categories) {
				Label category = categoryEntry.getKey();
				if (category == null) {
					for (PropertyDescriptor pd: categoryEntry.getValue()) {
						categoryRow.header(viewGenerator.labelFragment(pd)).toHTMLElement().rowspan(2);
					}
				} else {
					categoryRow.header(viewGenerator.labelFragment(category)).toHTMLElement().colspan(categoryEntry.getValue().size());					
				}
			}
			if (hasActions) {
				categoryRow.header(actionsHeader()).toHTMLElement().rowspan(2);
			}
			Row pdRow = table.row();			
			for (Entry<Label, List<PropertyDescriptor>> categoryEntry: categories) {
				Label category = categoryEntry.getKey();
				if (category != null) {
					for (PropertyDescriptor pd: categoryEntry.getValue()) {
						pdRow.header(viewGenerator.labelFragment(pd)).toHTMLElement();
					}
				}
			}			
		} else {
			Row pdRow = table.row();			
			for (Entry<Label, List<PropertyDescriptor>> categoryEntry: categories) {
				for (PropertyDescriptor pd: categoryEntry.getValue()) {
					pdRow.header(viewGenerator.labelFragment(pd)).toHTMLElement();
				}
			}
			if (hasActions) {
				pdRow.header(actionsHeader()).toHTMLElement();
			}
		}
		
		for (Object value: values) {
			Row valueRow = table.row();
			for (Entry<Label, List<PropertyDescriptor>> categoryEntry: categories) {
				for (PropertyDescriptor pd: categoryEntry.getValue()) {
					valueRow.cell(viewGenerator.processViewPart(pd.getDisplayValue(value)));
				}
			}
			if (hasActions) {
				// TODO - filter actions - just an icon if there is an icon and label in the tooltip.
				valueRow.cell(viewGenerator.buttonToolbar(propertySource.getActions(value)));
			}
		}
		// TODO - category color as cell border and category name background, property descriptor color as cell background.
		List<Action> propertySourceActions = propertySource.getActions();
		if (propertySourceActions.isEmpty()) {
			return table;
		}
		return viewGenerator.getHTMLFactory().fragment(table, viewGenerator.buttonToolbar(propertySourceActions));
	}

	/**
	 * @return Content to show in actions header. This method returns "Actions".
	 */
	protected Object actionsHeader() {
		return "Actions";
	}

	/**
	 * Creates and configures a table. This method creates a bordered table.
	 * @param viewGenerator
	 * @return
	 */
	protected Table createTable(ViewGenerator viewGenerator) {
		return viewGenerator.getBootstrapFactory().table().bordered();
	}

}
