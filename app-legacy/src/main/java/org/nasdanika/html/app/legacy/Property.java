package org.nasdanika.html.app;

import java.util.List;

/**
 * Base class for property descriptors and their delegates. Deals with data binding - retrieving and setting values.
 * @author Pavel Vlasov
 *
 */
public interface Property {
		
	/**
	 * @param obj Value object for single value property sources and values collection element for multi-value property sources.
	 * @return Property value to be displayed in the UI or a {@link ViewPart}. For example, if value is a reference to another
	 * object it might be wrapped into a view {@link Action} and the method may return a ViewPart which generates a link using one of {@link ViewGenerator}.link() methods.  
	 */
	Object getDisplayValue(Object obj);
	
	/**
	 * Creates an edit control.
	 * @param viewGenerator
	 * @param obj
	 * @return
	 */
	Object createEditControl(ViewGenerator viewGenerator, Object obj);
	
	List<Choice> getChoices(Object obj);
		
	/**
	 * @return true if property can be edited. If this method returns false then the property is rendered for view in 
	 * edit forms by using getDisplayValue().
	 */
	boolean isEditable(Object obj);
	
	// TODO - setting value, validation...
	
	/**
	 * Updates property value. This method is typically invoked by the containing property source update() method
	 * and the returned diagnostic is added as a child to the parent diagnostic.
	 * @param obj
	 * @param originalValue
	 * @param newValue
	 * @return
	 */
	Diagnostic update(Object obj, Object originalValue, Object newValue);
	
	/**
	 * Property name to use in controls. Typically scoped to the containing {@link PropertyDescriptor}, i.e. unique within the descriptor.
	 * @return
	 */
	String getPropertyName();

}
