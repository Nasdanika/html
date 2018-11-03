package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Well;

class WellImpl extends BootstrapElementImpl<Tag> implements Well {
		
	private Tag tag;

	WellImpl(BootstrapFactory factory, Object[] content) {
		super(factory);
		tag = factory.getHTMLFactory().div();
		for (Object c: content) {
			tag.content(c);
		}
		tag.addClass("well");
	}
	
	@Override
	public Well content(Object... content) {		
		for (Object c: content) {
			tag.content(c);
		}
		return this;
	}
	
	@Override
	public Well small() {
		tag.addClass("well-sm");
		return this;
	}
	
	@Override
	public Well large() {
		tag.addClass("well-lg");
		return this;
	}
	
	@Override
	public Tag toHTMLElement() {
		return tag;
	}

	@Override
	public boolean isEmpty() {
		return tag.isEmpty();
	}

	@Override
	public void close() throws Exception {
		tag.close();		
	}

}
