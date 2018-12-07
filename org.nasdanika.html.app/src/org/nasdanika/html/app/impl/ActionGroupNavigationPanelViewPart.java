package org.nasdanika.html.app.impl;

import java.util.List;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.ActionGroup;

/**
 * Uses {@link ActionGroup} in the navigation panel.
 * Configuration options - ajax tree, ajax context menus. Plugins, e.g. search, sort.
 * TODO - context menus.
 * @author Pavel Vlasov
 *
 */
public class ActionGroupNavigationPanelViewPart implements ViewPart {
	
	protected List<? extends Action> navigationPanelActions;
	protected Action activeAction;

	public ActionGroupNavigationPanelViewPart(List<? extends Action> navigationPanelActions, Action activeAction) {
		this.navigationPanelActions = navigationPanelActions;
		this.activeAction = activeAction;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator) {
		ActionGroup actionGroup = viewGenerator.getBootstrapFactory().actionGroup(true);
		for (Action child: navigationPanelActions) {
			viewGenerator.add(actionGroup, child, Util.equalOrInPath(activeAction, child));
		}
		return actionGroup;
	}

}
