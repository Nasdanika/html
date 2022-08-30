package org.nasdanika.html.model.app.drawio;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
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
import org.nasdanika.html.model.app.drawio.AppDrawioResourceFactory.URIResolverAdapter;

public abstract class ModelElementProcessor extends ElementProcessor {

//	protected String sort; // Inherit from semantic parent?
	
	@Override
	public ModelElement getElement() {
		return (ModelElement) super.getElement();
	}

	protected ModelElementProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
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
	
	@Override
	public EReference getRole() {
		return resourceFactory.getRole(getElement());
	}
	
	/**
	 * Resolves semantic URI. This implementation calls resolveSemanticURIs of semantic children.
	 * @param baseURI Base URI for top-level relative URI's. Also used as <code>${base-uri}</code> token in links.
	 * @param semanticParentURI URI of the semantic parent. 
	 */
	public void resolveSemanticURI(URI baseURI, URI semanticParentURI) {
		String path = Context.singleton(Context.BASE_URI_PROPERTY,  baseURI).interpolateToString(getPath());		
		semanticUri = URI.createURI(Util.isBlank(path) ? "index.html" : path + "/index.html").resolve(semanticParentURI);
		getSemanticChildrenInfo().stream().forEach(info -> {
			URI semanticBaseURI = semanticUri;			
			EReference childRole = info.getProcessor().getRole();
			if (childRole != null) {
				semanticBaseURI = URI.createURI(childRole.getName() + "/").resolve(semanticBaseURI);					
			}
			info.getProcessor().resolveSemanticURI(baseURI, semanticBaseURI);
		});
	}
	
