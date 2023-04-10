package org.nasdanika.html.model.app.graph;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * Processor factory which uses EObjet URI's as identifiers
 * @author Pavel
 *
 */
public class EObjectProcessorFactory extends ProcessorFactory<URI> {

	@Override
	protected LabelInfo resolve(Processor p, Processor base) {
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

	@Override
	protected ConnectionProcessor<URI> createConnectionProcessor(
			ConnectionProcessorConfig<Processor, LabelInfo, LabelInfo, Registry<URI>> config,
			ProgressMonitor progressMonitor) {
		
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected NodeProcessor<URI> createNodeProcessor(
			NodeProcessorConfig<Processor, LabelInfo, LabelInfo, Registry<URI>> config,
			ProgressMonitor progressMonitor) {
		
		Node node = config.getElement();
		if (node instanceof EObjectNode) {
			Object adapter = EcoreUtil.getRegisteredAdapter(((EObjectNode) node).getTarget(), NodeProcessor.Factory.class);
			if (adapter instanceof NodeProcessor.Factory) {
				return ((NodeProcessor.Factory<URI>) adapter).create(config, getContext(), progressMonitor);
			}
		}
		return null;
	}
	
	protected Context getContext() {
		return Context.EMPTY_CONTEXT;
	}

}
