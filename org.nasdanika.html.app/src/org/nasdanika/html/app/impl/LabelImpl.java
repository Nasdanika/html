package org.nasdanika.html.app.impl;

import java.util.Map;

import org.nasdanika.html.app.Label;
import org.nasdanika.html.bootstrap.Color;

/**
 * Bean label implementation
 * @author Pavel Vlasov
 *
 */
public class LabelImpl implements Label {
	
	private String icon;
	private String text;
	private String tooltip;
	private Color color;
	private boolean outline;
	private String description;
	private Object id;
	private String notification;
	
	public LabelImpl() {
		
	}
	
	public LabelImpl(Map<String, Object> data) {
		icon = (String) data.get("icon");
		text = (String) data.get("text");
		tooltip = (String) data.get("tooltip");
		Object cv = data.get("color");
		color = cv instanceof String ? Color.valueOf((String) cv) : (Color) cv;
		outline = Boolean.TRUE.equals(data.get("outline"));
		description = (String) data.get("description");
		id = data.get("id");
		notification = (String) data.get("notification");
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
	public Object getId() {
		return id;
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

	public void setId(Object id) {
		this.id = id;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}		

}
