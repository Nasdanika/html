package org.nasdanika.html.app.impl;

import java.nio.file.Path;
import java.util.List;

import org.nasdanika.html.app.Action;

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
	public List<Action> getChildren() {
		return target.getChildren();
	}

	@Override
	public List<Action> getContextActions() {
		return target.getContextActions();
	}

	@Override
	public Action getParent() {
		return target.getParent();
	}

	@Override
	public List<Path> getPath() {
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
		
}
