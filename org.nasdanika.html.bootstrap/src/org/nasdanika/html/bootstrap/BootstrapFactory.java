package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
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
	
	Tag alert(Color color, Object... content);
	
	Tag badge(boolean pill, Color color, Object... content);
	Tag badge(boolean pill, Object... content);
	Tag badgeLink(Object href, boolean pill, Color color, Object... content);
	
	Breadcrumbs breadcrums();
	
	/**
	 * Wraps HTML element into a Bootstrap button.
	 * @param htmlElement
	 * @return
	 */
	<H extends HTMLElement<?>> Button<H> button(H htmlElement, Color color, boolean outline);
	
	ButtonGroup buttonGroup(boolean vertical);
	
	ButtonToolbar buttonToolbar();
	
	Dropdown dropdown(Button<?> button, boolean split, Direction direction);

	InputGroup inputGroup();
	
	Table table(org.nasdanika.html.Table htmlTable);
	
	/**
	 * Wraps HTML row.
	 * @param row
	 * @return
	 */
	RowContainer.Row row(org.nasdanika.html.RowContainer.Row htmlRow);

	/**
	 * Wraps HTML cell.
	 * @param htmlCell
	 * @return
	 */
	RowContainer.Row.Cell cell(org.nasdanika.html.RowContainer.Row.Cell htmlCell);
	
	
	/**
	 * Creates a new HTML Table, wraps it into Bootstrap table and returns.
	 * @return
	 */
	Table table();
	
		
	/* TODO - Implement, add factory method and remove
	 * Form
	 * Jumbotron
	 * List group
	 * Modal
	 * Navs - tabs, pills
	 * Navbar
	 * Pagination
	 * Tooltip
	 * 
	 * Popover
	 * Progress
	 * Scrollspy
	 * Card
	 * Carousel
	 * Collapse
	 *  
	 */
	
}
