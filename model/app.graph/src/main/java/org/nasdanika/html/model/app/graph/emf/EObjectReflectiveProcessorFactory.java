package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.html.model.app.graph.NodeProcessor;
import org.nasdanika.html.model.app.graph.ReflectiveProcessorFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.ncore.util.NcoreUtil;

public class EObjectReflectiveProcessorFactory extends ReflectiveProcessorFactory<URI> {

	public EObjectReflectiveProcessorFactory(Object... targets) {
		super(targets);
	}
	
	@Override
	public Object createProcessor(ProcessorConfig<Object, Registry<URI>> config, boolean parallel, Consumer<CompletionStage<?>> stageCollector, ProgressMonitor progressMonitor) {
		Object processor = super.createProcessor(config, parallel, stageCollector, progressMonitor);
		if (processor == null && config instanceof ConnectionProcessorConfig) {
			return new ConnectionProcessor((ConnectionProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>>) config);
		}
			
		return processor;
	}

	@Override
	protected WidgetFactory resolve(NodeProcessor<URI> p, NodeProcessor<URI> base) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Collection<URI> getIdentifiers(Element element) {
		if (element instanceof EObjectNode) {
			return NcoreUtil.getIdentifiers(((EObjectNode) element).getTarget());
		}
		
		return Collections.emptyList();
	}
	
	public boolean isPassThrough(Connection connection) {
		return false;
	}

}
