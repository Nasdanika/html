package org.nasdanika.html.model.app.gen;

import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.emf.ResourceSetContributor;
import org.nasdanika.capability.emf.ResourceSetContributorCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.persistence.EObjectLoader;

/**
 * Loads objects using {@link EObjectLoader}
 */
public class AppAdapterCapabilityFactory extends ResourceSetContributorCapabilityFactory {

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<ResourceSetContributor>>> createService(
			Class<ResourceSetContributor> serviceType, 
			Predicate<ResourceSetContributor> serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		return wrap(new ResourceSetContributor() {
			
			@Override
			public void contribute(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
				resourceSet.getAdapterFactories().add(new AppAdapterFactory());
			}
			
		});
	}

}
