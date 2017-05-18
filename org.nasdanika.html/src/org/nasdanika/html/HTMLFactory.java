package org.nasdanika.html;

import java.util.Map;

import org.nasdanika.html.impl.DefaultHTMLFactory;


/**
 * Implementations of this class abstract HTML rendering code from underlying JavaScript/CSS frameworks and
 * rendering of low-level HTML primitives. 
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
	
	Tag tag(Tag.TagName tagName, Object... content);
	
	Tag div(Object... content);
	
	Tag span(Object... content);
	
	Tag badge(Object... content);
	
	enum InputType { 
		button,
		checkbox,
		color,
		date, 
		datetime, 
		datetime_local, 
		email,
		file,
		hidden,
		image,
		month, 
		number, 
		password,
		radio,
		range, 
		reset,
		search,
		submit,
		tel,
		text,
		time, 
		url,
		week;
	
		public String code() {
			return name().replace('_', '-');
		}
		
		/**
		 * Creates input with {@link HTMLFactory}.INSTANCE.
		 * @return
		 */
		public Input create() {
			return HTMLFactory.INSTANCE.input(this);
		}
	}
	
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
	
	Tabs tabs();
	
	Pills pills();
	
	Tag panel(Bootstrap.Style style, Object header, Object body, Object footer);
	
	/**
	 * Builds a router reference in a form <code>#router/&lt;targetElement&gt;/&lt;path&gt;</code>
	 * @param targetElement
	 * @param path
	 * @return
	 */
	AutoCloseable routeRef(final Object targetElement, final Object path);	

	/**
	 * Build a link for the in-page router.
	 * @param targetElement
	 * @param path
	 * @param text
	 * @param hint
	 * @return
	 */
	Tag routeLink(Object targetElement, Object path, Object... content);	
	
	/**
	 * Generates a router application with Bootstrap script and style.
	 * @param title Application title.
	 * @param initialRoute Initial route to navigate.
	 * @param head Declarations to add to head, e.g. script and css references.
	 * @param body Application body.
	 * @return
	 */
	AutoCloseable bootstrapRouterApplication(
			Theme theme,
			Object title, 
			Object initialRoute, 
			Object head, 
			Object... body);
	
	/**
	 * Generates a router application
	 * @param title Application title.
	 * @param initialRoute Initial route to navigate.
	 * @param head Declarations to add to head, e.g. script and css references.
	 * @param body Application body.
	 * @return
	 */
	AutoCloseable routerApplication(
			Object title, 
			Object initialRoute, 
			Object head, 
			Object... body);

	Tag label(Bootstrap.Style style, Object... content);
	
	Tag alert(Bootstrap.Style style, boolean dismissable, Object... content);
	
	// TODO - code from InputStream/Reader, e.g. from class loader resource, escape for putting to attributes, e.g. onclick() 
	// String code(InputStream in, boolean escape);
	
	/****************************
	 *  Complex elements API's  *
	 ****************************/

	ListGroup listGroup();
	
	LinkGroup linkGroup();
	
	Navbar navbar(Object brand, Object brandRef);
	
	Table table();
			
	ApplicationPanel applicationPanel();
	
	Button button(Object... content);
	
	Form form();
	
	InputGroup<?> inputGroup(Object control);

	Accordion accordion();
	
	Object collapsible(Bootstrap.Style style, Object title, boolean collapsed, Object... content);
	
	enum Placement { LEFT, TOP, RIGHT, BOTTOM }
	
	/**
	 * Adds attributes to the element, typically a button, to make it a popover. 
	 * This method does not initialize the popover, it shall be done through JavaScript <code>$(selector).popover();</code> e.g.
	 * <code>$('#my_popover_button').popover();</code>
	 * @param element Element
	 * @param placement popover placement.
	 * @param title popover title
	 * @param text popover text
	 * @return
	 */
	<T extends UIElement<?>> T popover(T element, Placement placement, String title, String text);
	
	/**
	 * Adds tooltip to the element. 
	 * This method does not initialize the tooltip, it shall be done through JavaScript <code>$(selector).tooltip();</code> e.g.
	 * <code>$('#my_button').tooltip();</code>
	 * @param element Element
	 * @param placement tooltip placement.
	 * @param text tooltip text
	 * @return
	 */
	<T extends UIElement<?>> T tooltip(T element, Placement placement, String text);	
	
	Tag glyphicon(Bootstrap.Glyphicon glyphicon);
	
	Modal modal();
	
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
	
	Breadcrumbs breadcrumbs();
	
	ButtonGroup buttonGroup(Button... buttons);
	
	ButtonToolbar buttonToolbar(ButtonGroup... buttonGroups);
	
	// --- JavaScript ---
	
	Function function(Object... param);
	
	/**
	 * Generates RequireJS <code>require([module list], function)</code> 
	 * @param function
	 * @param module
	 * @return
	 */
	Require require(Object function, Object... module);

	Carousel carousel();
	
	/**
	 * Shortcut for <code>factory.span().fontAwesome();</code>
	 * @return
	 */
	FontAwesome<Tag> fontAwesome();
	
	FontAwesome.Stack fontAwesomeStack();
	
	/**
	 * Creates an overlay div with vertically and horizontally centered content.
	 * @param content
	 * @return
	 */
	Tag overlay(Object... content);
	
	/**
	 * Creates an overlay div with a spinning spinner.
	 * @param spinner
	 * @return
	 */
	Tag spinnerOverlay(FontAwesome.Spinner spinner);
	
	/**
	 * Generates JavaScript code (without script tag) to size the overlay and then display it.
	 * @param overlaySelector
	 * @param overlayedSelector
	 */
	String showOverlay(String overlaySelector, String overlayedSelector, int widthAdjustment, int heightAdjustment);
	
	Well well(Object... content);
	
	/**
	 * Generates script to stack modals as demonstrated at http://miles-by-motorcycle.com/static/bootstrap-modal/index.html
	 * @return
	 */
	Tag stackModal();
	
	Dropdown<?> dropdown(UIElement<?> toggle);
	
	/**
	 * Dropdown with the caret-down toggle.
	 * @return
	 */
	Dropdown<?> caretDropdown();
	
	KnockoutVirtualElement knockoutVirtualElement(Object... content);
	
	/**
	 * Source of token values for interpolation.
	 * @author Pavel Vlasov.
	 *
	 */
	interface TokenSource {
		
		Object get(String token);
		
	}

	/**
	 * Expands tokens in the form of <code>{{token name}}</code> to their values.
	 * If a token is not found expansion is not processed.
	 * @param input
	 * @param tokenSource
	 * @return
	 */
	String interpolate(Object input, TokenSource tokenSource);
	
	/**
	 * Expands tokens in the form of <code>{{token name}}</code> to their values.
	 * If a token is not found expansion is not processed.
	 * @param input
	 * @param env
	 * @return
	 */
	String interpolate(Object input, final Map<String, Object> env);	
	
	/**
	 * Expands a single token in the form of <code>{{token name}}</code> to its value.
	 * If a token is not found expansion is not processed.
	 * @param input 
	 * @param token
	 * @param value
	 * @return
	 */
	String interpolate(Object input, String token, Object value);
	
	JsTreeNode jsTreeNode();
	
}
