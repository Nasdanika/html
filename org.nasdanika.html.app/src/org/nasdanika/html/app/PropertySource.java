package org.nasdanika.html.app;

import java.util.List;

/**
 * Source of properties. 
 * @author Pavel Vlasov
 *
 */
public interface PropertySource extends Label, DataSource {
	
	/**
	 * Property descriptors.
	 * @return
	 */
	List<PropertyDescriptor> getPropertyDescriptors();
	
	/**
	 * Actions which can be performed on this property source. Typically are rendered below
	 * the property source view. For a single-value property source object/value actions and property source actions are typically rendered together in 
	 * the same button toolbar.
	 * @return
	 */
	List<Action> getActions();
	
}
