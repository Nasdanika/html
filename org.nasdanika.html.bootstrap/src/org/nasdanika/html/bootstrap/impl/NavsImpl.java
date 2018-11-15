package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Navs;

public class NavsImpl extends BootstrapElementImpl<Tag,Navs> implements Navs {
	
	private Fragment fragment;
	private Tag navDiv;
	private Tag contentDiv;

	protected NavsImpl(BootstrapFactory factory, boolean pills) {
		super(factory);
		navDiv = factory.getHTMLFactory().nonEmptyDiv()
				.addClass("nav")
				.addClassConditional(pills, "nav-pills")
				.addClassConditional(!pills, "nav-tabs")
				.attribute("role", "tablist");
		
		contentDiv = factory.getHTMLFactory().nonEmptyDiv().addClass("tab-content");
		fragment = factory.getHTMLFactory().fragment(factory.getHTMLFactory().tag(TagName.nav).content(navDiv), contentDiv);
	}

	@Override
	public void item(Object name, Object content) {
		item(name, isEmpty(), null, content);
		
	}

	@Override
	public boolean isEmpty() {
		return navDiv.isEmpty();
	}

	@Override
	public Navs item(Object name, boolean active, Object contentId, Object... content) {
		if (contentId == null) {
			contentId = "nav-"+getFactory().getHTMLFactory().nextId();
		}
		Tag navLink = getFactory().getHTMLFactory().link("#"+contentId, name)
				.addClass("nav-item", "nav-link")
				.addClassConditional(active, "active")
				.attribute("data-toggle", "tab")
				.attribute("role", "tab");
		navDiv.content(navLink);
		
		Tag cDiv = getFactory().getHTMLFactory().nonEmptyDiv(content)
				.id(contentId)
				.attribute("role", "tabpanel")
				.addClass("tab-pane", "fade")
				.addClassConditional(active, "show", "active");				
				
		contentDiv.content(cDiv);		
		return this;
	}

	@Override
	public Tag toHTMLElement() {
		return navDiv;
	}

	@Override
	public void close() throws Exception {
		fragment.close();
	}

	@Override
	public Object produce(int indent) {
		return fragment.produce(indent);
	}
	
	@Override
	public String toString() {
		return fragment.toString();
	}

}
