package org.nasdanika.html.impl;

import java.util.List;

import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;

class TagImpl extends HTMLElementImpl<Tag> implements Tag {
	
	TagImpl(HTMLFactory factory, String tagName, boolean nonEmpty, Object... content) {
		super(factory, tagName, nonEmpty);
		for (Object c: content) {
			this.content.add(c);
		}
	}
	
	@Override
	public Tag content(Object... content) {
		return super.content(content);
	}
	
	protected List<Object> getContent() {
		return content;
	}
	
	@Override
	public String getTagName() {
		return super.getTagName();
	}

	@Override
	public boolean is(TagName tagName) {
		return getTagName().equalsIgnoreCase(tagName.name());
	}

}
