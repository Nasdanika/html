package org.nasdanika.html.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.html.app.impl.Util;

/**
 * Abstraction of something that can be invoked.
 * Actions may be rendered as buttons, JsTree context menu items, drop-down items, links, and other UI elements.
 * @author Pavel Vlasov
 *
 */
public interface Action extends Label, ViewPart, Categorized, Adaptable {
	
	/**
	 * "Built-in" roles.
	 * @author Pavel
	 *
	 */
	interface Role {
		
		/**
		 * Navigation actions are shown in the navigation panel as a list or as a tree. 
		 * Typically navigation actions represent contained objects. 
		 * For example for a customer account actions would be in the "navigation" role and be shown in the navigation panel.
		 */
		String NAVIGATION = "navigation";
		
		/**
		 * Context actions are shown as a tree context menu and as a button toolbar in the content panel.
		 * For the root action context actions are shown in the footer and for the principal action in the navigation bar.
		 * Typically context actions will represent operations available on/for the object. E.g. "Transfer funds" may be a context action
		 * for a bank customer.  
		 */
		String CONTEXT = "context";
		
		/**
		 * Section actions are shown as sections in the content panel. They may represent objects which are not shown in 
		 * the navigation. For example, customer address may be shown as a section of the customer page.
		 */
		String SECTION = "section";
		
		/**
		 * {@link PropertySource} or {@link PropertyDescriptor} action to be displayed in view mode.
		 */
		String VIEW = "view";
		
		/**
		 * {@link PropertySource} or {@link PropertyDescriptor} action to be displayed in edit mode.
		 */
		String EDIT = "edit";
		
	}
	
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
	
//	/**
//	 * If true the rendered UI element is aligned to the right.
//	 * @return
//	 */
//	boolean isFloatRight(); 
	
	/**
	 * Sub-actions. If action has children it may be displayed, for example, as a drop-down button.
	 * Implementations may create child actions list on access to optimize processing in cases of, say on-demand ajax loading.
	 * @return
	 */
	List<Action> getChildren();
	
	/**
	 * Action role defines where its UI element is displayed. There are 3 built-in roles ({@link Role}):
	 * 
	 *  * NAVIGATION - navigation panel for the principal action.  
	 *  * CONTEXT:
	 *      * A button toolbar below the active action content, 
	 *      * JsTree context menu, 
	 *      * Navigation bar for the principal action.
	 *      * Footer for the root action.  
	 *  * SECTION - sections in the content panel below the active action content and context actions button toolbar.
	 *  
	 * Customizations may define additional roles.
	 *  
	 * @param role
	 * @return
	 */
	boolean isInRole(String role);	
	
	/**
	 * Parent action.
	 * @return
	 */
	Action getParent();
	
	default SectionStyle getSectionStyle() {
		return SectionStyle.AUTO; 
	}
	
	default int getSectionColumns() {
		return 3;
	}
	
	/**
	 * Action path in the hierarchy starting from the root action, not including the action itself.
	 * @return
	 */
	default List<Action> getPath() {
		Action parent = getParent();
		if (parent == null) {
			return Collections.emptyList();
		}
		List<Action> path = new ArrayList<>(parent.getPath());
		path.add(parent);
		return Collections.unmodifiableList(path);
	}
	
	/**
	 * @return Root of the action hierarchy.
	 */
	default Action getRoot() {
		Action parent = getParent();
		return parent == null ? this : parent.getRoot();
	}
	
	/**
	 * Returns a child action by its id path. 
	 * @param path
	 * @return
	 */
	default Action getChildById(Object... path) {
		if (path.length == 0) {
			return null;
		}
		for (Action child: getChildren()) {
			Object id = child.getId();
			if (id != null && id.equals(path[0])) {
				if (path.length == 1) {
					return child;
				}
				return child.getChildById(Arrays.copyOfRange(path, 1, path.length));
			}
		}		
		return null;
	}
	
	/**
	 * @return An optional {@link ActionActivator} to activate this action. 
	 * Some actions may have no activators. One case is section actions which are executed with the containing action, another possible case is when an action
	 * is used for grouping its child actions, similar to a category.
	 */
	ActionActivator getActivator();
	
	// --- Helper methods ---
	
	/**
	 * Returns children in a given role
	 * @param role
	 * @return
	 */
	default List<Action> getChildrenByRole(String... roles) {		
		if (roles.length == 0) {
			return getChildren();
		}
		Predicate<? super Action> predicate = c -> {
			for (String role: roles) {
				if (c.isInRole(role)) {
					return true;
				}
			}
			return false;
		};
		return getChildren().stream().filter(predicate).collect(Collectors.toList());
	}
	
	default List<Map.Entry<Label, List<Action>>> getChildrenGroupedByCategory(String... roles) {
		return Util.groupByCategory(getChildrenByRole(roles));		
	}	
		
	/**
	 * Returns children in the NAVIGATION role
	 * @param role
	 * @return
	 */
	default List<Action> getNavigationChildren() {
		return getChildrenByRole(Role.NAVIGATION);
	}
	
	default List<Map.Entry<Label, List<Action>>> getNavigationChildrenGroupedByCategory() {
		return getChildrenGroupedByCategory(Role.NAVIGATION);
	}
	
	/**
	 * Returns children in the CONTEXT role
	 * 
	 * @param role
	 * @return
	 */
	default List<Action> getContextChildren() {
		return getChildrenByRole(Role.CONTEXT);
	}

	default List<Map.Entry<Label, List<Action>>> getContextChildrenGroupedByCategory() {
		return getChildrenGroupedByCategory(Role.CONTEXT);
	}
	
	/**
	 * Returns children in the SECTION role
	 * 
	 * @param role
	 * @return
	 */
	default List<Action> getSectionChildren() {
		return getChildrenByRole(Role.SECTION);
	}

	default List<Map.Entry<Label, List<Action>>> getSectionChildrenGroupedByCategory() {
		return getChildrenGroupedByCategory(Role.SECTION);
	}
	
	default ViewPart createSectionsViewPart(Action activeAction, int level, int paragraphLevel) {
		return getSectionStyle().createViewPart(this, activeAction, level, paragraphLevel);
	}
		
}
