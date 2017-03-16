package org.nasdanika.html.impl;

import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.Tag;

abstract class InputBaseImpl<T extends InputBase<T>> extends UIElementImpl<T> implements InputBase<T> {
	
	InputBaseImpl(HTMLFactory factory, String tagName) {
		super(factory, tagName);
	}

	InputBaseImpl(HTMLFactory factory, Tag.TagName tagName) {
		this(factory, tagName.name());
	}

	@Override
	public T autofocus(boolean autofocus) {		
		return attribute("autofocus", autofocus ? Boolean.TRUE : null);
	}

	@Override
	public T autofocus() {
		return autofocus(true);
	}

	@Override
	public T disabled(boolean disabled) {		
		return attribute("disabled", disabled ? Boolean.TRUE : null);
	}

	@Override
	public T disabled() {
		return disabled(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T form(Form... form) {
		String existingForm = (String) attributes.get("form");
		for (Form f: form) {
			if (f.getId()==null) {
				f.id(factory.nextId());
			}
			if (existingForm!=null && existingForm.trim().length()>0) {
				existingForm+=" ";
			}
			if (existingForm==null) {
				existingForm = f.getId().toString();
			} else {
				existingForm+= f.getId();
			}			
		}
		return (T) this;
	}
	@Override
	public T name(Object name) {
		return attribute("name", name);
	}

	@Override
	public T required(boolean required) {
		return attribute("required", required ? Boolean.TRUE : null);
	}

	@Override
	public T required() {
		return required(true);
	}
	
}
