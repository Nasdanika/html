package org.nasdanika.html.model.app.gen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.json.JSONObject;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputType;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.TagBootstrapElement;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.NavigationPanelStyle;

public class NavigationPanelConsumerFactoryAdapter extends PagePartConsumerFactoryAdapter<NavigationPanel> {

	public static final String CLEAR_STATE_FILTER = "tree.state.filter = function(state) { delete state.core.selected; return state; };";
	private static final String SEARCH_FILTER = " tree.search.search_callback = function(searchStr, node) { if (typeof window.nsdJsTreeSearchCallback === 'function') return window.nsdJsTreeSearchCallback(searchStr, node); var sf = new $.vakata.search(searchStr, true, { caseSensitive : false, fuzzy : false }); return sf.search(node.text).isMatch; };";
	private static final String SEARCH_INPUT_SUFFIX = "_searchInput";
	private static final String SEARCH_INPUT_TOOLTIP = "Full-text search. You can use wildcards, e.g. 'foo*' or 'f*o'; title or content fields, e.g. 'title:foo* bar'; boosts, e.g. 'foo^10 bar'; fuzzy matches, e.g. 'foo~1'; and term presence, e.g. '+foo bar -baz'";

	protected NavigationPanelConsumerFactoryAdapter(NavigationPanel navigationPanel, AdapterFactory adapterFactory) {
		super(navigationPanel, adapterFactory);
	}
	
	private Function<BiSupplier<HTMLElement<?>, List<JsTreeNode>>, HTMLElement<?>> createTreeFunction(NavigationPanelStyle effectiveStyle, Context context) {
		return new Function<BiSupplier<HTMLElement<?>, List<JsTreeNode>>, HTMLElement<?>>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "JsTree";
			}
			
