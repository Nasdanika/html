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
	public Tag item(boolean active, Object... content) {
		Tag liTag = getFactory().getHTMLFactory()
				.tag(TagName.li, content)
				.addClass("breadcrumb-item")
				.addClassConditional(active, "active");
		ol.content(liTag);
		return liTag;
	}
	
}
