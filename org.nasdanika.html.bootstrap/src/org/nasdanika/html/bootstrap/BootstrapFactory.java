package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.Select;
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
	
	/**
	 * Wraps HTML element for applyng Bootstrap styling.
	 * @param htmlElement
	 * @return
	 */
	<H extends HTMLElement<?>> BootstrapElement<H, BootstrapElement<H, ?>> wrap(H htmlElement);
	
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
	
	/**
	 * Adds bootstrap required meta tags to the page.
	 * @param htmlPage
	 * @return
	 */
	<P extends HTMLPage> P bootstrapHTMLPage(P htmlPage);

	/**
	 * Adds bootstrap required meta tags and stylesheet and script tags to load
	 * resources from CDN.
	 * @param htmlPage
	 * @return
	 */
	<P extends HTMLPage> P bootstrapCdnHTMLPage(P htmlPage);
	
	/**
	 * Adds bootstrap required meta tags, default or Bootswatch stylesheet and script tags to load
	 * resources from CDN.
	 * @param htmlPage
	 * @param theme Bootswatch theme, uses default theme is null or Default.
	 * @return
	 */
	<P extends HTMLPage> P bootstrapCdnHTMLPage(P htmlPage, Theme theme);		
	
	/**
	 * Creates an HTML page with meta tags required by Bootstrap.
	 * @return
	 */
	HTMLPage bootstrapHTMLPage();
	
	/**
	 * Creates an HTML page with Bootstrap stylesheet and scripts served from CDN.
	 * @return
	 */
	HTMLPage bootstrapCdnHTMLPage();
	
	/**
	 * Creates an HTML page with Bootstrap stylesheet and scripts served from CDN.
	 * @param theme Bootswatch theme. Uses the default Bootstrap theme if null or Default.
	 * @return
	 */
	HTMLPage bootstrapCdnHTMLPage(Theme theme);		
	
	/**
	 * Creates a select with themes as options and an event handler to update the application theme on change.  
	 * @return
	 */
	Select themeSelect(Theme selected);
	
	/**
	 * Adds "display-&lt;level&gt;" class.
	 * @param element
	 * @param level Display level from 1 (largest) to 4 (smallest).
	 * @return
	 */
	<H extends HTMLElement<?>> H display(H element, int level);
	
	/**
	 * Creates a navbar
	 * @param expandSize
	 * @param dark
	 * @param background
	 * @param brand
	 * @return
	 */
	Navbar navbar(DeviceSize expandSize, boolean dark, Color background, HTMLElement<?> brand);
	
	ListGroup listGroup(boolean flush);
	
	ActionGroup actionGroup(boolean flush);
		
	/* TODO - Implement, add factory method and remove
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
