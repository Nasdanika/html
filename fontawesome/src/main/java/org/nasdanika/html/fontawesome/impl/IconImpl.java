package org.nasdanika.html.fontawesome.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.fontawesome.Icon;

class IconImpl<T extends HTMLElement<?>> implements Icon<T> {
	/**
	 * 
	 */
	private final T target;

	/**
	 * @param htmlElement
	 */
	IconImpl(String icon, Style style, T htmlElement) {
		target = htmlElement;
		switch (style) {
		case BRANDS:
			target.addClass("fab");
			break;
		case REGULAR:
			target.addClass("far");
			break;
		case SOLID:
			target.addClass("fas");
			break;
		}
		target.addClass("fa-"+icon);
	}
		
	@Override
	public T toHTMLElement() {
		return target;
	}
	
	@Override
	public Icon<T> fixedWidth() {
		target.addClass("fa-fw");
		return this;
	}

	@Override
	public Icon<T> li() {
		target.addClass("fa-li");
		return this;
	}

	@Override
	public Icon<T> ul() {
		target.addClass("fa-ul");
		return this;
	}

	@Override
	public Icon<T> spin() {
		target.addClass("fa-spin");
		return this;
	}

	@Override
	public Icon<T> pullLeft() {
		target.addClass("pull-left", "fa-border");
		return this;
	}

	@Override
	public Icon<T> pullRight() {
		target.addClass("pull-right", "fa-border");
		return this;
	}

	@Override
	public Icon<T> rotate(Rotate rotate) {
		switch (rotate) {
		case R180:
			target.addClass("fa-rotate-180");
			break;
		case R270:
			target.addClass("fa-rotate-270");
			break;
		case R90:
			target.addClass("fa-rotate-90");
			break;
		default:
			break;
		
		}
		return this;
	}

	@Override
	public Icon<T> flip(Flip flip) {
		target.addClass("fa-flip-"+flip.name());
		return this;
	}

	@Override
	public Icon<T> size(Size size) {
		size(size, target);
		return this;
	}

	static void size(Size size, HTMLElement<?> target) {
		switch (size) {
		case large:
			target.addClass("fa-lg");
			break;
		case x2:
			target.addClass("fa-2x");
			break;
		case x3:
			target.addClass("fa-3x");
			break;
		case x4:
			target.addClass("fa-4x");
			break;
		case x5:
			target.addClass("fa-5x");
			break;
		default:
			throw new IllegalArgumentException("Unexpected size: "+size);	
		}
	}

	@Override
	public String toString() {
		return target.toString();
	}
	
	@Override
	public Object produce(int indent) {
		return target.produce(indent);
	}
	
}