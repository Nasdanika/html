package org.nasdanika.html.model.app.drawio;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.ExecFactory;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;

public abstract class ModelElementProcessor extends ElementProcessor {
	
	protected List<ProcessorInfo<ElementProcessor>> semanticChildrenInfo;
	protected ProcessorInfo<ElementProcessor> semanticParentInfo;
	protected EObject semanticElement;

	protected ModelElementProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		super(resourceFactory, uri, config, baseURI);
	}
	
	@Override
	public ModelElement getElement() {
		return (ModelElement) super.getElement();
	}
	
	/**
	 * @return true if this element creates one or more semantic elements and as such shall be passed as a semantic parent to setSemanticParentInfo().
	 */
	protected boolean isSemantic() {
		return !Util.isBlank(getText()) && resourceFactory.shallCreateAction(getElement());
	}
	
	public String getText() {
		String label = (getElement()).getLabel();
		return Util.isBlank(label) ? null : Jsoup.parse(label).text(); 				
	}
	
	public List<ProcessorInfo<ElementProcessor>> setSemanticParentInfo(ProcessorInfo<ElementProcessor> semanticParentInfo) {
		if (isSemantic()) {
			this.semanticParentInfo = semanticParentInfo;
			ProcessorInfo<ElementProcessor> info = ProcessorInfo.of(config, this);
			semanticChildrenInfo = collectSemanticChildrenInfo(info);
			return Collections.singletonList(info);
		}

		return collectSemanticChildrenInfo(semanticParentInfo);
	}
	
	protected abstract List<ProcessorInfo<ElementProcessor>> collectSemanticChildrenInfo(ProcessorInfo<ElementProcessor> semanticParentInfo);
		
	public void build() {
		EObject semanticElement = getSemanticElement();
		if (semanticElement instanceof Action) {
			Action action = (Action) semanticElement;
						
			EObject doc = getDocumentation();
			if (doc == null) {
				String tooltip = getElement().getTooltip();
				if (!Util.isBlank(tooltip)) {
					addContent(action, tooltip);
				}
				String embeddedDiagram = getEmbeddedDiagram();
				if (!Util.isBlank(embeddedDiagram)) {
					addContent(action, embeddedDiagram);
				}
				String toc = getTableOfContents();
				if (!Util.isBlank(toc)) {
					addContent(action, toc);
				}
			} else {					
				action.getContent().add(doc);
			}
			
			// Adding children to roles and building
			if (semanticChildrenInfo != null) {
				semanticChildrenInfo.forEach(info -> {			
					ModelElementProcessor processor = (ModelElementProcessor) info.getProcessor();
					EReference childRole = processor.getRole();
					if (childRole != null) {
						@SuppressWarnings("unchecked")
						Collection<EObject> roleElements = (Collection<EObject>) action.eGet(childRole);
						roleElements.addAll(processor.getSemanticElements());
					}
					processor.build();
				});
			}			
		}		
	}	
	
