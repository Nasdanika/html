package org.nasdanika.html;

public interface Select extends InputBase<Select> {
	
	interface OptionGroup extends HTMLElement<OptionGroup> {
		
		OptionGroup disabled(boolean disabled);
		
		OptionGroup disabled();
		
		Tag option(String value, String label, boolean selected, boolean disabled);

		/**
		 * Advanced option creation
		 * @param value
		 * @param selected
		 * @param disabled
		 * @param content Content (label)
		 * @return option tag
		 */
		Tag option(String value, boolean selected, boolean disabled, Object... content);
		
	}
	
	OptionGroup optionGroup(Object label);
	
	/**
	 * Simple option creation
	 * @param value
	 * @param label
	 * @param selected
	 * @param disabled
	 * @return Select input
	 */
	Tag option(String value, String label, boolean selected, boolean disabled);

	/**
	 * Advanced option creation
	 * @param value
	 * @param selected
	 * @param disabled
	 * @param content Content (label)
	 * @return option tag
	 */
	Tag option(String value, boolean selected, boolean disabled, Object... content);
	
	
	Select multiple();
	Select multiple(boolean multiple);
	
	Select size(int size);

}
