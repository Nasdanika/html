package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface ButtonGroup extends BootstrapElement<Tag> {
	
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
