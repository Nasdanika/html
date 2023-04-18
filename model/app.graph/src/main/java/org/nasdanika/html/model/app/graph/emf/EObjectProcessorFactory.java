package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.NodeProcessor;
import org.nasdanika.html.model.app.graph.ProcessorFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.URINodeProcessor;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * Processor factory which uses EObjet URI's as identifiers and adapters to create node processors.
 * @author Pavel
 *
 */
public class EObjectProcessorFactory extends ProcessorFactory<URI> {

	@Override
	protected Collection<URI> getIdentifiers(Element element) {
		if (element instanceof EObjectNode) {
			return NcoreUtil.getIdentifiers(((EObjectNode) element).getTarget());
		}
		
		return Collections.emptyList();
	}

	@Override
	protected EReferenceConnectionProcessor createConnectionProcessor(
			ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
			ProgressMonitor progressMonitor) {
		
		Connection connection = config.getElement();
		if (connection instanceof EReferenceConnection) {
			return new EReferenceConnectionProcessor(config);
		}

		return null;
	}

	@Override
	protected URINodeProcessor createNodeProcessor(
			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
			ProgressMonitor progressMonitor) {
		
		Node node = config.getElement();
		if (node instanceof EObjectNode) {
			Object adapter = EcoreUtil.getRegisteredAdapter(((EObjectNode) node).getTarget(), NodeProcessor.Factory.class);
			if (adapter instanceof NodeProcessor.Factory) {
				return ((URINodeProcessor.Factory) adapter).create(config, getContext(), progressMonitor);
			}
		}
		return null;
	}
	
	protected Context getContext() {
		return Context.EMPTY_CONTEXT;
	}
	
	@Override
	protected LabelFactory resolve(NodeProcessor<URI> p, NodeProcessor<URI> base) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
