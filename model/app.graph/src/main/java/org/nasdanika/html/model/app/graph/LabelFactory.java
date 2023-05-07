package org.nasdanika.html.model.app.graph;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
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
	Label createLabel(ProgressMonitor progressMonitor);
	
	/**
	 * Creates a {@link Link} if possible or a {@link Label}.
	 * @return
	 */
	default Label createLink(ProgressMonitor progressMonitor) {
		return createLink(null, progressMonitor);
	}
	
	/**
	 * Creates a link with a relative URL de-resolved against a given path which is relative to the calling node's URI.   
	 * @param path
	 * @return
	 */
	Label createLink(String path, ProgressMonitor progressMonitor);
	
	/**
	 * Creates a link to an aspect (feature) of the object identified by the selector, which can be a predicate.   
	 * @param selector Aspect/feature key. 
	 * @param path A link is deresolved relative to the sub-path. It can be used by sub-actions. Path can be null for the main action or actions at the same URI level.
	 * @return A link or a lablel or null
	 */
	default Label createLink(Object selector, String path, ProgressMonitor progressMonitor) {
		return null;
	}
	
	/**
	 * Propagates caller URI.
	 * @param base
	 */
	void resolve(URI base, ProgressMonitor progressMonitor);
	
	Supplier<Collection<Label>> createLabelsSupplier();

}
