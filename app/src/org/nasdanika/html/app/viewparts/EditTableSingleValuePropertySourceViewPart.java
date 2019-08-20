package org.nasdanika.html.app.viewparts;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLFactory;
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
		super(propertySource, true);
	}
	
	/**
	 * Wraps table into a form.
	 */
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
		Form form = htmlFactory.form();
		form.content(super.generate(viewGenerator, progressMonitor));
		// TODO - context (submit) actions.
		
		return form;
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
