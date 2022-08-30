package org.nasdanika.html.model.app.drawio;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.processor.ProcessorConfig;

/**
 * Possible page types: actions - multiple roles, diagram, contents, none
 * Action is null if there is a page element or role is none - for linked pages.
 * @author Pavel
 *
 */
public class PageProcessor extends ElementProcessor {
	
	public PageProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
	}
	
	@Override
	public List<EObject> createSemanticElements() {
		Page page = getElement();
		if (resourceFactory.getPageRole(page) == null) {
			return Collections.emptyList();
		}

		// Pass-through to the page element, root by default.
		ModelElement pageElement = resourceFactory.getPageElement(page);
		return registry.get(pageElement).getProcessor().createSemanticElements();
	}
	
	@Override
	public Page getElement() {
		return (Page) super.getElement();
	}
	
	@Override
	public EReference getRole() {
		return resourceFactory.getPageRole(getElement());
	}
		
}
