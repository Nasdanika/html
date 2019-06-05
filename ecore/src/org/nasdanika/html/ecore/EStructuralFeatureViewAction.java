package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EStructuralFeature;

public class EStructuralFeatureViewAction<T extends EStructuralFeature> extends ETypedElementViewAction<T> {

	public EStructuralFeatureViewAction(T value) {
		super(value);
	}
	
}
