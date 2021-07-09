package org.nasdanika.html.impl;

import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.TextArea;

class TextAreaImpl extends InputBaseImpl<TextArea> implements TextArea {
	
	public TextAreaImpl(HTMLFactory factory) {
		super(factory, "textarea");
	}
	
	@Override
	public TextArea content(Object... content) {
		return super.content(content);
	}

	@Override
	public TextArea placeholder(Object placeholder) {
		return attribute("placeholder", placeholder);
	}

	@Override
	public TextArea cols(int cols) {
		return attribute("cols", cols);
	}

	@Override
	public TextArea rows(int rows) {
		return attribute("rows", rows);
	}

	@Override
	public TextArea maxLength(int maxLength) {
		return attribute("maxlength", maxLength);
	}

	@Override
	public TextArea readonly() {
		return readonly(true);
	}

	@Override
	public TextArea readonly(boolean readonly) {
		return attribute("readonly", readonly ? Boolean.TRUE : null);
	}

	@Override
	public TextArea wrap() {
		return wrap(true);
	}

	@Override
	public TextArea wrap(boolean wrap) {
		return attribute("wrap", wrap ? Boolean.TRUE : null);
	}
	
	
}
