package org.nasdanika.html.app;

import java.util.List;
import java.util.Map;

/**
 * Delegate for action execution and property source operations.
 * @author Pavel Vlasov
 *
 */
public interface Delegate {
	
	// --- Action ---
	
	/**
	 * Executes action.
	 * @param action
	 * @return
	 */
	Object execute(Action action);
		
	// --- Property Source ---
	
	Object getVersion(PropertySource propertySource, Object obj);
	
	List<Action> getActions(PropertySource propertySource, Object obj);
	
	Diagnostic update(PropertySource propertySource, Object obj, Object version, List<Delta> deltas);

	Object getValue(SingleValuePropertySource propertySource);	
	
	List<Object> getValues(MultiValuePropertySource propertySource);
	
	List<Object> getValues(MultiValuePropertySource propertySource, Map<Object, String> filter, Map<Object, Boolean> sort);	

}
