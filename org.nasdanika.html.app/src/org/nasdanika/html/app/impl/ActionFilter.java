package org.nasdanika.html.app.impl;

import java.util.List;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;

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
	public List<? extends Action> getContextActions() {
		return target.getContextActions();
	}

	@Override
	public Action getParent() {
		return target.getParent();
	}

	@Override
	public List<Action> getPath() {
		return target.getPath();
	}

	@Override
	public Object execute() {
		return target.execute();
	}

	@Override
	public boolean isInRole(String role) {
		return target.isInRole(role);
	}

	@Override
	public ActionActivator getActivator() {
		return target.getActivator();
	}
		
}
