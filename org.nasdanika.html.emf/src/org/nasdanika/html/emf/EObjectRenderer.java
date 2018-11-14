package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * Renderer which binds to EObject data.
 * @author Pavel Vlasov
 *
 */
public class EObjectRenderer<T extends EObject, RC extends RenderingContext> extends EClassBootstrapContextHelpRenderer<RC> {

	protected T eObject;

	public EObjectRenderer(T eObject, BootstrapFactory bootstrapFactory, RC renderingContext) {
		super(eObject.eClass(), bootstrapFactory, renderingContext);
		this.eObject = eObject;
	}
	
	public EObjectRenderer(EObject eObject, RC renderingContext) {
		super(eObject.eClass(), renderingContext);
	}
	
	// Object path

}
