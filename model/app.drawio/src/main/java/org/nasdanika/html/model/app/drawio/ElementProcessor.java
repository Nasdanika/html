package org.nasdanika.html.model.app.drawio;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * Base class for processors.
 * @author Pavel
 *
 */
public class ElementProcessor {
	
	protected ResourceFactory resourceFactory;
	protected ProcessorConfig<ElementProcessor> config;
	protected Map<Element, ProcessorInfo<ElementProcessor>> registry;
	protected URI resourceURI;
	protected URI baseURI;
	
	public ElementProcessor(ResourceFactory resourceFactory, URI resourceURI, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		this.resourceFactory = resourceFactory;
		this.resourceURI = resourceURI;
		this.config = config;		
		this.baseURI = baseURI;
	}
	
	public Element getElement() {
		return config.getElement();
	}
	
	/**
	 * Does nothing. Override if parent info is needed.
	 * @param parentInfo
	 */
	public void setParentInfo(ProcessorInfo<ElementProcessor> parentInfo) { }
	
	public void setRegistry(Map<Element, ProcessorInfo<ElementProcessor>> registry) {
		this.registry = registry;
	}
	
}
