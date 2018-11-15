package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.InputType;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumbs;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Direction;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.FormGroup;
import org.nasdanika.html.bootstrap.InputGroup;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Table.TableBody;
import org.nasdanika.html.bootstrap.Table.TableHeader;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.bootstrap.Text.Weight;

/**
 * HTML factory which relies on Bootstrap styles and scripts.
 * @author Pavel
 *
 */
public class DefaultBootstrapFactory implements BootstrapFactory {
	
	private HTMLFactory htmlFactory;

	public DefaultBootstrapFactory(HTMLFactory htmlFactory) {
		this.htmlFactory = htmlFactory;
	}
	
	@Override
	public HTMLFactory getHTMLFactory() {
		return htmlFactory;
	}

	@Override
	public Tag alert(Color color, Object... content) {
		return getHTMLFactory().div(content)
				.addClass("alert")
				.addClass("alert-"+color.code)
				.attribute("role", "alert");
	}

	@Override
	public Tag badge(boolean pill, Color color, Object... content) {
		Tag ret = getHTMLFactory().span(content)
				.addClass("badge")
				.addClass("badge-"+color.code)
				.addClassConditional(pill, "badge-pill");
		return ret;
	}

	@Override
	public Tag badge(boolean pill, Object... content) {
		Tag ret = getHTMLFactory().span(content)
				.addClass("badge")
				.addClassConditional(pill, "badge-pill");
		return ret;
	}

	@Override
	public Tag badgeLink(Object href, boolean pill, Color color, Object... content) {
		Tag ret = getHTMLFactory().link(href, content)
				.addClass("badge")
				.addClass("badge-"+color.code)
				.addClassConditional(pill, "badge-pill");
		return ret;
	}

	@Override
	public Breadcrumbs breadcrums() {
		return new BreadcrumbsImpl(this);
	}

	@Override
	public <H extends HTMLElement<?>> Button<H> button(H htmlElement, Color color, boolean outline) {
		return new ButtonImpl<H>(this, htmlElement, color, outline);
	}

	@Override
	public ButtonGroup buttonGroup(boolean vertical) {
		return new ButtonGroupImpl(this, vertical);
	}

	@Override
	public ButtonToolbar buttonToolbar() {
		return new ButtonToolbarImpl(this);
	}

	@Override
	public Dropdown dropdown(Button<?> button, boolean split, Direction direction) {
		return new DropdownImpl(this, button, split, direction);
	}
	
	@Override
	public InputGroup inputGroup() {
		return new InputGroupImpl(this);
	}
	
	@Override
	public Table table(org.nasdanika.html.Table htmlTable) {
		return new TableImpl(this, htmlTable);
	}
	
	@Override
	public Table table() {
		return table(htmlFactory.table());
	}
	
	@Override
	public Row row(org.nasdanika.html.RowContainer.Row htmlRow) {
		return new RowImpl(this, htmlRow);
	}
	
	@Override
	public Cell cell(org.nasdanika.html.RowContainer.Row.Cell htmlCell) {
		return new CellImpl(this, htmlCell);
	}
	
	@Override
	public FormGroup formGroup(Object label, InputBase<?> input, Object hint) {
		return new FormGroupImpl(this, label, input, hint);
	}
	
	@Override
	public <B extends BootstrapElement<?,?>> B tooltip(B bootstrapElement, Object tooltip, boolean html, Placement placement) {
		tooltip(bootstrapElement.toHTMLElement(), tooltip, html, placement);
		return bootstrapElement;
	}
	
	@Override
	public <H extends HTMLElement<?>> H tooltip(H htmlElement, Object tooltip, boolean html, Placement placement) {
		htmlElement
			.attribute("data-toggle", "tooltip")
			.attribute("data-placement", placement.name().toLowerCase())			
			.attribute("data-html", "true", html)
			.attribute("title", tooltip);		
		return htmlElement;
	}
	
	@Override
	public Tag initTooltipScript() {
		return htmlFactory.nonEmptyTag(TagName.script, "$(function () { $('[data-toggle=\"tooltip\"]').tooltip(); });");
	}
	
	@Override
	public Navs pills() {
		return new NavsImpl(this, true);
	}
	
	@Override
	public Navs tabs() {
		return new NavsImpl(this, false);
	}
	
	@Override
	public Container container() {
		return container(htmlFactory.div());
	}

	@Override
	public Container container(Tag div) {
		return new ContainerImpl(this, div, false);
	}

	@Override
	public Container fluidContainer() {
		return fluidContainer(htmlFactory.div());
	}

	@Override
	public Container fluidContainer(Tag div) {
		return new ContainerImpl(this, div, true);
	}
	
	@Override
	public HTMLPage bootstrapHTMLPage() {
		HTMLPage page = getHTMLFactory().page();
		page.head(getHTMLFactory().tag(TagName.meta).attribute("charset", "utf-8"));
		page.head(getHTMLFactory().tag(TagName.meta).attribute("name", "viewport").attribute("content", "width=device-width, initial-scale=1, shrink-to-fit=no"));
		return page;
	}
	
