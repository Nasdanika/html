package org.nasdanika.html.model.app.drawio;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.processor.ProcessorConfig;

/**
 * Always pass-through to the page element. 
 * @author Pavel
 *
 */
public class PageProcessor extends ElementProcessor {
	
	public PageProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		super(resourceFactory, uri, config, baseURI);
	}
	
	public ModelElement getPageElement() {
		return resourceFactory.getPageElement(getElement());
	}
		
	@Override
	public Page getElement() {
		return (Page) super.getElement();
	}
	
	public boolean isRootPage() {
		return resourceFactory.isRootPage(getElement());
	}
		
}
