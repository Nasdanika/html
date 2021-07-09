package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;

/**
 * {@link EStructuralFeatureViewAction} for the feature value or an element of it. 
 * @author Pavel
 *
 * @param <T>
 * @param <F>
 */
public interface EOperationElementViewAction<T extends EObject, E> extends EOperationViewAction<T>, ETypedElementElementViewAction<T, EOperation, E>  {
	
}
