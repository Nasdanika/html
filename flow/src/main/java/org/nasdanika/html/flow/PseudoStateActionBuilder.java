package org.nasdanika.html.flow;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.html.model.app.Action;

public class PseudoStateActionBuilder<T extends PseudoState> extends FlowElementActionBuilder<T> {
	
	public PseudoStateActionBuilder(T value, Context context) {
		super(value, context);
	}
	
	@Override
	protected Action buildAction(
			Action action,
			BiConsumer<EObject, Action> registry,
			Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer,
			ProgressMonitor progressMonitor) throws Exception {		
		action = super.buildAction(action, registry, resolveConsumer, progressMonitor);
		if (Util.isBlank(action.getText())) {
			action.setText(getTarget().eClass().getName());			
		}
		return action;
	}

}
