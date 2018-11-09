package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.Dropdown;

class ButtonGroupImpl extends DivWrappingBootstrapElementImpl implements ButtonGroup {
	
	ButtonGroupImpl(BootstrapFactory factory, boolean vertical) {
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
	public ButtonGroup add(Button<?> button) {
		htmlElement.content(button);
		return this;
	}

	@Override
	public ButtonGroup add(Dropdown dropdown) {
		htmlElement.content(dropdown);
		return this;
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
