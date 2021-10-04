package org.nasdanika.html.flow;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
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

public class PackageActionSupplier extends PackageElementActionSupplier<org.nasdanika.flow.Package> {
	
	public PackageActionSupplier(org.nasdanika.flow.Package value, Context context) {
		super(value, context);
	}
	
	@Override
	public Action execute(ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(progressMonitor);
		
		EList<EObject> children = action.getChildren();
		children.addAll(createSubPackageActions(progressMonitor));
		children.addAll(createActivityActions(progressMonitor));
		children.addAll(createParticipantActions(progressMonitor));
		children.addAll(createResourceActions(progressMonitor));
		children.addAll(createArtifactActions(progressMonitor));
		
		return action;
	}
	
	/**
	 * Creates a list of actions for sub-packages. 
	 * @param progressMonitor
	 * @return An empty list if there are no sub-packages. A singleton list containing a grouping action containing sub-package actions otherwise.
	 * @throws Exception 
	 */
	protected List<Action> createSubPackageActions(ProgressMonitor progressMonitor) throws Exception {
		EMap<String, Package> subPackages = eObject.getSubPackages();
		if (subPackages.isEmpty()) {
			return Collections.emptyList();
		}
		Action group = AppFactory.eINSTANCE.createAction();
		group.setText("Sub-packages");
		// TODO - icon, ...
		for (Package sp: subPackages.values()) {
			group.getChildren().add(adaptChild(sp).execute(progressMonitor));
		}
		
		return Collections.singletonList(group);
	}
	
	/**
	 * Creates a list of actions for activities. 
	 * @param progressMonitor
	 * @return An empty list if there are no activities. A singleton list containing a grouping action containing activity actions otherwise.
	 * @throws Exception 
	 */
	protected List<Action> createActivityActions(ProgressMonitor progressMonitor) throws Exception {
		Collection<Activity<?>> activities = eObject.getActivities().values();
		if (activities.isEmpty()) {
			return Collections.emptyList();
		}
		Action group = AppFactory.eINSTANCE.createAction();
		group.setText("Activities");
		// TODO - icon, ...
		for (Activity<?> activity: activities) {
			group.getChildren().add(adaptChild(activity).execute(progressMonitor));
		}
		
		return Collections.singletonList(group);
	}
	
	/**
	 * Creates a list of actions for participants. 
	 * @param progressMonitor
	 * @return An empty list if there are no participants. A singleton list containing a grouping action containing participant actions otherwise.
	 * @throws Exception 
	 */
	protected List<Action> createParticipantActions(ProgressMonitor progressMonitor) throws Exception {
		Collection<Participant> participants = eObject.getParticipants().values();
		if (participants.isEmpty()) {
			return Collections.emptyList();
		}
		Action group = AppFactory.eINSTANCE.createAction();
		group.setText("Participants");
		// TODO - icon, ...
		for (Participant participant: participants) {
			group.getChildren().add(adaptChild(participant).execute(progressMonitor));
		}
		
		return Collections.singletonList(group);
	}
	
	/**
	 * Creates a list of actions for resources. 
	 * @param progressMonitor
	 * @return An empty list if there are no resources. A singleton list containing a grouping action containing resource actions otherwise.
	 * @throws Exception 
	 */
	protected List<Action> createResourceActions(ProgressMonitor progressMonitor) throws Exception {
		Collection<Resource> resources = eObject.getResources().values();
		if (resources.isEmpty()) {
			return Collections.emptyList();
		}
		Action group = AppFactory.eINSTANCE.createAction();
		group.setText("Resources");
		// TODO - icon, ...
		for (Resource resource: resources) {
			group.getChildren().add(adaptChild(resource).execute(progressMonitor));
		}
		
		return Collections.singletonList(group);
	}
	
	/**
	 * Creates a list of actions for activities. 
	 * @param progressMonitor
	 * @return An empty list if there are no activities. A singleton list containing a grouping action containing actvity actions otherwise.
	 * @throws Exception 
	 */
	protected List<Action> createArtifactActions(ProgressMonitor progressMonitor) throws Exception {
		Collection<Artifact> artifacts = eObject.getArtifacts().values();
		if (artifacts.isEmpty()) {
			return Collections.emptyList();
		}
		Action group = AppFactory.eINSTANCE.createAction();
		group.setText("Artifacts");
		// TODO - icon, ...
		for (Artifact artifact: artifacts) {
			group.getChildren().add(adaptChild(artifact).execute(progressMonitor));
		}
		
		return Collections.singletonList(group);
	}

}
