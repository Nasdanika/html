package org.nasdanika.html.bootstrap;

import org.nasdanika.html.BlockElement;
import org.nasdanika.html.Tag;

public interface ButtonToolbar extends BootstrapElement<Tag>, BlockElement {
	
	ButtonToolbar add(ButtonGroup buttonGroup);

}
