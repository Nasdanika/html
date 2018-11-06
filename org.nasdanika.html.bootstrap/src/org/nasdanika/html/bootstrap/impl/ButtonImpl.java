package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.Color;

class ButtonImpl<H extends HTMLElement<?>> extends BootstrapElementImpl<H> implements Button<H> {

	H htmlElement;
	
	public ButtonImpl(BootstrapFactory factory, H htmlElement, Color color, boolean outline) {
		super(factory);
		this.htmlElement = htmlElement;
		htmlElement.addClass("btn")
		.addClassConditional(!outline, "btn-"+color.code)
		.addClassConditional(!outline, "btn-outline-"+color.code)
		.attribute("role", "button", htmlElement instanceof Tag && ((Tag) htmlElement).is(TagName.a) ); // For <a>
	}

	@Override
	public H toHTMLElement() {
		return htmlElement;
	}

	@Override
	public Button<H> large() {
		return large(true);
	}

	@Override
	public Button<H> large(boolean large) {
		htmlElement.addClassConditional(large, "btn-lg");
		return this;
	}

	@Override
	public Button<H> small() {
		return small(true);
	}

	@Override
	public Button<H> small(boolean small) {
		htmlElement.addClassConditional(small, "btn-sm");
		return this;
	}

	@Override
	public Button<H> block() {
		return block(true);
	}

	@Override
	public Button<H> block(boolean block) {
		htmlElement.addClassConditional(block, "btn-block");
		return this;
	}

	@Override
	public Button<H> active() {
		return active(true);
	}

	@Override
	public Button<H> active(boolean active) {
		htmlElement.addClassConditional(active && !(htmlElement instanceof Button), "active");
		return this;
	}

	@Override
	public Button<H> disabled() {
		return disabled(true);
	}

	@Override
	public Button<H> disabled(boolean disabled) {
		if (htmlElement instanceof org.nasdanika.html.Button) {
			((org.nasdanika.html.Button) htmlElement).disabled();
		} else {
			htmlElement.addClassConditional(disabled, "disabled");
		}
		return this;
	}

	@Override
	public Button<H> dataToggle() {
		return dataToggle(true);
	}

	@Override
	public Button<H> dataToggle(boolean dataToggle) {
		htmlElement.attribute("data-toggle", "button", dataToggle);
		return null;
	}

}
