package org.nasdanika.html.app.viewparts;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.ActionGroup;

/**
 * Uses {@link ActionGroup} in the navigation panel if navigation panel actions do not have children and 
 * {@link JsTreeNavigationPanelViewPart} otherwise.
 * @author Pavel Vlasov
 *
 */
public class AdaptiveNavigationPanelViewPart implements ViewPart {
	
	protected List<? extends Action> navigationPanelActions;
	protected Action activeAction;
	protected ViewPart delegate;

	public AdaptiveNavigationPanelViewPart(List<? extends Action> navigationPanelActions, Action activeAction) {
		this.navigationPanelActions = navigationPanelActions;
		this.activeAction = activeAction;
		delegate = navigationPanelActions.stream().mapToInt(a -> a.getNavigationChildren().size()).sum() == 0 ? createFlatNavigationPanelViewPart(navigationPanelActions, activeAction) : createHierarchicalNavigationPanelViewPart(navigationPanelActions, activeAction);
	}

	protected ViewPart createHierarchicalNavigationPanelViewPart(
			List<? extends Action> navigationPanelActions,
			Action activeAction) {
		return new JsTreeNavigationPanelViewPart(navigationPanelActions, activeAction);
	}

	protected ViewPart createFlatNavigationPanelViewPart(
			List<? extends Action> navigationPanelActions,
			Action activeAction) {
		return new ActionGroupNavigationPanelViewPart(navigationPanelActions, activeAction);
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return delegate.generate(viewGenerator, progressMonitor);
	}

}
