package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * {@link EStructuralFeatureViewAction} for the feature value or an element of it. 
 * @author Pavel
 *
 * @param <T>
 * @param <F>
 */
public interface EStructuralFeatureElementViewAction<T extends EObject, F extends EStructuralFeature, E> extends EStructuralFeatureViewAction<T,F>  {

	/**
	 * @return the underlying {@link EStructuralFeature}
	 */
	E getElement();
	
	int getElementIndex();
	
}