	/**
	 * Returns documentation URI for this element or for its ancestor.
	 */
	@Override
	protected URI resolveDocumentationBaseURI() {
		ProcessorInfo<ElementProcessor> spi = getSemanticParentInfo();
		URI base = spi == null || spi.getProcessor() == null ? null : spi.getProcessor().resolveDocumentationBaseURI();
		
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
	
	protected String getIcon() {
		String iconProperty = resourceFactory.getIconProperty();
		ModelElement modelElement = getElement();
		return Util.isBlank(iconProperty) ? null : modelElement.getProperty(iconProperty);
	}
	
	protected String getText() {
		String label = (getElement()).getLabel();
		return Util.isBlank(label) ? null : Jsoup.parse(label).text(); 				
	}

	@Override
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
		
	@SuppressWarnings("unchecked")
	@Override
	public List<EObject> createSemanticElements() {
		ModelElement modelElement = getElement();
		String text = getText();
		if (Util.isBlank(text) || !resourceFactory.shallCreateAction(modelElement)) {
			return super.createSemanticElements();
		}
		
		Action action;
		Page linkedPage = modelElement.getLinkedPage();
		if (linkedPage == null) {
			String actionProperty = modelElement.getProperty("action");
			if (Util.isBlank(actionProperty)) {
				action = AppFactory.eINSTANCE.createAction();
			} else {
				URI actionURI = URI.createURI(actionProperty);
				actionURI = actionURI.resolve(resourceUri);
				action = EcoreUtil.copy((Action) resourceFactory.getResourceSet().getEObject(actionURI, true));
				action.setId(UUID.randomUUID().toString());
			}
		} else {
			// TODO - handle content and diagram roles - by the page processor?
			
			ProcessorInfo<ElementProcessor> pageInfo = registry.get(linkedPage);
			List<EObject> pageSemanticElements = pageInfo.getProcessor().createSemanticElements();
			if (pageSemanticElements.isEmpty()) {
				action = AppFactory.eINSTANCE.createAction();
			} else {
				action = (Action) pageSemanticElements.get(0);
			}
		}
		action.setId(modelElement.getModel().getPage().getId() + "-" + modelElement.getId()); 
		action.setText(text);
		action.setDescription(modelElement.getTooltip());
		String icon = getIcon();
		if (!Util.isBlank(icon)) {
			action.setIcon(icon);
		}
		URI actionURI = semanticUri;
		if (actionURI != null) {
			if (getSemanticParentInfo() != null) {
				URI parentSemanticURI = getSemanticParentInfo().getProcessor().getSemanticUri();
				if (parentSemanticURI != null) {
					actionURI = actionURI.deresolve(parentSemanticURI, true, true, true);
				}			
			}
			action.setLocation(actionURI.toString());
		}
		
		getSemanticChildrenInfo().forEach(info -> {			
			EReference childRole = info.getProcessor().getRole();
			if (childRole != null) {
				((Collection<EObject>) action.eGet(childRole)).addAll(info.getProcessor().createSemanticElements());
			}
		});
		
		return Collections.singletonList(action);
		
	}		
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public void resolveSemanticElements(URI baseURI) {
//		super.resolveSemanticElements(baseURI);		
//		if (action != null) {
//			ModelElement modelElement = getElement();
//			String link = modelElement.getLink();
//			if (Util.isBlank(link) || modelElement.isPageLink()) {
//				String path = Objects.requireNonNull(getPath(), "Path is null for " + getText() + " / " + (getElement()).getId());
//				action.setLocation(path + "/index.html");
//			} else {
//				action.setLocation(link);
//			}
//			
//			// Embedded diagram
////			String embeddedDiagram = null;
////			
////			Page linkedPage = ((ModelElement) element).getLinkedPage();
////			if (linkedPage != null) {
////				embeddedDiagram = generateEmbeddedDiagram(linkedPage, action, resolver);
////				if (isEmbedDiagram(resource, element, childEntries)) {															
////					addContent(action, embeddedDiagram);						
////				}
////			}								
////			
////			StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
////			for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) { 						
////				Element child = childEntry.getKey();
////				if (child instanceof ModelElement) {
////					tocBuilder.append(generateTableOfContents(action, (ModelElement) child, childEntry.getValue().getChildEntries(), resolver));
////				}
////			}								
////			tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator());
////			
////			if (isGenerateTableOfContents(resource, element, childEntries)) {
////				addContent(action, tocBuilder.toString());
////			}
//			
//			EObject doc = getDocumentation();
//			if (doc == null) {
//				String tooltip = modelElement.getTooltip();
//				if (!Util.isBlank(tooltip)) {
//					addContent(action, tooltip);
//				}
//				String embeddedDiagram = getEmbeddedDiagram();
//				if (!Util.isBlank(embeddedDiagram)) {
//					addContent(action, embeddedDiagram);
//				}
//				String toc = getTableOfContents();
//				if (!Util.isBlank(toc)) {
//					addContent(action, toc);
//				}
//			} else {					
//				action.getContent().add(doc);
//			}			
//			
//			// Adding child actions to respective roles
//			getSemanticChildrenInfo().forEach(childInfo -> {
//				org.nasdanika.graph.Element child = childInfo.getConfig().getElement();
//				if (child instanceof ModelElement) {
//					EReference childRole = resourceFactory.getRole((ModelElement) child);
//					if (childRole != null) {
//						List<EObject> childSemanticElements = childInfo.getProcessor().getSemanticElements();
//						Collection<EObject> childRoleElements = (Collection<EObject>) action.eGet(childRole);
//						childSemanticElements.forEach(childSemanticElement -> {
//							childRoleElements.add(childSemanticElement);						
//							Action childAction = (Action) childSemanticElement;
//							String childLocation = childAction.getLocation();
//							if (!Util.isBlank(childLocation) && URI.createURI(childLocation).isRelative()) {
//								childAction.setLocation(childRole.getName() + "/" + childLocation);
//							}
//						});
//						
//					}
//				}
//			});			
//		}
//	}
//	
//	/**
//	 * @return Diagram for embedding into documentaion using <code>${diagram}</code> token.
//	 * This implementation returns null, {@link RootProcessor} returns page diagram.
//	 */
//	protected Document getEmbeddedDiagramDocument() throws ParserConfigurationException {
//		Page linkedPage = getElement().getLinkedPage();
//		if (linkedPage == null) {
//			return null;
//		}
//		Root root = linkedPage.getModel().getRoot();
//		return ((ModelElementProcessor) registry.get(root).getProcessor()).getEmbeddedDiagramDocument();
//	}
//	
//	protected String getTableOfContents() {
//		// TODO
//		return null;
//	}	
	
//	protected String getEmbeddedDiagram() {
//		try {
//			Document embeddedDiagramDocument = getEmbeddedDiagramDocument();
//			embeddedDiagramDocument.accept(this::processEmbeddedDiagramElement, resourceFactory.getConnectionBase());		
//			return embeddedDiagramDocument.toHtml(null, resourceFactory.getDiagramViewer());
//		} catch (Exception  e) {
//			return "Error embedding diagram: " + e;
//		}				
//	}
//	
//	/**
//	 * Processes an element of the embedded diagram. 
//	 * This implementation adds a link. Override for additional behavior, e.g. change background color based on external information.
//	 * @param element
//	 * @param action
//	 * @param resolver
//	 */
//	protected void processEmbeddedDiagramElement(Element element) {		
//		if (element instanceof ModelElement) {
//			ModelElement modelElement = (ModelElement) element;
//			if (getElement().equals(modelElement) || modelElement.isPageLink()) {
//				modelElement.setLink(null);
//			}
//			if (Util.isBlank(modelElement.getLink())) {
//				String link = getModelElementLink(modelElement);
//				if (!Util.isBlank(link)) {
//					modelElement.setLink(link);
//				}				
//			}
//		}		
//	}
//	
//	
//	protected EObject getDocumentation() {
//		String documentationProperty = resourceFactory.getDocumentationProperty();
//		if (Util.isBlank(documentationProperty)) {
//			return null;
//		}
//		
//		String docProp = (getElement()).getProperty(documentationProperty);
//		if (Util.isBlank(docProp)) {
//			return null;
//		}
//		
//		URI docURI = resolveDocumentationBaseURI();			
//		
//		Markdown markdown = ContentFactory.eINSTANCE.createMarkdown();
//		org.nasdanika.exec.content.Resource docResource = ContentFactory.eINSTANCE.createResource();
//		docResource.setLocation(docURI.toString());
//		markdown.setSource(docResource);						
//		markdown.setStyle(true);
//
//		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();			
//		interpolator.setSource(markdown);
//		
//		String embeddedDiagram = getEmbeddedDiagram();
//				
//		String tableOfContents = getTableOfContents();
//		if (Util.isBlank(embeddedDiagram) && Util.isBlank(tableOfContents)) {
//			return interpolator;
//		}
//		
//		Configurator configurator = ExecFactory.eINSTANCE.createConfigurator();
//		configurator.setTarget(interpolator);
//		
//		EMap<String, EObject> properties = configurator.getProperties();
//		if (!Util.isBlank(embeddedDiagram)) {
//			Text diagramText = ContentFactory.eINSTANCE.createText();
//			diagramText.setContent(embeddedDiagram);
//			properties.put("diagram", diagramText);
//		}
//		
//		if (!Util.isBlank(tableOfContents)) {
//			Text tocText = ContentFactory.eINSTANCE.createText();
//			tocText.setContent(tableOfContents);
//			properties.put("toc", tocText);
//		}
//		
//		return configurator;			
//	}	

//	/**
//	 * Resolves link to the model element from this action.
//	 * @param modelElement Model element
//	 * @param action Source action 
//	 * @param resolver Resolver
//	 * @return
//	 */
//	protected String getModelElementLink(ModelElement modelElement) {
//		if (resourceFactory.shallCreateLink(modelElement)) {
//			// Link by cross-reference
//			String crossReferenceProperty = resourceFactory.getCrossReferenceProperty();
//			if (!Util.isBlank(crossReferenceProperty)) {
//				String crossReferencePropertyValue = modelElement.getProperty(crossReferenceProperty);
//				if (!Util.isBlank(crossReferencePropertyValue)) {
//					Optional<ElementProcessor> processorOptional = registry
//						.entrySet()
//						.stream()
//						.filter(e -> resourceFactory.match(e.getKey(), crossReferencePropertyValue))
//						.map(e -> e.getValue().getProcessor())
//						.findFirst();
//						
//					if (processorOptional.isPresent()) {
//						return processorOptional.get().resolveRelativeURI(this);
//					}
//				}
//			}
//			
//			// Link by ID equality
//			Optional<ElementProcessor> processorOptional = registry
//					.entrySet()
//					.stream()
//					.filter(e -> e.getKey() instanceof ModelElement && Objects.equals(modelElement.getId(), ((ModelElement) e.getKey()).getId()))
//					.map(e -> e.getValue().getProcessor())
//					.findFirst();
//					
//				if (processorOptional.isPresent()) {
//					return processorOptional.get().resolveRelativeURI(this);
//				}
//		}
//		return null;
//	}
//	
//	@Override
//	public String resolveRelativeURI(ElementProcessor base) {
//		if (action != null) {
//			
//		}
//		return super.resolveRelativeURI(base);
//	}
	
//
//	
//	protected String generateTableOfContents(
//			Action pageAction, 
//			ModelElement modelElement, 
//			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,
//			Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {		
//		
//		String label = getModelElementLabel(modelElement);
//		StringBuilder ret = new StringBuilder();
//		if (Util.isBlank(label)) {
//			if (modelElement instanceof Layer && childEntries != null) {
//				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) {
//					ret.append(generateTableOfContents(pageAction, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
//				}
//			}
//		} else {		
//			ret.append("<li>").append(System.lineSeparator());
//			String link = getModelElementLink(modelElement, pageAction, resolver);
//			if (Util.isBlank(link)) {
//				ret.append(label);
//			} else {
//				ret
//					.append("<a href=\"")
//					.append(link)
//					.append("\">")
//					.append(label)
//					.append("</a>");				
//			}			
//			
//			String tooltip = modelElement.getTooltip();
//			if (!Util.isBlank(tooltip)) {
//				ret.append(" - ").append(tooltip).append(System.lineSeparator());
//			}
//
//			if (modelElement instanceof Layer) {
//				if (childEntries != null && !childEntries.isEmpty()) {
//					ret.append(getListOpenTag(modelElement)).append(System.lineSeparator());
//					for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) {
//						ret.append(generateTableOfContents(pageAction, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
//					}
//					ret.append(getListCloseTag(modelElement)).append(System.lineSeparator());
//				}
//			}
//			ret.append("</li>").append(System.lineSeparator());
//		}
//		
//		return ret.toString();
//	}
	
//	protected EObject getDocumentation(
//			Resource resource, 
//			Element element, 
//			String embeddedDiagram,
//			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,			
//			String tableOfContents) {
//	
//		if (element instanceof Page) {
//			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, (Page) element, childEntries);
//			return getDocumentation(resource, pageElementEntry.getKey(), embeddedDiagram, childEntries, tableOfContents);
//		}
//		
//		return null;
//	}
//	
//	protected Map.Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> findPageElementEntry(
//			Resource resource, 
//			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
//				
//		if (childEntries == null) {
//			return null;
//		}
//		
//		for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: childEntries.entrySet()) {
//			if (childEntry.getKey() instanceof ModelElement && isPageElement((ModelElement) childEntry.getKey())) {
//				return new Map.Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>>() {
//
//					@Override
//					public ModelElement getKey() {
//						return (ModelElement) childEntry.getKey();
//					}
//
//					@Override
//					public Map<Element, ElementEntry<Map<EReference, List<Action>>>> getValue() {
//						return childEntry.getValue().getChildEntries();
//					}
//
//					@Override
//					public Map<Element, ElementEntry<Map<EReference, List<Action>>>> setValue(Map<Element, ElementEntry<Map<EReference, List<Action>>>> value) {
//						throw new UnsupportedOperationException();
//					}
//
//				};				
//			}
//			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> ret = findPageElementEntry(resource, childEntry.getValue().getChildEntries());
//			if (ret != null) {
//				return ret;
//			}
//		}
//		
//		return null;
//	}
//	
//	
//	/**
//	 * 
//	 * @param resource
//	 * @param page
//	 * @param childEntries
//	 * @return The "main" element of the page. This implementation returns the root. TODO: Mind-maps - auto-detection (no inbound connections), property to mark the root element.  
//	 */
//	protected Map.Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> getPageElementEntry(
//			Resource resource, 
//			Page page, 
//			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
//				
//		Model model = page.getModel();
//		Map<Element, ElementEntry<Map<EReference, List<Action>>>> modelChildEntries = childEntries.get(model).getChildEntries();
//		Root root = model.getRoot();
//		Map<Element, ElementEntry<Map<EReference, List<Action>>>> rootChildEntries = modelChildEntries.get(root).getChildEntries();
//		
//		Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> ret = findPageElementEntry(resource, rootChildEntries);
//		if (ret != null) {
//			return ret;
//		}
//
//		return new Map.Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>>() {
//
//			@Override
//			public ModelElement getKey() {
//				return root;
//			}
//
//			@Override
//			public Map<Element, ElementEntry<Map<EReference, List<Action>>>> getValue() {
//				return rootChildEntries;
//			}
//
//			@Override
//			public Map<Element, ElementEntry<Map<EReference, List<Action>>>> setValue(Map<Element, ElementEntry<Map<EReference, List<Action>>>> value) {
//				throw new UnsupportedOperationException();
//			}
//
//		};
//		
//	}
//	
//	protected Action createPageAction(Resource resource, Page page,
//			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
//		Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(
//				resource, page, childEntries);
//		Action ret = pageElementEntry == null ? createAction(resource, page)
//				: createModelElementAction(resource, pageElementEntry.getKey(), pageElementEntry.getValue(), null);
//		if (Util.isBlank(ret.getText())) {
//			ret.setText(page.getName());
//		}
//		ret.setLocation("pages/" + page.getId() + "/index.html");
//		return ret;
//	}
//	
//
//	protected String generateEmbeddedDiagram(
//			Element element, 
//			Action action,			
//			Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
//		try {
//			Document toEmbed = Document.create(true, null);
//			Page page = (Page) element;
//			toEmbed.getPages().add(page);
//			
//			Consumer<Element> visitor = e -> processEmbeddedDiagramElement(e, action, resolver);			
//			toEmbed.accept(visitor, connectionBase);			
//			return toEmbed.toHtml(null, getDiagramViewer());
//		} catch (Exception  e) {
//			return "Error embedding diagram: " + e;
//		}
//	}
//	
//	/**
//	 * Processes an element of the embedded diagram. 
//	 * This implementation adds a link. Override for additional behavior, e.g. change background color based on external information.
//	 * @param element
//	 * @param action
//	 * @param resolver
//	 */
//	protected void processEmbeddedDiagramElement(
//			Element element, 
//			Action action,			
//			Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
//		
//		if (element instanceof ModelElement) {
//			ModelElement modelElement = (ModelElement) element;
//			String link = getModelElementLink(modelElement, action, resolver);
//			if (!Util.isBlank(link)) {
//				modelElement.setLink(link);
//			}
//		}		
//	}
	
//	@SuppressWarnings("unchecked")
//	@Override
//	protected void resolve(
//			Resource resource, 
//			Element element, 
//			Map<EReference, List<Action>> semanticElement,
//			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,
//			Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
//		
//						
//			// Adding URI resolver adapters
//			for (EObject root: resource.getContents()) {
//				if (root instanceof Action) {
//					addURIResolverAdapters((Action) root);
//				}
//			}
//		} else if (element instanceof Page) {
//			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, (Page) element, childEntries);
//
//			for (Action action: semanticElement.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(pageElementEntry.getKey(), a)).collect(Collectors.toList())) {
//				// Embedded diagram
//				String embeddedDiagram = generateEmbeddedDiagram(element, action, resolver);
//				if (isEmbedDiagram(resource, element, childEntries)) {															
//					addContent(action, embeddedDiagram);						
//				}
//
//				// TOC
//				StringBuilder tocBuilder = new StringBuilder(getListOpenTag(element)).append(System.lineSeparator());
//				for (Entry<Element, ElementEntry<Map<EReference, List<Action>>>> childEntry: pageElementEntry.getValue().entrySet()) { 
//					tocBuilder.append(generateTableOfContents(action, (ModelElement) childEntry.getKey(), childEntry.getValue().getChildEntries(), resolver));
//				}					
//				tocBuilder.append(getListCloseTag(element)).append(System.lineSeparator());
//				
//				if (isGenerateTableOfContents(resource, element, childEntries)) {
//					addContent(action, tocBuilder.toString());
//				}
//				
//				EObject doc = getDocumentation(resource, element, embeddedDiagram, childEntries, tocBuilder.toString());
//				if (doc == null) {
//					String rootTooltip = pageElementEntry.getKey().getTooltip();
//					if (!Util.isBlank(rootTooltip)) {
//						addContent(action, rootTooltip);
//					}
//				} else {
//					action.getContent().add(doc);
//				}					
//			}			
//		} else if (element instanceof ModelElement) {
//			for (Action action: semanticElement.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(element, a)).collect(Collectors.toList())) {
//				if (element instanceof Connection) {
//					// Source and target
//					Table table = BootstrapFactory.eINSTANCE.createTable();
//					table.getAttributes().put("style", createText("width:auto"));
//					
//					TableRow sourceRow = BootstrapFactory.eINSTANCE.createTableRow();
//					table.getRows().add(sourceRow);
//					
//					TableCell sourceHeader = BootstrapFactory.eINSTANCE.createTableCell();
//					sourceHeader.setHeader(true);
//					sourceRow.getCells().add(sourceHeader);
//					sourceHeader.getContent().add(createText("Source"));
//										
//					TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
//					sourceRow.getCells().add(sourceCell);
//					
//					Node source = ((Connection) element).getSource();
//					String sourceLabel = getModelElementLabel(source);
//					if (Util.isBlank(sourceLabel)) {
//						sourceLabel = "(unlabeled)";
//					} 
//					String sourceLink = getModelElementLink(source, action, resolver);
//					if (sourceLink == null) {
//						sourceCell.getContent().add(createText(sourceLabel));						
//					} else {
//						sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
//					}
//					
//					TableRow targetRow = BootstrapFactory.eINSTANCE.createTableRow();
//					table.getRows().add(targetRow);
//					
//					TableCell targetHeader = BootstrapFactory.eINSTANCE.createTableCell();
//					targetHeader.setHeader(true);
//					targetRow.getCells().add(targetHeader);
//					targetHeader.getContent().add(createText("Target"));
//										
//					TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
//					targetRow.getCells().add(targetCell);
//					
//					Node target = ((Connection) element).getTarget();
//					String targetLabel = getModelElementLabel(target);
//					if (Util.isBlank(targetLabel)) {
//						targetLabel = "(unlabeled)";
//					} 
//					String targetLink = getModelElementLink(target, action, resolver);
//					if (targetLink == null) {
//						targetCell.getContent().add(createText(targetLabel));						
//					} else {
//						targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
//					}					
//
//					action.getContent().add(table);
//				} else if (element instanceof Node) {
//					Node node = (Node) element;
//					EReference connectionsActionContainmentReference = getConnectionsActionContainmentReference(node);
//					if (connectionsActionContainmentReference != null) {
//						List<Connection> inboundConnections = node.getInboundConnections().stream().filter(c -> getConnectionRole(c) == null).collect(Collectors.toList());
//						if (!inboundConnections.isEmpty()) {
//							Table table = BootstrapFactory.eINSTANCE.createTable();
//							table.setBordered(true);
//							table.getAttributes().put("style", createText("width:auto"));
//							
//							TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
//							table.getRows().add(headerRow);
//												
//							TableCell labelHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							labelHeader.setHeader(true);
//							headerRow.getCells().add(labelHeader);
//							labelHeader.getContent().add(createText("Label"));
//	
//							TableCell sourceHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							sourceHeader.setHeader(true);
//							headerRow.getCells().add(sourceHeader);
//							sourceHeader.getContent().add(createText("Source"));
//	
//							TableCell tooltipHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							tooltipHeader.setHeader(true);
//							headerRow.getCells().add(tooltipHeader);
//							tooltipHeader.getContent().add(createText("Tooltip"));
//							
//							for (Connection inboundConnection: inboundConnections) { // TODO - sorting
//								TableRow connectionRow = BootstrapFactory.eINSTANCE.createTableRow();
//								table.getRows().add(connectionRow);
//													
//								TableCell labelCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(labelCell);
//								String connectionLabel = getModelElementLabel(inboundConnection);
//								if (Util.isBlank(connectionLabel) && hasDocumentation(resource, inboundConnection, childEntries)) {
//									connectionLabel = "(unlabeled)";
//								}
//								if (!Util.isBlank(connectionLabel)) {
//									String connectionLink = getModelElementLink(inboundConnection, action, resolver);
//									if (connectionLink == null) {
//										labelCell.getContent().add(createText(connectionLabel));						
//									} else {
//										labelCell.getContent().add(createText("<a href=\"" + connectionLink + "\">" + connectionLabel + "</a>"));												
//									}
//								}
//		
//								TableCell sourceCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(sourceCell);
//
//								Node source = inboundConnection.getSource();
//								String sourceLabel = getModelElementLabel(source);
//								if (Util.isBlank(sourceLabel)) {
//									sourceLabel = "(unlabeled)";
//								} 
//								String sourceLink = getModelElementLink(source, action, resolver);
//								if (sourceLink == null) {
//									sourceCell.getContent().add(createText(sourceLabel));						
//								} else {
//									sourceCell.getContent().add(createText("<a href=\"" + sourceLink + "\">" + sourceLabel + "</a>"));												
//								}
//		
//								TableCell tooltipCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(tooltipCell);
//								String connectionTooltip = inboundConnection.getTooltip();
//								if (!Util.isBlank(connectionTooltip)) {
//									tooltipCell.getContent().add(createText(connectionTooltip));
//								}								
//							}							
//							
//							Action inboundConnectionsAction = AppFactory.eINSTANCE.createAction();
//							inboundConnectionsAction.setText("Inbound connections");
//							if (connectionsActionContainmentReference == AppPackage.Literals.ACTION__SECTIONS) {
//								inboundConnectionsAction.setName("inbound-connections");
//							} else {
//								inboundConnectionsAction.setLocation("inbound-connections.html");								
//							}
//							inboundConnectionsAction.getContent().add(table);
//							((Collection<Action>) action.eGet(connectionsActionContainmentReference)).add(inboundConnectionsAction);
//						}
//						
//						List<Connection> outboundConnections = node.getOutboundConnections().stream().filter(c -> getConnectionRole(c) == null).collect(Collectors.toList());
//						if (!outboundConnections.isEmpty()) {
//							Table table = BootstrapFactory.eINSTANCE.createTable();
//							table.setBordered(true);
//							table.getAttributes().put("style", createText("width:auto"));
//							
//							TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
//							table.getRows().add(headerRow);
//												
//							TableCell labelHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							labelHeader.setHeader(true);
//							headerRow.getCells().add(labelHeader);
//							labelHeader.getContent().add(createText("Label"));
//	
//							TableCell targetHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							targetHeader.setHeader(true);
//							headerRow.getCells().add(targetHeader);
//							targetHeader.getContent().add(createText("Target"));
//	
//							TableCell tooltipHeader = BootstrapFactory.eINSTANCE.createTableCell();
//							tooltipHeader.setHeader(true);
//							headerRow.getCells().add(tooltipHeader);
//							tooltipHeader.getContent().add(createText("Tooltip"));
//							
//							for (Connection outboundConnection: outboundConnections) { // TODO - sorting
//								TableRow connectionRow = BootstrapFactory.eINSTANCE.createTableRow();
//								table.getRows().add(connectionRow);
//													
//								TableCell labelCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(labelCell);
//								String connectionLabel = getModelElementLabel(outboundConnection);
//								if (Util.isBlank(connectionLabel) && hasDocumentation(resource, outboundConnection, childEntries)) {
//									connectionLabel = "(unlabeled)";
//								}
//								if (!Util.isBlank(connectionLabel)) {
//									String connectionLink = getModelElementLink(outboundConnection, action, resolver);
//									if (connectionLink == null) {
//										labelCell.getContent().add(createText(connectionLabel));						
//									} else {
//										labelCell.getContent().add(createText("<a href=\"" + connectionLink + "\">" + connectionLabel + "</a>"));												
//									}
//								}
//		
//								TableCell targetCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(targetCell);
//
//								Node target = outboundConnection.getTarget();
//								String targetLabel = getModelElementLabel(target);
//								if (Util.isBlank(targetLabel)) {
//									targetLabel = "(unlabeled)";
//								} 
//								String targetLink = getModelElementLink(target, action, resolver);
//								if (targetLink == null) {
//									targetCell.getContent().add(createText(targetLabel));						
//								} else {
//									targetCell.getContent().add(createText("<a href=\"" + targetLink + "\">" + targetLabel + "</a>"));												
//								}
//		
//								TableCell tooltipCell = BootstrapFactory.eINSTANCE.createTableCell();
//								connectionRow.getCells().add(tooltipCell);
//								String connectionTooltip = outboundConnection.getTooltip();
//								if (!Util.isBlank(connectionTooltip)) {
//									tooltipCell.getContent().add(createText(connectionTooltip));
//								}								
//							}							
//							
//							Action outboundConnectionsAction = AppFactory.eINSTANCE.createAction();
//							outboundConnectionsAction.setText("Outbound connections");
//							if (connectionsActionContainmentReference == AppPackage.Literals.ACTION__SECTIONS) {							
//								outboundConnectionsAction.setName("outbound-connections");
//							} else {
//								outboundConnectionsAction.setLocation("outbound-connections.html");								
//							}
//							outboundConnectionsAction.getContent().add(table);
//							((Collection<Action>) action.eGet(connectionsActionContainmentReference)).add(outboundConnectionsAction);
//						}
//					}
//				}
//				
//			}
//		}
//	}
	
	

}
