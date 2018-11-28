package org.nasdanika.html.app.impl;

import java.util.List;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;

/**
 * Builds an application from actions. 
 * @author Pavel Vlasov
 *
 */
public abstract class AbstractActionApplicationBuilder implements ApplicationBuilder {

	protected Action root;
	protected  Action principal;
	protected  List<? extends Action> navigation;
	protected  Action active;

	/**
	 * Creates an application builder with the active action's root action as the root and
	 * the next child as the principal. This constructor shall be used only for
	 * actions with path length greater or equal to 2.
	 * @param active
	 */
	protected AbstractActionApplicationBuilder(Action active) {
		this(active.getPath().get(0), active.getPath().get(1), active);
	}
	
	/**
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the nav bar. Its child actions are generated in the left menu.
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the top or left navigation is shall
	 * be selected. 
	 */
	protected AbstractActionApplicationBuilder(Action root, Action principal, Action active) {
		this(root, principal, principal.getChildren(), active);
	}	
	
	/**
	 * 
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the nav bar.
	 * @param navigation Navigation actions to show in the left menu. 
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the top or left navigation is shall
	 * be selected. 
	 */
	protected AbstractActionApplicationBuilder(Action root, Action principal, List<? extends Action> navigation, Action active) {
		this.root = root;
		this.principal = principal;
		this.navigation = navigation;
		this.active = active;
	}
	
	@Override
	public void build(Application application) {
		// TODO Auto-generated method stub
		
	}

}
