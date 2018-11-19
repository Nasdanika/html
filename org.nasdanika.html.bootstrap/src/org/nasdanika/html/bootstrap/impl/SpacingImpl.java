package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.DeviceSize;
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
	public Spacing<B> top(int space) {
		suffix("t-"+space);
		return this;
	}

	@Override
	public Spacing<B> top(DeviceSize size, int space) {
		suffix("t-"+size.code+"-"+space);
		return this;
	}

	@Override
	public Spacing<B> topAuto() {
		suffix("t-auto");
		return this;
	}

	@Override
	public Spacing<B> topAuto(DeviceSize size) {
		suffix("t-"+size.code+"-auto");
		return this;
	}

	@Override
	public Spacing<B> bottom(int space) {
		suffix("b-"+space);
		return this;
	}

	@Override
	public Spacing<B> bottom(DeviceSize size, int space) {
		suffix("b-"+size.code+"-"+space);
		return this;
	}

	@Override
	public Spacing<B> bottomAuto() {
		suffix("b-auto");
		return this;
	}

	@Override
	public Spacing<B> bottomAuto(DeviceSize size) {
		suffix("b-"+size.code+"-auto");
		return this;
	}

	@Override
	public Spacing<B> left(int space) {
		suffix("l-"+space);
		return this;
	}

	@Override
	public Spacing<B> left(DeviceSize size, int space) {
		suffix("l-"+size.code+"-"+space);
		return this;
	}

	@Override
	public Spacing<B> leftAuto() {
		suffix("l-auto");
		return this;
	}

	@Override
	public Spacing<B> leftAuto(DeviceSize size) {
		suffix("l-"+size.code+"-auto");
		return this;
	}

	@Override
	public Spacing<B> right(int space) {
		suffix("r-"+space);
		return this;
	}

	@Override
	public Spacing<B> right(DeviceSize size, int space) {
		suffix("r-"+size.code+"-"+space);
		return this;
	}

	@Override
	public Spacing<B> rightAuto() {
		suffix("r-auto");
		return this;
	}

	@Override
	public Spacing<B> rightAuto(DeviceSize size) {
		suffix("r-"+size.code+"-auto");
		return this;
	}

	@Override
	public Spacing<B> x(int space) {
		suffix("x-"+space);
		return this;
	}

	@Override
	public Spacing<B> x(DeviceSize size, int space) {
		suffix("x-"+size.code+"-"+space);
		return this;
	}

	@Override
	public Spacing<B> xAuto() {
		suffix("x-auto");
		return this;
	}

	@Override
	public Spacing<B> xAuto(DeviceSize size) {
		suffix("x-"+size.code+"-auto");
		return this;
	}

	@Override
	public Spacing<B> y(int space) {
		suffix("y-"+space);
		return this;
	}

	@Override
	public Spacing<B> y(DeviceSize size, int space) {
		suffix("y-"+size.code+"-"+space);
		return this;
	}

	@Override
	public Spacing<B> yAuto() {
		suffix("y-auto");
		return this;
	}

	@Override
	public Spacing<B> yAuto(DeviceSize size) {
		suffix("y-"+size.code+"-auto");
		return this;
	}

}
