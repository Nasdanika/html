package org.nasdanika.html.app;

import java.util.List;

import org.nasdanika.html.InputType;

/**
 * Describes object property, which may be the object itself.
 * Extracts property value from an object to display and also sets property value in the underlying object.
 * @author Pavel Vlasov
 *
 */
public interface PropertyDescriptor extends Label, Property, Categorized {
			
	/**
	 * Property input type. If property descriptor implements {@link ChoiceProvider} or {@link LookupChoiceProvider} then 
	 * radio input type indicates that choices shall be rendered as a list/tree with radio buttons for choice selection.
	 * Checkbox input type indicates the same for multi-value properties.
	 * @return
	 */
	InputType getInputType();
	
	/**
	 * If true, {@link MultiValuePropertySource} values may be sorted based on this descriptor value.
	 * @return
	 */
	boolean isSortable();
	
	/**
	 * If true, {@link MultiValuePropertySource} values may be filtered based on this descriptor filter.
	 * @return
	 */
	boolean isFilterable();
		
	/**
	 * Actions available for this property for this value.
	 * @return
	 */
	List<Action> getActions(Object obj);	
		
}
