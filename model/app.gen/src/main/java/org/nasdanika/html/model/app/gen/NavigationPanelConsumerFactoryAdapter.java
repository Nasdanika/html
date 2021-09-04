package org.nasdanika.html.model.app.gen;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.nasdanika.html.Tag;
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

	protected NavigationPanelConsumerFactoryAdapter(NavigationPanel navigationPanel) {
		super(navigationPanel);
	}
	
	private Function<BiSupplier<HTMLElement<?>, List<JsTreeNode>>, HTMLElement<?>> createTreeFunction(Context context) {
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
			public HTMLElement<?> execute(BiSupplier<HTMLElement<?>, List<JsTreeNode>> input, ProgressMonitor progressMonitor) throws Exception {
				Tag ret = (Tag) input.getFirst();
				
				JsTreeFactory jsTreeFactory = context.get(JsTreeFactory.class, JsTreeFactory.INSTANCE);
				String treeId = getTarget().getId();
				HTMLFactory htmlFactory = jsTreeFactory.getHTMLFactory();
				if (org.nasdanika.common.Util.isBlank(treeId)) {
					treeId = htmlFactory.nextId();
				}
				Tag container = htmlFactory.div().id(treeId);
				ret.accept(container);
				
				JSONObject jsTree = jsTreeFactory.buildJsTree(input.getSecond());

				// Configures jsTree. TODO - from the model.
				jsTree.put("plugins", Arrays.asList("state", "types")); 		
				jsTree.put("state", Collections.singletonMap("key", treeId));
				jsTree.put("types", Collections.singletonMap("leaf", Collections.singletonMap("icon", "jstree-file"))); // File leaf icon.
				
				// Deletes selection from state
				String filter = "tree.state.filter = function(state) { delete state.core.selected; return state; };";
				
				Tag script = jsTreeFactory.bind(container, jsTree, filter);
				ret.accept(script);
				
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
			public HTMLElement<?> execute(BiSupplier<HTMLElement<?>, Map<Class<?>, Object>> input, ProgressMonitor progressMonitor) throws Exception {
				Tag ret = (Tag) input.getFirst();
				
				@SuppressWarnings("unchecked")
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
									ret.accept(currentActionGroup);
									currentActionGroup = null;
								}
								
								Card card = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE).card();
								ret.accept(card);
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
								
								cardHeader.toHTMLElement().content(title);
								
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
								} else {									
									cardBody.padding().all(Breakpoint.DEFAULT, Size.S1);
									for (JsTreeNode node: (List<JsTreeNode>) input.getSecond().get(JsTreeNode.class)) {
										if (node.getData() == semanticElement) {
											String nodeTreeId;
											if (org.nasdanika.common.Util.isBlank(treeId)) {
												nodeTreeId = "nsd-nav-tree-" + semanticElement.getId();
											} else {
												nodeTreeId = treeId + "-" + semanticElement.getId();
											}
											
											Tag container = htmlFactory.div().id(nodeTreeId);
											cardBody.toHTMLElement().accept(container);
											
											JSONObject jsTree = jsTreeFactory.buildJsTree(node.children());
							
											// Configures jsTree. TODO - from the model.
											jsTree.put("plugins", Arrays.asList("state", "types")); 		
											jsTree.put("state", Collections.singletonMap("key", treeId));
											jsTree.put("types", Collections.singletonMap("leaf", Collections.singletonMap("icon", "jstree-file"))); // File leaf icon.
											
											// Deletes selection from state
											String filter = "tree.state.filter = function(state) { delete state.core.selected; return state; };";
											
											Tag script = jsTreeFactory.bind(container, jsTree, filter);
											ret.accept(script);
											break;
										}
									}
								}								
							}
						} else {
							ret.accept(item);
						}						
					} else {
						ret.accept(item);
					}
				}
				if (currentActionGroup != null) {
					ret.accept(currentActionGroup);
				}
				
				return ret;
			}
		};
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
	
	/**
	 * Adapts items to {@link JsTreeNode} suppliers.
	 */
	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) throws Exception {
		NavigationPanel semanticElement = getTarget();
		NavigationPanelStyle style = semanticElement.getStyle();
		
		EList<EObject> items = semanticElement.getItems();
		if (style == NavigationPanelStyle.AUTO) {
			style = depth(items) > 2 ?  NavigationPanelStyle.TREE : NavigationPanelStyle.CARDS; 
		}
		
		ListCompoundSupplierFactory<JsTreeNode> nodesFactory = new ListCompoundSupplierFactory<>("Nodes", EObjectAdaptable.adaptToSupplierFactoryNonNull(items, JsTreeNode.class));
		if (style == NavigationPanelStyle.TREE) {
			Function<HTMLElement<?>, BiSupplier<HTMLElement<?>, List<JsTreeNode>>> nodesFunction = nodesFactory.<HTMLElement<?>>asFunctionFactory().create(context);
			return super.createConfigureFunction(context).then(nodesFunction).then(createTreeFunction(context));
		}
		
		ListCompoundSupplierFactory<Object> tagsFactory = new ListCompoundSupplierFactory<>("Tags", EObjectAdaptable.adaptToSupplierFactoryNonNull(items, Object.class));
		
		MapCompoundSupplierFactory<Class<?>, Object> tagsAndNodesSupplierFactory = new MapCompoundSupplierFactory<>("Tags and nodes");
		tagsAndNodesSupplierFactory.put(JsTreeNode.class, nodesFactory);
		tagsAndNodesSupplierFactory.put(Object.class, tagsFactory);
		Function<HTMLElement<?>, BiSupplier<HTMLElement<?>, Map<Class<?>, Object>>> tagsAndNodesFunction = tagsAndNodesSupplierFactory.<HTMLElement<?>>asFunctionFactory().create(context);
		
		return super.createConfigureFunction(context).then(tagsAndNodesFunction).then(createCardsFunction(context));
	}
	
	
	
	

}
