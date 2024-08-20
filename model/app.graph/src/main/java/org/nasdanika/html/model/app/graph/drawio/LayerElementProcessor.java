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
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.jsoup.Jsoup;
import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.processor.NodeProcessorInfo;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class LayerElementProcessor<T extends LayerElement> extends LinkTargetProcessor<T> {
	
	protected Map<ModelElement, ProcessorInfo<WidgetFactory>> childInfos = new ConcurrentHashMap<>();
	
	protected Map<Connection, CompletableFuture<ConnectionProcessor>> outgoingEndpoints = new ConcurrentHashMap<>();	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addReferrer(ModelElement referrer) {
		super.addReferrer(referrer);		
		for (Element child: referrer.getChildren()) {
			if (child instanceof ModelElement) {
				ProcessorInfo<WidgetFactory> ci = registry.get(child);
				if (ci != null /* && ci.getProcessor() != null */) {
					childInfos.put((ModelElement) child, ci);
				}
			}
		}		
		
		ProcessorInfo<WidgetFactory> referrerInfo = registry.get(referrer);
		if (referrerInfo instanceof NodeProcessorInfo) {
			NodeProcessorInfo<WidgetFactory, WidgetFactory, WidgetFactory> npi = (NodeProcessorInfo<WidgetFactory, WidgetFactory, WidgetFactory>) referrerInfo;
			outgoingEndpoints.putAll((Map) npi.getOutgoingEndpoints());			
		}
	}
	
	public LayerElementProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}

	public Collection<ModelElement> referrers = new ArrayList<>();	

	/**
	 * Has documentation or has a page link (which implies having documentation)
	 */
	@Override
	public URI getActionURI(ProgressMonitor progressMonitor) {
		LinkTarget linkTarget = element.getLinkTarget();
		if (linkTarget instanceof Page) {
			ProcessorInfo<WidgetFactory> ppi = registry.get(linkTarget);
			if (ppi != null) {
				PageProcessor pageProcessor = (PageProcessor) ppi.getProcessor();
				if (pageProcessor != null) {
					return uri;
				}
			}
		}
		
		if (super.getDocumentation(progressMonitor).isEmpty()) {
			return null;
		}
		return uri;
	}	
	
	@Override
	public void resolve(URI base, ProgressMonitor progressMonitor) {
		super.resolve(base, progressMonitor);
		for (Entry<ModelElement, ProcessorInfo<WidgetFactory>> cpe: childInfos.entrySet()) {
			if (cpe.getKey() instanceof Node || isLogicalChildConnection(cpe.getKey())) {
				cpe.getValue().getProcessor().resolve(uri, progressMonitor);
			}
		}		
		for (Entry<Connection, CompletableFuture<ConnectionProcessor>> oe: outgoingEndpoints.entrySet()) {
			oe.getValue().thenAccept(cp -> cp.resolve(uri, progressMonitor));
		}	
		if (element.isTargetLink()) {
			LinkTarget linkTarget = element.getLinkTarget();
			if (linkTarget instanceof Page) {
				ProcessorInfo<WidgetFactory> ppi = registry.get(linkTarget);
				if (ppi != null) {
					ppi.getProcessor().resolve(uri, progressMonitor);
				}
			}
		}
	}	
	
	@Override
	protected Collection<EObject> getDocumentation(ProgressMonitor progressMonitor) {
		List<EObject> ret = new ArrayList<>();		
		LinkTarget linkTarget = element.getLinkTarget();
		if (linkTarget instanceof Page) {
			ProcessorInfo<WidgetFactory> ppi = registry.get(linkTarget);
			if (ppi != null) {
				PageProcessor pageProcessor = (PageProcessor) ppi.getProcessor();
				Text representationText = ContentFactory.eINSTANCE.createText(); // Interpolate with element properties?
				try {
					Document representation = pageProcessor.createRepresentation(progressMonitor);
					representationText.setContent(representation.toHtml(true, factory.getViewer()));
					ret.addAll(factory.createRepresentationContent(representation, registry, progressMonitor));
				} catch (TransformerException | IOException | ParserConfigurationException e) {
					representationText.setContent("<div class=\"alert alert-danger\" role=\"alert\">Error creating representation:" + e + "</div>");
				}
				ret.add(representationText);
			}
		}
				
		ret.addAll(super.getDocumentation(progressMonitor));
		return ret;
	}
	
	protected Collection<Label> addPageLabels(Collection<Label> labels, Collection<Label> pageLabels) {
		for (Label label: labels) {
			label.getChildren().addAll(pageLabels);
			if (label instanceof Action) {
				pageLabels.forEach(pageLabel -> pageLabel.rebase(null, uri));		
			}
		}
		return labels;
	}
	
	
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
						
		Supplier<Collection<Label>> labelSupplier = childLabelsSupplier
				.then(outgoingConnectionsLabelsSupplier.asFunction(this::logicalChildrenLabels))
				.then(this::createLayerElementLabels);
		
		if (element.isTargetLink()) {
			LinkTarget linkTarget = element.getLinkTarget();
			if (linkTarget instanceof Page) {
				ProcessorInfo<WidgetFactory> ppi = registry.get(linkTarget);
				Supplier<Collection<Label>> pageLabelSupplier = ppi.getProcessor().createLabelsSupplier();
				return labelSupplier.then(pageLabelSupplier.asFunction(this::addPageLabels));
			}
		}		
		
		return labelSupplier;
	}
	
	protected Map<ModelElement, Collection<Label>> logicalChildrenLabels(
			Map<ModelElement, Collection<Label>> childLabels, 
			Map<Connection,Collection<Label>> connectionLabels) {
		
		Map<ModelElement, Collection<Label>> ret = new HashMap<>(childLabels);
		ret.putAll(connectionLabels);
		return ret;
	}
	
	protected Collection<Label> createLayerElementLabels(
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
			((Action) mLabel).setLocation(uri.toString());
			childNodesLabelsList.forEach(cl -> cl.rebase(null, uri));		
			childConnectionsActionList.forEach(cl -> cl.rebase(null, uri));		
		}		
		
		return Collections.singleton(mLabel);			
	}		
	

}
