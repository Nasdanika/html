package org.nasdanika.html;

/**
 * Functional interface for producing/generating HTML.
 * @author Pavel Vlasov
 *
 */
public interface Producer {
	
	
	/**
	 * Produces content.
	 * @param indent Indent. 
	 * @return Content. 
	 */
	Object produce(int indent);

}
