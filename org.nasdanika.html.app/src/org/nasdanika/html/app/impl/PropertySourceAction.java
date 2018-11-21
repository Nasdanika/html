package org.nasdanika.html.app.impl;

import java.util.List;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.PropertySource;

/**
 * Action backed by a property source. Property source actions are returned as Actions' context actions.
 * @author Pavel Vlasov
 *
 */
public abstract class PropertySourceAction<T extends PropertySource> extends LabelFilter<T> implements Action {
	
	public PropertySourceAction(T propertySource) {
		super(propertySource);
	}

	@Override
	public List<Action> getContextActions() {
		return target.getActions();
	}

}
