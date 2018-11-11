package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * Renderer which binds to EObject data.
 * @author Pavel Vlasov
 *
 */
public class EObjectRenderer<T extends EObject> extends EClassBootstrapContextHelpRenderer {

	protected T eObject;

	public EObjectRenderer(T eObject, BootstrapFactory bootstrapFactory) {
		super(eObject.eClass(), bootstrapFactory);
		this.eObject = eObject;
	}
	
	public EObjectRenderer(EObject eObject) {
		super(eObject.eClass());
	}
	
	// Object path

}
