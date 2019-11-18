package org.nasdanika.html.app;

/**
 * Application-level objects such as actions may implement this interface to customize their appearance.
 * @author Pavel
 *
 */
public interface Decorator {
	
	/**
	 * Customizes target.
	 * @param target
	 * @param viewGenerator
	 */
	void decorate(Object target, ViewGenerator viewGenerator);	

}
