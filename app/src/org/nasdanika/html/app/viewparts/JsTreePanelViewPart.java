package org.nasdanika.html.app.viewparts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.json.JSONObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;

/**
 * Uses JsTree in the navigation panel.
 * Configuration options - ajax tree, ajax context menus. Plugins, e.g. search, sort.
 * TODO - context menus.
 * @author Pavel Vlasov
 *
 */
public class JsTreePanelViewPart implements ViewPart {
	
	protected List<? extends Action> rootActions;
	protected Action activeAction;
	private String treeId;
	private boolean categorize;
	private String role;

	public JsTreePanelViewPart(List<? extends Action> rootActions, Action activeAction, String role, boolean categorize) {
		this.rootActions = rootActions;
		this.activeAction = activeAction;
		this.role = role;
		this.categorize = categorize;
		
		// Computing tree ID. If all navigation actions have the same parent or the same category then id is the parent/category id. Otherwise it is a base64 digest of action ID's.
		Set<Object> parentIDs = new HashSet<>();
		Set<Object> categoryIDs = new HashSet<>();
		for (Action npa: rootActions) {
			Action parent = npa.getParent();
			if (parent != null && parent.getId() != null) {
				parentIDs.add(parent.getId());
			}
			Label category = npa.getCategory();
			if (category != null && category.getId() != null) {
				categoryIDs.add(category.getId());
			}			
		}	
		StringBuilder idBuilder = new StringBuilder("nsd-navigation-tree");
		if (parentIDs.isEmpty() && categoryIDs.isEmpty()) {
			idBuilder.append("-").append(UUID.randomUUID()); // Random
		} else {
			for (Object pid: parentIDs) {
				idBuilder.append("-");
				idBuilder.append(pid instanceof CharSequence ? pid : pid.hashCode());
			}
			if (!parentIDs.isEmpty()) {
				idBuilder.append("-");
			}
			for (Object cid: categoryIDs) {
				idBuilder.append("-");
				idBuilder.append(cid instanceof CharSequence ? cid : cid.hashCode());
			}
		}
		treeId = idBuilder.toString();
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
		Tag container = htmlFactory.div().id(treeId);
		JsTreeFactory jsTreeFactory = viewGenerator.get(JsTreeFactory.class);
		List<JsTreeNode> roots = new ArrayList<>();
		
		// Group by category
		for (Entry<Label, ?> group: Util.groupByCategory(rootActions)) {			
			Label category = group.getKey();
			@SuppressWarnings("unchecked")
			List<Action> categoryActions = (List<Action>) group.getValue();
			if (!categorize || category == null || Util.isBlank(category.getText())) {
				for (Action ca: categoryActions) {
					JsTreeNode jsTreeNode = viewGenerator.jsTreeNode(ca, false, this::filterNode);
					if (jsTreeNode != null) {
						roots.add(jsTreeNode);
					}
				}				
			} else {
				JsTreeNode categoryNode = viewGenerator.jsTreeNode(category);
				roots.add(categoryNode);
				for (Action ca: categoryActions) {
					JsTreeNode jsTreeNode = viewGenerator.jsTreeNode(ca, false, this::filterNode);
					if (jsTreeNode != null) {
						categoryNode.children().add(jsTreeNode);
					}
				}				
			}			
		}
		JSONObject jsTree = jsTreeFactory.buildJsTree(roots);
		configureJsTree(jsTree);
//		System.out.println("----");
//		System.out.println(jsTree.toString(4));
		// TODO - context menus - extract from 
		// https://github.com/Nasdanika/server/blob/0f641d3c60bea3acff19bc5cd90ce8060cc48556/org.nasdanika.cdo.web/src/org/nasdanika/cdo/web/routes/app/Renderer.java
		// https://github.com/Nasdanika/server/blob/0f641d3c60bea3acff19bc5cd90ce8060cc48556/org.nasdanika.cdo.web/src/org/nasdanika/cdo/web/routes/app/jstree-initializer.js
		Tag script = jsTreeFactory.bind(container, jsTree, getTreeFilter());
		return htmlFactory.fragment(container, script);
	}
	
	/**
	 * Override to configure jsTree, e.g. add search or state plug-ins.
	 * This implementation adds state plugin.
	 * @param jsTree
	 */
	protected void configureJsTree(JSONObject jsTree) {
		jsTree.put("plugins", Arrays.asList("state", "types")); 		
		jsTree.put("state", Collections.singletonMap("key", treeId));
		jsTree.put("types", Collections.singletonMap("leaf", Collections.singletonMap("icon", "jstree-file"))); // File leaf icon.
	}
	
	/**
	 * Filters/configures node. This implementation sets selection for active node and type to "leaf" for nodes without children.
	 * @param action
	 * @param node
	 * @return
	 */
	protected JsTreeNode filterNode(Action action, JsTreeNode node) {
		if (action.isInRole(role)) {
			if (action == activeAction) {
				node.selected();
			} else {
				node.selected(Util.equalOrInPath(activeAction, action) && action.getChildrenByRole(role).isEmpty());
			}
			if (node.children().isEmpty()) {
				node.attribute("type", "leaf"); // Set node type to leaf if there are no children.
			}
			return node;
		}
		return null;
	}
	
	/**
	 * @return Script to filter/configure the tree. This implementation adds a filter function to the state plugin which deletes selection.
	 */
	protected String getTreeFilter() {
		return "tree.state.filter = function(state) { delete state.core.selected; return state; };";
	}

}
