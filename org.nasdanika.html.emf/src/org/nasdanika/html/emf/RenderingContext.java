package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * Provides context-specific rendering information.
 * @author Pavel Vlasov
 *
 */
public interface RenderingContext {
	
	Renderer getRenderer(EObject obj);
	
	Renderer getRenderer(EClass eClass);
	

}
