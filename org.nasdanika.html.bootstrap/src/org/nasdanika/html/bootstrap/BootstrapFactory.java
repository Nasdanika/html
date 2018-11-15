package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.InputBase;
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
	
	FormGroup formGroup(Object label, InputBase<?> input, Object hint);
	
	<H extends HTMLElement<?>> H tooltip(H htmlElement, Object tooltip, boolean html, Placement placement);
	
	<B extends BootstrapElement<?,?>> B tooltip(B bootstrapElement, Object tooltip, boolean html, Placement placement);
	
	/**
	 * @return Script to initialize tooltips.
	 */
	Tag initTooltipScript();
	
	Navs tabs();
	
	Navs pills();
	
	Container container();
	
	Container container(Tag div);
	
	Container fluidContainer();
	
	Container fluidContainer(Tag div);
	
	
		
	/* TODO - Implement, add factory method and remove
	 * Navs - tabs, pills
	 * Navbar
	 * Modal

	 * TODO - create issues to implement in later versions (Bootstrap category).
	 * Pagination
	 * Jumbotron
	 * List group
	 * Popover
	 * Progress
	 * Scrollspy
	 * Card
	 * Carousel
	 * Collapse
	 *  
	 *  Other TODO's - load(Map) - to load configuration from, say, YAML files. For both HTML and Bootstrap elements.
	 */
	
}
