package org.nasdanika.html;

import java.util.Map;
import java.util.Map.Entry;

/**
 * A container of named items, e.g. sections with headers or Bootstrap tabs/pills. 
 * @author Pavel Vlasov
 *
 * @param <I>
 */
public interface NamedItemsContainer {

	void item(Object name, Object content);
	
	boolean isEmpty();
	
	default void load(Iterable<Map.Entry<Object, Object>> items) {
		for (Entry<Object, Object> item: items) {
			item(item.getKey(), item.getValue());
		}
	}
	
	default void load(Map<Object, Object> items) {
		load(items.entrySet());
	}
	
}