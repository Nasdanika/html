package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EClassifier;

public class EClassifierViewAction<T extends EClassifier> extends ENamedElementViewAction<T> {
	
	public static final String iconsBase = "https://www.nasdanika.org/resources/images/ecore/";

	public EClassifierViewAction(T value) {
		super(value);
	}

}
