package org.nasdanika.html.app.impl;

import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.Color;

public class LabelFilter<T extends Label> extends IdentityFilter<T> implements Label, Decorator {
	
	public LabelFilter(T target) {
		super(target);
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
	public Object getId() {
		return target.getId();
	}

	@Override
	public String getNotification() {
		return target.getNotification();
	}

	@Override
	public void decorate(Object target, ViewGenerator viewGenerator) {
		if (this.target instanceof Decorator) {
			((Decorator) this.target).decorate(target, viewGenerator);
		}		
	}

}
