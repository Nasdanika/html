package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumbs;

class BreadcrumbsImpl extends BootstrapElementImpl<Tag> implements Breadcrumbs {
	
	private Tag nav;
	private Tag ol;

	public BreadcrumbsImpl(BootstrapFactory factory) {
		super(factory);
		nav = getFactory().getHTMLFactory().tag("nav").attribute("aria-label", "breadcrumb");
		ol = getFactory().getHTMLFactory().tag(TagName.ol).addClass("breadcrumb");
		nav.content(ol);
	}

	@Override
	public Tag toHTMLElement() {
		return nav;
	}

	@Override
	public boolean isEmpty() {
		return ol.isEmpty();
	}

	@Override
	public Breadcrumbs item(Object href, Object... content) {
		ol.content(getFactory().getHTMLFactory()
				.tag(TagName.li, href== null ? content : new Object[] { getFactory().getHTMLFactory().link(href, content) })
				.addClass("breadcrumb-item")
				.addClassConditional(href == null, "active"));
		return this;
	}

	@Override
	public void close() throws Exception {
		nav.close();		
	}

	@Override
	public Object produce(int indent) {
		return nav.produce(indent);
	}

}
