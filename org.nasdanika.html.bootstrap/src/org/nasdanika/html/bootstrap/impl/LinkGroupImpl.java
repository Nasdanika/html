package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.LinkGroup;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;

class LinkGroupImpl extends UIElementImpl<LinkGroup> implements LinkGroup {
	
	LinkGroupImpl(HTMLFactory factory) {
		super(factory, TagName.div);
		addClass("list-group");
	}

	@Override
	public Tag item(Object content, Object href, Bootstrap.Style style, boolean active) {
		return item(content, style, active).attribute("href", href);
	}
	
	@Override
	public Tag item(Object itemContent, Bootstrap.Style style, boolean active) {
		Tag a = factory.tag(TagName.a, itemContent).addClass("list-group-item");
		if (style!=null && Bootstrap.Style.DEFAULT!=style) {
			a.addClass("list-group-item-"+style.name().toLowerCase());
		}
		if (active) {
			a.addClass("active");
		}
		this.content.add(a);
		return a;
	}

	@Override
	public int length() {
		return content.size();
	}

}
