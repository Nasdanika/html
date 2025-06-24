package org.nasdanika.html.producer.factories.core;

import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.producer.HtmlGenerator.ProducerFactoryRequirement;

import reactor.core.publisher.Flux;

public class CoreProducerFactoryCapabilityFactory implements CapabilityFactory<ProducerFactoryRequirement, Object> {

	@Override
	public boolean canHandle(Object requirement) {
		return requirement instanceof ProducerFactoryRequirement;
	}

	@Override
	public CompletionStage<Iterable<CapabilityProvider<Object>>> create(
			ProducerFactoryRequirement requirement,
			Loader loader,
			ProgressMonitor progressMonitor) {

		CoreProducerFactory factory = new CoreProducerFactory(requirement.context());
		if (requirement.factoryPredicate() == null || requirement.factoryPredicate().test(factory)) {
			CapabilityProvider<Object> capabilityProvider = new CapabilityProvider<Object>() {
				
				@Override
				public Flux<Object> getPublisher() {
					return Flux.just(factory);
				}
				
			};			
			
			return CompletableFuture.completedStage(Collections.singleton(capabilityProvider));
		}
		return CompletableFuture.completedStage(Collections.emptyList());
	}

}