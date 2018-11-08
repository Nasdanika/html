package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Markup;
import org.nasdanika.html.Tag;

public interface ButtonToolbar extends BootstrapElement<Tag>, Markup {
	
	ButtonToolbar add(ButtonGroup buttonGroup);

}
