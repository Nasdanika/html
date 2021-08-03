package org.nasdanika.html.app;

import java.util.List;

/**
 * Input choice. It can be rendered in a variety of ways - an option for selects, check/radio buttons, dropdown item, jsTree node, ... 
 * @author Pavel Vlasov
 *
 */
public interface Choice extends Label {
	
	/**
	 * Choice value. If it is null then choice is not selectable and is considered a group if it has children
	 * or is rendered as disabled if it doesn't. 
	 * @return
	 */
	Object getValue();
	
	/**
	 * Choices may form hierarchical structure. It may be rendered  
	 * @return
	 */
	List<Choice> getChildren();

}
