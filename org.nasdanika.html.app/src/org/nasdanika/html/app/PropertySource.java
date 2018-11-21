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
	
}
