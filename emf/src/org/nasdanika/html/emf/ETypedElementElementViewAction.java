package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;

/**
 * {@link EStructuralFeatureViewAction} for the feature value or an element of it. 
 * @author Pavel
 *
 * @param <T>
 * @param <F>
 */
public interface ETypedElementElementViewAction<T extends EObject, E extends ETypedElement, EE> extends ETypedElementViewAction<T,E>  {

	/**
	 * @return the underlying {@link EStructuralFeature}
	 */
	EE getElement();
	
	int getElementIndex();
	
}
