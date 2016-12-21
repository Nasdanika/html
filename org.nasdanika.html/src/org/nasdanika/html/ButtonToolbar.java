package org.nasdanika.html;

public interface ButtonToolbar extends UIElement<ButtonToolbar> {
	
	/**
	 * Factory method.
	 * @param buttons
	 * @return
	 */
	ButtonGroup buttonGroup(Button... buttons);

	ButtonToolbar add(ButtonGroup... buttonGroups);
	
	boolean isEmpty();
	
}
