package org.nasdanika.html.app;

import java.util.List;

/**
 * Looks up choices based on the filter.
 * @author Pavel Vlasov
 *
 */
public interface LookupChoiceProvider {
	
	List<Choice> getChoices(String filter);

}
