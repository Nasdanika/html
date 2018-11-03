package org.nasdanika.html.bootstrap.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.Bootstrap.Glyphicon;
import org.nasdanika.html.FormGroup;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;
import org.nasdanika.html.UIElement;

class FormGroupImpl<T extends FormGroup<T>, C> extends UIElementImpl<T> implements FormGroup<T> {
	
	private Object label;
	private Object controlId;
	protected C control;
	private Status status;
	private FormImpl form;
	private Object helpText;
	private boolean feedback;

	FormGroupImpl(HTMLFactory factory, FormImpl form, Object label, Object controlId, C control, Object helpText) {
		super(factory, TagName.div);
		this.form = form;
		this.label = label;
		this.controlId = controlId;
		this.control = control;	
		if (control instanceof InputBase) {
			((InputBase<?>) control).addClass("form-control");
		}
		this.helpText = helpText;
		addClass("form-group");
	}

	@SuppressWarnings("unchecked")
	@Override
	public T feedback(boolean feedback) {
		this.feedback = feedback;
		if (feedback) {
			addClass("has-feedback");
		} else {
			removeClass("has-feedback");
		}
		return (T) this;
	}

	@Override
	public T feedback() {
		return feedback(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T status(Status status) {
		if (this.status!=null) {
			removeClass("has-"+this.status.name().toLowerCase());
		}
		this.status = status;
		if (status!=null) {
			addClass("has-"+status.name().toLowerCase());
		}

		return (T) this;
	}
	
	@Override
	protected List<Object> getContent() {
		List<Object> ret = new ArrayList<>();
		if (label!=null) {
			UIElement<?> labelTag = form.factory.tag(TagName.label, label).attribute("for", String.valueOf(controlId));
			if (form.inline && form.hideInlineLabels) {
				labelTag.addClass("sr-only");
			}
			if (form.horizontal) {
				labelTag.addClass("col-"+form.deviceSize.code+"-"+form.labelWidth);
				labelTag.addClass("control-label");
			}
			ret.add(labelTag);
		}
		if (form.horizontal) {
			UIElement<?> controlDiv = form.factory.div(control);
			controlDiv.addClass("col-"+form.deviceSize.code+"-"+(12-form.labelWidth));
			if (label==null) {
				controlDiv.addClass("col-"+form.deviceSize.code+"-offset-"+form.labelWidth);
			}
			ret.add(controlDiv);
		} else {
			ret.add(control);
		}
		if (feedback && status!=null) {
			Tag feedbackSpan = null;
			switch (status) {
			case ERROR:
				feedbackSpan = form.factory.glyphicon(Glyphicon.remove);
				break;
			case SUCCESS:
				feedbackSpan = form.factory.glyphicon(Glyphicon.ok);
				break;
			case WARNING:
				feedbackSpan = form.factory.glyphicon(Glyphicon.warning_sign);
				break;
			default:
				break;			
			}
			if (feedbackSpan!=null) {
				feedbackSpan.addClass("form-control-feedback");
				ret.add(feedbackSpan);
			}
		}
		if (helpText!=null && !form.horizontal && !form.inline) {
			ret.add(form.factory.tag(TagName.p, helpText).addClass("help-block"));			
		}
		return ret;
	}

	@Override
	public void close() throws Exception {
		super.close();
		close(control);
		close(controlId);
		close(helpText);
		close(label);		
	}
	
}