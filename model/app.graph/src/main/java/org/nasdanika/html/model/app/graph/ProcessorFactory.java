package org.nasdanika.html.model.app.graph;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Label;

/**
 * Base class for {@link Label} {@link Supplier} processors. Connections are pass-through - node processors are responsible for creating handler {@link WidgetFactory}ies.
 * @author Pavel
 *
 * @param <I>
 */
public abstract class ProcessorFactory<I> implements NopEndpointProcessorConfigFactory<Object, WidgetFactory, Registry<I>> {
	
	@Override
	public boolean isPassThrough(Connection connection) {
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object createProcessor(ProcessorConfig<Object, Registry<I>> config, boolean parallel, Consumer<CompletionStage<?>> stageCollector, ProgressMonitor progressMonitor) {
		if (config instanceof NodeProcessorConfig) {
			NodeProcessor<I> nodeProcessor = createNodeProcessor((NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<I>>) config, progressMonitor);
			return nodeProcessor; 
		} 
		
		if (config instanceof ConnectionProcessorConfig) {
			Object connectionProcessor = createConnectionProcessor((ConnectionProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<I>>) config, progressMonitor);
			return connectionProcessor; 			
		}
		
		return NopEndpointProcessorConfigFactory.super.createProcessor(config, parallel, stageCollector, progressMonitor);
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
						.map(e -> ProcessorFactory.this.resolve((NodeProcessor<I>) e.getValue().getProcessor(), base))
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
						.map(e -> ProcessorFactory.this.resolve((NodeProcessor<I>) e.getValue().getProcessor(), base))
						.findFirst()
						.orElse(null);
			}
			
		};
	}
	
	protected abstract WidgetFactory resolve(NodeProcessor<I> p, NodeProcessor<I> base);
	
	protected abstract Collection<I> getIdentifiers(Element element); 
	
	protected abstract NodeProcessor<I> createNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<I>> config, 
			ProgressMonitor progressMonitor);

	protected abstract Object createConnectionProcessor(
			ConnectionProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<I>> config,
			ProgressMonitor progressMonitor);
		
}
