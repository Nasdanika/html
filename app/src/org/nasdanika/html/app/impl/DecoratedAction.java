package org.nasdanika.html.app.impl;

import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.ViewGenerator;

public class DecoratedAction extends ActionImpl implements Decorator {
	
	private Decorator decorator;
	
	public DecoratedAction(Decorator decorator) {
		this.decorator = decorator;
	}

	@Override
	public void decorate(Object target, ViewGenerator viewGenerator) {
		if (decorator != null) {
			decorator.decorate(target, viewGenerator);
		}

	}

}
