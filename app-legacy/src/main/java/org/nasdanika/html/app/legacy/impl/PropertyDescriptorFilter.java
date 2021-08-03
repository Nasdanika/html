package org.nasdanika.html.app.impl;

import java.util.List;

import org.nasdanika.html.app.ActionProvider;
import org.nasdanika.html.app.Choice;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.ViewGenerator;

public class PropertyDescriptorFilter<T extends PropertyDescriptor> extends LabelFilter<T> implements PropertyDescriptor {

	public PropertyDescriptorFilter(T target) {
		super(target);
	}

	@Override
	public Object getDisplayValue(Object obj) {
		return target.getDisplayValue(obj);
	}

	@Override
	public List<Choice> getChoices(Object obj) {
		return target.getChoices(obj);
	}

	@Override
	public boolean isEditable(Object obj) {
		return target.isEditable(obj);
	}

	@Override
	public Diagnostic update(Object obj, Object originalValue, Object newValue) {
		return target.update(obj, originalValue, newValue);
	}

	@Override
	public Label getCategory() {
		return target.getCategory();
	}

	@Override
	public boolean isSortable() {
		return target.isSortable();		
	}

	@Override
	public boolean isFilterable() {
		return target.isFilterable();
	}
	
	@Override
	public ActionProvider getActionProvider(Object obj) {
		return target.getActionProvider(obj);
	}

	@Override
	public Object createEditControl(ViewGenerator viewGenerator, Object obj) {
		return target.createEditControl(viewGenerator, obj);
	}
	
	@Override
	public String getPropertyName() {
		return target.getPropertyName();
	}
	
}
