package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class RootProcessor extends BaseProcessor<Root> {
	
	public RootProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}

	@ChildProcessors
	public Map<Layer, ProcessorInfo<WidgetFactory>> layerProcessorInfos;
	
	@SuppressWarnings("resource")
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		MapCompoundSupplier<Layer, Collection<Label>> childLabelsSupplier = new MapCompoundSupplier<>("Layer labels supplier");
		for (Entry<Layer, ProcessorInfo<WidgetFactory>> ce: layerProcessorInfos.entrySet()) {
			childLabelsSupplier.put(ce.getKey(), ce.getValue().getProcessor().createLabelsSupplier());
		}
		
		return childLabelsSupplier.then(this::createRootLabels);
	}
	
	protected Collection<Label> createRootLabels(Map<Layer, Collection<Label>> childLabels, ProgressMonitor progressMonitor) {
		return childLabels.entrySet()
			.stream()
			.sorted((a,b) -> compareModelElementsByLabel(a.getKey(), b.getKey()))
			.flatMap(e -> e.getValue().stream())
			.toList();
	}
	
	@Override
	public void resolve(URI base, ProgressMonitor progressMonitor) {
		super.resolve(base, progressMonitor);
		for (ProcessorInfo<WidgetFactory> cpi: layerProcessorInfos.values()) {
			cpi.getProcessor().resolve(base, progressMonitor);
		}
	}

}
