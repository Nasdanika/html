package org.nasdanika.html;

/**
 * A container of named items, e.g. sections with headers or Bootstrap tabs/pills. 
 * @author Pavel Vlasov
 *
 * @param <I>
 */
public interface NamedItemsContainer<I> {

	/**
	 */
	void item(Object name, Object content);
	
	boolean isEmpty();
	
}