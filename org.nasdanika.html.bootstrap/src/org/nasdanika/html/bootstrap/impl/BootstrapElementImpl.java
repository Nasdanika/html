package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Text;

abstract class BootstrapElementImpl<H extends HTMLElement<?>,B extends BootstrapElement<H,B>> implements BootstrapElement<H,B> {
	
	private BootstrapFactory factory;

	protected BootstrapElementImpl(BootstrapFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public BootstrapFactory getFactory() {
		return factory;
	}
	
	@Override
	public String toString() {
		return toHTMLElement().toString();
	}
	
	@Override
	public Object produce(int indent) {
		return toHTMLElement().produce(indent);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public B border(Color color, Placement... placement) {
		H htmlElement = toHTMLElement();
		if (htmlElement != null) {
			for (Placement p: placement) {
				htmlElement.addClass("border-"+p.name().toLowerCase());
			}
			htmlElement.addClassConditional(placement.length == 0, "border");
			htmlElement.addClassConditional(color != null, "border-"+color.code);
		}		
		return (B) this;
	}
	
	@SuppressWarnings("unchecked")
	public B background(Color color) {
		toHTMLElement().addClass("bg-"+color.code);
		return (B) this;
	}
	
	@Override
	public Text<B> text() {
		return new Text<B>() {

			@SuppressWarnings("unchecked")
			@Override
			public B toBootstrapElement() {
				return (B) BootstrapElementImpl.this;
			}

			@Override
			public Text<B> color(Color color) {
				toHTMLElement().addClass("text-"+color.code);
				return this;
			}

			@Override
			public Text<B> alignment(Alignment alignment) {
				toHTMLElement().addClass("text-"+alignment.name().toLowerCase());
				return this;
			}

			@Override
			public Text<B> alignment(DeviceSize deviceSize, Alignment alignment) {
				toHTMLElement().addClass("text-"+deviceSize.code+"-"+alignment.name().toLowerCase());
				return this;
			}

			@Override
			public Text<B> transform(Transform transform) {
				toHTMLElement().addClass("text-"+transform.name().toLowerCase());
				return this;
			}

			@Override
			public Text<B> weight(Weight weight) {
				toHTMLElement().addClass("font-weight-"+weight.name().toLowerCase());
				return this;
			}

			@Override
			public Text<B> monospace() {
				return monospace(true);
			}

			@Override
			public Text<B> monospace(boolean monospace) {
				toHTMLElement().addClassConditional(monospace, "text-monospace");
				return this;
			}

			@Override
			public Text<B> italic() {
				return italic(true);
			}

			@Override
			public Text<B> italic(boolean italic) {
				toHTMLElement().addClassConditional(italic, "text-italic");
				return this;
			}
			
		};
	}

}
