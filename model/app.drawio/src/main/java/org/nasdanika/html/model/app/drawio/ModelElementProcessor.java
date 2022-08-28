package org.nasdanika.html.model.app.drawio;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.jsoup.Jsoup;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;

public abstract class ModelElementProcessor extends ElementProcessor {

	protected Action action;
//	protected String sort; // Inherit from semantic parent? 

	protected ModelElementProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
	}
		
	protected String getPath() {
		ModelElement modelElement = (ModelElement) config.getElement();
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
	
	protected String getIcon() {
		String iconProperty = resourceFactory.getIconProperty();
		ModelElement modelElement = (ModelElement) config.getElement();
		return Util.isBlank(iconProperty) ? null : modelElement.getProperty(iconProperty);
	}
	
	protected String getText() {
		String label = ((ModelElement) config.getElement()).getLabel();
		return Util.isBlank(label) ? null : Jsoup.parse(label).text(); 				
	}
	
	@Override
	public List<EObject> getSemanticElements() {
		return action == null ? super.getSemanticElements() : Collections.singletonList(action);
	}
	
	@Override
	public void createSemanticElements() {
		String text = getText();
		if (!Util.isBlank(text)) {
			action = AppFactory.eINSTANCE.createAction();
			action.setText(text);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void resolveSemanticElements(URI baseURI) {
		super.resolveSemanticElements(baseURI);		
		if (action != null) {
			String path = Objects.requireNonNull(getPath(), "Path is null for " + getText() + " / " + ((ModelElement) config.getElement()).getId());
			action.setLocation(path + "/index.html");
			
			// Adding child actions to respective roles
			getSemanticChildrenInfo().forEach(childInfo -> {
				org.nasdanika.graph.Element child = childInfo.getConfig().getElement();
				if (child instanceof ModelElement) {
					EReference childRole = resourceFactory.getRole((ModelElement) child);
					if (childRole != null) {
						List<EObject> childSemanticElements = childInfo.getProcessor().getSemanticElements();
						Collection<EObject> childRoleElements = (Collection<EObject>) action.eGet(childRole);
						childSemanticElements.forEach(childSemanticElement -> {
							childRoleElements.add(childSemanticElement);						
							Action childAction = (Action) childSemanticElement;
							String childLocation = childAction.getLocation();
							if (!Util.isBlank(childLocation)) {
								childAction.setLocation(childRole.getName() + "/" + childLocation);
							}
						});
						
					}
				}
			});			
		}
	}

	@Override
	protected Comparator<ProcessorInfo<ElementProcessor>> getSemanticChildrenComparator() {
		String sortProperty = resourceFactory.getSortProperty();
		String sort = null;
		if (!org.nasdanika.common.Util.isBlank(sortProperty)) {
			sort = ((ModelElement) config.getElement()).getProperty(sortProperty);
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

	
	protected String getModelElementLabel(ModelElement modelElement) {
		if (modelElement == null) {
			return null;
		}
		String label = modelElement.getLabel();
		return Util.isBlank(label) ? null : Jsoup.parse(label).text(); 		
	}
	
	
	
//	protected boolean hasDocumentation(Resource resource, Element element, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries) {
//		if (element instanceof Page) {
//			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, (Page) element, childEntries);
//			return hasDocumentation(resource, pageElementEntry.getKey(), childEntries);
//		}
//		if (element instanceof ModelElement) {
//			String documentationProperty = getDocumentationProperty();
//			if (Util.isBlank(documentationProperty)) {
//				return false;
//			}
//			
//			String docProp = ((ModelElement) element).getProperty(documentationProperty);
//			return !Util.isBlank(docProp);
//		}
//		
//		return false;
//	}
//	
//	protected EObject getDocumentation(
//			Resource resource, 
//			Element element, 
//			String embeddedDiagram,
//			Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries,			
//			String tableOfContents) {
//		if (element instanceof Page) {
//			Entry<ModelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>>> pageElementEntry = getPageElementEntry(resource, (Page) element, childEntries);
//			return getDocumentation(resource, pageElementEntry.getKey(), embeddedDiagram, childEntries, tableOfContents);
//		}
//		if (element instanceof ModelElement) {
//			String documentationProperty = getDocumentationProperty();
//			if (Util.isBlank(documentationProperty)) {
//				return null;
//			}
//			
//			String docProp = ((ModelElement) element).getProperty(documentationProperty);
//			if (Util.isBlank(docProp)) {
//				return null;
//			}
//			
//			Markdown markdown = ContentFactory.eINSTANCE.createMarkdown();
//			URI docURI = URI.createURI(docProp);			
//			URI base = resolveDocumentationBaseURI(resource, getSemanticParent((ModelElement) element));
//			if (base != null) {
//				docURI = docURI.resolve(base);
//			}
//			org.nasdanika.exec.content.Resource docResource = ContentFactory.eINSTANCE.createResource();
//			docResource.setLocation(docURI.toString());
//			markdown.setSource(docResource);						
//			markdown.setStyle(true);
//	
//			Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();			
//			interpolator.setSource(markdown);
//			
//			if (Util.isBlank(embeddedDiagram) && Util.isBlank(tableOfContents)) {
//				return interpolator;
//			}
//			
//			Configurator configurator = ExecFactory.eINSTANCE.createConfigurator();
//			configurator.setTarget(interpolator);
//			
//			EMap<String, EObject> properties = configurator.getProperties();
//			if (!Util.isBlank(embeddedDiagram)) {
//				Text diagramText = ContentFactory.eINSTANCE.createText();
//				diagramText.setContent(embeddedDiagram);
//				properties.put("diagram", diagramText);
//			}
//			
//			if (!Util.isBlank(tableOfContents)) {
//				Text tocText = ContentFactory.eINSTANCE.createText();
//				tocText.setContent(tableOfContents);
//				properties.put("toc", tocText);
//			}
//			
//			return configurator;			
//		}
//		
//		return null;
//	}
	

//	/**
//	 * Resolves link to the model element from this action.
//	 * @param modelElement Model element
//	 * @param action Source action 
//	 * @param resolver Resolver
//	 * @return
//	 */
//	protected String getModelElementLink(ModelElement modelElement, Action action, Function<Predicate<Element>, Map<EReference, List<Action>>> resolver) {
//		if (shallCreateLink(modelElement)) {
//			// Link by cross-reference
//			String crossReferenceProperty = getCrossReferenceProperty();
//			if (!Util.isBlank(crossReferenceProperty)) {
//				String crossReferencePropertyValue = modelElement.getProperty(crossReferenceProperty);
//				if (!Util.isBlank(crossReferencePropertyValue)) {
//					int colonIdx = crossReferencePropertyValue.indexOf(":");
//					if (colonIdx != -1) {
//						Predicate<Element> predicate = e -> {
//							if (e instanceof ModelElement) {
//								String prop = ((ModelElement) e).getProperty(crossReferencePropertyValue.substring(0, colonIdx));
//								if (!Util.isBlank(prop)) {
//									return prop.equals(crossReferencePropertyValue.substring(colonIdx + 1));
//								}
//							}
//							return false;
//						};
//						Map<EReference, List<Action>> actionMap = resolver.apply(predicate);
//						for (Action eAction: actionMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList())) {
//							Object uriResolverAdapter = EcoreUtil.getRegisteredAdapter(eAction, URIResolverAdapter.class);
//							if (uriResolverAdapter instanceof URIResolverAdapter) {
//								URI eURI = ((URIResolverAdapter) uriResolverAdapter).resolve(action);
//								if (eURI != null) {
//									return eURI.toString();
//								}
//							}
//						}
//					}
//				}
//			}
//			
//			// Link by equality
//			Map<EReference, List<Action>> actionMap = resolver.apply(modelElement::equals);
//			for (Action eAction: actionMap.values().stream().flatMap(Collection::stream).filter(a -> isActionForElement(modelElement, a)).collect(Collectors.toList())) {
//				Object uriResolverAdapter = EcoreUtil.getRegisteredAdapter(eAction, URIResolverAdapter.class);
//				if (uriResolverAdapter instanceof URIResolverAdapter) {
//					URI eURI = ((URIResolverAdapter) uriResolverAdapter).resolve(action);
//					if (eURI != null) {
//						return eURI.toString();
//					}
//				}
//			}
//		}
//		return null;
//	}
	
//	protected boolean isRootAction(Element element, Action action) {
//		if (action == null) {
//			return false;
//		}
//		Object elementAdapter = EcoreUtil.getRegisteredAdapter(action, ElementAdapter.class);					
//		if (elementAdapter instanceof ElementAdapter) {
//			 Element actionElement = ((ElementAdapter) elementAdapter).getElement();
//			 if (actionElement instanceof ModelElement) {
//				 String rootActionFlag = ((ModelElement) actionElement).getProperty(getRootActionFlagProperty());
//				 return Util.isBlank(rootActionFlag) || !"false".equals(rootActionFlag);
//			 }
//		}
//		
//		return true;
//	}

	
//	@SuppressWarnings("unchecked")
//	protected Action createModelElementAction(Resource resource, ModelElement modelElement, Map<Element, ElementEntry<Map<EReference, List<Action>>>> childEntries, EReference containmentReference) {
//		if (!shallCreateAction(resource, modelElement, childEntries, containmentReference)) {
//			return null;
//		}
//		Action ret = createAction(resource, modelElement);
//		ret.setId(modelElement.getId());
//		ret.setText(getModelElementLabel(modelElement));
//		String link = modelElement.getLink();
//		if (Util.isBlank(link) || modelElement.isPageLink()) {
//			String path = Objects.requireNonNull(getPath(modelElement), "Path is null for " + modelElement.getLabel() + " / " + modelElement.getId());
//			if (containmentReference == null) {
//				ret.setLocation(path + "/index.html");
//			} else {
//				ret.setLocation(containmentReference.getName() + "/" + path + "/index.html");
//			}
//		} else {
//			ret.setLocation(link);
//		}
//		ret.setDescription(modelElement.getTooltip());
//		String icon = getIcon(modelElement);
//		if (!Util.isBlank(icon)) {
//			ret.setIcon(icon);
//		}
//				
//		Map<EReference, List<Action>> accumulator = new HashMap<>();
//		for (ElementEntry<Map<EReference, List<Action>>> childEntry: childEntries.values()) {
//			for (Entry<EReference, List<Action>> referenceEntry: childEntry.getSemanticElement().entrySet()) {
//				accumulator.computeIfAbsent(referenceEntry.getKey(), r -> new ArrayList<>()).addAll(referenceEntry.getValue());
//			}
//		}
//		
//		for (Entry<EReference, List<Action>> re: accumulator.entrySet()) {
//			((Collection<Action>) ret.eGet(re.getKey())).addAll(re.getValue());
//		}
//		
//		return ret;
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
	

}
