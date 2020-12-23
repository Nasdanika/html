package org.nasdanika.html.bootstrap.impl;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.impl.HTMLPageFactory;

public class BootstrapPageFactory extends HTMLPageFactory {
	
	private String theme;
	private boolean cdn = true;
	private Marker themeMarker;
	
	private static final String THEME_KEY = "theme";
	private static final String CDN_KEY = "cdn";
	
	@Override
	protected Collection<String> getSupportedKeys() {
		Collection<String> ret = super.getSupportedKeys();
		ret.add(THEME_KEY);
		ret.add(CDN_KEY);
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public BootstrapPageFactory(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		Map<String,Object> configMap = (Map<String,Object>) config;
		
		if (configMap.containsKey(CDN_KEY)) {
			cdn = Boolean.TRUE.equals(configMap.get(CDN_KEY));					
		}
		
		if (configMap.containsKey(THEME_KEY)) {
			theme = Util.getString(configMap, THEME_KEY, null);
			themeMarker = Util.getMarker(configMap, THEME_KEY);
			if (!cdn) {
				throw new ConfigurationException("When 'theme' is specified 'cdn' must not be false", marker);
			}
		}
		
	}
	
	@Override
	protected HTMLPage createPage(Context context) {
		HTMLPage page = super.createPage(context);
		
		BootstrapFactory bootstrapFactory = BootstrapFactory.INSTANCE;
		if (theme != null) {
			try {
				return bootstrapFactory.bootstrapCdnHTMLPage(page, Theme.valueOf(context.interpolateToString(theme)));
			} catch (IllegalArgumentException e) {
				throw new ConfigurationException(e.getMessage(), e, themeMarker);
			}			
		}
		
		if (cdn) {
			return bootstrapFactory.bootstrapCdnHTMLPage(page);
		}
		
		return bootstrapFactory.bootstrapHTMLPage(page);
	}

}
