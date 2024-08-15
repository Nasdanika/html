package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.Supplier;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class RootProcessor extends BaseProcessor<Root> {
	
	@ChildProcessors
	public Map<Layer, ProcessorInfo<WidgetFactory>> layerProcessorInfos;
	
	@SuppressWarnings("resource")
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		MapCompoundSupplier<Layer, Collection<Label>> childLabelsSupplier = new MapCompoundSupplier<>("Layer labels supplier");
		for (Entry<Layer, ProcessorInfo<WidgetFactory>> ce: layerProcessorInfos.entrySet()) {
			childLabelsSupplier.put(ce.getKey(), ce.getValue().getProcessor().createLabelsSupplier());
		}
		
		return childLabelsSupplier.then(this::createLabelsSupplier);
	}
	
	protected Collection<Label> createLabelsSupplier(Map<Layer, Collection<Label>> childLabels) {
		throw new UnsupportedOperationException("Label, action, or pass-through");
	}

}
