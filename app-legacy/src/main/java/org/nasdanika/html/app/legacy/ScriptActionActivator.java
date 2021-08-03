package org.nasdanika.html.app;

/**
 * Action activator which executes script.
 * @author Pavel Vlasov
 *
 */
public interface ScriptActionActivator extends ActionActivator {
	
	/**
	 * @return code to be executed.
	 */
	String getCode();

}
