package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.RowContainer.Row;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.impl.DefaultBootstrapFactory;


/**
 * Factory for creating bootstrap UI elements. 
 * @author Pavel
 *
 */
public interface BootstrapFactory {
	
	BootstrapFactory INSTANCE = new DefaultBootstrapFactory(HTMLFactory.INSTANCE);
	
	/**
	 * @return Underlying HTML factory.
	 */
	HTMLFactory getHTMLFactory();
		
	Tag badge(Object... content);
	
	/**
	 * Wraps {@link HTMLElement} into Bootstrap for setting Bootstrap classes and attributes.
	 * @param htmlElement
	 * @return
	 */
	<T extends HTMLElement<?>> Bootstrap<T> from(T htmlElement);
	
	Tabs tabs();
	
	Pills pills();
	
	Tag panel(Bootstrap.Style style, Object header, Object body, Object footer);
	
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
	
	/**
	 * Creates a bootstrap table backed by an empty HTML table.
	 * @return
	 */
	Table table();
	
	/**
	 * Wraps HTML table.
	 * @param htmlTable
	 * @return
	 */
	Table table(org.nasdanika.html.Table htmlTable); 
	
	/**
	 * Creates HTML Button with specified content, wraps into Bootstrap button and returns.
	 * @param content
	 * @return
	 */
	Button button(Object... content);
	
	/**
	 * Wraps HTML button.
	 * @param htmlButton
	 * @return
	 */
	Button button(org.nasdanika.html.Button htmlButton);
	
	Form form();
	
	/**
	 * Wraps HTML form.
	 * @param form
	 * @return
	 */
	Form form(org.nasdanika.html.Form form);
	
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
	<T extends HTMLElement<?>> T popover(T element, Placement placement, String title, String text);
	
	/**
	 * Adds tooltip to the element. 
	 * This method does not initialize the tooltip, it shall be done through JavaScript <code>$(selector).tooltip();</code> e.g.
	 * <code>$('#my_button').tooltip();</code>
	 * @param element Element
	 * @param placement tooltip placement.
	 * @param text tooltip text
	 * @return
	 */
	<T extends HTMLElement<?>> T tooltip(T element, Placement placement, String text);	
	
	Tag glyphicon(Bootstrap.Glyphicon glyphicon);
	
	Modal modal();
	
	/**
	 * Generates document.title = title script.
	 * @param title
	 * @return
	 */
	Tag title(Object title);
		
	Breadcrumbs breadcrumbs();
	
	ButtonGroup buttonGroup(Button... buttons);
	
	ButtonToolbar buttonToolbar(ButtonGroup... buttonGroups);
	
	Carousel carousel();
		
	Well well(Object... content);
	
	Dropdown<?> dropdown(HTMLElement<?> toggle);
	
	/**
	 * Dropdown with the caret-down toggle.
	 * @return
	 */
	Dropdown<?> caretDropdown();
	
	Row style(Row row, Bootstrap.Style style);

	<T extends HTMLElement<?>> T bootstrapColor(T htmlElement, Bootstrap.Color color);

	
}
