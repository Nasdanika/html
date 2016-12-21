package org.nasdanika.html.impl;

import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Select;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;

class SelectImpl extends InputBaseImpl<Select> implements Select {
	
	public SelectImpl(HTMLFactory factory) {
		super(factory, TagName.select);
	}
	
	private class OptionGroupImpl extends UIElementImpl<OptionGroup> implements OptionGroup {
		
		public OptionGroupImpl(Object label) {
			super(SelectImpl.this.factory, TagName.optgroup);
			attribute("label", label);
		}

		@Override
		public OptionGroup disabled(boolean disabled) {
			attribute("disabled", disabled ? "disabled" : null);
			return this;
		}

		@Override
		public OptionGroup disabled() {
			return disabled(true);
		}
		
		@Override
		public Tag option(String value, boolean selected, boolean disabled, Object... optionContent) {
			Tag option = factory.tag(TagName.option, optionContent).attribute("value", value);
			if (selected) {
				option.attribute("selected", "selected");
			} else if (disabled) {
				option.attribute("disabled", "disabled");
			}
			this.content.add(option);
			return option;
		}

		@Override
		public Tag option(String value, String label, boolean selected, boolean disabled) {
			return option(value, selected, disabled, label);
		}
		
	}

	@Override
	public OptionGroup optionGroup(Object label) {
		OptionGroupImpl ret = new OptionGroupImpl(label);
		this.content.add(ret);
		return ret;
	}
	
	@Override
	public Tag option(String value, boolean selected, boolean disabled, Object... optionContent) {
		Tag option = factory.tag(TagName.option, optionContent).attribute("value", value);
		if (selected) {
			option.attribute("selected", "selected");
		} else if (disabled) {
			option.attribute("disabled", "disabled");
		}
		this.content.add(option);
		return option;
	}

	@Override
	public Tag option(String value, String label, boolean selected, boolean disabled) {
		return option(value, selected, disabled, label);
	}
	
	@Override
	public Select multiple() {
		return multiple(true);
	}

	@Override
	public Select multiple(boolean multiple) {
		return attribute("multiple", multiple? Boolean.TRUE : null);
	}

	@Override
	public Select size(int size) {
		return attribute("size", size);
	}
	
}
