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
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

/**
 * Uses {@link ACTION_GROUP} in the navigation panel if navigation panel actions do not have children and 
 * {@link JsTreeNavigationPanelViewPart} otherwise.
 * @author Pavel Vlasov
 *
 */
public class AdaptiveNavigationPanelViewPart implements ViewPart {
	
	public enum Style {
		
		/**
		 * TREE if the tree depth is higher than one, CARD otherwise.
		 */
		AUTO,
		
		/**
		 * Tree even if the depth is one.
		 */
		TREE,
		
		/**
		 * Top level elements are cards - category cards and action cards. 
		 * Cards contents depends on the tree depth - list if one, tree if more than one.  
		 */
		CARDS
	}
	
	protected List<Action> navigationPanelActions;
	protected Action activeAction;

	public AdaptiveNavigationPanelViewPart(List<Action> navigationPanelActions, Action activeAction) {
		this.navigationPanelActions = navigationPanelActions;
		this.activeAction = activeAction;		
	}
	
	/**
	 * Creates a view part depending on the nesting depth.
	 * @param actions
	 * @return
	 */
	protected ViewPart createActionsViewPart(List<Action> actions, boolean categorize) {
		if (actions.stream().mapToInt(a -> a.getNavigationChildren().size()).sum() == 0) {		
			return new ViewPart() {

				@Override
				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
					ActionGroup actionGroup = viewGenerator.get(BootstrapFactory.class).actionGroup(true);			
					for (Action child: (List<Action>) actions) {
						viewGenerator.add(actionGroup, child, Util.equalOrInPath(activeAction, child));
					}
					return actionGroup;
				}
				
			};
		}

		return new JsTreeNavigationPanelViewPart(actions, activeAction, categorize);
	}
	
	protected Card createCategoryCard(Label category, ViewGenerator viewGenerator) {
		Card categoryCard = viewGenerator.get(BootstrapFactory.class).card();
		categoryCard.margin().top(Breakpoint.DEFAULT, Size.S1);

		if (category != null) {
			if (category.getColor() != null) {
				categoryCard.border(category.getColor());
				categoryCard.getHeader().background(category.getColor());
			}
			viewGenerator.label(category, categoryCard.getHeader().toHTMLElement());
		}
		
		return categoryCard;
	}
	
	// TODO - createActionCard if top level is forced to be cards

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		List<Entry<Label, List<Action>>> categoryGroups = Util.groupByCategory(navigationPanelActions);
		if (categoryGroups.size() == 0) {
			return null;
		}
		
		// Tree "in the raw"
		if (categoryGroups.size() == 1) {
			for (Entry<Label, List<Action>> categoryGroup: categoryGroups) {
				if (categoryGroup.getKey() == null) {
					return createActionsViewPart(categoryGroup.getValue(), true).generate(viewGenerator, progressMonitor);
				}
			}
		}
						
		// Put things in cards
		Fragment ret = viewGenerator.get(HTMLFactory.class).fragment();
		for (Entry<Label, List<Action>> categoryGroup: categoryGroups) {
			Card card = createCategoryCard(categoryGroup.getKey(), viewGenerator);
			Object actionsView = createActionsViewPart(categoryGroup.getValue(), false).generate(viewGenerator, progressMonitor);
			if (actionsView instanceof ActionGroup) {
				card.toHTMLElement().content(actionsView);
			} else {
				TagBootstrapElement cardBody = card.getBody();
				cardBody.toHTMLElement().content(actionsView);
				cardBody.padding().all(Breakpoint.DEFAULT, Size.S1);
			}
			ret.content(card);
		}
		return ret;
	}

}
