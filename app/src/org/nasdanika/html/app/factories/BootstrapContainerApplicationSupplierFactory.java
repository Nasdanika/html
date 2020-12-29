package org.nasdanika.html.app.factories;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ContextualFactory;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.DelegatingSupplierFactoryFeature;
import org.nasdanika.common.persistence.EnumSupplierFactoryAttribute;
import org.nasdanika.common.persistence.FeatureObjectAttribute;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.app.impl.BootstrapContainerApplication.Section;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.bootstrap.factories.BootstrapPageSupplierFactory;
import org.nasdanika.html.factories.HTMLPageSupplierFactory;

public class BootstrapContainerApplicationSupplierFactory extends SupplierFactoryFeatureObject<BootstrapContainerApplication> {
	
	protected SupplierFactoryFeature<Consumer<Object>> header;	
	protected SupplierFactoryFeature<Consumer<Object>> navigationBar;	
	protected SupplierFactoryFeature<Consumer<Object>> navigationPanel;	
	protected SupplierFactoryFeature<Consumer<Object>> contentPanel;	
	protected SupplierFactoryFeature<Consumer<Object>> footer;	
	protected SupplierFactoryFeature<HTMLPage> page;
	private Attribute<Boolean> fluid = addFeature(new Attribute<Boolean>("fluid", false, false, false, null));
	private EnumSupplierFactoryAttribute<Theme> theme;
	
	// page - default page?
	// action?
	
	public BootstrapContainerApplicationSupplierFactory() {
		theme = addFeature(new EnumSupplierFactoryAttribute<Theme>(new StringSupplierFactoryAttribute(new Attribute<String>("theme", false, false, null, null, "page"), true), Theme.class, Theme.Default));
		page = addFeature(new DelegatingSupplierFactoryFeature<>(new FeatureObjectAttribute<>("page", BootstrapPageSupplierFactory::new, false, false, null, null, "theme")));
	}
	
	@Override
	protected Function<Map<Object, Object>, BootstrapContainerApplication> createResultFunction(Context context) {
		return new Function<Map<Object,Object>, BootstrapContainerApplication>() {
			
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
			public BootstrapContainerApplication execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				HTMLPage htmlPage;
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				if (page.isLoaded()) {
					htmlPage = (HTMLPage) page.get(data);
				} else {
					htmlPage = bootstrapFactory.bootstrapCdnHTMLPage((Theme) theme.get(data));
				}
				
				Map<Section, Consumer<Object>> sectionConfigurators = new HashMap<>();
				if (header.isLoaded()) {
					// TODO ((ViewBuilder) header.get(data)).build(app.he, viewGenerator, progressMonitor);
				}
				
				BootstrapContainerApplication app = new BootstrapContainerApplication(bootstrapFactory, htmlPage, (boolean) fluid.get(data), sectionConfigurators::get);
				
				// header
				// navigation bar
				// navigation panel
				// content panel
				// footer

				return app;
			}
		};
	}
	
	
//	protected Collection<String> getSupportedKeys() {
//		Collection<String> ret = new ArrayList<>();
//		ret.add(THEME_KEY);
//		ret.add(FLUID_KEY);
//		ret.add(PAGE_KEY);
//		return ret;
//	}
//	
//	private String theme;
//	private boolean fluid;
//	private HTMLPageFactory pageFactory;
//	private Marker themeMarker;
//	
//	private static final String THEME_KEY = "theme";
//	private static final String FLUID_KEY = "fluid";
//	private static final String PAGE_KEY = "page";
//	
//	@SuppressWarnings("unchecked")
//	public BootstrapContainerApplicationSupplierFactory(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
//		if (config instanceof Map) {
//			this.marker = marker;
//			Map<String,Object> configMap = (Map<String,Object>) config;
//			Util.checkUnsupportedKeys(configMap, getSupportedKeys());
//			
//			if (configMap.containsKey(PAGE_KEY)) {
//				pageFactory = (HTMLPageFactory) loader.load(configMap.get(PAGE_KEY), base, progressMonitor);
//				if (configMap.containsKey(THEME_KEY)) {
//					throw new ConfigurationException(PAGE_KEY + " and " + THEME_KEY + " are mutually exclusive", marker);					
//				}
//			}
//			
//			if (configMap.containsKey(FLUID_KEY)) {
//				fluid = Boolean.TRUE.equals(configMap.get(FLUID_KEY));					
//			}
//			
//			if (configMap.containsKey(THEME_KEY)) {
//				theme = Util.getString(configMap, THEME_KEY, null);
//				themeMarker = Util.getMarker(configMap, THEME_KEY);
//			}
//		} else {
//			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
//		}
//		
//		
//	}
//	
//	protected BootstrapContainerApplication createApplication(Context context) throws Exception {
//		if (pageFactory == null) {
//			Theme theTheme = Theme.Default;
//			if (theme != null) {
//				try {
//					theTheme = Theme.valueOf(context.interpolateToString(theme));
//				} catch (IllegalArgumentException e) {
//					throw new ConfigurationException(e.getMessage(), e, themeMarker);
//				}			
//			}
//			return new BootstrapContainerApplication(theTheme, fluid);
//		}
//		HTMLPage page = pageFactory.create(context);
//		return new BootstrapContainerApplication(BootstrapFactory.INSTANCE, page, fluid);
//	}
//
//	@Override
//	public BootstrapContainerApplication create(Context context) throws Exception {
//		BootstrapContainerApplication ret = createApplication(context);
//		// TODO - header, footer, ...
//		return ret;
//	}

}
