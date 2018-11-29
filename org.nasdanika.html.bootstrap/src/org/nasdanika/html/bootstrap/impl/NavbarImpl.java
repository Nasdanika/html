package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Button;
import org.nasdanika.html.Button.Type;
import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.Navbar;

public class NavbarImpl extends WrappingBootstrapElementImpl<Tag, Navbar> implements Navbar {

	protected HTMLElement<?> brand;
	protected Tag items;

	public NavbarImpl(
			BootstrapFactory factory,
			DeviceSize expandSize, 
			boolean dark,
			Color background, 
			HTMLElement<?> brand) {
		super(factory, factory.getHTMLFactory().tag(TagName.nav).addClass("navbar"));
		htmlElement
			.addClass("navbar-expand-"+expandSize.code)
			.addClass(dark ? "navbar-dark" : "navbar-light")
			.addClassConditional(background !=null && background.code != null, "bg-"+background.code);
		
		this.brand = brand;
		if (brand != null) {
			brand.addClass("navbar-brand");
			htmlElement.content(brand);
		}
		
		HTMLFactory htmlFactory = factory.getHTMLFactory();		
		String collapseDivId = htmlFactory.nextId();
		
		Button toggler = htmlFactory.button(htmlFactory.span().addClass("navbar-toggler-icon"));
		toggler
			.type(Type.BUTTON)
			.addClass("navbar-toggler")
			.attribute("data-toggle", "collapse")
			.attribute("data-target", "#"+collapseDivId);
		htmlElement.content(toggler);		
		
		items = htmlFactory.tag(TagName.ul).addClass("navbar-nav");
		Tag collapseDiv = htmlFactory.div(items).addClass("collapse", "navbar-collapse").id(collapseDivId);
		htmlElement.content(collapseDiv);
	}

	@Override
	public Tag item(Object href, boolean active, boolean disabled, Object... content) {
		HTMLFactory htmlFactory = getFactory().getHTMLFactory();
		Tag link = htmlFactory.link(href, content).attribute("disabled", disabled).addClass("nav-link"); 
		Tag li = htmlFactory.tag(TagName.li, link).addClass("nav-item").addClassConditional(active, "active");
		items.content(li);
		return li;
	}

	@Override
	public Dropdown dropdown(Object... name) {
		HTMLFactory htmlFactory = getFactory().getHTMLFactory();
		Tag toggle = htmlFactory.link("#", name)
				.addClass("nav-link", "dropdown-toggle")
				.attribute("role", "button")
				.attribute("data-toggle", "dropdown");		
		DropdownMenu menu = new DropdownMenu(getFactory());
		items.content(htmlFactory.tag(TagName.li, toggle, menu.toHTMLElement()).addClass("nav-item", "dropdown")); 
		return menu;
	}

	@Override
	public Navbar form(Form form) {
		form.addClass("form-inline");
		items.content(form);
		return this;
	}

	@Override
	public Tag navbarText(Object... content) {
		Tag span = getFactory().getHTMLFactory().span(content).addClass("navbar-text");
		items.content(span);
		return span;
	}

}
