package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.jsoup.Jsoup;
import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class LayerProcessor extends BaseProcessor<Layer> {
	
	public LayerProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}

	@ChildProcessors
	public Map<LayerElement, ProcessorInfo<WidgetFactory>> childInfos;
		
	@SuppressWarnings("resource")
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		MapCompoundSupplier<LayerElement, Collection<Label>> childLabelsSupplier = new MapCompoundSupplier<>("Child labels supplier");
		for (Entry<LayerElement, ProcessorInfo<WidgetFactory>> ce: childInfos.entrySet()) {
			childLabelsSupplier.put(ce.getKey(), ce.getValue().getProcessor().createLabelsSupplier());
		}
		
		return childLabelsSupplier.then(this::createLayerLabels);
	}
	
	protected Collection<Label> createLayerLabels(Map<LayerElement, Collection<Label>> childLabels, ProgressMonitor progressMonitor) {
		List<Label> childLabelsList = childLabels.entrySet()
			.stream()
			.filter(e -> !e.getKey().isTargetLink() || e.getKey().getLinkTarget() instanceof Page)
			.sorted((a,b) -> compareModelElementsByLabel(a.getKey(), b.getKey()))
			.flatMap(e -> e.getValue().stream())
			.toList();		
				
		String label = element.getLabel();
		if (Util.isBlank(label)) {
			return childLabelsList;
		}
		
		Collection<EObject> documentation = getDocumentation(progressMonitor);
		int childLabelsSum = childLabels.values()
				.stream()
				.mapToInt(Collection::size)
				.sum();
		
		if (documentation.isEmpty() && childLabelsSum == 0) {
			return Collections.emptyList();
		}

		Label mLabel = documentation.isEmpty() ? AppFactory.eINSTANCE.createLabel() : AppFactory.eINSTANCE.createAction();
		mLabel.setText(Jsoup.parse(label).text());
		mLabel.getChildren().addAll(childLabelsList);
		configureLabel(mLabel);
				
		if (!documentation.isEmpty() ) {
			((Action) mLabel).getContent().addAll(documentation);
		}
		
		if (mLabel instanceof Action) {
			((Action) mLabel).setLocation(element.getId() + "/index.html");
		}		
		
		return Collections.singleton(mLabel);			
	}	

}
