package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.ListGroup;

public class ListGroupImpl extends WrappingBootstrapElementImpl<Tag, ListGroup> implements ListGroup {

	protected ListGroupImpl(BootstrapFactory factory, boolean flush) {
		super(factory, factory.getHTMLFactory().tag(TagName.ul));
		htmlElement.addClass("list-group").addClassConditional(flush, "list-group-flush");
	}

	@Override
	public boolean isEmpty() {
		return htmlElement.isEmpty();
	}

	@Override
	public Tag item(boolean active, boolean disabled, Color color, Object... content) {
		Tag li = getFactory().getHTMLFactory().tag(TagName.li, content)
				.addClass("list-group-item")
				.addClassConditional(active, "active")
				.addClassConditional(disabled, "disabled")
				.addClassConditional(color != null && color.code != null, "list-group-item-"+color.code);
		htmlElement.content(li);
		return li;
	}

}
