package org.nasdanika.html;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * Abstraction of a UI element. toString() produces HTML markup.
 * @author Pavel
 *
 */
public interface HTMLElement<T extends HTMLElement<T>> extends AutoCloseable, Producer, Markup {
	
	/**
	 * Enumeration for frequently used HTML events
	 * @author Pavel
	 *
	 */
	enum Event {
		// Form Events
		// Events triggered by actions inside a HTML form (applies to almost all HTML elements, but is most used in form elements):

		/** Fires the moment that the element loses focus **/
		blur, 
		
		/** Fires the moment when the value of the element is changed **/
		change,
		
		/** Script to be run when a context menu is triggered **/
		contextmenu, 
		
		/** Fires the moment when the element gets focus **/
		focus, 
		
		/** Script to be run when a form changes **/
		formchange,
		
		/** Script to be run when a form gets user input **/
		forminput,
		
		/** Script to be run when an element gets user input **/
		input, 
		
		/** Script to be run when an element is invalid **/
		invalid, 
		
		/** Fires when the Reset button in a form is clicked **/
		reset, 
		
		/** Fires after some text has been selected in an element **/
		select, 
		
		/** Fires when a form is submitted **/
		submit, 

		// Keyboard Events
		
		/** Fires when a user is pressing a key **/
		keydown, 
		
		/** Fires when a user presses a key **/
		keypress,
		
		/** Fires when a user releases a key **/
		keyup, 

		// Mouse Events
		// Events triggered by a mouse, or similar user actions:

		/** Fires on a mouse click on the element **/
		click, 
		
		/** Fires on a mouse double-click on the element **/
		dblclick,
		
		/** Script to be run when an element is dragged **/
		drag, 
		
		/** Script to be run at the end of a drag operation **/
		dragend, 
		
		/** Script to be run when an element has been dragged to a valid drop target **/
		dragenter, 
		
		/** Script to be run when an element leaves a valid drop target **/
		dragleave, 
		
		/** Script to be run when an element is being dragged over a valid drop target **/
		dragover, 
		
		/** Script to be run at the start of a drag operation **/
		dragstart, 
		
		/** Script to be run when dragged element is being dropped **/
		drop, 
		
		/** Fires when a mouse button is pressed down on an element **/
		mousedown, 
		
		/** Fires when the mouse pointer moves over an element **/
		mousemove, 
		
		/** Fires when the mouse pointer moves out of an element **/
		mouseout, 
		
		/** Fires when the mouse pointer moves over an element **/
		mouseover, 
		
		/** Fires when a mouse button is released over an element **/
		mouseup, 
		
		/** Script to be run when the mouse wheel is being rotated **/
		mousewheel, 
		
		/** Script to be run when an element's scrollbar is being scrolled **/
		scroll 		
	}

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
}
