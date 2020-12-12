package org.nasdanika.html;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Abstraction of a UI element. toString() produces HTML markup.
 * @author Pavel
 *
 */
public interface HTMLElement<T extends HTMLElement<T>> extends Producer, Markup {
	
	/**
	 * 
	 * @param event Event type.
	 * @param handler Handler script. 
	 * @return
	 */
	T on(Event event, Object handler);

	/**
	 * 
	 * @param event Event name without 'on' prefix, e.g. 'click' for 'onclick' handler
	 * @param handler Handler script.
	 * @return
	 */
	T on(String event, Object handler);

	/**
	 * 
	 * @param event Event type.
	 * @param handler Handler script.
	 * @return
	 */
	T on(Event event, Reader handler) throws IOException;

	/**
	 * 
	 * @param event Event name without 'on' prefix, e.g. 'click' for 'onclick' handler
	 * @param handler Handler script. 
	 * @return
	 */
	T on(String event, Reader handler) throws IOException;

	/**
	 * 
	 * @param event Event type.
	 * @param handler Handler script. 
	 * @return
	 */
	T on(Event event, InputStream handler) throws IOException;

	/**
	 * 
	 * @param event Event name without 'on' prefix, e.g. 'click' for 'onclick' handler
	 * @param handler Handler script. 
	 * @return
	 */
	T on(String event, InputStream handler) throws IOException;
		
	/**
	 * Sets element id.
	 * @param id
	 * @return
	 */
	T id(Object id);
	
	Object getId();
	
	/**
	 * Sets element attribute
	 * @param name  if name is 'id' then value replaces value set through id() and vice versa. If attribute name is 'style' then
	 * this definition is merged with styles defined through style() method.
	 * @param value Attribute value. This method HTML-escapes the value, e.g. replaces " with &quot;
	 * @return
	 * 
	 */
	T attribute(String name, Object value);
	
	/**
	 * Conditionally sets attribute. A convenience method for writing more compact code.
	 * @return
	 * 
	 */
	T attribute(String name, Object value, boolean condition);	
	
	String getAttribute(String name);
	
	/**
	 * Sets style attribute
	 * @param name
	 * @param value
	 * @return
	 */
	T style(String name, Object value);
	
	Style<T> style();
	
	/**
	 * Adds class definition.
	 * @param clazz
	 * @return
	 */
	T addClass(Object... clazz);

	/**
	 * Removes class definition.
	 * @param clazz
	 * @return
	 */
	T removeClass(Object... clazz);	
	
	/**
	 * Adds class definition if condition is true. Convenience method to write shorter code.
	 * @param clazz
	 * @return
	 */
	T addClassConditional(boolean condition, Object... clazz);
	
	
	T comment(String comment);
	
	/**
	 * 
	 * @return Factory used to create this element.
	 */
	HTMLFactory getFactory();	

	/**
	 * Convenience method for constructing ``jQuery('#<element id>').<expr>`` strings.
	 * If id is not set, it is set to ``factory.nextId()``.
	 * @param expr Expression to add after ``jQuery('#<element id>').``.
	 * @return
	 */
	String jQuery(String expr);
	
	/**
	 * Sets the application defined UI element data. This data is not used for rendering by UI elements.
	 * Applications may put arbitrary objects in this field. 
	 * @param data
	 * @return this UI element.
	 */
	T setData(Object data);
	
	/**
	 * Returns the application defined UI element data, or null if it has not been set. 
	 * @return
	 */
	Object getData();
	
	/**
	 * Sets the application defined property of the UI element with the specified name to the given value.
	 * Applications may associate arbitrary objects with the UI element. This data is not used for rendering by UI elements.
	 * @param key
	 * @param data
	 * @return this UI element.
	 */
	T setData(String key, Object data);
	
	/**
	 * Returns the application defined property of the UI element with the specified name, or null if it has not been set.
	 * @param Key
	 * @return
	 */
	Object getData(String key);
	
	/**
	 * @return Element content.
	 */
	List<Object> getContent();
	
