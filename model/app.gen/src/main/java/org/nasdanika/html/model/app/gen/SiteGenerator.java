package org.nasdanika.html.model.app.gen;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Status;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.comparators.LabelModelElementComparator;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.ReconcileAction;
import org.nasdanika.exec.resources.ResourcesFactory;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.emf.NcoreActionBuilder;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.html.gen.ContentConsumer;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.resources.FileSystemContainer;

import com.redfin.sitemapgenerator.ChangeFreq;

public class SiteGenerator {
	
	/**
	 * Creates a resource set for loading models.
	 * Override to customize, e.g. register {@link EPackage}'s and adapter factories.
	 * @param progressMonitor
	 * @return
	 */
	protected ResourceSet createResourceSet(Context context, ProgressMonitor progressMonitor) {
		return Util.createResourceSet(context, progressMonitor);
	}	
	
	/**
	 * Generates a resource model from an action model.
	 * @throws IOException 
	 * @throws Exception
	 */
	protected Resource generateResourceModel(
			Action root, 
			Map<EObject, Label> registry,
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			URI resourceURI, 
			String containerName,
			File resourceWorkDir,
			Context context, 
			ProgressMonitor progressMonitor) throws IOException {
		
		java.util.function.Consumer<Diagnostic> diagnosticConsumer = diagnostic -> {
			if (diagnostic.getStatus() == Status.FAIL || diagnostic.getStatus() == Status.ERROR) {
				System.err.println("***********************");
				System.err.println("*      Diagnostic     *");
				System.err.println("***********************");
				diagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
			}
			if (diagnostic.getStatus() != Status.SUCCESS) {
				throw new DiagnosticException(diagnostic);
			};
		};
		
		Container container = ResourcesFactory.eINSTANCE.createContainer();
		container.setName(containerName);
		container.setReconcileAction(ReconcileAction.OVERWRITE);
		
		ResourceSet resourceSet = createResourceSet(context, progressMonitor);
		Resource modelResource = resourceSet.createResource(resourceURI);
		modelResource.getContents().add(container);
		
		// Generating content file from action content 
		
		File pagesDir = new File(resourceWorkDir, "pages");
		pagesDir.mkdirs();
		
		JSONObject semanticMap = semanticMap(root, registry, context);
		if (semanticMap != null) {
			org.nasdanika.exec.resources.File semanticMapFile = container.getFile("semantic-map.json");
			Text semanticMapText = ContentFactory.eINSTANCE.createText();
			semanticMapText.setContent(semanticMap.toString(4));
			semanticMapFile.getContents().add(semanticMapText);
		}
		
		Util.generateSite(
				root, 
				pageTemplate,
				container,
				contentProviderContext -> (cAction, uriResolver, pMonitor) -> getActionContent(cAction, uriResolver, registry, resourceWorkDir, contentProviderContext, diagnosticConsumer, pMonitor),
				contentProviderContext -> (page, baseURI, uriResolver, pMonitor) -> getPageContent(page, baseURI, uriResolver, pagesDir, contentProviderContext, progressMonitor),
				Context.singleton("semantic-map", semanticMap.toString()).compose(context),
				progressMonitor);
		
		modelResource.save(null);
		
		// Page model to XML conversion
		ResourceSet pageResourceSet = new ResourceSetImpl();
		pageResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());			
		pageResourceSet.getAdapterFactories().add(new AppAdapterFactory());
		for (File pageFile: pagesDir.listFiles(f -> f.getName().endsWith(".xml"))) {
			URI pageURI = URI.createFileURI(pageFile.getCanonicalPath());
			Resource pageEResource = pageResourceSet.getResource(pageURI, true);
			SupplierFactory<InputStream> contentFactory = org.nasdanika.common.Util.asInputStreamSupplierFactory(pageEResource.getContents());			
			try (InputStream contentStream = org.nasdanika.common.Util.call(contentFactory.create(context), progressMonitor, diagnosticConsumer, Status.FAIL, Status.ERROR)) {
				Files.copy(contentStream, new File(pageFile.getCanonicalPath().replace(".xml", ".html")).toPath(), StandardCopyOption.REPLACE_EXISTING);
				progressMonitor.worked(1, "[Page xml -> html] " + pageFile.getName());
			}
		}
		
