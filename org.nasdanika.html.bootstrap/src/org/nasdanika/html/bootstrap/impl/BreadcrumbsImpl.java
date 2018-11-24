package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumbs;

public class BreadcrumbsImpl extends WrappingBootstrapElementImpl<Tag,Breadcrumbs> implements Breadcrumbs {
	
	private Tag ol;

	public BreadcrumbsImpl(BootstrapFactory factory) {
		super(factory, factory.getHTMLFactory().tag("nav").attribute("aria-label", "breadcrumb"));
		ol = getFactory().getHTMLFactory().tag(TagName.ol).addClass("breadcrumb");
		htmlElement.content(ol);
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

}
