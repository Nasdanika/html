package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Form;
import org.nasdanika.html.Fragment;
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
import org.nasdanika.html.bootstrap.Theme;

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
	public <P extends HTMLPage> P bootstrapHTMLPage(P page) {
		page
			.head(getHTMLFactory().tag(TagName.meta).attribute("charset", "utf-8"))
			.head(getHTMLFactory().tag(TagName.meta).attribute("name", "viewport").attribute("content", "width=device-width, initial-scale=1, shrink-to-fit=no"));
		return page;
	}
	
	@Override
	public <P extends HTMLPage> P bootstrapCdnHTMLPage(P page, Theme theme) {
		theme.cdn(page);
		bootstrapHTMLPage(page)
			.script("https://code.jquery.com/jquery-3.3.1.min.js")
			.script("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js")
			.script("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js");
		return page;
	}
	
	@Override
	public <P extends HTMLPage> P bootstrapCdnHTMLPage(P page) {
		return bootstrapCdnHTMLPage(page, Theme.Default);
	}
	
	@Override
	public HTMLPage bootstrapHTMLPage() {
		return bootstrapHTMLPage(getHTMLFactory().page());
	}
	
	@Override
	public HTMLPage bootstrapCdnHTMLPage(Theme theme) {
		return bootstrapCdnHTMLPage(bootstrapHTMLPage(), theme);
	}
	
	@Override
	public HTMLPage bootstrapCdnHTMLPage() {
		return bootstrapCdnHTMLPage(Theme.Default);
	}	
	
	/**
	 * Basic testing/demo, paste output to https://www.w3schools.com/bootstrap4/tryit.asp?filename=trybs_default&stacked=h body
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();
		
		Fragment content = htmlFactory.fragment();

		content.content(TagName.h1.create("Bootstrap demo"));
		
		content.content(TagName.h2.create("Alert"));
		
		content.content(factory.alert(Color.INFO, "Alert"));
		
		content.content(TagName.h2.create("Badge"));		
		content.content(factory.badge(true, Color.INFO, "Badge"));
		content.content(factory.badgeLink("#", false, Color.WARNING, "Badge link"));
		
		
		content.content(TagName.h2.create("Breadcrumbs"));
		
		Breadcrumbs breadcrumbs = factory.breadcrums();
		breadcrumbs.item("#", "First");
		breadcrumbs.item(null, "Last");
		content.content(breadcrumbs);

		
		content.content(TagName.h2.create("Button"));
		
		org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
		Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
		factory.tooltip(button, "I am a <I>tooltip</I>." , true, Placement.BOTTOM);
		content.content(button);				
		
		content.content(factory.initTooltipScript());
		
		
		content.content(TagName.h2.create("Button group"));
		
		ButtonGroup buttonGroup = factory.buttonGroup(false);
		buttonGroup.add(button);
		buttonGroup.add(button);
		content.content(buttonGroup);

		
		content.content(TagName.h2.create("Button toolbar"));
		
		ButtonToolbar toolbar = factory.buttonToolbar();
		toolbar.add(buttonGroup);
		content.content(toolbar);

		
		content.content(TagName.h2.create("Dropdown"));
		
		Dropdown dropdown = factory.dropdown(button, true, Direction.DOWN);
		dropdown.item("#", false, false, "Item 1");
		dropdown.header("Header");
		dropdown.item("#", true, false, "Item 1");
		dropdown.divider();
		dropdown.item("#", false, true, "Item 1");
		content.content(dropdown);

		
		content.content(TagName.h2.create("Input group"));
		
		InputGroup inputGroup = factory.inputGroup();
		Input input = htmlFactory.input(InputType.text);
		inputGroup.input(input);
		inputGroup.prepend("@");
		inputGroup.append("Zorro").large();
		content.content(inputGroup);
		
		content.content(TagName.h2.create("Table"));
		
		Table table = factory.table().striped();
		table.toHTMLElement().caption("My table");
		TableHeader header = table.header();
		header.headerRow("A", "B", "C");
		TableBody body = table.body();
		body.row("One", "Two", "Three");		
		content.content(table);
		
		content.content(TagName.h2.create("Form"));
		
		Form form = htmlFactory.form();
		
		form.content(factory.formGroup("Email address", htmlFactory.input(InputType.email).value("email@example.com"), "We'll never share").large().plainText());
		form.content(factory.formGroup("Password", htmlFactory.input(InputType.password).disabled(), null).small());
		form.content(factory.formGroup("Check me out", htmlFactory.input(InputType.checkbox), null).invalid("Oh, no"));

		form.content(factory.formGroup("1", htmlFactory.input(InputType.radio), null).inline());
		form.content(factory.formGroup("2", htmlFactory.input(InputType.radio), null).inline());
		form.content(factory.formGroup("3", htmlFactory.input(InputType.radio), null).inline());

		form.content(factory.formGroup("City", htmlFactory.input(InputType.text), "City").valid());
		form.content(factory.formGroup("State", htmlFactory.input(InputType.text), "State").invalid("No such state"));
		
		content.content(form);
		
		content.content(TagName.h2.create("Navs"));
		
		Navs navs = factory.tabs();
		navs.item("First", "First content");
		navs.item("Second", "Second content");
		navs.item("Third", "Third content");
		navs.item("Fourth", "Fourth content");
		
		content.content(navs);
		
		content.content(TagName.h2.create("Containers"));
		
		Container container = factory.container();
		container.row().col(content);
		org.nasdanika.html.bootstrap.Container.Row row = container.row();
		row.col("Col 1").border(Color.DARK).background(Color.WARNING).text().color(Color.PRIMARY);
		row.col("Col 2").border(Color.PRIMARY).text().weight(Weight.BOLD).alignment(Alignment.CENTER);
		row.col("Col 3").border(Color.WARNING, Placement.RIGHT).background(Color.SECONDARY).text().monospace();		
			
		// Bootstrap page
		HTMLPage bootstrapPage = factory.bootstrapCdnHTMLPage();//Theme.Cerulean);
		bootstrapPage.title("Bootstrap demo");
		bootstrapPage.body(container);
		
		System.out.println(bootstrapPage);
	}
	
}