			@Override
			public HTMLElement<?> execute(BiSupplier<HTMLElement<?>, List<JsTreeNode>> input, ProgressMonitor progressMonitor) {
				Tag ret = (Tag) input.getFirst();
				
				Tag panel;
				if (getTarget().isCollapsible()) {
					Tag triggerDiv = ret.getFactory().div().addClass("nsd-collapse-panel", "bg-light", "text-center");
					ret.content(triggerDiv);
					HTMLFactory htmlFactory = ret.getFactory();
					panel = htmlFactory.div();
					panel.id("nsd-nav-panel-" + getTarget().getUuid() + "-collapsible");
					
					Tag iTag = htmlFactory.tag(TagName.i).addClass("fa").attribute("aria-hidden", "true");

					Tag collapsibleTrigger = htmlFactory.span(iTag)
							.addClass("nsd-collapsible-trigger")
							.attribute("data-toggle", "collapse")
							.attribute("data-target", "#" + panel.getId())
							.attribute("aria-expanded", "true")
							.attribute("aria-controls", panel.getId())
							.attribute("title", "Toggle panel")
							.id(panel.getId()+"-trigger");
					
					triggerDiv.content(collapsibleTrigger);
					panel.addClass("nsd-collapsible", "collapse", "show");
					panel.style("clear", "right");

					ret.content(panel);
				} else {
					panel = ret;
				}
				
				JsTreeFactory jsTreeFactory = context.get(JsTreeFactory.class, JsTreeFactory.INSTANCE);
				HTMLFactory htmlFactory = jsTreeFactory.getHTMLFactory();
				String treeId = getTarget().getId();
				if (org.nasdanika.common.Util.isBlank(treeId)) {
					treeId = htmlFactory.nextId();
				}
				
				String searchInputId = null;
				if (effectiveStyle == NavigationPanelStyle.SEARCHABLE_TREE) {
					Input searchInput = htmlFactory.input(InputType.text);
					searchInput.addClass("form-control", "mt-1");
					searchInputId = treeId + SEARCH_INPUT_SUFFIX;
					searchInput.id(searchInputId);
					searchInput.attribute("title", SEARCH_INPUT_TOOLTIP);
					panel.accept(searchInput);
				}
				
				Tag container = htmlFactory.div().id(treeId);
				panel.accept(container);
				
				JSONObject jsTree = jsTreeFactory.buildJsTree(input.getSecond());

				// Configures jsTree. TODO - from the model.
				List<String> plugins = new ArrayList<>();
				plugins.add("state");
				plugins.add("type");
				if (effectiveStyle == NavigationPanelStyle.SEARCHABLE_TREE) {
					plugins.add("search");
					JSONObject searchConfig = new JSONObject();
					searchConfig.put("show_only_matches", true);
					searchConfig.put("show_only_matches_children", true);
					jsTree.put("search", searchConfig);
					
					// TODO - use lunrjs search.js
				}
				
				jsTree.put("plugins", plugins); 		
				jsTree.put("state", Collections.singletonMap("key", treeId));
				jsTree.put("types", Collections.singletonMap("leaf", Collections.singletonMap("icon", "jstree-file"))); // File leaf icon.
				
				// Deletes selection from state
				String filter = CLEAR_STATE_FILTER;
				
				if (effectiveStyle == NavigationPanelStyle.SEARCHABLE_TREE) {
					filter += " " + SEARCH_FILTER;
				}
				
				Tag script = jsTreeFactory.bind(container, jsTree, filter, searchInputId == null ? null : "#" + searchInputId);
				panel.accept(script);
				
				return ret;
			}
						
		};
	}
	
	private Function<BiSupplier<HTMLElement<?>, Map<Class<?>, Object>>, HTMLElement<?>> createCardsFunction(Context context) {
		return new Function<BiSupplier<HTMLElement<?>, Map<Class<?>, Object>>, HTMLElement<?>>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Cards";
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public HTMLElement<?> execute(BiSupplier<HTMLElement<?>, Map<Class<?>, Object>> input, ProgressMonitor progressMonitor) {
				Tag ret = (Tag) input.getFirst();
				Tag panel;
				if (getTarget().isCollapsible()) {
					HTMLFactory htmlFactory = ret.getFactory();
					Tag triggerDiv = htmlFactory.div().addClass("nsd-collapse-panel", "bg-light", "text-center");
					ret.content(triggerDiv);
					panel = htmlFactory.div();
					panel.id("nsd-nav-panel-" + getTarget().getUuid() + "-collapsible");
					
					Tag iTag = htmlFactory.tag(TagName.i).addClass("fa").attribute("aria-hidden", "true");

					Tag collapsibleTrigger = htmlFactory.span(iTag)
							.addClass("nsd-collapsible-trigger")
							.attribute("data-toggle", "collapse")
							.attribute("data-target", "#" + panel.getId())
							.attribute("aria-expanded", "true")
							.attribute("aria-controls", panel.getId())
							.attribute("title", "Toggle panel")							
							.id(panel.getId()+"-trigger");
					
					triggerDiv.content(collapsibleTrigger);
					panel.addClass("nsd-collapsible", "collapse", "show");
					panel.style("clear", "right");

					ret.content(panel);
				} else {
					panel = ret;
				}
				
				List<Object> items = (List<Object>) input.getSecond().get(Object.class);
				
				JsTreeFactory jsTreeFactory = context.get(JsTreeFactory.class, JsTreeFactory.INSTANCE);
				String treeId = getTarget().getId();
				HTMLFactory htmlFactory = jsTreeFactory.getHTMLFactory();
				
				Tag currentActionGroup = null; // Add items without children to an action group
				for (Object item: items) {
					if (item instanceof Tag) {
						Tag itemTag = (Tag) item;
						Object data = itemTag.getData();
						if (data instanceof Label) {
							Label semanticElement = (Label) data; 
							if (semanticElement.getChildren().isEmpty()) {
								if (currentActionGroup == null) {
									currentActionGroup = htmlFactory.div().addClass("list-group", "list-group-flush");
								}
								itemTag.addClass("list-group-item", "list-group-action");
								itemTag.addClassConditional(semanticElement.isActive(), "active");
								itemTag.addClassConditional(semanticElement.isDisabled(), "disabled");
								currentActionGroup.accept(itemTag);
							} else {
								if (currentActionGroup != null) {
									panel.accept(currentActionGroup);
									currentActionGroup = null;
								}
								
								Card card = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE).card();
								panel.accept(card);
								TagBootstrapElement cardHeader = card.getHeader();
								Color color = semanticElement.getColor();
								if (color != null) {
									card.border(color);
									cardHeader.background(color);
								}
								Tag title = (Tag) itemTag.getContent().get(0);
								
								// Remove dropdown classes and attributes
								title
									.removeClass("nav-link", "dropdown-toggle")
									.attribute("role", null)
									.attribute("data-toggle", null);								
								
								if (color != null) {
									title.removeClass("text-" + color.code);
								}
								
								Tag cardHeaderTag = cardHeader.toHTMLElement();
								Tag collapsible;
								// Tree or items
								TagBootstrapElement cardBody = card.getBody();																
								if (depth(semanticElement.getChildren()) == 1) {
									Tag childrenActionGroup = htmlFactory.div().addClass("list-group", "list-group-flush");
									for (Object itemChild: (List<Object>) itemTag.getData(AppPackage.Literals.LABEL__CHILDREN)) {
										if (itemChild instanceof Tag) {
											Tag itemChildTag = (Tag) itemChild;
											Label childSemanticElement = (Label) itemChildTag.getData();
											itemChildTag.addClass("list-group-item", "list-group-action").removeClass("dropdown-item");
											itemChildTag.addClassConditional(childSemanticElement.isActive(), "active");
											itemChildTag.addClassConditional(childSemanticElement.isDisabled(), "disabled");
											childrenActionGroup.accept(itemChildTag);
										} else {
											cardBody.toHTMLElement().accept(itemChild);
										}
									}
									card.toHTMLElement().accept(childrenActionGroup);
									collapsible = childrenActionGroup;
								} else {									
									cardBody.padding().all(Breakpoint.DEFAULT, Size.S1);
									collapsible = cardBody.toHTMLElement();
									List<JsTreeNode> nodes = (List<JsTreeNode>) input.getSecond().get(JsTreeNode.class);
									for (JsTreeNode node: nodes) {
										if (node.getData() == semanticElement) {
											String nodeTreeId;
											if (org.nasdanika.common.Util.isBlank(treeId)) {
												nodeTreeId = "nsd-nav-tree-" + semanticElement.getId();
											} else {
												nodeTreeId = treeId + "-" + semanticElement.getId();
											}
											
											boolean isSearchable = treeSize(node.children()) >= getSearchThreshold();									
											String searchInputId = null;
											if (isSearchable) {
												Input searchInput = htmlFactory.input(InputType.text);
												searchInput.addClass("form-control");
												searchInputId = cardBody.getFactory().getHTMLFactory().nextId() + SEARCH_INPUT_SUFFIX;
												searchInput.id(searchInputId);
												searchInput.attribute("title", SEARCH_INPUT_TOOLTIP);												
												cardBody.toHTMLElement().accept(searchInput);
											}
											
											Tag container = htmlFactory.div().id(nodeTreeId);
											cardBody.toHTMLElement().accept(container);
											
											JSONObject jsTree = jsTreeFactory.buildJsTree(node.children());
							
											// Configures jsTree. TODO - from the model.
											List<String> plugins = new ArrayList<>();
											plugins.add("state");
											plugins.add("type");
											if (isSearchable) {
												plugins.add("search");
												JSONObject searchConfig = new JSONObject();
												searchConfig.put("show_only_matches", true);
												searchConfig.put("show_only_matches_children", true);
												jsTree.put("search", searchConfig);
												
												// TODO - use lunrjs search.js
											}
											
											jsTree.put("plugins", plugins); 		
											jsTree.put("state", Collections.singletonMap("key", treeId));
											jsTree.put("types", Collections.singletonMap("leaf", Collections.singletonMap("icon", "jstree-file"))); // File leaf icon.
											
											// Deletes selection from state
											String filter = CLEAR_STATE_FILTER;
											
											if (isSearchable) {
												filter += " " + SEARCH_FILTER;
											}
											
											Tag script = jsTreeFactory.bind(container, jsTree, filter, searchInputId == null ? null : "#" + searchInputId);
											panel.accept(script);
											break;
										}
									}
								}

								// Collapse button
								if (getTarget().getStyle() == NavigationPanelStyle.COLLAPSIBLE_CARDS) {
									Tag iTag = htmlFactory.tag(TagName.i).addClass("fa").attribute("aria-hidden", "true");
									collapsible.id(semanticElement.getId()+"-collapsible");

									Tag collapsibleTrigger = htmlFactory.span(iTag)
											.addClass("nsd-collapsible-trigger")
											.attribute("data-toggle", "collapse")
											.attribute("data-target", "#" + collapsible.getId())
											.attribute("aria-expanded", "true")
											.attribute("aria-controls", collapsible.getId())
											.attribute("title", "Toggle card")											
											.id(semanticElement.getId()+"-collapsible-trigger");
									
									cardHeaderTag.content(collapsibleTrigger);
									collapsible.addClass("nsd-collapsible", "collapse", "show");
								}
								
								cardHeaderTag.content(title);								
							}
						} else {
							panel.accept(item);
						}						
					} else {
						panel.accept(item);
					}
				}
				if (currentActionGroup != null) {
					panel.accept(currentActionGroup);
				}
				
				return ret;
			}
		};
	}
	
	protected int getSearchThreshold() {
		return getTarget().getJsTreeSearchThreshold(); 
	}
		
	private static int depth(Collection<EObject> items) {
		int ret = 0;
		for (EObject item: items) {
			if (item instanceof Label) {
				ret = Math.max(ret, depth(((Label) item).getChildren()) + 1);
			}
		}
		return ret;
	}
	
	private static int treeSize(Collection<JsTreeNode> nodes) {
		int ret = 0;
		for (JsTreeNode node: nodes) {
			++ret;
			ret += treeSize(node.children());
		}
		return ret;
	}	
	
	private static int size(Collection<EObject> items) {
		int ret = 0;
		for (EObject item: items) {
			++ret;
			if (item instanceof Label) {
				ret += size(((Label) item).getChildren());
			}
		}
		return ret;
	}
	
	/**
	 * Adapts items to {@link JsTreeNode} suppliers.
	 */
	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) {
		NavigationPanel semanticElement = getTarget();
		NavigationPanelStyle style = semanticElement.getStyle();
		
		EList<EObject> items = semanticElement.getItems();
		if (style == NavigationPanelStyle.AUTO) {
			style = depth(items) > 2 ?  size(items) >= getSearchThreshold() ? NavigationPanelStyle.SEARCHABLE_TREE : NavigationPanelStyle.TREE : NavigationPanelStyle.CARDS; 
		}
		
		ListCompoundSupplierFactory<JsTreeNode> nodesFactory = new ListCompoundSupplierFactory<>("Nodes", EObjectAdaptable.adaptToSupplierFactoryNonNull(items, JsTreeNode.class));
		if (style == NavigationPanelStyle.TREE || style == NavigationPanelStyle.SEARCHABLE_TREE) {
			Function<HTMLElement<?>, BiSupplier<HTMLElement<?>, List<JsTreeNode>>> nodesFunction = nodesFactory.<HTMLElement<?>>asFunctionFactory().create(context);
			return super.createConfigureFunction(context).then(nodesFunction).then(createTreeFunction(style, context));
		}
		
		ListCompoundSupplierFactory<Object> tagsFactory = new ListCompoundSupplierFactory<>("Tags", EObjectAdaptable.adaptToSupplierFactoryNonNull(items, Object.class));
		
		MapCompoundSupplierFactory<Class<?>, Object> tagsAndNodesSupplierFactory = new MapCompoundSupplierFactory<>("Tags and nodes");
		tagsAndNodesSupplierFactory.put(JsTreeNode.class, nodesFactory);
		tagsAndNodesSupplierFactory.put(Object.class, tagsFactory);
		Function<HTMLElement<?>, BiSupplier<HTMLElement<?>, Map<Class<?>, Object>>> tagsAndNodesFunction = tagsAndNodesSupplierFactory.<HTMLElement<?>>asFunctionFactory().create(context);
		
		return super.createConfigureFunction(context).then(tagsAndNodesFunction).then(createCardsFunction(context));
	}

}
