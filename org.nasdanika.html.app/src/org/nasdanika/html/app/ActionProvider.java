package org.nasdanika.html.app;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.nasdanika.html.app.Action.Role;
import org.nasdanika.html.app.impl.Util;

/**
 * Provider of actions. Base interface for {@link PropertyDescriptor} and
 * {@link PropertySource}.
 * 
 * @author Pavel
 *
 */
public interface ActionProvider {
	
	/**
	 * Use this provider if there are no actions, getActionProvider() assumes non-null return value.
	 */
	ActionProvider EMPTY_ACTION_PROVIDER = () -> Collections.emptyList();
	
	List<? extends Action> getActions();

	// --- Helper methods ---

	/**
	 * Returns actions in a given role
	 * 
	 * @param role
	 * @return
	 */
	default List<? extends Action> getActions(String role) {
		return getActions().stream().filter(c -> c.isInRole(role)).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	default <T extends Action> List<Map.Entry<Label, List<T>>> getActionsGroupedByCategory() {
		List<?> ret = Util.groupByCategory(getActions());
		return (List<Map.Entry<Label, List<T>>>) ret;
	}

	@SuppressWarnings("unchecked")
	default <T extends Action> List<Map.Entry<Label, List<T>>> getActionsGroupedByCategory(String role) {
		List<?> ret = Util.groupByCategory(getActions(role));
		return (List<Map.Entry<Label, List<T>>>) ret;
	}

	/**
	 * Returns children in the VIEW role
	 * 
	 * @param role
	 * @return
	 */
	default List<? extends Action> getViewActions() {
		return getActions(Role.VIEW);
	}

	default <T extends Action> List<Map.Entry<Label, List<T>>> getViewActionsGroupedByCategory() {
		return getActionsGroupedByCategory(Role.VIEW);
	}

	/**
	 * Returns children in the EDIT role
	 * 
	 * @param role
	 * @return
	 */
	default List<? extends Action> getEditActions() {
		return getActions(Role.EDIT);
	}

	default <T extends Action> List<Map.Entry<Label, List<T>>> getEditActionsGroupedByCategory() {
		return getActionsGroupedByCategory(Role.EDIT);
	}

}
