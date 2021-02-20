package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.emf.ViewActionStorable;

/**
 * Adapts {@link EObject} to {@link ViewActionStorable}.
 * @author Pavel Vlasov
 *
 */
public abstract class EObjectViewActionStorable<T extends EObject> implements ViewActionStorable {
		
	protected T eObject;

	public EObjectViewActionStorable(T eObject) {
		this.eObject = eObject;
	}	
			
	
}
