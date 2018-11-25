package org.nasdanika.html.app;

import java.util.List;

/**
 * Base class for property descriptors and their delegates. Deals with data binding - retrieving and setting values.
 * @author Pavel Vlasov
 *
 */
public interface Property {
		
	/**
	 * @param obj Value object for single value property sources and collection element for multi-value property sources.
	 * @return Property value rendered to display in the UI.
	 */
	Object getDisplayValue(Object obj); 
	
	/**
	 * Actions available for this property for this value.
	 * @return
	 */
	List<Action> getActions(Object obj);
	
	/**
	 * Property value to use in input "value" attribute.  
	 * @param obj Value object for single value property sources and collection element for multi-value property sources.
	 * @return
	 */
	Object getEditValue(Object obj);
		
	/**
	 * @return true if property can be edited. If this method returns false then the property is rendered for view in 
	 * edit forms by using getDisplayValue().
	 */
	boolean isEditable(Object obj);
	
	// TODO - setting value, validation...
	
	/**
	 * Updates property value. This method is typically invoked by the containing property source
	 * and diagnostic is added as a child to the parent diagnostic.
	 * @param obj
	 * @param originalValue
	 * @param newValue
	 * @return
	 */
	Diagnostic update(Object obj, Object originalValue, Object newValue);

}
