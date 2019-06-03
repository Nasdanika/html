package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.ETypedElement;

public class ETypedElementViewAction<T extends ETypedElement> extends ENamedElementViewAction<T> {
	
	public ETypedElementViewAction(T value) {
		super(value);
	}

}
