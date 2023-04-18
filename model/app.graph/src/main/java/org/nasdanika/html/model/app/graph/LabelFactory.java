package org.nasdanika.html.model.app.graph;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Supplier;
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
	default Label createLink() {
		return createLink(null);
	}
	
	/**
	 * Creates a link with a relative URL de-resolved against a given path which is relative to the calling node's URI.   
	 * @param path
	 * @return
	 */
	Label createLink(String path);
	
	/**
	 * Propagates caller URI.
	 * @param base
	 */
	void resolve(URI base);
	
	Supplier<Collection<Label>> createLabelsSupplier();

}