	@Override
	public HTMLPage bootstrapCdnHTMLPage() {
		HTMLPage page = bootstrapHTMLPage();
		page.stylesheet("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css");
		page.script("https://code.jquery.com/jquery-3.3.1.slim.min.js");
		page.script("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js");
		page.script("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js");
		return page;
	}
	
	
	/**
	 * Basic testing/demo, paste output to https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_default&stacked=h body
	 * @param args
	 */
	public static void main(String[] args) {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();
		
		System.out.println(factory.alert(Color.INFO, "Alert"));
		System.out.println(factory.badge(true, Color.INFO, "Badge"));
		System.out.println(factory.badgeLink("#", false, Color.WARNING, "Badge link"));
		
		Breadcrumbs breadcrumbs = factory.breadcrums();
		breadcrumbs.item("#", "First");
		breadcrumbs.item(null, "Last");
		System.out.println(breadcrumbs);

		org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
		Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
		factory.tooltip(button, "I am a <I>tooltip</I>." , true, Placement.BOTTOM);
		System.out.println(button);				
		
		System.out.println(factory.initTooltipScript());
		
		ButtonGroup buttonGroup = factory.buttonGroup(false);
		buttonGroup.add(button);
		buttonGroup.add(button);
		System.out.println(buttonGroup);
		
		ButtonToolbar toolbar = factory.buttonToolbar();
		toolbar.add(buttonGroup);
		System.out.println(toolbar);
		
		Dropdown dropdown = factory.dropdown(button, true, Direction.DOWN);
		dropdown.item("#", false, false, "Item 1");
		dropdown.header("Header");
		dropdown.item("#", true, false, "Item 1");
		dropdown.divider();
		dropdown.item("#", false, true, "Item 1");
		System.out.println(dropdown);
		
		InputGroup inputGroup = factory.inputGroup();
		Input input = htmlFactory.input(InputType.text);
		inputGroup.input(input);
		inputGroup.prepend("@");
		inputGroup.append("Zorro").large();
		System.out.println(inputGroup);
		
		// Tables
		
		Table table = factory.table().striped();
		table.toHTMLElement().caption("My table");
		TableHeader header = table.header();
		header.headerRow("A", "B", "C");
		TableBody body = table.body();
		body.row("One", "Two", "Three");		
		System.out.println(table);
		
		// Forms
		
		Form form = htmlFactory.form();
		
		form.content(factory.formGroup("Email address", htmlFactory.input(InputType.email).value("email@example.com"), "We'll never share").large().plainText());
		form.content(factory.formGroup("Password", htmlFactory.input(InputType.password).disabled(), null).small());
		form.content(factory.formGroup("Check me out", htmlFactory.input(InputType.checkbox), null).invalid("Oh, no"));

		form.content(factory.formGroup("1", htmlFactory.input(InputType.radio), null).inline());
		form.content(factory.formGroup("2", htmlFactory.input(InputType.radio), null).inline());
		form.content(factory.formGroup("3", htmlFactory.input(InputType.radio), null).inline());

		form.content(factory.formGroup("City", htmlFactory.input(InputType.text), "City").valid());
		form.content(factory.formGroup("State", htmlFactory.input(InputType.text), "State").invalid("No such state"));
		
		System.out.println(form);
		
		// Navs		
		Navs navs = factory.tabs();
		navs.item("First", "First content");
		navs.item("Second", "Second content");
		navs.item("Third", "Third content");
		navs.item("Fourth", "Fourth content");
		
		System.out.println(navs);
		
		// Containers		
		Container container = factory.container();
		org.nasdanika.html.bootstrap.Container.Row row = container.row();
		row.col("Col 1").border(Color.DARK).background(Color.WARNING).text().color(Color.PRIMARY);
		row.col("Col 2").border(Color.PRIMARY).text().weight(Weight.BOLD).alignment(Alignment.CENTER);
		row.col("Col 3").border(Color.WARNING, Placement.RIGHT).background(Color.SECONDARY).text().monospace();		
			
		System.out.println(container);
		
		// HTML page
		HTMLPage page = factory.getHTMLFactory().page();
		page.head(factory.getHTMLFactory().tag(TagName.meta).attribute("charset", "utf-8"));
		page.head(factory.getHTMLFactory().tag(TagName.meta).attribute("name", "viewport").attribute("content", "width=device-width, initial-scale=1, shrink-to-fit=no"));
		
		page.title("Demo");
		page.stylesheet("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css");
		page.script("https://code.jquery.com/jquery-3.3.1.slim.min.js");
		page.script("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js");
		page.script("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js");
		
		page.body(container);
		
		System.out.println(page);
		
		// Bootstrap page
		HTMLPage bootstrapPage = factory.bootstrapCdnHTMLPage();
		bootstrapPage.title("Bootstrap demo");
		bootstrapPage.body(container);		
		System.out.println(bootstrapPage);
		
		
	}
	
}
