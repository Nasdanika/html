package org.nasdanika.html.app.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.nasdanika.common.Factory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.Color;

public class LabelFactory implements Factory<ViewGenerator, Label>, Marked {
	
	private Marker marker;
	
	private String id;
	private String icon;
	private String text;
	private String tooltip;
	private String color;
	private boolean outline;
	private String description;
	private String notification;

	private Marker colorMarker;
	
	private static final String ID_KEY = "id";
	private static final String ICON_KEY = "icon";
	private static final String TEXT_KEY = "text";
	private static final String TOOLTIP_KEY = "tooltip";
	private static final String COLOR_KEY = "color";
	private static final String OUTLINE_KEY = "outline";
	private static final String DESCRIPTION_KEY = "description";
	private static final String NOTIFICATION_KEY = "notification";
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	protected Collection<String> getSupportedKeys() {
		Collection<String> ret = new ArrayList<>();
		ret.add(ID_KEY);
		ret.add(ICON_KEY);
		ret.add(TEXT_KEY);
		ret.add(TOOLTIP_KEY);
		ret.add(COLOR_KEY);
		ret.add(OUTLINE_KEY);
		ret.add(DESCRIPTION_KEY);
		ret.add(NOTIFICATION_KEY);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public LabelFactory(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			Util.checkUnsupportedKeys(configMap, getSupportedKeys());
			id = Util.getString(configMap, ID_KEY, null);
			icon = Util.getString(configMap, ICON_KEY, null);
			text = Util.getString(configMap, TEXT_KEY, null);
			tooltip = Util.getString(configMap, TOOLTIP_KEY, null);
			color = Util.getString(configMap, COLOR_KEY, null);
			colorMarker = Util.getMarker(configMap, COLOR_KEY);
			outline = Util.getBoolean(configMap, OUTLINE_KEY, false);
			description = Util.getString(configMap, DESCRIPTION_KEY, null);
			notification = Util.getString(configMap, NOTIFICATION_KEY, null);
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}		
	
	@Override
	public Label create(ViewGenerator viewGenerator) throws Exception {
		LabelImpl ret = createLabel();
		if (color != null) {
			try {
				ret.setColor(Color.valueOf(viewGenerator.interpolateToString(color)));
			} catch (IllegalArgumentException e) {
				throw new ConfigurationException(e.getMessage(), e, colorMarker);
			}
		}
		ret.setDescription(viewGenerator.interpolateToString(description));
		ret.setIcon(viewGenerator.interpolateToString(icon));
		ret.setId(viewGenerator.interpolateToString(id));
		ret.setNotification(viewGenerator.interpolateToString(notification));
		ret.setOutline(outline);
		ret.setText(viewGenerator.interpolateToString(text));
		ret.setTooltip(viewGenerator.interpolateToString(tooltip));
		
		return ret;
	}
	
	/**
	 * Override to return ActionImpl
	 * @return
	 */
	protected LabelImpl createLabel() {
		return new LabelImpl(); 
	}

}
