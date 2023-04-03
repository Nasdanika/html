package org.nasdanika.html.ecore.gen;

import org.eclipse.emf.ecore.EModelElement;
import org.nasdanika.common.Context;
import org.nasdanika.html.emf.EObjectLabelBuilder;

public class EModelElementLabelBuilder<T extends EModelElement> extends EObjectLabelBuilder<T> {

	public EModelElementLabelBuilder(T target, Context context) {
		super(target, context);
	}

}
