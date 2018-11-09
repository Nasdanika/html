package org.nasdanika.html.impl;

import org.nasdanika.html.Button;
import org.nasdanika.html.FieldSet;
import org.nasdanika.html.FormFragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;

class FieldSetImpl extends HTMLElementImpl<FieldSet> implements FieldSet {
	
	private FieldContainerImpl<FieldSet> container;

	FieldSetImpl(HTMLFactory factory, FormImpl form) {
		super(factory, "fieldset", false);
		container = new FieldContainerImpl<FieldSet>(factory, this, form);	
		this.content.add(container);
	}

	@Override
	public FieldSet content(Object... content) {
		return container.content(content);
	}
	
	@Override
	public Tag legend(Object... content) {
		Tag legend = factory.tag(TagName.legend, content);
		content(legend);
		return legend;
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
	public FieldSet disabled(boolean disabled) {
		attribute("disabled", disabled ? "disabled" : null);	
		return this;
	}

	@Override
	public FieldSet disabled() {
		return disabled(true);
	}

	@Override
	public void close() throws Exception {
		super.close();
		container.close();		
	}	

}
