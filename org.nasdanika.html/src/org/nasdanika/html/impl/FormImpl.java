package org.nasdanika.html.impl;

import org.nasdanika.html.Button;
import org.nasdanika.html.FieldSet;
import org.nasdanika.html.Form;
import org.nasdanika.html.FormFragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag.TagName;

class FormImpl extends HTMLElementImpl<Form> implements Form {
	
	private FieldContainerImpl<Form> container;

	FormImpl(HTMLFactory factory) {
		super(factory, TagName.form, false);
		container = new FieldContainerImpl<Form>(factory, this, this);
		this.content.add(container);
	}

	@Override
	public Form content(Object... content) {
		return container.content(content);
	}

	@Override
	public Button button(Object... content) {
		return container.button(content);
	}

	@Override
	public FieldSet fieldset() {
		return container.fieldset();
	}
	
	@Override
	public FormFragment formFragment() {
		return container.formFragment();
	}
	
	@Override
	public void close() throws Exception {
		super.close();
		close(container);		
	}

	@Override
	public Form action(Object action) {
		return attribute("action", action);
	}

	@Override
	public Form autocomplete() {
		return autocomplete(true);
	}

	@Override
	public Form autocomplete(boolean autocomplete) {
		return attribute("autocomplete", autocomplete ? "on" : "off");
	}

	@Override
	public Form enctype(EncType enctype) {
		return attribute("enctype", enctype.literal);
	}

	@Override
	public Form acceptCharset(String charset) {
		return attribute("accept-charset", charset);
	}

	@Override
	public Form method(Method method) {
		return attribute("method", method.name());
	}

	@Override
	public Form name(String name) {
		return attribute("name", name);
	}

	@Override
	public Form novalidate() {
		return novalidate(true);
	}

	@Override
	public Form novalidate(boolean novalidate) {
		return attribute("novalidate", novalidate ? "on" : null);
	}

	@Override
	public Form target(String target) {
		return attribute("target", target);
	}

}
