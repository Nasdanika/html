package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;

/**
 * {@link ViewAction} for a {@link EOperation} of {@link EObject}.
 * @author Pavel
 *
 * @param <T>
 * @param <F>
 */
public interface EOperationViewAction<T extends EObject> extends ETypedElementViewAction<T, EOperation>  {
	
}
