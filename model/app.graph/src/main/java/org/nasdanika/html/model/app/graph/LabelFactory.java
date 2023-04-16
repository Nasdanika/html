package org.nasdanika.html.model.app.graph;

import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;

/**
 * Creates labels and links for cross-referencing.
 * @author Pavel
 *
 */
public interface LabelFactory {
	
	/**
	 * Creates a {@link Label}.
	 * @return
	 */
	Label createLabel();
	
	/**
	 * Creates a {@link Link} if possible or a {@link Label}.
	 * @return
	 */
	Label createLink();

}
