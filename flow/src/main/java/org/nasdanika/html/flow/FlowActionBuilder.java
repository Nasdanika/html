package org.nasdanika.html.flow;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.flow.Call;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.Transition;
import org.nasdanika.flow.util.FlowStateDiagramGenerator;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.NavigationPanelStyle;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.util.NamedElementComparator;

public class FlowActionBuilder extends ActivityActionBuilder<Flow> {
	
	public FlowActionBuilder(Flow value, Context context) {
		super(value, context);
	}
	
	@Override
	protected Action buildAction(
			Action action,
			BiConsumer<EObject, Action> registry,
			Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer,
			ProgressMonitor progressMonitor) {
		
		action = super.buildAction(action, registry, resolveConsumer, progressMonitor);
		EList<EObject> children = action.getChildren(); 
		Predicate<FlowElement<?>> isPseudoState= PseudoState.class::isInstance;
		for (FlowElement<?> element: getTarget().getElements().values().stream().filter(isPseudoState.negate()).sorted(FlowActionBuilder::compareFlowElements).collect(Collectors.toList())) {
			children.add(createChildAction(element, registry, resolveConsumer, progressMonitor));
		}
		EList<Action> anonymous = action.getAnonymous(); 
		for (FlowElement<?> element: getTarget().getElements().values().stream().filter(isPseudoState).collect(Collectors.toList())) {
			anonymous.add(createChildAction(element, registry, resolveConsumer, progressMonitor));
		}
		
		return action;
	}
		
	public static int compareFlowElements(FlowElement<?> a, FlowElement<?> b) {
		if (a == b) {
			return 0;
		}

		String asg = a.getSortGroup();
		String bsg = b.getSortGroup();
		if (Util.isBlank(asg)) {
			if (!Util.isBlank(bsg)) {
				return -1;
			}
		} else {
			if (Util.isBlank(bsg)) {
				return 1;
			}
			int cmp = asg.compareTo(bsg);
			if (cmp != 0) {
				return cmp;
			}
		}
		
		int abDistance = distance(a, b, new HashSet<>());
		int baDistance = distance(b, a, new HashSet<>());
		
		if (abDistance != -1 && baDistance == -1) {
			return -1;
		}
		
		if (baDistance != -1 && abDistance == -1) {
			return 1;
		}
		
		// Cycle
		if (abDistance != -1 && baDistance != -1) {
			return abDistance - baDistance;
		}
		
		// Common source - shortest sum.
		Map<FlowElement<?>, Integer> aSources = sources(a, new HashSet<>());
		int da = 0;
		int db = 0;
		FlowElement<?> cs = null;
		for (Entry<FlowElement<?>, Integer> bse: sources(b, new HashSet<>()).entrySet()) {
			for (Entry<FlowElement<?>, Integer> ase: aSources.entrySet()) {
				if (ase.getKey() == bse.getKey()) {
					int sd = ase.getValue() + bse.getValue();
					if (cs == null || sd < da + db) {
						da = ase.getValue();
						db = bse.getValue();
						cs = ase.getKey();
					}
				}
			}					
		}				
		if (cs != null && da != db) {
			return da - db;
		}
		
//		for (Marker am: a.getMarkers()) {
//			for (Marker bm: b.getMarkers()) {
//				if (am != null 
//						&& bm != null 
//						&& !Util.isBlank(am.getLocation())
//						&& am.getLocation().equals(bm.getLocation())) {
//					
//					int lineDiff = am.getLine() - bm.getLine();
//					if (lineDiff != 0) {
//						return lineDiff;
//					}
//					int colDiff = am.getColumn() - bm.getColumn();
//					if (colDiff != 0) {
//						return colDiff;
//					}			
//				}
//			}
//		}
		
		return NamedElementComparator.INSTANCE.compare(a, b);
	}
	
	/**
	 * All sources for this target with distances.
	 * @param source
	 * @param target
	 * @param traversed
	 * @return
	 */
	private static Map<FlowElement<?>, Integer> sources(FlowElement<?> target, Set<FlowElement<?>> traversed) {
		Map<FlowElement<?>, Integer> ret = new HashMap<>();
		if (traversed.add(target)) {
			for (Transition input: target.getInputs()) {
				FlowElement<?> source = (FlowElement<?>) input.eContainer().eContainer();
				ret.put(source, 1);
				for (Entry<FlowElement<?>, Integer> se: sources(source, traversed).entrySet()) {
					Integer existingDistance = ret.get(se.getKey());
					if (existingDistance == null) {
						ret.put(se.getKey(), se.getValue() + 1);						
					} else if (se.getValue() < existingDistance) {
						ret.put(se.getKey(), se.getValue());
					}
				}				
			}
			
			for (Call invocation: target.getInvocations()) {
				FlowElement<?> source = (FlowElement<?>) invocation.eContainer().eContainer();
				ret.put(source, 1);
				for (Entry<FlowElement<?>, Integer> se: sources(source, traversed).entrySet()) {
					Integer existingDistance = ret.get(se.getKey());
					if (existingDistance == null) {
						ret.put(se.getKey(), se.getValue() + 1);						
					} else if (se.getValue() < existingDistance) {
						ret.put(se.getKey(), se.getValue());
					}
				}								
			}
		}
		return ret;
	}
	
