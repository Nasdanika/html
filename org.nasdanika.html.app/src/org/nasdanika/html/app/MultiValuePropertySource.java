package org.nasdanika.html.app;

import java.util.List;
import java.util.Map;

/**
 * Source of multiple values.
 * Multiple values may be rendered as a list if there is a single property descriptor or as a table if there are many.
 * @author Pavel Vlasov
 *
 */
public interface MultiValuePropertySource extends PropertySource {
	
	/**
	 * Unfiltered values in natural order.
	 * @return
	 */
	List<Object> getValues();
	
	/**
	 * Filtred and sorted values.
	 * @param filter
	 * @param sort Sort ascending if false, i.e. 1, 2, 3, or descending is true, i.e. 3, 2, 1.
	 * @return
	 */
	List<Object> getValues(Map<PropertyDescriptor, String> filter, Map<PropertyDescriptor, Boolean> sort);

}
