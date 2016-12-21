package org.nasdanika.html;

public interface ListGroup extends UIElement<ListGroup> {
	
	Tag item(Object content, Bootstrap.Style style);

	int length();

}
