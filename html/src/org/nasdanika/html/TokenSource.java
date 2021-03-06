package org.nasdanika.html;

import java.util.function.BiConsumer;

/**
 * Mutable token source with ability to add tokens in a fluent fashion. 
 * @author Pavel Vlasov
 *
 */
public interface TokenSource extends java.util.function.Function<String, Object>, BiConsumer<String, Object> {
	
	/**
	 * Adds a new token. Null values are ignored.
	 * @param name
	 * @param value
	 * @return
	 */
	TokenSource put(String token, Object value);
	
	@Override
	default void accept(String token, Object value) {
		put(token, value);		
	}
	
	/**
	 * Interpolates the object using this token source. 
	 * @param input String, Reader, InputStream or URL.
	 * @return
	 */
	String interpolate(Object input);

}
