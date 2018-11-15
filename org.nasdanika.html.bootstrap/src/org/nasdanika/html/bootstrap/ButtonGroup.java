package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface ButtonGroup extends BootstrapElement<Tag,ButtonGroup> {
	
	ButtonGroup large();
	ButtonGroup large(boolean large);
	
	ButtonGroup small();
	ButtonGroup small(boolean small);
	
	ButtonGroup add(Button<?> button); 

	ButtonGroup add(Dropdown dropdown);

}
