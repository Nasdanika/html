package org.nasdanika.html.app;

import java.util.List;
import java.util.Map;

/**
 * Source of multiple values organized in pages.
 * Multiple values may be rendered as a list if there is a single property descriptor or as a table if there are many.
 * @author Pavel Vlasov
 *
 */
public interface PagedPropertySource extends PropertySource {
	
	interface Pages {
		
		/**
		 * @return Number of pages or -1 if unknown.
		 */
		int size();
		
		/**
		 * Returns a specified page
		 * @param idx zero-based page index.
		 * @return
		 */
		List<Object> getPage(int idx);
	}
	
	Pages getPages();
	
	/**
	 * Filtred and sorted values organized in pages.
	 * @param filter
	 * @param sort Sort ascending if false, i.e. 1, 2, 3, or descending is true, i.e. 3, 2, 1.
	 * @return
	 */
	Pages getPages(Map<PropertyDescriptor, String> filter, Map<PropertyDescriptor, Boolean> sort);

}
