package org.nasdanika.html.model.app.graph.drawio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.jsoup.Jsoup;
import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.OutgoingEndpoints;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class NodeProcessor extends LinkTargetProcessor<Node> {
		
	private static final String DATA_URI_PNG_PREFIX_NO_BASE_64 = "data:image/png,";
	private static final String DATA_URI_JPEG_PREFIX_NO_BASE_64 = "data:image/jpeg,";	
	
	public NodeProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}

	@ChildProcessors
	public Map<ModelElement, ProcessorInfo<WidgetFactory>> childInfos;
	
	@OutgoingEndpoints
	public Map<Connection, CompletableFuture<ConnectionProcessor>> outgoingEndpoints;
	
	@SuppressWarnings("resource")
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		MapCompoundSupplier<ModelElement, Collection<Label>> childLabelsSupplier = new MapCompoundSupplier<>("Child labels supplier");
		for (Entry<ModelElement, ProcessorInfo<WidgetFactory>> ce: childInfos.entrySet()) {
			ModelElement child = ce.getKey();
			if (child instanceof Connection && ((Connection) child).getSource() != null) {
				continue;
			}
			childLabelsSupplier.put(child, ce.getValue().getProcessor().createLabelsSupplier());
		}
		MapCompoundSupplier<Connection, Collection<Label>> outgoingConnectionsLabelsSupplier = new MapCompoundSupplier<>("Outgoing connections labels supplier");
		for (Entry<Connection, CompletableFuture<ConnectionProcessor>> ce: outgoingEndpoints.entrySet()) {
			outgoingConnectionsLabelsSupplier.put(ce.getKey(), ce.getValue().join().createLabelsSupplier());
		}
						
		return childLabelsSupplier
				.then(outgoingConnectionsLabelsSupplier.asFunction(this::logicalChildrenLabels))
				.then(this::createNodeLabels);
	}
	
	protected Map<ModelElement, Collection<Label>> logicalChildrenLabels(
			Map<ModelElement, Collection<Label>> childLabels, 
			Map<Connection,Collection<Label>> connectionLabels) {
		
		Map<ModelElement, Collection<Label>> ret = new HashMap<>(childLabels);
		ret.putAll(connectionLabels);
		return ret;
	}
	
	
	@Override
	public void configureLabel(Label label, ProgressMonitor progressMonitor) {
		super.configureLabel(label, progressMonitor);
		
		Map<String, String> style = element.getStyle();
		String image = style.get("image");
		if (!Util.isBlank(image)) {
			// Drawio does not add ;base64 to the image URL, browsers don't understand. Fixing it here.
			if (image.startsWith(DATA_URI_PNG_PREFIX_NO_BASE_64)) {
				int insertIdx = DATA_URI_PNG_PREFIX_NO_BASE_64.length() - 1;
				image = image.substring(0, insertIdx) + ";base64" + image.substring(insertIdx);
			} else if (image.startsWith(DATA_URI_JPEG_PREFIX_NO_BASE_64)) {
				int insertIdx = DATA_URI_JPEG_PREFIX_NO_BASE_64.length() - 1;
				image = image.substring(0, insertIdx) + ";base64" + image.substring(insertIdx);
			} else {
				URI imageURI = URI.createURI(image);
				if (imageURI.isRelative()) {
					URI appBase = factory.getAppBase();
					if (appBase != null && !appBase.isRelative()) {
						imageURI = imageURI.resolve(appBase);
					}
				}
				image = imageURI.toString();				
			}
			
			try {
				label.setIcon(Util.scaleImage(factory.rewriteImage(image, progressMonitor), factory.getIconSize()));
			} catch (IOException e) {
				progressMonitor.worked(Status.WARNING, 1, "Could not scale image: " + e, e);
			}
		}
	}
	
	protected boolean isLogicalChildConnection(ModelElement modelElement) {
		if (modelElement instanceof Connection) {
			Node source = ((Connection) modelElement).getSource();
			if (source != null) {
				return source == element;
			}
		}
		return element == modelElement.getParent();
	}
	
	protected Collection<Label> createNodeLabels(
			Map<ModelElement, Collection<Label>> logicalChildLabels, 
			ProgressMonitor progressMonitor) {
		
		List<Label> childNodesLabelsList = logicalChildLabels.entrySet()
			.stream()
			.filter(e -> e.getKey() instanceof Node)
			.sorted((a,b) -> compareModelElementsByLabel(a.getKey(), b.getKey()))
			.flatMap(e -> e.getValue().stream())
			.toList();		
		
		List<Action> childConnectionsActionList = logicalChildLabels.entrySet()
				.stream()
				.filter(e -> isLogicalChildConnection(e.getKey()))
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
		int childLabelsSum = logicalChildLabels.values()
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
		configureLabel(mLabel, progressMonitor);
				
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
