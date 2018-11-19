package org.nasdanika.html.app;

import java.util.List;

import org.nasdanika.html.bootstrap.Color;

public class ActionFilter implements Action {
	
	private Action target;
	
	public String getIcon() {
		return target.getIcon();
	}

	public String getLabel() {
		return target.getLabel();
	}

	public boolean isDisabled() {
		return target.isDisabled();
	}

	public String getTooltip() {
		return target.getTooltip();
	}

	public Color getColor() {
		return target.getColor();
	}

	public boolean isOutline() {
		return target.isOutline();
	}

	public String getConfirmation() {
		return target.getConfirmation();
	}

	public boolean isFloatRight() {
		return target.isFloatRight();
	}

	public List<Action> getChildren() {
		return target.getChildren();
	}

	public List<Action> getContextActions() {
		return target.getContextActions();
	}

	public String getId() {
		return target.getId();
	}

	public String getDescription() {
		return target.getDescription();
	}

	public ActionFilter(Action target) {
		this.target = target;
	}

	
}
