package org.nasdanika.html;

public interface ButtonGroup extends UIElement<ButtonGroup> {
	
	/**
	 * Factory method, creates a button.
	 * @param content
	 * @return
	 */
	Button button(Object... content);
	
	ButtonGroup add(Button... buttons);
	
	boolean isEmpty();
		
	ButtonGroup size(Bootstrap.Size size);
	
	ButtonGroup vertical();
	
	ButtonGroup vertical(boolean vertical);
	
	ButtonGroup justified();
	
	ButtonGroup justified(boolean justified);

}
