package org.nasdanika.html.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;
import org.nasdanika.html.Angular;
import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.Bootstrap.Grid;
import org.nasdanika.html.FactoryProducer;
import org.nasdanika.html.FontAwesome;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.JsTree;
import org.nasdanika.html.Knockout;
import org.nasdanika.html.KnockoutBindingsSource;
import org.nasdanika.html.Producer;
import org.nasdanika.html.ProducerException;
import org.nasdanika.html.Style;
import org.nasdanika.html.Tag;
import org.nasdanika.html.UIElement;

/**
 * Base class for UI elements
 * @author Pavel
 *
 * @param <T>
 */
public abstract class UIElementImpl<T extends UIElement<T>> implements UIElement<T>, AutoCloseable {


	private static final String STYLE = "style";

	private static final String DATA_BIND = "data-bind";
	
	private static final String CLASS = "class";
	
	private static final String ID = "id";
	
	public Object getId() {
		return attributes.get(ID);
	}
	
	protected Map<String, Object> attributes = new LinkedHashMap<>();
	
	private Map<String, Object> styles = new LinkedHashMap<>();
	
	private Map<String, Object> koDataBindEntries = new LinkedHashMap<>();
	
	private Map<String, Object> koInitialValueEntries = new HashMap<>();

	protected HTMLFactory factory;
	
	public HTMLFactory getFactory() {
		return factory;
	}
	
	public UIElementImpl(HTMLFactory factory, Tag.TagName tagName) {
		this(factory, tagName.name());
	}
	
	public UIElementImpl(HTMLFactory factory, String tagName) {
		this.factory = factory;
		this.tagName = tagName;
	}
	
	@Override
	public String getAttribute(String name) {
		Object av = attributes.get(name);
		return av==null ? null : av.toString();
	}
	
	/**
	 * Renders attributes
	 * @param excluded Attributes to exclude (already rendered by subclass).
	 */
	protected String attributes(String... excluded) {
		StringBuilder attributeBuilder = new StringBuilder();
		
		for (Entry<String, Object> a: attributes.entrySet()) {
			if (!DATA_BIND.equals(a.getKey()) 
					&& !STYLE.equals(a.getKey()) 
					&& !CLASS.equals(a.getKey()) 
					&& !Arrays.asList(excluded).contains(a.getKey())) {
				if (attributeBuilder.length()>0) {
					attributeBuilder.append(" ");
				}				
				Object value = a.getValue();
				if (Boolean.TRUE.equals(value)) {
					// boolean attributes
					attributeBuilder.append(a.getKey());					
				} else {
					attributeBuilder.append(a.getKey()+"=\""+StringEscapeUtils.escapeHtml4(stringify(value, 0))+"\"");
				}
			}
		}
		
		if (!Arrays.asList(excluded).contains(STYLE)) {
			StringBuilder styleBuilder = styles();
			if (styleBuilder.length()>0) {
				if (attributeBuilder.length()>0) {
					attributeBuilder.append(" ");
				}
				attributeBuilder.append(STYLE+"=\""+styleBuilder+"\"");				
			}
		}
		
		if (!Arrays.asList(excluded).contains(DATA_BIND)) {
			StringBuilder dataBindBuilder = dataBinds();
			if (dataBindBuilder.length()>0) {
				if (attributeBuilder.length()>0) {
					attributeBuilder.append(" ");
				}
				attributeBuilder.append(DATA_BIND+"=\""+dataBindBuilder+"\"");				
			}
		}
		
		if (!Arrays.asList(excluded).contains(CLASS)) {
			StringBuilder classBuilder = classes();
			if (classBuilder.length()>0) {
				if (attributeBuilder.length()>0) {
					attributeBuilder.append(" ");
				}
				attributeBuilder.append(CLASS+"=\""+classBuilder+"\"");				
			}
		}
		
		return attributeBuilder.length()==0 ? "" : " "+attributeBuilder.toString();
	}

	protected StringBuilder classes() {
		StringBuilder classBuilder = new StringBuilder();
		for (Object cls: classes) {
			if (classBuilder.length()>0) {
				classBuilder.append(" ");
			}
			classBuilder.append(stringify(cls, 0));
		}
		if (attributes.containsKey(CLASS)) {
			if (classBuilder.length()>0) {
				classBuilder.append(" ");
			}
			classBuilder.append(stringify(attributes.get(CLASS), 0));
		}
		return classBuilder;
	}

