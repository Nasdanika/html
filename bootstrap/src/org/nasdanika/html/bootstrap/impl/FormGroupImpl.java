package org.nasdanika.html.bootstrap.impl;

import java.util.Map;

import org.nasdanika.html.Input;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.InputType;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.FormGroup;

public class FormGroupImpl extends DivWrappingBootstrapElementImpl<FormGroup> implements FormGroup {

	private InputBase<?> input;

	public FormGroupImpl(BootstrapFactory factory, Object label, InputBase<?> input, Object hint, Map<DeviceSize, Integer> horizontalLabelWidths) {
		super(factory);
		this.input = input;
		boolean isHorizontal = horizontalLabelWidths != null && !horizontalLabelWidths.isEmpty();
		htmlElement.addClass("form-group").addClassConditional(isHorizontal, "row");
		// Possibly keep and expose the label
		Tag labelTag = label==null ? null : factory.getHTMLFactory().nonEmptyTag(TagName.label, label).addClassConditional(isHorizontal, "col-form-label");
		if (isHorizontal && labelTag != null) {
			horizontalLabelWidths.entrySet().forEach(horizontalLabelWidth -> {
				labelTag.addClass("col-"+horizontalLabelWidth.getKey().code+"-"+horizontalLabelWidth.getValue());
			});
		}
		
		if (isHorizontal) {
			Tag controlDiv = getFactory().getHTMLFactory().div();
			horizontalLabelWidths.entrySet().forEach(horizontalLabelWidth -> {
				controlDiv.addClass("col-"+horizontalLabelWidth.getKey().code+"-"+(12 - horizontalLabelWidth.getValue()));
			});
			
			htmlElement.content(controlDiv);
			if (input instanceof Input && (((Input) input).getType() == InputType.checkbox || ((Input) input).getType() == InputType.radio)) {				
				input.addClass("form-check-input");	
				Tag formCheckDiv = getFactory().getHTMLFactory().div();
				controlDiv.content(formCheckDiv);
				formCheckDiv.addClass("form-check");
				formCheckDiv.content(input);
				if (label == null) {
					input.addClass("position-static");
				} else {
					formCheckDiv.content(labelTag.addClass("form-check-label"));
				}
			} else {
				if (label != null) {
					htmlElement.content(labelTag);
				}				
				input.addClass("form-control");
				controlDiv.content(input);				
			}
			
			if (hint != null) {
				controlDiv.content(factory.getHTMLFactory().nonEmptyTag(TagName.small, hint).addClass("form-text", "text-muted"));			
			}
		} else {		
			if (input instanceof Input && (((Input) input).getType() == InputType.checkbox || ((Input) input).getType() == InputType.radio)) {
				input.addClass("form-check-input");			
				htmlElement.addClass("form-check");
				if (label == null) {
					input.addClass("position-static");
				} else {
					htmlElement.content(labelTag.addClass("form-check-label"));
				}
			} else {
				if (label != null) {
					htmlElement.content(labelTag);
				}
				input.addClass("form-control");
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
