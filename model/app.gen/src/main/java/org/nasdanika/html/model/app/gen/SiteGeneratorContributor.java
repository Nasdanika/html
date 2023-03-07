package org.nasdanika.html.model.app.gen;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;

/**
 * Contributor to generation which can be used by {@link SiteGenerator} subclasses. 
 * @author Pavel
 *
 */
public interface SiteGeneratorContributor {
	
	/**
	 * Configures semantic model resource set.
	 * Can be used to register {@link EPackage}s and adapter factories.
	 * @param resourceSet
	 */
	default void configureSemanticModelResourceSet(ResourceSet resourceSet, Context context, ProgressMonitor progressMonitor) {
		
	}
	
	/**
	 * Configures action model resource set.
	 * Can be used to register {@link EPackage}s and adapter factories.
	 * @param resourceSet
	 */
	default void configureActionModelResourceSet(ResourceSet resourceSet, Context context, ProgressMonitor progressMonitor) {
		
	}
	
	/**
	 * Configures resource model resource set
	 * Can be used to register {@link EPackage}s and adapter factories
	 * @param resourceSet
	 */
	default void configureResourceModelResourceSet(ResourceSet resourceSet, Context context, ProgressMonitor progressMonitor) {
		
	}
	
	/**
	 * Configures resource set, invoked for all types of resource sets. 
	 * Can be used to register {@link EPackage}s and adapter factories
	 * @param resourceSet
	 */
	default void configureResourceSet(ResourceSet resourceSet, Context context, ProgressMonitor progressMonitor) {
		
	}
	
	/**
	 * Processes the semantic model. For example, inlines (embeds) resources, images, representations, diagrams, etc.
	 * @param semanticResource
	 * @param context
	 * @param progressMonitor
	 */
	default void processSemanticModel(Resource semanticResource, Context context, ProgressMonitor progressMonitor) {
		
	}
	
	/**
	 * Processes the action model. For example, inlines (embeds) resources, images, representations, diagrams, etc.
	 * This method is called by {@link SemanticSiteGenerator}.
	 * @param semanticResource
	 * @param context
	 * @param progressMonitor
	 */
	default void processActionModel(Resource semanticResource, Context context, ProgressMonitor progressMonitor) {
		
	}
	
	/**
	 * Processes the root action. This method is called by {@link ActionSiteGenerator} and {@link SemanticSiteGenerator}.
	 * @param semanticResource
	 * @param context
	 * @param progressMonitor
	 */
	default void processRootAction(Action rootAction, Context context, ProgressMonitor progressMonitor) {
		
	}
	
	/**
	 * Processes the resource model. For example, inlines (embeds) resources, images, representations, diagrams, etc.
	 * @param semanticResource
	 * @param context
	 * @param progressMonitor
	 */
	default void processResourcecModel(Resource semanticResource, Context context, ProgressMonitor progressMonitor) {
		
	}
	
}