package org.nasdanika.html.impl;

import java.util.Map;

import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.Button;
import org.nasdanika.html.FormInputGroup;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLFactory.Placement;
import org.nasdanika.html.InputGroup;

class FormInputGroupImpl extends FormGroupImpl<FormInputGroup, InputGroupImpl> implements FormInputGroup {
		
	FormInputGroupImpl(HTMLFactory factory, FormImpl form, Object label, Object controlId, Object control, Object helpText) {
		super(factory, form, label, controlId, new InputGroupImpl(factory, control), helpText);
	}

	@Override
	public FormInputGroup leftAddOn(Object... addOn) {
		control.leftAddOn(addOn);
		return this;
	}

	@Override
	public FormInputGroup size(Bootstrap.Size size) {
		control.size(size);
		return this;
	}

	@Override
	public Button leftButton(Object... content) {		
		return control.leftButton(content);
	}

	@Override
	public FormInputGroup rightAddOn(Object... addOn) {
		control.rightAddOn(addOn);
		return this;
	}

	@Override
	public Button rightButton(Object... content) {
		return control.rightButton(content);
	}

	@Override
	public Button leftPopoverHelpButton(Placement placement, String title, String body, Map<String, Object> options) {
		return control.leftPopoverHelpButton(placement, title, body, options);
	}

	@Override
	public Button rightPopoverHelpButton(Placement placement, String title, String body, Map<String, Object> options) {
		return control.rightPopoverHelpButton(placement, title, body, options);
	}

	@SuppressWarnings("unchecked")
	@Override
	public InputGroup<FormInputGroup> getInputGroup() {
		return (InputGroup<FormInputGroup>) (Object) control; // A hack
	}
	
}
