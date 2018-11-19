package org.nasdanika.html.emf;

import org.nasdanika.html.app.Action;

/**
 * Renders different aspects of a class/object.
 * @author Pavel Vlasov
 *
 */
public interface Renderer {
	
	/**
	 * @return View action.
	 */
	Action getViewAction(); 
	
	/**
	 * Renders a view of an object.
	 * @return
	 */
	Object renderView();
	
	/**
	 * Renders edit form.
	 * @return
	 */
	Object renderEdit();
	
}