////@SuppressWarnings("unchecked")
////@Override
////protected void resolve(
////		Resource resource, 
////		Element element, 
////		Map<EReference, List<Action>> semanticElement,
////		Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,
////		Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
////	
////					
////		// Adding URI resolver adapters
////		for (EObject root: resource.getContents()) {
////			if (root instanceof Action) {
////				addURIResolverAdapters((Action) root);
////			}
////		}
////	} else if (element instanceof Page) {
////		Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, (Page) element, childEntries);
////
////		for (Action action: semanticElement.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(pageElementEntry.getKey(), a)).collect(Collectors.toList())) {
////			// Embedded diagram
////			String embeddedDiagram = generateEmbeddedDiagram(element, action, resolver);
////			if (isEmbedDiagram(resource, element, childEntries)) {															
////				addContent(action, embeddedDiagram);						
////			}
////
////			// TOC
////			StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
////			for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: pageElementEntry.getValue().entrySet()) { 
////				tocBuilder.append(generateTableOfContents(action, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
////			}					
////			tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator());
////			
////			if (isGenerateTableOfContents(resource, element, childEntries)) {
////				addContent(action, tocBuilder.toString());
////			}
////			
////			EObject doc = getDocumentation(resource, element, embeddedDiagram, childEntries, tocBuilder.toString());
////			if (doc == null) {
////				String rootTooltip = pageElementEntry.getKey().getTooltip();
////				if (!Util.isBlank(rootTooltip)) {
////					addContent(action, rootTooltip);
////				}
////			} else {
////				action.getContent().add(doc);
////			}					
////		}			
////	} else if (element instanceof ModelElement) {
////		for (Action action: semanticElement.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(element, a)).collect(Collectors.toList())) {
////			if (element instanceof Connection) {
////				// Source and target
////				Table table = BootstrapFactory.eINSTANCE.createTable();
////				table.getAttributes().put("style", createText("width:auto"));
////				
////				TableRow sourceRow = BootstrapFactory.eINSTANCE.createTableRow();
////				table.getRows().add(sourceRow);
////				
////				TableCell sourceHeader = BootstrapFactory.eINSTANCE.createTableCell();
////				sourceHeader.setHeader(true);
////				sourceRow.getCells().add(sourceHeader);
////				sourceHeader.getContent().add(createText("Source"));
////									
////				TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
////				sourceRow.getCells().add(sourceCell);
////				
////				Node source = ((Connection) element).getSource();
////				String sourceLabel = getModelElementLabel(source);
////				if (Util.isBlank(sourceLabel)) {
////					sourceLabel = "(unlabeled)";
////				} 
////				String sourceLink = getModelElementLink(source, action, resolver);
////				if (sourceLink == null) {
////					sourceCell.getContent().add(createText(sourceLabel));						
////				} else {
////					sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
////				}
////				
////				TableRow targetRow = BootstrapFactory.eINSTANCE.createTableRow();
////				table.getRows().add(targetRow);
////				
////				TableCell targetHeader = BootstrapFactory.eINSTANCE.createTableCell();
////				targetHeader.setHeader(true);
////				targetRow.getCells().add(targetHeader);
////				targetHeader.getContent().add(createText("Target"));
////									
////				TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
////				targetRow.getCells().add(targetCell);
////				
////				Node target = ((Connection) element).getTarget();
////				String targetLabel = getModelElementLabel(target);
////				if (Util.isBlank(targetLabel)) {
////					targetLabel = "(unlabeled)";
////				} 
////				String targetLink = getModelElementLink(target, action, resolver);
////				if (targetLink == null) {
////					targetCell.getContent().add(createText(targetLabel));						
////				} else {
////					targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
////				}					
////
////				action.getContent().add(table);
////			} else if (element instanceof Node) {
////				}
////			}
////			
////		}
////	}
////}
	
	
	protected String getSemanticID() {
		return getElement().getId();
	}
	
	protected Document getEmbeddedDiagramDocument() throws ParserConfigurationException {
		Page linkedPage = getElement().getLinkedPage();
		if (linkedPage == null) {
			return null;
		}
		Root root = linkedPage.getModel().getRoot();
		return ((ModelElementProcessor) registry.get(root).getProcessor()).getEmbeddedDiagramDocument();
	}
	
	protected Comparator<ProcessorInfo<ElementProcessor>> getSemanticChildrenComparator() {
		String sortProperty = resourceFactory.getSortProperty();
		String sort = null;
		if (!org.nasdanika.common.Util.isBlank(sortProperty)) {
			sort = (getElement()).getProperty(sortProperty);
		}
		if (org.nasdanika.common.Util.isBlank(sort)) {
			// Inherit from semantic parent here?
			sort = resourceFactory.getDefaultSort();
		}
		
		if (org.nasdanika.common.Util.isBlank(sort) || "none".equals(sort)) {
			return null;
		}
		
		String config = null;
		int colonIdx = sort.indexOf(":");
		if (colonIdx != -1) {
			config = sort.substring(colonIdx + 1);
			sort = sort.substring(0, colonIdx);
		}
		
		Comparator<Element> elementComparator = resourceFactory.createComparator(this.config.getElement(), sort, config);
		if (elementComparator == null) {
			return null;
		}
		
		return (a,b) -> elementComparator.compare(a.getConfig().getElement(), b.getConfig().getElement()); 
	}
	
	public EReference getRole() {
		return resourceFactory.getRole(getElement());
	}
	
	protected String getIcon() {
		String iconProperty = resourceFactory.getIconProperty();
		ModelElement modelElement = getElement();
		return Util.isBlank(iconProperty) ? null : modelElement.getProperty(iconProperty);
	}

	public List<EObject> getSemanticElements() {
		EObject semanticElement = getSemanticElement();
		if (semanticElement == null) {
			return semanticChildrenInfo
					.stream()
					.map(ProcessorInfo::getProcessor)
					.map(ModelElementProcessor.class::cast)
					.map(ModelElementProcessor::getSemanticElements)
					.flatMap(Collection::stream)
					.collect(Collectors.toList());
		}
		return Collections.singletonList(semanticElement);
	}	
	
	public EObject getSemanticElement() {
		if (semanticElement == null) {
			Action action;
			Page linkedPage = getElement().getLinkedPage();
			if (linkedPage == null) {
				String actionProperty = getElement().getProperty("action");
				if (Util.isBlank(actionProperty)) {
					action = AppFactory.eINSTANCE.createAction();
				} else {
					URI actionURI = URI.createURI(actionProperty);
					actionURI = actionURI.resolve(resourceURI);
					action = EcoreUtil.copy((Action) resourceFactory.getResourceSet().getEObject(actionURI, true));
					action.setId(UUID.randomUUID().toString());
				}
			} else {
				// TODO - handle content and diagram roles - by the page processor?
				
				ProcessorInfo<ElementProcessor> pageInfo = registry.get(linkedPage);
				PageProcessor pageProcessor = (PageProcessor) pageInfo.getProcessor();
				ModelElement pageElement = pageProcessor.getPageElement();
				ModelElementProcessor pageElementProcessor = (ModelElementProcessor) registry.get(pageElement).getProcessor();
				EObject pageSemanticElement = pageElementProcessor.getSemanticElement();
				if (pageSemanticElement instanceof Action) {
					action = (Action) pageSemanticElement;
				} else {
					action = AppFactory.eINSTANCE.createAction();
				}
			}
			action.setId(getSemanticID()); 
			action.setText(getText());
			action.setDescription(getElement().getTooltip());
			String icon = getIcon();
			if (!Util.isBlank(icon)) {
				action.setIcon(icon);
			}
		
			EReference role = getRole();
			String path = getPath();
			if (!Util.isBlank(path) && path.startsWith("./")) { // A trick to avoid prepending location with role.
				role = null;
				path = path.substring(2);
			}
			StringBuilder locationBuilder = new StringBuilder();
			if (role != null) {
				locationBuilder.append(role.getName()).append("/");				
			}
			if (!Util.isBlank(path)) {
				locationBuilder.append(path).append("/");
			}
			locationBuilder.append("index.html");
			action.setLocation(locationBuilder.toString());
			
			semanticElement = action;
		}
		
		return semanticElement;
	}			
	
	/**
	 * 
	 * @return Absolute URI resolve relative to the semantic parent and eventually the baseURI.
	 */
	public URI getURI() {
		EObject se = getSemanticElement();
		if (se instanceof Action) {
			Action action = (Action) se;
			String location = action.getLocation();
			if (!Util.isBlank(location)) {
				URI actionURI = URI.createURI(Context.singleton(Context.BASE_URI_PROPERTY, baseURI).interpolateToString(location));
				if (semanticParentInfo != null) {
					ElementProcessor sp = semanticParentInfo.getProcessor();
					if (sp instanceof ModelElementProcessor) {
						URI spURI = ((ModelElementProcessor) sp).getURI();
						return actionURI.resolve(spURI);
					}
				}
				return actionURI.resolve(baseURI);
			}
		}
		
		if (semanticParentInfo != null) {
			ElementProcessor sp = semanticParentInfo.getProcessor();
			if (sp instanceof ModelElementProcessor) {
				return ((ModelElementProcessor) sp).getURI();
			}
		}
		return null;
	}
		
	protected String getPath() {
		ModelElement modelElement = getElement();
		String pathProperty = resourceFactory.getPathProperty();
		if (!Util.isBlank(pathProperty)) {
			String path = modelElement.getProperty(pathProperty);
			if (!Util.isBlank(path)) {
				return path;
			}
		}
		if (modelElement instanceof Root) {
			return ((Root) modelElement).getModel().getPage().getId();
		}
		return modelElement.getId();
	}
	
	/**
	 * Returns semantic URI of this processor relative to the base processor's semantic URI.
	 * @param base
	 * @return
	 */
	public String relativeURI(ElementProcessor base) {
		URI uri = getURI();
		if (uri == null) {
			return null;
		}
		if (!(base instanceof ModelElementProcessor)) {
			return uri.toString();
		}
		
		URI baseURI = ((ModelElementProcessor) base).getURI();
		if (baseURI == null) {
			return uri.toString();
		}
		
		return uri.deresolve(baseURI, true, true, true).toString();
	}
	
	/**
	 * Returns documentation URI for this element or for its ancestor.
	 */
	protected URI resolveDocumentationBaseURI() {
		ProcessorInfo<ElementProcessor> spi = semanticParentInfo;				
		URI base = spi instanceof ModelElementProcessor ? ((ModelElementProcessor) spi.getProcessor()).resolveDocumentationBaseURI() : resourceURI;
		
		String documentationProperty = resourceFactory.getDocumentationProperty();
		if (Util.isBlank(documentationProperty)) {
			return base;
		}
		
		String docProp = (getElement()).getProperty(documentationProperty);
		if (Util.isBlank(docProp)) {
			return base;
		}

		URI docURI = URI.createURI(docProp);
		return base == null ? docURI : docURI.resolve(base);
	}		
	
	/**
	 * @param modelElement
	 * @return true if this element is the main element on the page which "represents" the page. 
	 */
	protected boolean isPageElement(ModelElement modelElement) {
		String pageElementFlagProperty = resourceFactory.getPageElementFlagProperty();
		if (Util.isBlank(pageElementFlagProperty)) {
			return false;
		}
		String pageElementProp = modelElement.getProperty(pageElementFlagProperty);
		return "true".equals(pageElementProp);
	}
	
	protected String getTableOfContents() {
		// TODO
		return null;
	}	
	
	// TODO - pass base processor for relative links resolution.
