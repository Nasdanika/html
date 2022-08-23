package org.nasdanika.html.model.app.drawio;

import org.nasdanika.graph.processor.ProcessorConfig;

public abstract class ModelElementProcessor extends ElementProcessor {

	protected ModelElementProcessor(ResourceFactory resourceFactory, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, config);
	}

}
