package org.nasdanika.html.model.app.drawio;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Util;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;

public class ElementProcessor {
	
	protected ResourceFactory resourceFactory;
	protected ProcessorConfig<ElementProcessor> config;
	protected ProcessorInfo<ElementProcessor> parentInfo;
	protected Map<Element, ProcessorInfo<ElementProcessor>> registry;
	protected URI uri;

	protected ElementProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		this.resourceFactory = resourceFactory;
		this.uri = uri;
		this.config = config;		
	}
	
	public List<EObject> getOwnSemanticElements() {
		return Collections.emptyList();
	}	

	/**
	 * This implementation collects semantic elements from children and passes them through.
	 * @return
	 */
	public List<EObject> getSemanticElements() {
		return config
			.getChildProcessorsInfo()
			.values()
			.stream()
			.map(ProcessorInfo::getProcessor)
			.map(ElementProcessor::getSemanticElements)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}
	
	public void setParentInfo(ProcessorInfo<ElementProcessor> parentInfo) {
		this.parentInfo = parentInfo;
	}
	
	public void setRegistry(Map<Element, ProcessorInfo<ElementProcessor>> registry) {
		this.registry = registry;
	}
	
	public ProcessorInfo<ElementProcessor> getSemanticParentInfo() {
		return parentInfo;		
	}
	
	public Collection<ProcessorInfo<ElementProcessor>> getSemanticChildrenInfo() {
		return config.getChildProcessorsInfo().values();
	}
	
	// TODO - get semantic children, children by default.

	/**
	 * Adds textual content.
	 * @param content
	 */
	protected static void addContent(Action action, String content) {
		if (!Util.isBlank(content)) {
			Text text = createText(content);
			action.getContent().add(text);
		}
	}
	
	/**
	 * Convenience method to create Text and set content in one shot.
	 * @param content
	 * @return
	 */
	protected static Text createText(String content) {
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(content);
		return text;
	}
	
	/**
	 * Resolves URI of this element relative to the baseURI by traversing the semantic hierarchy from the top and resolving URI's.
	 * @param baseURI
	 * @return
	 */
	public URI resolve(URI baseURI) {
		ProcessorInfo<ElementProcessor> spi = getSemanticParentInfo();
		if (spi == null || spi.getProcessor() == null) {
			return baseURI;
		}
		return spi.getProcessor().resolve(baseURI);
	}
	
	// --- Lifecycle methods ---
	
	/**
	 * This implementation does nothing. @{NodeProcessor}'s of nodes with default role override to call outgoing connections to propagate 
	 */
	public void setDefaultConnectionRole() {
		
	}
	
	/**
	 * This implementation does nothing. @{NodeProcessors} override to pass nodes to connections.
	 */
	public void setSemanticParent() {
		
	}
	
	/**
	 * Creates semantic elements for the processor/element.
	 */
	public void createSemanticElements() {
		
	}
	
	/**
	 * 
	 */
	public void resolveSemanticElements(URI baseURI) { // Resource URI's and 
		
	}
	
}
