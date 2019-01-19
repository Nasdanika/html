package org.nasdanika.html.app.impl;

import java.util.List;
import java.util.Map;

import org.nasdanika.html.app.Action;

/**
 * Builds an application from actions. 
 * @author Pavel Vlasov
 *
 */
public class ActionApplicationBuilder extends AbstractActionApplicationBuilder {
	
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
		this(rootAction, principalAction, principalAction.getNavigationChildren(), activeAction, input);
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
	protected Action getRootAction() {
		return rootAction;
	}

	@Override
	protected Action getPrincipalAction() {
		return principalAction;
	}

	@Override
	protected List<? extends Action> getNavigationPanelActions() {
		return navigationPanelActions;
	}

	@Override
	protected Action getActiveAction() {
		return activeAction;
	}

	@Override
	protected Map<String, Object> getInput() {
		return input;
	}
	
}
