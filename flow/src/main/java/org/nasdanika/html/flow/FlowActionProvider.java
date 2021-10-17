package org.nasdanika.html.flow;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.gen.plantuml.Generator;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.util.FlowStateDiagramGenerator;
import org.nasdanika.html.model.app.Action;

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
		EList<EObject> children = action.getChildren(); // TODO - sort by dependency then by name - comparator.
		for (FlowElement<?> element: getTarget().getElements().values()) {
			if (!(element instanceof PseudoState)) {
				children.add(createChildAction(element, registry, resolveConsumer, progressMonitor));
			}
		}
		
		return action;
	}
	
	@Override
	protected void resolve(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context, 
			ProgressMonitor progressMonitor)
			throws Exception {
		super.resolve(action, context, progressMonitor);
		
		FlowStateDiagramGenerator flowStateDiagramGenerator = new FlowStateDiagramGenerator() {
		
			@Override
			protected String getFlowElementLocation(String key, FlowElement<?> flowElement) {
				Action elementAction = context.getAction(flowElement);
				if (elementAction == null) {
					return null;
				}
				
				URI uri = context.resolve(elementAction, action);
				return uri == null ? null : uri.toString();
			}
			
		};
		
		Diagram diagram = flowStateDiagramGenerator.generateFlowDiagram(getTarget());
		Generator generator = new Generator();
		String diagramHTML = generator.generateUmlDiagram(diagram);
		addContent(action, diagramHTML);
	}

}
