package org.nasdanika.html.model.app.drawio;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.drawio.Model;
import org.nasdanika.graph.processor.ProcessorConfig;

public class ModelProcessor extends ElementProcessor {

	public ModelProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor, Registry> config, URI baseURI) {
		super(resourceFactory, uri, config, baseURI);
	}
	
	@Override
	public Model getElement() {
		return (Model) super.getElement();
	}
	
}
