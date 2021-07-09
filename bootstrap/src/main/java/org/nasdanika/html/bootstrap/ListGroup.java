package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

/**
 * Interface for building <a href="https://getbootstrap.com/docs/4.5/components/list-group/">List groups</a> with UL and LI.
 * @author Pavel Vlasov
 *
 */
public interface ListGroup extends BootstrapElement<Tag, ListGroup> {
	
	Tag item(boolean active, boolean disabled, Color color, Object... content);	

	boolean isEmpty();
	
}
