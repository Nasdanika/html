package org.nasdanika.html;

import java.util.Map;

import org.nasdanika.html.impl.DefaultHTMLFactory;


/**
 * Implementations of this class abstract HTML rendering code from rendering of low-level HTML primitives. 
 * @author Pavel
 *
 */
public interface HTMLFactory {
	
	HTMLFactory INSTANCE = new DefaultHTMLFactory();
	
	/**
	 * Generates element ID.
	 * @return
	 */
	String nextId();
	
	/*******************
	 *  Simple API's   *
	 *******************/
	
	/**
	 * Creates a tag with a given name which attributes and styles can be manipulated
	 * with UIElement methods.
	 * @param tagName
	 * @param content
	 * @return
	 */
	Tag tag(String tagName, Object... content);
	
	Tag tag(TagName tagName, Object... content);
	
	/**
	 * Creates a tag which is rendered only if it has content.
	 * @param tagName
	 * @param content
	 * @return
	 */
	Tag nonEmptyTag(String tagName, Object... content);

	/**
	 * Creates a tag which is rendered only if it has content.
	 * @param tagName
	 * @param content
	 * @return
	 */
	Tag nonEmptyTag(TagName tagName, Object... content);
	
	
	Tag div(Object... content);
	
	/**
	 * Creates a div which is rendered only if it has content.
	 * @param content
	 * @return
	 */
	Tag nonEmptyDiv(Object... content);
	
	
	Tag span(Object... content);
	
	Fragment fragment(Object... content);
	
	/**
	 * A convenience method for creating form inputs
	 * @param type Input type
	 * @param name Input name - optional
	 * @param value Input value - optional
	 * @param id Control id - optional
	 * @param placeholder Placeholder - optional
	 * @return
	 */
	Input input(InputType type);
	
	Select select();

	TextArea textArea();

	Tag link(Object href, Object... content);
	
	Tag ul(Iterable<?> items);
	
	Tag ol(Iterable<?> items);
	
	Table table();
			
	Button button(Object... content);
	
	Form form();
	
	/**
	 * Generates document.title = title script.
	 * @param title
	 * @return
	 */
	Tag title(Object title);
	
	/**
	 * Generates a DIV and a script to inject content into an element
	 * specified by selector, if such element exists. After injection the 
	 * content is removed from the DOM tree. If target container does not exist then 
	 * nothing happens and the content remains where it is. This method can 
	 * be used in single page applications to update, say, breadcrumbs
	 * when a new content is loaded in one of page containers.   
	 * 
	 * @param selector
	 * @param content
	 * @return A DIV contai
	 */
	Tag inject(Object selector, Object... content);
	
	// --- JavaScript ---
	
	Function function(Object... param);
	
	/**
	 * Creates an overlay div with vertically and horizontally centered content.
	 * @param content
	 * @return
	 */
	Tag overlay(Object... content);
	
	/**
	 * Generates JavaScript code (without script tag) to size the overlay and then display it.
	 * @param overlaySelector
	 * @param overlayedSelector
	 */
	String showOverlay(String overlaySelector, String overlayedSelector, int widthAdjustment, int heightAdjustment);
	
	/**
	 * Expands tokens in the form of <code>{{token name}}</code> to their values.
	 * If a token is not found expansion is not processed.
	 * @param input String, Reader, InputStream or URL.
	 * @param tokenSource
	 * @return
	 */
	String interpolate(Object input, TokenSource tokenSource);
	
	/**
	 * Expands tokens in the form of <code>{{token name}}</code> to their values.
	 * If a token is not found expansion is not processed.
	 * @param input String, Reader, InputStream or URL.
	 * @param env
	 * @return
	 */
	String interpolate(Object input, final Map<String, Object> env);	
	
	/**
	 * Expands a single token in the form of <code>{{token name}}</code> to its value.
	 * If a token is not found expansion is not processed.
	 * @param input String, Reader, InputStream or URL.
	 * @param token
	 * @param value
	 * @return
	 */
	String interpolate(Object input, String token, Object value);
	
	/**
	 * Creates a named items container which wraps name in the specified tag, e.g. H2.
	 * @param tagName
	 * @return
	 */
	NamedItemsContainer tagNamedItemsContainer(TagName tagName);
	
	/**
	 * Creates HTML page.
	 * @return
	 */
	HTMLPage page();
	
	/**
	 * Creates a mutable token source with initial token.
	 * @param token
	 * @param value
	 * @return
	 */
	MutableTokenSource mutableTokenSource(String token, Object value);
	
	/**
	 * Creates a new mutable token source.
	 * @return
	 */
	MutableTokenSource mutableTokenSource();	
}
