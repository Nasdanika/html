package org.nasdanika.html.impl;

import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Well;

class WellImpl extends UIElementImpl<Well> implements Well {
	
	WellImpl(HTMLFactory factory, Object[] content) {
		super(factory, "div");
		for (Object c: content) {
			this.content.add(c);
		}
		addClass("well");
	}
	
	@Override
	public Well content(Object... content) {		
		return super.content(content);
	}
	
	@Override
	public Well small() {
		addClass("well-sm");
		return this;
	}
	
	@Override
	public Well large() {
		addClass("well-lg");
		return this;
	}

}
