package org.nasdanika.html.model.app.graph.drawio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class LayerElementProcessor<T extends LayerElement> extends LinkTargetProcessor<T> {
	
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
		LinkTarget linkTarget = element.getLinkTarget();
		if (linkTarget instanceof Page) {
			ProcessorInfo<WidgetFactory> ppi = registry.get(linkTarget);
			if (ppi != null) {
				PageProcessor pageProcessor = (PageProcessor) ppi.getProcessor();
				pageProcessor.resolve(uri, progressMonitor);
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

}
