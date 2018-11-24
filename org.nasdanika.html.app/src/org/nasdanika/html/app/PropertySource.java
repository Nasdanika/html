package org.nasdanika.html.app;

import java.util.List;

/**
 * Source of properties. 
 * @author Pavel Vlasov
 *
 */
public interface PropertySource extends Label {
	
	/**
	 * Property descriptors.
	 * @return
	 */
	List<PropertyDescriptor> getPropertyDescriptors();
	
	/**
	 * Actions which can be performed on this property source. Typically are rendered below
	 * the property source view.
	 * @return
	 */
	List<Action> getActions();
	
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
