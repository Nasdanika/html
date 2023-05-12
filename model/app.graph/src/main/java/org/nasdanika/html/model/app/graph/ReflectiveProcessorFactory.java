package org.nasdanika.html.model.app.graph;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.IntrospectionLevel;
import org.nasdanika.graph.processor.ProcessorInfo;

public abstract class ReflectiveProcessorFactory<I> extends org.nasdanika.graph.processor.NopEndpointReflectiveProcessorFactory<Object, WidgetFactory, Registry<I>> {

	protected ReflectiveProcessorFactory(IntrospectionLevel introspectionLevel, Object[] targets) {
		super(introspectionLevel, targets);
	}

	@Override
	public Registry<I> createRegistry(Map<Element, ProcessorInfo<Object, Registry<I>>> registry) {
		return new Registry<I>() {

			@Override
			public Map<Element, ProcessorInfo<Object, Registry<I>>> getProcessorInfoMap() {
				return registry;
			}

			@SuppressWarnings("unchecked")
			@Override
			public Collection<WidgetFactory> select(Predicate<Element> predicate, NodeProcessor<I> base) {
				return registry
						.entrySet()
						.stream()
						.filter(e -> predicate.test(e.getKey()))
						.filter(e -> e.getValue().getProcessor() instanceof NodeProcessor)
						.map(e -> ReflectiveProcessorFactory.this.resolve((NodeProcessor<I>) e.getValue().getProcessor(), base))
						.collect(Collectors.toList());
			}

			@SuppressWarnings("unchecked")
			@Override
			public WidgetFactory find(I identifier, NodeProcessor<I> base) {
				return registry
						.entrySet()
						.stream()
						.filter(e -> getIdentifiers(e.getKey()).contains(identifier))
						.filter(e -> e.getValue().getProcessor() instanceof NodeProcessor)
						.map(e -> ReflectiveProcessorFactory.this.resolve((NodeProcessor<I>) e.getValue().getProcessor(), base))
						.findFirst()
						.orElse(null);
			}
			
		};
	}
	
	protected abstract WidgetFactory resolve(NodeProcessor<I> p, NodeProcessor<I> base);
	
	protected abstract Collection<I> getIdentifiers(Element element); 

	@Override
	protected Iterable<Entry<Element, ProcessorInfo<Object, Registry<I>>>> registryEntries(Registry<I> registry) {
		return registry.getProcessorInfoMap().entrySet();
	}

}
