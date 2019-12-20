package org.nasdanika.html.app.viewparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
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
public class JsTreeNavigationPanelViewPart implements ViewPart {
	
	protected List<? extends Action> rootActions;
	protected Action activeAction;
	private String treeId;

	public JsTreeNavigationPanelViewPart(List<? extends Action> rootActions, Action activeAction) {
		this.rootActions = rootActions;
		this.activeAction = activeAction;
		
		// Computing tree ID. If all navigation actions have the same parent or the same category then id is the parent/category id. Otherwise it is a base64 digest of action ID's.
		Set<Object> parentIDs = new TreeSet<>();
		Set<Object> categoryIDs = new TreeSet<>();
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
				idBuilder.append(pid);
			}
			if (!parentIDs.isEmpty()) {
				idBuilder.append("-");
			}
			for (Object cid: categoryIDs) {
				idBuilder.append("-");
				idBuilder.append(cid);
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
			if (category == null || Util.isBlank(category.getText())) {
				for (Action ca: categoryActions) {
					JsTreeNode jsTreeNode = viewGenerator.jsTreeNode(ca, false);
					jsTreeNode.selected(Util.equalOrInPath(activeAction, ca) && ca.getNavigationChildren().isEmpty());
					roots.add(jsTreeNode);
				}				
			} else {
				JsTreeNode categoryNode = viewGenerator.jsTreeNode(category);
				roots.add(categoryNode);
				for (Action ca: categoryActions) {
					JsTreeNode jsTreeNode = viewGenerator.jsTreeNode(ca, false);
					jsTreeNode.selected(Util.equalOrInPath(activeAction, ca) && ca.getNavigationChildren().isEmpty());
					categoryNode.children().add(jsTreeNode);
				}				
			}			
		}
		JSONObject jsTree = jsTreeFactory.buildJsTree(roots);
		configureJsTree(jsTree);
//		System.out.println("----");
//		System.out.println(jsTree.toString(4));
		// TODO - context menus
		Tag script = jsTreeFactory.bind(container, jsTree);
		return htmlFactory.fragment(container, script);
	}
	
	/**
	 * Override to configure jsTree, e.g. add search or state plug-ins.
	 * This implementation adds state plugin.
	 * @param jsTree
	 */
	protected void configureJsTree(JSONObject jsTree) {
		jsTree.put("plugins", Collections.singletonList("state"));		
		jsTree.put("state", Collections.singletonMap("key", treeId));
	}

}
