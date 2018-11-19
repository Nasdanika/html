package org.nasdanika.html;

/**
 * Mutable token source with ability to add tokens in a fluent fashion. 
 * @author Pavel Vlasov
 *
 */
public interface MutableTokenSource extends TokenSource {
	
	/**
	 * Adds a new token. Null values are ignored.
	 * @param name
	 * @param value
	 * @return
	 */
	MutableTokenSource put(String token, Object value);

}
