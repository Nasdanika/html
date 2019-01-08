package org.nasdanika.html.app.impl;

import java.util.List;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Delta;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.PropertySource;

public abstract class PropertySourceFilter<T extends PropertySource> extends LabelFilter<T> implements PropertySource {

	public PropertySourceFilter(T target) {
		super(target);
	}

	@Override
	public Object getVersion(Object obj) {
		return target.getVersion(obj);
	}

	@Override
	public Diagnostic update(Object obj, Object version, List<Delta> deltas) {
		return target.update(obj, version, deltas);
	}

	@Override
	public List<PropertyDescriptor> getPropertyDescriptors() {
		return target.getPropertyDescriptors();
	}

	@Override
	public List<Action> getActions() {
		return target.getActions();
	}

	@Override
	public List<Action> getActions(Object obj) {
		return target.getActions(obj);
	}
	
}
