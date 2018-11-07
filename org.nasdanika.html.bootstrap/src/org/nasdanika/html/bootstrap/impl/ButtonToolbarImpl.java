package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.ButtonToolbar;

class ButtonToolbarImpl extends BootstrapElementImpl<Tag> implements ButtonToolbar {
	
	private Tag tag;

	public ButtonToolbarImpl(BootstrapFactory factory) {
		super(factory);
		tag = getFactory().getHTMLFactory().div().addClass("btn-toolbar").attribute("role", "toolbar");
	}

	@Override
	public Tag toHTMLElement() {
		return tag;
	}

	@Override
	public void close() throws Exception {
		tag.close();
	}

	@Override
	public Object produce(int indent) {
		return tag.produce(indent);
	}

	@Override
	public ButtonToolbar add(ButtonGroup buttonGroup) {
		tag.content(buttonGroup);
		return this;
	}

}
