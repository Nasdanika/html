package org.nasdanika.html.factories;

import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;

public class HTMLPageSupplierFactory extends SupplierFactoryFeatureObject<HTMLPage> {

	@Override
	protected Function<Map<Object, Object>, HTMLPage> createResultFunction(Context context) {
		return new Function<Map<Object,Object>, HTMLPage>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Creating bootstrap container application";
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public HTMLPage execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				HTMLFactory factory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
				HTMLPage page = factory.page();
//				- from context HTMLFactory.INSTANCE.page()
				// configure
				return configure(page);
			}
		};
	}
	
	protected HTMLPage configure(HTMLPage page) {
		return page;
	}
	
//	private Marker marker;
//	
////	private static final String ID_KEY = "id";
////	private static final String ICON_KEY = "icon";
////	private static final String TEXT_KEY = "text";
////	private static final String TOOLTIP_KEY = "tooltip";
////	private static final String COLOR_KEY = "color";
////	private static final String OUTLINE_KEY = "outline";
////	private static final String DESCRIPTION_KEY = "description";
////	private static final String NOTIFICATION_KEY = "notification";
//	
//	@Override
//	public Marker getMarker() {
//		return marker;
//	}
//	
//	protected Collection<String> getSupportedKeys() {
//		Collection<String> ret = new ArrayList<>();
////		ret.add(ID_KEY);
////		ret.add(ICON_KEY);
////		ret.add(TEXT_KEY);
////		ret.add(TOOLTIP_KEY);
////		ret.add(COLOR_KEY);
////		ret.add(OUTLINE_KEY);
////		ret.add(DESCRIPTION_KEY);
////		ret.add(NOTIFICATION_KEY);
//		return ret;
//	}
//
//	@SuppressWarnings("unchecked")
//	public HTMLPageSupplierFactory(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
//		if (config instanceof Map) {
//			this.marker = marker;
//			Map<String,Object> configMap = (Map<String,Object>) config;
//			Util.checkUnsupportedKeys(configMap, getSupportedKeys());
////			id = Util.getString(configMap, ID_KEY, UUID.randomUUID().toString());
////			icon = Util.getString(configMap, ICON_KEY, null);
////			text = Util.getString(configMap, TEXT_KEY, null);
////			tooltip = Util.getString(configMap, TOOLTIP_KEY, null);
////			color = Util.getString(configMap, COLOR_KEY, null);
////			colorMarker = Util.getMarker(configMap, COLOR_KEY);
////			outline = Util.getBoolean(configMap, OUTLINE_KEY, false);
////			description = Util.getString(configMap, DESCRIPTION_KEY, null);
////			notification = Util.getString(configMap, NOTIFICATION_KEY, null);
//		} else {
//			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
//		}
//	}		
//	
//	@Override
//	public HTMLPage create(Context context) throws Exception {
//		HTMLPage ret = createPage(context);
////		if (color != null) {
////			try {
////				ret.setColor(Color.valueOf(context.interpolateToString(color)));
////			} catch (IllegalArgumentException e) {
////				throw new ConfigurationException(e.getMessage(), e, colorMarker);
////			}
////		}
////		ret.setDescription(context.interpolateToString(description));
////		ret.setIcon(context.interpolateToString(icon));
////		ret.setId(context.interpolateToString(id));
////		ret.setNotification(context.interpolateToString(notification));
////		ret.setOutline(outline);
////		ret.setText(context.interpolateToString(text));
////		ret.setTooltip(context.interpolateToString(tooltip));
//		
//		return ret;
//	}
//	
//	protected HTMLPage createPage(Context context) {
//		return HTMLFactory.INSTANCE.page();
//	}

}