		return modelResource;
	}

	/**
	 * Creates a semantic map of semantic URI's to text, tooltip, and location of pages corresponding to the URI.
	 * Override to create aggregated semantic maps.
	 * @param root
	 * @param registry
	 * @param context
	 * @return
	 */
	protected JSONObject semanticMap(Action root, Map<EObject, Label> registry, Context context) {
		// Semantic map
		JSONObject semanticMap = new JSONObject();		
		URI baseURI = URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/semantic-map.json");
		Context semanticMapContext = Context.singleton(Context.BASE_URI_PROPERTY, baseURI).compose(context);
		BiFunction<Label, URI, URI> uriResolver = org.nasdanika.html.model.app.util.Util.uriResolver(root, semanticMapContext);				
		
		for (Entry<EObject, Label> re: registry.entrySet()) {
			for (URI uri: NcoreUtil.getUris(re.getKey())) {
				Label label = re.getValue();
				if (label != null) {
					JSONObject value = new JSONObject();
					String labelText = label.getText();
					if (!org.nasdanika.common.Util.isBlank(labelText)) {
						value.put("text", labelText);
					}
					String labelTooltip = label.getTooltip();
					if (!org.nasdanika.common.Util.isBlank(labelTooltip)) {
						value.put("tooltip", labelTooltip);
					}
					if (label instanceof Link) {					
						URI linkURI = uriResolver.apply(label, baseURI);
						if (linkURI != null) {
							value.put("location", linkURI.toString());
						}
					}
					
					semanticMap.put(uri.toString(), value);
				}
			}
		}
		return semanticMap;
	}
	
	/**
	 * Creates a file with .xml extension containing page contents resource model. Creates and returns a resource with .html extension. 
	 * A later processing step shall convert .xml to .html 
	 * @param page
	 * @param baseURI
	 * @param uriResolver
	 * @param pagesDir
	 * @param progressMonitor
	 * @return
	 */
	protected EList<EObject> getPageContent(
			org.nasdanika.html.model.bootstrap.Page page, 
			URI baseURI, 
			BiFunction<Label, URI, URI> uriResolver,
			File pagesDir,
			Context contentProviderContext,
			ProgressMonitor progressMonitor) {
		
		try {
			// Saving a page to .xml and creating a reference to .html; Pages shall be processed from .xml to .html individually.
			page.setUuid(UUID.randomUUID().toString());
			
			ResourceSet pageResourceSet = new ResourceSetImpl();
			pageResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());			
			URI pageURI = URI.createFileURI(new File(pagesDir, page.getUuid() + ".xml").getCanonicalPath());
			Resource pageEResource = pageResourceSet.createResource(pageURI);
			pageEResource.getContents().add(page);
			pageEResource.save(null);
			
			org.nasdanika.exec.content.Resource pageResource = ContentFactory.eINSTANCE.createResource();
			pageResource.setLocation("pages/" + page.getUuid() + ".html");
			progressMonitor.worked(1, "[Page content] " + page.getName() + " -> " + pageResource.getLocation());
			return ECollections.singletonEList(pageResource);
		} catch (IOException e) {
			throw new NasdanikaException(e);
		}		
	}
	
	/**
	 * Filters registry entries for inclusion into the search site map tree. 
	 * This implementation returns true if a semantic element is mapped to an action or one of its descendants is mapped to an active, i.e. an intermediate node is required to put that child into the tree.
	 * @param semanticElement
	 * @param label
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected boolean isSiteMapTreeNode(EObject semanticElement, Label label, Map<EObject, Label> registry) {
		// Action with location
		if (label instanceof Action && !org.nasdanika.common.Util.isBlank(((Action) label).getLocation())) {
			return true;
		}
		
		for (EReference eRef: semanticElement.eClass().getEAllReferences()) {
			if (isParentReference(eRef)) {
				Object eRefValue = semanticElement.eGet(eRef);
				for (Object ve: eRefValue instanceof Collection ? (Collection<Object>) eRefValue : Collections.singletonList(eRefValue)) {
					if (ve instanceof EObject && isSiteMapTreeNode((EObject) ve, registry.get(ve), registry)) {
						return true;
					}
				}
			}
		}
				
		return false;
	}
	
	/**
	 * Computes site map tree script - context property computer
	 * @param context
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String computeSiteMapTreeScript(
			Context context, 
			Action action, 
			BiFunction<Label, URI, URI> uriResolver,
			Map<EObject, Label> registry,
			ProgressMonitor progressMonitor) {
		// TODO - actions from action prototype, e.g. Ecore doc actions, to the tree.
		
		JsTreeFactory jsTreeFactory = context.get(JsTreeFactory.class, JsTreeFactory.INSTANCE);
		int maxLength = 50;
		Map<EObject, JsTreeNode> nodeMap = new HashMap<>();
		for (Entry<EObject, Label> re: registry.entrySet().stream().filter(e -> isSiteMapTreeNode(e.getKey(), e.getValue(), registry)).collect(Collectors.toList())) {
			Label treeLabel = re.getValue();			
			EObject key = re.getKey();
			if (treeLabel == null) {
				JsTreeNode jsTreeNode = jsTreeFactory.jsTreeNode();
				String nodeText;
				if (key instanceof NamedElement) {
					nodeText = StringEscapeUtils.escapeHtml4(((NamedElement) key).getName());
				} else {
					nodeText = key.eClass().getName();
				}
				if (org.nasdanika.common.Util.isBlank(nodeText)) {
					nodeText = "(blank)";
				}
				jsTreeNode.text(nodeText.length() > maxLength ? nodeText.substring(0, maxLength) + "..." : nodeText);
				nodeMap.put(key, jsTreeNode);
			} else {			
				Label label = treeLabel instanceof Link ? AppFactory.eINSTANCE.createLink() : AppFactory.eINSTANCE.createLabel();
				String treeLabelText = treeLabel.getText();
				if (org.nasdanika.common.Util.isBlank(treeLabelText)) {
					treeLabelText = "(blank)";
				}
				label.setText(treeLabelText.length() > maxLength ? treeLabelText.substring(0, maxLength) + "..." : treeLabelText);
				label.setIcon(treeLabel.getIcon());
				
				LabelJsTreeNodeSupplierFactoryAdapter<?> adapter;
				if (label instanceof Link) {
					URI bURI = uriResolver.apply(action, (URI) null);
					URI tURI = uriResolver.apply(treeLabel, bURI);
					if (tURI != null) {
						((Link) label).setLocation(tURI.toString());
					}
					adapter = new LinkJsTreeNodeSupplierFactoryAdapter<Link>((Link) label);
				} else {
					adapter = new LabelJsTreeNodeSupplierFactoryAdapter<>(label);				
				}
				
				try {
					JsTreeNode jsTreeNode = adapter.create(context).execute(progressMonitor);
					jsTreeNode.attribute(Util.DATA_NSD_LABEL_UUID_ATTRIBUTE, treeLabel.getUuid());
					nodeMap.put(key, jsTreeNode);
				} catch (Exception e) {
					throw new NasdanikaException(e);
				}
			}
		}
		
		// Organizing JsTreeNodes into a tree.
		Map<EObject, JsTreeNode> roots = new HashMap<>(nodeMap);
		
		Map<EObject,Map<String,List<JsTreeNode>>> refMap = new HashMap<>();
		for (EObject eObj: new ArrayList<>(nodeMap.keySet())) {
			Map<String,List<JsTreeNode>> rMap = new TreeMap<>();					
			for (EReference eRef: eObj.eClass().getEAllReferences()) {
				if (isParentReference(eRef)) {
					Object eRefValue = eObj.eGet(eRef);
					List<JsTreeNode> refNodes = new ArrayList<>();
					for (Object ve: eRefValue instanceof Collection ? (Collection<Object>) eRefValue : Collections.singletonList(eRefValue)) {
						JsTreeNode refNode = roots.remove(ve);
						if (refNode != null) {
							refNodes.add(refNode);
						}
					}
					if (!refNodes.isEmpty()) {
						rMap.put(org.nasdanika.common.Util.nameToLabel(eRef.getName()) , refNodes);
					}
				}
			}
			if (!rMap.isEmpty()) {
				refMap.put(eObj, rMap);
			}
		}
		
		for (Entry<EObject, JsTreeNode> ne: nodeMap.entrySet()) {
			Map<String, List<JsTreeNode>> refs = refMap.get(ne.getKey());
			if (refs != null) {
				for (Entry<String, List<JsTreeNode>> ref: refs.entrySet()) {
					JsTreeNode refNode = jsTreeFactory.jsTreeNode();
					refNode.text(ref.getKey());
					refNode.children().addAll(ref.getValue());
					ne.getValue().children().add(refNode);
				}
			}
		}
		
		JSONObject jsTree = jsTreeFactory.buildJsTree(roots.values());

		List<String> plugins = new ArrayList<>();
		plugins.add("state");
		plugins.add("search");
		JSONObject searchConfig = new JSONObject();
		searchConfig.put("show_only_matches", true);
		jsTree.put("search", searchConfig);
		jsTree.put("plugins", plugins); 		
		jsTree.put("state", Collections.singletonMap("key", "nsd-site-map-tree"));
		
		// Deletes selection from state
		String filter = NavigationPanelConsumerFactoryAdapter.CLEAR_STATE_FILTER + " tree.search.search_callback = (results, node) => results.split(' ').includes(node.original['data-nsd-label-uuid']);";
		
		return jsTreeFactory.bind("#nsd-site-map-tree", jsTree, filter, null).toString();					
	}
	
	protected boolean isParentReference(EReference eReference) {
		return eReference.isContainment() || eReference == NcorePackage.Literals.REFERENCE__TARGET;
	}
	
	/**
	 * Computes a table of contents for a Drawio element
	 * @param element
	 * @param context
	 * @return
	 */
	protected Object computeTableOfContents(org.nasdanika.drawio.Element element, Context context) {
		HTMLFactory htmlFactory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
		if (element instanceof org.nasdanika.drawio.Document) {
			List<Page> pages = ((org.nasdanika.drawio.Document) element).getPages();
			if (pages.size() == 1) {
				return computeTableOfContents(pages.get(0), context);
			}
			Tag ol = htmlFactory.tag(TagName.ol);
			for (Page page: pages) {
				Tag li = htmlFactory.tag(TagName.li, page.getName(), computeTableOfContents(page, context));
				ol.content(li);
			}
			return ol;
		}
		
		if (element instanceof Page) {
			List<Layer> layers = new ArrayList<>(((Page) element).getModel().getRoot().getLayers());
			if (layers.size() == 1) {
				return computeTableOfContents(layers.get(0), context);
			}
			Collections.reverse(layers);
			Tag ol = htmlFactory.tag(TagName.ol);
			for (Layer layer: layers) {
				if (org.nasdanika.common.Util.isBlank(layer.getLabel())) {
					ol.content(computeTableOfContents(layer, context));
				} else {
					Tag li = htmlFactory.tag(
							TagName.li, 
							org.nasdanika.common.Util.isBlank(layer.getLink()) || layer.getLinkedPage() != null ? layer.getLabel() : htmlFactory.tag(TagName.a, layer.getLabel()).attribute("href", layer.getLink()),
							org.nasdanika.common.Util.isBlank(layer.getTooltip()) ? "" : " - " + Jsoup.parse(layer.getTooltip()).text() ,
							computeTableOfContents(layer, context));
					ol.content(li);								
				}							
			}
			return ol;
		}
		
		if (element instanceof Layer) { // Including nodes
			List<LayerElement> layerElements = new ArrayList<>(((Layer) element).getElements());
			Collections.sort(layerElements, new LabelModelElementComparator(false));
			if (element instanceof org.nasdanika.drawio.Node) {
				List<LayerElement> outgoingConnections = new ArrayList<>(((org.nasdanika.drawio.Node) element).getOutgoingConnections());
				Collections.sort(outgoingConnections, new LabelModelElementComparator(false));
				layerElements.addAll(outgoingConnections);
			}
			
			Tag ol = htmlFactory.tag(TagName.ol);
			for (LayerElement layerElement: layerElements) {
				if (layerElement instanceof org.nasdanika.drawio.Node || (layerElement instanceof Connection && (((Connection) layerElement).getSource() == null || ((Connection) layerElement).getSource() == element))) {
					if (org.nasdanika.common.Util.isBlank(layerElement.getLabel())) { 
						ol.content(computeTableOfContents(layerElement, context));
					} else {
						Tag li = htmlFactory.tag(
								TagName.li,
								org.nasdanika.common.Util.isBlank(layerElement.getLink()) || layerElement.getLinkedPage() != null ? Jsoup.parse(layerElement.getLabel()).text() : htmlFactory.tag(TagName.a, Jsoup.parse(layerElement.getLabel()).text()).attribute("href", layerElement.getLink()),										
								org.nasdanika.common.Util.isBlank(layerElement.getTooltip()) ? "" : " - " + Jsoup.parse(layerElement.getTooltip()).text() ,
										computeTableOfContents(layerElement, context));
						ol.content(li);								
					}
				}
			}
			return ol;						
		}
		
		return null; 		
	}
	
	/**
	 * Computes semantic link. This implementation uses the registry and uriResolver. 
	 * Override to add support of resolving external semantic links, e.g. loaded from semantic maps of dependency sites.
	 * @param context
	 * @param key
	 * @param path
	 * @param action
	 * @param baseSemanticURI
	 * @param uriResolver
	 * @param registry
	 * @return
	 */
	protected String computeSemanticLink(
			Context context, 
			String key, 
			String path, 
			Action action,
			URI baseSemanticURI,
			BiFunction<Label, URI, URI> uriResolver,
			Map<EObject, Label> registry) {
		int spaceIdx = path.indexOf(' ');
		URI targetURI = URI.createURI(spaceIdx == -1 ? path : path.substring(0, spaceIdx));
		if (baseSemanticURI != null && targetURI.isRelative()) {
			targetURI = targetURI.resolve(baseSemanticURI.appendSegment(""));
		}	
		URI bURI = uriResolver.apply(action, (URI) null);						
		for (Entry<EObject, Label> registryEntry: registry.entrySet()) {
			for (URI semanticURI: NcoreUtil.getUris(registryEntry.getKey())) {
				if (Objects.equals(targetURI, semanticURI)) {
					Label targetLabel = registryEntry.getValue();
					HTMLFactory htmlFactory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
					URI targetActionURI = uriResolver.apply(targetLabel, bURI);
					Tag tag = htmlFactory.tag(targetActionURI == null ? TagName.span : TagName.a, spaceIdx == -1 ? targetLabel.getText() : path.substring(spaceIdx + 1));
					String targetActionTooltip = targetLabel.getTooltip();
					if (!org.nasdanika.common.Util.isBlank(targetActionTooltip)) {
						tag.attribute("title", targetActionTooltip);
					}
					if (targetActionURI != null) {
						tag.attribute("href", targetActionURI.toString());
					}
					return tag.toString(); 
				}
			}
		}
		return computeSemanticLink(targetURI, bURI, context);
	}
	
	/**
	 * Computes semantic links for external URI's. This implementation returns null;
	 * @param targetURI 
	 * @param baseURI Base URI to deresolve link's reference to make it relative.
	 * @param context
	 * @return
	 */
	protected String computeSemanticLink(URI targetURI, URI baseURI, Context context) { 
		return null;
	}	

	/**
	 * Computes semantic reference. This implementation uses the registry and uriResolver. 
	 * Override to add support of resolving external semantic references, e.g. loaded from semantic maps of dependency sites.
	 * @param context
	 * @param key
	 * @param path
	 * @param action
	 * @param baseSemanticURI
	 * @param uriResolver
	 * @param registry
	 * @return
	 */
	protected URI computeSemanticReferfence(
			Context context, 
			String key, 
			String path,
			Action action,
			URI baseSemanticURI,
			BiFunction<Label, URI, URI> uriResolver,
			Map<EObject, Label> registry) {

		URI targetURI = URI.createURI(path);
		if (baseSemanticURI != null && targetURI.isRelative()) {
			targetURI = targetURI.resolve(baseSemanticURI.appendSegment(""));
		}	
		URI bURI = uriResolver.apply(action, (URI) null);						
		for (Entry<EObject, Label> registryEntry: registry.entrySet()) {
			for (URI semanticURI: NcoreUtil.getUris(registryEntry.getKey())) {
				if (Objects.equals(targetURI, semanticURI)) {
					Label targetLabel = registryEntry.getValue();
					URI targetActionURI = uriResolver.apply(targetLabel, bURI);
					if (targetActionURI != null) {
						return targetActionURI;
					}
				}
			}
		}
		URI ref = computeSemanticReference(targetURI, context);		
		return ref == null || bURI == null ? ref : ref.deresolve(bURI, true, true, true);
	}

	/**
	 * Computes semantic links for external URI's. This implementation returns null;
	 * @param targetURI
	 * @param context
	 * @return
	 */
	protected URI computeSemanticReference(URI targetURI, Context context) { 
		return null;
	}	
	
	/**
	 * {@link ActionContentProvider} method
	 * @param action
	 * @param uriResolver
	 * @param progressMonitor
	 * @return
	 */
	protected EList<EObject> getActionContent(
			Action action, 
			BiFunction<Label, URI, URI> uriResolver,
			Map<EObject, Label> registry,
			File resourceWorkDir,
			Context context,
			java.util.function.Consumer<Diagnostic> diagnosticConsumer,
			ProgressMonitor progressMonitor) {

		List<Object> contentContributions = new ArrayList<>();
		
		Context actionContentContext = createActionContentContext(action, uriResolver, registry, (ContentConsumer) contentContributions::add, context, progressMonitor);
		
		File contentDir = new File(resourceWorkDir, "content");
		contentDir.mkdirs();
		
		String fileName = action.getUuid() + ".html";
		SupplierFactory<InputStream> contentFactory = org.nasdanika.common.Util.asInputStreamSupplierFactory(action.getContent());			
		try (InputStream contentStream = org.nasdanika.common.Util.call(contentFactory.create(actionContentContext), progressMonitor, diagnosticConsumer, Status.FAIL, Status.ERROR)) {
			if (contentContributions.isEmpty()) {
				Files.copy(contentStream, new File(contentDir, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
			} else {
				Stream<InputStream> pageBodyContributionsStream = contentContributions.stream().filter(Objects::nonNull).map(e -> {
					try {
						return DefaultConverter.INSTANCE.toInputStream(e.toString());
					} catch (IOException ex) {
						throw new NasdanikaException("Error converting element to InputStream: " + ex, ex);
					}
				});
				Stream<InputStream> concatenatedStream = Stream.concat(pageBodyContributionsStream, Stream.of(contentStream));
				Files.copy(org.nasdanika.common.Util.join(concatenatedStream), new File(contentDir, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			throw new NasdanikaException(e);
		}
		
		org.nasdanika.exec.content.Resource contentResource = ContentFactory.eINSTANCE.createResource();
		contentResource.setLocation("../content/" + fileName);
		progressMonitor.worked(1, "[Action content] " + action.getText() + " -> " + fileName);
		return ECollections.singletonEList(contentResource);					
	}
	
	
	/**
	 * Computes semantic link. This implementation uses the registry and uriResolver. 
	 * Override to add support of resolving external semantic links, e.g. loaded from semantic maps of dependency sites.
	 * @param context
	 * @param key
	 * @param path
	 * @param action
	 * @param baseSemanticURI
	 * @param uriResolver
	 * @param registry
	 * @return
	 */
	protected Object computeRepresentation(
			Map<String, Object> representations,
			Context context, 
			String key, 
			String path, 
			Action action,
			URI baseSemanticURI,
			BiFunction<Label, URI, URI> uriResolver,
			Map<EObject, Label> registry) {

		String[] pathSegments = path.split("/");
		if (pathSegments.length == 1) {
			// Non drawio representation, just a key
			return representations.get(path);
		}
		try {
			org.nasdanika.drawio.Document valueDocument = (org.nasdanika.drawio.Document) representations.get(pathSegments[0]);
			if (pathSegments.length == 2) {
				// Document
				if ("diagram".equals(pathSegments[1])) {
					return valueDocument.save(true);
				}
				
				if ("toc".equals(pathSegments[1])) {
					return String.valueOf(computeTableOfContents(valueDocument, context));
				}
				return null;
			} 
			
			if (pathSegments.length == 3) {
				// Page
				for (Page page: valueDocument.getPages()) {				
					org.nasdanika.drawio.Document pageDocument = org.nasdanika.drawio.Document.create(true, null);
					pageDocument.getPages().add(page);
					if ("diagram".equals(pathSegments[2])) {
						return pageDocument.save(true);
					}
					
					if ("toc".equals(pathSegments[2])) {
						return String.valueOf(computeTableOfContents(pageDocument, context));
					}
				}
			}
		} catch (TransformerException | IOException | ParserConfigurationException e) {
			throw new NasdanikaException(e);
		}
		
		return null;
		
	}
	

	/**
	 * Registers semantic-link and semantic-ref property computers
	 * @param action
	 * @param uriResolver
	 * @param registry
	 * @param mctx
	 */
	protected Context createActionContentContext(
			Action action, 
			BiFunction<Label, URI, URI> uriResolver,
			Map<EObject, Label> registry,
			ContentConsumer contentConsumer,
			Context context,
			ProgressMonitor progressMonitor) {
		
		MutableContext mctx = context.fork();
		mctx.put("nsd-site-map-tree-script", (Function<Context, String>) ctx -> computeSiteMapTreeScript(ctx, action, uriResolver, registry, progressMonitor));
		
		if (contentConsumer != null) {
			mctx.register(ContentConsumer.class, contentConsumer);
		}
		
		Optional<URI> baseSemanticURI = registry
				.entrySet()
				.stream()
				.filter(e -> e.getValue() != null && Objects.equals(e.getValue().getUuid(), action.getUuid()))
				.flatMap(e -> NcoreUtil.getUris(e.getKey()).stream())
				.filter(Objects::nonNull)
				.filter(u -> !u.isRelative() && u.isHierarchical())
				.findFirst();									
		
		Map<String, Object> representations = NcoreActionBuilder.resolveRepresentationLinks(action, uriResolver, progressMonitor);
		
		PropertyComputer representationsPropertyComputer = new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T compute(Context ctx, String key, String path, Class<T> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					return (T) computeRepresentation(representations, ctx, key, path, action, baseSemanticURI.orElse(null), uriResolver, registry);			
				}
				return null;
			}
		}; 
		
		mctx.put("representations", representationsPropertyComputer);
		
		PropertyComputer semanticLinkPropertyComputer = new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T compute(Context ctx, String key, String path, Class<T> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					return (T) computeSemanticLink(ctx, key, path, action, baseSemanticURI.orElse(null), uriResolver, registry);			
				}
				return null;
			}
		}; 
		
		mctx.put("semantic-link", semanticLinkPropertyComputer);
					
		PropertyComputer semanticReferencePropertyComputer = new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T compute(Context propertyComputerContext, String key, String path, Class<T> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					URI sRef = computeSemanticReferfence(propertyComputerContext, key, path, action, baseSemanticURI.orElse(null), uriResolver, registry);
					return sRef == null ? null : (T) sRef.toString();
				}
				return null;
			}
		};
		
		mctx.put("semantic-ref", semanticReferencePropertyComputer);
		
		return mctx;
	}	
	
	/**
	 * Generates files from the previously generated resource model.
	 * @throws org.eclipse.emf.common.util.DiagnosticException 
	 * @throws IOException 
	 * @throws Exception
	 */
	protected Map<String, Collection<String>> generateContainer(
			Resource resourceModel,
			File workDir,
			File outputDir,
			Predicate<String> cleanPredicate,
			String siteMapDomain,
			String containerName, 
			Context context, 
			ProgressMonitor progressMonitor) throws org.eclipse.emf.common.util.DiagnosticException, IOException {
		Map<String, Collection<String>> errors = new TreeMap<>();
		
		Util.generateContainer(
				resourceModel, 
				new FileSystemContainer(workDir), 
				context, 
				progressMonitor);
		
		org.nasdanika.common.Util.copy(new File(workDir, containerName), outputDir, true, cleanPredicate, null);
		
		Util.generateSitemapAndSearch(
				outputDir, 
				siteMapDomain, 
				this::isSiteMap, 
				getChangeFrequency(), 
				this::isSearch, 
				(path, error) ->  {
					progressMonitor.worked(Status.ERROR, 1, "[" + path +"] " + error);
					errors.computeIfAbsent(path, p -> new ArrayList<>()).add(error);
				});		
		
		return errors;
	}
	
	protected ChangeFreq getChangeFrequency() {
		return ChangeFreq.WEEKLY;
	}
	
	protected boolean isSiteMap(File file, String path) {
		return path.endsWith(".html");
	}
	
	protected boolean isSearch(File file, String path) {
		return path.endsWith(".html") && !"search.html".equals(path);
	}
	
}
