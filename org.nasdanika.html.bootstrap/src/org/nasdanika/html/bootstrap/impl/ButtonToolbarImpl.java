package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Button;
import org.nasdanika.html.ButtonGroup;
import org.nasdanika.html.ButtonToolbar;
import org.nasdanika.html.HTMLFactory;

class ButtonToolbarImpl extends UIElementImpl<ButtonToolbar> implements ButtonToolbar {
	
	ButtonToolbarImpl(HTMLFactory factory, ButtonGroup... buttonGroups) {
		super(factory, "div");
		addClass("btn-toolbar");
		attribute("role", "toolbar");
		add(buttonGroups);
	}

	@Override
	public ButtonGroup buttonGroup(Button... buttons) {
		ButtonGroup ret = factory.buttonGroup(buttons);
		content.add(ret);
		return ret;
	}

	@Override
	public ButtonToolbar add(ButtonGroup... buttonGroups) {
		for (ButtonGroup g: buttonGroups) {
			if (g!=null) {
				content.add(g);
			}
		}
		return this;
	}

	@Override
	public boolean isEmpty() {
		return content.isEmpty();
	}

}