	protected StringBuilder dataBinds() {
		StringBuilder dataBindBuilder = new StringBuilder();
		if (attributes.containsKey(DATA_BIND)) {
			dataBindBuilder.append(stringify(attributes.get(DATA_BIND), 0));
		}
		for (Entry<String, Object> se: koDataBindEntries.entrySet()) {
			if (dataBindBuilder.length()>0 && !dataBindBuilder.toString().trim().endsWith(",")) {
				dataBindBuilder.append(",");
			}
			dataBindBuilder.append(se.getKey()+":"+stringify(se.getValue(), 0));
		}
		return dataBindBuilder;
	}

	protected StringBuilder styles() {
		StringBuilder styleBuilder = new StringBuilder();
		if (attributes.containsKey(STYLE)) {
			styleBuilder.append(stringify(attributes.get(STYLE), 0));
		}
		for (Entry<String, Object> se: styles.entrySet()) {
			if (styleBuilder.length()>0 && !styleBuilder.toString().trim().endsWith(";")) {
				styleBuilder.append(";");
			}
			styleBuilder.append(se.getKey()+":"+stringify(se.getValue(), 0));
		}
		return styleBuilder;
	}
	
	/**
	 * Merges value of given attribute, prepended by space, to the attribute definition being
	 * rendered by a subclass
	 * @param attribute
	 */
	protected String merge(String attribute) {
		if (STYLE.equals(attribute)) {
			StringBuilder styleBuilder = new StringBuilder();
			if (attributes.containsKey(STYLE)) {
				styleBuilder.append(stringify(attributes.get(STYLE), 0));
			}
			for (Entry<String, Object> se: styles.entrySet()) {
				if (styleBuilder.length()>0 && !styleBuilder.toString().endsWith(";")) {
					styleBuilder.append(";");
				}
				styleBuilder.append(se.getKey()+":"+stringify(se.getValue(), 0));
			}
			return styleBuilder.toString();
		}
		
		if (attributes.containsKey(attribute)) {
			return StringEscapeUtils.escapeHtml4(String.valueOf(attributes.get(attribute)));
		}
		
		return "";
	}
	
	/**
	 * Merges attributes from the source element into this element.
	 * @param source
	 */
	protected void merge(UIElementImpl<?> source) {
		this.attributes.putAll(source.attributes);
		this.classes.addAll(source.classes);
		this.styles.putAll(source.styles);
	}
	
