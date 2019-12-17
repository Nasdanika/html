package org.nasdanika.html.bootstrap;

import org.nasdanika.html.NamedItemsContainer;
import org.nasdanika.html.Tag;

/**
 * Interface for building <a href="https://getbootstrap.com/docs/4.1/components/list-group/">List groups</a> with actions - div container, link elements.
 * @author Pavel Vlasov
 *
 */
public interface ActionGroup extends BootstrapElement<Tag, ActionGroup>, NamedItemsContainer {
	
	Tag action(boolean active, boolean disabled, Color color, Object href, Object... name);
	
	/**
	 * Creates a content action, similar to navs.
	 * @param name
	 * @param active
	 * @param disabled
	 * @param color
	 * @param contentId
	 * @param content
	 * @return
	 */
	Tag contentAction(Object name, boolean active, boolean disabled, Color color, Object contentId, Object... content);
	
	/**
	 * ActionGroup toHTMLElement() returns the div containing action links. 
	 * Content div shall be output either using this method or asContainer(). 
	 * @return
	 */
	Tag getContentDiv();
	
	/**
	 * Creates a container with a single row and two columns - one with the action group div and the other with the content div. 
	 * @return
	 */
	Container asContainer(boolean fluid);
	
}
