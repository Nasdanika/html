package org.nasdanika.html.app;

/**
 * Action which executes script.
 * @author Pavel Vlasov
 *
 */
public interface ScriptAction extends Action {
	
	/**
	 * @return code to be executed (using eval() function).
	 */
	String getCode();

}
