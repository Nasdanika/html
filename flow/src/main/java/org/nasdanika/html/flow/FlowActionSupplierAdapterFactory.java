package org.nasdanika.html.flow;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Service;
import org.nasdanika.html.model.app.util.ActionSupplier;

/**
 * Provides adapters for the Ecore types - {@link EPackage}, {@link EClass}, {@link EStructuralFeature}, {@link EOperation}, ...
 * @author Pavel
 *
 */
public class FlowActionSupplierAdapterFactory extends ComposedAdapterFactory {
	
	public FlowActionSupplierAdapterFactory(Context context) {
		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<ActionSupplier, org.nasdanika.flow.Package>(
				FlowPackage.Literals.PACKAGE, 
				ActionSupplier.class, 
				this.getClass().getClassLoader(), 
				e -> new PackageActionSupplier(e, context)));

		// Participant
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionSupplier, Participant>(
					FlowPackage.Literals.PARTICIPANT, 
					ActionSupplier.class, 
					this.getClass().getClassLoader(), 
					e -> new ParticipantActionSupplier(e, context)));
		
		// Resource
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionSupplier, Resource>(
					FlowPackage.Literals.RESOURCE, 
					ActionSupplier.class, 
					this.getClass().getClassLoader(), 
					e -> new ResourceActionSupplier(e, context)));
		
		// Artifact
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionSupplier, Artifact>(
					FlowPackage.Literals.ARTIFACT, 
					ActionSupplier.class, 
					this.getClass().getClassLoader(), 
					e -> new ArtifactActionSupplier(e, context)));
		
		// Activity
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionSupplier, Activity<?>>(
					FlowPackage.Literals.ACTIVITY, 
					ActionSupplier.class, 
					this.getClass().getClassLoader(), 
					e -> new ActivityActionSupplier<Activity<?>>(e, context)));
		
		// Flow
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionSupplier, Flow>(
					FlowPackage.Literals.FLOW, 
					ActionSupplier.class, 
					this.getClass().getClassLoader(), 
					e -> new FlowActionSupplier(e, context)));
		
		// Service
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionSupplier, Service>(
					FlowPackage.Literals.SERVICE, 
					ActionSupplier.class, 
					this.getClass().getClassLoader(), 
					e -> new ServiceActionSupplier(e, context)));
		
	}

}
