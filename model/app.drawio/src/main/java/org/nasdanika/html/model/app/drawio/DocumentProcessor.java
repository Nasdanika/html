package org.nasdanika.html.model.app.drawio;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;

public class DocumentProcessor extends ElementProcessor {
	
	protected Action documentAction;

	public DocumentProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
	}
	
	@Override
	public List<EObject> getSemanticElements() {
		return documentAction == null ? super.getSemanticElements() : Collections.singletonList(documentAction);
	}
	
	@Override
	public void createSemanticElements() {
		documentAction = resourceFactory.createDocumentAction((Document) config.getElement());
	}
	
	/**
	 * Returns pages except the pages without a role. Sorted if comparator was provided. 
	 */
	@Override
	public List<ProcessorInfo<ElementProcessor>> getSemanticChildrenInfo() {
		Stream<Entry<Element, ProcessorInfo<ElementProcessor>>> entriesStream = config
			.getChildProcessorsInfo()
			.entrySet()
			.stream();
		
		Comparator<Page> pageComparator = resourceFactory.getPageComparator((Document) config.getElement());
		if (pageComparator != null) {
			entriesStream = entriesStream.sorted();
		}
		return entriesStream.map(Map.Entry::getValue).collect(Collectors.toList());
	}

	/**
	 * Adds page actions to document according to the page role.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void resolveSemanticElements(URI baseURI) {
		getSemanticChildrenInfo().forEach(info -> {			
			Page page = (Page) info.getConfig().getElement();
			String roleName = resourceFactory.getRoleName(page);
			if ("content".equals(roleName)) {
				// TODO
				throw new UnsupportedOperationException("Content role is not supported yet"); 
			} else if ("diagram".equals(roleName)) {
				// TODO
				throw new UnsupportedOperationException("Diagram role is not supported yet");
			} else {
				EReference pageRole = resourceFactory.getPageRole(page);
				if (pageRole != null) {
					System.out.println(info.getConfig().getElement());			
					List<EObject> semanticElements = info.getProcessor().getSemanticElements();
					((Collection<EObject>) documentAction.eGet(pageRole)).addAll(semanticElements);
				}
			}			
		});
	}
	
}
