package org.nasdanika.html.app.impl;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.DelegatingSupplierFactoryFeature;
import org.nasdanika.common.persistence.EnumSupplierFactoryAttribute;
import org.nasdanika.common.persistence.Feature;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ReferenceList;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;

public class ActionSupplierFactory extends LabelSupplierFactory<Action> {
	
	// Content - single value or a list of input streams.
	private SupplierFactoryFeature<?> content;
	private ListSupplierFactoryAttribute<Action> children;
	private StringSupplierFactoryAttribute confirmation;
	private StringSupplierFactoryAttribute disabled;
	private StringSupplierFactoryAttribute href;
	private StringSupplierFactoryAttribute script; 
	private CategorySupplierFactory category;
	private ListSupplierFactoryAttribute<String> roles;
	private EnumSupplierFactoryAttribute<SectionStyle> sectionStyle;
	private Feature<Integer> sectionColumns = new Attribute<Integer>("section-columns", false, false, 3, null);	
	
	public ActionSupplierFactory() {
		content = addFeature(new DelegatingSupplierFactoryFeature<Object>(new Attribute<Object>("content", false, false, null, null)));
		children = addFeature(new ListSupplierFactoryAttribute<Action>(new ReferenceList<>("children", false, false, null, null)));
		href = addFeature(new StringSupplierFactoryAttribute(new Attribute<String>("href", false, false, null, "script" ), true));
//		confirmation;
//		disabled;
//		script; 
//		category;
//		roles;
//		sectionStyle;
		 // = addFeature(new Attribute<String>("icon", false, false, null, null))
		// TODO Auto-generated constructor stub
//		 = "AUTO";
	}
//
//	@SuppressWarnings("unchecked")
//	public ActionSupplierFactory(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
//		super(loader, config, base, progressMonitor, marker);
//		Map<String,Object> configMap = (Map<String,Object>) config;
//		
//		if (configMap.containsKey(CONTENT_KEY)) {
//			content = loader.load(configMap.get(CONTENT_KEY), base, progressMonitor);
//			contentMarker = Util.getMarker(configMap, CONTENT_KEY);
//		}
//		
//		for (Object cfg: Util.getCollection(configMap, CHILDREN_KEY, Collections.emptyList())) {
//			children.add(loader.load(cfg, base, progressMonitor));
//		}
//		confirmation = Util.getString(configMap, CONFIRMATION_KEY, null);
//		if (configMap.containsKey(DISABLED_KEY)) {
//			disabled = org.nasdanika.common.Util.asSupplierFactory(loader.load(configMap.get(DISABLED_KEY), base, progressMonitor));
//		}
//		if (configMap.containsKey(HREF_KEY) && configMap.containsKey(SCRIPT_KEY)) {
//			throw new ConfigurationException("'href' and 'script' configuration keys are mutually exclusive", marker);
//		}
//		if (configMap.containsKey(HREF_KEY)) {
//			href = Util.getString(configMap, HREF_KEY, null);
//			hrefMarker = Util.getMarker(configMap, HREF_KEY);
//		}
//		if (configMap.containsKey(SCRIPT_KEY)) {
//			script = org.nasdanika.common.Util.asSupplierFactory(loader.load(configMap.get(SCRIPT_KEY), base, progressMonitor));
//		}
//		if (configMap.containsKey(CATEGORY_KEY)) {
//			category = (LabelFactory) loader.load(configMap.get(CATEGORY_KEY), base, progressMonitor);			
//		}
//		if (configMap.containsKey(ROLES_KEY)) {
//			Util.loadMultiString(configMap, ROLES_KEY, roles::add);
//		} else {
//			roles.add(Action.Role.NAVIGATION);
//		}
//		if (configMap.containsKey(SECTION_STYLE_KEY)) {
//			sectionStyle = Util.getString(configMap, SECTION_STYLE_KEY, null);
//			sectionStyleMarker = Util.getMarker(configMap, SECTION_STYLE_KEY);
//		}
//		sectionColumns = Util.getInt(configMap, SECTION_COLUMNS_KEY, 3);
//	}	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void setData(Context context, Map<Object, Object> data, LabelImpl label) {
		super.setData(context, data, label);
		ActionImpl action = (ActionImpl) label;
		
