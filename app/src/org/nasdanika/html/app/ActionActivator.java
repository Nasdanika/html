package org.nasdanika.html.app;

/**
 * Actions can be activated in a variety of ways - by navigating to a specified URL, by executing a script, or by 
 * some framework binding. This is the base interface for different activators. 
 * @author Pavel Vlasov
 *
 */
public interface ActionActivator {
	
	/**
	 * If this method returns true then action shall be inlined if possible, i.e. its content shall be rendered instead of a link.
	 * @return
	 */
	default boolean inline() {
		return false;
	};

}