	/**
	 * Loads HTML attributes from a map.
	 * For all top-level keys except ``class``, ``style``, and ``data`` attribute value is produced by converting the value to string for scalars and to JSON string for lists and maps.
	 * "children" key is ignored - it is used to define a hierarchy of attributes.
	 * For class attribute its value is formed by concatenating elements using space as a separator. If elements are hierarchical then class name is formed by concatenation with a dash (``-``) as a separator.
	 * If value of ``data`` attribute is a map then keys of that map get concatenated with ``data`` using dash (``-``) as a separator, them same applies to nested maps. Non-map values become attribute values - scalars are converted to string, 
	 * lists are converted to JSON string.
	 * Style can be defined as a string, list or map. If style is defined as a list, all list values are concatenated with a space as a separator - it is a convent way for long unstructured definitions.
	 * If style value is a map then the value and its contained map values are processed in the following fashion:
	 * a) Keys are concatenated with dash as a separator.
	 * b) List values are concatenated with space as a separator.
	 * 
	 * @param attributes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default T attributes(Map<String,Object> attributes) {
		
		class Util {
			
			void setDataAttribute(HTMLElement<?> htmlElement, String key, Object value) {
				if (value instanceof Map) {
					for (Entry<String, Object> entry: ((Map<String,Object>) value).entrySet()) {
						setDataAttribute(htmlElement, key+"-"+entry.getKey(), value);
					}
				} else if (value instanceof Collection) {
					JSONArray jsonArray = new JSONArray((Collection<?>) value);
					htmlElement.attribute(key, jsonArray); 																
				} else {
					htmlElement.attribute(key, value);
				}				
			}

			void setStyle(HTMLElement<?> htmlElement, String key, Object value) {
				if (value instanceof Map) {
					for (Entry<String, Object> entry: ((Map<String,Object>) value).entrySet()) {
						setStyle(htmlElement, key + "-" + entry.getKey(), entry.getValue());
					}
				} else if (value instanceof Collection) {
					StringBuilder styleBuilder = new StringBuilder();
					for (Object e: (Collection<?>) value) {
						if (styleBuilder.length() > 0) {
							styleBuilder.append(" ");
						}
						styleBuilder.append(e);
					}
					htmlElement.style(key, styleBuilder.toString());
				} else {
					htmlElement.style(key, value);
				}				
			}

			void addClass(HTMLElement<?> htmlElement, String prefix, Object value) {
				if (value instanceof Map) {
					for (Entry<String, Object> entry: ((Map<String,Object>) value).entrySet()) {
						addClass(htmlElement, prefix == null ? String.valueOf(entry.getKey()) : prefix + "-" + entry.getKey(), entry.getValue());
					}
				} else if (value instanceof Collection) {
					for (Object e: (Collection<?>) value) {
						addClass(htmlElement, prefix, e);
					}
				} else {
					if (value instanceof Boolean) {
						if (Boolean.TRUE.equals(value) && prefix != null) {
							htmlElement.addClass(prefix);
						}
					} else {
						htmlElement.addClass(prefix == null ? String.valueOf(value) : prefix + "-" + value);
					}
				}				
			}
			
			void setAttributes() {
				for (Entry<String, Object> entry: attributes.entrySet()) {
					Object value = entry.getValue();
					switch (entry.getKey()) {
					case "children":
						break;
					case "class":
						addClass(HTMLElement.this, null, value);
						break;
					case "style":
						if (value instanceof Collection) {
							StringBuilder styleBuilder = new StringBuilder();
							for (Object e: (Collection<?>) value) {
								if (styleBuilder.length() > 0) {
									styleBuilder.append(" ");
								}
								styleBuilder.append(e);
							}
							HTMLElement.this.attribute(entry.getKey(), styleBuilder.toString());								
						} else if (value instanceof Map) {
							for (Entry<String, Object> se: ((Map<String,Object>) value).entrySet()) {
								setStyle(HTMLElement.this, se.getKey(), se.getValue());
							}
						} else {
							HTMLElement.this.attribute(entry.getKey(), value);								
						}
						break;
					case "data":
						setDataAttribute(HTMLElement.this, entry.getKey(), value);
						break;
					default:
						if (value instanceof Map) {
							JSONObject jsonObject = new JSONObject((Map<?,?>) value);
							HTMLElement.this.attribute(entry.getKey(), jsonObject); 								
						} else if (value instanceof Collection) {
							JSONArray jsonArray = new JSONArray((Collection<?>) value);
							HTMLElement.this.attribute(entry.getKey(), jsonArray); 																
						} else {
							HTMLElement.this.attribute(entry.getKey(), value);
						}
					}
				}
			}
		}
		
		new Util().setAttributes();
	
		return (T) this;
	}
	
}
