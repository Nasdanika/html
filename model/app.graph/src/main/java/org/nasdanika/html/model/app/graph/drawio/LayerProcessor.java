package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class LayerProcessor extends BaseProcessor<Layer> {
	
	@ChildProcessors
	public Map<LayerElement, ProcessorInfo<WidgetFactory>> childInfos;
		
	@SuppressWarnings("resource")
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		MapCompoundSupplier<LayerElement, Collection<Label>> childLabelsSupplier = new MapCompoundSupplier<>("Child labels supplier");
		for (Entry<LayerElement, ProcessorInfo<WidgetFactory>> ce: childInfos.entrySet()) {
			childLabelsSupplier.put(ce.getKey(), ce.getValue().getProcessor().createLabelsSupplier());
		}
		
		return childLabelsSupplier.then(this::createLabelsSupplier);
	}
	
	protected Collection<Label> createLabelsSupplier(Map<LayerElement, Collection<Label>> childLabels) {
		List<Label> childLabelsList = childLabels.values().stream().flatMap(Collection::stream).toList();
		String label = element.getLabel();
		if (Util.isBlank(label)) {
			return childLabelsList;
		}
		
		// TODO - documentation, return only if either documented or has children
		
		Label mLabel = AppFactory.eINSTANCE.createLabel();
		mLabel.setText(Jsoup.parse(label).text());
		mLabel.getChildren().addAll(childLabelsList);
		return Collections.singleton(mLabel);
	}	

}
