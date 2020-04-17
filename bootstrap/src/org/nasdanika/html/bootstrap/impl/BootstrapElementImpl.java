package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Spacing;
import org.nasdanika.html.bootstrap.Text;

public abstract class BootstrapElementImpl<H extends HTMLElement<?>,B extends BootstrapElement<H,?>> implements BootstrapElement<H,B> {
	
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
		if (htmlElement != null && color != Color.NONE) {
			for (Placement p: placement) {
				htmlElement.addClass("border-"+p.name().toLowerCase());
			}
			htmlElement.addClassConditional(placement.length == 0, "border");
			if (color != null && color.code != null) {
				htmlElement.addClass("border-"+color.code);
			}
		}		
		return (B) this;
	}
	
	@SuppressWarnings("unchecked")
	public B background(Color color) {
		toHTMLElement().addClassConditional(color != null && color.code != null, "bg-"+color.code);
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
				toHTMLElement().addClassConditional(color != null && color.code != null, "text-"+color.code);
				return this;
			}

			@Override
			public Text<B> alignment(Alignment alignment) {
				toHTMLElement().addClass("text-"+alignment.name().toLowerCase());
				return this;
			}

			@Override
			public Text<B> alignment(Breakpoint breakpoint, Alignment alignment) {
				String code = breakpoint.code;
				String prefix = code.length() == 0 ? "text" : "text-"+code;				
				toHTMLElement().addClass(prefix+"-"+alignment.name().toLowerCase());
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
				toHTMLElement().addClassConditional(italic, "font-italic");
				return this;
			}

			@Override
			public Text<B> nowrap() {
				return nowrap(true);
			}

			@Override
			public Text<B> nowrap(boolean nowrap) {
				toHTMLElement().addClassConditional(nowrap, "text-nowrap");
				return this;
			}

			@Override
			public Text<B> truncate() {
				return truncate(true);
			}

			@Override
			public Text<B> truncate(boolean truncate) {
				toHTMLElement().addClassConditional(truncate, "text-truncate");
				return this;
			}
			
		};
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public org.nasdanika.html.bootstrap.Float<B> _float() {
		return new FloatImpl<B>((B) this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Spacing<B> margin() {
		return new SpacingImpl<B>((B) this, "m");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Spacing<B> padding() {
		return new SpacingImpl<B>((B) this, "p");
	}

}
