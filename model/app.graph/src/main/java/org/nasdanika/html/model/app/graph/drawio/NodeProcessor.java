package org.nasdanika.html.model.app.graph.drawio;

import java.util.ArrayList;
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
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class NodeProcessor extends LinkTargetProcessor<Node> {
	
	public NodeProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}

	@ChildProcessors
	public Map<ModelElement, ProcessorInfo<WidgetFactory>> childInfos;
	
	@SuppressWarnings("resource")
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		MapCompoundSupplier<ModelElement, Collection<Label>> childLabelsSupplier = new MapCompoundSupplier<>("Child labels supplier");
		for (Entry<ModelElement, ProcessorInfo<WidgetFactory>> ce: childInfos.entrySet()) {
			childLabelsSupplier.put(ce.getKey(), ce.getValue().getProcessor().createLabelsSupplier());
		}
		
		return childLabelsSupplier.then(this::createNodeLabels);
	}
	
	@Override
	public void configureLabel(Label label) {
		super.configureLabel(label);
		
		// TODO - icon scaling
	}
	
	protected Collection<Label> createNodeLabels(Map<ModelElement, Collection<Label>> childLabels, ProgressMonitor progressMonitor) {
		List<Label> childNodesLabelsList = childLabels.entrySet()
			.stream()
			.filter(e -> e.getKey() instanceof Node)
			.sorted((a,b) -> compareModelElementsByLabel(a.getKey(), b.getKey()))
			.flatMap(e -> e.getValue().stream())
			.toList();		
		
		List<Action> childConnectionsActionList = childLabels.entrySet()
				.stream()
				.filter(e -> e.getKey() instanceof Connection)
				.sorted((a,b) -> compareModelElementsByLabel(a.getKey(), b.getKey()))
				.flatMap(e -> e.getValue().stream())
				.filter(Action.class::isInstance)
				.map(Action.class::cast)
				.toList();		
		
		String label = element.getLabel();
		if (Util.isBlank(label)) {
			List<Label> ret = new ArrayList<>(childNodesLabelsList);
			ret.addAll(childConnectionsActionList);
			return ret;
		}
		
		Collection<EObject> documentation = getDocumentation(progressMonitor);
		int childLabelsSum = childLabels.values()
				.stream()
				.mapToInt(Collection::size)
				.sum();
		
		if (documentation.isEmpty() && childLabelsSum == 0) {
			return Collections.emptyList();
		}
	
		Label mLabel = documentation.isEmpty() && childConnectionsActionList.isEmpty() ? AppFactory.eINSTANCE.createLabel() : AppFactory.eINSTANCE.createAction();
		String labelText = Jsoup.parse(label).text();
		mLabel.setText(labelText);
		mLabel.getChildren().addAll(childNodesLabelsList);
		configureLabel(mLabel);
				
		if (!documentation.isEmpty() ) {
			((Action) mLabel).getContent().addAll(documentation);
		}
		
		if (mLabel instanceof Action) {
			((Action) mLabel).getAnonymous().addAll(childConnectionsActionList);
			((Action) mLabel).setLocation(element.getId() + "/index.html");
		}		
		
		return Collections.singleton(mLabel);			
	}		
	
}
