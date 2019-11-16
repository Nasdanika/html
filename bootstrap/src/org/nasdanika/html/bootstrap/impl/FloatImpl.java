package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Float;

public class FloatImpl<B extends BootstrapElement<?, ?>> implements org.nasdanika.html.bootstrap.Float<B> {
	
	private B bootstrapElement;

	public FloatImpl(B bootstrapElement) {
		this.bootstrapElement = bootstrapElement;
	}

	@Override
	public B toBootstrapElement() {
		return bootstrapElement;
	}

	@Override
	public Float<B> right() {
		bootstrapElement.toHTMLElement().addClass("float-right");
		return this;
	}

	@Override
	public Float<B> right(Breakpoint breakpoint) {
		if (breakpoint == Breakpoint.DEFAULT) {
			return right();
		}
		bootstrapElement.toHTMLElement().addClass("float-"+breakpoint.code+"-right");
		return this;
	}

	@Override
	public Float<B> left() {
		bootstrapElement.toHTMLElement().addClass("float-left");
		return this;
	}

	@Override
	public Float<B> left(Breakpoint breakpoint) {
		if (breakpoint == Breakpoint.DEFAULT) {
			return left();
		}
		bootstrapElement.toHTMLElement().addClass("float-"+breakpoint.code+"-left");
		return this;
	}

	@Override
	public Float<B> none() {
		bootstrapElement.toHTMLElement().addClass("float-none");
		return this;
	}

	@Override
	public Float<B> none(Breakpoint breakpoint) {
		if (breakpoint == Breakpoint.DEFAULT) {
			return none();
		}
		bootstrapElement.toHTMLElement().addClass("float-"+breakpoint.code+"-none");
		return this;
	}

}
