package org.nasdanika.html.emf;

/**
 * Renders different aspects of a class/object.
 * @author Pavel Vlasov
 *
 */
public interface Renderer {
	
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
	
	/**
	 * Renders a link to the object view.
	 */
	Object renderLink();
	
	/**
	 * Renders a tree representation of the object structure.
	 * @return
	 */
	Object renderTree();
}
