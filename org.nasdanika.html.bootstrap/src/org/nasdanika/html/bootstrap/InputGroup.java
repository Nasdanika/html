package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Markup;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.Tag;

public interface InputGroup extends BootstrapElement<Tag>, Markup {
	
	InputGroup prepend(Object content);
	
	InputGroup input(InputBase<?> inputs);
	
	InputGroup append(Object content);
	
	InputGroup large();
	InputGroup large(boolean large);
	
	InputGroup small();
	InputGroup small(boolean small);	

}
