package org.nasdanika.html.tests;

import java.util.Collections;
import java.util.Map;

import org.junit.Test;
import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputType;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumbs;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Direction;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.InputGroup;
import org.nasdanika.html.bootstrap.ListGroup;
import org.nasdanika.html.bootstrap.Navbar;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Table.TableBody;
import org.nasdanika.html.bootstrap.Table.TableHeader;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.bootstrap.Text.Weight;


public class TestBootstrap extends HTMLTestBase {
	
	@Test
	public void testAlert() throws Exception {
		writeThemedPage("bootstrap/alert.html", "Bootstrap alert", BootstrapFactory.INSTANCE.alert(Color.INFO, "Alert"));
	}
	
	@Test
	public void testBadge() throws Exception {
		writeThemedPage(
				"bootstrap/badge.html", 
				"Bootstrap badge", 
				BootstrapFactory.INSTANCE.badge(true, Color.INFO, "Badge"),
				BootstrapFactory.INSTANCE.badgeLink("#", false, Color.WARNING, "Badge link"));
	}
	
	@Test
	public void testBreadcrumbs() throws Exception {
		Breadcrumbs breadcrumbs = BootstrapFactory.INSTANCE.breadcrums();
		breadcrumbs.item(false, breadcrumbs.getFactory().getHTMLFactory().link("#", "First"));
		breadcrumbs.item(true, "Last");
		writeThemedPage("bootstrap/breadcrumbs.html", "Bootstrap breadcrumbs", breadcrumbs); 
	}
		
	@Test
	public void testCards() throws Exception {
		Card card = BootstrapFactory.INSTANCE.card().border(Color.SUCCESS);		
		card.getTitle().toHTMLElement().content("Header");
		card.getBody().toHTMLElement().content("Body");
		card.getFooter().toHTMLElement().content("Footer");		
		writeThemedPage("bootstrap/card.html", "Bootstrap card", card); 
	}	
		
