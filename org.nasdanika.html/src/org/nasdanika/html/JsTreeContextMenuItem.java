package org.nasdanika.html;

/**
 * Builds JsTree context menu item - https://www.jstree.com/api/#/?q=$.jstree.defaults.contextmenu&f=$.jstree.defaults.contextmenu.items.
 * Use toString() to produce JavaScript.
 * @author Pavel Vlasov
 *
 */
public interface JsTreeContextMenuItem {

	/**
	 * @param separatorBefore a boolean indicating if there should be a separator before this item
	 * @return
	 */
	JsTreeContextMenuItem separatorBefore(boolean separatorBefore);
	
	default JsTreeContextMenuItem separatorBefore() {
		return separatorBefore(true);
	}

	/**
	 * 
	 * @param separatorAfter a boolean indicating if there should be a separator after this item
	 * @return
	 */
	JsTreeContextMenuItem separatorAfter(boolean separatorAfter);
	
	default JsTreeContextMenuItem separatorAfter() {
		return separatorAfter(true);
	}
	
	/**
	 * 
	 * @param disabled a boolean indicating if this action should be disabled
	 * @return
	 */
	JsTreeContextMenuItem disabled(boolean disabled);
	
	default JsTreeContextMenuItem disabled() {
		return disabled(true);
	}
	
	/**
	 * 
	 * @param label a string - the name of the action (could be a function returning a string)
	 * @return
	 */
	JsTreeContextMenuItem label(Object label);
	
	/**
	 * 
	 * @param title a string - an optional tooltip for the item
	 * @return
	 */
	JsTreeContextMenuItem title(Object title);
	
	/**
	 * 
	 * @param action a function to be executed if this item is chosen
	 * @return
	 */
	JsTreeContextMenuItem action(Object action);
	
	/**
	 * 
	 * @param icon a string, can be a path to an icon or a className, if using an image that is in the current directory use a ./ prefix, otherwise it will be detected as a class
	 * @return
	 */
	JsTreeContextMenuItem icon(Object icon);
	
	/**
	 * 
	 * @param shortcut keyCode which will trigger the action if the menu is open (for example 113 for rename, which equals F2)
	 * @return
	 */
	JsTreeContextMenuItem shortcut(Object shortcut);
	
	/**
	 * 
	 * @param shortcutLabel shortcut label (like for example F2 for rename)
	 * @return
	 */
	JsTreeContextMenuItem shortcutLabel(Object shortcutLabel);
	
	/**
	 * Creates a sub-menu item and returns it. If an item for a given key already exists, returns the existing item.
	 * @param key sub-menu item key.
	 * @return sub-menu item.
	 */
	JsTreeContextMenuItem createSubMenuItem(String key);
	
	/**
	 * Adds a sub-menu item.
	 * @param key
	 * @param item
	 * @return this item.
	 */
	JsTreeContextMenuItem addSubMenuItem(String key, JsTreeContextMenuItem item);

	/**
	 * Sets sub-menu. If sub-menu set this way is not null, then items added or created with addSubMenuItem and createSubMenuItem are ignored.
	 */
	JsTreeContextMenuItem subMenu(Object subMenu);
	
}
