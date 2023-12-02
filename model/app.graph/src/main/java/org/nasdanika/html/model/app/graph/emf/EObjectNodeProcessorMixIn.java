package org.nasdanika.html.model.app.graph.emf;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

/**
 * Base interface for {@link EObjectNodeProcessor} mix-ins to support multiple inheritance
 * without duplication.
 */
public interface EObjectNodeProcessorMixIn<T> {
	
	/**
	 * Configures a label for a given source object. This method allows multiple widget factories 
	 * collaborate on label/action configuration. E.g. the primary WidgetFactory may delegate to "facet" factories.  
	 * @param source
	 * @param label
	 * @param progressMonitor
	 */
	default void configureLabel(Object source, Label label, ProgressMonitor progressMonitor) {
		
	}
	
	NodeProcessorConfig<WidgetFactory, WidgetFactory> getConfig();
	
	Context getContext();
	
	public EObjectNode getNode();
	
	public T getTarget();

	public URI getUri();
	
}
