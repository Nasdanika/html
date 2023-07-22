package org.nasdanika.html.model.app.graph.emf;

import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class EObjectReflectiveProcessorFactoryProvider extends ReflectiveProcessorFactoryProvider<Object, WidgetFactory, WidgetFactory> {

	public EObjectReflectiveProcessorFactoryProvider(Object... targets) {
		super(targets);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Object createProcessor(
			ProcessorConfig config,
			boolean parallel,
			Function<Element, CompletionStage<ProcessorInfo<Object>>> infoProvider,
			Consumer<CompletionStage<?>> stageConsumer, 
			ProgressMonitor progressMonitor) {
		
		Object processor = super.createProcessor(config, parallel, infoProvider, stageConsumer, progressMonitor);
		if (processor == null && config instanceof ConnectionProcessorConfig) {
			return new ConnectionProcessor((ConnectionProcessorConfig<WidgetFactory, WidgetFactory>) config);
		}
			
		return processor;
	}
//
//	@Override
//	protected WidgetFactory resolve(NodeProcessor<URI> p, NodeProcessor<URI> base) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected Collection<URI> getIdentifiers(Element element) {
//		if (element instanceof EObjectNode) {
//			return NcoreUtil.getIdentifiers(((EObjectNode) element).getTarget());
//		}
//		
//		return Collections.emptyList();
//	}
//	
//	public boolean isPassThrough(Connection connection) {
//		return false;
//	}

}
