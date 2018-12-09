package org.nasdanika.html;

/**
 * An interface for building JavaScript functions.
 * @author Pavel Vlasov
 *
 */
public interface Function {
	
	Function parameter(Object... param);

	Function code(Object... code);
	
	Function bind(Object... bind);

}
