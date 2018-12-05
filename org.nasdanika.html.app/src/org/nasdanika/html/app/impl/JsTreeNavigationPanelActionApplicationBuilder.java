package org.nasdanika.html.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;

/**
 * Uses JsTree in the navigation panel.
 * @author Pavel Vlasov
 *
 */
public class JsTreeNavigationPanelActionApplicationBuilder extends ActionApplicationBuilder {

	public JsTreeNavigationPanelActionApplicationBuilder(Action activeAction) {
		super(activeAction);
	}

	public JsTreeNavigationPanelActionApplicationBuilder(Action rootAction, Action principalAction,	Action activeAction) {
		super(rootAction, principalAction, activeAction);
	}

	public JsTreeNavigationPanelActionApplicationBuilder(
			Action rootAction, 
			Action principalAction,
			List<? extends Action> navigationPanelActions, 
			Action activeAction) {
		super(rootAction, principalAction, navigationPanelActions, activeAction);
	}
	
	@Override
	protected Object generateNavigationPanel(ViewGenerator viewGenerator, Object result) {
		String treeId = "nsd-navigation-tree";
		HTMLFactory htmlFactory = viewGenerator.getHTMLFactory();
		Tag container = htmlFactory.div().id(treeId);
		JsTreeFactory jsTreeFactory = viewGenerator.getJsTreeFactory();
		List<JsTreeNode> roots = new ArrayList<>();
		for (Action ca: principalAction.getChildren()) {
			JsTreeNode jsTreeNode = viewGenerator.jsTreeNode(ca, false);
			jsTreeNode.selected(ca == activeAction);
			roots.add(jsTreeNode);
		}
		JSONObject jsTree = jsTreeFactory.buildJsTree(roots);
		// TODO - context menus, activation
		Tag script = jsTreeFactory.bind(container, jsTree);
		return htmlFactory.fragment(container, script);
	}

}
