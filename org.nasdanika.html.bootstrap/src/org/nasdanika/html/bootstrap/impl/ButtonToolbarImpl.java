package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.ButtonToolbar;

public class ButtonToolbarImpl extends DivWrappingBootstrapElementImpl<ButtonToolbar> implements ButtonToolbar {
	
	public ButtonToolbarImpl(BootstrapFactory factory) {
		super(factory);
		htmlElement.addClass("btn-toolbar").attribute("role", "toolbar");
	}

	@Override
	public ButtonToolbar add(ButtonGroup buttonGroup) {
		htmlElement.content(buttonGroup);
		return this;
	}

}
