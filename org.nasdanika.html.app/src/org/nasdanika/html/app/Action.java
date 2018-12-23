package org.nasdanika.html.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Abstraction of something that can be invoked.
 * Actions may be rendered as buttons, JsTree context menu items, drop-down items, links, and other UI elements.
 * @author Pavel Vlasov
 *
 */
public interface Action extends Label, Executable, Categorized {
	
	/**
	 * Action may be disabled.
	 * @return
	 */
	boolean isDisabled();
	
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
	 * Sub-actions. If action has children it may be displayed, for example, as a drop-down button.
	 * Implementations may create child actions list on access to optimize processing in cases of, say on-demand ajax loading.
	 * @return
	 */
	List<? extends Action> getChildren();
	
	/**
	 * Actions which are typically displayed in a context menu. For example, View customer action may have View account action as its child - so it is appears
	 * this way in the left navigation panel, and Edit profile context action.
	 * At the same time context actions may be rendered as a button group at the content area. 
	 * E.g. for view Customer.accounts reference child actions would be account view actions and context actions may include "Open account" action.    
	 * @return
	 */
	List<? extends Action> getContextActions();		
	
	/**
	 * Actions which are displayed in sections in the content pane.  
	 * @return
	 */
	List<? extends Action> getSections();
	
	/**
	 * Parent action.
	 * @return
	 */
	Action getParent();
	
	/**
	 * Action path in the hierarchy starting from the root action, not including the action itself.
	 * @return
	 */
	List<Action> getPath();
	
	ActionActivator getActivator();
		
	@Override
	default Map<String, Object> toMap() {
		Map<String, Object> map = Label.super.toMap();
		if (getActivator() instanceof NavigationActionActivator) {
			map.put("activator", Collections.singletonMap("url", ((NavigationActionActivator) getActivator()).getUrl()));
		} else if (getActivator() instanceof ScriptActionActivator) {
			map.put("activator", Collections.singletonMap("code", ((ScriptActionActivator) getActivator()).getCode()));
		}
		if (getChildren() != null) {
			List<Map<String, Object>> mc = new ArrayList<>();
			map.put("children", mc);
			for (Action child: getChildren()) {
				mc.add(child.toMap());
			}
		}
		if (getContextActions() != null) {
			List<Map<String, Object>> mcx = new ArrayList<>();
			map.put("contextActions", mcx);
			for (Action cx: getContextActions()) {
				mcx.add(cx.toMap());
			}
		}
		if (getSections() != null) {
			List<Map<String, Object>> ms = new ArrayList<>();
			map.put("sections", ms);
			for (Action section: getSections()) {
				ms.add(section.toMap());
			}
		}
		if (getConfirmation() != null) {
			map.put("confirmation", getConfirmation());
		}
		if (isDisabled()) {
			map.put("disabled", true);
		}
		if (isFloatRight()) {
			map.put("floatRight", true);
		}
		if (getCategory() != null) {
			map.put("category", getCategory().toMap());
		}
		// Roles cannot be stored at this level.
		return map;
	}
	
	
	
}
