package org.nasdanika.html.model.app.gen;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.Diagnostic;
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
	
	/**
	 * Creates action provider adapter factories which take instance diagnostic. 
	 * Instance diagnostic may be used to display diagnostic results in the generated Web UI.
	 * @param context
	 * @param instanceDiagnostic
	 * @return
	 */
	default List<AdapterFactory> createActionProviderAdapterFactories(Context context, Diagnostic instanceDiagnostic, ProgressMonitor progressMonitor) {
		return Collections.emptyList();
	}		
	
	
}