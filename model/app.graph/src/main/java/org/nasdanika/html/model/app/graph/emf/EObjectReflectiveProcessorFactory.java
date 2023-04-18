package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.IntrospectionLevel;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.NodeProcessor;
import org.nasdanika.html.model.app.graph.ReflectiveProcessorFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.ncore.util.NcoreUtil;

public class EObjectReflectiveProcessorFactory extends ReflectiveProcessorFactory<URI> {

	public EObjectReflectiveProcessorFactory(IntrospectionLevel introspectionLevel, Object... targets) {
		super(introspectionLevel, targets);
	}
	
	@Override
	public ProcessorInfo<Object, Registry<URI>> createProcessor(ProcessorConfig<Object, Registry<URI>> config, ProgressMonitor progressMonitor) {
		ProcessorInfo<Object, Registry<URI>> processor = super.createProcessor(config, progressMonitor);
		if (processor == null && config instanceof ConnectionProcessorConfig) {
			return ProcessorInfo.of(config, new EReferenceConnectionProcessor((ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>>) config), null);
		}
			
		return processor;
	}

	@Override
	protected LabelFactory resolve(NodeProcessor<URI> p, NodeProcessor<URI> base) {
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

}
