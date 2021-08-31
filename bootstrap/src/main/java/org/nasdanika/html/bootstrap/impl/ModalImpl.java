package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Modal;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

public class ModalImpl extends WrappingBootstrapElementImpl<Tag, Modal> implements Modal {

	private TagBootstrapElement content;	
	private TagBootstrapElement header;
	private TagBootstrapElement body;
	private TagBootstrapElement footer;
	private Tag dialog;

	@Override
	public TagBootstrapElement getContent() {
		return content;
	}
	
	public ModalImpl(BootstrapFactory factory) {
		super(factory, factory.getHTMLFactory().nonEmptyDiv());
		htmlElement.addClass("modal");
		htmlElement.attribute("tabindex", "-1");
		dialog = factory.getHTMLFactory().div().addClass("modal-dialog");
		htmlElement.content(dialog);
		Tag contentDiv = factory.getHTMLFactory().div().addClass("modal-content");
		dialog.content(contentDiv);
		content = getFactory().wrap(contentDiv);
		
		header = getFactory().wrap(getFactory().getHTMLFactory().nonEmptyDiv());
		header.toHTMLElement().addClass("modal-header");
		content.toHTMLElement().content(header.toHTMLElement());
		
		body = getFactory().wrap(getFactory().getHTMLFactory().nonEmptyDiv());
		body.toHTMLElement().addClass("modal-body");
		content.toHTMLElement().content(body.toHTMLElement());					
		
		footer = getFactory().wrap(getFactory().getHTMLFactory().nonEmptyDiv());
		footer.toHTMLElement().addClass("modal-footer");
		content.toHTMLElement().content(footer.toHTMLElement());
	}

	@Override
	public TagBootstrapElement getHeader() {
		return header;
	}

	@Override
	public TagBootstrapElement getBody() {
		return body;
	}

	@Override
	public TagBootstrapElement getFooter() {
		return footer;
	}

	@Override
	public void bindTrigger(HTMLElement<?> trigger) {
		if (htmlElement.getId() == null) {
			htmlElement.id(getFactory().getHTMLFactory().nextId());
		}
		trigger.attribute("data-toggle", "modal");
		trigger.attribute("data-target", "#" + htmlElement.getId());
	}
	
	@Override
	public String activatorScript() {
		if (htmlElement.getId() == null) {
			htmlElement.id(getFactory().getHTMLFactory().nextId());
		}
		return "jQuery('#" + htmlElement.getId() + "').modal('show')";
	}

	@Override
	public void bindDismisser(HTMLElement<?> dismisser) {
		dismisser.attribute("data-dismiss", "modal");
	}

	@Override
	public Modal scrollable(boolean scrollable) {
		if (scrollable) {
			dialog.addClass("modal-dialog-scrollable");
		} else {
			dialog.removeClass("modal-dialog-scrollable");
		}
		return this;
	}

	@Override
	public Modal centered(boolean centered) {
		if (centered) {
			dialog.addClass("modal-dialog-centered");
		} else {
			dialog.removeClass("modal-dialog-centered");
		}
		return this;
	}

	@Override
	public Modal size(Breakpoint size) {
		dialog.removeClass("modal-sm");
		dialog.removeClass("modal-lg");
		dialog.removeClass("modal-xl");
		if (size != null) {
			switch (size) {
			case EXTRA_LARGE:
			case LARGE:
			case SMALL:
				dialog.addClass("modal-" + size.code);
				break;
			default:
				break;			
			}
		}
		return this;
	}

}
