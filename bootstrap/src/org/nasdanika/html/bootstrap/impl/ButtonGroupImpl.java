package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.ButtonGroup;

public class ButtonGroupImpl extends DivWrappingBootstrapElementImpl<ButtonGroup> implements ButtonGroup {
	
	public ButtonGroupImpl(BootstrapFactory factory, boolean vertical) {
		super(factory);
		htmlElement.addClass(vertical? "btn-group-vertical" : "btn-group").attribute("role", "group");
	}

	@Override
	public ButtonGroup large() {
		return large(true);
	}

	@Override
	public ButtonGroup large(boolean large) {
		htmlElement.addClass("btn-group-lg");
		return this;
	}

	@Override
	public ButtonGroup small() {
		return small(true);
	}

	@Override
	public ButtonGroup small(boolean small) {
		htmlElement.addClass("btn-group-sm");
		return this;
	}

	@Override
	public ButtonGroup add(Object content) {
		htmlElement.content(content);
		return this;
	}

	@Override
	public Object produce(int indent) {
		return htmlElement.produce(indent);
	}

}
