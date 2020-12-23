package org.nasdanika.html.app.impl;

import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.nasdanika.common.Context;
import org.nasdanika.common.ContextualFactory;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;

public class ActionFactory extends LabelFactory {
		
	private List<Object> children = new ArrayList<>();
	private Object content;
	private String confirmation;
	private SupplierFactory<InputStream> disabled;
	private String href;
	private SupplierFactory<InputStream> script; 
	private LabelFactory category;
	private Set<String> roles = new HashSet<>();
	private String sectionStyle = "AUTO";
	private int sectionColumns;

	private Marker sectionStyleMarker;
	private Marker contentMarker;
	private Marker hrefMarker;
	
	private static final String CONTENT_KEY = "content";
	private static final String CHILDREN_KEY = "children";
	private static final String CONFIRMATION_KEY = "confirmation";
	private static final String DISABLED_KEY = "disable";
	private static final String HREF_KEY = "href";
	private static final String SCRIPT_KEY = "script";
	private static final String CATEGORY_KEY = "category";
	private static final String ROLES_KEY = "roles";
	private static final String SECTION_STYLE_KEY = "section-style";
	private static final String SECTION_COLUMNS_KEY = "section-columns";
	
	protected Collection<String> getSupportedKeys() {
		Collection<String> ret = super.getSupportedKeys();
		ret.add(CONTENT_KEY);
		ret.add(CHILDREN_KEY);
		ret.add(CONFIRMATION_KEY);
		ret.add(DISABLED_KEY);
		ret.add(HREF_KEY);
		ret.add(SCRIPT_KEY);
		ret.add(CATEGORY_KEY);
		ret.add(ROLES_KEY);
		ret.add(SECTION_STYLE_KEY);
		ret.add(SECTION_COLUMNS_KEY);
		return ret;
	}

	@SuppressWarnings("unchecked")
	public ActionFactory(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		Map<String,Object> configMap = (Map<String,Object>) config;
		
		if (configMap.containsKey(CONTENT_KEY)) {
			content = loader.load(configMap.get(CONTENT_KEY), base, progressMonitor);
			contentMarker = Util.getMarker(configMap, CONTENT_KEY);
		}
		
		for (Object cfg: Util.getCollection(configMap, CHILDREN_KEY, Collections.emptyList())) {
			children.add(loader.load(cfg, base, progressMonitor));
		}
		confirmation = Util.getString(configMap, CONFIRMATION_KEY, null);
		if (configMap.containsKey(DISABLED_KEY)) {
			disabled = org.nasdanika.exec.Loader.asSupplierFactory(loader.load(configMap.get(DISABLED_KEY), base, progressMonitor));
		}
		if (configMap.containsKey(HREF_KEY) && configMap.containsKey(SCRIPT_KEY)) {
			throw new ConfigurationException("'href' and 'script' configuration keys are mutually exclusive", marker);
		}
		if (configMap.containsKey(HREF_KEY)) {
			href = Util.getString(configMap, HREF_KEY, null);
			hrefMarker = Util.getMarker(configMap, HREF_KEY);
		}
		if (configMap.containsKey(SCRIPT_KEY)) {
			script = org.nasdanika.exec.Loader.asSupplierFactory(loader.load(configMap.get(SCRIPT_KEY), base, progressMonitor));
		}
		if (configMap.containsKey(CATEGORY_KEY)) {
			category = (LabelFactory) loader.load(configMap.get(CATEGORY_KEY), base, progressMonitor);			
		}
		if (configMap.containsKey(ROLES_KEY)) {
			Util.loadMultiString(configMap, ROLES_KEY, roles::add);
		} else {
			roles.add(Action.Role.NAVIGATION);
		}
		if (configMap.containsKey(SECTION_STYLE_KEY)) {
			sectionStyle = Util.getString(configMap, SECTION_STYLE_KEY, null);
			sectionStyleMarker = Util.getMarker(configMap, SECTION_STYLE_KEY);
		}
		sectionColumns = Util.getInt(configMap, SECTION_COLUMNS_KEY, 3);
	}		
	
	@SuppressWarnings("unchecked")
	@Override
	public DecoratedAction create(Context context) throws Exception {		
		DecoratedAction ret = (DecoratedAction) super.create(context);
		ret.setConfirmation(context.interpolateToString(confirmation));
		NullProgressMonitor progressMonitor = new NullProgressMonitor();
		if (disabled != null) {
			String dCode = disabled.then(Util.TO_STRING).create(context).execute(progressMonitor);
			ret.setDisabled(Boolean.TRUE.equals(Util.eval(dCode, Collections.singletonMap("view-generator", context))));
		}
		
		if (script != null) {
			String code = script.then(Util.TO_STRING).create(context).execute(progressMonitor);
			ret.setActivator(new ScriptActionActivator() {
				
				@Override
				public String getCode() {
					return code;
				}
				
			});
		} else {
			String iHref = href == null ? ret.getId() + ".html" : context.interpolateToString(href);
			if (!"#".equals(iHref)) {
				ret.setActivator(new NavigationActionActivator() {
					
					@Override
					public String getUrl(String base) {					
						if (Util.isBlank(base)) {
							return iHref;
						}
						try {
							return new URI(base).resolve(new URI(iHref)).toString();
						} catch (URISyntaxException e) {
							throw new ConfigurationException(e.getMessage(), e, hrefMarker);
						}
					}
					
				});
			}			
		}
		
		if (category != null) {
			ret.setCategory(category.create(context));
		}
		
		ret.getRoles().addAll(roles);
		ret.setSectionColumns(sectionColumns);

		try {
			ret.setSectionStyle(SectionStyle.valueOf(context.interpolateToString(sectionStyle)));
		} catch (IllegalArgumentException e) {
			throw new ConfigurationException(e.getMessage(), e, sectionStyleMarker);
		}
				
		for (Object c: children) {
			Collection<Action> children;
			if (c instanceof Action) {
				children = Collections.singleton((Action) c);
			} else if (c instanceof Collection) {
				children = (Collection<Action>) c;
			} else if (c instanceof CategoryReference) {
				children = ((CategoryReference) c).create(context);				
			} else {
				children = Collections.singleton(((ContextualFactory<Action>) c).create(context));
			}
			ret.getChildren().addAll(children);
			for (Action child: children) {
				((ActionImpl) child).setParent(ret);
			}
		}
		
		return ret;
	}
	
	/**
	 * Override to return ActionImpl
	 * @return
	 */
	@Override
	protected DecoratedAction createLabel(Context context) {
		DecoratedAction decoratedAction = new DecoratedAction(createDecorator(context)) {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				if (content instanceof ViewPart) {
					return ((ViewPart) content).generate(viewGenerator, progressMonitor);
				}
				if (content == null) {
					return super.generate(viewGenerator, progressMonitor);
				}
				
				try {
					SupplierFactory<InputStream> cf = org.nasdanika.exec.Loader.asSupplierFactory(content);
					return cf.then(Util.TO_STRING).create(viewGenerator).execute(progressMonitor);
				} catch (Exception e) {
					throw new ConfigurationException(e.getMessage(), e, contentMarker);
				}
			}
			
			@Override
			public boolean isEmpty() {
				return content == null;
			}
			
		};
		return decoratedAction; 
	}

}
