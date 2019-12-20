package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Size;

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
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		Fragment ret = viewGenerator.get(HTMLFactory.class).fragment();
		for (Entry<Label, ?> categoryGroup: Util.groupByCategory(navigationPanelActions)) {
			ActionGroup actionGroup = viewGenerator.get(BootstrapFactory.class).actionGroup(true);			
			for (Action child: (List<Action>) categoryGroup.getValue()) {
				viewGenerator.add(actionGroup, child, Util.equalOrInPath(activeAction, child));
			}
			if (categoryGroup.getKey() == null) {
				ret.content(actionGroup);
			} else {
				Card categoryCard = viewGenerator.get(BootstrapFactory.class).card();
				categoryCard.margin().top(Breakpoint.DEFAULT, Size.S1);
				Label category = categoryGroup.getKey();
				categoryCard.border(category.getColor());
				categoryCard.getHeader().background(category.getColor());
				viewGenerator.label(category, categoryCard.getHeader().toHTMLElement());
				categoryCard.toHTMLElement().content(actionGroup);
				ret.content(categoryCard);
			}
		}
		return ret;
	}

}
