package org.nasdanika.html.model.app.drawio;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.drawio.Layer;
import org.nasdanika.graph.processor.ProcessorConfig;

public class LayerProcessor extends ModelElementProcessor {

	public LayerProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
	}
	
	@Override
	public Layer getElement() {
		return (Layer) super.getElement();
	}

}
