package org.nasdanika.html.app.viewparts;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.app.ViewGenerator;

/**
 * Generates a two-column table with property names in one column and property values in the other.
 * Categories are generated as cards with property tables inside.   
 * @author Pavel Vlasov
 *
 */
public class EditTableSingleValuePropertySourceViewPart extends TableSingleValuePropertySourceViewPart {

	public EditTableSingleValuePropertySourceViewPart(SingleValuePropertySource propertySource) {
		super(propertySource);
	}
	
	@Override
	protected Object generateValueView(
			Object obj, 
			PropertyDescriptor propertyDescriptor, 
			ViewGenerator viewGenerator,
			ProgressMonitor progressMonitor) {		
		return propertyDescriptor.createEditControl(viewGenerator, obj);	
	}

}