	/**
	 * 
	 * @param source
	 * @param target
	 * @param traversed
	 * @return Number of transitions/calls between the source and the target. -1 if target is not reacheable from the source. 
	 */
	private static int distance(FlowElement<?> source, FlowElement<?> target, Set<FlowElement<?>> traversed) {
		if (source == target) {
			return 0;
		}
		int distance = -1;
		if (traversed.add(source)) {
			for (Transition output: source.getOutputs().values()) {
				int targetDistance = distance(output.getTarget(), target, traversed);
				if (targetDistance != -1) {
					if (distance == -1) {
						distance = targetDistance + 1;
					} else {
						distance = Math.min(distance, targetDistance + 1);
					}
				}
			}
			for (Call call: source.getCalls().values()) {
				int targetDistance = distance(call.getTarget(), target, traversed);
				if (targetDistance != -1) {
					if (distance == -1) {
						distance = targetDistance + 1;
					} else {
						distance = Math.min(distance, targetDistance + 1);
					}
				}
			}
		}
		return distance;
	}
		
	@Override
	protected void populateRepresentation(Diagram representation, FlowStateDiagramGenerator flowStateDiagramGenerator) {
		if (getTarget().isPartition()) {
			super.populateRepresentation(representation, flowStateDiagramGenerator);
		} else {
			flowStateDiagramGenerator.generateDiagram(getTarget(), representation, null);
		}
	}
	
	@Override
	protected void resolve(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) {
		super.resolve(action, context, progressMonitor);
		
		if (getTarget().eContainmentFeature() == FlowPackage.Literals.ACTIVITY_ENTRY__VALUE && getTarget().eContainer().eContainmentFeature() == FlowPackage.Literals.SERVICE_PROVIDER__SERVICES) {
			// Left content panel with children.
			NavigationPanel leftNavigation = action.getLeftNavigation();
			if (leftNavigation == null) {
				leftNavigation = AppFactory.eINSTANCE.createNavigationPanel();
				action.setLeftNavigation(leftNavigation);			
				int subChildren = getTarget().getElements().values().stream().filter(Flow.class::isInstance).map(Flow.class::cast).map(Flow::getElements).mapToInt(Collection::size).sum();				
				leftNavigation.setStyle(subChildren == 0 ? NavigationPanelStyle.AUTO : flowElements(getTarget()) > 25 ? NavigationPanelStyle.SEARCHABLE_TREE : NavigationPanelStyle.TREE);
			}
			Predicate<FlowElement<?>> isPseudoState= PseudoState.class::isInstance;
			for (FlowElement<?> element: getTarget().getElements().values().stream().filter(isPseudoState.negate()).sorted(FlowActionBuilder::compareFlowElements).collect(Collectors.toList())) {
				leftNavigation.getItems().add(createItem(action, context, progressMonitor, element));
			}
		}
	}
	
	/**
	 * Recursively calculates number of flow elements excluding the flow itself
	 * @param flow
	 * @return
	 */
	private static int flowElements(Flow flow) {
		return flow.getElements().stream().mapToInt(e -> e instanceof Flow ? flowElements((Flow) e) + 1 : 1).sum();
	}

	private EObject createItem(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor, 
			FlowElement<?> element) {
		EObject item = renderValue(action, FlowPackage.Literals.FLOW__ELEMENTS, element, context, progressMonitor);
		if (element instanceof Flow && item instanceof Label) {
			Predicate<FlowElement<?>> isPseudoState= PseudoState.class::isInstance;
			for (FlowElement<?> child: ((Flow) element).getElements().values().stream().filter(isPseudoState.negate()).sorted(FlowActionBuilder::compareFlowElements).collect(Collectors.toList())) {
				((Label) item).getChildren().add(createItem(action, context, progressMonitor, child));
			}
		}
		return item;
	}

}
