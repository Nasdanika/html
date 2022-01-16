package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.model.app.util.ActionSupplier;

/**
 * Adapts {@link EObject} to {@link ActionSupplier}.
 * @author Pavel Vlasov
 *
 */
public abstract class EObjectActionSupplier<T extends EObject> implements EcoreActionSupplier {
		
	protected T eObject;

	public EObjectActionSupplier(T eObject) {
		this.eObject = eObject;
	}	
	
	/**
	 * Adapts child eObject to {@link ActionSupplier} and adds to the list of children to be configured.
	 * @param child
	 * @return
	 */
	protected EcoreActionSupplier adaptChild(EObject child) {
		return EObjectAdaptable.adaptTo(child, EcoreActionSupplier.class);
	}
	
}
