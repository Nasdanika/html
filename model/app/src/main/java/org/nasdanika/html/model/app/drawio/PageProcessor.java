package org.nasdanika.html.model.app.drawio;

import org.nasdanika.graph.processor.ProcessorConfig;

/**
 * Possible page types: actions, diagram, contents.
 * Action is null if there is page element or the type is not actions. 
 * For type page the action 
 * @author Pavel
 *
 */
public class PageProcessor extends ElementProcessor {

	public PageProcessor(ResourceFactory resourceFactory, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, config);
	}

}