		if (children.isLoaded()) {
			Collection<Object> theChildren = (Collection<Object>) children.get(data);
			for (Object c : theChildren) {
				Collection<Action> children;
				if (c instanceof Action) {
					children = Collections.singleton((Action) c);
				} else if (c instanceof Collection) {
					children = (Collection<Action>) c;
				} else if (c instanceof Category) {
					children = ((Category) c).getActions();
//				} else {
//					children = Collections.singleton(((ContextualFactory<Action>) c).create(context));
				} else {
					throw new ConfigurationException("Unexpected child: "+c, this.children.getMarker());
				}
				
				action.getChildren().addAll(children);
				for (Action child : children) {
					((ActionImpl) child).setParent(action);
				}
			}
		}

//		content;
//		children;
//		href;
//		confirmation;
//		disabled;
//		script; 
//		category;
//		roles;
//		sectionStyle;
		 // = addFeature(new Attribute<String>("icon", false, false, null, null))
		// TODO Auto-generated constructor stub
//		 = "AUTO";
		
		
	}
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public DecoratedAction create(Context context) throws Exception {		
//		DecoratedAction ret = (DecoratedAction) super.create(context);
//		ret.setConfirmation(context.interpolateToString(confirmation));
//		NullProgressMonitor progressMonitor = new NullProgressMonitor();
//		if (disabled != null) {
//			String dCode = disabled.then(Util.TO_STRING).create(context).execute(progressMonitor);
//			ret.setDisabled(Boolean.TRUE.equals(Util.eval(dCode, Collections.singletonMap("view-generator", context))));
//		}
//		
//		if (script != null) {
//			String code = script.then(Util.TO_STRING).create(context).execute(progressMonitor);
//			ret.setActivator(new ScriptActionActivator() {
//				
//				@Override
//				public String getCode() {
//					return code;
//				}
//				
//			});
//		} else {
//			String iHref = href == null ? ret.getId() + ".html" : context.interpolateToString(href);
//			if (!"#".equals(iHref)) {
//				ret.setActivator(new NavigationActionActivator() {
//					
//					@Override
//					public String getUrl(String base) {					
//						if (Util.isBlank(base)) {
//							return iHref;
//						}
//						try {
//							return new URI(base).resolve(new URI(iHref)).toString();
//						} catch (URISyntaxException e) {
//							throw new ConfigurationException(e.getMessage(), e, hrefMarker);
//						}
//					}
//					
//				});
//			}			
//		}
//		
//		if (category != null) {
//			ret.setCategory(category.create(context));
//		}
//		
//		ret.getRoles().addAll(roles);
//		ret.setSectionColumns(sectionColumns);
//
//		try {
//			ret.setSectionStyle(SectionStyle.valueOf(context.interpolateToString(sectionStyle)));
//		} catch (IllegalArgumentException e) {
//			throw new ConfigurationException(e.getMessage(), e, sectionStyleMarker);
//		}
//				
//		
//		return ret;
//	}
//	
	
	@Override
	protected Action createLabel(Context context, Map<Object, Object> data) {
		ActionImpl ret = new ActionImpl(createDecorator(context)) {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				if (isEmpty()) {
					return super.generate(viewGenerator, progressMonitor);					
				}
				Object contentData = data.get(content.getKey());
				if (contentData instanceof ViewPart) {
					return ((ViewPart) content).generate(viewGenerator, progressMonitor);
				}
				if (contentData == null) {
					return super.generate(viewGenerator, progressMonitor);
				}
				
				try {
					SupplierFactory<InputStream> cf = org.nasdanika.common.Util.asInputStreamSupplierFactory(contentData);
					return cf.then(Util.TO_STRING).create(viewGenerator).execute(progressMonitor);
				} catch (Exception e) {
					throw new ConfigurationException(e.getMessage(), e, content.getMarker());
				}
			}
			
			@Override
			public boolean isEmpty() {
				return !content.isLoaded();
			}
			
		};
		return ret; 
	}

}
