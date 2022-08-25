package org.nasdanika.html.model.app.drawio;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;

public class RootProcessor extends ModelElementProcessor {

	protected Action action;
	
	public RootProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
	}
	
	@Override
	public List<EObject> getSemanticElements() {
		return action == null ? super.getSemanticElements() : Collections.singletonList(action);
	}
	
	@Override
	public void createSemanticElements() {
		action = AppFactory.eINSTANCE.createAction();
		Root root = (Root) config.getElement();
		action.setText(root.getModel().getPage().getName());
		action.setLocation("${base-uri}index.html");	
	}
	
	
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
