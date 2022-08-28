package org.nasdanika.html.model.app.drawio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jsoup.Jsoup;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.DrawioResourceFactory;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ElementAdapter;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.ExecFactory;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableRow;

// TODO - child comparators here and "sort" property. 

/**
 * Creates an action model from Drawio documents
 * @author Pavel
 *
 */
public class AppDrawioResourceFactory extends DrawioResourceFactory<Map<EReference,List<Action>>> {
	
	@Override
	protected Map<EReference, List<Action>> createSemanticElement(Resource resource, Element element, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
		
		EReference containmentReference = getContainmentReference(resource, element);
		Action action = null;
		
		if (element instanceof Document) {
			action = createDocumentAction(resource, (Document) element, childEntries);
			if (action == null) {
				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) {
					for (Entry<EReference, List<Action>> referenceEntry: childEntry.getValue().getSemanticElement().entrySet()) {						
						for (Action rootAction: referenceEntry.getValue()) {
							if (isRootAction(childEntry.getKey(), rootAction)) {
								resource.getContents().add(rootAction);
							}
						}
					}
				}				
			} else {
				resource.getContents().add(action);
			}			
		} else if (element instanceof Page) {
			action = createPageAction(resource, (Page) element, childEntries);
		} else if (element instanceof Node) {
			action = createNodeAction(resource, (Node) element, childEntries, containmentReference);
		} else if (element instanceof Layer) {
			action = createLayerAction(resource, (Layer) element, childEntries, containmentReference);
		} else if (element instanceof Connection) {
			action = createConnectionAction(resource, (Connection) element, childEntries, containmentReference);
		}
		
		if (action == null) {
			// Pass-through with grouping
			Map<EReference, List<Action>> ret = new HashMap<>();				
			for (ElementEntry<Map<EReference, List<Action>>> childEntry: childEntries.values()) {
				for (Entry<EReference, List<Action>> referenceEntry: childEntry.getSemanticElement().entrySet()) {						
					ret.computeIfAbsent(referenceEntry.getKey(), ref -> new ArrayList<>()).addAll(referenceEntry.getValue());	
				}
			}
			return ret;
		}
		
