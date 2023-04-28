package org.nasdanika.html.model.app.graph;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Label;

/**
 * Base class for {@link Label} {@link Supplier} processors. Connections are pass-through - node processors are responsible for creating handler {@link LabelFactory}ies.
 * @author Pavel
 *
 * @param <I>
 */
public abstract class ProcessorFactory<I> implements NopEndpointProcessorFactory<Object, LabelFactory, Registry<I>> {
	
	@Override
	public boolean isPassThrough(Connection connection) {
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ProcessorInfo<Object, Registry<I>> createProcessor(ProcessorConfig<Object, Registry<I>> config, ProgressMonitor progressMonitor) {
		if (config instanceof NodeProcessorConfig) {
			NodeProcessor<I> nodeProcessor = createNodeProcessor((NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<I>>) config, progressMonitor);
			return ProcessorInfo.of(config, nodeProcessor, null); 
		} 
		
		if (config instanceof ConnectionProcessorConfig) {
			Object connectionProcessor = createConnectionProcessor((ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<I>>) config, progressMonitor);
			return ProcessorInfo.of(config, connectionProcessor, null); 			
		}
		
		return NopEndpointProcessorFactory.super.createProcessor(config, progressMonitor);
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
			public Collection<LabelFactory> select(Predicate<Element> predicate, NodeProcessor<I> base) {
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
			public LabelFactory find(I identifier, NodeProcessor<I> base) {
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
	
	protected abstract LabelFactory resolve(NodeProcessor<I> p, NodeProcessor<I> base);
	
	protected abstract Collection<I> getIdentifiers(Element element); 
	
	protected abstract NodeProcessor<I> createNodeProcessor(
			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<I>> config, 
			ProgressMonitor progressMonitor);

	protected abstract Object createConnectionProcessor(
			ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<I>> config,
			ProgressMonitor progressMonitor);
		
}