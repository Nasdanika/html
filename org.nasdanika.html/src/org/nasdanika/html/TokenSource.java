package org.nasdanika.html;

/**
 * Source of token values for interpolation.
 * @author Pavel Vlasov.
 *
 */
public interface TokenSource {
	
	Object get(String token);
	
}