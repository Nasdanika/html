package org.nasdanika.html.flow;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.Package;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Resource;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.ncore.util.NamedElementComparator;

public class PackageActionProvider extends PackageElementActionProvider<org.nasdanika.flow.Package> {
	
	public PackageActionProvider(org.nasdanika.flow.Package value, Context context) {
		super(value, context);
	}
	
	@Override
	protected Action createAction(
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) throws Exception {
		Action action = super.createAction(registry, resolveConsumer, progressMonitor);
		
		EList<EObject> children = action.getChildren();
		children.addAll(createSubPackageActions(registry, resolveConsumer, progressMonitor));
		children.addAll(createActivityActions(registry, resolveConsumer, progressMonitor));
		children.addAll(createParticipantActions(registry, resolveConsumer, progressMonitor));
		children.addAll(createResourceActions(registry, resolveConsumer, progressMonitor));
		children.addAll(createArtifactActions(registry, resolveConsumer, progressMonitor));
		
		return action;
	}
	
	/**
	 * Creates a list of actions for sub-packages. 
	 * @param progressMonitor
	 * @return An empty list if there are no sub-packages. A singleton list containing a grouping action containing sub-package actions otherwise.
	 * @throws Exception 
	 */
	protected List<Action> createSubPackageActions(
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) throws Exception {
		List<Package> subPackages = getTarget().getSubPackages().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList());
		if (subPackages.isEmpty()) {
			return Collections.emptyList();
		}
		Action group = AppFactory.eINSTANCE.createAction();
		group.setText("Sub-packages");
		// TODO - icon, ...
		EList<EObject> children = group.getChildren();
		for (Package sp: subPackages) {
			children.add(createChildAction(sp, registry, resolveConsumer, progressMonitor));
		}
		
		return Collections.singletonList(group);
	}
	
	/**
	 * Creates a list of actions for activities. 
	 * @param progressMonitor
	 * @return An empty list if there are no activities. A singleton list containing a grouping action containing activity actions otherwise.
	 * @throws Exception 
	 */
	protected List<Action> createActivityActions(
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) throws Exception {
		Collection<Activity<?>> activities = getTarget().getActivities().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList());
		if (activities.isEmpty()) {
			return Collections.emptyList();
		}
		Action group = AppFactory.eINSTANCE.createAction();
		group.setText("Activities");
		// TODO - icon, ...
		EList<EObject> children = group.getChildren();
		for (Activity<?> activity: activities) {
			children.add(createChildAction(activity, registry, resolveConsumer, progressMonitor));
		}
		
		return Collections.singletonList(group);
	}
	
	/**
	 * Creates a list of actions for participants. 
	 * @param progressMonitor
	 * @return An empty list if there are no participants. A singleton list containing a grouping action containing participant actions otherwise.
	 * @throws Exception 
	 */
	protected List<Action> createParticipantActions(
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) throws Exception {
		Collection<Participant> participants = getTarget().getParticipants().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList());
		if (participants.isEmpty()) {
			return Collections.emptyList();
		}
		Action group = AppFactory.eINSTANCE.createAction();
		group.setText("Participants");
		// TODO - icon, ...
		EList<EObject> children = group.getChildren();
		for (Participant participant: participants) {
			children.add(createChildAction(participant, registry, resolveConsumer, progressMonitor));
		}
		
		return Collections.singletonList(group);
	}
	
	/**
	 * Creates a list of actions for resources. 
	 * @param progressMonitor
	 * @return An empty list if there are no resources. A singleton list containing a grouping action containing resource actions otherwise.
	 * @throws Exception 
	 */
	protected List<Action> createResourceActions(
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) throws Exception {
		Collection<Resource> resources = getTarget().getResources().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList());
		if (resources.isEmpty()) {
			return Collections.emptyList();
		}
		Action group = AppFactory.eINSTANCE.createAction();
		group.setText("Resources");
		// TODO - icon, ...
		EList<EObject> children = group.getChildren();
		for (Resource resource: resources) {
			children.add(createChildAction(resource, registry, resolveConsumer, progressMonitor));
		}
		
		return Collections.singletonList(group);
	}
	
	/**
	 * Creates a list of actions for activities. 
	 * @param progressMonitor
	 * @return An empty list if there are no activities. A singleton list containing a grouping action containing actvity actions otherwise.
	 * @throws Exception 
	 */
	protected List<Action> createArtifactActions(
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) throws Exception {
		Collection<Artifact> artifacts = getTarget().getArtifacts().values().stream().sorted(NamedElementComparator.INSTANCE).collect(Collectors.toList());
		if (artifacts.isEmpty()) {
			return Collections.emptyList();
		}
		Action group = AppFactory.eINSTANCE.createAction();
		group.setText("Artifacts");
		// TODO - icon, ...
		EList<EObject> children = group.getChildren();
		for (Artifact artifact: artifacts) {
			children.add(createChildAction(artifact, registry, resolveConsumer, progressMonitor));
		}
		
		return Collections.singletonList(group);
	}
	
}
