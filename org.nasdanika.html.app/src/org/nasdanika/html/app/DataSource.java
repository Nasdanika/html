package org.nasdanika.html.app;

import java.util.List;

/**
 * Base interface for PropertySource and delegates/adapters.
 * Contains value-specific methods.
 * @author Pavel Vlasov
 *
 */
public interface DataSource {
	
	/**
	 * Actions which can be performed on value. 
	 * @return
	 */
	List<Action> getActions(Object obj);
	
	/**
	 * Version of the object or null if not supported. 
	 * Versions can be used to detect concurrent modifications.
	 * @param obj
	 * @return
	 */
	Object getVersion(Object obj);
	
	/**
	 * Updates object with a list of deltas.
	 * @param obj Object to update.
	 * @param version Object version when original values were taken.
	 * @param deltas A list of changes in values.
	 * @return
	 */
	Diagnostic update(Object obj, Object version, List<Delta> deltas);	

}
