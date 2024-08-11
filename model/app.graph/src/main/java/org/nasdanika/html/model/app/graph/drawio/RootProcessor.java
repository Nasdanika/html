package org.nasdanika.html.model.app.graph.drawio;

import java.util.Map;

import org.nasdanika.drawio.Layer;
import org.nasdanika.graph.processor.ChildProcessors;

public class RootProcessor extends BaseProcessor {
	
	@ChildProcessors
	public Map<Layer, LayerProcessor> layerProcessors;

}
