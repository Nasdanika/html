package org.nasdanika.html.impl;

import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag.TagName;

class ButtonImpl extends UIElementImpl<Button> implements Button {
	
	ButtonImpl(HTMLFactory factory, Object... content) {
		super(factory, TagName.button);
		for (Object c: content) {
			if (c!=null) {
				this.content.add(c);
			}
		}
	}
	
	@Override
	public Button type(Type type) {
		attribute("type", type.name().toLowerCase());
		return this;
	}
			
	@Override
	public Button content(Object... content) {
		return super.content(content);
	}
}
