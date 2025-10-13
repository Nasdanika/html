package org.nasdanika.html.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;

import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.common.Adaptable;
import org.nasdanika.html.Event;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Markup;
import org.nasdanika.html.Producer;
import org.nasdanika.html.ProducerException;
import org.nasdanika.html.Style;
import org.nasdanika.html.TagName;

import reactor.core.publisher.Mono;

/**
 * Base class for UI elements
 * @author Pavel
 *
 * @param <T>
 */
public abstract class HTMLElementImpl<T extends HTMLElement<T>> implements HTMLElement<T>, Adaptable {

	private static final String STYLE = "style";
	
	private static final String CLASS = "class";
	
	private static final String ID = "id";
	
	public Object getId() {
		return attributes.get(ID);
	}
	
	protected Map<String, Object> attributes = Collections.synchronizedMap(new LinkedHashMap<>());
	
	private Map<String, Object> styles = Collections.synchronizedMap(new LinkedHashMap<>());

	protected HTMLFactory factory;

	private boolean nonEmpty;
	
	public HTMLFactory getFactory() {
		return factory;
	}
	
	public HTMLElementImpl(HTMLFactory factory, TagName tagName, boolean nonEmpty) {
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
			if (styleBuilder.length() > 0) {
				if (attributeBuilder.length() > 0) {
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
		List<String> classList = new ArrayList<>();
		for (Object cls: classes) {
			String classString = stringify(cls, 0).trim();
			if (!classList.contains(classString)) {
				classList.add(classString);
			}
		}
		
		StringBuilder classBuilder = new StringBuilder();
		for (String classString: classList) {
			if (classBuilder.length()>0) {
				classBuilder.append(" ");
			}
			classBuilder.append(stringify(classString, 0));
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
				if (styleBuilder.length() > 0 && !styleBuilder.toString().endsWith(";")) {
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
		} else if (value instanceof InputStream || value instanceof Reader) {
			attributes.put(name, stringify(value));			
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
	
	@SuppressWarnings("unchecked")
	@Override
	public T removeClass(Object... clazz) {
		for (Object clz: clazz) {
			classes.remove(clz);
		}
		return (T) this;
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
	 * Special handling for input streams, readers, producers and URL's.
	 * @param content
	 * @return
	 */
	public static String stringify(Object content) {
		return stringify(content, 0);
	}
	
		
	/**
	 * Special handling for input streams, readers, producers and URL's.
	 * @param content
	 * @return
	 */
	public static String stringify(Object content, int indent) {
		try {
			if (content == null) {
				return "";
			}
			
			if (content instanceof String) {
				return (String) content;
			}
			
			if (content instanceof Producer) {
				return stringify(((Producer<?>) content).produce(indent), indent);
			}
			
			if (content instanceof InputStream) {
				return stringify(new InputStreamReader((InputStream) content, StandardCharsets.UTF_8), indent);
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
				return stringify(((URL) content).openStream(), indent);
			}
	
			return content.toString();
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new ProducerException(e);
		}
	}
	
	/**
	 * Fallback for situations with missed refactorings of append().
	 */
	@Override
	public String toString() {
		return stringify(produce(0), 0);
	}
	
	public static StringBuilder indent(StringBuilder stringBuilder, int indent) {
		if (indent != -1) {
			stringBuilder.append(System.lineSeparator());
			for (int i=0; i<indent; ++i) {
				stringBuilder.append("  ");
			}
		}
		return stringBuilder;
	}
	
	public static StringBuilder indent(int indent) {
		return indent(new StringBuilder(), indent);
	}
	
	protected List<Object> content = Collections.synchronizedList(new ArrayList<>());	
	
	public List<Object> getContent() {
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
		boolean hasNonUIElementContent = false;
		
		StringBuilder contentBuilder = new StringBuilder(); 
		for (Object c: theContent) {
			contentBuilder.append(stringify(c, indent == -1 ? indent : indent+1));
			if (!(c instanceof Markup)) {
				hasNonUIElementContent = true;
			}
		}		
		String strContent = contentBuilder.toString();
		if (nonEmpty && strContent.trim().length() == 0) {
			return "";
		}
		StringBuilder sb = indent(renderComment(indent), indent).append("<").append(getTagName()).append(attributes()).append(">").append(strContent);		
		if (strContent.length() > 0 && !hasNonUIElementContent) {
			indent(sb, indent);
		}
		return sb.append("</").append(getTagName()).append(">").toString();
	}

	@Override
	public Mono<Object> produceAsync(int indent) {		
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
				return Mono.just("");
			}
			
			if (!forceEndTag()) {
				return Mono.just(indent(renderComment(indent), indent).append("<"+getTagName()+attributes()+"/>").toString());
			}
		}
		
		List<Mono<Object>> contentProducers = theContent
				.stream()
				.map(Producer::of)
				.filter(Objects::nonNull)
				.map(p -> p.produceAsync(indent))
				.toList();
		
		return contentProducers.isEmpty() ? Mono.just("") : Mono.zip(contentProducers, (Function<Object[], String>) elements -> combine(elements, indent));
	}
	
	private String combine(Object[] elements, int indent) {
		boolean hasNonUIElementContent = false;
		
		StringBuilder contentBuilder = new StringBuilder(); 
		for (Object c: elements) {
			contentBuilder.append(stringify(c, indent == -1 ? indent : indent+1));
			if (!(c instanceof Markup)) {
				hasNonUIElementContent = true;
			}
		}		
		String strContent = contentBuilder.toString();
		if (nonEmpty && strContent.trim().length() == 0) {
			return "";
		}
		StringBuilder sb = indent(renderComment(indent), indent).append("<").append(getTagName()).append(attributes()).append(">").append(strContent);		
		if (strContent.length() > 0 && !hasNonUIElementContent) {
			indent(sb, indent);
		}
		return sb.append("</").append(getTagName()).append(">").toString();
	}			

	private boolean forceEndTag() {
		for (TagName tagName: TagName.values()) {
			if (tagName.name().equalsIgnoreCase(this.tagName)) {
				return tagName.paired;
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
			if (c instanceof Iterable) {
				for (Object cc: (Iterable<?>) c) {
					content(cc);
				}
			} else if (c != null) {
				if (c.getClass().isArray()) {
					for (int i = 0; i < Array.getLength(c); ++i) {
						content(Array.get(c, i));
					}
				} else if (c instanceof InputStream || c instanceof Reader) {
					this.content.add(stringify(c));
				} else {
					this.content.add(c);
				}
			}
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
	private Map<Object, Object> properties = new HashMap<>();
	
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
	public Object getData(Object key) {
		return properties.get(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T setData(Object key, Object data) {
		properties.put(key, data);
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <TT> TT adaptTo(Class<TT> type) {
		if (type == InputStream.class) {
			String str = toString();
			return (TT) new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
		}
		return Adaptable.super.adaptTo(type);
	}
		
}



