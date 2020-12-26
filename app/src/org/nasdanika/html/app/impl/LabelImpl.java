package org.nasdanika.html.app.impl;

import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.Color;

/**
 * Bean label implementation
 * @author Pavel Vlasov
 *
 */
public class LabelImpl extends IdentityImpl implements Label, Decorator {
	
	private String icon;
	private String text;
	private String tooltip;
	private Color color;
	private boolean outline;
	private String description;
	private String notification;
	private Decorator decorator;
	
	public LabelImpl() {
		
	}
	
	public LabelImpl(Decorator decorator) {
		this.decorator = decorator;
	}

	@Override
	public String getIcon() {
		return icon;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public String getTooltip() {
		return tooltip;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public boolean isOutline() {
		return outline;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getNotification() {
		return notification;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setOutline(boolean outline) {
		this.outline = outline;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}
	
	@Override
	public void decorate(Object target, ViewGenerator viewGenerator) {
		if (decorator != null) {
			decorator.decorate(target, viewGenerator);
		}
	}		

}
