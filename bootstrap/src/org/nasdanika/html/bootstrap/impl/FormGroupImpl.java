package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputType;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.FormGroup;

public class FormGroupImpl extends DivWrappingBootstrapElementImpl<FormGroup> implements FormGroup {

	private Object control;

	public FormGroupImpl(BootstrapFactory factory, Object label, Object control, Object hint) {
		super(factory);
		this.control = control;
		htmlElement.addClass("form-group");
		// Possibly keep and expose the label
		if (control instanceof Input && (((Input) control).getType() == InputType.checkbox || ((Input) control).getType() == InputType.radio)) {
			((Input) control).addClass("form-check-input");			
			htmlElement.addClass("form-check");
			htmlElement.content(control);
			if (label == null) {
				((Input) control).addClass("position-static");
			} else {
				htmlElement.content(factory.getHTMLFactory().nonEmptyTag(TagName.label, label).addClass("form-check-label"));
			}
		} else {
			if (label != null) {
				htmlElement.content(factory.getHTMLFactory().nonEmptyTag(TagName.label, label));
			}
			htmlElement.content(control);
			HTMLElement<?> che = getControlHTMLElement();
			if (che != null) {
				che.addClass("form-control");
			}
		}
		if (hint != null) {
			htmlElement.content(factory.getHTMLFactory().nonEmptyTag(TagName.small, hint).addClass("form-text", "text-muted"));			
		}
	}
	
	protected HTMLElement<?> getControlHTMLElement() {
		if (control instanceof HTMLElement) {
			return (HTMLElement<?>) control;						
		}
		
		if (control instanceof BootstrapElement) {
			return ((BootstrapElement<?, ?>) control).toHTMLElement(); 
		}
		
		return null;
	}

	@Override
	public FormGroup large() {
		return large(true);
	}

	@Override
	public FormGroup large(boolean large) {
		HTMLElement<?> che = getControlHTMLElement();
		if (che != null) {
			che.addClassConditional(large, "form-control-lg");
		}
		return this;
	}

	@Override
	public FormGroup small() {
		return small(true);
	}

	@Override
	public FormGroup small(boolean small) {
		HTMLElement<?> che = getControlHTMLElement();
		if (che != null) {
			che.addClassConditional(small, "form-control-sm");
		}
		return this;
	}

	@Override
	public FormGroup plainText() {
		return plainText(true);
	}

	@Override
	public FormGroup plainText(boolean plainText) {
		HTMLElement<?> che = getControlHTMLElement();
		if (che != null) {
			che.addClassConditional(plainText, "form-control-plaintext");
		}
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
		HTMLElement<?> che = getControlHTMLElement();
		if (che != null) {
			che.addClass("is-valid");
		}
		htmlElement.content(getFactory().getHTMLFactory().nonEmptyDiv(feedback).addClass("valid-feedback"));
		return this;
	}

	@Override
	public FormGroup invalid(Object... feedback) {
		HTMLElement<?> che = getControlHTMLElement();
		if (che != null) {
			che.addClass("is-invalid");
		}
		htmlElement.content(getFactory().getHTMLFactory().nonEmptyDiv(feedback).addClass("invalid-feedback"));
		return this;
	}

}
