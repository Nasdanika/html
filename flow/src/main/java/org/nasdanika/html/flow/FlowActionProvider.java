package org.nasdanika.html.flow;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.flow.Call;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.Transition;
import org.nasdanika.flow.util.FlowStateDiagramGenerator;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.ncore.util.NamedElementComparator;

public class FlowActionProvider extends ActivityActionProvider<Flow> {
	
	public FlowActionProvider(Flow value, Context context) {
		super(value, context);
	}
	
	@Override
	protected Action createAction(
			BiConsumer<EObject, Action> registry,
			Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer,
			ProgressMonitor progressMonitor) throws Exception {
		
		Action action = super.createAction(registry, resolveConsumer, progressMonitor);
		EList<EObject> children = action.getChildren(); 
		Predicate<FlowElement<?>> isPseudoState = PseudoState.class::isInstance;
		for (FlowElement<?> element: getTarget().getElements().values().stream().filter(isPseudoState.negate()).sorted(this::compareFlowElements).collect(Collectors.toList())) {
			children.add(createChildAction(element, registry, resolveConsumer, progressMonitor));
		}
		EList<Action> anonymous = action.getAnonymous(); 
		for (FlowElement<?> element: getTarget().getElements().values().stream().filter(isPseudoState).collect(Collectors.toList())) {
			anonymous.add(createChildAction(element, registry, resolveConsumer, progressMonitor));
		}
		
		return action;
	}
		
	private int compareFlowElements(FlowElement<?> a, FlowElement<?> b) {
		if (a == b) {
			return 0;
		}
		
		if (isReacheable(a, b, new HashSet<>())) {
			return -1;
		}
		
		if (isReacheable(b, a, new HashSet<>())) {
			return 1;
		}
		
		return NamedElementComparator.INSTANCE.compare(a, b);
	}
	
	private boolean isReacheable(FlowElement<?> source, FlowElement<?> target, Set<FlowElement<?>> traversed) {
		if (source == target) {
			return true;
		}
		if (traversed.add(source)) {
			for (Transition output: source.getOutputs().values()) {
				if (isReacheable(output.getTarget(), target, traversed)) {
					return true;
				}
			}
			for (Call call: source.getCalls().values()) {
				if (isReacheable(call.getTarget(), target, traversed)) {
					return true;
				}
			}
		}
		return false;
	}


	protected Diagram generateDiagram(FlowStateDiagramGenerator flowStateDiagramGenerator) {		
		Diagram diagram = flowStateDiagramGenerator.generateFlowDiagram(getTarget());
		diagram.setHideEmptyDescription(true); // TODO - configurable via representations		
		return diagram;
	}

}
