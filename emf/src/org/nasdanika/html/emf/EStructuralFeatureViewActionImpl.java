package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EStructuralFeatureViewActionImpl<T extends EObject, F extends EStructuralFeature> extends ViewActionImpl<T> implements EStructuralFeatureViewAction<T,F> {
	
	private F feature;

	public EStructuralFeatureViewActionImpl(T semanticElement, F feature) {
		super(semanticElement);
		this.feature = feature;
	}
	
	@Override
	public F getEStructuralFeature() {
		return feature;
	}

}
