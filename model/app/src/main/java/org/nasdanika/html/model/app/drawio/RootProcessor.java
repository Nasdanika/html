package org.nasdanika.html.model.app.drawio;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.graph.processor.ProcessorConfig;

public class RootProcessor extends ModelElementProcessor {

	public RootProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
	}
	
}
