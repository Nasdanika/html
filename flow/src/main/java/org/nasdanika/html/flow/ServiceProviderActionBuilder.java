package org.nasdanika.html.flow;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.ServiceProvider;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.ncore.util.NamedElementComparator;

public class ServiceProviderActionBuilder<T extends ServiceProvider<?>> extends PackageElementActionBuilder<T> {
	
	public ServiceProviderActionBuilder(T value, Context context) {
		super(value, context);
	}
	
	@Override
	protected Action buildAction(
			Action action,
			BiConsumer<EObject, Action> registry,
			Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer,
			ProgressMonitor progressMonitor) throws Exception {
		
		action = super.buildAction(action, registry, resolveConsumer, progressMonitor);
		List<Activity<?>> services = getTarget().getServices().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList());
		if (!services.isEmpty()) {
			Action group = AppFactory.eINSTANCE.createAction();
			group.setText("Services");
//			group.setDescription("Shared activities or journeys which can be used on their own or be referenced from other journeys."); TODO - description modal.
			group.setTooltip("Shared activities or journeys which can be used on their own or be referenced from other journeys.");
			group.setUuid(action.getUuid() + "-services");
			EList<Action> anonymous = action.getAnonymous();
			for (Activity<?> service: getTarget().getServices().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList())) {
				anonymous.add(createChildAction(service, registry, resolveConsumer, progressMonitor));
			}
		
			action.getSections().add(group);
		}
		
		return action;
	}
		
	@Override
	protected void resolve(
			Action action, 
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) throws Exception {
		super.resolve(action, context, progressMonitor);
				
		List<Activity<?>> services = getTarget().getServices().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList());
		if (!services.isEmpty()) {
			String servicesGroupUUID = action.getUuid() + "-services";
			Optional<Action> servicesActionOptional = action.getSections().stream()
					.filter(a -> servicesGroupUUID.equals(a.getUuid()))
					.findFirst();
			
			Action servicesAction = servicesActionOptional.get();
			servicesAction.getContent().add(renderList(services, false, null, action, FlowPackage.Literals.SERVICE_PROVIDER__SERVICES, context, progressMonitor));
		}
	}
	
}
