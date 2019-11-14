package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Spacing;

public class SpacingImpl<B extends BootstrapElement<?, ?>> implements Spacing<B> {
	
	private B bootstrapElement;
	private String prefix;

	SpacingImpl(B bootstrapElement, String prefix) {
		this.bootstrapElement = bootstrapElement;
		this.prefix = prefix;
	}

	@Override
	public B toBootstrapElement() {
		return bootstrapElement;
	}
	
	private void suffix(String suffix) {
		bootstrapElement.toHTMLElement().addClass(prefix+suffix);
	}

	@Override
	public Spacing<B> top(Breakpoint breakpoint, Size size) {
		suffix(breakpoint.size("t", size));
		return this;
	}

	@Override
	public Spacing<B> bottom(Breakpoint breakpoint, Size size) {
		suffix(breakpoint.size("b", size));
		return this;
	}

	@Override
	public Spacing<B> left(Breakpoint breakpoint, Size size) {
		suffix(breakpoint.size("l", size));
		return this;
	}

	@Override
	public Spacing<B> right(Breakpoint breakpoint, Size size) {
		suffix(breakpoint.size("r", size));
		return this;
	}

	@Override
	public Spacing<B> x(Breakpoint breakpoint, Size size) {
		suffix(breakpoint.size("x", size));
		return this;
	}

	@Override
	public Spacing<B> y(Breakpoint breakpoint, Size size) {
		suffix(breakpoint.size("y", size));
		return this;
	}

	@Override
	public Spacing<B> all(Breakpoint breakpoint, Size size) {
		bootstrapElement.toHTMLElement().addClass(breakpoint.size(prefix, size));
		// TODO Auto-generated method stub
		return this;
	}

}
