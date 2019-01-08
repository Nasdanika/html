package org.nasdanika.html.app.impl;

import java.util.List;
import java.util.Map;

import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.Color;

/**
 * Builds an application from actions. Provides default implementations of protected "generateXXX" methods.
 * Delegates generation to {@link ViewPart}'s created by createXXXViewPart methods. 
 * This class be customized by subclassing and overriding either generateXXX or createXXXViewPart methods.
 * @author Pavel Vlasov
 *
 */
public class ActionApplicationBuilder extends ViewPartApplicationBuilder {
	
	protected Action rootAction;
	protected Action principalAction;
	protected List<? extends Action> navigationPanelActions;
	protected Action activeAction;
	protected Map<String,Object> input;
	
	/**
	 * Creates an application builder with the active action's root action as the root and
	 * the next child as the principal. This constructor shall be used only for
	 * actions with path length greater or equal to 2.
	 * @param activeAction
	 */
	public ActionApplicationBuilder(Action activeAction, Map<String,Object> input) {
		this(activeAction.getPath().get(0), activeAction.getPath().get(1), activeAction, input);
	}
	
	/**
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the navigation bar. Its child actions are generated in the navigation panel.
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the navigation bar or panel is shall
	 * be selected/active. 
	 */
	public ActionApplicationBuilder(Action rootAction, Action principalAction, Action activeAction, Map<String,Object> input) {
		this(rootAction, principalAction, principalAction.getChildren(), activeAction, input);
	}	
	
	/**
	 * 
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the nav bar.
	 * @param navigationPanelActions Actions to show in the navigation panel. 
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the navigation bar or panel is shall
	 * be selected/active. 
	 */
	public ActionApplicationBuilder(
			Action rootAction, 
			Action principalAction, 
			List<? extends Action> navigationPanelActions, 
			Action activeAction,
			Map<String,Object> input) {
		this.rootAction = rootAction;
		this.principalAction = principalAction;
		this.navigationPanelActions = navigationPanelActions;
		this.activeAction = activeAction;
		this.input = input;
		
	}

	@Override
	protected ViewPart getHeaderViewPart() {
		return rootAction == null ? vg -> null : viewGenerator -> {
			Tag link = viewGenerator.link(rootAction).style().text().decoration().none();
			viewGenerator.getBootstrapFactory().wrap(link).text().color(Color.DARK);
			return viewGenerator.getBootstrapFactory().display(link, 4);
		};
	}

	@Override
	protected ViewPart getNavigationBarViewPart() {
		return principalAction == null ? vg -> null : new NavigationBarViewPart(principalAction, activeAction);
	}

	@Override
	protected ViewPart getNavigationPanelViewPart() {
		return navigationPanelActions == null || navigationPanelActions.isEmpty() ? vg -> null : new ActionGroupNavigationPanelViewPart(navigationPanelActions, activeAction);
	}

	@Override
	protected ViewPart getContentPanelViewPart() {
		return activeAction == null ? vg -> null : new ContentPanelViewPart(activeAction, input, Util.equal(activeAction, principalAction) || Util.equal(activeAction, rootAction));
	}

	@Override
	protected ViewPart getFooterViewPart() {
		return rootAction == null ? vg -> null : new FooterViewPart(rootAction);
	}
	
}
