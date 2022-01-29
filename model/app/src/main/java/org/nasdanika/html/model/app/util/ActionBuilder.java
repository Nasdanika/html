package org.nasdanika.html.model.app.util;

import java.util.function.BiConsumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.html.model.app.Action;

/**
 * Adapter interface. Builds an action for the target.
 * Implementations shall call the argument {@link BiConsumer} passing the target and resulting action as argument to use
 * later in resolve() phase.
 * @author Pavel
 *
 */
public interface ActionBuilder extends org.nasdanika.common.Function<BiSupplier<Action,BiConsumer<EObject,Action>>,Action> {

}
