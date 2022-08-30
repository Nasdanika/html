package org.nasdanika.html.model.app.drawio;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Util;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;

/**
 * Base class for processors.
 * @author Pavel
 *
 */
public class ElementProcessor {
	
	protected ResourceFactory resourceFactory;
	protected ProcessorConfig<ElementProcessor> config;
	protected ProcessorInfo<ElementProcessor> parentInfo;
	protected ProcessorInfo<ElementProcessor> semanticParentInfo;
	protected Map<Element, ProcessorInfo<ElementProcessor>> registry;
	protected URI resourceUri;
	
	/**
	 * Absolute URI of the semantic element to be created. Semantic URI is set in resolve().
	 * When a semantic element is created its URI (location) is resolved relative to its parent URI.
	 */
	protected URI semanticUri;

	public ElementProcessor(ResourceFactory resourceFactory, URI resourceUri, ProcessorConfig<ElementProcessor> config) {
		this.resourceFactory = resourceFactory;
		this.resourceUri = resourceUri;
		this.config = config;		
	}
	
	public Element getElement() {
		return config.getElement();
	}
	
	public void setParentInfo(ProcessorInfo<ElementProcessor> parentInfo) {
		this.parentInfo = parentInfo;
	}
	
	public void setRegistry(Map<Element, ProcessorInfo<ElementProcessor>> registry) {
		this.registry = registry;
	}
	
	/**
	 * @return Semantic parent info. This implementation returns parent info. Override for elements which may have semantic parent different from the "physical" parent.
	 */
	public ProcessorInfo<ElementProcessor> getSemanticParentInfo() {
		return semanticParentInfo;		
	}
	
	/**
	 * Semantic children can be different from children as defined in the diagram model. For example, in a mind-map diagram
	 * one node is a semantic child of another if there is a connection with a role between them.
	 * @return Sorted semantic children.
	 */
	public List<ProcessorInfo<ElementProcessor>> getSemanticChildrenInfo() {
		Stream<ProcessorInfo<ElementProcessor>> semanticChildrenInfoStream = registry
			.values()
			.stream()
			.filter(ep -> {
				ElementProcessor candidateProcessor = ep.getProcessor();
				ProcessorInfo<ElementProcessor> candidateSemanticParentInfo = candidateProcessor.getSemanticParentInfo();
				return candidateSemanticParentInfo != null &&  candidateSemanticParentInfo.getProcessor() == this;
			});
		
		Comparator<ProcessorInfo<ElementProcessor>> semanticChildrenInfoComparator = getSemanticChildrenComparator();
		if (semanticChildrenInfoComparator != null) {
			semanticChildrenInfoStream = semanticChildrenInfoStream.sorted(semanticChildrenInfoComparator);
		}		
		
		return semanticChildrenInfoStream.collect(Collectors.toList());
	}
	
	/**
	 * Adds textual content. Helper method.
	 * @param content
	 */
	public static void addContent(Action action, String content) {
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
	public static Text createText(String content) {
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(content);
		return text;
	}
	
	protected URI resolveDocumentationBaseURI() {
		ProcessorInfo<ElementProcessor> spi = getSemanticParentInfo();
		if (spi == null || spi.getProcessor() == null) {
			return null;
		}
		return spi.getProcessor().resolveDocumentationBaseURI();
	}
	
	// --- Lifecycle methods ---
	
	/**
	 * This implementation does nothing. @{NodeProcessor}'s of nodes with default role override to call outgoing connections to propagate 
	 */
	public void setDefaultConnectionRole() {
		
	}
	
	/**
	 * This implementation sets semantic parent to parent. 
	 */
	public void setSemanticParent() {
		semanticParentInfo = parentInfo;
	}
	
	/**
	 * Creates semantic elements for the processor/element.
	 * This implementation collects created semantic elements from semantic children (pass-through)
	 */
	public List<EObject> createSemanticElements() {
		return getSemanticChildrenInfo()
				.stream()
				.map(ProcessorInfo::getProcessor)
				.map(ElementProcessor::createSemanticElements)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());		
	}
	
	/**
	 * Resolves semantic URI. This implementation calls resolveSemanticURIs of semantic children.
	 * @param baseURI Base URI for top-level relative URI's. Also used as <code>${base-uri}</code> token in links.
	 * @param semanticParentURI URI of the semantic parent. 
	 */
	public void resolveSemanticURI(URI baseURI, URI semanticParentURI) { 
		semanticUri = semanticParentURI;
		getSemanticChildrenInfo().stream().map(ProcessorInfo::getProcessor).forEach(ep -> ep.resolveSemanticURI(baseURI, semanticParentURI));
	}
	
	public URI getSemanticUri() {
		return semanticUri;
	}
	
	protected Comparator<ProcessorInfo<ElementProcessor>> getSemanticChildrenComparator() {
		return null;
	}
	
	public EReference getRole() {
		return null;
	}
	
}
