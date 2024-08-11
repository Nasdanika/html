package org.nasdanika.html.model.app.graph.drawio;

import java.util.Map;

import org.nasdanika.drawio.LayerElement;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class LayerProcessor extends BaseProcessor {
	
	@ChildProcessors
	public Map<LayerElement, WidgetFactory> elementProcessors;

}