		return Collections.singletonMap(containmentReference, Collections.singletonList(action));
	}
	
	protected ModelElement getSemanticParent(ModelElement element) {
		return semanticParents.computeIfAbsent(element, super::getSemanticParent);
	}
	
	
	/**
	 * Adapter for {@link Action}s to resolve cross-references.
	 * @author Pavel
	 *
	 */
	public interface URIResolverAdapter extends Adapter {
		
		/**
		 * @param base Base action. Can be null.
		 * @return URI to the target action of this adapter relative to the base action.
		 */
		URI resolve(Action base);
		
	}
	
	protected boolean isActionForElement(Element element, Action action) {
		Object elementAdapter = EcoreUtil.getRegisteredAdapter(action, ElementAdapter.class);					
		return elementAdapter instanceof ElementAdapter && element.equals(((ElementAdapter) elementAdapter).getElement());			
	}
		
	protected void addURIResolverAdapters(Action root) {
		BiFunction<Label, URI, URI> uriResolver = org.nasdanika.html.model.app.util.Util.uriResolver(root);
		class URIResolverAdapterImpl extends AdapterImpl implements URIResolverAdapter {
						
			@Override
			public boolean isAdapterForType(Object type) {
				return URIResolverAdapter.class == type;
			}

			@Override
			public URI resolve(Action base) {
				Action targetAction = (Action) getTarget();
				if (targetAction == base) {
					return null;
				}				
				URI baseURI = base == null ? null : uriResolver.apply(base, null);
				return uriResolver.apply(targetAction, baseURI);
			}

		}
		
		root.eAdapters().add(new URIResolverAdapterImpl());
		TreeIterator<EObject> cit = root.eAllContents();
		while (cit.hasNext()) {
			EObject next = cit.next();
			if (next instanceof Action /* && EcoreUtil.getRegisteredAdapter(next, URIResolverAdapter.class) == null */) {
				next.eAdapters().add(new URIResolverAdapterImpl());
			}
		}			
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void resolve(
			Resource resource, 
			Element element, 
			Map<EReference, List<Action>> semanticElement,
			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,
			Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
		
		if (element instanceof Document) {
			
			// Resolving assigned connection roles.
			String connectionRoleProperty = getRoleProperty();
			if (!Util.isBlank(connectionRoleProperty)) {
				element.stream(connectionBase)
					.filter(Connection.class::isInstance)
					.map(Connection.class::cast)
					.forEach(connection -> {
						String connectionRoleName = connection.getProperty(connectionRoleProperty);
						if (!Util.isBlank(connectionRoleName)) {
							EReference connectionRole = resolveRole(connectionRoleName);
							if (connectionRole == null) {
								connectionRoles.remove(connection);
							} else {
								connectionRoles.put(connection, connectionRole);
							}
						}
					});				
			}
			
			// Resolving semantic parents for nodes
			element.stream(connectionBase)
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.forEach(node -> {
					for (Connection inboundConnection: node.getIncomingConnections()) {
						if (connectionRoles.containsKey(inboundConnection)) {
							Node candidate = inboundConnection.getSource();
							if (candidate != null && !node.equals(candidate) && !isSemanticAncestor(node, candidate, new HashSet<>())) {
								semanticParents.put(node, candidate);
								break;
							}
						}
					}					
				});
			
			// Rearranging actions for connections with role
			for (Connection connection: element.stream(connectionBase).filter(Connection.class::isInstance).map(Connection.class::cast).collect(Collectors.toList())) {
	            EReference connectionRole = getConnectionRole(connection);
	            if (connectionRole != null) {
		            Node connectionSource = connection.getSource();
		            if (connectionSource != null) {
			            Element toResolve = connectionSource;
			            if (isPageElement(connectionSource)) {
			            	String rootActionFlag = connectionSource.getModel().getRoot().getProperty(getRootActionFlagProperty());
			            	if (Util.isBlank(rootActionFlag) || !"false".equals(rootActionFlag)) {
			            		toResolve = connectionSource.getModel().getPage();
			            	}
			            }
			            
			            Optional<Action> sourceAction = resolver.apply(toResolve::equals).values().stream().flatMap(Collection::stream).findFirst();
			            if (sourceAction.isPresent()) {
			                Node connectionTarget = connection.getTarget();
			                if (connectionTarget != null && !connectionTarget.equals(connectionSource) && connectionSource.equals(getSemanticParent(connectionTarget))) {
			        			Comparator<Element> childComparator = loadChildComparator(connectionSource, getDefaultSort());
			        			List<Action> childActions = ((List<Action>) sourceAction.get().eGet(connectionRole));
								for (Action targetAction: resolver.apply(connectionTarget::equals).values().stream().flatMap(Collection::stream).collect(Collectors.toList())) {
									Object targetActionElementAdapter = EcoreUtil.getRegisteredAdapter(targetAction, ElementAdapter.class);					
									if (childComparator == null || targetActionElementAdapter == null || childActions.isEmpty()) {
										childActions.add(targetAction);
									} else {
										Element targetElement = ((ElementAdapter) targetActionElementAdapter).getElement();
										ListIterator<Action> listIterator = childActions.listIterator();
										while (listIterator.hasNext()) {
											Action next = listIterator.next();
											Object nextElementAdapter = EcoreUtil.getRegisteredAdapter(next, ElementAdapter.class);					
											if (nextElementAdapter instanceof ElementAdapter) {
												 Element nextElement = ((ElementAdapter) nextElementAdapter).getElement();
												 if (childComparator.compare(targetElement, nextElement) < 0) {
													 listIterator.previous(); // Going back so it target is inserted before next.
													 break;
												 }
											}
										}
										listIterator.add(targetAction);
									}
				                }
			                }
			            }
		            }
	            }				
			}
			
			// Rearranging actions for linked pages
			for (ModelElement modelElement: element.stream(connectionBase).filter(ModelElement.class::isInstance).map(ModelElement.class::cast).filter(ModelElement::isPageLink).collect(Collectors.toList())) {
	            Optional<Action> linkingElementAction = resolver.apply(modelElement::equals).values().stream().flatMap(Collection::stream).findFirst();
	            if (linkingElementAction.isPresent()) {
	            	Page linkedPage = modelElement.getLinkedPage();
	            	Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, linkedPage, childEntries.get(linkedPage).getChildEntries());
		            Optional<Action> linkedElementAction = resolver.apply(pageElementEntry.getKey()::equals).values().stream().flatMap(Collection::stream).findFirst();
		            if (linkedElementAction.isPresent()) {
		            	linkingElementAction.get().getAnonymous().addAll(linkedElementAction.get().getAnonymous());
		            	linkingElementAction.get().getChildren().addAll(linkedElementAction.get().getChildren());
		            	linkingElementAction.get().getNavigation().addAll(linkedElementAction.get().getNavigation());
		            	linkingElementAction.get().getSections().addAll(linkedElementAction.get().getSections());
		            }
	            }
			}
						
			// Adding URI resolver adapters
			for (EObject root: resource.getContents()) {
				if (root instanceof Action) {
					addURIResolverAdapters((Action) root);
				}
			}
		} else if (element instanceof Page) {
			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, (Page) element, childEntries);

			for (Action action: semanticElement.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(pageElementEntry.getKey(), a)).collect(Collectors.toList())) {
				// Embedded diagram
				String embeddedDiagram = generateEmbeddedDiagram(element, action, resolver);
				if (isEmbedDiagram(resource, element, childEntries)) {															
					addContent(action, embeddedDiagram);						
				}

				// TOC
				StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: pageElementEntry.getValue().entrySet()) { 
					tocBuilder.append(generateTableOfContents(action, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
				}					
				tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator());
				
				if (isGenerateTableOfContents(resource, element, childEntries)) {
					addContent(action, tocBuilder.toString());
				}
				
				EObject doc = getDocumentation(resource, element, embeddedDiagram, childEntries, tocBuilder.toString());
				if (doc == null) {
					String rootTooltip = pageElementEntry.getKey().getTooltip();
					if (!Util.isBlank(rootTooltip)) {
						addContent(action, rootTooltip);
					}
				} else {
					action.getContent().add(doc);
				}					
			}			
		} else if (element instanceof ModelElement) {
			for (Action action: semanticElement.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(element, a)).collect(Collectors.toList())) {
				if (element instanceof Connection) {
					// Source and target
					Table table = BootstrapFactory.eINSTANCE.createTable();
					table.getAttributes().put("style", createText("width:auto"));
					
					TableRow sourceRow = BootstrapFactory.eINSTANCE.createTableRow();
					table.getRows().add(sourceRow);
					
					TableCell sourceHeader = BootstrapFactory.eINSTANCE.createTableCell();
					sourceHeader.setHeader(true);
					sourceRow.getCells().add(sourceHeader);
					sourceHeader.getContent().add(createText("Source"));
										
					TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
					sourceRow.getCells().add(sourceCell);
					
					Node source = ((Connection) element).getSource();
					String sourceLabel = getModelElementLabel(source);
					if (Util.isBlank(sourceLabel)) {
						sourceLabel = "(unlabeled)";
					} 
					String sourceLink = getModelElementLink(source, action, resolver);
					if (sourceLink == null) {
						sourceCell.getContent().add(createText(sourceLabel));						
					} else {
						sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
					}
					
					TableRow targetRow = BootstrapFactory.eINSTANCE.createTableRow();
					table.getRows().add(targetRow);
					
					TableCell targetHeader = BootstrapFactory.eINSTANCE.createTableCell();
					targetHeader.setHeader(true);
					targetRow.getCells().add(targetHeader);
					targetHeader.getContent().add(createText("Target"));
										
					TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
					targetRow.getCells().add(targetCell);
					
					Node target = ((Connection) element).getTarget();
					String targetLabel = getModelElementLabel(target);
					if (Util.isBlank(targetLabel)) {
						targetLabel = "(unlabeled)";
					} 
					String targetLink = getModelElementLink(target, action, resolver);
					if (targetLink == null) {
						targetCell.getContent().add(createText(targetLabel));						
					} else {
						targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
					}					

					action.getContent().add(table);
				} else if (element instanceof Node) {
					Node node = (Node) element;
					EReference connectionsActionContainmentReference = getConnectionsActionContainmentReference(node);
					if (connectionsActionContainmentReference != null) {
						List<Connection> inboundConnections = node.getIncomingConnections().stream().filter(c -> getConnectionRole(c) == null).collect(Collectors.toList());
						if (!inboundConnections.isEmpty()) {
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
							
							for (Connection inboundConnection: inboundConnections) { // TODO - sorting
								TableRow connectionRow = BootstrapFactory.eINSTANCE.createTableRow();
								table.getRows().add(connectionRow);
													
								TableCell labelCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(labelCell);
								String connectionLabel = getModelElementLabel(inboundConnection);
								if (Util.isBlank(connectionLabel) && hasDocumentation(resource, inboundConnection, childEntries)) {
									connectionLabel = "(unlabeled)";
								}
								if (!Util.isBlank(connectionLabel)) {
									String connectionLink = getModelElementLink(inboundConnection, action, resolver);
									if (connectionLink == null) {
										labelCell.getContent().add(createText(connectionLabel));						
									} else {
										labelCell.getContent().add(createText("<a href=\"" + connectionLink + "\">" + connectionLabel + "</a>"));												
									}
								}
		
								TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(sourceCell);

								Node source = inboundConnection.getSource();
								String sourceLabel = getModelElementLabel(source);
								if (Util.isBlank(sourceLabel)) {
									sourceLabel = "(unlabeled)";
								} 
								String sourceLink = getModelElementLink(source, action, resolver);
								if (sourceLink == null) {
									sourceCell.getContent().add(createText(sourceLabel));						
								} else {
									sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
								}
		
								TableCell tooltipCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(tooltipCell);
								String connectionTooltip = inboundConnection.getTooltip();
								if (!Util.isBlank(connectionTooltip)) {
									tooltipCell.getContent().add(createText(connectionTooltip));
								}								
							}							
							
							Action inboundConnectionsAction = AppFactory.eINSTANCE.createAction();
							inboundConnectionsAction.setText("Inbound connections");
							if (connectionsActionContainmentReference == AppPackage.Literals.ACTION__SECTIONS) {
								inboundConnectionsAction.setName("inbound-connections");
							} else {
								inboundConnectionsAction.setLocation("inbound-connections.html");								
							}
							inboundConnectionsAction.getContent().add(table);
							((Collection<Action>) action.eGet(connectionsActionContainmentReference)).add(inboundConnectionsAction);
						}
						
						List<Connection> outboundConnections = node.getOutgoingConnections().stream().filter(c -> getConnectionRole(c) == null).collect(Collectors.toList());
						if (!outboundConnections.isEmpty()) {
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
							
							for (Connection outboundConnection: outboundConnections) { // TODO - sorting
								TableRow connectionRow = BootstrapFactory.eINSTANCE.createTableRow();
								table.getRows().add(connectionRow);
													
								TableCell labelCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(labelCell);
								String connectionLabel = getModelElementLabel(outboundConnection);
								if (Util.isBlank(connectionLabel) && hasDocumentation(resource, outboundConnection, childEntries)) {
									connectionLabel = "(unlabeled)";
								}
								if (!Util.isBlank(connectionLabel)) {
									String connectionLink = getModelElementLink(outboundConnection, action, resolver);
									if (connectionLink == null) {
										labelCell.getContent().add(createText(connectionLabel));						
									} else {
										labelCell.getContent().add(createText("<a href=\"" + connectionLink + "\">" + connectionLabel + "</a>"));												
									}
								}
		
								TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(targetCell);

								Node target = outboundConnection.getTarget();
								String targetLabel = getModelElementLabel(target);
								if (Util.isBlank(targetLabel)) {
									targetLabel = "(unlabeled)";
								} 
								String targetLink = getModelElementLink(target, action, resolver);
								if (targetLink == null) {
									targetCell.getContent().add(createText(targetLabel));						
								} else {
									targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
								}
		
								TableCell tooltipCell = BootstrapFactory.eINSTANCE.createTableCell();
								connectionRow.getCells().add(tooltipCell);
								String connectionTooltip = outboundConnection.getTooltip();
								if (!Util.isBlank(connectionTooltip)) {
									tooltipCell.getContent().add(createText(connectionTooltip));
								}								
							}							
							
							Action outboundConnectionsAction = AppFactory.eINSTANCE.createAction();
							outboundConnectionsAction.setText("Outbound connections");
							if (connectionsActionContainmentReference == AppPackage.Literals.ACTION__SECTIONS) {							
								outboundConnectionsAction.setName("outbound-connections");
							} else {
								outboundConnectionsAction.setLocation("outbound-connections.html");								
							}
							outboundConnectionsAction.getContent().add(table);
							((Collection<Action>) action.eGet(connectionsActionContainmentReference)).add(outboundConnectionsAction);
						}
					}
				}
				
				// Embedded diagram
				String embeddedDiagram = null;
				
				Page linkedPage = ((ModelElement) element).getLinkedPage();
				if (linkedPage != null) {
					embeddedDiagram = generateEmbeddedDiagram(linkedPage, action, resolver);
					if (isEmbedDiagram(resource, element, childEntries)) {															
						addContent(action, embeddedDiagram);						
					}
				}								
				
				StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) { 						
					Element child = childEntry.getKey();
					if (child instanceof ModelElement) {
						tocBuilder.append(generateTableOfContents(action, (ModelElement) child, childEntry.getValue().getChildEntries(), resolver));
					}
				}								
				tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator());
				
				if (isGenerateTableOfContents(resource, element, childEntries)) {
					addContent(action, tocBuilder.toString());
				}
				
				EObject doc = getDocumentation(resource, element, embeddedDiagram, childEntries, tocBuilder.toString());
				if (doc == null) {
					String tooltip = ((ModelElement) element).getTooltip();
					if (!Util.isBlank(tooltip)) {
						addContent(action, tooltip);
					}
				} else {					
					action.getContent().add(doc);
				}
			}
		}
	}

	/**
	 * @param node
	 * @param candidate
	 * @param hashSet
	 * @return true if there is a semantic parent path from the descendant to the ancestor
	 */
	protected boolean isSemanticAncestor(ModelElement ancestor, ModelElement descendant, HashSet<ModelElement> traversed) {
		if (descendant == null || ancestor == null) {
			return false;
		}
		if (traversed.add(descendant)) {
			if (descendant.equals(ancestor)) {
				return true; // loop
			}
			ModelElement semanticParent = semanticParents.get(descendant);
			if (semanticParent == null) {
				return false;
			}
			if (semanticParent.equals(ancestor)) {
				return true;
			}
			return isSemanticAncestor(ancestor, semanticParent, traversed);
		}
		return false;
	}
	
	/**
	 * Creates a new action using factory. Override to, say, use a prototype action.
	 * @param resource
	 * @param element
	 * @return
	 */
	protected Action createAction(Resource resource, Element element) {
		if (element instanceof ModelElement) {
			String actionProperty = ((ModelElement) element).getProperty("action");
			if (!Util.isBlank(actionProperty)) {
				URI actionURI = URI.createURI(actionProperty);
				actionURI = actionURI.resolve(resource.getURI());
				Action prototype = EcoreUtil.copy((Action) resourceSet.getEObject(actionURI, true));
				prototype.setId(UUID.randomUUID().toString());
				prototype.eAdapters().add(createElementAdapter(element));
				return prototype;
			}
		}
		Action action = AppFactory.eINSTANCE.createAction();
		action.eAdapters().add(createElementAdapter(element));		
		return action;
	}
	
	@Override
	protected Map<Element, ElementEntry<Map<EReference, List<Action>>>> getLinkedPageChildMappings(
			Resource resource,
			Page linkedPage,
			ElementEntry<Map<EReference, List<Action>>> linkedPageEntry) {
		
		if (linkedPage == null) {
			return super.getLinkedPageChildMappings(resource, linkedPage, linkedPageEntry);
		}
		
		Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> linkedPageElementEntry = getPageElementEntry(resource, linkedPage, linkedPageEntry.getChildEntries());
		return linkedPageElementEntry.getValue(); // Child entries of the page element.
	}

}
