package org.nasdanika.html.app;

/**
 * Describes object property, which may be the object itself.
 * Extracts property value from an object to display and also sets property value in the underlying object.
 * @author Pavel Vlasov
 *
 */
public interface PropertyDescriptor extends Label, Property, Categorized {
	
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
	 * Provider of actions available for this property. 
	 * @return
	 */
	default ActionProvider getActionProvider(Object obj) {
		return ActionProvider.EMPTY_ACTION_PROVIDER;
	}
	
}
