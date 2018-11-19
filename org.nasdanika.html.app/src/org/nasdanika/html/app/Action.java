package org.nasdanika.html.app;

import java.util.List;

import org.nasdanika.html.bootstrap.Color;

/**
 * Abstraction of something that can be invoked.
 * Actions may be rendered as buttons, JsTree context menu items, drop-down items, links, and other UI elements.
 * @author Pavel Vlasov
 *
 */
public interface Action {
	
	/**
	 * Action icon. URL if contains slash, css class otherwise, e.g. 'far fa-user'.
	 * @return
	 */
	String getIcon();
	
	/**
	 * Action label.
	 * @return
	 */
	String getLabel();
	
	/**
	 * Action may be disabled.
	 * @return
	 */
	boolean isDisabled();
		
	/**
	 * @return Short help text, typically rendered as 'title' attribute.
	 */
	String getTooltip();
	
	/**
	 * @return Bootstrap color. Applicable only to bootstrap-based UI elements.
	 */
	Color getColor();
	
	/**
	 * Indicates that action UI element shall be rendered as outline, not "solid" - see https://getbootstrap.com/docs/4.1/components/buttons/#outline-buttons 
	 * @return
	 */
	boolean isOutline();
	
	/**
	 * If this method returns a non-null value then when the action UI is activated (e.g. button clicked) a confirmation dialog is shown
	 * to the user, e.g. confirm(confirmation), and the action is executed only if the user confirms.
	 * @return Confirmation for performing action.
	 */
	String getConfirmation();
	
	/**
	 * If true the rendered UI element is aligned to the right.
	 * @return
	 */
	boolean isFloatRight(); 
	
	/**
	 * Sub-actions. If action has children it may be rendered, for example, as a drop-down button.
	 * Implementations may create child actions list on access to optimize processing in cases of, say on-demand ajax loading.
	 * @return
	 */
	List<Action> getChildren();
	
	/**
	 * Actions which are typically rendered as context menu. For example, View customer action may have View account action as its child - so it is appears
	 * this way in the left navigation panel, and Edit profile context action.
	 * At the same time context actions may be rendered as a button group at the content area. 
	 * E.g. for view Customer.accounts reference child actions would be account view actions and context actions may include "Open account" action.    
	 * @return
	 */
	List<Action> getContextActions();		
	
	/**
	 * Action id. The same action may be rendered as different UI elements and the UI may be structures to dispatch actions invocations to a function
	 * and pass elements or action id to it. In "object-oriented" UI's action id may be formed from object id, action, and optional qualifier. E.g. L3-view-accounts.
	 * @return
	 */
	String getId();
	
	/**
	 * A detailed action description which can be shown to the user in, say, a dialog box.
	 * @return
	 */	
	String getDescription();
	
}
