package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Markup;
import org.nasdanika.html.Tag;

public interface Breadcrumbs extends BootstrapElement<Tag>, Markup {
	
	boolean isEmpty();
	
	/**
	 * Adds a breadcrumb item. href can be null - in this case the item is marked as active (last item).
	 * @param href
	 * @param content
	 * @return
	 */
	Breadcrumbs item(Object href, Object... content);

}
