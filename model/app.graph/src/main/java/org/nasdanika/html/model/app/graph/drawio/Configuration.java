package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.graph.WidgetFactory;

/**
 * Base class for processor configuration/customization classes.
 */
public class Configuration {
	/**
	 * Icons size for UI generation - jsTree displays icons up to 24x24 pixels, leaving 4 pixes for padding
	 */
	public static final int ICON_SIZE = 20;
	/**
	 * Default base URI for the Drawio application to resolve library relative URL's.
	 */
	public static final URI DEFAULT_APP_BASE = URI.createURI("https://app.diagrams.net/");
	
	public static final String DOC_FORMAT_PROPERTY = "doc-format";
	public static final String DOC_REF_PROPERTY = "doc-ref";
	public static final String DOCUMENTATION_PROPERTY = "documentation";
	public static final String TITLE_PROPERTY = "title";
	public static final String ICON_PROPERTY = "icon";
	
	protected String getIconProperty() {
		return ICON_PROPERTY;
	}	
	
	protected String getTitleProperty() {
		return TITLE_PROPERTY;
	}	
		
	protected String getDocumentationProperty() {
		return DOCUMENTATION_PROPERTY;
	}	
		
	protected String getDocRefProperty() {
		return DOC_REF_PROPERTY;
	}	
	
	protected String getDocFormatProperty() {
		return DOC_FORMAT_PROPERTY; 
	}		
	
	/**
	 * Override to implement filtering of representation elements
	 * @param representationElement
	 * @param registry
	 * @param progressMonitor
	 */
	protected void filterRepresentationElement(
			ModelElement sourceElement,
			ModelElement representationElement,
			Map<org.nasdanika.drawio.Element, ProcessorInfo<WidgetFactory>> registry,
			ProgressMonitor progressMonitor) {
		
	}

	/**
	 * Override to customize viewer.
	 * @return
	 */
	protected String getViewer() {
		return DiagramGenerator.JSDELIVR_DRAWIO_VIEWER;
	}
	
	/**
	 * Application base for resolving relative image URL's. 
	 * This implementation returns DEFAULT_APP_BASE. 
	 * Override to customize for different (e.g. intranet) installations.
	 * App sources - https://github.com/jgraph/drawio/tree/dev/src/main/webapp.
	 * For the purposes of serving images and a diagram editor the web app can be deployed as a static site.
	 * It can also be deployed as a Docker container - https://www.drawio.com/blog/diagrams-docker-app, https://hub.docker.com/r/jgraph/drawio 
	 * @return
	 */
	protected URI getAppBase() {
		return DrawioProcessorFactory.DEFAULT_APP_BASE;
	}
	
	/**
	 * This implementation returns the argument. 
	 * Override to rewrite URL's before conversion to icons. For example, read representations from a file system and convert to data URL's.
	 * @param imageRepr
	 * @return
	 */
	protected String rewriteImage(String imageRepr, ProgressMonitor progressMonitor) {
		return imageRepr;
	}
	
	/**
	 * Icon size to scale image representations to
	 * @return
	 */
	protected int getIconSize() {
		return DrawioProcessorFactory.ICON_SIZE;
	}

	/**
	 * Override to create additional content from a representation (page).
	 * For example, aria for screen readers and AI explaining the diagrams.
	 * This implementation returns an empty collection.
	 * @param representation
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected Collection<? extends EObject> createRepresentationContent(
			Document representation,
			Map<org.nasdanika.drawio.Element, ProcessorInfo<WidgetFactory>> registry,
			ProgressMonitor progressMonitor) {
		return Collections.emptyList();
	}

}
