package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * {@link ViewAction} for a {@link EStructuralFeature} of {@link EObject}.
 * @author Pavel
 *
 * @param <T>
 * @param <F>
 */
public interface EStructuralFeatureViewAction<T extends EObject, F extends EStructuralFeature> extends ViewAction<T>  {

	/**
	 * @return the unerlying {@link EStructuralFeature}
	 */
	F getEStructuralFeature();
	
}
