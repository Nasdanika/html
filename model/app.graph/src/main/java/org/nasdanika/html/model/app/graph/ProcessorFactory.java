package org.nasdanika.html.model.app.graph;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.NopEndpointProcessorFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

public abstract class ProcessorFactory<I> implements NopEndpointProcessorFactory<Processor, LabelInfo, Registry<I>> {
	
	@Override
	public boolean isPassThrough(Connection connection) {
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ProcessorInfo<Processor, Registry<I>> createProcessor(ProcessorConfig<Processor, Registry<I>> config, ProgressMonitor progressMonitor) {
		if (config instanceof NodeProcessorConfig) {
			NodeProcessor<I> nodeProcessor = createNodeProcessor((NodeProcessorConfig<Processor, LabelInfo, LabelInfo, Registry<I>>) config, progressMonitor);
			return ProcessorInfo.of(config, nodeProcessor, null); 
		} 
		
		if (config instanceof ConnectionProcessorConfig) {
			ConnectionProcessor<I> connectionProcessor = createConnectionProcessor((ConnectionProcessorConfig<Processor, LabelInfo, LabelInfo, Registry<I>>) config, progressMonitor);
			return ProcessorInfo.of(config, connectionProcessor, null); 			
		}
		
		return NopEndpointProcessorFactory.super.createProcessor(config, progressMonitor);
	}

	@Override
	public Registry<I> createRegistry(Map<Element, ProcessorInfo<Processor, Registry<I>>> registry) {
		return new Registry<I>() {

			@Override
			public Map<Element, ProcessorInfo<Processor, Registry<I>>> getProcessorInfoMap() {
				return registry;
			}

			@Override
			public Collection<LabelInfo> select(Predicate<Element> predicate, Processor base) {
				return registry
						.entrySet()
						.stream()
						.filter(e -> predicate.test(e.getKey()))
						.map(e -> ProcessorFactory.this.resolve(e.getValue().getProcessor(), base))
						.collect(Collectors.toList());
			}

			@Override
			public LabelInfo find(Object identifier, Processor base) {
				return registry
						.entrySet()
						.stream()
						.filter(e -> getIdentifiers(e.getKey()).contains(identifier))
						.map(e -> ProcessorFactory.this.resolve(e.getValue().getProcessor(), base))
						.findFirst()
						.orElse(null);
			}
			
		};
	}
	
	protected abstract LabelInfo resolve(Processor p, Processor base);
	
	protected abstract Collection<I> getIdentifiers(Element element); 
	
	protected abstract ConnectionProcessor<I> createConnectionProcessor(ConnectionProcessorConfig<Processor, LabelInfo, LabelInfo, Registry<I>> config, ProgressMonitor progressMonitor);
	
	protected abstract NodeProcessor<I> createNodeProcessor(NodeProcessorConfig<Processor, LabelInfo, LabelInfo, Registry<I>> config, ProgressMonitor progressMonitor);



}
