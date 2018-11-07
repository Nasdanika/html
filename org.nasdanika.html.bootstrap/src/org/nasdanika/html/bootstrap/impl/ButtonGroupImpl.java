package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.Dropdown;

class ButtonGroupImpl extends BootstrapElementImpl<Tag> implements ButtonGroup {
	
	private Tag tag;

	public ButtonGroupImpl(BootstrapFactory factory, boolean vertical) {
		super(factory);
		tag = getFactory().getHTMLFactory().div().addClass(vertical? "btn-group-vertical" : "btn-group").attribute("role", "group");
	}

	@Override
	public Tag toHTMLElement() {
		return tag;
	}

	@Override
	public ButtonGroup large() {
		return large(true);
	}

	@Override
	public ButtonGroup large(boolean large) {
		tag.addClass("btn-group-lg");
		return this;
	}

	@Override
	public ButtonGroup small() {
		return small(true);
	}

	@Override
	public ButtonGroup small(boolean small) {
		tag.addClass("btn-group-sm");
		return this;
	}

	@Override
	public ButtonGroup add(Button<?> button) {
		tag.content(button);
		return this;
	}

	@Override
	public ButtonGroup add(Dropdown dropdown) {
		tag.content(dropdown);
		return this;
	}

	@Override
	public void close() throws Exception {
		tag.close();		
	}

	@Override
	public Object produce(int indent) {
		return tag.produce(indent);
	}

}
