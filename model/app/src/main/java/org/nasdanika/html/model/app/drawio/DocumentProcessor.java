package org.nasdanika.html.model.app.drawio;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Document;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.html.model.app.Action;

public class DocumentProcessor extends ElementProcessor {
	
	protected Action documentAction;

	protected DocumentProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
	}
	
	@Override
	public void createSemanticElements() {
		documentAction = resourceFactory.createDocumentAction((Document) config.getElement());
	}
	
	@Override
	public List<EObject> getOwnSemanticElements() {
		return documentAction == null ? Collections.emptyList() : Collections.singletonList(documentAction);
	}
	
}
