package org.nasdanika.html.app;

/**
 * Action which performs its own binding to the UI element. 
 * @author Pavel Vlasov
 *
 */
public interface BindingAction extends Action {
	
	/**
	 * Binds action to the UI element.
	 * @param uiElement
	 * @return true if binding was successful. If false is returned then the UI element shall not be added to the UI.
	 */
	boolean bind(Object uiElement);

}
