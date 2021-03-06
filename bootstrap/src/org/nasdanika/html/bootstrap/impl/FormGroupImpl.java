package org.nasdanika.html.bootstrap.impl;

import java.util.Map;

import org.nasdanika.html.Container;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputType;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.FormGroup;
import org.nasdanika.html.bootstrap.Size;

public class FormGroupImpl extends DivWrappingBootstrapElementImpl<FormGroup> implements FormGroup {

	private Object input;
	private Container<?> inputContainer;

	@SuppressWarnings("unchecked")
	public FormGroupImpl(BootstrapFactory factory, Object label, Object input, Object hint, Map<Breakpoint, Size> horizontalLabelWidths) {
		super(factory);
		this.input = input;
		boolean isHorizontal = horizontalLabelWidths != null && !horizontalLabelWidths.isEmpty();
		htmlElement.addClass("form-group").addClassConditional(isHorizontal, "row");
		// Possibly keep and expose the label
		Tag labelTag = label==null ? null : factory.getHTMLFactory().nonEmptyTag(TagName.label, label).addClassConditional(isHorizontal, "col-form-label");
		if (isHorizontal && labelTag != null) {
			horizontalLabelWidths.entrySet().forEach(horizontalLabelWidth -> {
				labelTag.addClass(horizontalLabelWidth.getKey().size("col", horizontalLabelWidth.getValue()));
			});
		}
		
		if (isHorizontal) {
			if (label != null) {
				htmlElement.content(labelTag);
			}				
			Tag controlDiv = getFactory().getHTMLFactory().div();
			horizontalLabelWidths.entrySet().forEach(horizontalLabelWidth -> {
				controlDiv.addClass(horizontalLabelWidth.getKey().size("col", horizontalLabelWidth.getValue().complementary()));
			});
			
			htmlElement.content(controlDiv);
			if (input instanceof Input && (((Input) input).getType() == InputType.checkbox || ((Input) input).getType() == InputType.radio)) {				
				((Input) input).addClass("form-check-input");	
				Tag formCheckDiv = getFactory().getHTMLFactory().div();
				controlDiv.content(formCheckDiv);
				formCheckDiv.addClass("form-check");
				formCheckDiv.content(input);
				inputContainer = formCheckDiv;
				if (label == null) {
					((Input) input).addClass("position-static");
				} 				
				if (hint != null) {
					formCheckDiv.content(factory.getHTMLFactory().nonEmptyTag(TagName.label, hint).addClass("form-check-label"));			
				}
			} else {
				inputContainer = controlDiv;
				if (input instanceof HTMLElement) {
					((HTMLElement<Input>) input).addClass("form-control");
				}
				controlDiv.content(input);				
				
				if (hint != null) {
					controlDiv.content(factory.getHTMLFactory().nonEmptyTag(TagName.small, hint).addClass("form-text", "text-muted"));			
				}
			}
		} else {	
			inputContainer = htmlElement;
			if (input instanceof Input && (((Input) input).getType() == InputType.checkbox || ((Input) input).getType() == InputType.radio)) {
				((HTMLElement<Input>) input).addClass("form-check-input");			
				htmlElement.addClass("form-check");
				if (label == null) {
					((HTMLElement<Input>) input).addClass("position-static");
				} else {
					htmlElement.content(labelTag.addClass("form-check-label"));
				}
			} else {
				if (label != null) {
					htmlElement.content(labelTag);
				}
				if (input instanceof HTMLElement<?>) {
					((HTMLElement<Input>) input).addClass("form-control");
				}
				htmlElement.content(input);
			}
			
			if (hint != null) {
				htmlElement.content(factory.getHTMLFactory().nonEmptyTag(TagName.small, hint).addClass("form-text", "text-muted"));			
			}
		}
	}

	@Override
	public FormGroup large() {
		return large(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public FormGroup large(boolean large) {
		if (input instanceof HTMLElement) {
			((HTMLElement<Input>) input).addClassConditional(large, "form-control-lg");
		}
		return this;
	}

	@Override
	public FormGroup small() {
		return small(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public FormGroup small(boolean small) {
		if (input instanceof HTMLElement) {
			((HTMLElement<Input>) input).addClassConditional(small, "form-control-sm");
		}
		return this;
	}

	@Override
	public FormGroup plainText() {
		return plainText(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public FormGroup plainText(boolean plainText) {
		if (input instanceof HTMLElement) {
			((HTMLElement<Input>) input).addClassConditional(plainText, "form-control-plaintext");
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

	@SuppressWarnings("unchecked")
	@Override
	public FormGroup valid(Object... feedback) {
		if (input instanceof HTMLElement) {
			((HTMLElement<Input>) input).addClass("is-valid");
		}
		inputContainer.content(getFactory().getHTMLFactory().nonEmptyDiv(feedback).addClass("valid-feedback"));
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FormGroup invalid(Object... feedback) {
		if (input instanceof HTMLElement) {
			((HTMLElement<Input>) input).addClass("is-invalid");
		}
		inputContainer.content(getFactory().getHTMLFactory().nonEmptyDiv(feedback).addClass("invalid-feedback"));
		return this;
	}

}
