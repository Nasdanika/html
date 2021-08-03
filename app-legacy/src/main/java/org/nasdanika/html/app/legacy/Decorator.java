package org.nasdanika.html.app;

/**
 * Application-level objects such as actions may implement this interface to customize their appearance.
 * @author Pavel
 *
 */
public interface Decorator {
	
	/**
	 * Decorator selector is a view generator property which can be used to customize decoration logic. 
	 */
	String SELECTOR_KEY = Decorator.class.getName()+":selector";
	
	/**
	 * Selector value indicating that decorators shall not be used.
	 */
	String NO_DECORATOR = "no-decorator";
	
	/**
	 * Customizes target.
	 * @param target
	 * @param viewGenerator
	 */
	void decorate(Object target, ViewGenerator viewGenerator);	

}
