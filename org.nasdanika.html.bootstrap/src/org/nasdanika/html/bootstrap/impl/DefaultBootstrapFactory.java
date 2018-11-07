package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLFactory.InputType;
import org.nasdanika.html.Input;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumbs;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Direction;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.InputGroup;

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
		System.out.println(button);
		
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
	}
	
}
