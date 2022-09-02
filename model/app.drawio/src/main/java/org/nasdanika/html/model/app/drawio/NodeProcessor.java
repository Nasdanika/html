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
	
//	@Override
//	public void setRegistry(Map<Element, ProcessorInfo<ElementProcessor>> registry) {
//		super.setRegistry(registry);
//		
//		// Resolving connection default roles by traversing from all nodes with default connection property set
//		Node node = getElement();
//		String defaultConnectionRoleName = getDefaultConnectionRoleName();
//		if (!Util.isBlank(defaultConnectionRoleName)) {
//			EReference defaultConnectionRole = resourceFactory.resolveRole(defaultConnectionRoleName);
//			if (defaultConnectionRole != null) {
//				for (Connection outboundConnection: node.getOutgoingConnections()) {
//					((ConnectionProcessor) registry.get(outboundConnection).getProcessor()).setDefaultConnectionRole(registry, defaultConnectionRole, new HashSet<>());						
//				}	
//				
//				setLinkedPageDefaultConnectionRole(registry, defaultConnectionRole, new HashSet<>());
//			}				
//		}		
//	}
//	
//	protected void setLinkedPageDefaultConnectionRole(Map<Element, ProcessorInfo<ElementProcessor>> registry, EReference defaultConnectionRole, Set<Node> traversed) {
//		Page linkedPage = getElement().getLinkedPage();
//		if (linkedPage != null) {
//			// TODO - getPageElementEntry(null, linkedPage, null)
//		}		
//	}
//	
//	// TODO Semantic children - children for which this one is a semantic parent
//	// TODO Use endpoints and handlers to talk to connections such as set default connection role, set semantic parent, isSemanticDescendant 
//
//	/**
//	 * 
//	 * @param node
//	 * @param defaultConnectionRoleProperty
//	 */
//	public void setDefaultConnectionRole(Map<Element, ProcessorInfo<ElementProcessor>> registry, EReference defaultConnectionRole, Set<Node> traversed) {
//		Node node = getElement();
//		String defaultConnectionRoleName = getDefaultConnectionRoleName();			
//		if (Util.isBlank(defaultConnectionRoleName) && traversed.add(node)) {
//			for (Connection outboundConnection: node.getOutgoingConnections()) {
//				((ConnectionProcessor) registry.get(outboundConnection).getProcessor()).setDefaultConnectionRole(registry, defaultConnectionRole, traversed);					
//			}
//			setLinkedPageDefaultConnectionRole(registry, defaultConnectionRole, traversed);
//		}
//	}
//
//	protected String getDefaultConnectionRoleName() {
//		String defaultConnectionRoleProperty = resourceFactory.getDefaultConnectionRoleProperty();
//		if (Util.isBlank(defaultConnectionRoleProperty)) {
//			return null;
//		}
//		return getElement().getProperty(defaultConnectionRoleProperty);
//	}
	
	// ----	
	
