package org.nasdanika.html.flow;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Resource;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.ncore.util.NamedElementComparator;

public class ResourceActionProvider extends ServiceProviderActionProvider<Resource> {
	
	public ResourceActionProvider(Resource value, Context context) {
		super(value, context);
	}
	
	@Override
	protected Action createAction(
			BiConsumer<EObject, Action> registry,
			Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer,
			ProgressMonitor progressMonitor) throws Exception {
		
		Action action = super.createAction(registry, resolveConsumer, progressMonitor);
		EList<EObject> children = action.getChildren(); 
		for (Resource element: getTarget().getChildren().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList())) {
			children.add(createChildAction(element, registry, resolveConsumer, progressMonitor));
		}
		
		return action;
	}	
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.RESOURCE__ARTIFACTS);
		properties.add(FlowPackage.Literals.RESOURCE__USED_BY);
		properties.add(FlowPackage.Literals.RESOURCE__USED_IN);
		return properties;
	}

}
