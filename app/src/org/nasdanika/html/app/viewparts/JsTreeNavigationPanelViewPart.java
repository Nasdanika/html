package org.nasdanika.html.app.viewparts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

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
	
	protected List<? extends Action> navigationPanelActions;
	protected Action activeAction;

	public JsTreeNavigationPanelViewPart(List<? extends Action> navigationPanelActions, Action activeAction) {
		this.navigationPanelActions = navigationPanelActions;
		this.activeAction = activeAction;
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		String treeId = "nsd-navigation-tree";
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
		Tag container = htmlFactory.div().id(treeId);
		JsTreeFactory jsTreeFactory = viewGenerator.get(JsTreeFactory.class);
		List<JsTreeNode> roots = new ArrayList<>();
		// Group by category
		for (Entry<Label, ?> group: Util.groupByCategory(navigationPanelActions)) {			
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
		// TODO - context menus
		Tag script = jsTreeFactory.bind(container, jsTree);
		return htmlFactory.fragment(container, script);
	}

}
