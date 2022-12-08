package org.nasdanika.html.model.app.drawio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.emf.SemanticProcessor;
import org.nasdanika.html.model.app.Action;

public class DocumentProcessor extends ElementProcessor implements SemanticProcessor<EObject> {
	
	private Action documentAction;

	public DocumentProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		super(resourceFactory, uri, config, baseURI);		
	}
	
	@Override
	public Document getElement() {
		return (Document) super.getElement();
	}
	
	/**
	 * @return Semantic element for this processor. Can be created in advance or on access.
	 */
	@Override
	public Collection<EObject> getSemanticElements() {
		return Collections.singleton(documentAction);
	}	

	/**
	 * Builds semantic element so it is ready for use.
	 */
	public Stream<EObject> build() {
		Action documentAction  = resourceFactory.createDocumentAction(getElement());
		
		List<Page> pages = new ArrayList<>(getElement().getPages());
		Comparator<Page> pageComparator = resourceFactory.getPageComparator(getElement());
		if (pageComparator != null) {
			pages.sort(pageComparator);
		}
		
		// Setting semantic hierarchy
		Map<ProcessorInfo<ElementProcessor>, EReference> semanticChildrenInfo = new LinkedHashMap<>();
		for (Page page: pages) {
			PageProcessor pageProcessor = (PageProcessor) registry.get(page).getProcessor();
			if (pageProcessor.isRootPage()) {
				ModelElement pageElement = pageProcessor.getPageElement();
				
				ProcessorInfo<ElementProcessor> semanticParentInfo = documentAction == null ? null : ProcessorInfo.of(config, this);
				ModelElementProcessor pageElementProcessor = (ModelElementProcessor) registry.get(pageElement).getProcessor();
				Map<ProcessorInfo<ElementProcessor>, EReference> pageElementSemanticInfo = pageElementProcessor.setSemanticParentInfo(semanticParentInfo);
				semanticChildrenInfo.putAll(pageElementSemanticInfo);
			}
		}

		// Build semantic children recursively.
		for (Entry<ProcessorInfo<ElementProcessor>, EReference> entry: semanticChildrenInfo.entrySet()) {
			ModelElementProcessor processor = (ModelElementProcessor) entry.getKey().getProcessor();
			if (documentAction != null) {
				EReference role = entry.getValue();
				if (role != null) {
					@SuppressWarnings("unchecked")
					Collection<EObject> roleElements = (Collection<EObject>) documentAction.eGet(role);
					List<EObject> semanticElements = processor.getSemanticElements();
					roleElements.addAll(semanticElements);
				}
			}
			processor.build();
		}
		
		if (documentAction == null) {
			return semanticChildrenInfo
					.keySet()
					.stream()
					.map(ProcessorInfo::getProcessor)
					.map(ModelElementProcessor.class::cast)
					.map(ModelElementProcessor::getSemanticElements)
					.flatMap(Collection::stream);
		}
		return Stream.of(documentAction);		
	}
	
}
	