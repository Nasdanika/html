package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.Supplier;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class NodeProcessor extends LinkTargetProcessor<Node> {
	
	@ChildProcessors
	public Map<Element, ProcessorInfo<WidgetFactory>> childInfos;
	
	@SuppressWarnings("resource")
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		MapCompoundSupplier<Element, Collection<Label>> childLabelsSupplier = new MapCompoundSupplier<>("Child labels supplier");
		for (Entry<Element, ProcessorInfo<WidgetFactory>> ce: childInfos.entrySet()) {
			childLabelsSupplier.put(ce.getKey(), ce.getValue().getProcessor().createLabelsSupplier());
		}
		
		return childLabelsSupplier.then(this::createLabelsSupplier);
	}
	
	protected Collection<Label> createLabelsSupplier(Map<Element, Collection<Label>> childLabels) {
		throw new UnsupportedOperationException("Label, action, or pass-through");
	}

}
