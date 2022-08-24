package org.nasdanika.html.model.app.drawio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jsoup.Jsoup;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.ExecFactory;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.drawio.AppDrawioResourceFactory.URIResolverAdapter;

public abstract class ModelElementProcessor extends ElementProcessor {

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

}
