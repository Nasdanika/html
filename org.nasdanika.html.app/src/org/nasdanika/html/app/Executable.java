package org.nasdanika.html.app;

import java.util.Map;

/**
 * Base interface for action. Also shall be implemented by action delegates such as adapters.
 * @author Pavel Vlasov
 *
 */
public interface Executable {

	/**
	 * Executes the action and returns result to be displayed in the UI.
	 * @param input Optional input data, e.g. HTTP request query parameters. 
	 * @return
	 */
	Object execute(ViewGenerator viewGenerator, Map<String,Object> input);

}
