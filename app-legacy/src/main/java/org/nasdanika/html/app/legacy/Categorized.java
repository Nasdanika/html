package org.nasdanika.html.app;

/**
 * Something which can be grouped into categories.
 * Category is a label with ID as a grouping key.
 * 
 * @author Pavel Vlasov
 *
 */
public interface Categorized {
	
	Label getCategory();

}
