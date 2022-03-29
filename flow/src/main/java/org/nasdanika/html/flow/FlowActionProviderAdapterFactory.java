package org.nasdanika.html.flow;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Service;
import org.nasdanika.html.emf.ActionProviderAdapterFactory;
import org.nasdanika.html.model.app.util.ActionProvider;

/**
 * Provides adapters for the Ecore types - {@link EPackage}, {@link EClass}, {@link EStructuralFeature}, {@link EOperation}, ...
 * @author Pavel
 *
 */
public class FlowActionProviderAdapterFactory extends ActionProviderAdapterFactory {
	
	public FlowActionProviderAdapterFactory(Context context) {
		super(context);
		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<ActionProvider, org.nasdanika.flow.Package>(
				FlowPackage.Literals.PACKAGE, 
				ActionProvider.class, 
				this.getClass().getClassLoader(), 
				e -> new PackageActionBuilder(e, context).asActionProvider()));

		// Participant
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Participant>(
					FlowPackage.Literals.PARTICIPANT, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new ParticipantActionBuilder(e, context).asActionProvider()));
		
		// Resource
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Resource>(
					FlowPackage.Literals.RESOURCE, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new ResourceActionBuilder(e, context).asActionProvider()));
		
		// Artifact
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Artifact>(
					FlowPackage.Literals.ARTIFACT, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new ArtifactActionBuilder(e, context).asActionProvider()));
		
		// Pseudo-state
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, PseudoState>(
					FlowPackage.Literals.PSEUDO_STATE, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new PseudoStateActionBuilder<PseudoState>(e, context).asActionProvider()));
		
		// Activity
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Activity<?>>(
					FlowPackage.Literals.ACTIVITY, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new ActivityActionBuilder<Activity<?>>(e, context).asActionProvider()));
		
		// Flow
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Flow>(
					FlowPackage.Literals.FLOW, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new FlowActionBuilder(e, context).asActionProvider()));
		
		// Service
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Service>(
					FlowPackage.Literals.SERVICE, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new ServiceActionBuilder(e, context).asActionProvider()));
		
	}
		
}
