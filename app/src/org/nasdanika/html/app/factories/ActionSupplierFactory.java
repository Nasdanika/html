package org.nasdanika.html.app.factories;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Feature;
import org.nasdanika.common.persistence.FunctionSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ListAttribute;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ReferenceList;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.app.impl.Category;
import org.nasdanika.html.app.impl.LabelImpl;
import org.nasdanika.html.app.impl.PathNavigationActionActivator;

public class ActionSupplierFactory extends LabelSupplierFactory<Action> {
	
	// Content - single value or a list of input streams.
	private SupplierFactoryFeature<List<Object>> content;
	private ListSupplierFactoryAttribute<Action,?> children;
	private StringSupplierFactoryAttribute confirmation;
	private StringSupplierFactoryAttribute disabled;
	private Attribute<Boolean> inline = addFeature(new Attribute<Boolean>("inline", false, false, false, null));
	private StringSupplierFactoryAttribute href;
	private StringSupplierFactoryAttribute script; 
	private ListSupplierFactoryAttribute<String,?> roles;
	private SupplierFactoryFeature<SectionStyle> sectionStyle;
	private Feature<Integer> sectionColumns = addFeature(new Attribute<Integer>("section-columns", false, false, 3, null));	
	
	public ActionSupplierFactory() {
		content = addFeature(new ListSupplierFactoryAttribute<>(new ReferenceList<>("content", false, false, null, null), true));
		children = addFeature(new ListSupplierFactoryAttribute<>(new ReferenceList<>("children", false, false, null, null), false));
		href = addFeature(new StringSupplierFactoryAttribute(new Attribute<String>("href", false, false, null, null, "script"), true));
		confirmation = addFeature(new StringSupplierFactoryAttribute(new Attribute<String>("confirmation", false, false, null, null), true));
		script = addFeature(new StringSupplierFactoryAttribute(new Attribute<String>("script", false, false, null, null, "href"), true));
		disabled = addFeature(new StringSupplierFactoryAttribute(new Attribute<String>("disabled", false, false, null, "JavaScript expression which is evaluated with context binding"), true));
		roles = addFeature(new ListSupplierFactoryAttribute<>(new ListAttribute<String>("role", false, false, Collections.singletonList(Action.Role.NAVIGATION), null), true));
				
		FunctionFactory<String, SectionStyle> sectionStyleFactory = context -> Function.fromFunction(str -> str == null ? null : SectionStyle.valueOf(str.toUpperCase().replace("-", "_")), "Section style from lower kebab case", 1);
		sectionStyle = addFeature(new FunctionSupplierFactoryAttribute<String,SectionStyle>(new StringSupplierFactoryAttribute(new Attribute<String>("section-style", false, false, null, null), true), sectionStyleFactory));		
	}
	
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
					children = new ArrayList<>();
					Collection<Action> cc = (Collection<Action>) c;
					for (Object e: cc) {
						Action a = Adaptable.adaptTo(e, Action.class);
						if (a == null) {
							throw new ConfigurationException("Unexpected action child: "+e, Util.getMarker(cc, children.size()));
						} else {
							children.add(a);
						}
					}
				} else if (c instanceof Category) {
					children = ((Category) c).getActions();
				} else {
					Action adapter = Adaptable.adaptTo(c, Action.class);
					if (adapter == null) {
						throw new ConfigurationException("Unexpected action child: "+c, this.children.getMarker());
					}
					children = Collections.singleton((Action) c);					
				}
				
				action.getChildren().addAll(children);
				for (Action child : children) {
					((ActionImpl) child).setParent(action);
				}
			}
		}
		boolean isInline = (boolean) inline.get(data);
		if (script.isLoaded()) {
			action.setActivator(new ScriptActionActivator() {
				
				@Override
				public String getCode() {
					return (String) script.get(data);
				}
				
				@Override
				public boolean inline() {
					return isInline;
				}
				
			});
		} else if (href.isLoaded()) {
			action.setActivator(new PathNavigationActionActivator(action, context.getString(Context.BASE_URI_PROPERTY), (String) href.get(data), href.getMarker()) {
				
				@Override
				public boolean inline() {
					return isInline;
				}
				
			});
		} else if (content.isLoaded()) {
			// Action with content and not activator - using NavigationActionActivator with <id>.html href
			action.setActivator(new PathNavigationActionActivator(action, context.getString(Context.BASE_URI_PROPERTY), action.getId() + ".html", getMarker()) {
				
				@Override
				public boolean inline() {
					return isInline;
				}
				
			});
		}
		
		if (disabled.isLoaded()) {
			String dCode = (String) disabled.get(data);
			try {
				action.setDisabled(Boolean.TRUE.equals(Util.eval(dCode, Collections.singletonMap("context", context))));
			} catch (Exception e) {
				throw new ConfigurationException(e.getMessage(),  e, disabled.getMarker());
			}
		}
		
		action.getRoles().addAll((Collection<String>) roles.get(data));
		
		if (confirmation.isLoaded()) {
			action.setConfirmation((String) confirmation.get(data));
		}
		
		if (sectionStyle.isLoaded()) {
			action.setSectionStyle((SectionStyle) sectionStyle.get(data));
		}
		
		if (sectionColumns.isLoaded()) {
			action.setSectionColumns((int) sectionColumns.get(data));
		}
		
	}
	
	@Override
	protected Action createLabel(Context context, Map<Object, Object> data) throws Exception {
		@SuppressWarnings("unchecked")
		Object[] contentData = ((List<Object>) content.get(data)).toArray();
		for (int i = 0; i < contentData.length; ++i) {
			if (contentData[i] instanceof InputStream) {
				try {
					contentData[i] = DefaultConverter.INSTANCE.toString((InputStream) contentData[i]);
				} catch (IOException e) {
					throw new ConfigurationException(e.getMessage(), e, content.getMarker());
				}
			}
		}
		
		
		ActionImpl ret = new ActionImpl((Decorator) appearance.get(data)) {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				if (isEmpty()) {
					return super.generate(viewGenerator, progressMonitor);					
				}
				ViewGenerator actionViewGenerator = viewGenerator.fork();
				actionViewGenerator.register(Action.class, this);
				if (contentData.length == 1 && contentData[0] instanceof ViewPart) {
					return ((ViewPart) contentData[0]).generate(actionViewGenerator, progressMonitor);
				}
			
				HTMLFactory htmlFactory = actionViewGenerator.get(HTMLFactory.class, HTMLFactory.INSTANCE);
				Fragment fragment = htmlFactory.fragment();
				for (Object ce: contentData) {
					if (ce instanceof ViewPart) {
						fragment.content(((ViewPart) ce).generate(actionViewGenerator, progressMonitor));
					} else {
						fragment.content(ce);
					}
				}
				return fragment;
			}
			
			@Override
			public boolean isEmpty() {
				return !content.isLoaded();
			}
			
		};
		return ret; 
	}

}
