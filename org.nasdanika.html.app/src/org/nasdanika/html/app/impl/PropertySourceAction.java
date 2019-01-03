package org.nasdanika.html.app.impl;

import java.util.List;
import java.util.Map;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.PropertySource;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;

/**
 * Action backed by a property source. Property source actions are returned as Actions' context actions.
 * @author Pavel Vlasov
 *
 */
public abstract class PropertySourceAction<T extends PropertySource> extends LabelFilter<T> implements Action {

	protected abstract ViewPart getViewPart(Map<String, Object> input);
	
	public PropertySourceAction(T propertySource) {
		super(propertySource);
	}

	@Override
	public List<Action> getContextActions() {
		return target.getActions();
	}
	
	@Override
	public Object execute(ViewGenerator viewGenerator, Map<String, Object> input) {
		return getViewPart(input).generate(viewGenerator);
	}
	
}
