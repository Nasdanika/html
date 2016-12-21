package org.nasdanika.html;

/**
 * Functional interface for producing/generating HTML.
 * @author Pavel Vlasov
 *
 */
public interface Producer {
	
	/**
	 * Adapter to Producer. 
	 * @author Pavel Vlasov
	 *
	 */
	interface Adapter {
		
		Producer asProducer(Object obj);
		
	}
	
	/**
	 * Produces content.
	 * @param indent Indent. 
	 * @return Content. 
	 */
	Object produce(int indent);

}
