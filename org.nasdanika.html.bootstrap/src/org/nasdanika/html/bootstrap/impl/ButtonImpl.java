package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.Color;

public class ButtonImpl<H extends HTMLElement<?>> extends WrappingBootstrapElementImpl<H,Button<H>> implements Button<H> {

	private Color color;
	private boolean outline;
	
	public ButtonImpl(BootstrapFactory factory, H htmlElement, Color color, boolean outline) {
		super(factory, htmlElement);
		htmlElement.addClass("btn")
			.addClassConditional(color != null && color.code != null && !outline, color ==  null ? null : "btn-"+color.code)
			.addClassConditional(color != null && color.code != null && outline,  color ==  null ? null : "btn-outline-"+color.code)
			.attribute("role", "button", htmlElement instanceof Tag && ((Tag) htmlElement).is(TagName.a) ); // For <a>
		
		this.color = color;
		this.outline = outline;
	}
	
	Color getColor() {
		return color;
	}
	
	boolean isOutline() {
		return outline;
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

	@Override
	public void close() throws Exception {
		htmlElement.close();		
	}

	@Override
	public Object produce(int indent) {
		return htmlElement.produce(indent);
	}

}
