package org.nasdanika.html.app.factories;

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
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.impl.HTMLPageFactory;

public class BootstrapContainerApplicationFactory implements /* TODO - Supplier factory because parts are suppliers, need to then() them */ ContextualFactory<BootstrapContainerApplication>, Marked {
	
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	protected Collection<String> getSupportedKeys() {
		Collection<String> ret = new ArrayList<>();
		ret.add(THEME_KEY);
		ret.add(FLUID_KEY);
		ret.add(PAGE_KEY);
		return ret;
	}
	
	private String theme;
	private boolean fluid;
	private HTMLPageFactory pageFactory;
	private Marker themeMarker;
	
	private static final String THEME_KEY = "theme";
	private static final String FLUID_KEY = "fluid";
	private static final String PAGE_KEY = "page";
	
	@SuppressWarnings("unchecked")
	public BootstrapContainerApplicationFactory(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			Util.checkUnsupportedKeys(configMap, getSupportedKeys());
			
			if (configMap.containsKey(PAGE_KEY)) {
				pageFactory = (HTMLPageFactory) loader.load(configMap.get(PAGE_KEY), base, progressMonitor);
				if (configMap.containsKey(THEME_KEY)) {
					throw new ConfigurationException(PAGE_KEY + " and " + THEME_KEY + " are mutually exclusive", marker);					
				}
			}
			
			if (configMap.containsKey(FLUID_KEY)) {
				fluid = Boolean.TRUE.equals(configMap.get(FLUID_KEY));					
			}
			
			if (configMap.containsKey(THEME_KEY)) {
				theme = Util.getString(configMap, THEME_KEY, null);
				themeMarker = Util.getMarker(configMap, THEME_KEY);
			}
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
		
		
	}
	
	protected BootstrapContainerApplication createApplication(Context context) throws Exception {
		if (pageFactory == null) {
			Theme theTheme = Theme.Default;
			if (theme != null) {
				try {
					theTheme = Theme.valueOf(context.interpolateToString(theme));
				} catch (IllegalArgumentException e) {
					throw new ConfigurationException(e.getMessage(), e, themeMarker);
				}			
			}
			return new BootstrapContainerApplication(theTheme, fluid);
		}
		HTMLPage page = pageFactory.create(context);
		return new BootstrapContainerApplication(BootstrapFactory.INSTANCE, page, fluid);
	}

	@Override
	public BootstrapContainerApplication create(Context context) throws Exception {
		BootstrapContainerApplication ret = createApplication(context);
		// TODO - header, footer, ...
		return ret;
	}

}
