package org.nasdanika.html.flow;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.html.model.app.Action;

public class FlowActionProvider extends ActivityActionProvider<Flow> {
	
	public FlowActionProvider(Flow value, Context context) {
		super(value, context);
	}
	
	@Override
	protected Action createAction(
			BiConsumer<EObject, Action> registry,
			Consumer<org.nasdanika.common.Consumer<Function<EObject, Action>>> resolveConsumer,
			ProgressMonitor progressMonitor) throws Exception {
		Action action = super.createAction(registry, resolveConsumer, progressMonitor);
		EList<EObject> children = action.getChildren(); // TODO - sort by dependency then by name - comparator.
		for (FlowElement<?> element: getTarget().getElements().values()) {
			if (!(element instanceof PseudoState)) {
				children.add(adaptChild(element).execute(registry, progressMonitor));
			}
		}
		
		return action;
	}

}
