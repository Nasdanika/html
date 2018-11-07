package org.nasdanika.html.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.html.FactoryProducer;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Producer;
import org.nasdanika.html.ProducerException;
import org.nasdanika.html.Style;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Markup;

/**
 * Base class for UI elements
 * @author Pavel
 *
 * @param <T>
 */
public abstract class HTMLElementImpl<T extends HTMLElement<T>> implements HTMLElement<T>, AutoCloseable {


	private static final String STYLE = "style";
	
	private static final String CLASS = "class";
	
	private static final String ID = "id";
	
	public Object getId() {
		return attributes.get(ID);
	}
	
	protected Map<String, Object> attributes = new LinkedHashMap<>();
	
	private Map<String, Object> styles = new LinkedHashMap<>();

	protected HTMLFactory factory;

	private boolean nonEmpty;
	
	public HTMLFactory getFactory() {
		return factory;
	}
	
	public HTMLElementImpl(HTMLFactory factory, Tag.TagName tagName, boolean nonEmpty) {
		this(factory, tagName.name(), nonEmpty);
	}
	
	/**
	 * 
	 * @param factory
	 * @param tagName
	 * @param nonEmpty If true then the element is rendered only if it has content, i.e. there is no point to 
	 * render an empty element.
	 */
	public HTMLElementImpl(HTMLFactory factory, String tagName, boolean nonEmpty) {
		this.factory = factory;
		this.tagName = tagName;
		this.nonEmpty = nonEmpty;
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
			if (!STYLE.equals(a.getKey())
					&& !CLASS.equals(a.getKey()) 
					&& !Arrays.asList(excluded).contains(a.getKey())) {
				if (attributeBuilder.length() > 0) {
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
	protected void merge(HTMLElementImpl<?> source) {
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
	public T attribute(String name, Object value, boolean condition) {
		return condition ? attribute(name, value) : (T) this;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public T addClassConditional(boolean condition, Object... clazz) {
		return condition ? addClass(clazz) : (T) this;
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
	static Object autoId(final HTMLFactory factory, final HTMLElement<?> uiElement) {
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
				factory instanceof DefaultHTMLFactory ? ((DefaultHTMLFactory) factory).getProducerAdapter() : null,
				factory instanceof DefaultHTMLFactory ? ((DefaultHTMLFactory) factory).getFactoryProducerAdapter() : null);
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
		if (theContent.isEmpty()) {
			if (nonEmpty) {
				return "";
			}
			
			if (!forceEndTag()) {
				return indent(renderComment(indent), indent).append("<"+getTagName()+attributes()+"/>").toString();
			}
		}
		StringBuilder sb = indent(renderComment(indent), indent).append("<").append(getTagName()).append(attributes()).append(">");
		boolean hasNonUIElementContent = false;
		for (Object c: theContent) {
			sb.append(stringify(c, indent+1));
			if (!(c instanceof Markup)) {
				hasNonUIElementContent = true;
			}
		}
		if (!theContent.isEmpty() && !hasNonUIElementContent) {
			indent(sb, indent);
		}
		return sb.append("</").append(getTagName()).append(">").toString();
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
	
	@Override
	public String jQuery(String expr) {
		if (getId() == null) {
			id(factory.nextId());
		}
		return "jQuery('#"+getId()+"')."+expr;
	}
	
	private Object data;
	private Map<String, Object> properties = new HashMap<>();
	
	@Override
	public Object getData() {
		return data;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T setData(Object data) {
		this.data = data;
		return (T) this;
	}
	
	@Override
	public Object getData(String key) {
		return properties.get(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T setData(String key, Object data) {
		properties.put(key, data);
		return (T) this;
	}
		
}



