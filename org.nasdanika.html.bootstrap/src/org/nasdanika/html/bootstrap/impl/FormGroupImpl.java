package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Input;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.InputType;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.FormGroup;

class FormGroupImpl extends DivWrappingBootstrapElementImpl implements FormGroup {

	private InputBase<?> input;

	FormGroupImpl(BootstrapFactory factory, Object label, InputBase<?> input, Object hint) {
		super(factory);
		this.input = input;
		htmlElement.addClass("form-group");
		// Possibly keep and expose the label
		if (input instanceof Input && (((Input) input).getType() == InputType.checkbox || ((Input) input).getType() == InputType.radio)) {
			input.addClass("form-check-input");			
			htmlElement.addClass("form-check");
			htmlElement.content(input);
			if (label == null) {
				input.addClass("position-static");
			} else {
				htmlElement.content(factory.getHTMLFactory().nonEmptyTag(TagName.label, label).addClass("form-check-label"));
			}
		} else {
			if (label != null) {
				htmlElement.content(factory.getHTMLFactory().nonEmptyTag(TagName.label, label));
			}
			htmlElement.content(input);
			input.addClass("form-control");
		}
		if (hint != null) {
			htmlElement.content(factory.getHTMLFactory().nonEmptyTag(TagName.small, label).addClass("form-text", "text-muted"));			
		}
	}

	@Override
	public FormGroup large() {
		return large(true);
	}

	@Override
	public FormGroup large(boolean large) {
		input.addClassConditional(large, "form-control-lg");
		return this;
	}

	@Override
	public FormGroup small() {
		return small(true);
	}

	@Override
	public FormGroup small(boolean small) {
		input.addClassConditional(small, "form-control-sm");
		return this;
	}

	@Override
	public FormGroup plainText() {
		return plainText(true);
	}

	@Override
	public FormGroup plainText(boolean plainText) {
		input.addClassConditional(plainText, "form-control-plaintext");
		return this;
	}

	@Override
	public FormGroup inline() {
		return inline(true);
	}

	@Override
	public FormGroup inline(boolean inline) {
		htmlElement.addClassConditional(inline, "form-check-inline");
		return this;
	}

	@Override
	public FormGroup valid(Object... feedback) {
		input.addClass("is-valid");
		htmlElement.content(getFactory().getHTMLFactory().nonEmptyDiv(feedback).addClass("valid-feedback"));
		return this;
	}

	@Override
	public FormGroup invalid(Object... feedback) {
		input.addClass("is-invalid");
		htmlElement.content(getFactory().getHTMLFactory().nonEmptyDiv(feedback).addClass("invalid-feedback"));
		return this;
	}

}
