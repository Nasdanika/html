package org.nasdanika.html.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.Context;
import org.nasdanika.common.FilterExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.html.app.impl.Util;

/**
 * Abstraction of something that can be invoked.
 * Actions may be rendered as buttons, JsTree context menu items, drop-down items, links, and other UI elements.
 * @author Pavel Vlasov
 *
 */
public interface Action extends Label, ViewPart, Categorized, Adaptable {
		
	/**
	 * Binding of org.nasdanika.Supplier to {@link Action}
	 * @author Pavel
	 *
	 */
	interface Supplier extends Label.Supplier<Action> {
		
		/**
		 * Wraps generic supplier in this strongly typed one.
		 * @param generic
		 */
		static Supplier from(org.nasdanika.common.Supplier<Action> generic) {
			class SupplierImpl extends FilterExecutionParticipant<org.nasdanika.common.Supplier<Action>> implements Supplier {
	
				public SupplierImpl(org.nasdanika.common.Supplier<Action> target) {
					super(target);
				}
	
				@Override
				public Action execute(ProgressMonitor progressMonitor) throws Exception {
					return target.execute(progressMonitor);
				}
				
			}
			return new SupplierImpl(generic);			
		}
		
		/**
		 * Binding of {@link SupplierFactory} to {@link Supplier}
		 * @author Pavel
		 *
		 */
		interface Factory extends Label.Supplier.Factory<Action> {
			
			@Override
			Supplier create(Context context) throws Exception;
			
			/**
			 * Wraps generic {@link SupplierFactory} in this strongly typed {@link Factory}
			 * @param generic
			 * @return
			 */
			static Factory from(SupplierFactory<Action> generic) {
				return new Factory() {
	
					@Override
					public Supplier create(Context context) throws Exception {
						return Supplier.from(generic.create(context));
					}
					
				};
			}
			
		}
		
	}	
	
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
		 * Content right actions are shown in an adaptive navigation panel to the right of the action content.
		 */
		String CONTENT_RIGHT = "content-right";
		
		/**
		 * Content left actions are shown in an adaptive  navigation panel to the left of the action content.
		 */
		String CONTENT_LEFT = "content-left";
		
		/**
		 * Float right actions are styled to float right.
		 */
		String FLOAT_RIGHT = "float-right";
		
		/**
		 * Float left actions are styled to float left.
		 */
		String FLOAT_LEFT = "float-left";
				
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
			if (child != null) {
				Object id = child.getId();
				if (id != null && id.equals(path[0])) {
					if (path.length == 1) {
						return child;
					}
					return child.getChildById(Arrays.copyOfRange(path, 1, path.length));
				}
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
				if (c != null && c.isInRole(role)) {
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
	
	default ViewPart createSectionsViewPart(Action activeAction, int sectionLevel, int headerLevel) {
		return getSectionStyle().createViewPart(this, activeAction, sectionLevel, headerLevel);
	}
		
	/**
	 * Finds action by id in the hierarchy of this action.
	 * @param id Id of the action to find. If null, then search is not performed and null is returned. 
	 * @return Action or null.
	 */
	default Action findById(String id) {
		return find(id == null ? null : action -> id.equals(action.getId()));
	}
	
	/**
	 * Finds action matching a predicate in the hierarchy of this action.
	 * @param predicate Action predicate 
	 * @return Action or null.
	 */
	default Action find(Predicate<Action> predicate) {
		if (predicate == null) {
			return null;
		}
		if (predicate.test(this)) {
			return this;
		}
		for (Action child: getChildren()) {
			if (child != null) {
				Action found = child.find(predicate);
				if (found != null) {
					return found;
				}
			}
		}
		return null;
	}
		
}
