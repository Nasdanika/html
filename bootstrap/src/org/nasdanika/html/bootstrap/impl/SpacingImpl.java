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
	public Spacing<B> top(Breakpoint size, Size space) {
		suffix(size.size("t", space));
		return this;
	}

	@Override
	public Spacing<B> bottom(Breakpoint size, Size space) {
		suffix(size.size("b", space));
		return this;
	}

	@Override
	public Spacing<B> left(Breakpoint size, Size space) {
		suffix(size.size("l", space));
		return this;
	}

	@Override
	public Spacing<B> right(Breakpoint size, Size space) {
		suffix(size.size("r", space));
		return this;
	}

	@Override
	public Spacing<B> x(Breakpoint size, Size space) {
		suffix(size.size("x", space));
		return this;
	}

	@Override
	public Spacing<B> y(Breakpoint size, Size space) {
		suffix(size.size("y", space));
		return this;
	}

}
