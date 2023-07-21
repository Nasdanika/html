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
	protected ProcessorConfig config;
	protected Map<Element,ProcessorConfig> configs; // Processor records?
	protected URI resourceURI;
	protected URI baseURI;
	
	public ElementProcessor(ResourceFactory resourceFactory, URI resourceURI, ProcessorConfig config, URI baseURI) {
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
	
	public void setRegistry(Map<Element,ProcessorConfig> configs) {
		this.configs = configs; // Processor records?
	}
	
}
