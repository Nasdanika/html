package org.nasdanika.html.app;

/**
 * An interface to delegate UI generation.
 * @author Pavel Vlasov
 *
 */
public interface ViewPart {
	
	Object generate(ViewGenerator viewGenerator);

}
