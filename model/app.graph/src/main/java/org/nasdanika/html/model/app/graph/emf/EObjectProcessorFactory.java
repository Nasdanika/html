package org.nasdanika.html.model.app.graph.emf;

import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorInfo;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.graph.WidgetFactory;

/**
 * Processor factory which uses EObjet URI's as identifiers and adapters to create node processors.
 * @author Pavel
 *
 */
public class EObjectProcessorFactory extends ProcessorFactory<Object> {
	
	@SuppressWarnings("unchecked")
	@Override
	protected ProcessorInfo<Object> createProcessor(
			ProcessorConfig config, 
			boolean parallel,
			Function<Element, CompletionStage<ProcessorInfo<Object>>> infoProvider,
			Consumer<CompletionStage<?>> stageConsumer, ProgressMonitor progressMonitor) {
		
		if (config.getElement() instanceof EReferenceConnection) {
			return config.toInfo(new ConnectionProcessor((ConnectionProcessorConfig<WidgetFactory, WidgetFactory>) config));
		}
		
		if (config.getElement() instanceof EObjectNode) {
			Object adapter = EcoreUtil.getRegisteredAdapter(((EObjectNode) config.getElement()).get(), NodeProcessorInfo.Factory.class);
			if (adapter instanceof NodeProcessorInfo.Factory) {
				return ((NodeProcessorInfo.Factory<Object,WidgetFactory,WidgetFactory>) adapter).create((NodeProcessorConfig<WidgetFactory, WidgetFactory>) config, parallel, infoProvider, stageConsumer, getContext(), progressMonitor);
			}
		}
		
		return super.createProcessor(config, parallel, infoProvider, stageConsumer, progressMonitor);
	}
	
	protected Context getContext() {
		return Context.EMPTY_CONTEXT;
	}
		
}
