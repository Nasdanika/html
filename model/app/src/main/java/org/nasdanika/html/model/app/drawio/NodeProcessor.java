package org.nasdanika.html.model.app.drawio;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

public class NodeProcessor extends LayerProcessor {

	public NodeProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
		
		NodeProcessorConfig<ElementProcessor, Handler, Handler> nodeProcessorConfig = (NodeProcessorConfig<ElementProcessor, Handler, Handler>) config;
		
		for (Entry<org.nasdanika.graph.Connection, Consumer<Handler>> incomingHandlerConsumerEntry: nodeProcessorConfig.getIncomingHandlerConsumers().entrySet()) {
			incomingHandlerConsumerEntry.getValue().accept(new Handler() {

				@Override
				public void setDefaultConnectionRole(EReference connectionRole, Predicate<Element> traversePredicate) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void setSemanticParentInfo(ProcessorInfo<ElementProcessor> semanticParentInfo) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
	}
	
	@Override
	public void setRegistry(Map<Element, ProcessorInfo<ElementProcessor>> registry) {
		super.setRegistry(registry);
		
		// Resolving connection default roles by traversing from all nodes with default connection property set
		Node node = (Node) config.getElement();
		String defaultConnectionRoleName = getDefaultConnectionRoleName();
		if (!Util.isBlank(defaultConnectionRoleName)) {
			EReference defaultConnectionRole = resourceFactory.resolveRole(defaultConnectionRoleName);
			if (defaultConnectionRole != null) {
				for (Connection outboundConnection: node.getOutgoingConnections()) {
					((ConnectionProcessor) registry.get(outboundConnection).getProcessor()).setDefaultConnectionRole(registry, defaultConnectionRole, new HashSet<>());						
				}	
				
				setLinkedPageDefaultConnectionRole(registry, defaultConnectionRole, new HashSet<>());
			}				
		}		
	}
	
	protected void setLinkedPageDefaultConnectionRole(Map<Element, ProcessorInfo<ElementProcessor>> registry, EReference defaultConnectionRole, Set<Node> traversed) {
		Page linkedPage = ((Node) config.getElement()).getLinkedPage();
		if (linkedPage != null) {
			// TODO - getPageElementEntry(null, linkedPage, null)
		}		
	}
	
	// TODO Semantic children - children for which this one is a semantic parent
	// TODO Use endpoints and handlers to talk to connections such as set default connection role, set semantic parent, isSemanticDescendant 

	/**
	 * 
	 * @param node
	 * @param defaultConnectionRoleProperty
	 */
	public void setDefaultConnectionRole(Map<Element, ProcessorInfo<ElementProcessor>> registry, EReference defaultConnectionRole, Set<Node> traversed) {
		Node node = (Node) config.getElement();
		String defaultConnectionRoleName = getDefaultConnectionRoleName();			
		if (Util.isBlank(defaultConnectionRoleName) && traversed.add(node)) {
			for (Connection outboundConnection: node.getOutgoingConnections()) {
				((ConnectionProcessor) registry.get(outboundConnection).getProcessor()).setDefaultConnectionRole(registry, defaultConnectionRole, traversed);					
			}
			setLinkedPageDefaultConnectionRole(registry, defaultConnectionRole, traversed);
		}
	}

	protected String getDefaultConnectionRoleName() {
		String defaultConnectionRoleProperty = resourceFactory.getDefaultConnectionRoleProperty();
		if (Util.isBlank(defaultConnectionRoleProperty)) {
			return null;
		}
		return ((Node) config.getElement()).getProperty(defaultConnectionRoleProperty);
	}	
	
}
