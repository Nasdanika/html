package org.nasdanika.html.flow;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.DiagnosticProvider;
import org.nasdanika.emf.DiagnosticProviderAdapter;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.Resource;
import org.nasdanika.flow.Service;
import org.nasdanika.html.model.app.util.ActionProvider;

/**
 * Provides adapters for the Ecore types - {@link EPackage}, {@link EClass}, {@link EStructuralFeature}, {@link EOperation}, ...
 * @author Pavel
 *
 */
public class FlowActionProviderAdapterFactory extends ComposedAdapterFactory {
	
	public FlowActionProviderAdapterFactory(Context context) {
		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<ActionProvider, org.nasdanika.flow.Package>(
				FlowPackage.Literals.PACKAGE, 
				ActionProvider.class, 
				this.getClass().getClassLoader(), 
				e -> new PackageActionProvider(e, context)));

		// Participant
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Participant>(
					FlowPackage.Literals.PARTICIPANT, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new ParticipantActionProvider(e, context)));
		
		// Resource
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Resource>(
					FlowPackage.Literals.RESOURCE, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new ResourceActionProvider(e, context)));
		
		// Artifact
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Artifact>(
					FlowPackage.Literals.ARTIFACT, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new ArtifactActionProvider(e, context)));
		
		// Pseudo-state
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, PseudoState>(
					FlowPackage.Literals.PSEUDO_STATE, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new PseudoStateActionProvider<PseudoState>(e, context)));
		
		// Activity
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Activity<?>>(
					FlowPackage.Literals.ACTIVITY, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new ActivityActionProvider<Activity<?>>(e, context)));
		
		// Flow
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Flow>(
					FlowPackage.Literals.FLOW, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new FlowActionProvider(e, context)));
		
		// Service
		registerAdapterFactory(
				new FunctionAdapterFactory<ActionProvider, Service>(
					FlowPackage.Literals.SERVICE, 
					ActionProvider.class, 
					this.getClass().getClassLoader(), 
					e -> new ServiceActionProvider(e, context)));
		
	}
	
	@Override
	public Adapter adaptNew(Notifier target, Object type) {
		if (type == DiagnosticProvider.class) {
			return new DiagnosticProviderAdapter(target, this::getDiagnostic, this::getFeatureDiagnostic);
		}
		return super.adaptNew(target, type);
	}
	
	@Override
	public boolean isFactoryForType(Object type) {
		return super.isFactoryForType(type) || type == DiagnosticProvider.class;
	}
	
	protected Collection<Diagnostic> getDiagnostic(Notifier target) {
		return Collections.emptyList();
	}

	protected Collection<Diagnostic> getFeatureDiagnostic(Notifier target, EStructuralFeature feature) {
		return Collections.emptyList();
	}
	
}
