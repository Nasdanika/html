package org.nasdanika.html.app.impl;

import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.Card;

/**
 * Uses {@link ActionGroup} in the navigation panel.
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

	@SuppressWarnings("unchecked")
	@Override
	public Object generate(ViewGenerator viewGenerator) {
		Fragment ret = viewGenerator.getHTMLFactory().fragment();
		for (Entry<Label, ?> categoryGroup: Util.groupByCategory(navigationPanelActions)) {
			ActionGroup actionGroup = viewGenerator.getBootstrapFactory().actionGroup(true);			
			for (Action child: (List<Action>) categoryGroup.getValue()) {
				viewGenerator.add(actionGroup, child, Util.equalOrInPath(activeAction, child));
			}
			if (categoryGroup.getKey() == null) {
				ret.content(actionGroup);
			} else {
				Card categoryCard = viewGenerator.getBootstrapFactory().card();
				categoryCard.margin().top(1);
				Label category = categoryGroup.getKey();
				categoryCard.border(category.getColor());
				viewGenerator.label(category, categoryCard.getTitle().toHTMLElement());
				categoryCard.getBody().toHTMLElement().content(actionGroup);
				ret.content(categoryCard);
			}
		}
		return ret;
	}

}
