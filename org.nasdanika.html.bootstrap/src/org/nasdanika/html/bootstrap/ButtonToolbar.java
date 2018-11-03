package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface ButtonToolbar extends BootstrapElement<Tag> {
	
	/**
	 * Factory method.
	 * @param buttons
	 * @return
	 */
	ButtonGroup buttonGroup(Button... buttons);

	ButtonToolbar add(ButtonGroup... buttonGroups);
	
	boolean isEmpty();
	
}
