package org.nasdanika.html.emf.legacy;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EStructuralFeatureElementViewActionImpl<T extends EObject, F extends EStructuralFeature, V extends SimpleEObjectViewAction<T>, E> extends ETypedElementElementViewActionImpl<T,F,V,E> implements EStructuralFeatureElementViewAction<T,F,E> {
	
	public EStructuralFeatureElementViewActionImpl(T semanticElement, F feature, E element, int index) {
		super(semanticElement, feature, element, index);
	}

	public EStructuralFeatureElementViewActionImpl(V semanticElementViewAction, F feature, E element, int index) {
		super(semanticElementViewAction, feature, element, index);
	}

}
