package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface ListGroup extends BootstrapElement<Tag> {
	
	Tag item(Object content, Bootstrap.Style style);

	int length();
	
	boolean isEmpty();

}
