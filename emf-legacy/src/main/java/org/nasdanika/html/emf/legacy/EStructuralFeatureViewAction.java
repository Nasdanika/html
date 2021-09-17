package org.nasdanika.html.emf.legacy;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * {@link ViewAction} for a {@link EStructuralFeature} of {@link EObject}.
 * @author Pavel
 *
 * @param <T>
 * @param <F>
 */
public interface EStructuralFeatureViewAction<T extends EObject, F extends EStructuralFeature> extends ETypedElementViewAction<T, F>  {
	
}
