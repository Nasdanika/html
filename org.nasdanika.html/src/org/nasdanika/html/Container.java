package org.nasdanika.html;


/**
 * @author Pavel
 *
 * @param <T>
 */
public interface Container<T extends Container<?>> extends AutoCloseable {

	/**
	 * Adds content to the container.
	 * @param content
	 * @return
	 */
	T content(Object... content);
	
	boolean isEmpty();
	
}
