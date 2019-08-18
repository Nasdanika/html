package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.Navs;

public class NavsImpl extends WrappingBootstrapElementImpl<Tag,Navs> implements Navs {
	
	private Fragment fragment;
	private Tag contentDiv;

	protected NavsImpl(BootstrapFactory factory, boolean tabs, boolean pills) {
		super(factory, factory.getHTMLFactory().nonEmptyDiv());
		htmlElement
			.addClass("nav")
			.addClassConditional(pills, "nav-pills")
			.addClassConditional(tabs, "nav-tabs")
			.attribute("role", "tablist");
		
		contentDiv = factory.getHTMLFactory().nonEmptyDiv().addClass("tab-content");
		fragment = factory.getHTMLFactory().fragment(factory.getHTMLFactory().tag(TagName.nav).content(htmlElement), contentDiv);
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
				
		contentDiv.content(cDiv);		
		return this;
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
		return fragment.produce(indent);
	}
	
	@Override
	public String toString() {
		return fragment.toString();
	}

	@Override
	public Tag getContentDiv() {
		return contentDiv;
	}

}
