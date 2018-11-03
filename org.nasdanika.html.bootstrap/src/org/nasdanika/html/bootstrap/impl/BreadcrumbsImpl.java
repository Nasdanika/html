package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Breadcrumbs;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag.TagName;

class BreadcrumbsImpl extends UIElementImpl<Breadcrumbs> implements Breadcrumbs {

	public BreadcrumbsImpl(HTMLFactory factory) {
		super(factory, TagName.ol);
		addClass("breadcrumb");
	}
	
	@Override
	public Breadcrumbs item(Object href, Object... itemContent) {
		if (href==null) {
			this.content.add(factory.tag(TagName.li, itemContent).addClass("active"));
		} else {
			this.content.add(factory.tag(TagName.li, factory.link(href, itemContent)));
		}
		return this;
	}

}
