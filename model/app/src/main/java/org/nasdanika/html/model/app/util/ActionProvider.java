package org.nasdanika.html.model.app.util;

import java.util.function.BiConsumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.model.app.Action;

/**
 * Adapter interface. Takes a map of semantic elements to their default actions and creates an action for the target.
 * Implementations shall call the argument {@link BiConsumer} passing the target and resulting action as argument to use
 * later in resolve() phase.
 * @author Pavel
 *
 */
public interface ActionProvider extends org.nasdanika.common.Function<BiConsumer<EObject,Action>,Action> {

}
