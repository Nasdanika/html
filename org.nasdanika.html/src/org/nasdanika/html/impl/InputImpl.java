package org.nasdanika.html.impl;

import org.nasdanika.html.Form.EncType;
import org.nasdanika.html.Form.Method;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Input;
import org.nasdanika.html.Tag.TagName;

class InputImpl extends InputBaseImpl<Input> implements Input {
		
	InputImpl(HTMLFactory factory, HTMLFactory.InputType type) {
		super(factory, TagName.input);
		attribute("type", type);
	}
	
	@Override
	public Input content(Object... content) {
		return super.content(content);
	}

	@Override
	public Input autocomplete(boolean autocomplete) {
		return attribute("autocomplete", autocomplete ? "on" : "off");
	}

	@Override
	public Input autocomplete() {
		return autocomplete(true);
	}

	@Override
	public Input formaction(Object formaction) {
		return attribute("formaction", formaction);
	}

	@Override
	public Input formenctype(EncType formEncType) {
		return attribute("formenctype", formEncType.literal);
	}

	@Override
	public Input formmethod(Method formMethod) {
		return attribute("method", formMethod.name());
	}

	@Override
	public Input formnovalidate(boolean formnovalidate) {
		return attribute("formnovalidate", formnovalidate ? Boolean.TRUE : null);
	}

	@Override
	public Input formnovalidate() {
		return formnovalidate(true);
	}

	@Override
	public Input formtarget(Object formTarget) {
		return attribute("formtarget", formTarget);
	}

	@Override
	public Input dimensions(int width, int height) {
		return attribute("width", width).attribute("height", height);
	}

	@Override
	public Input list(Object dataListId) {
		return attribute("list", dataListId);
	}

	@Override
	public Input min(Object min) {
		return attribute("min", min);
	}

	@Override
	public Input max(Object max) {
		return attribute("max", max);
	}

	@Override
	public Input multiple(boolean multiple) {
		return attribute("multiple", multiple ? Boolean.TRUE : null);
	}

	@Override
	public Input multiple() {
		return multiple(true);
	}

	@Override
	public Input pattern(Object pattern) {
		return attribute("pattern", pattern);
	}

	@Override
	public Input value(Object value) {
		return attribute("value", value);
	}

	@Override
	public Input placeholder(Object placeholder) {
		return attribute("placeholder", placeholder);
	}

	@Override
	public Input step(Object step) {
		return attribute("step", step);
	}
}
