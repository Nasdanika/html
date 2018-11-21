package org.nasdanika.html.app;

import java.util.List;

import org.nasdanika.html.InputType;

/**
 * Describes object property, which may be the object itself.
 * Extracts property value from an object to display and also sets property value in the underlying object.
 * @author Pavel Vlasov
 *
 */
public interface PropertyDescriptor extends Label {
	
	/**
	 * Property name/id to use as input name. Unique in the containing property set.
	 * @return
	 */
	String getPropertyName();
	
	/**
	 * @param obj Value object for single value property sources and collection element for multi-value property sources.
	 * @return Property value rendered to display in the UI.
	 */
	Object getDisplayValue(Object obj); 
	
	/**
	 * Actions available for this property.
	 * @return
	 */
	List<Action> getActions();
	
	/**
	 * Property value to use in input "value" attribute.  
	 * @param obj Value object for single value property sources and collection element for multi-value property sources.
	 * @return
	 */
	Object getEditValue(Object obj);
	
	/**
	 * Property input type. If property descriptor implements {@link ChoiceProvider} or {@link LookupChoiceProvider} then 
	 * radio input type indicates that choices shall be rendered as a list/tree with radio buttons for choice selection.
	 * Checkbox input type indicates the same for multi-value properties.
	 * @return
	 */
	InputType getInputType();
	
	/**
	 * Property category. Properties in the same category may be rendered together in some container such as a card,
	 * tab, fieldset. Label.getId() is used for grouping by.
	 * @return
	 */
	Label getCategory();
	
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
	
	// TODO - setting value, validation...

}