////
////protected String generateTableOfContents(
////		Action pageAction, 
////		ModelElement modelElement, 
////		Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,
////		Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {		
////	
////	String label = getModelElementLabel(modelElement);
////	StringBuilder ret = new StringBuilder();
////	if (Util.isBlank(label)) {
////		if (modelElement instanceof Layer && childEntries != null) {
////			for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) {
////				ret.append(generateTableOfContents(pageAction, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
////			}
////		}
////	} else {		
////		ret.append("<li>").append(System.lineSeparator());
////		String link = getModelElementLink(modelElement, pageAction, resolver);
////		if (Util.isBlank(link)) {
////			ret.append(label);
////		} else {
////			ret
////				.append("<a href=\"")
////				.append(link)
////				.append("\">")
////				.append(label)
////				.append("</a>");				
////		}			
////		
////		String tooltip = modelElement.getTooltip();
////		if (!Util.isBlank(tooltip)) {
////			ret.append(" - ").append(tooltip).append(System.lineSeparator());
////		}
////
////		if (modelElement instanceof Layer) {
////			if (childEntries != null && !childEntries.isEmpty()) {
////				ret.append(getListOpenTag(modelElement)).append(System.lineSeparator());
////				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) {
////					ret.append(generateTableOfContents(pageAction, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
////				}
////				ret.append(getListCloseTag(modelElement)).append(System.lineSeparator());
////			}
////		}
////		ret.append("</li>").append(System.lineSeparator());
////	}
////	
////	return ret.toString();
////}
//
	
	
	protected String getEmbeddedDiagram() {
		try {
			Document embeddedDiagramDocument = getEmbeddedDiagramDocument();
			embeddedDiagramDocument.accept(this::processEmbeddedDiagramElement, resourceFactory.getConnectionBase());		
			return embeddedDiagramDocument.toHtml(null, resourceFactory.getDiagramViewer());
		} catch (Exception  e) {
			return "Error embedding diagram: " + e;
		}				
	}
	
	/**
	 * Processes an element of the embedded diagram. 
	 * This implementation adds a link. Override for additional behavior, e.g. change background color based on external information.
	 * @param element
	 * @param action
	 * @param resolver
	 */
	protected void processEmbeddedDiagramElement(Element element) {		
		if (element instanceof ModelElement) {
			ModelElement modelElement = (ModelElement) element;
			if (getElement().equals(modelElement) || modelElement.isPageLink()) {
				modelElement.setLink(null);
			}
			if (Util.isBlank(modelElement.getLink())) {
				String link = getModelElementLink(modelElement);
				if (!Util.isBlank(link)) {
					modelElement.setLink(link);
				}				
			}
		}		
	}
	
	public boolean hasDocumentation() {
		String documentationProperty = resourceFactory.getDocumentationProperty();
		if (Util.isBlank(documentationProperty)) {
			return false;
		}
		
		String docProp = getElement().getProperty(documentationProperty);
		if (Util.isBlank(docProp)) {
			return false;
		}
		
		return true;
	}
	
	
	protected EObject getDocumentation() {
		if (!hasDocumentation()) {
			return null;
		}
		
		URI docURI = resolveDocumentationBaseURI();			
		
		Markdown markdown = ContentFactory.eINSTANCE.createMarkdown();
		org.nasdanika.exec.content.Resource docResource = ContentFactory.eINSTANCE.createResource();
		docResource.setLocation(docURI.toString());
		markdown.setSource(docResource);						
		markdown.setStyle(true);

		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();			
		interpolator.setSource(markdown);
		
		String embeddedDiagram = getEmbeddedDiagram();
				
		String tableOfContents = getTableOfContents();
		if (Util.isBlank(embeddedDiagram) && Util.isBlank(tableOfContents)) {
			return interpolator;
		}
		
		Configurator configurator = ExecFactory.eINSTANCE.createConfigurator();
		configurator.setTarget(interpolator);
		
		EMap<String, EObject> properties = configurator.getProperties();
		if (!Util.isBlank(embeddedDiagram)) {
			Text diagramText = ContentFactory.eINSTANCE.createText();
			diagramText.setContent(embeddedDiagram);
			properties.put("diagram", diagramText);
		}
		
		if (!Util.isBlank(tableOfContents)) {
			Text tocText = ContentFactory.eINSTANCE.createText();
			tocText.setContent(tableOfContents);
			properties.put("toc", tocText);
		}
		
		return configurator;			
	}	

	/**
	 * Resolves link to the model element from this action.
	 * @param modelElement Model element
	 * @param action Source action 
	 * @param resolver Resolver
	 * @return
	 */
	protected String getModelElementLink(ModelElement modelElement) {
		if (resourceFactory.shallCreateLink(modelElement)) {
			// Link by cross-reference
			String crossReferenceProperty = resourceFactory.getCrossReferenceProperty();
			if (!Util.isBlank(crossReferenceProperty)) {
				String crossReferencePropertyValue = modelElement.getProperty(crossReferenceProperty);
				if (!Util.isBlank(crossReferencePropertyValue)) {
					Optional<ModelElementProcessor> processorOptional = registry
						.entrySet()
						.stream()
						.filter(e -> resourceFactory.match(e.getKey(), crossReferencePropertyValue))
						.map(e -> e.getValue().getProcessor())
						.filter(ModelElementProcessor.class::isInstance)
						.map(ModelElementProcessor.class::cast)
						.filter(ModelElementProcessor::isSemantic)
						.findFirst();
						
					if (processorOptional.isPresent()) {
						return processorOptional.get().relativeURI(this);
					}
				}
			}
			
			// Link by ID equality
			Optional<ModelElementProcessor> processorOptional = registry
					.entrySet()
					.stream()
					.filter(e -> e.getKey() instanceof ModelElement && Objects.equals(modelElement.getId(), ((ModelElement) e.getKey()).getId()))
					.map(e -> e.getValue().getProcessor())
					.filter(ModelElementProcessor.class::isInstance)
					.map(ModelElementProcessor.class::cast)
					.filter(ModelElementProcessor::isSemantic)
					.findFirst();
					
				if (processorOptional.isPresent()) {
					return processorOptional.get().relativeURI(this);
				}
		}
		return null;
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
	
}
