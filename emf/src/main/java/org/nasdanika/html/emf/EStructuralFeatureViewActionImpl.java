package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EStructuralFeatureViewActionImpl<T extends EObject, F extends EStructuralFeature, V extends ViewAction<T>> extends ETypedElementViewActionImpl<T,F,V> implements EStructuralFeatureViewAction<T,F> {
	
	public EStructuralFeatureViewActionImpl(T semanticElement, F feature) {
		super(semanticElement, feature);
	}
	
	public EStructuralFeatureViewActionImpl(V semanticElementViewAction, F feature) {
		super(semanticElementViewAction, feature);
	}	

}
