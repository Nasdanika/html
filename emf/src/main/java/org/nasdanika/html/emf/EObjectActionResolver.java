package org.nasdanika.html.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.model.app.Action;

/**
 * Marker interface for adapters attached to actions created by
 * {@link EObjectActionProvider} instances.
 * 
 * This interface is used to perform action configuration which requires action cross-referencing 
 * such as linking from one action to another. Resolver's execute() is invoked after all actions are created
 * and the action hierarchy is established. 
 *  
 * Parameter function provides mapping of semantic elements to actions created during the adapter execution phase
 * and collected by the registry.
 *  
 * @author Pavel
 *
 */
public interface EObjectActionResolver extends org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context> {
	
	/**
	 * Resolver context providing access to semantic element to action mapping and URI resolver.
	 * @author Pavel
	 *
	 */
	interface Context {
		
		Action getAction(EObject semanticElement);
		
		URI resolve(Action action, URI base);

		default URI resolve(Action action, Action base) {
			URI bURI = resolve(base, (URI) null);
			return resolve(action, bURI);
		}
		
	}	

}