//	@SuppressWarnings("unchecked")
//	@Override
//	protected void resolve(
//			Resource resource, 
//			Element element, 
//			Map<EReference, List<Action>> semanticElement,
//			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,
//			Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
//		
//		if (element instanceof Document) {
//			// Rearranging actions for connections with role
//			for (Connection connection: element.stream(connectionBase).filter(Connection.class::isInstance).map(Connection.class::cast).collect(Collectors.toList())) {
//	            EReference connectionRole = getConnectionRole(connection);
//	            if (connectionRole != null) {
//		            Node connectionSource = connection.getSource();
//		            if (connectionSource != null) {
//			            Element toResolve = connectionSource;
//			            if (isPageElement(connectionSource)) {
//			            	String rootActionFlag = connectionSource.getModel().getRoot().getProperty(getRootActionFlagProperty());
//			            	if (Util.isBlank(rootActionFlag) || !"false".equals(rootActionFlag)) {
//			            		toResolve = connectionSource.getModel().getPage();
//			            	}
//			            }
//			            
//			            Optional<Action> sourceAction = resolver.apply(toResolve::equals).values().stream().flatMap(Collection::stream).findFirst();
//			            if (sourceAction.isPresent()) {
//			                Node connectionTarget = connection.getTarget();
//			                if (connectionTarget != null) {
//			        			Comparator<Element> childComparator = loadChildComparator(connectionSource, getDefaultSort());
//			        			List<Action> childActions = ((List<Action>) sourceAction.get().eGet(connectionRole));
//								for (Action targetAction: resolver.apply(connectionTarget::equals).values().stream().flatMap(Collection::stream).collect(Collectors.toList())) {
//									Object targetActionElementAdapter = EcoreUtil.getRegisteredAdapter(targetAction, ElementAdapter.class);					
//									if (childComparator == null || targetActionElementAdapter == null || childActions.isEmpty()) {
//										childActions.add(targetAction);
//									} else {
//										Element targetElement = ((ElementAdapter) targetActionElementAdapter).getElement();
//										ListIterator<Action> listIterator = childActions.listIterator();
//										while (listIterator.hasNext()) {
//											Action next = listIterator.next();
//											Object nextElementAdapter = EcoreUtil.getRegisteredAdapter(next, ElementAdapter.class);					
//											if (nextElementAdapter instanceof ElementAdapter) {
//												 Element nextElement = ((ElementAdapter) nextElementAdapter).getElement();
//												 if (childComparator.compare(targetElement, nextElement) < 0) {
//													 listIterator.previous(); // Going back so it target is inserted before next.
//													 break;
//												 }
//											}
//										}
//										listIterator.add(targetAction);
//									}
//				                }
//			                }
//			            }
//		            }
//	            }				
//			}
//			
//			// Rearranging actions for linked pages
//			for (ModelElement modelElement: element.stream(connectionBase).filter(ModelElement.class::isInstance).map(ModelElement.class::cast).filter(ModelElement::isPageLink).collect(Collectors.toList())) {
//	            Optional<Action> linkingElementAction = resolver.apply(modelElement::equals).values().stream().flatMap(Collection::stream).findFirst();
//	            if (linkingElementAction.isPresent()) {
//	            	Page linkedPage = modelElement.getLinkedPage();
//	            	Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, linkedPage, childEntries.get(linkedPage).getChildEntries());
//		            Optional<Action> linkedElementAction = resolver.apply(pageElementEntry.getKey()::equals).values().stream().flatMap(Collection::stream).findFirst();
//		            if (linkedElementAction.isPresent()) {
//		            	linkingElementAction.get().getAnonymous().addAll(linkedElementAction.get().getAnonymous());
//		            	linkingElementAction.get().getChildren().addAll(linkedElementAction.get().getChildren());
//		            	linkingElementAction.get().getNavigation().addAll(linkedElementAction.get().getNavigation());
//		            	linkingElementAction.get().getSections().addAll(linkedElementAction.get().getSections());
//		            }
//	            }
//			}
//						
//			// Adding URI resolver adapters
//			for (EObject root: resource.getContents()) {
//				if (root instanceof Action) {
//					addURIResolverAdapters((Action) root);
//				}
//			}
//		} else if (element instanceof Page) {
//			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, (Page) element, childEntries);
//
//			for (Action action: semanticElement.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(pageElementEntry.getKey(), a)).collect(Collectors.toList())) {
//				// Embedded diagram
//				String embeddedDiagram = generateEmbeddedDiagram(element, action, resolver);
//				if (isEmbedDiagram(resource, element, childEntries)) {															
//					addContent(action, embeddedDiagram);						
//				}
//
//				// TOC
//				StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
//				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: pageElementEntry.getValue().entrySet()) { 
//					tocBuilder.append(generateTableOfContents(action, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
//				}					
//				tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator());
//				
//				if (isGenerateTableOfContents(resource, element, childEntries)) {
//					addContent(action, tocBuilder.toString());
//				}
//				
//				EObject doc = getDocumentation(resource, element, embeddedDiagram, childEntries, tocBuilder.toString());
//				if (doc == null) {
//					String rootTooltip = pageElementEntry.getKey().getTooltip();
//					if (!Util.isBlank(rootTooltip)) {
//						addContent(action, rootTooltip);
//					}
//				} else {
//					action.getContent().add(doc);
//				}					
//			}			
//		} else if (element instanceof ModelElement) {
//			for (Action action: semanticElement.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(element, a)).collect(Collectors.toList())) {
//				if (element instanceof Connection) {
//					// Source and target
//					Table table = BootstrapFactory.eINSTANCE.createTable();
//					table.getAttributes().put("style", createText("width:auto"));
//					
//					TableRow sourceRow = BootstrapFactory.eINSTANCE.createTableRow();
//					table.getRows().add(sourceRow);
//					
//					TableCell sourceHeader = BootstrapFactory.eINSTANCE.createTableCell();
//					sourceHeader.setHeader(true);
//					sourceRow.getCells().add(sourceHeader);
//					sourceHeader.getContent().add(createText("Source"));
//										
//					TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
//					sourceRow.getCells().add(sourceCell);
//					
//					Node source = ((Connection) element).getSource();
//					String sourceLabel = getModelElementLabel(source);
//					if (Util.isBlank(sourceLabel)) {
//						sourceLabel = "(unlabeled)";
//					} 
//					String sourceLink = getModelElementLink(source, action, resolver);
//					if (sourceLink == null) {
//						sourceCell.getContent().add(createText(sourceLabel));						
//					} else {
//						sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
//					}
//					
//					TableRow targetRow = BootstrapFactory.eINSTANCE.createTableRow();
//					table.getRows().add(targetRow);
//					
//					TableCell targetHeader = BootstrapFactory.eINSTANCE.createTableCell();
//					targetHeader.setHeader(true);
//					targetRow.getCells().add(targetHeader);
//					targetHeader.getContent().add(createText("Target"));
//										
//					TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
//					targetRow.getCells().add(targetCell);
//					
//					Node target = ((Connection) element).getTarget();
//					String targetLabel = getModelElementLabel(target);
//					if (Util.isBlank(targetLabel)) {
//						targetLabel = "(unlabeled)";
//					} 
//					String targetLink = getModelElementLink(target, action, resolver);
//					if (targetLink == null) {
//						targetCell.getContent().add(createText(targetLabel));						
//					} else {
//						targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
//					}					
//
//					action.getContent().add(table);
//				} else if (element instanceof Node) {
//					Node node = (Node) element;
//					EReference connectionsActionContainmentReference = getConnectionsActionContainmentReference(node);
//					if (connectionsActionContainmentReference != null) {
//						List<Connection> inboundConnections = node.getInboundConnections().stream().filter(c -> getConnectionRole(c) == null).collect(Collectors.toList());
//						if (!inboundConnections.isEmpty()) {
//							Table table = BootstrapFactory.eINSTANCE.createTable();
//							table.setBordered(true);
//							table.getAttributes().put("style", createText("width:auto"));
//							
//							TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
//							table.getRows().add(headerRow);
//												
//							TableCell labelHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							labelHeader.setHeader(true);
//							headerRow.getCells().add(labelHeader);
//							labelHeader.getContent().add(createText("Label"));
//	
//							TableCell sourceHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							sourceHeader.setHeader(true);
//							headerRow.getCells().add(sourceHeader);
//							sourceHeader.getContent().add(createText("Source"));
//	
//							TableCell tooltipHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							tooltipHeader.setHeader(true);
//							headerRow.getCells().add(tooltipHeader);
//							tooltipHeader.getContent().add(createText("Tooltip"));
//							
//							for (Connection inboundConnection: inboundConnections) { // TODO - sorting
//								TableRow connectionRow = BootstrapFactory.eINSTANCE.createTableRow();
//								table.getRows().add(connectionRow);
//													
//								TableCell labelCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(labelCell);
//								String connectionLabel = getModelElementLabel(inboundConnection);
//								if (Util.isBlank(connectionLabel) && hasDocumentation(resource, inboundConnection, childEntries)) {
//									connectionLabel = "(unlabeled)";
//								}
//								if (!Util.isBlank(connectionLabel)) {
//									String connectionLink = getModelElementLink(inboundConnection, action, resolver);
//									if (connectionLink == null) {
//										labelCell.getContent().add(createText(connectionLabel));						
//									} else {
//										labelCell.getContent().add(createText("<a href=\"" + connectionLink + "\">" + connectionLabel + "</a>"));												
//									}
//								}
//		
//								TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(sourceCell);
//
//								Node source = inboundConnection.getSource();
//								String sourceLabel = getModelElementLabel(source);
//								if (Util.isBlank(sourceLabel)) {
//									sourceLabel = "(unlabeled)";
//								} 
//								String sourceLink = getModelElementLink(source, action, resolver);
//								if (sourceLink == null) {
//									sourceCell.getContent().add(createText(sourceLabel));						
//								} else {
//									sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
//								}
//		
//								TableCell tooltipCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(tooltipCell);
//								String connectionTooltip = inboundConnection.getTooltip();
//								if (!Util.isBlank(connectionTooltip)) {
//									tooltipCell.getContent().add(createText(connectionTooltip));
//								}								
//							}							
//							
//							Action inboundConnectionsAction = AppFactory.eINSTANCE.createAction();
//							inboundConnectionsAction.setText("Inbound connections");
//							if (connectionsActionContainmentReference == AppPackage.Literals.ACTION__SECTIONS) {
//								inboundConnectionsAction.setName("inbound-connections");
//							} else {
//								inboundConnectionsAction.setLocation("inbound-connections.html");								
//							}
//							inboundConnectionsAction.getContent().add(table);
//							((Collection<Action>) action.eGet(connectionsActionContainmentReference)).add(inboundConnectionsAction);
//						}
//						
//						List<Connection> outboundConnections = node.getOutboundConnections().stream().filter(c -> getConnectionRole(c) == null).collect(Collectors.toList());
//						if (!outboundConnections.isEmpty()) {
//							Table table = BootstrapFactory.eINSTANCE.createTable();
//							table.setBordered(true);
//							table.getAttributes().put("style", createText("width:auto"));
//							
//							TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
//							table.getRows().add(headerRow);
//												
//							TableCell labelHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							labelHeader.setHeader(true);
//							headerRow.getCells().add(labelHeader);
//							labelHeader.getContent().add(createText("Label"));
//	
//							TableCell targetHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							targetHeader.setHeader(true);
//							headerRow.getCells().add(targetHeader);
//							targetHeader.getContent().add(createText("Target"));
//	
//							TableCell tooltipHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							tooltipHeader.setHeader(true);
//							headerRow.getCells().add(tooltipHeader);
//							tooltipHeader.getContent().add(createText("Tooltip"));
//							
//							for (Connection outboundConnection: outboundConnections) { // TODO - sorting
//								TableRow connectionRow = BootstrapFactory.eINSTANCE.createTableRow();
//								table.getRows().add(connectionRow);
//													
//								TableCell labelCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(labelCell);
//								String connectionLabel = getModelElementLabel(outboundConnection);
//								if (Util.isBlank(connectionLabel) && hasDocumentation(resource, outboundConnection, childEntries)) {
//									connectionLabel = "(unlabeled)";
//								}
//								if (!Util.isBlank(connectionLabel)) {
//									String connectionLink = getModelElementLink(outboundConnection, action, resolver);
//									if (connectionLink == null) {
//										labelCell.getContent().add(createText(connectionLabel));						
//									} else {
//										labelCell.getContent().add(createText("<a href=\"" + connectionLink + "\">" + connectionLabel + "</a>"));												
//									}
//								}
//		
//								TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(targetCell);
//
//								Node target = outboundConnection.getTarget();
//								String targetLabel = getModelElementLabel(target);
//								if (Util.isBlank(targetLabel)) {
//									targetLabel = "(unlabeled)";
//								} 
//								String targetLink = getModelElementLink(target, action, resolver);
//								if (targetLink == null) {
//									targetCell.getContent().add(createText(targetLabel));						
//								} else {
//									targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
//								}
//		
//								TableCell tooltipCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(tooltipCell);
//								String connectionTooltip = outboundConnection.getTooltip();
//								if (!Util.isBlank(connectionTooltip)) {
//									tooltipCell.getContent().add(createText(connectionTooltip));
//								}								
//							}							
//							
//							Action outboundConnectionsAction = AppFactory.eINSTANCE.createAction();
//							outboundConnectionsAction.setText("Outbound connections");
//							if (connectionsActionContainmentReference == AppPackage.Literals.ACTION__SECTIONS) {							
//								outboundConnectionsAction.setName("outbound-connections");
//							} else {
//								outboundConnectionsAction.setLocation("outbound-connections.html");								
//							}
//							outboundConnectionsAction.getContent().add(table);
//							((Collection<Action>) action.eGet(connectionsActionContainmentReference)).add(outboundConnectionsAction);
//						}
//					}
//				}
//				
//				// Embedded diagram
//				String embeddedDiagram = null;
//				
//				Page linkedPage = ((ModelElement) element).getLinkedPage();
//				if (linkedPage != null) {
//					embeddedDiagram = generateEmbeddedDiagram(linkedPage, action, resolver);
//					if (isEmbedDiagram(resource, element, childEntries)) {															
//						addContent(action, embeddedDiagram);						
//					}
//				}								
//				
//				StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
//				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) { 						
//					Element child = childEntry.getKey();
//					if (child instanceof ModelElement) {
//						tocBuilder.append(generateTableOfContents(action, (ModelElement) child, childEntry.getValue().getChildEntries(), resolver));
//					}
//				}								
//				tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator());
//				
//				if (isGenerateTableOfContents(resource, element, childEntries)) {
//					addContent(action, tocBuilder.toString());
//				}
//				
//				EObject doc = getDocumentation(resource, element, embeddedDiagram, childEntries, tocBuilder.toString());
//				if (doc == null) {
//					String tooltip = ((ModelElement) element).getTooltip();
//					if (!Util.isBlank(tooltip)) {
//						addContent(action, tooltip);
//					}
//				} else {					
//					action.getContent().add(doc);
//				}
//			}
//		}
//	}
	
}
