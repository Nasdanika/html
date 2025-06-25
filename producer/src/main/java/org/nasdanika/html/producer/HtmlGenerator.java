package org.nasdanika.html.producer;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Predicate;

import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.html.Producer;

/**
 * HTML generator which uses reflective node processor factories obtained from {@link CapabilityLoader}
 */
public class HtmlGenerator {
	
	private Transformer<Object,Producer<Object>> transformer;

	public static record ProducerFactoryRequirement(Predicate<Object> factoryPredicate, Context context) {
		
	}
	
	public interface Factory<T extends HtmlGenerator> {
		
		T create(Collection<Object> producerFactories);		
		
	}
	
	public HtmlGenerator(Collection<Object> producerFactories) {		
		transformer = new Transformer<>(producerFactories.toArray());
	}
	
	public static HtmlGenerator load(
			Context context, 
			Predicate<Object> factoryPredicate,
			ProgressMonitor progressMonitor) {

		return load(
				context, 
				factoryPredicate,
				new CapabilityLoader(),
				progressMonitor);
	}	
	
	public static HtmlGenerator load(
			Context context, 
			Predicate<Object> factoryPredicate,
			CapabilityLoader capabilityLoader, 
			ProgressMonitor progressMonitor) {

		return load(
				context, 
				factoryPredicate,
				capabilityLoader, 
				HtmlGenerator::new,
				progressMonitor);					
	}
	
	public static <T extends HtmlGenerator> T load(
			Context context, 
			Predicate<Object> factoryPredicate,
			CapabilityLoader capabilityLoader, 
			Factory<T> factory,
			ProgressMonitor progressMonitor) {
		
		ProducerFactoryRequirement requirement = new ProducerFactoryRequirement(factoryPredicate, context); 
		return factory.create(capabilityLoader.loadAll(requirement, progressMonitor));
	}
		
	public static CompletionStage<HtmlGenerator> load(
			Context context, 
			Predicate<Object> factoryPredicate,
			CapabilityFactory.Loader capabilityLoader, 
			ProgressMonitor progressMonitor) {

		return load(
				context, 
				factoryPredicate,
				capabilityLoader, 
				HtmlGenerator::new,
				progressMonitor);					
	}
	
	public static <T extends HtmlGenerator> CompletionStage<T> load(
			Context context, 
			Predicate<Object> factoryPredicate,
			CapabilityFactory.Loader capabilityLoader, 
			Factory<T> factory,
			ProgressMonitor progressMonitor) {
		
		ProducerFactoryRequirement requirement = new ProducerFactoryRequirement(factoryPredicate, context); 
		return capabilityLoader.loadAll(requirement, progressMonitor).thenApply(factory::create);
	}
	
	public Producer<Object> createProducer(Object source, ProgressMonitor progressMonitor) {
		return transformer.transform(Collections.singleton(source), false, progressMonitor).get(source);		
	}
	
	public Map<Object,Producer<Object>> createProducers(Collection<?> sources, ProgressMonitor progressMonitor) {
		return transformer.transform(sources, false, progressMonitor);		
	}

	public boolean canHandle(Object source) {
		return transformer.canHandle(source);
	}
		
}
