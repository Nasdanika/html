package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;

public class EOperationElementViewActionImpl<T extends EObject, V extends SimpleEObjectViewAction<T>, E> extends ETypedElementElementViewActionImpl<T,EOperation,V,E> implements EOperationElementViewAction<T,E> {
	
	public EOperationElementViewActionImpl(T semanticElement, EOperation operation, E element, int index) {
		super(semanticElement, operation, element, index);
	}

	public EOperationElementViewActionImpl(V semanticElementViewAction, EOperation operation, E element, int index) {
		super(semanticElementViewAction, operation, element, index);
	}

}
