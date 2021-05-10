package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class EStructuralFeatureElementViewActionImpl<T extends EObject, F extends EStructuralFeature, E> extends EStructuralFeatureViewActionImpl<T,F> implements EStructuralFeatureElementViewAction<T,F,E> {
	
	private E element;
	private int elementIndex;

	public EStructuralFeatureElementViewActionImpl(T semanticElement, F feature, E element, int index) {
		super(semanticElement, feature);
		this.element = element;
		this.elementIndex = index;
	}
	
	@Override
	public E getElement() {
		return element;
	}
	
	@Override
	public int getElementIndex() {
		return elementIndex;
	}

}
