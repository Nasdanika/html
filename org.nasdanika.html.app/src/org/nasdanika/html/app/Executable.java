package org.nasdanika.html.app;

/**
 * Base interface for action. Also shall be implemented by action delegates such as adapters.
 * @author Pavel Vlasov
 *
 */
public interface Executable {

	/**
	 * Executes the action and returns result to be displayed in the UI.
	 * @return
	 */
	Object execute();

}
