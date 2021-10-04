package org.nasdanika.html.flow;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.model.app.util.ActionSupplier;

/**
 * Adapts {@link EObject} to {@link ActionSupplier}.
 * @author Pavel Vlasov
 *
 */
public abstract class EObjectActionSupplier<T extends EObject> implements ActionSupplier {
		
	protected T eObject;

	public EObjectActionSupplier(T eObject) {
		this.eObject = eObject;
	}	
	
	/**
	 * Adapts child eObject to {@link ActionSupplier} and adds to the list of children to be configured.
	 * @param child
	 * @return
	 */
	protected ActionSupplier adaptChild(EObject child) {
		return EObjectAdaptable.adaptTo(child, ActionSupplier.class);
	}
	
}
