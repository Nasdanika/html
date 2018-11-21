package org.nasdanika.html.app.impl;

import org.nasdanika.html.app.Label;
import org.nasdanika.html.bootstrap.Color;

public class LabelFilter<T extends Label> implements Label {
	
	protected T target;

	protected LabelFilter(T target) {
		this.target = target;
	}
	
	@Override
	public String getIcon() {
		return target.getIcon();
	}

	@Override
	public String getText() {
		return target.getText();
	}

	@Override
	public String getTooltip() {
		return target.getTooltip();
	}

	@Override
	public Color getColor() {
		return target.getColor();
	}

	@Override
	public boolean isOutline() {
		return target.isOutline();
	}

	@Override
	public String getDescription() {
		return target.getDescription();
	}

	@Override
	public String getId() {
		return target.getId();
	}

}
