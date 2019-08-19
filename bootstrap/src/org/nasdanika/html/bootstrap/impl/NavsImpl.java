package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.Navs;

public class NavsImpl extends WrappingBootstrapElementImpl<Tag,Navs> implements Navs {
	
	private static final String NAV_TABS = "nav-tabs";
	private static final String NAV_PILLS = "nav-pills";
	private Fragment fragment;
	private Tag contentDiv;
	private boolean hasContent;

	protected NavsImpl(BootstrapFactory factory) {
		super(factory, factory.getHTMLFactory().nonEmptyDiv());
		htmlElement.addClass("nav");
		
		contentDiv = factory.getHTMLFactory().nonEmptyDiv().addClass("tab-content");
		fragment = factory.getHTMLFactory().fragment(htmlElement, contentDiv);
	}

	@Override
	public void item(Object name, Object content) {
		item(name, isEmpty(), false, null, content);
		
	}

	@Override
	public boolean isEmpty() {
		return htmlElement.isEmpty();
	}

	@Override
	public Navs item(Object name, boolean active, boolean disabled, Object contentId, Object... content) {
		if (contentId == null) {
			contentId = "nav-"+getFactory().getHTMLFactory().nextId();
		}
		Tag navLink = getFactory().getHTMLFactory().link("#"+contentId, name)
				.addClass("nav-item", "nav-link")
				.addClassConditional(active, "active")
				.attribute("data-toggle", "tab")
				.addClassConditional(disabled, "disabled")
				.attribute("role", "tab");
		htmlElement.content(navLink);

		Tag cDiv = getFactory().getHTMLFactory().nonEmptyDiv(content)
				.id(contentId)
				.attribute("role", "tabpanel")
				.addClass("tab-pane", "fade")
				.addClassConditional(active, "show", "active");
		
		if (!cDiv.isEmpty()) {
			htmlElement.attribute("role", "tablist");
			hasContent = true;
		}
				
		contentDiv.content(cDiv);		
		return this;
	}
	
	@Override
	public Tag item(Object name, Object href, boolean active, boolean disabled) {
		Tag navLink = getFactory().getHTMLFactory().link(href, name)
				.addClass("nav-item", "nav-link")
				.addClassConditional(active, "active")
				.addClassConditional(disabled, "disabled");
		htmlElement.content(navLink);
		return navLink;
	}
	
	@Override
	public Dropdown dropdown(boolean active, Object... name) {
		HTMLFactory htmlFactory = getFactory().getHTMLFactory();
		Tag toggle = htmlFactory.link("#", name)
				.addClass("nav-link", "dropdown-toggle")
				.attribute("role", "button")
				.attribute("data-toggle", "dropdown");		
		DropdownMenu menu = new DropdownMenu(getFactory());
		Tag li = htmlFactory.tag(TagName.li, toggle, menu.toHTMLElement())
				.addClass("nav-item", "dropdown")
				.addClassConditional(active, "active");
		htmlElement.content(li); 
		return menu;
	}	

	@Override
	public Object produce(int indent) {
		return (hasContent ? fragment : htmlElement).produce(indent);
	}
	
	@Override
	public String toString() {
		return (hasContent ? fragment : htmlElement).toString();
	}

	@Override
	public Tag getContentDiv() {
		return contentDiv;
	}

	@Override
	public Navs tabs(boolean tabs) {
		htmlElement.addClassConditional(tabs, NAV_TABS);
		return this;
	}

	@Override
	public Navs pills(boolean pills) {
		htmlElement.addClassConditional(pills, NAV_PILLS);
		return this;
	}

}
