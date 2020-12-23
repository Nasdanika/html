package org.nasdanika.html.app.impl;

import java.util.Collections;
import java.util.List;

import org.nasdanika.common.Context;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.DecoratorProvider;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.viewparts.AdaptiveNavigationPanelViewPart;
import org.nasdanika.html.app.viewparts.ContentPanelViewPart;
import org.nasdanika.html.app.viewparts.FooterViewPart;
import org.nasdanika.html.app.viewparts.NavigationBarViewPart;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Navs;

/**
 * Builds an application from actions. 
 * @author Pavel Vlasov
 *
 */
public abstract class AbstractActionApplicationBuilder extends ViewPartApplicationBuilder {
	
	protected AbstractActionApplicationBuilder() {
		super();
	}

	protected AbstractActionApplicationBuilder(Context context) {
		super(context);
	}

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
	
	protected List<Action> getNavigationPanelActions() {
		Action principalAction = getPrincipalAction();
		return principalAction == null ? Collections.emptyList() : principalAction.getNavigationChildren();
	}

	@Override
	protected ViewPart getHeaderViewPart() {
		Action rootAction = getRootAction();
		return rootAction == null ? (vg, progressMonitor) -> null : (viewGenerator, progressMonitor) -> {
			Tag link = viewGenerator.link(rootAction);
			link.addClass("nsd-root-action");
			
			DecoratorProvider decoratorProvider = viewGenerator.computingContext().get(DecoratorProvider.class);
			if (decoratorProvider != null) {
				viewGenerator.decorate(link, decoratorProvider.getDecorator("application/header/title"));
			}
			
			List<Action> navigationChildren = rootAction.getNavigationChildren();
			if (navigationChildren.size() < 2) {
				return styleRootActionLink(viewGenerator, link);
			}

			ViewGenerator navsViewGenerator = viewGenerator;
			
			if (decoratorProvider != null) {
				Decorator navsDecorator = decoratorProvider.getDecorator("application/header/navs");
				if (navsDecorator != null) {
					navsViewGenerator = viewGenerator.fork();
					navsViewGenerator.register(Decorator.class, navsDecorator);
				}
			}

			Navs navs = navsViewGenerator.categorizedLinkNavs(navigationChildren.subList(1, navigationChildren.size()), getActiveAction(), getHeaderNavTextColor());
			navs._float().right();
			
			return viewGenerator.get(HTMLFactory.class).fragment(styleRootActionLink(viewGenerator, link), navs);
		};
	}
	
	/**
	 * Apply styling to the root action link in the header.
	 * @param link
	 * @return
	 */
	protected Object styleRootActionLink(ViewGenerator viewGenerator, Tag link) {
		return link;
//		link.style().text().decoration().none();
//		viewGenerator.get(BootstrapFactory.class).wrap(link).text().color(Color.DARK);
//		return viewGenerator.get(BootstrapFactory.class).display(link, 4);
	}

	/**
	 * Override to customize header navs text color.
	 * @return
	 */
	protected Color getHeaderNavTextColor() {
		return null;
	};

	@Override
	protected ViewPart getNavigationBarViewPart() {
		Action principalAction = getPrincipalAction();
		return principalAction == null ? (vg, progressMonitor) -> null : new NavigationBarViewPart(principalAction, getActiveAction());
	}

	@Override
	protected ViewPart getNavigationPanelViewPart() {		
		List<Action> navigationPanelActions = getNavigationPanelActions();
		return navigationPanelActions == null || navigationPanelActions.isEmpty() ? (vg, progressMonitor) -> null : new AdaptiveNavigationPanelViewPart(navigationPanelActions, getActiveAction());
	}

	@Override
	protected ViewPart getContentPanelViewPart() {
		Action activeAction = getActiveAction();
		return activeAction == null ? (vg, progressMonitor) -> null : new ContentPanelViewPart(activeAction);
	}

	@Override
	protected ViewPart getFooterViewPart() {
		Action rootAction = getRootAction();
		return rootAction == null ? (vg, progressMonitor) -> null : new FooterViewPart(rootAction.getContextChildren());
	}
	
}
