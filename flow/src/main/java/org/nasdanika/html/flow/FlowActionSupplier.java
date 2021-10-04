package org.nasdanika.html.flow;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.html.model.app.Action;

public class FlowActionSupplier extends ActivityActionSupplier<Flow> {
	
	public FlowActionSupplier(Flow value, Context context) {
		super(value, context);
	}
	
	@Override
	public Action execute(ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(progressMonitor);
		
		EList<EObject> children = action.getChildren();
		for (FlowElement<?> element: eObject.getElements().values()) {
			if (!(element instanceof PseudoState)) {
				children.add(adaptChild(element).execute(progressMonitor));
			}
		}
		
		return action;
	}

}
