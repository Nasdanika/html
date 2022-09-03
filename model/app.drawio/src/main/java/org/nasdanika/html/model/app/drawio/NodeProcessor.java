package org.nasdanika.html.model.app.drawio;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableRow;

public class NodeProcessor extends LayerProcessor {

	public NodeProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		super(resourceFactory, uri, config, baseURI);
		
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
	public Node getElement() {
		return (Node) super.getElement();
	}
	
	@Override
	public List<ProcessorInfo<ElementProcessor>> collectSemanticChildrenInfo(ProcessorInfo<ElementProcessor> semanticParentInfo) {
		List<ProcessorInfo<ElementProcessor>> sci = super.collectSemanticChildrenInfo(semanticParentInfo);
		// TODO - connections with target-role 
		return sci;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void build() {
		super.build();
		EReference connectionsActionContainmentReference = resourceFactory.getConnectionsActionContainmentReference(getElement());
		if (connectionsActionContainmentReference != null) {
			List<Connection> incomingConnections = getElement().getIncomingConnections(); // TODO - filtering - do not show mapping connections
			if (!incomingConnections.isEmpty()) {
				Table table = BootstrapFactory.eINSTANCE.createTable();
				table.setBordered(true);
				table.getAttributes().put("style", createText("width:auto"));
				
				TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
				table.getRows().add(headerRow);
									
				TableCell labelHeader = BootstrapFactory.eINSTANCE.createTableCell();
				labelHeader.setHeader(true);
				headerRow.getCells().add(labelHeader);
				labelHeader.getContent().add(createText("Label"));
	
				TableCell sourceHeader = BootstrapFactory.eINSTANCE.createTableCell();
				sourceHeader.setHeader(true);
				headerRow.getCells().add(sourceHeader);
				sourceHeader.getContent().add(createText("Source"));
	
				TableCell tooltipHeader = BootstrapFactory.eINSTANCE.createTableCell();
				tooltipHeader.setHeader(true);
				headerRow.getCells().add(tooltipHeader);
				tooltipHeader.getContent().add(createText("Tooltip"));
				
				for (Connection incomingConnection: incomingConnections) { // TODO - sorting
					ProcessorInfo<ElementProcessor> incomingConnectionInfo = registry.get(incomingConnection);
					ModelElementProcessor incomingConnectionProcessor = (ModelElementProcessor) incomingConnectionInfo.getProcessor();
					
					TableRow connectionRow = BootstrapFactory.eINSTANCE.createTableRow();
					table.getRows().add(connectionRow);
										
					TableCell labelCell = BootstrapFactory.eINSTANCE.createTableCell();
					connectionRow.getCells().add(labelCell);
					String connectionLabel = incomingConnectionProcessor.getText(); 
					if (Util.isBlank(connectionLabel) && incomingConnectionProcessor.hasDocumentation()) {
						connectionLabel = "(unlabeled)";
					}
					if (!Util.isBlank(connectionLabel)) {
						String connectionLink = getModelElementLink(incomingConnection);
						if (connectionLink == null) {
							labelCell.getContent().add(createText(connectionLabel));						
						} else {
							labelCell.getContent().add(createText("<a href=\"" + connectionLink + "\">" + connectionLabel + "</a>"));												
						}
					}
	
					TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
					connectionRow.getCells().add(sourceCell);
	
					Node source = incomingConnection.getSource();
					if (source != null) {
						ModelElementProcessor sourceProcessor = (ModelElementProcessor) registry.get(source).getProcessor(); 
						String sourceLabel =  sourceProcessor.getText();
						if (Util.isBlank(sourceLabel)) {
							sourceLabel = "(unlabeled)";
						} 
						String sourceLink = getModelElementLink(source);
						if (sourceLink == null) {
							sourceCell.getContent().add(createText(sourceLabel));						
						} else {
							sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
						}
					}
	
					TableCell tooltipCell = BootstrapFactory.eINSTANCE.createTableCell();
					connectionRow.getCells().add(tooltipCell);
					String connectionTooltip = incomingConnection.getTooltip();
					if (!Util.isBlank(connectionTooltip)) {
						tooltipCell.getContent().add(createText(connectionTooltip));
					}								
				}							
				
				Action incomingConnectionsAction = AppFactory.eINSTANCE.createAction();
				incomingConnectionsAction.setText("Incoming connections");
				if (connectionsActionContainmentReference == AppPackage.Literals.ACTION__SECTIONS) {
					incomingConnectionsAction.setName("incoming-connections");
				} else {
					incomingConnectionsAction.setLocation("incoming-connections.html");								
				}
				incomingConnectionsAction.getContent().add(table);
				((Collection<Action>) getSemanticElement().eGet(connectionsActionContainmentReference)).add(incomingConnectionsAction);
			}
			
			List<Connection> outgoingConnections = getElement().getOutgoingConnections(); // TODO - filtering, do not show mapping connections
			if (!outgoingConnections.isEmpty()) {
				Table table = BootstrapFactory.eINSTANCE.createTable();
				table.setBordered(true);
				table.getAttributes().put("style", createText("width:auto"));
				
				TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
				table.getRows().add(headerRow);
									
				TableCell labelHeader = BootstrapFactory.eINSTANCE.createTableCell();
				labelHeader.setHeader(true);
				headerRow.getCells().add(labelHeader);
				labelHeader.getContent().add(createText("Label"));
	
				TableCell targetHeader = BootstrapFactory.eINSTANCE.createTableCell();
				targetHeader.setHeader(true);
				headerRow.getCells().add(targetHeader);
				targetHeader.getContent().add(createText("Target"));
	
				TableCell tooltipHeader = BootstrapFactory.eINSTANCE.createTableCell();
				tooltipHeader.setHeader(true);
				headerRow.getCells().add(tooltipHeader);
				tooltipHeader.getContent().add(createText("Tooltip"));
				
				for (Connection outgoingConnection: outgoingConnections) { // TODO - sorting
					ProcessorInfo<ElementProcessor> outgoingConnectionInfo = registry.get(outgoingConnection);
					ModelElementProcessor outgoingConnectionProcessor = (ModelElementProcessor) outgoingConnectionInfo.getProcessor();
					
					TableRow connectionRow = BootstrapFactory.eINSTANCE.createTableRow();
					table.getRows().add(connectionRow);
										
					TableCell labelCell = BootstrapFactory.eINSTANCE.createTableCell();
					connectionRow.getCells().add(labelCell);
					String connectionLabel = outgoingConnectionProcessor.getText();
					if (Util.isBlank(connectionLabel) && outgoingConnectionProcessor.hasDocumentation()) {
						connectionLabel = "(unlabeled)";
					}
					if (!Util.isBlank(connectionLabel)) {
						String connectionLink = getModelElementLink(outgoingConnection);
						if (connectionLink == null) {
							labelCell.getContent().add(createText(connectionLabel));						
						} else {
							labelCell.getContent().add(createText("<a href=\"" + connectionLink + "\">" + connectionLabel + "</a>"));												
						}
					}
	
					TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
					connectionRow.getCells().add(targetCell);
	
					Node target = outgoingConnection.getTarget();
					if (target != null) {
						ModelElementProcessor targetProcessor = (ModelElementProcessor) registry.get(target).getProcessor(); 
						String targetLabel =  targetProcessor.getText();
						if (Util.isBlank(targetLabel)) {
							targetLabel = "(unlabeled)";
						} 
						String targetLink = getModelElementLink(target);
						if (targetLink == null) {
							targetCell.getContent().add(createText(targetLabel));						
						} else {
							targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
						}
					}
	
					TableCell tooltipCell = BootstrapFactory.eINSTANCE.createTableCell();
					connectionRow.getCells().add(tooltipCell);
					String connectionTooltip = outgoingConnection.getTooltip();
					if (!Util.isBlank(connectionTooltip)) {
						tooltipCell.getContent().add(createText(connectionTooltip));
					}								
				}							
				
				Action outboundConnectionsAction = AppFactory.eINSTANCE.createAction();
				outboundConnectionsAction.setText("Outgoing connections");
				if (connectionsActionContainmentReference == AppPackage.Literals.ACTION__SECTIONS) {							
					outboundConnectionsAction.setName("outgoing-connections");
				} else {
					outboundConnectionsAction.setLocation("outgoing-connections.html");								
				}
				outboundConnectionsAction.getContent().add(table);
				((Collection<Action>) getSemanticElement().eGet(connectionsActionContainmentReference)).add(outboundConnectionsAction);
			}
		}		
	}
	
}
