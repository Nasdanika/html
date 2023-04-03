package org.nasdanika.html.ecore.gen;

import org.eclipse.emf.ecore.ENamedElement;
import org.nasdanika.common.Context;

public class ENamedElementLabelBuilder<T extends ENamedElement> extends EModelElementLabelBuilder<T> {

	public ENamedElementLabelBuilder(T target, Context context) {
		super(target, context);
	}

}
