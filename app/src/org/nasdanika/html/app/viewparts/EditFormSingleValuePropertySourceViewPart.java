package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.FieldContainer;
import org.nasdanika.html.FieldSet;
import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.InputPropertyDescriptor;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.FormGroup;

/**
 * Generates edit form. {@link InputPropertyDescriptor}s are rendered as {@link FormGroup}s. 
 * Categories are generated as {@link FieldSet}s.   
 * @author Pavel Vlasov
 *
 */
public abstract class EditFormSingleValuePropertySourceViewPart implements ViewPart {

	private SingleValuePropertySource propertySource;
	private Map<DeviceSize, Integer> horizontalLabelWidths;

	public EditFormSingleValuePropertySourceViewPart(SingleValuePropertySource propertySource, Map<DeviceSize, Integer> horizontalLabelWidths) {
		this.propertySource = propertySource;
		this.horizontalLabelWidths = horizontalLabelWidths;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		if (propertySource == null) {
			return "";
		}
		
		Form form = viewGenerator.get(HTMLFactory.class).form();		
		for(Entry<Label, List<PropertyDescriptor>> descriptorGroup: Util.groupByCategory(propertySource.getPropertyDescriptors())) {
			FieldContainer<?> fieldContainer;
			Label category = descriptorGroup.getKey();
			if (category == null) {
				fieldContainer = form;
			} else {
				fieldContainer = form.fieldset();
				((FieldSet) fieldContainer).legend(viewGenerator.label(category));
			}
			for (PropertyDescriptor pd: descriptorGroup.getValue()) {
				if (pd instanceof InputPropertyDescriptor) {
					fieldContainer.content(((InputPropertyDescriptor) pd).formGroup(viewGenerator, propertySource.getValue(), horizontalLabelWidths));
				} else {
					fieldContainer.content(controlGroup(pd));
				}
				// Better way to render actions? 
				List<Action> editActions = pd.getActionProvider(propertySource.getValue()).getEditActions();
				if (!editActions.isEmpty()) {
					ButtonToolbar buttonToolbar = viewGenerator.buttonToolbar(editActions);
					buttonToolbar.margin().top(1).bottom(1);
					fieldContainer.content(buttonToolbar);
				}
			}
			
		}
		
		form.content(viewGenerator.buttonToolbar(propertySource.getActionProvider(propertySource.getValue()).getEditActions()));
		return form;
	}
	
	/**
	 * Creates a control group for property descriptors which do not implement {@link InputPropertyDescriptor}. 
	 * @param propertyDescriptor
	 * @return
	 */
	protected Object controlGroup(PropertyDescriptor propertyDescriptor) {
		// TODO
		return null;
	}

}