	@Override
	public T id(Object id) {
		return attribute(ID, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T attribute(String name, Object value) {
		if (value==null) {
			attributes.remove(name);
		} else {
			attributes.put(name, value);
		}
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T style(String name, Object value) {
		if (value==null) {
			styles.remove(name);
		} else {
			styles.put(name, value);
		}
		return (T) this;
	}
	
	private List<Object> classes = new ArrayList<>();

	private Object remoteContent;

	private String comment;
	
	protected StringBuilder renderComment(int indent) {
		if (comment==null || comment.trim().length()==0) {
			return new StringBuilder();
		}
		return indent(indent).append("<!-- "+comment+" -->");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T addClass(Object... clazz) {
		for (Object clz: clazz) {
			if (!classes.contains(clz)) {
				classes.add(clz);
			}
		}
		return (T) this;
	}
	
	protected void removeClass(Object... clazz) {
		for (Object clz: clazz) {
			classes.remove(clz);
		}
	}
	
	/**
	 * Helper method
	 * @param o
	 * @throws Exception
	 */
	protected static void close(Object o) throws Exception {
		if (o instanceof AutoCloseable) {
			((AutoCloseable) o).close();
		} else if (o!=null && o.getClass().isArray()) {
			for (Object e: (Object[]) o) {
				close(e);
			}
		} else if (o instanceof Iterable) {
			for (Object e: (Iterable<?>) o) {
				close(e);
			}
		}
	}
	
	@Override
	public void close() throws Exception {
		for (Object attr: attributes.values()) {
			close(attr);
		}		
		for (Object cls: classes) {
			close(cls);
		}		
		for (Object style: styles.values()) {
			close(style);
		}	
		close(remoteContent);
		
		for (Object c: getContent()) {
			close(c);
		}
	}

	@Override
	public T on(Event event, Object handler) {		
		return on(event.name(), handler);
	}

	@Override
	public T on(String event, Object handler) {		
		return attribute("on"+event, handler);
	}

	@Override
	public T on(Event event, Reader handler) throws IOException {
		return on(event.name(), handler);
	}

	@Override
	public T on(String event, Reader handler) throws IOException {
		StringWriter sw = new StringWriter();
		char[] cbuf = new char[1024];
		int l;
		while ((l=handler.read(cbuf))!=-1) {
			sw.write(cbuf, 0, l);
		}
		sw.close();
		handler.close();
		return on(event, sw.toString());
	}

	@Override
	public T on(Event event, InputStream handler) throws IOException {
		return on(event.name(), handler);
	}

	@Override
	public T on(String event, InputStream handler) throws IOException {
		return on(event, new InputStreamReader(handler));
	}		

	@SuppressWarnings("unchecked")
	@Override
	public T remoteContent(Object href) {
		if (getId()==null) {
			id(factory.nextId());
		}
		this.remoteContent = href;
		return (T) this;
	}
	
	protected String genLoadRemoteContentScript() {
		if (remoteContent==null) {
			return "";
		}
		
		return factory.tag("script", "nsdLoad(\"#"+getId()+"\", \""+remoteContent+"\");").toString();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public FontAwesome<T> fontAwesome() {
		return new FontAwesomeImpl<T>((T) this);
	}
	
	@Override
	public Knockout<T> knockout() {
		
		return new Knockout<T>() {

			@Override
			public T visible(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.VISIBLE.name, expression, initialValue);
			}
			
			@Override
			public T visible(Object expression) {
				return visible(expression, null);
			}

			@Override
			public T text(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.TEXT.name, expression, initialValue);
			}

			@Override
			public T text(Object expression) {
				return text(expression, null);
			}

			@Override
			public T html(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.HTML.name, expression, initialValue);
			}
			
			@Override
			public T html(Object expression) {
				return html(expression, null);
			}

			@Override
			public T css(Object expression) {
				return bind(KnockoutBindingInfo.CSS.name, expression, null);
			}

			@Override
			public T style(Object expression) {
				return bind(KnockoutBindingInfo.STYLE.name, expression, null);
			}

			@Override
			public T attr(Object expression) {
				return bind(KnockoutBindingInfo.ATTR.name, expression, null);
			}

			@Override
			public T foreach(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.FOREACH.name, expression, initialValue);
			}
			
			@Override
			public T foreach(Object expression) {
				return foreach(expression, null);
			}

			@Override
			public T if_(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.IF.name, expression, initialValue);
			}
			
			@Override
			public T if_(Object expression) {
				return if_(expression, null);
			}

			@Override
			public T ifnot(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.IFNOT.name, expression, initialValue);
			}
			
			@Override
			public T ifnot(Object expression) {
				return ifnot(expression, null);
			}

			@Override
			public T with(Object expression) {
				return bind(KnockoutBindingInfo.WITH.name, expression, null);
			}

			@Override
			public T component(Object expression) {
				return bind(KnockoutBindingInfo.COMPONENT.name, expression, null);
			}

			@Override
			public T click(Object expression) {
				return bind(KnockoutBindingInfo.CLICK.name, expression, null);
			}

			@Override
			public T event(Object expression) {
				return bind(KnockoutBindingInfo.EVENT.name, expression, null);
			}

			@Override
			public T submit(Object expression) {
				return bind(KnockoutBindingInfo.SUBMIT.name, expression, null);
			}

			@Override
			public T enable(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.ENABLE.name, expression, initialValue);
			}
			
			@Override
			public T enable(Object expression) {
				return enable(expression, null);
			}

