package org.nasdanika.html.app.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.bootstrap.Color;

/**
 * Bean label implementation
 * @author Pavel Vlasov
 *
 */
public class LabelImpl extends IdentityImpl implements Label {
	
	private Marker marker;
	
	private String icon;
	private String text;
	private String tooltip;
	private Color color;
	private boolean outline;
	private String description;
	private String notification;
	
	public LabelImpl() {
		
	}
	
	private static final String ICON_KEY = "icon";
	private static final String TEXT_KEY = "text";
	private static final String TOOLTIP_KEY = "tooltip";
	private static final String COLOR_KEY = "color";
	private static final String OUTLINE_KEY = "outline";
	private static final String DESCRIPTION_KEY = "description";
	private static final String NOTIFICATION_KEY = "notification";

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

}
