package org.nasdanika.html.model.app.graph;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * 
 * @author Pavel
 *
 * @param <I> Element identifier type
 */
public abstract class Registry<I> {
	
	private Map<Element, ProcessorInfo<Object>> registry;

	protected Registry(Map<Element, ProcessorInfo<Object>> registry) {
		this.registry = registry;
	}
	
	Map<Element, ProcessorInfo<Object>> getProcessorInfoMap();
	
	/**
	 * Finds {@link WidgetFactory}ies by predicate
	 * @param predicate
	 * @return 
	 */
	Collection<WidgetFactory> select(Predicate<Element> predicate, NodeProcessor base);
	
	/**
	 * Finds a {@link WidgetFactory} by identifier
	 * @param identifier
	 * @return label record or null
	 */
	WidgetFactory find(I identifier, NodeProcessor base);
	
	
	
	@Override
	public Registry<I> createRegistry() {
		return new Registry<I>() {

			@Override
			public Map<Element, ProcessorInfo<Object, Registry<I>>> getProcessorInfoMap() {
				return registry;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Collection<WidgetFactory> select(Predicate<Element> predicate, NodeProcessor base) {
				return registry
						.entrySet()
						.stream()
						.filter(e -> predicate.test(e.getKey()))
						.filter(e -> e.getValue().getProcessor() instanceof NodeProcessor)
						.map(e -> ProcessorFactory.this.resolve((NodeProcessor<I>) e.getValue().getProcessor(), base))
						.collect(Collectors.toList());
			}

			@SuppressWarnings("unchecked")
			@Override
			public WidgetFactory find(I identifier, NodeProcessor base) {
				return registry
						.entrySet()
						.stream()
						.filter(e -> getIdentifiers(e.getKey()).contains(identifier))
						.filter(e -> e.getValue().getProcessor() instanceof NodeProcessor)
						.map(e -> ProcessorFactory.this.resolve((NodeProcessor<I>) e.getValue().getProcessor(), base))
						.findFirst()
						.orElse(null);
			}
			
		};
	}
	
	protected abstract WidgetFactory resolve(NodeProcessor<I> p, NodeProcessor<I> base);
	
	protected abstract Collection<I> getIdentifiers(Element element); 
	
	
}
