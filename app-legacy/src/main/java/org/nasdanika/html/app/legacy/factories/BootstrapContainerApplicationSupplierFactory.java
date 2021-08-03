package org.nasdanika.html.app.factories;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.DelegatingSupplierFactoryFeature;
import org.nasdanika.common.persistence.EnumSupplierFactoryAttribute;
import org.nasdanika.common.persistence.FeatureObjectAttribute;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ReferenceList;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.app.impl.BootstrapContainerApplication.Section;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.bootstrap.factories.AppearanceSupplierFactory;
import org.nasdanika.html.bootstrap.factories.BootstrapPageSupplierFactory;

public class BootstrapContainerApplicationSupplierFactory extends SupplierFactoryFeatureObject<BootstrapContainerApplication> {
	
	protected SupplierFactoryFeature<Consumer<Object>> header;	
	protected SupplierFactoryFeature<Consumer<Object>> navigationBar;	
	protected SupplierFactoryFeature<Consumer<Object>> contentRow;	
	protected SupplierFactoryFeature<Consumer<Object>> navigationPanel;	
	protected SupplierFactoryFeature<Consumer<Object>> contentPanel;	
	protected SupplierFactoryFeature<Consumer<Object>> footer;	
	protected SupplierFactoryFeature<HTMLPage> page;
	private Attribute<Boolean> fluid = addFeature(new Attribute<Boolean>("fluid", false, false, false, null));
	private EnumSupplierFactoryAttribute<Theme> theme;
	protected SupplierFactoryFeature<Consumer<Object>> appearance;
	private SupplierFactoryFeature<List<Object>> content;
	
	// action? Adapting to consumer of BinaryEntity container for generation of application pages.
	
	public BootstrapContainerApplicationSupplierFactory() {
		theme = addFeature(new EnumSupplierFactoryAttribute<Theme>(new StringSupplierFactoryAttribute(new Attribute<String>("theme", false, false, null, null, "page"), true), Theme.class, Theme.Default));
		page = addFeature(new DelegatingSupplierFactoryFeature<>(new FeatureObjectAttribute<>("page", BootstrapPageSupplierFactory::new, false, false, null, null, "theme")));
		appearance = addFeature(new DelegatingSupplierFactoryFeature<Consumer<Object>>(new FeatureObjectAttribute<AppearanceSupplierFactory>("appearance", AppearanceSupplierFactory::new, false, false, null, "Appearance"))); 
		content = addFeature(new ListSupplierFactoryAttribute<>(new ReferenceList<>("content", false, false, null, null), true));
		
		header = addFeature(new DelegatingSupplierFactoryFeature<Consumer<Object>>(new FeatureObjectAttribute<BootstrapContainerApplicationSectionSupplierFactory>("header", BootstrapContainerApplicationSectionSupplierFactory::new, false, false, null, "Header"))); 
		navigationBar = addFeature(new DelegatingSupplierFactoryFeature<Consumer<Object>>(new FeatureObjectAttribute<BootstrapContainerApplicationSectionSupplierFactory>("navigation-bar", BootstrapContainerApplicationSectionSupplierFactory::new, false, false, null, "Navigation Bar"))); 
		contentRow = addFeature(new DelegatingSupplierFactoryFeature<Consumer<Object>>(new FeatureObjectAttribute<BootstrapContainerApplicationSectionSupplierFactory>("content-row", BootstrapContainerApplicationSectionSupplierFactory::new, false, false, null, "Content Row"))); 
		navigationPanel = addFeature(new DelegatingSupplierFactoryFeature<Consumer<Object>>(new FeatureObjectAttribute<BootstrapContainerApplicationPanelSupplierFactory>("navigation-panel", BootstrapContainerApplicationPanelSupplierFactory::new, false, false, null, "Navigation Panel"))); 
		contentPanel = addFeature(new DelegatingSupplierFactoryFeature<Consumer<Object>>(new FeatureObjectAttribute<BootstrapContainerApplicationPanelSupplierFactory>("content-panel", BootstrapContainerApplicationPanelSupplierFactory::new, false, false, null, "Content Panel"))); 
		footer = addFeature(new DelegatingSupplierFactoryFeature<Consumer<Object>>(new FeatureObjectAttribute<BootstrapContainerApplicationSectionSupplierFactory>("footer", BootstrapContainerApplicationSectionSupplierFactory::new, false, false, null, "Footer"))); 		
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

				sectionConfigurators.put(Section.Container, container -> {
					if (appearance.isLoaded()) {
						((Consumer<Object>) appearance.get(data)).accept(container);
					} else {
						// Default border
						((org.nasdanika.html.bootstrap.Container) container).border(Color.DEFAULT).margin().top(Breakpoint.DEFAULT, Size.S1);	
					}
					if (content.isLoaded()) {	
						Object target = container;
						if (container instanceof BootstrapElement) { 
							target = ((BootstrapElement<?, ?>) container).toHTMLElement();
						} 
						
						if (!(target instanceof Consumer)) {
							throw new ConfigurationException("Cannot add content to " + target, getMarker());						
						} 	
						
						Consumer<Object> consumer = (Consumer<Object>) target;
						
						for (Object ce: (List<Object>) data.get(content.getKey())) {
							if (ce instanceof InputStream) {
								try {
									consumer.accept(DefaultConverter.INSTANCE.toString((InputStream) ce));
								} catch (IOException e) {
									throw new ConfigurationException(e.getMessage(), e, content.getMarker());
								}
							} else {
								consumer.accept(ce);
							}
						}
					}					
										
				});
				
				if (header.isLoaded()) {
					sectionConfigurators.put(Section.Header, container -> ((Consumer<Object>) header.get(data)).accept(container));
				}
				
				if (navigationBar.isLoaded()) {
					sectionConfigurators.put(Section.NavigationBar, container -> ((Consumer<Object>) navigationBar.get(data)).accept(container));
				}
				
				if (contentRow.isLoaded()) {
					sectionConfigurators.put(Section.ContentRow, container -> ((Consumer<Object>) contentRow.get(data)).accept(container));
				}
				
				if (navigationPanel.isLoaded()) {
					sectionConfigurators.put(Section.NavigationPanel, container -> ((Consumer<Object>) navigationPanel.get(data)).accept(container));
				}
				
				if (contentPanel.isLoaded()) {
					sectionConfigurators.put(Section.ContentPanel, container -> ((Consumer<Object>) contentPanel.get(data)).accept(container));
				}
				
				if (footer.isLoaded()) {
					sectionConfigurators.put(Section.Footer, container -> ((Consumer<Object>) footer.get(data)).accept(container));
				}
				
				BootstrapContainerApplication app = new BootstrapContainerApplication(bootstrapFactory, htmlPage, (boolean) fluid.get(data), sectionConfigurators::get);

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
