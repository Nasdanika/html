package org.nasdanika.html.app.impl;

import java.util.List;

import org.nasdanika.common.Context;
import org.nasdanika.html.app.Action;

/**
 * Builds an application from actions. 
 * @author Pavel Vlasov
 *
 */
public class ActionApplicationBuilder extends AbstractActionApplicationBuilder {
	
	protected Action rootAction;
	protected Action principalAction;
	protected List<Action> navigationPanelActions;
	protected Action activeAction;
	
	/**
	 * Creates an application builder with the active action's root action as the root and
	 * the next child as the principal. This constructor shall be used only for
	 * actions with path length greater or equal to 2.
	 * @param activeAction
	 */
	public ActionApplicationBuilder(Action activeAction) {
		this(activeAction.getPath().get(0), activeAction.getPath().get(1), activeAction);
	}
	
	/**
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the navigation bar. Its child actions are generated in the navigation panel.
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the navigation bar or panel is shall
	 * be selected/active. 
	 */
	public ActionApplicationBuilder(Action rootAction, Action principalAction, Action activeAction) {
		this(rootAction, principalAction, principalAction.getNavigationChildren(), activeAction);
	}	
	
	/**
	 * 
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the nav bar.
	 * @param rootActions Actions to show in the navigation panel. 
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the navigation bar or panel is shall
	 * be selected/active. 
	 */
	public ActionApplicationBuilder(
			Action rootAction, 
			Action principalAction, 
			List<Action> navigationPanelActions, 
			Action activeAction) {
		this.rootAction = rootAction;
		this.principalAction = principalAction;
		this.navigationPanelActions = navigationPanelActions;
		this.activeAction = activeAction;
	}
		
	/**
	 * Creates an application builder with the active action's root action as the root and
	 * the next child as the principal. This constructor shall be used only for
	 * actions with path length greater or equal to 2.
	 * @param activeAction
	 */
	public ActionApplicationBuilder(Context context, Action activeAction) {
		this(context, activeAction.getPath().get(0), activeAction.getPath().get(1), activeAction);
	}
	
	/**
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the navigation bar. Its child actions are generated in the navigation panel.
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the navigation bar or panel is shall
	 * be selected/active. 
	 */
	public ActionApplicationBuilder(Context context, Action rootAction, Action principalAction, Action activeAction) {
		this(context, rootAction, principalAction, principalAction.getNavigationChildren(), activeAction);
	}	
	
	/**
	 * 
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the nav bar.
	 * @param rootActions Actions to show in the navigation panel. 
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the navigation bar or panel is shall
	 * be selected/active. 
	 */
	public ActionApplicationBuilder(
			Context context, 
			Action rootAction, 
			Action principalAction, 
			List<Action> navigationPanelActions, 
			Action activeAction) {
		super(context);
		this.rootAction = rootAction;
		this.principalAction = principalAction;
		this.navigationPanelActions = navigationPanelActions;
		this.activeAction = activeAction;
	}	

	@Override
	protected Action getRootAction() {
		return rootAction;
	}

	@Override
	protected Action getPrincipalAction() {
		return principalAction;
	}

	@Override
	protected List<Action> getNavigationPanelActions() {
		return navigationPanelActions;
	}

	@Override
	protected Action getActiveAction() {
		return activeAction;
	}
	
}
