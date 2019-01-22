package org.nasdanika.html.app.impl;

import java.util.List;
import java.util.Map;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;

public class ActionFilter<T extends Action> extends LabelFilter<T> implements Action {

	protected ActionFilter(T target) {
		super(target);
	}

	@Override
	public boolean isDisabled() {
		return target.isDisabled();
	}

	@Override
	public String getConfirmation() {
		return target.getConfirmation();
	}

	@Override
	public boolean isFloatRight() {
		return target.isFloatRight();
	}

	@Override
	public List<? extends Action> getChildren() {
		return target.getChildren();
	}

	@Override
	public Action getParent() {
		return target.getParent();
	}

	@Override
	public Object execute(ViewGenerator viewGenerator, Map<String, Object> input) {
		return target.execute(viewGenerator, input);
	}

	@Override
	public ActionActivator getActivator() {
		return target.getActivator();
	}

	@Override
	public Label getCategory() {
		return target.getCategory();
	}

	/**
	 * Converts navigation role to section role if parent is in section role.
	 */
	@Override
	public boolean isInRole(String role) {
		Action parent = getParent();
		// Navigations become sections if parent role is section.
		if (parent != null && parent.isInRole(Role.SECTION) && target.isInRole(Role.NAVIGATION)) {
			return Role.SECTION.equals(role);
		}
		return target.isInRole(role);
	}
	
	@Override
	public String toString() {
		return super.toString() + " -> " + target;
	}
		
}