	@Test
	public void testButton() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();
		org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
		Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
		writeThemedPage("bootstrap/button.html", "Bootstrap button", button); 
	}
		
	@Test
	public void testTooltip() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();
		org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
		Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
		factory.tooltip(button, "I am a <I>tooltip</I>." , true, Placement.BOTTOM);
		writeThemedPage("bootstrap/tooltip.html", "Bootstrap tooltip", button, factory.initTooltipScript()); 
	}
	
	@Test
	public void testButtonGroup() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();
		org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
		Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
		
		ButtonGroup buttonGroup = factory.buttonGroup(false);
		buttonGroup.add(button);
		buttonGroup.add(button);
		
		writeThemedPage("bootstrap/button-group.html", "Bootstrap group", buttonGroup); 
	}
	
	@Test
	public void testButtonToolbar() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();
		org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
		Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
		
		ButtonGroup buttonGroup = factory.buttonGroup(false);
		buttonGroup.add(button);
		buttonGroup.add(button);
		
		ButtonToolbar toolbar = factory.buttonToolbar();
		toolbar.add(buttonGroup);
		
		writeThemedPage("bootstrap/button-toolbar.html", "Bootstrap toolbar", toolbar); 
	}
	
	@Test
	public void testDropdown() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();
		org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
		Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
		
		Dropdown dropdown = factory.dropdown(button, true, Direction.DOWN);
		dropdown.item(htmlFactory.link("#", "Item 1"), false, false);
		dropdown.header("Header");
		dropdown.item(htmlFactory.link("#", "Item 2"), true, false);
		dropdown.divider();
		dropdown.item(htmlFactory.link("#", "Item 3"), false, true);
				
		writeThemedPage("bootstrap/dropdown.html", "Bootstrap dropdown", dropdown); 
	}
	
	@Test
	public void testInputGroup() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();

		InputGroup inputGroup = factory.inputGroup();
		Input input = htmlFactory.input(InputType.text);
		inputGroup.input(input);
		inputGroup.prepend("@");
		inputGroup.append("Zorro").large();
				
		writeThemedPage("bootstrap/input-group.html", "Bootstrap input group", inputGroup); 
	}
	
	@Test
	public void testTable() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;

		Table table = factory.table().striped();
		table.toHTMLElement().caption("My table");
		TableHeader header = table.header();
		header.headerRow("A", "B", "C");
		TableBody body = table.body();
		body.row("One", "Two", "Three");		
				
		writeThemedPage("bootstrap/table.html", "Bootstrap table", table); 
	}
	
	@Test
	public void testForm() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();

		Form form = htmlFactory.form();
		
		Map<Breakpoint, Integer> horizontalLabelWidths = null;
		form.content(factory.formGroup(
				"Email address", 
				htmlFactory.input(InputType.email).value("email@example.com"), 
				"We'll never share", 
				horizontalLabelWidths).large().plainText());
		form.content(factory.formGroup("Password", htmlFactory.input(InputType.password).disabled(), null, horizontalLabelWidths).small());
		form.content(factory.formGroup("Check me out", htmlFactory.input(InputType.checkbox), null, horizontalLabelWidths).invalid("Oh, no"));

		form.content(factory.formGroup("1", htmlFactory.input(InputType.radio), null, horizontalLabelWidths).inline());
		form.content(factory.formGroup("2", htmlFactory.input(InputType.radio), null, horizontalLabelWidths).inline());
		form.content(factory.formGroup("3", htmlFactory.input(InputType.radio), null, horizontalLabelWidths).inline());

		form.content(factory.formGroup("City", htmlFactory.input(InputType.text), "City", horizontalLabelWidths).valid());
		form.content(factory.formGroup("State", htmlFactory.input(InputType.text), "State", horizontalLabelWidths).invalid("No such state"));
				
		writeThemedPage("bootstrap/form.html", "Bootstrap form", form); 
	}
		
	@Test
	public void testHorizontalForm() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();

		Form form = htmlFactory.form();
		
		Map<Breakpoint, Integer> horizontalLabelWidths = Collections.singletonMap(Breakpoint.SMALL, 2);
		form.content(factory.formGroup(
				"Email address", 
				htmlFactory.input(InputType.email).value("email@example.com"), 
				"We'll never share", 
				horizontalLabelWidths).large().plainText());
		form.content(factory.formGroup("Password", htmlFactory.input(InputType.password).disabled(), null, horizontalLabelWidths).small());
		form.content(factory.formGroup("Check me out", htmlFactory.input(InputType.checkbox), "Do not click if not sure", horizontalLabelWidths));

		form.content(factory.formGroup("1", htmlFactory.input(InputType.radio), null, horizontalLabelWidths));
		form.content(factory.formGroup("2", htmlFactory.input(InputType.radio), null, horizontalLabelWidths));
		form.content(factory.formGroup("3", htmlFactory.input(InputType.radio), null, horizontalLabelWidths));

		form.content(factory.formGroup("City", htmlFactory.input(InputType.text), "City", horizontalLabelWidths).valid());
		form.content(factory.formGroup("State", htmlFactory.input(InputType.text), "State", horizontalLabelWidths).invalid("No such state"));
				
		writeThemedPage("bootstrap/horizontal-form.html", "Bootstrap horizontal form", form); 
	}
	
	@Test
	public void testNavs() throws Exception {
		Navs simpleTabs = BootstrapFactory.INSTANCE.navs().tabs();
		simpleTabs.item("First", "First content");
		simpleTabs.item("Second", "Second content");
		simpleTabs.item("Third", "Third content");
		simpleTabs.item("Fourth", "Fourth content");
				
		writeThemedPage("bootstrap/simple-tabs.html", "Bootstrap simple tabs", simpleTabs);
		
		writeThemedPage("bootstrap/navs.html", "Bootstrap navs", navigationNavsItems(BootstrapFactory.INSTANCE.navs()));
		writeThemedPage("bootstrap/tabs.html", "Bootstrap tabs", navsItems(BootstrapFactory.INSTANCE.navs().tabs(), true));
		writeThemedPage("bootstrap/pills.html", "Bootstrap pills", navsItems(BootstrapFactory.INSTANCE.navs().pills(), true));
		
		Navs verticalPills = navsItems(BootstrapFactory.INSTANCE.navs().pills(), false);
		verticalPills.toHTMLElement().addClass("flex-column");		
		Container container = BootstrapFactory.INSTANCE.container();
		Row row = container.row();
		row.col(verticalPills.toHTMLElement()).width(Breakpoint.DEFAULT, Size.AUTO);
		row.col(verticalPills.getContentDiv());
		writeThemedPage("bootstrap/vertical-pills.html", "Bootstrap vertical pills", container);
	}

	private Navs navsItems(Navs navs, boolean withDropdown) {
		navs.item("First", false, false, null, "First content");
		navs.item("Second", true, false, null, "Second content");
		navs.item("Third", false, true, null, "Third content");
		navs.item("Fourth", "Fourth content");
		if (withDropdown) {
			HTMLFactory htmlFactory = navs.getFactory().getHTMLFactory();
			Dropdown dropdown = navs.dropdown(false, "Fith");
			dropdown.item(htmlFactory.link("#", "Item 1"), false, false);
			dropdown.header("Header");
			dropdown.item(htmlFactory.link("#", "Item 2"), true, false);
			dropdown.divider();
			dropdown.item(htmlFactory.link("#", "Item 3"), false, true);
		}
		
		return navs;
	}
	
	private Navs navigationNavsItems(Navs navs) {
		navs.item("Codegen", "https://www.nasdanika.org/home/products/codegen/index.html", false, false);
		navs.item("Rigel", "https://www.nasdanika.org/home/products/rigel/index.html", true, false);
		navs.item("Sage", "https://www.nasdanika.org/home/products/sage/index.html", false, true);
		navs.item("Core", "https://www.nasdanika.org/home/products/core/index.html", false, false);

		HTMLFactory htmlFactory = navs.getFactory().getHTMLFactory();
		Dropdown dropdown = navs.dropdown(false, "HTML");
		dropdown.item(htmlFactory.link("https://www.nasdanika.org/home/products/html/bootstrap.html", "Bootstrap"), false, false);
		dropdown.header("Header");
		dropdown.item(htmlFactory.link("https://www.nasdanika.org/home/products/html/app.html", "Application"), true, false);
		dropdown.divider();
		dropdown.item(htmlFactory.link("https://www.nasdanika.org/home/products/html/jstree.html", "JsTree"), false, true);
		
		return navs;
	}
	
	
	@Test
	public void testNavbar() throws Exception {		
		Tag brand = HTMLFactory.INSTANCE.link("#", "Nasdanika");
		Navbar navbar = BootstrapFactory.INSTANCE.navbar(Breakpoint.LARGE, false, Color.LIGHT, brand);
		navbar.item("#", true, false, "Item 1");
		navbar.item("#", false, false, "Item 2");
		navbar.item("#", false, true, "Item 3");
		
		Dropdown dropdown = navbar.dropdown(false, "Dropdown 4");
		dropdown.item(HTMLFactory.INSTANCE.link("#", "Item 1"), false, false);
		dropdown.header("Header");
		dropdown.item(HTMLFactory.INSTANCE.link("#", "Item 2"), true, false);
		dropdown.divider();
		dropdown.item(HTMLFactory.INSTANCE.link("#", "Item 3"), false, true);
		
		navbar.navbarText("Some text");
				
		writeThemedPage("bootstrap/navbar.html", "Bootstrap navbar", navbar); 
	}
		
	@Test
	public void testListGroup() throws Exception {		
		ListGroup listGroup = BootstrapFactory.INSTANCE.listGroup(false);
		
		listGroup.item(false, false, Color.DEFAULT, "One");
		listGroup.item(true, false, Color.DEFAULT, "Two - active");
		listGroup.item(false, true, Color.DEFAULT, "Three - disabled");
		listGroup.item(false, false, Color.WARNING, "Four - warning");
						
		writeThemedPage("bootstrap/list-group.html", "Bootstrap list group", listGroup); 
	}	
	
	@Test
	public void testBackgroundColors() throws Exception {		
		ListGroup listGroup = BootstrapFactory.INSTANCE.listGroup(false);
		
		for (Color color: Color.values()) {
			listGroup.item(false, false, color, color.name());
		}						
		writeThemedPage("bootstrap/background-colors.html", "Bootstrap background colors", listGroup); 
	}	
		
	@Test
	public void testActionGroupInNavs() throws Exception {
		ActionGroup actionGroup = BootstrapFactory.INSTANCE.actionGroup(false);
		
		actionGroup.contentAction("One", false, false, Color.DEFAULT, null, "First content");
		actionGroup.contentAction("Two - active", true, false, Color.DEFAULT, "action-xyz", "Active content");
		actionGroup.contentAction("Three - disabled", false, true, Color.DEFAULT, null, "Disabled content");
		actionGroup.contentAction("Four - warning", false, false, Color.WARNING, null, BootstrapFactory.INSTANCE.alert(Color.WARNING, "Be careful!"));
		
		Navs simpleTabs = BootstrapFactory.INSTANCE.navs().tabs();
		simpleTabs.item("First", actionGroup.asContainer(true).margin().top(Breakpoint.DEFAULT, Size.S1).toBootstrapElement());
		simpleTabs.item("Second", "Second content");
		simpleTabs.item("Third", "Third content");
		simpleTabs.item("Fourth", "Fourth content");
				
		writeThemedPage("bootstrap/content-action-group-in-tabs.html", "Bootstrap content action group in tabs", simpleTabs);		
	}
	
		
	@Test
	public void testFlushListGroup() throws Exception {		
		ListGroup listGroup = BootstrapFactory.INSTANCE.listGroup(true);
		
		listGroup.item(false, false, Color.DEFAULT, "One");
		listGroup.item(true, false, Color.DEFAULT, "Two - active");
		listGroup.item(false, true, Color.DEFAULT, "Three - disabled");
		listGroup.item(false, false, Color.WARNING, "Four - warning");
						
		writeThemedPage("bootstrap/list-group-flush.html", "Bootstrap flush list group", listGroup); 
	}	
	
	@Test
	public void testActionGroup() throws Exception {		
		ActionGroup actionGroup = BootstrapFactory.INSTANCE.actionGroup(false);
		
		actionGroup.action(false, false, Color.DEFAULT, "#", "One");
		actionGroup.action(true, false, Color.DEFAULT, "#", "Two - active");
		actionGroup.action(false, true, Color.DEFAULT, "#", "Three - disabled");
		actionGroup.action(false, false, Color.WARNING, "#", "Four - warning");
						
		writeThemedPage("bootstrap/action-group.html", "Bootstrap action group", actionGroup); 
	}	
		
	@Test
	public void testContentActionGroup() throws Exception {		
		ActionGroup actionGroup = BootstrapFactory.INSTANCE.actionGroup(false);
		
		actionGroup.contentAction("One", false, false, Color.DEFAULT, null, "First content");
		actionGroup.contentAction("Two - active", true, false, Color.DEFAULT, "action-xyz", "Active content");
		actionGroup.contentAction("Three - disabled", false, true, Color.DEFAULT, null, "Disabled content");
		actionGroup.contentAction("Four - warning", false, false, Color.WARNING, null, BootstrapFactory.INSTANCE.alert(Color.WARNING, "Be careful!"));
						
		writeThemedPage("bootstrap/action-group-content.html", "Bootstrap content action group", actionGroup.asContainer(true)); 
	}	
		
	@Test
	public void testMisc() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		
		Container container = factory.container();
		container.row().col("Some row content");
		org.nasdanika.html.bootstrap.Container.Row row = container.row();
		row.col("Col 1").border(Color.DARK).background(Color.WARNING).text().color(Color.PRIMARY);
		row.col("Col 2").border(Color.PRIMARY).text().weight(Weight.BOLD).alignment(Alignment.CENTER);
		row.col("Col 3").border(Color.WARNING, Placement.RIGHT).background(Color.SECONDARY).text().monospace();		
				
		writeThemedPage("bootstrap/misc.html", "Bootstrap misc", container); 
	}
	
}
