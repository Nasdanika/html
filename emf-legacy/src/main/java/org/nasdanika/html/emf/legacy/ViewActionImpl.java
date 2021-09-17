package org.nasdanika.html.emf.legacy;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.app.impl.ActionImpl;

public class ViewActionImpl<T extends EObject> extends ActionImpl implements ViewAction<T> {
	
	private T semanticElement;

	public ViewActionImpl(T semanticElement) {
		this.semanticElement = semanticElement;
	}
	
	@Override
	public T getSemanticElement() {
		return semanticElement;
	}

}
