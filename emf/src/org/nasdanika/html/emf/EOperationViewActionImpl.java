package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;

public class EOperationViewActionImpl<T extends EObject, V extends ViewAction<T>> extends ETypedElementViewActionImpl<T,EOperation,V> implements EOperationViewAction<T> {
	
	public EOperationViewActionImpl(T semanticElement, EOperation operation) {
		super(semanticElement, operation);
	}
	
	public EOperationViewActionImpl(V semanticElementViewAction, EOperation operation) {
		super(semanticElementViewAction, operation);
	}	

}
