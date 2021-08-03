package org.nasdanika.html.app;

/**
 * Action activator which performs its own binding to the UI element. 
 * @author Pavel Vlasov
 *
 */
public interface BindingActionActivator extends ActionActivator {
	
	/**
	 * Binds action to the UI element.
	 * @param uiElement
	 * @param viewGenerator View generator may be used to build UI to be activated by this action, e.g. a modal dialog.
	 * @return true if binding was successful. If false is returned then the UI element shall not be added to the UI.
	 */
	boolean bind(Object uiElement, ViewGenerator viewGenerator);

}
