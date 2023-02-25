package org.nasdanika.html.emf;

import java.util.List;
import java.util.function.BiConsumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.ncore.Composite;

public class CompositeActionBuilder<T extends Composite> extends NcoreActionBuilder<T> {
	
	public CompositeActionBuilder(T value, Context context) {
		super(value, context);		
	}
	
	@Override
	protected Action buildAction(
			Action action,
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) {
		
		Action ret = super.buildAction(action, registry, resolveConsumer, progressMonitor);
		createChildrenActions(ret, registry, resolveConsumer, progressMonitor);						
		return ret;
	}
	
	/**
	 * Creates a list of actions for sub-packages. 
	 * @param progressMonitor
	 * @return An empty list if there are no sub-packages. A singleton list containing a grouping action containing sub-package actions otherwise.
	 */
	protected void createChildrenActions(
			Action action,
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) {
		
		List<EObject> children = action.getChildren();
		for (Composite child: getTarget().getChildren()) {
			children.add(createChildAction(child, registry, resolveConsumer, progressMonitor));
		}
	}	
	
}
