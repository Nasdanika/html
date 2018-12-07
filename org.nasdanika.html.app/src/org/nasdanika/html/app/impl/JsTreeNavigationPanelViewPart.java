package org.nasdanika.html.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
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
	
	protected List<? extends Action> navigationPanelActions;
	protected Action activeAction;

	public JsTreeNavigationPanelViewPart(List<? extends Action> navigationPanelActions, Action activeAction) {
		this.navigationPanelActions = navigationPanelActions;
		this.activeAction = activeAction;
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator) {
		String treeId = "nsd-navigation-tree";
		HTMLFactory htmlFactory = viewGenerator.getHTMLFactory();
		Tag container = htmlFactory.div().id(treeId);
		JsTreeFactory jsTreeFactory = viewGenerator.getJsTreeFactory();
		List<JsTreeNode> roots = new ArrayList<>();
		for (Action ca: navigationPanelActions) {
			JsTreeNode jsTreeNode = viewGenerator.jsTreeNode(ca, false);
			jsTreeNode.selected(Util.equalOrInPath(activeAction, ca) && ca.getChildren().isEmpty());
			roots.add(jsTreeNode);
		}
		JSONObject jsTree = jsTreeFactory.buildJsTree(roots);
		// TODO - context menus
		Tag script = jsTreeFactory.bind(container, jsTree);
		return htmlFactory.fragment(container, script);
	}

}
