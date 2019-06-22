package org.nasdanika.html.app.impl;

import java.util.Collections;
import java.util.List;

import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.viewparts.AdaptiveNavigationPanelViewPart;
import org.nasdanika.html.app.viewparts.ContentPanelViewPart;
import org.nasdanika.html.app.viewparts.FooterViewPart;
import org.nasdanika.html.app.viewparts.NavigationBarViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;

/**
 * Builds an application from actions. 
 * @author Pavel Vlasov
 *
 */
public abstract class AbstractActionApplicationBuilder extends ViewPartApplicationBuilder {
	
	protected abstract Action getActiveAction();
	
	protected Action getRootAction() {
		Action activeAction = getActiveAction();
		if (activeAction == null || activeAction.getPath().isEmpty()) {
			return activeAction;
		}
		return activeAction.getPath().get(0);
	}
			
	protected Action getPrincipalAction() {
		Action activeAction = getActiveAction();
		if (activeAction == null) {
			return activeAction;
		}
		List<Action> path = activeAction.getPath();
		if (path.isEmpty()) {
			return null;
		}
		if (path.size() == 1) {
			return activeAction;
		}
		
		return activeAction.getPath().get(1);		
	}
	
	protected List<? extends Action> getNavigationPanelActions() {
		Action principalAction = getPrincipalAction();
		return principalAction == null ? Collections.emptyList() : principalAction.getNavigationChildren();
	}

	@Override
	protected ViewPart getHeaderViewPart() {
		Action rootAction = getRootAction();
		return rootAction == null ? vg -> null : viewGenerator -> {
			Tag link = viewGenerator.link(rootAction).style().text().decoration().none();
			viewGenerator.get(BootstrapFactory.class).wrap(link).text().color(Color.DARK);
			return viewGenerator.get(BootstrapFactory.class).display(link, 4);
		};
	}

	@Override
	protected ViewPart getNavigationBarViewPart() {
		Action principalAction = getPrincipalAction();
		return principalAction == null ? vg -> null : new NavigationBarViewPart(principalAction, getActiveAction());
	}

	@Override
	protected ViewPart getNavigationPanelViewPart() {		
		List<? extends Action> navigationPanelActions = getNavigationPanelActions();
		return navigationPanelActions == null || navigationPanelActions.isEmpty() ? vg -> null : new AdaptiveNavigationPanelViewPart(navigationPanelActions, getActiveAction());
	}

	@Override
	protected ViewPart getContentPanelViewPart() {
		Action activeAction = getActiveAction();
		return activeAction == null ? vg -> null : new ContentPanelViewPart(activeAction, Util.equal(activeAction, getPrincipalAction()) || Util.equal(activeAction, getRootAction()));
	}

	@Override
	protected ViewPart getFooterViewPart() {
		Action rootAction = getRootAction();
		return rootAction == null ? vg -> null : new FooterViewPart(rootAction.getContextChildren());
	}
	
}
