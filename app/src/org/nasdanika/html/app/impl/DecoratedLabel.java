package org.nasdanika.html.app.impl;

import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.ViewGenerator;

public class DecoratedLabel extends LabelImpl implements Decorator {
	
	private Decorator decorator;
	
	public DecoratedLabel(Decorator decorator) {
		this.decorator = decorator;
	}

	@Override
	public void decorate(Object target, ViewGenerator viewGenerator) {
		if (decorator != null) {
			decorator.decorate(target, viewGenerator);
		}

	}

}
