package org.nasdanika.html.emf;

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
public interface EObjectActionResolver extends org.nasdanika.common.Consumer<java.util.function.Function<EObject, Action>> {
	

}
