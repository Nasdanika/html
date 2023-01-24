package org.nasdanika.html.emf;

import java.util.Objects;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Util;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.ncore.ModelElement;

/**
 * Marker interface for adapters attached to actions created by
 * {@link EObjectActionBuilder} instances.
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
		
		default Action getAction(EObject semanticElement) {
			return getAction(e -> Objects.equals(e, semanticElement) 
					|| e instanceof ModelElement && semanticElement instanceof ModelElement && !Util.isBlank(((ModelElement) e).getUuid()) && ((ModelElement) e).getUuid().equals(((ModelElement) semanticElement).getUuid()));
		}
		
		Action getAction(Predicate<EObject> semanticElementPredicate);
		
		URI resolve(Action action, URI base);

		default URI resolve(Action action, Action base) {
			URI bURI = resolve(base, (URI) null);
			return resolve(action, bURI);
		}
		
	}	

}
