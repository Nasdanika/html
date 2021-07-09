package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;

public class ETypedElementElementViewActionImpl<T extends EObject, E extends ETypedElement, V extends SimpleEObjectViewAction<T>, EE> extends ETypedElementViewActionImpl<T,E,V> implements ETypedElementElementViewAction<T,E,EE> {
	
	private EE element;
	private int elementIndex;

	public ETypedElementElementViewActionImpl(T semanticElement, E typedElement, EE element, int index) {
		super(semanticElement, typedElement);
		this.element = element;
		this.elementIndex = index;
	}

	public ETypedElementElementViewActionImpl(V semanticElementViewAction, E typedElement, EE element, int index) {
		super(semanticElementViewAction, typedElement);
		this.element = element;
		this.elementIndex = index;
	}

	@Override
	public EE getElement() {
		return element;
	}
	
	@Override
	public int getElementIndex() {
		return elementIndex;
	}

}
