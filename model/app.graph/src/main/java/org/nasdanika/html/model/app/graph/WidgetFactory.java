package org.nasdanika.html.model.app.graph;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.html.model.app.Label;

/**
 * Creates labels, links for cross-referencing, and other "widgets".
 * @author Pavel
 *
 */
public interface WidgetFactory {
	
	/**
	 * Creates a "label" which is an HTML/text representation of something which does not navigate to that something. E.g. {@link Label}. Can be composite.
	 * @return
	 */
	Object createLabel(ProgressMonitor progressMonitor);
	
	/**
	 * Creates a string (HTML text) representation of "label".
	 * @return
	 */
	String createLabelString(ProgressMonitor progressMonitor);
	
	/**
	 * Creates a link if possible or a label.
	 * Calls createLink(null, progressMonitor) 
	 * @return
	 */
	default Object createLink(ProgressMonitor progressMonitor) {
		return createLink(null, progressMonitor);
	}
	
	/**
	 * Creates a link with URL's deresolved (relativized) against the provided base URI.
	 * 
	 * @param base For connections, if not null, is resolved against the calling end URI - source or target, if it is not null. 
	 * If null, the respective end URI is used as the base.
	 * @param progressMonitor
	 * @return
	 */
	Object createLink(URI base, ProgressMonitor progressMonitor);
	
	/**
	 * Link rendered to String
	 * @param progressMonitor
	 * @return
	 */
	default String createLinkString(ProgressMonitor progressMonitor) {
		return createLinkString(null, progressMonitor);
	}
	
	/**
	 * Link rendered to String
	 * @param base
	 * @param progressMonitor
	 * @return
	 */
	String createLinkString(URI base, ProgressMonitor progressMonitor);
	
	/**
	 * Calls createWidget(selector, null, progressMonitor).
	 * For connections URL's in the link are deresolved (relativized) against the calling end URI - source or target, if it is not null.
	 * @param selector
	 * @param progressMonitor
	 * @return
	 */
	default Object createWidget(Object selector, ProgressMonitor progressMonitor) {
		return createWidget(selector, null, progressMonitor);
	}

	/**
	 * Creates a widget for an aspect (feature) of the object identified by the selector, which can be a function.   
	 * @param selector Aspect/feature key. 
	 * @param base For connections, if not null, is resolved against the calling end URI - source or target, if it is not null. 
	 * If null, the respective end URI is used as the base.
	 * @param progressMonitor
	 * @return A widget or null
	 */
	Object createWidget(Object selector, URI base, ProgressMonitor progressMonitor);

	/**
	 * Calls createWidgetString(selector, null, progressMonitor)
	 * @param selector
	 * @param progressMonitor
	 * @return
	 */
	default String createWidgetString(Object selector, ProgressMonitor progressMonitor) {
		return createWidgetString(selector, null, progressMonitor);
	}

	/**
	 * @param selector
	 * @param base
	 * @param progressMonitor
	 * @return widget rendered to (HTML) string
	 */
	String createWidgetString(Object selector, URI base, ProgressMonitor progressMonitor);
	
	/**
	 * Used to establish node URI's. Propagates caller URI.
	 * @param base
	 */
	void resolve(URI base, ProgressMonitor progressMonitor);
	
	Supplier<Collection<Label>> createLabelsSupplier();

}
