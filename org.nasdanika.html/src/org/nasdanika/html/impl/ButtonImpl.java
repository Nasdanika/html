package org.nasdanika.html.impl;

import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.TagName;

class ButtonImpl extends HTMLElementImpl<Button> implements Button {
	
	ButtonImpl(HTMLFactory factory, Object... content) {
		super(factory, TagName.button, false);
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

	@Override
	public Button disabled(boolean disabled) {
		attribute("disabled", disabled ? true : null);
		return this;
	}

	@Override
	public Button disabled() {
		return disabled(true);
	}
	
}
