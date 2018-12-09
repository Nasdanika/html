package org.nasdanika.html;

import java.util.function.Consumer;

/**
 * @author Pavel
 *
 * @param <T>
 */
public interface Container<T extends Container<?>> extends Consumer<Object> {

	/**
	 * Adds content to the container.
	 * @param content
	 * @return
	 */
	T content(Object... content);
	
	boolean isEmpty();
	
	@Override
	default void accept(Object content) {
		content(content);		
	}
	
}
