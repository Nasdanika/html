package org.nasdanika.html.model.app.graph;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Supplier;
import org.nasdanika.html.model.app.Label;

public interface Processor extends Supplier<Collection<Label>> {
	
	/**
	 * Call for the root action, implementations shall propagate down the hierarchy and through action references.
	 * @param base
	 */
	void resolveURI(URI base);

}
