package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EStructuralFeature;

public class EStructuralFeatureViewAction<T extends EStructuralFeature> extends ENamedElementViewAction<T> {

	public EStructuralFeatureViewAction(T value) {
		super(value);
	}

}
