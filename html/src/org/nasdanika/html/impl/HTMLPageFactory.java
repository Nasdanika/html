package org.nasdanika.html.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ContextualFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;

public class HTMLPageFactory implements ContextualFactory<HTMLPage>, Marked {
	
	private Marker marker;
	
//	private static final String ID_KEY = "id";
//	private static final String ICON_KEY = "icon";
//	private static final String TEXT_KEY = "text";
//	private static final String TOOLTIP_KEY = "tooltip";
//	private static final String COLOR_KEY = "color";
//	private static final String OUTLINE_KEY = "outline";
//	private static final String DESCRIPTION_KEY = "description";
//	private static final String NOTIFICATION_KEY = "notification";
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	protected Collection<String> getSupportedKeys() {
		Collection<String> ret = new ArrayList<>();
//		ret.add(ID_KEY);
//		ret.add(ICON_KEY);
//		ret.add(TEXT_KEY);
//		ret.add(TOOLTIP_KEY);
//		ret.add(COLOR_KEY);
//		ret.add(OUTLINE_KEY);
//		ret.add(DESCRIPTION_KEY);
//		ret.add(NOTIFICATION_KEY);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public HTMLPageFactory(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			Util.checkUnsupportedKeys(configMap, getSupportedKeys());
//			id = Util.getString(configMap, ID_KEY, UUID.randomUUID().toString());
//			icon = Util.getString(configMap, ICON_KEY, null);
//			text = Util.getString(configMap, TEXT_KEY, null);
//			tooltip = Util.getString(configMap, TOOLTIP_KEY, null);
//			color = Util.getString(configMap, COLOR_KEY, null);
//			colorMarker = Util.getMarker(configMap, COLOR_KEY);
//			outline = Util.getBoolean(configMap, OUTLINE_KEY, false);
//			description = Util.getString(configMap, DESCRIPTION_KEY, null);
//			notification = Util.getString(configMap, NOTIFICATION_KEY, null);
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}		
	
	@Override
	public HTMLPage create(Context context) throws Exception {
		HTMLPage ret = createPage(context);
//		if (color != null) {
//			try {
//				ret.setColor(Color.valueOf(context.interpolateToString(color)));
//			} catch (IllegalArgumentException e) {
//				throw new ConfigurationException(e.getMessage(), e, colorMarker);
//			}
//		}
//		ret.setDescription(context.interpolateToString(description));
//		ret.setIcon(context.interpolateToString(icon));
//		ret.setId(context.interpolateToString(id));
//		ret.setNotification(context.interpolateToString(notification));
//		ret.setOutline(outline);
//		ret.setText(context.interpolateToString(text));
//		ret.setTooltip(context.interpolateToString(tooltip));
		
		return ret;
	}
	
	protected HTMLPage createPage(Context context) {
		return HTMLFactory.INSTANCE.page();
	}

}
