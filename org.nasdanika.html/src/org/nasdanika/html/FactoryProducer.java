package org.nasdanika.html;

/**
 * Functional interface for producing/generating HTML.
 * @author Pavel Vlasov
 *
 */
public interface FactoryProducer {
	
	/**
	 * Adapter to Producer. 
	 * @author Pavel Vlasov
	 *
	 */
	interface Adapter {
		
		FactoryProducer asFactoryProducer(Object obj);
		
	}
	
	/**
	 * Produces content.
	 * @return Content.
	 */
	Object produce(HTMLFactory factory, int indent);

}