			@Override
			public T disable(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.DISABLE.name, expression, initialValue);
			}
			
			@Override
			public T disable(Object expression) {
				return disable(expression, null);
			}

			@Override
			public T value(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.VALUE.name, expression, initialValue);
			}
			
			@Override
			public T value(Object expression) {
				return value(expression, null);
			}

			@Override
			public T textInput(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.TEXT_INPUT.name, expression, initialValue);
			}
			
			@Override
			public T textInput(Object expression) {
				return textInput(expression, null);
			}

			@Override
			public T hasFocus(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.HAS_FOCUS.name, expression, initialValue);
			}
			
			@Override
			public T hasFocus(Object expression) {
				return hasFocus(expression, null);
			}

			@Override
			public T checked(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.CHECKED.name, expression, initialValue);
			}
			
			@Override
			public T checked(Object expression) {
				return checked(expression, null);
			}

			@Override
			public T options(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.OPTIONS.name, expression, initialValue);
			}
			
			@Override
			public T options(Object expression) {
				return options(expression, null);
			}

			@Override
			public T selectedOptions(Object expression, Object initialValue) {
				return bind(KnockoutBindingInfo.SELECTED_OPTIONS.name, expression, initialValue);
			}
			
			@Override
			public T selectedOptions(Object expression) {
				return selectedOptions(expression, null);
			}

			@Override
			public T uniqueName(Object expression) {
				return bind(KnockoutBindingInfo.UNIQUE_NAME.name, expression, null);
			}

			@Override
			public T template(Object expression) {
				return bind(KnockoutBindingInfo.TEMPLATE.name, expression, null);
			}

			@SuppressWarnings("unchecked")
			@Override
			public T bind(String binding, Object expression, Object initialValue) {
				if (expression==null) {
					koDataBindEntries.remove(binding);
					koInitialValueEntries.remove(binding);
				} else {
					koDataBindEntries.put(binding, expression);
					if (initialValue!=null) {
						koInitialValueEntries.put(binding, initialValue);
					}
				}
				return (T) UIElementImpl.this;
			}
			
			@Override
			public Collection<Binding> getAllBindings() {
				Map<String, Binding> collector = new LinkedHashMap<>();
				boolean isNewScope = false;
				for (String binding: koDataBindEntries.keySet()) {
					if (KnockoutBindingInfo.isObservable(binding)) {
						String fieldName = String.valueOf(koDataBindEntries.get(binding)).trim();
						if (KnockoutVirtualElementImpl.isJavaIdentifierPath(fieldName)) {
							collector.put(fieldName, new KnockoutBindingImpl(fieldName, KnockoutBindingInfo.isArray(binding), koInitialValueEntries.get(binding)));
						}
					}
					if (KnockoutBindingInfo.isNewScope(binding)) {
						isNewScope = true;
					}
				}
				
				if (!isNewScope) {
					for (Object c: getContent()) {
						if (c instanceof UIElement) {
							for (Binding b: ((UIElement<?>) c).knockout().getAllBindings()) {
								Binding eb = collector.get(b.getName());
								if (eb==null) {
									collector.put(b.getName(), b);
								} else if (eb.getInitialValue()==null && b.getInitialValue()!=null) {
									((KnockoutBindingImpl) eb).setInitialValue(b.getInitialValue());
								}
							}
						} else if (c instanceof KnockoutBindingsSource) {
							for (Binding b: ((KnockoutBindingsSource) c).getAllBindings()) {
								Binding eb = collector.get(b.getName());
								if (eb==null) {
									collector.put(b.getName(), b);
								} else if (eb.getInitialValue()==null && b.getInitialValue()!=null) {
									((KnockoutBindingImpl) eb).setInitialValue(b.getInitialValue());
								}
							}							
						}
					}
				}
				return Collections.unmodifiableCollection(collector.values());
			}			

			@Override
			public String generateObservables(String... excludes) {
				StringBuilder ret = new StringBuilder("// Generated observables").append(System.lineSeparator());
				ObservablesGenerator og = new ObservablesGenerator(excludes);
				for (Binding binding: getAllBindings()) {
					og.addBinding(binding);
				}	
				og.generateObservables(ret);
				return ret.append("// End of generated observables").append(System.lineSeparator()).toString();		
			}
		};
	}
			
	@Override
	public Bootstrap<T> bootstrap() {		
		return new Bootstrap<T>() {

			@Override
			public Grid<T> grid() {
				return UIElementImpl.this.grid();
			}

			@SuppressWarnings("unchecked")
			@Override
			public T background(org.nasdanika.html.Bootstrap.Style style) {
				return style==null ? (T) UIElementImpl.this : addClass("bg-"+style.name().toLowerCase());
			}

			@Override
			public T pullLeft() {
				return addClass("pull-left");
			}

			@Override
			public T pullRight() {
				return addClass("pull-right");
			}

			@Override
			public T centerBlock() {
				return addClass("center-block");
			}

			@Override
			public T clearfix() {
				return addClass("clearfix");
			}

			@Override
			public Text<T> text() {
				return new Text<T>() {

					@SuppressWarnings("unchecked")
					@Override
					public T color(Style style) {
						return style==null ? (T) UIElementImpl.this : addClass("text-"+style.name().toLowerCase());
					}

					@Override
					public T left() {
						return addClass("text-left");
					}

					@Override
					public T center() {
						return addClass("text-center");
					}

					@Override
					public T right() {
						return addClass("text-right");
					}

					@Override
					public T justify() {
						return addClass("text-justify");
					}

					@Override
					public T nowrap() {
						return addClass("text-nowrap");
					}

					@Override
					public T lowercase() {
						return addClass("text-lowercase");
					}

					@Override
					public T uppercase() {
						return addClass("text-uppercase");
					}

					@Override
					public T capitalize() {
						return addClass("text-capitalize");
					}
					
				};
			}

			@Override
			public T show() {
				return addClass("show");
			}

			@Override
			public T hidden() {
				return addClass("hidden");
			}

			@Override
			public T visibleBlock(DeviceSize deviceSize) {
				return addClass("visible-"+deviceSize.code+"-block");
			}

			@Override
			public T visibleInline(DeviceSize deviceSize) {
				return addClass("visible-"+deviceSize.code+"-inline");
			}

			@Override
			public T visibleInlineBlock(org.nasdanika.html.Bootstrap.DeviceSize deviceSize) {
				return addClass("visible-"+deviceSize.code+"-inline-block");
			}

			@Override
			public T hidden(org.nasdanika.html.Bootstrap.DeviceSize deviceSize) {
				return addClass("hidden-"+deviceSize.code);
			}
			
		};
	}
	
	private Grid<T> grid() {
		return new Grid<T>() {

			@Override
			public T container() {
				return addClass("container");
			}

			@Override
			public T fluidContainer() {
				return addClass("container-fluid");
			}

			@Override
			public T row() {
				return addClass("row");
			}

			@Override
			public void close() throws Exception {
				UIElementImpl.this.close();
			}
			
			@Override
			public String toString() {
				return UIElementImpl.this.toString();
			}

			@Override
			public T col(Bootstrap.DeviceSize deviceSize, int width) {
				return addClass("col-"+deviceSize.code+"-"+width);
			}

			@SuppressWarnings("unchecked")
			@Override
			public T col(int width) {
				for (Bootstrap.DeviceSize ds: Bootstrap.DeviceSize.values()) {
					col(ds, width);
				}
				return (T) UIElementImpl.this;
			}

			@Override
			public T colOffset(Bootstrap.DeviceSize deviceSize, int width) {
				return addClass("col-"+deviceSize.code+"-offset-"+width);
			}

			@SuppressWarnings("unchecked")
			@Override
			public T colOffset(int width) {
				for (Bootstrap.DeviceSize ds: Bootstrap.DeviceSize.values()) {
					colOffset(ds, width);
				}
				return (T) UIElementImpl.this;
			}

			@Override
			public T colPush(Bootstrap.DeviceSize deviceSize, int width) {
				return addClass("col-"+deviceSize.code+"-push-"+width);
			}

			@SuppressWarnings("unchecked")
			@Override
			public T colPush(int width) {
				for (Bootstrap.DeviceSize ds: Bootstrap.DeviceSize.values()) {
					colPush(ds, width);
				}
				return (T) UIElementImpl.this;
			}

			@Override
			public T colPull(Bootstrap.DeviceSize deviceSize, int width) {
				return addClass("col-"+deviceSize.code+"-pull-"+width);
			}

			@SuppressWarnings("unchecked")
			@Override
			public T colPull(int width) {
				for (Bootstrap.DeviceSize ds: Bootstrap.DeviceSize.values()) {
					colPull(ds, width);
				}
				return (T) UIElementImpl.this;
			}
			
		};
	}
	
	@Override
	public Angular<T> angular() {
		return new Angular<T>() {
			
			@Override
			public T app() {
				return app("");
			}

			@Override
			public T app(Object appName) {
				return directive("app", appName);
			}

			@Override
			public T controller(Object controllerName) {				
				return directive("controller", controllerName);
			}

			@Override
			public T bind(Object expr) {
				return directive("bind", expr);
			}

			@Override
			public T bindHtml(Object expr) {
				return directive("bind-html", expr);
			}

			@Override
			public T clazz(Object expr) {
				return directive("class", expr);
			}

			@Override
			public T cloak() {
				return directive("cloak", true);
			}

			@Override
			public T hide(Object expr) {
				return directive("hide", expr);
			}

			@Override
			public T show(Object expr) {
				return directive("show", expr);
			}

			@Override
			public T repeat(Object expr) {
				return directive("repeat", expr);
			}

			@Override
			public T click(Object expr) {
				return directive("click", expr);
			}
						
			@Override
			public T directive(String directive, Object expr) {
				return attribute("ng-"+directive, expr);
			}

			@Override
			public T bindTemplate(Object expr) {
				return directive("bind-template", expr);
			}

			@Override
			public T blur(Object expr) {
				return directive("blur", expr);
			}

			@Override
			public T change(Object expr) {
				return directive("change", expr);
			}

			@Override
			public T checked(Object expr) {
				return directive("checked", expr);
			}

			@Override
			public T classEven(Object expr) {
				return directive("class-even", expr);
			}

			@Override
			public T classOdd(Object expr) {
				return directive("class-odd", expr);
			}

			@Override
			public T copy(Object expr) {
				return directive("copy", expr);
			}

			@Override
			public T csp() {
				return directive("csp", ""); 
			}

			@Override
			public T cut(Object expr) {
				return directive("cut", expr);
			}

			@Override
			public T dblClick(Object expr) {
				return directive("dblclick", expr);
			}

			@Override
			public T disabled(Object expr) {
				return directive("disabled", expr);
			}

			@Override
			public T focus(Object expr) {
				return directive("focus", expr);
			}

			@Override
			public T form(Object expr) {
				return directive("form", expr);
			}

			@Override
			public T href(Object expr) {
				return directive("href", expr);
			}

			@Override
			public T if_(Object expr) {
				return directive("if", expr);
			}

			@Override
			public T include(Object expr) {
				return directive("include", expr);
			}

			@Override
			public T init(Object expr) {
				return directive("init", expr);
			}

			@Override
			public T jq(Object expr) {
				return directive("jq", expr);
			}

			@Override
			public T keyDown(Object expr) {
				return directive("keydown", expr);
			}

			@Override
			public T keyPress(Object expr) {
				return directive("keypress", expr);
			}

			@Override
			public T keyUp(Object expr) {
				return directive("keyup", expr);
			}

			@Override
			public T list() {
				return directive("list", "");
			}

			@Override
			public T model(Object expr) {
				return directive("model", expr);
			}

			@Override
			public T modelOptions(Object expr) {
				return directive("model-options", expr);
			}

			@Override
			public T mouseDown(Object expr) {
				return directive("mousedown", expr);
			}

			@Override
			public T mouseEenter(Object expr) {
				return directive("mouseenter", expr);
			}

			@Override
			public T mouseLeave(Object expr) {
				return directive("mouseleave", expr);
			}

			@Override
			public T mouseMove(Object expr) {
				return directive("mousemove", expr);
			}

			@Override
			public T mouseOver(Object expr) {
				return directive("mouseover", expr);
			}

			@Override
			public T mouseUp(Object expr) {
				return directive("mouseup", expr);
			}

			@Override
			public T nonBindable() {
				return directive("non-bindable", "");
			}

			@Override
			public T open(Object expr) {
				return directive("open", expr);
			}

			@Override
			public T options(Object expr) {
				return directive("optins", expr);
			}

			@Override
			public T paste(Object expr) {
				return directive("paste", expr);
			}

			@Override
			public T readonly(Object expr) {
				return directive("readonly", expr);
			}

			@Override
			public T selected(Object expr) {
				return directive("selected", expr);
			}

			@Override
			public T src(Object expr) {
				return directive("src", expr); 
			}

			@Override
			public T srcset(Object expr) {
				return directive("srcset", expr);
			}

			@Override
			public T style(Object expr) {
				return directive("style", expr);
			}

			@Override
			public T submit(Object expr) {
				return directive("submit", expr);
			}

			@Override
			public T switch_(Object expr) {
				return directive("switch", expr);
			}

			@Override
			public T switchWhen(Object expr) {
				return directive("switch-when", expr);
			}

			@Override
			public T switchDefault() {
				return directive("switch-default", "");
			}

			@Override
			public T value(Object expr) {
				return directive("value", expr);
			}

			@Override
			public T required(Object expr) {
				return directive("required", expr);
			}

			@Override
			public T minLength(Object expr) {
				return directive("minlength", expr);
			}

			@Override
			public T maxLength(Object expr) {
				return directive("maxlength", expr);
			}

			@Override
			public T pattern(Object expr) {
				return directive("pattern", expr);
			}

			@Override
			public T trim(Object expr) {
				return directive("trim", expr);
			}
			
		};
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public T comment(String comment) {
		this.comment = comment;
		return (T) this;
	}
	
	/**
	 * Utility method creates an object which toString() method returns ID of the uiElement argument.
	 * If id of the ui element is not set, it gets auto-generated by the factory. To correctly use the returned
	 * value, ui element id should either be assigned beforehand, or id rendering shall be done before ui element rendering.
	 * @param factory
	 * @param uiElement
	 * @return
	 */
	static Object autoId(final HTMLFactory factory, final UIElement<?> uiElement) {
		return new Object() {
			
			@Override
			public String toString() {
				if (uiElement==null) {
					return null;
				}
				Object id = uiElement.getId();
				if (id==null) {
					id = "uiElement_"+factory.nextId();
					uiElement.id(id);
				}
				return String.valueOf(id);
			}
		};
	}
		
	/**
	 * Special handling for input streams, readers, producers and URL's.
	 * @param content
	 * @return
	 */
	public static String stringify(
			Object content, 
			int indent,
			HTMLFactory factory,
			Producer.Adapter producerAdapter, 
			FactoryProducer.Adapter factoryProducerAdapter) {
		try {
			if (content == null) {
				return "";
			}
			
			if (content instanceof String) {
				return (String) content;
			}
			
			if (content instanceof Producer) {
				return stringify(((Producer) content).produce(indent), indent, factory, producerAdapter, factoryProducerAdapter);
			}
			
			if (content instanceof FactoryProducer) {
				return stringify(((FactoryProducer) content).produce(factory, indent), indent, factory, producerAdapter, factoryProducerAdapter);
			}
			
			if (content!=null && producerAdapter!=null) {
				Producer producer = producerAdapter.asProducer(content);
				if (producer!=null) {
					return stringify(producer, indent, factory, producerAdapter, factoryProducerAdapter);
				}
			}
			
			if (content!=null && factoryProducerAdapter!=null) {
				FactoryProducer factoryProducer = factoryProducerAdapter.asFactoryProducer(content);
				if (factoryProducer!=null) {
					return stringify(factoryProducer, indent, factory, producerAdapter, factoryProducerAdapter);
				}
			}
			
			if (content instanceof InputStream) {
				return stringify(new InputStreamReader((InputStream) content), indent, factory, producerAdapter, factoryProducerAdapter);
			}
			if (content instanceof Reader) {
				StringWriter sw = new StringWriter();
				for (int ch = ((Reader) content).read(); ch!=-1; ch = ((Reader) content).read()) {
					sw.write(ch);
				}
				((Reader) content).close();
				sw.close();
				return sw.toString();
			}
			
			if (content instanceof URL) {
				return stringify(((URL) content).openStream(), indent, factory, producerAdapter, factoryProducerAdapter);
			}
	
			return content.toString();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new ProducerException(e);
		}
	}
	
	public static String stringify(Object content, int indent, HTMLFactory factory) {
		return stringify(
				content,
				indent,
				factory,
				factory instanceof AbstractHTMLFactory ? ((AbstractHTMLFactory) factory).getProducerAdapter() : null,
				factory instanceof AbstractHTMLFactory ? ((AbstractHTMLFactory) factory).getFactoryProducerAdapter() : null);
	}
	
	protected String stringify(Object content, int indent) {
		return stringify(content, indent, factory);
	}
	
	/**
	 * Fallback for situations with missed refactorings of append().
	 */
	@Override
	public String toString() {
		return stringify(produce(0), 0);
	}
	
	public static StringBuilder indent(StringBuilder stringBuilder, int indent) {
		stringBuilder.append(System.lineSeparator());
		for (int i=0; i<indent; ++i) {
			stringBuilder.append("  ");
		}
		return stringBuilder;
	}
	
	public static StringBuilder indent(int indent) {
		return indent(new StringBuilder(), indent);
	}
	
	protected List<Object> content = new ArrayList<>();	
	
	protected List<Object> getContent() {
		return content;
	}
	
	protected String tagName;
	
	protected String getTagName() {
		return tagName;
	}
	
	@Override
	public String produce(int indent) {		
		List<Object> theContent = new ArrayList<>();
		for (Object c: getContent()) {
			if (c instanceof FragmentImpl) {
				theContent.addAll(((FragmentImpl) c).getAllContent());
			} else if (c!=null) {
				theContent.add(c);
			}			
		}
		if (theContent.isEmpty() && !forceEndTag()) {
			return indent(renderComment(indent), indent).append("<"+getTagName()+attributes()+"/>").toString();
		}
		StringBuilder sb = indent(renderComment(indent), indent).append("<").append(getTagName()).append(attributes()).append(">");
		boolean hasNonUIElementContent = false;
		for (Object c: theContent) {
			sb.append(stringify(c, indent+1));
			if (!(c instanceof UIElement)) {
				hasNonUIElementContent = true;
			}
		}
		if (!theContent.isEmpty() && !hasNonUIElementContent) {
			indent(sb, indent);
		}
		return sb.append("</").append(getTagName()).append(">").append(genLoadRemoteContentScript()).toString();
	}

	private boolean forceEndTag() {
		for (Tag.TagName tagName: Tag.TagName.values()) {
			if (tagName.name().equalsIgnoreCase(this.tagName)) {
				return tagName.isPaired();
			}
		}
		return false;
	}

	/**
	 * Method to add content, make public in container elements.
	 * @param content
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T content(Object... content) {
		for (Object c: content) {
			this.content.add(c);
		}
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	private Style<T> style = new StyleImpl<T>((T) this);

	@Override
	public org.nasdanika.html.Style<T> style() {
		return style;
	}
	
	public boolean isEmpty() {
		return getContent().isEmpty();
	}
	
	private JsTree jsTree;
	
	@Override
	public JsTree jsTree() {
		if (jsTree == null) {
			jsTree = new JsTree() {
				
				private boolean selected;
				private boolean opened;
				private boolean disabled;
				private String icon;
				private FontAwesome<Tag> fontAwesome;
				
				@Override
				public JsTree selected(boolean selected) {
					this.selected = selected;
					return this;
				}
				
				@Override
				public JsTree opened(boolean opened) {
					this.opened = opened;
					return this;
				}
				
				@Override
				public FontAwesome<Tag> icon() {
					icon = null;
					fontAwesome = getFactory().fontAwesome();
					return fontAwesome;
				}
				
				@Override
				public JsTree icon(String icon) {
					fontAwesome = null;
					this.icon = icon;
					return this;
				}
				
				@Override
				public JsTree disabled(boolean disabled) {
					this.disabled = disabled;
					return this;
				}
				
				@Override
				public String toString() {
					JSONObject data = new JSONObject();
					if (selected) {
						data.put("selected", true);
					}
					if (opened) {
						data.put("opened", true);
					}
					if (disabled) {
						data.put("disabled", true);
					}
					if (icon != null) {
						data.put("icon", icon);
					} else if (fontAwesome != null) {
						data.put("icon", ((TagImpl) fontAwesome.getTarget()).classes());
					}
					return data.toString(); // Escape???
				}
			};
			
			attribute("data-jstree", jsTree);
		}
		return jsTree;
	}
	
	@Override
	public String jQuery(String expr) {
		if (getId() == null) {
			id(factory.nextId());
		}
		return "jQuery('#"+getId()+"')."+expr;
	}
	
}



