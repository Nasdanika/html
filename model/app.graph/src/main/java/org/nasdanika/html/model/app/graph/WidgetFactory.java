package org.nasdanika.html.model.app.graph;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.emf.EClassConnection;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.emf.EObjectNodeProcessor;

/**
 * Creates labels, links for cross-referencing, and other "widgets".
 * @author Pavel
 *
 */
public interface WidgetFactory {
	
	/**
	 * Functional interface for selectors to which factories delegate widget creation.
	 * {@link Connection} {@link WidgetFactory} shall not delegate to Selector, but rather to ConnectionSelector 
	 */
	interface Selector<T> {
	
		/**
		 * Retrieves some information from the argument widget factory, e.g. creates a widget.   
		 * @param widgetFactory
		 * @param base For connections, if not null, is resolved against the calling end URI - source or target, if it is not null. 
		 * If null, the respective end URI is used as the base.
		 * @param progressMonitor
		 * @return A widget or null
		 */		
		T select(WidgetFactory widgetFactory, URI base, ProgressMonitor progressMonitor); 
		
	}
	
	/**
	 * A maker interface indicating that {@link Connection} {@link WidgetFactory} shall delegate to this selector instead of passing it to the other end's node.
	 * @param <T>
	 */
	interface ConnectionSelector<T> extends Selector<T> {
		
	}
	
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
	 * Creates a help decorator.
	 * Calls createHelpDecorator(null, progressMonitor) 
	 * @return
	 */
	default Label createHelpDecorator(ProgressMonitor progressMonitor) {
		return createHelpDecorator(null, progressMonitor);
	}
	
	/**
	 * Creates a help decorator with URL's deresolved (relativized) against the provided base URI.
	 * For example, an {@link EObjectNodeProcessor} for an object of type X would call its {@link EClassConnection} outgoing connection to create a help decorator. 
	 * The EClassConnection would in turn call EClassNodeProcessor for {@link EClass} X to generate context help decorator.  
	 * 
	 * @param base For connections, if not null, is resolved against the calling end URI - source or target, if it is not null. 
	 * If null, the respective end URI is used as the base.
	 * @param progressMonitor
	 * @return
	 */
	Label createHelpDecorator(URI base, ProgressMonitor progressMonitor);
	
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
	default Object select(Object selector, ProgressMonitor progressMonitor) {
		return select(selector, null, progressMonitor);
	}

	/**
	 * Creates a widget for an aspect (feature) of the object identified by the selector, which can be a {@link Selector}.   
	 * @param selector Aspect/feature key. If an instasnce of Selector, then the factory delegates widget creation to the selector passing itself as an argument.
	 * @param base For connections, if not null, is resolved against the calling end URI - source or target, if it is not null. 
	 * If null, the respective end URI is used as the base.
	 * @param progressMonitor
	 * @return A widget or null
	 */
	default Object select(Object selector, URI base, ProgressMonitor progressMonitor) {
		if (selector instanceof Selector) {
			return select((Selector<?>) selector, base, progressMonitor);
		}
		return null;
	}
	
	default <T> T select(Selector<T> selector, URI base, ProgressMonitor progressMonitor) {
		return selector.select(this, base, progressMonitor);
	}	
	
	default <T> T select(Selector<T> selector, ProgressMonitor progressMonitor) {
		return select(selector, null, progressMonitor);
	}	

	/**
	 * Calls createWidgetString(selector, null, progressMonitor)
	 * @param selector
	 * @param progressMonitor
	 * @return
	 */
	default String selectString(Object selector, ProgressMonitor progressMonitor) {
		return selectString(selector, null, progressMonitor);
	}

	/**
	 * @param selector
	 * @param base
	 * @param progressMonitor
	 * @return widget rendered to (HTML) string
	 */
	String selectString(Object selector, URI base, ProgressMonitor progressMonitor);
	
	/**
	 * Used to establish node URI's. Propagates caller URI.
	 * @param base
	 */
	void resolve(URI base, ProgressMonitor progressMonitor);
	
	Supplier<Collection<Label>> createLabelsSupplier();

}
