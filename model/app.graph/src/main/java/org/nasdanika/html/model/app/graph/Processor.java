package org.nasdanika.html.model.app.graph;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;

public interface Processor {
	
	/**
	 * Call for the root action, implementations shall propagate down the hierarchy and through action references.
	 * @param base
	 */
	void resolveURI(URI base);

	/**
	 * Creates {@link Label}s and its subclasses - {@link Link}s and {@link Action}s for building an action model.
	 * Processors may pass labels created by children, e.g. {@link EReferenceConnection} processors. 
	 * @return
	 */
	Collection<Label> createLabels();

}
