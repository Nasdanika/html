package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.ListGroup;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;

class ListGroupImpl extends UIElementImpl<ListGroup> implements ListGroup {
	
	ListGroupImpl(HTMLFactory factory) {
		super(factory, TagName.ul);
		addClass("list-group");
	}

	@Override
	public Tag item(Object itemContent, Bootstrap.Style style) {
		Tag li = factory.tag(TagName.li, itemContent).addClass("list-group-item");
		if (style!=null && Bootstrap.Style.DEFAULT!=style) {
			li.addClass("list-group-item-"+style.name().toLowerCase());
		}
		this.content.add(li);
		return li;
	}

	@Override
	public int length() {
		return content.size();
	}

}
