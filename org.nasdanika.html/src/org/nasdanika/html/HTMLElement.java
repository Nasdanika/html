package org.nasdanika.html;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

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
	
}
