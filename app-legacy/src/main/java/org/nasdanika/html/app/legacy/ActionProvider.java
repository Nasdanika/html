package org.nasdanika.html.app;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.nasdanika.html.app.Action.Role;
import org.nasdanika.html.app.impl.Util;

/**
 * Provider of actions for {@link PropertyDescriptor} and {@link PropertySource}.
 * 
 * @author Pavel
 *
 */
public interface ActionProvider {
	
	/**
	 * Use this provider if there are no actions, getActionProvider() assumes non-null return value.
	 */
	ActionProvider EMPTY_ACTION_PROVIDER = () -> Collections.emptyList();
	
	List<Action> getActions();

	// --- Helper methods ---

	/**
	 * Returns actions in a given role
	 * 
	 * @param role
	 * @return
	 */
	default List<Action> getActions(String role) {
		return getActions().stream().filter(c -> c.isInRole(role)).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	default List<Map.Entry<Label, List<Action>>> getActionsGroupedByCategory() {
		List<?> ret = Util.groupByCategory(getActions());
		return (List<Map.Entry<Label, List<Action>>>) ret;
	}

	default List<Map.Entry<Label, List<Action>>> getActionsGroupedByCategory(String role) {
		return Util.groupByCategory(getActions(role));
	}

	/**
	 * Returns children in the VIEW role
	 * 
	 * @param role
	 * @return
	 */
	default List<Action> getViewActions() {
		return getActions(Role.VIEW);
	}

	default List<Map.Entry<Label, List<Action>>> getViewActionsGroupedByCategory() {
		return getActionsGroupedByCategory(Role.VIEW);
	}

	/**
	 * Returns children in the EDIT role
	 * 
	 * @param role
	 * @return
	 */
	default List<Action> getEditActions() {
		return getActions(Role.EDIT);
	}

	default List<Map.Entry<Label, List<Action>>> getEditActionsGroupedByCategory() {
		return getActionsGroupedByCategory(Role.EDIT);
	}

}
