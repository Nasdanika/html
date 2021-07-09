package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;

/**
 * {@link ViewAction} for a {@link ETypedElement} member of {@link EObject} - {@link EStructuralFeature} or {@link EOperation}.
 * @author Pavel
 *
 * @param <T>
 * @param <F>
 */
public interface ETypedElementViewAction<T extends EObject, E extends ETypedElement> extends ViewAction<T>  {

	/**
	 * @return the underlying {@link ETypedElement}
	 */
	E getETypedElement();
	
}
