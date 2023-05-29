package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
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
	public ProcessorInfo<Object, Registry<URI>> createProcessor(ProcessorConfig<Object, Registry<URI>> config, ProgressMonitor progressMonitor) {
		ProcessorInfo<Object, Registry<URI>> processorInfo = super.createProcessor(config, progressMonitor);
		if (processorInfo.getProcessor() == null && config instanceof ConnectionProcessorConfig) {
			return ProcessorInfo.of(config, new ConnectionProcessor((ConnectionProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>>) config), null);
		}
			
		return processorInfo;
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
