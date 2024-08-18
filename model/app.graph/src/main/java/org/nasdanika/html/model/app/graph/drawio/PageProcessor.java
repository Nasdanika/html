package org.nasdanika.html.model.app.graph.drawio;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Page;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.Registry;
import org.nasdanika.graph.processor.RegistryEntry;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

/**
 * Page processor creates an action for top level pages, adds page diagram and root documentation.
 * For linked pages passes through child labels to be used by the linking element. 
 * Also generates diagram embedding widget to add to the linking element page. 
 */
public class PageProcessor extends LinkTargetProcessor<Page> {
	
	public PageProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}
	
	@ProcessorElement
	@Override
	public void setElement(Page element) {
		super.setElement(element);
		uri = URI.createURI("index.html");
	}
	
	/**
	 * Forcing top-level page
	 */
	public boolean isTopLevelPage;
	
	@RegistryEntry("#element.model.root == #this")
	public RootProcessor rootProcessor;
	
	@Registry 
	public Map<Element, ProcessorInfo<WidgetFactory>> registry;
	
	@Override
	public void resolve(URI base, ProgressMonitor progressMonitor) {
		super.resolve(base, progressMonitor);
		rootProcessor.resolve(base, progressMonitor);
	}
	
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		if (isTopLevelPage || referrers.isEmpty()) {
			return rootProcessor.createLabelsSupplier().then(this::createPageLabels);			
		}
		return rootProcessor.createLabelsSupplier();
	}
	
	protected Collection<Label> createPageLabels(Collection<Label> rootLabels, ProgressMonitor progressMonitor) {
		Action action = AppFactory.eINSTANCE.createAction();		
		action.setText(element.getName());
		action.setLocation(uri.toString());
		action.getChildren().addAll(rootLabels);
		rootLabels.forEach(rl -> rl.rebase(null, uri));
		rootProcessor.configureLabel(action, progressMonitor);
		Text representationText = ContentFactory.eINSTANCE.createText(); // Interpolate with element properties?
		try {
			representationText.setContent(createRepresentation(progressMonitor).toHtml(true, factory.getViewer()));
		} catch (TransformerException | IOException | ParserConfigurationException e) {
			representationText.setContent("<div class=\"alert alert-danger\" role=\"alert\">Error creating representation:" + e + "</div>");
		}
		action.getContent().add(representationText);
		action.getContent().addAll(rootProcessor.getDocumentation(progressMonitor));
		return Collections.singleton(action);
	}	
	
	/**
	 * Creates filtered representation
	 * @param semanticModelElement
	 * @param modelPage
	 * @param registry
	 * @param progressMonitor
	 * @return
	 * @throws ParserConfigurationException 
	 */
	public Document createRepresentation(ProgressMonitor progressMonitor) throws ParserConfigurationException {		
		Document representationDocument = Document.create(true, element.getDocument().getURI());
		representationDocument.getPages().add(element);
		representationDocument.getPages().get(0).accept(pageElement -> filterRepresentationElement((Element) pageElement, progressMonitor));
		return representationDocument;
	}
	
	/**
	 * Resolves links and delegates to the factory to customize filtering.
	 */
	protected void filterRepresentationElement(Element representationElement, ProgressMonitor progressMonitor) {
		// TODO - resolve links
		
		factory.filterRepresentationElement(representationElement, registry, progressMonitor);
	}
	
}
