package org.nasdanika.html.producer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.html.Producer;

/**
 * HTML generator which uses reflective node processor factories obtained from {@link CapabilityLoader}
 */
public class HtmlGenerator {
	
	public static record ProducerFactoryRequirement(Predicate<Object> factoryPredicate, Context context) {
		
	}
	
	public interface Factory<T extends HtmlGenerator> {
		
		T create(Collection<? extends EObject> sources,	Collection<Object> nodeProcessorFactories);		
		
	}
	
	protected Collection<? extends EObject> sources;
	protected Collection<Object> producerFactories;
	
	public HtmlGenerator(Collection<? extends EObject> sources,	Collection<Object> nodeProcessorFactories) {
		this.sources = sources;
		this.producerFactories = nodeProcessorFactories;
	}
	
	/**
	 * Loads mapping of {@link EPackage}s to doc URI's and node processor factories from {@link CapabilityLoader}. 
	 * @param sources
	 * @param references
	 * @param capabilityLoader
	 * @param progressMonitor
	 * @return
	 */
	public static HtmlGenerator load(
			Collection<EObject> sources,
			Context context, 
			Predicate<Object> factoryPredicate,
			ProgressMonitor progressMonitor) {

		return load(
				sources,
				context, 
				factoryPredicate,
				new CapabilityLoader(),
				progressMonitor);
	}	
	
	/**
	 * Loads mapping of {@link EPackage}s to doc URI's and node processor factories from {@link CapabilityLoader}. 
	 * @param sources
	 * @param references
	 * @param capabilityLoader
	 * @param progressMonitor
	 * @return
	 */
	public static HtmlGenerator load(
			Collection<EObject> sources,
			Context context, 
			Predicate<Object> factoryPredicate,
			CapabilityLoader capabilityLoader, 
			ProgressMonitor progressMonitor) {

		return load(
				sources,
				context, 
				factoryPredicate,
				capabilityLoader, 
				HtmlGenerator::new,
				progressMonitor);			
		
	}
	
	/**
	 * Loads mapping of {@link EPackage}s to doc URI's and node processor factories from {@link CapabilityLoader}. 
	 * @param sources
	 * @param references
	 * @param capabilityLoader
	 * @param progressMonitor
	 * @return
	 */
	public static <T extends HtmlGenerator> T load(
			Collection<EObject> sources,
			Context context, 
			Predicate<Object> factoryPredicate,
			CapabilityLoader capabilityLoader, 
			Factory<T> factory,
			ProgressMonitor progressMonitor) {
		
		List<Object> nodeProcessorFactories = Collections.synchronizedList(new ArrayList<>());
		ProducerFactoryRequirement requirement = new ProducerFactoryRequirement(factoryPredicate, context); 
		for (CapabilityProvider<Object> nodeProcessorProvider: capabilityLoader.load(requirement, progressMonitor)) {
			nodeProcessorProvider.getPublisher().filter(Objects::nonNull).collectList().block().forEach(nodeProcessorFactories::add);
		}
		
		return factory.create(sources, nodeProcessorFactories);
	}
	
	public Map<EObject,Producer<Object>> createProcessors(ProgressMonitor progressMonitor) {
		Transformer<EObject,Producer<Object>> transformer = new Transformer<>(producerFactories.toArray());
		return transformer.transform(sources, false, progressMonitor);
	}
		
}
