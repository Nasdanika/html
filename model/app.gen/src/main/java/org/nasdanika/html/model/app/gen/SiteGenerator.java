package org.nasdanika.html.model.app.gen;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.notify.Notifier;
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
import org.json.JSONArray;
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
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.comparators.LabelModelElementComparator;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.ReconcileAction;
import org.nasdanika.exec.resources.ResourcesFactory;
import org.nasdanika.graph.Element;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.emf.NcoreActionBuilder;
import org.nasdanika.html.emf.ResolutionListener;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.html.gen.ContentConsumer;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.ContainerInfo;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.ncore.util.SemanticIdentity;
import org.nasdanika.ncore.util.SemanticInfo;
import org.nasdanika.ncore.util.SemanticMap;
import org.nasdanika.persistence.Marker;
import org.nasdanika.resources.FileSystemContainer;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

import com.redfin.sitemapgenerator.ChangeFreq;

public class SiteGenerator {
	
	/**
	 * Creates and configures a resource set for loading models.
	 * Override to customize, e.g. register {@link EPackage}'s and adapter factories.
	 * @param progressMonitor
	 * @return
	 */
	protected ResourceSet createResourceSet(Context context, ProgressMonitor progressMonitor) {
		ResourceSet resourceSet = Util.createResourceSet(context, progressMonitor);
		for (SiteGeneratorContributor contributor: getContributors()) {
			contributor.configureResourceSet(resourceSet, context, progressMonitor);
		}
		return resourceSet;
	}
	
	/**
	 * Creates a resource set for loading resource model.
	 * Override to customize, e.g. register {@link EPackage}'s and adapter factories.
	 * @param progressMonitor
	 * @return
	 */
	protected ResourceSet createResourceModelResourceSet(Context context, ProgressMonitor progressMonitor) {
		ResourceSet resourceSet = createResourceSet(context, progressMonitor);
		for (SiteGeneratorContributor contributor: getContributors()) {
			contributor.configureResourceModelResourceSet(resourceSet, context, progressMonitor);
		}
		return resourceSet;
	}	
	
	/**
	 * Creates a resource set for loading action model.
	 * Override to customize, e.g. register {@link EPackage}'s and adapter factories.
	 * @param progressMonitor
	 * @return
	 */
	protected ResourceSet createActionModelResourceSet(Context context, ProgressMonitor progressMonitor) {
		ResourceSet resourceSet = createResourceSet(context, progressMonitor);
		for (SiteGeneratorContributor contributor: getContributors()) {
			contributor.configureActionModelResourceSet(resourceSet, context, progressMonitor);
		}
		return resourceSet;
	}	
		
	/**
	 * Generates a resource model from an action model.
	 * @throws IOException 
	 * @throws Exception
	 */
	protected Resource generateResourceModel(
			Action root, 
			Iterable<Map.Entry<SemanticInfo, ?>> semanticInfoSource,
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			URI resourceURI, 
			String containerName,
			File resourceWorkDir,
			ResolutionListener resolutionListener,						
			Context context, 
			ProgressMonitor progressMonitor) throws IOException {
		
		for (SiteGeneratorContributor contributor: getContributors()) {
			contributor.processRootAction(root, context, progressMonitor);
		}
		
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
		
		ResourceSet resourceSet = createResourceModelResourceSet(context, progressMonitor);
		Resource modelResource = resourceSet.createResource(resourceURI);
		modelResource.getContents().add(container);
		
		// Generating content file from action content 
		
		File pagesDir = new File(resourceWorkDir, "pages");
		pagesDir.mkdirs();
		
		JSONArray semanticInfo = semanticInfo(root, semanticInfoSource, context);
		if (semanticInfo != null) {
			org.nasdanika.exec.resources.File semanticMapFile = container.getFile("semantic-info.json");
			Text semanticMapText = ContentFactory.eINSTANCE.createText();
			semanticMapText.setContent(semanticInfo.toString(4));
			semanticMapFile.getContents().add(semanticMapText);
		}
		
		Util.generateSite(
				root, 
				pageTemplate,
				container,
				contentProviderContext -> (cAction, uriResolver, pMonitor) -> getActionContent(cAction, semanticInfoURIResolver(uriResolver), semanticInfoSource, resourceWorkDir, contentProviderContext, diagnosticConsumer, resolutionListener, pMonitor),
				contentProviderContext -> (page, baseURI, uriResolver, pMonitor) -> getPageContent(page, baseURI, semanticInfoURIResolver(uriResolver), pagesDir, contentProviderContext, progressMonitor),
				Context.singleton("semantic-info", semanticInfo.toString()).compose(context),
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
		
		for (SiteGeneratorContributor contributor: getContributors()) {
			contributor.processResourcecModel(modelResource, context, progressMonitor);
		}
		
		return modelResource;
	}
	
	protected BiFunction<Label, URI, URI> semanticInfoURIResolver(BiFunction<Label, URI, URI> uriResolver) {		
		return (label, base) -> {
			URI ret = uriResolver.apply(label, base);
			return ret == null ? resolveSemanticInfo(label, base) : ret;
		};
	}
	
	protected boolean isSemanticInfoLink(Link link) {
		return false;
	}

	/**
	 * Resolves URI from a semantic info (external definitions)
	 * @param label
	 * @param base
	 * @return
	 */
	protected URI resolveSemanticInfo(Label label, URI base) {
		if (label instanceof Link && isSemanticInfoLink((Link) label)) {
			String linkLocation = ((Link) label).getLocation();
			if (!org.nasdanika.common.Util.isBlank(linkLocation)) {
				URI locationURI = URI.createURI(linkLocation);
				return base == null || locationURI == null ? locationURI : locationURI.deresolve(base, true, true, true);
			}
		}
		return null;
	}

	/**
	 * Creates a semantic info array.
	 * Override to create aggregated semantic maps.
	 * @param root
	 * @param registry
	 * @param context
	 * @return
	 */
	protected JSONArray semanticInfo(Action root, Iterable<Map.Entry<SemanticInfo, ?>> semanticInfoSource, Context context) {
		// Semantic info
		JSONArray semanticInfo = new JSONArray();		
		URI baseURI = URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/");
		Context semanticMapContext = Context.singleton(Context.BASE_URI_PROPERTY, baseURI).compose(context);
		BiFunction<Label, URI, URI> uriResolver = semanticInfoURIResolver(org.nasdanika.html.model.app.util.Util.uriResolver(root, semanticMapContext));		

		if (semanticInfoSource != null) {
			for (Entry<SemanticInfo, ?> nextEntry: semanticInfoSource) {
				if (nextEntry != null) {
					Object nextValue = nextEntry.getValue();
					if (nextValue instanceof Label) {
						Label label = (Label) nextValue;
						SemanticInfo semanticElementAnnotation = nextEntry.getKey();
						if (semanticElementAnnotation != null) {
							JSONObject value = semanticElementAnnotation.toJSON();
							
							String labelText = label.getText();
							if (!org.nasdanika.common.Util.isBlank(labelText)) {
								value.put(ContainerInfo.NAME_KEY, labelText);
							}
							String labelTooltip = label.getTooltip();
							if (!org.nasdanika.common.Util.isBlank(labelTooltip)) {
								value.put(SemanticInfo.DESCRIPTION_KEY, labelTooltip);
							}
							String labelIcon = label.getIcon();
							if (!org.nasdanika.common.Util.isBlank(labelIcon)) {
								value.put(SemanticInfo.ICON_KEY, labelIcon);
							}				
							URI linkURI = uriResolver.apply(label, baseURI);
							if (linkURI != null) {
								value.put(SemanticInfo.LOCATION_KEY, linkURI.toString());
							}
							
							semanticInfo.put(value);								
						}				
					}
				}
			}
		}
		return semanticInfo;
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
	
	private static <K,V> Map.Entry<K, V> mapEntry(K key, V value) {
		return new Entry<K, V>() {

			@Override
			public K getKey() {
				return key;
			}

			@Override
			public V getValue() {
				return value;
			}

			@Override
			public V setValue(V value) {
				throw new UnsupportedOperationException();
			}
			
		};
	}
	
	// Helper method
	private static String containmentRef(SemanticIdentity se) {
		if (se instanceof SemanticInfo) {
			ContainerInfo cInfo = ((SemanticInfo) se).getContainerInfo();
			if (cInfo != null) {
				return cInfo.getReference();
			}
		}
		return null;
	}
	
	private static List<Entry<JsTreeNode, List<Entry<SemanticInfo, JsTreeNode>>>> createReferenceJsTreeNodes(
			Map<String, List<Entry<SemanticInfo, JsTreeNode>>> groupedByReference,
			JsTreeFactory jsTreeFactory) {
		
		return groupedByReference
			.entrySet()
			.stream()
			.sorted(SiteGenerator::compareReferenceEntries)
			.map(e -> createReferenceJsTreeNode(e, jsTreeFactory))
			.collect(Collectors.toList());			
	}
	
	private static int compareReferenceEntries(Entry<String, List<Entry<SemanticInfo, JsTreeNode>>> a, Entry<String, List<Entry<SemanticInfo, JsTreeNode>>> b) {
		if (a == b) {
			return 0;
		}
		if (a == null) {
			return 1;
		}
		
		if (b == null) {
			return -1;
		}
		
		String aRef = a.getKey();
		String bRef = b.getKey();
		
		if (org.nasdanika.common.Util.isBlank(aRef) && org.nasdanika.common.Util.isBlank(bRef)) {
			return 0;
		}

		if (org.nasdanika.common.Util.isBlank(aRef)) {
			return 1;
		}
		
		if (org.nasdanika.common.Util.isBlank(bRef)) {
			return -1;
		}
		
		return aRef.compareTo(bRef);		
	}
	
	/**
	 * Creates a {@link JsTreeNode} for a reference if refEntry is not null and reference name is not null. Links value nodes under the reference node
	 * @param refEntry
	 * @param jsTreeFactory
	 * @return
	 */
	private static Entry<JsTreeNode, List<Entry<SemanticInfo, JsTreeNode>>> createReferenceJsTreeNode(
			Entry<String, List<Entry<SemanticInfo, JsTreeNode>>> refEntry,
			JsTreeFactory jsTreeFactory) {
		
		if (refEntry == null) {
			return null;
		}
		
		if (org.nasdanika.common.Util.isBlank(refEntry.getKey())) {
			return new Entry<JsTreeNode, List<Entry<SemanticInfo,JsTreeNode>>>() {
				
				@Override
				public List<Entry<SemanticInfo, JsTreeNode>> setValue(List<Entry<SemanticInfo, JsTreeNode>> value) {
					throw new UnsupportedOperationException();
				}
				
				@Override
				public List<Entry<SemanticInfo, JsTreeNode>> getValue() {
					return refEntry.getValue();
				}
				
				@Override
				public JsTreeNode getKey() {
					return null;
				}
			};
		}
		
		JsTreeNode refNode = jsTreeFactory.jsTreeNode();
		refNode.text(org.nasdanika.common.Util.nameToLabel(refEntry.getKey()));
		for (Entry<SemanticInfo, JsTreeNode> ve: refEntry.getValue()) {
			refNode.children().add(ve.getValue());			
		}		
		
		return Map.entry(refNode, refEntry.getValue());
	}
		
	/**
	 * Computes site map tree script - context property computer
	 * @param context
	 * @return
	 */
	protected String computeSiteMapTreeScript(
			Context context, 
			Action action, 
			BiFunction<Label, URI, URI> uriResolver,
			Iterable<Map.Entry<SemanticInfo,?>> semanticInfoSource,
			ProgressMonitor progressMonitor) {
		// TODO - actions from action prototype, e.g. Ecore doc actions, to the tree.
		
		int maxLength = 50;
		SemanticMap<SemanticInfo,JsTreeNode> nodeMap = new SemanticMap<>();
		
		if (semanticInfoSource != null) {
			for (Entry<SemanticInfo, ?> entry: semanticInfoSource) {
				if (entry != null) {
					Object value = entry.getValue();
					if (value instanceof Label) {
						Label treeLabel = (Label) value;
						SemanticInfo semanticElementAnnotation = entry.getKey();
						if (semanticElementAnnotation != null) {
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
								nodeMap.put(semanticElementAnnotation, jsTreeNode);
							} catch (Exception e) {
								throw new NasdanikaException(e);
							}
						}
					}
				}
			}
		}
				
		JsTreeFactory jsTreeFactory = context.get(JsTreeFactory.class, JsTreeFactory.INSTANCE);
		SemanticMap<ContainerInfo, List<Entry<SemanticInfo, JsTreeNode>>> groupedByContainer = new SemanticMap<>();
		org.nasdanika.common.Util.groupBy(nodeMap.entrySet(), sea -> ((SemanticInfo) sea.getKey()).getContainerInfo(), groupedByContainer);
		SemanticMap<ContainerInfo, List<Entry<JsTreeNode, List<Entry<SemanticInfo, JsTreeNode>>>>> groupedByContainerAndReferenceName = new SemanticMap<>();
		groupedByContainer
			.entrySet()
			.stream()
			.map(e -> mapEntry(e.getKey(), org.nasdanika.common.Util.groupBy(e.getValue(), ve -> SiteGenerator.containmentRef(ve.getKey()))))
			.forEach(e -> groupedByContainerAndReferenceName.put(e.getKey(), createReferenceJsTreeNodes(e.getValue(), jsTreeFactory)));
		
		// Organizing JsTreeNodes into a tree - going over all SemanticInfo entries and moving containerInfo's from groupedByContainerAndReferenceName under
		List<Entry<SemanticInfo, JsTreeNode>> allEntries = groupedByContainerAndReferenceName
				.values()
				.stream()
				.flatMap(Collection::stream)
				.flatMap(e -> e.getValue().stream())
				.collect(Collectors.toList());
		
		for (Entry<SemanticInfo, JsTreeNode> entry: allEntries) {
			@SuppressWarnings("unlikely-arg-type")
			List<Entry<JsTreeNode, List<Entry<SemanticInfo, JsTreeNode>>>> children = groupedByContainerAndReferenceName.remove(entry.getKey());
			if (children != null) {
				for (Entry<JsTreeNode, List<Entry<SemanticInfo, JsTreeNode>>> childEntry: children) {
					if (childEntry != null) {
						JsTreeNode refNode = childEntry.getKey();
						List<JsTreeNode> entryValueChildren = entry.getValue().children();
						if (refNode == null) {
							List<Entry<SemanticInfo, JsTreeNode>> grandChildren = childEntry.getValue();
							if (grandChildren != null) {
								for (Entry<SemanticInfo, JsTreeNode> grandChild: grandChildren) {
									entryValueChildren.add(grandChild.getValue());
								}
							}
						} else {
							entryValueChildren.add(refNode);
						}
					}
				}
			}
		}
		
		List<JsTreeNode> roots = new ArrayList<>();
		for (Entry<JsTreeNode, List<Entry<SemanticInfo, JsTreeNode>>> re: groupedByContainerAndReferenceName.values().stream().flatMap(Collection::stream).collect(Collectors.toList())) {
			if (re != null) {
				JsTreeNode reKey = re.getKey();
				if (reKey == null) {
					for (Entry<SemanticInfo, JsTreeNode> childEntry: re.getValue()) {
						if (childEntry != null) {
							roots.add(childEntry.getValue());
						}
					}
				} else {
					roots.add(reKey);
				}
			}
		}		
		
		JSONObject jsTree = jsTreeFactory.buildJsTree(roots);

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
	 * Computes a info YAML dump for a Drawio element
	 * @param element
	 * @param context
	 * @return
	 */
	protected String computeInfo(Action action, org.nasdanika.drawio.Element element, Context context) {
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK); dumperOptions.setIndent(4);
		Map<String, Object> infoMap = new LinkedHashMap<>();
		Map<String, Object> actionMap = new LinkedHashMap<>();
		if (!org.nasdanika.common.Util.isBlank(action.getText())) {
			actionMap.put("text", action.getText());
		}
		List<URI> actionURIs = NcoreUtil.getIdentifiers(action);
		if (actionURIs.size() == 1) {
			actionMap.put("uri", actionURIs.get(0).toString());
		} else if (!actionURIs.isEmpty()) {
			actionMap.put("uris", actionURIs.stream().map(Object::toString).collect(Collectors.toList()));			
		}
		EList<org.nasdanika.ncore.Marker> actionMarkers = action.getMarkers();
		if (actionMarkers != null && !actionMarkers.isEmpty()) {
			actionMap.put("markers", actionMarkers.stream().map(am -> DefaultConverter.INSTANCE.toMap(am, null)).toArray());
		}
		infoMap.put("action", actionMap);
		Map<String, Object> elementInfo = element.accept((el, childInfo) -> this.elementInfo(el, childInfo, actionURIs));
		if (elementInfo != null) {
			infoMap.put("element", elementInfo);
		}
		StringWriter out = new StringWriter();
		try (out) {
			new Yaml(dumperOptions).dump(infoMap, out);
		} catch (Exception e) {
			return "Error computing representation info: " + e;			
		}		
		return out.toString();
	}
	
	private Map<String, Object> elementInfo(Element element, Map<? extends Element, Map<String, Object>> childInfo, List<URI> actionURIs) {
		Map<String, Object> info = new LinkedHashMap<>();
		info.put("type", element.getClass().getName());
		if (element instanceof org.nasdanika.drawio.Element) {
			org.nasdanika.drawio.Element drawioElement = (org.nasdanika.drawio.Element) element;
			URI uri = drawioElement.getURI();
			if (uri != null) {
				info.put("uri", uri.toString());
			}
			List<? extends Marker> markers = drawioElement.getMarkers();
			if (markers != null && !markers.isEmpty()) {
				info.put("markers", markers.stream().map(em -> em instanceof EObject ? DefaultConverter.INSTANCE.toMap((EObject) em, null) : String.valueOf(em)).toArray());				
			}
		}
		if (element instanceof org.nasdanika.drawio.Page) {
			org.nasdanika.drawio.Page page = (org.nasdanika.drawio.Page) element;
			String pageName = page.getName();
			if (!org.nasdanika.common.Util.isBlank(pageName)) {
				info.put("name", pageName);
			}
			String id = page.getId();
			if (!org.nasdanika.common.Util.isBlank(id)) {
				info.put("id", id);
			}
		}
		if (element instanceof org.nasdanika.drawio.ModelElement) {
			org.nasdanika.drawio.ModelElement modelElement = (org.nasdanika.drawio.ModelElement) element;
			String id = modelElement.getId();
			if (!org.nasdanika.common.Util.isBlank(id)) {
				info.put("id", id);
			}
			
			String label = modelElement.getLabel();
			if (!org.nasdanika.common.Util.isBlank(label)) {
				info.put("label", label);
			}
			
			String tooltip = modelElement.getTooltip();
			if (!org.nasdanika.common.Util.isBlank(tooltip)) {
				info.put("tooltip", tooltip);
			}
			
			if (modelElement.isPageLink()) {
				info.put("linked-page", modelElement.getLinkedPage().toString());
			} else {
				String link = modelElement.getLink();
				if (!org.nasdanika.common.Util.isBlank(link)) {
					info.put("link", link);
				}				
			}
			Map<String, String> style = modelElement.getStyle();
			if (style != null && !style.isEmpty()) {
				info.put("style", style);
			}
			
			Set<String> tags = modelElement.getTags();
			if (tags != null && !tags.isEmpty()) {
				info.put("tags", tags);
			}
			
			info.put("visible", modelElement.isVisible());		

			// Properties
			NamedNodeMap attributes = modelElement.getElement().getAttributes();
			if (attributes != null) {
				Map<String, String> properties = new LinkedHashMap<>();
				for (int i = 0; i < attributes.getLength(); ++i) {
					org.w3c.dom.Node attribute = attributes.item(i);
					if (attribute instanceof Attr) {
						String propertyName = ((Attr) attribute).getName();
						String propertyValue = modelElement.getProperty(propertyName);
						if (!org.nasdanika.common.Util.isBlank(propertyValue)) {
							properties.put(propertyName, propertyValue);
						}
					}
				}
				if (!properties.isEmpty()) {
					info.put("properties", properties);
				}
			}
			
			Collection<URI> elementActionURIs = NcoreActionBuilder.resolveURIs(NcoreActionBuilder.ACTION_URI_KEY, modelElement, actionURIs, false);
			if (elementActionURIs != null && !elementActionURIs.isEmpty()) {
				info.put("action-uris", elementActionURIs.stream().map(Object::toString).toArray());
			}
			
			Collection<URI> elementActionUriBases = NcoreActionBuilder.resolveURIs(NcoreActionBuilder.ACTION_URI_KEY, modelElement, actionURIs, true);
			if (elementActionUriBases != null && !elementActionUriBases.isEmpty()) {
				info.put("action-uri-bases", elementActionUriBases.stream().map(Object::toString).toArray());
			}
			
			Collection<URI> elementTargetURIs = NcoreActionBuilder.resolveURIs(NcoreActionBuilder.TARGET_URI_KEY, modelElement, actionURIs, false);
			if (elementTargetURIs != null && !elementTargetURIs.isEmpty()) {
				info.put("target-uris", elementTargetURIs.stream().map(Object::toString).toArray());
			}
			
			Collection<URI> elementTargetUriBases = NcoreActionBuilder.resolveURIs(NcoreActionBuilder.TARGET_URI_KEY, modelElement, actionURIs, true);
			if (elementTargetUriBases != null && !elementTargetUriBases.isEmpty()) {
				info.put("target-uri-bases", elementTargetUriBases.stream().map(Object::toString).toArray());
			}
			
		}
		if (element instanceof Connection) {
			Node source = ((Connection) element).getSource();
			if (source != null) {
				info.put("source", source.toString());
			}
			Node target = ((Connection) element).getTarget();
			if (target != null) {
				info.put("target", target.toString());
			}
		}
		if (element instanceof Node) {
			List<Connection> incomingConnections = ((Node) element).getIncomingConnections();
			if (incomingConnections != null && !incomingConnections.isEmpty()) {
				info.put("incoming-connections", incomingConnections.stream().map(Object::toString).toArray());
			}
			List<Connection> outgoingConnections = ((Node) element).getOutgoingConnections();
			if (outgoingConnections != null && !outgoingConnections.isEmpty()) {
				info.put("outgoing-connections", outgoingConnections.stream().map(Object::toString).toArray());
			}
		}
		
		if (childInfo != null && !childInfo.isEmpty()) {
			info.put("children", childInfo.values().stream().toArray());
		}
		return info;
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
			Collection<URI> baseSemanticURIs,
			BiFunction<Label, URI, URI> uriResolver,
			Iterable<Map.Entry<SemanticInfo,?>> semanticInfoSource,
			ResolutionListener resolutionListener,
			ProgressMonitor progressMonitor) {
		
		int spaceIdx = path.indexOf(' ');
		String targetUriStr = spaceIdx == -1 ? path : path.substring(0, spaceIdx);

		if (baseSemanticURIs == null || baseSemanticURIs.isEmpty()) {
			baseSemanticURIs = Collections.singleton(null);
		}
		for (URI baseSemanticURI: baseSemanticURIs) {
			URI targetURI = URI.createURI(targetUriStr);
			if (baseSemanticURI != null && targetURI.isRelative()) {
				targetURI = targetURI.resolve(baseSemanticURI.appendSegment(""));
			}	
			URI bURI = uriResolver.apply(action, (URI) null);				
	
			if (semanticInfoSource != null) {
				for (Entry<SemanticInfo, ?> entry: semanticInfoSource) {
					if (entry != null) {
						Object value = entry.getValue();
						if (value instanceof Label) {
							Label targetLabel = (Label) value;
							SemanticInfo semanticElementAnnotation = entry.getKey();
							if (semanticElementAnnotation != null) {
								for (URI semanticURI: semanticElementAnnotation.getIdentifiers()) {
									if (Objects.equals(targetURI, semanticURI)) {
										Label tLabel = Util.createLabel(targetLabel, action, uriResolver, null, null, false, false, false);
										SupplierFactory<Tag> tagSupplierFactory = EObjectAdaptable.adaptToSupplierFactory(tLabel, Tag.class, new AppAdapterFactory());
										Tag tag; 
										if (tagSupplierFactory == null) {
											HTMLFactory htmlFactory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
											URI targetActionURI = uriResolver.apply(targetLabel, bURI);
											tag = htmlFactory.tag(targetActionURI == null ? TagName.span : TagName.a, spaceIdx == -1 ? targetLabel.getText() : path.substring(spaceIdx + 1));
											String targetActionTooltip = targetLabel.getTooltip();
											if (!org.nasdanika.common.Util.isBlank(targetActionTooltip)) {
												tag.attribute("title", targetActionTooltip);
											}
											if (targetActionURI != null) {
												tag.attribute("href", targetActionURI.toString());
											}
										} else {
											tag = tagSupplierFactory.create(context).execute(progressMonitor);
										}
										if (resolutionListener != null) {
											URI rawTargetURI = uriResolver.apply(targetLabel, null);
											URI backLinkURI = uriResolver.apply(action, rawTargetURI);
											resolutionListener.onSemanticReferenceResolution(action, path, targetLabel, targetURI, backLinkURI);
										}										
										return tag.toString(); 
									}
								}
							}
						}
					}
				}
			}
					
			String semanticLink = computeSemanticLink(targetURI, bURI, context, resolutionListener);
			if (semanticLink != null) {				
				return semanticLink;
			}
		}
		
		String message = "Unresolved semantic URI: " + targetUriStr;
		progressMonitor.worked(Status.ERROR, 1, message, action);
		if (resolutionListener != null) {
			resolutionListener.onSemanticReferenceResolution(action, targetUriStr, null, null, null);
		}
		
		return null;
	}
	
	/**
	 * Computes semantic links for external URI's. This implementation returns null;
	 * @param targetURI 
	 * @param baseURI Base URI to deresolve link's reference to make it relative.
	 * @param context
	 * @return
	 */
	protected String computeSemanticLink(
			URI targetURI, 
			URI baseURI, 
			Context context,
			ResolutionListener resolutionListener) {
		
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
			Collection<URI> baseSemanticURIs,
			BiFunction<Label, URI, URI> uriResolver,
			Iterable<Map.Entry<SemanticInfo,?>> semanticInfoSource,
			ResolutionListener resolutionListener,
			ProgressMonitor progressMonitor) {

		if (baseSemanticURIs == null || baseSemanticURIs.isEmpty()) {
			baseSemanticURIs = Collections.singleton(null);
		}
		for (URI baseSemanticURI: baseSemanticURIs) {
			URI targetURI = URI.createURI(path);
			if (baseSemanticURI != null && targetURI.isRelative()) {
				targetURI = targetURI.resolve(baseSemanticURI.appendSegment(""));
			}	
			URI bURI = uriResolver.apply(action, (URI) null);		
			if (semanticInfoSource != null) {
				for (Entry<SemanticInfo, ?> entry: semanticInfoSource) {
					if (entry != null) {
						Object value = entry.getValue();
						if (value instanceof Label) {
							Label targetLabel = (Label) value;
							SemanticInfo semanticElementAnnotation = entry.getKey();
							if (semanticElementAnnotation != null) {
								for (URI semanticURI: semanticElementAnnotation.getIdentifiers()) {
									if (Objects.equals(targetURI, semanticURI)) {
										URI targetActionURI = uriResolver.apply(targetLabel, bURI);
										if (targetActionURI != null) {
											if (resolutionListener != null) {
												URI rawTargetURI = uriResolver.apply(targetLabel, null);
												URI backLinkURI = uriResolver.apply(action, rawTargetURI);
												resolutionListener.onSemanticReferenceResolution(action, path, targetLabel, targetURI, backLinkURI);
											}
											
											return targetActionURI;
										}
									}
								}
							}
						}
					}
				}
			}
			
			URI ref = computeSemanticReference(targetURI, context);		
			if (ref != null) {
				if (bURI != null) {
					ref = ref.deresolve(bURI, true, true, true);
				}
				if (resolutionListener != null) {
					resolutionListener.onSemanticReferenceResolution(action, path, null, ref, null);
				}				
				return ref;
			}
		}
		
		String message = "Unresolved semantic URI: " + path;
		progressMonitor.worked(Status.ERROR, 1, message, action);
		if (resolutionListener != null) {
			resolutionListener.onSemanticReferenceResolution(action, path, null, null, null);
		}
		return null;
		
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
			Iterable<Map.Entry<SemanticInfo,?>> semanticInfoSource,
			File resourceWorkDir,
			Context context,
			java.util.function.Consumer<Diagnostic> diagnosticConsumer,
			ResolutionListener resolutionListener,						
			ProgressMonitor progressMonitor) {

		List<Object> contentContributions = new ArrayList<>();
		
		Context actionContentContext = createActionContentContext(
				action, 
				uriResolver, 
				semanticInfoSource, 
				(ContentConsumer) contentContributions::add, 
				resolutionListener, 
				context, 
				progressMonitor);
		
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
			Collection<URI> baseSemanticURIs,
			BiFunction<Label, URI, URI> uriResolver,
			Iterable<Map.Entry<SemanticInfo,?>> semanticInfoSource) {

		String[] pathSegments = path.split("/");
		if (pathSegments.length == 1) {
			// Non drawio representation, just a key
			return representations.get(path);
		}
		try {
			org.nasdanika.drawio.Document valueDocument = (org.nasdanika.drawio.Document) representations.get(pathSegments[0]);			
			if (valueDocument != null) {
				if (pathSegments.length == 2) {
					// Document
					if ("diagram".equals(pathSegments[1])) {
						return valueDocument.save(true);
					}
					
					if ("toc".equals(pathSegments[1])) {
						return String.valueOf(computeTableOfContents(valueDocument, context));
					}
										
					if ("info".equals(pathSegments[1])) {
						return computeInfo(action, valueDocument, context);
					}
					
					return null;
				} 
				
				if (pathSegments.length == 3) {
					// Page
					for (Page page: valueDocument.getPages()) {
						if (pathSegments[1].equals(page.getName())) {
							org.nasdanika.drawio.Document pageDocument = org.nasdanika.drawio.Document.create(true, valueDocument.getURI());
							pageDocument.getPages().add(page);
							if ("diagram".equals(pathSegments[2])) {
								return pageDocument.save(true);
							}
							
							if ("toc".equals(pathSegments[2])) {
								return String.valueOf(computeTableOfContents(pageDocument, context));
							}
							
							if ("info".equals(pathSegments[2])) {
								return computeInfo(action, pageDocument, context);
							}
						}
					}
				}
			}
		} catch (TransformerException | IOException | ParserConfigurationException e) {
			throw new NasdanikaException(e);
		}
		
		return null;		
	}	
	
	protected SemanticInfo getSemanticInfoAnnotation(Action action) {
		return SemanticInfo.getAnnotation(action);
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
			Iterable<Map.Entry<SemanticInfo,?>> semanticInfoSource,
			ContentConsumer contentConsumer,
			ResolutionListener resolutionListener,						
			Context context,
			ProgressMonitor progressMonitor) {
		
		MutableContext mctx = context.fork();
		mctx.put("nsd-site-map-tree-script", (Function<Context, String>) ctx -> computeSiteMapTreeScript(ctx, action, uriResolver, semanticInfoSource, progressMonitor));
		
		if (contentConsumer != null) {
			mctx.register(ContentConsumer.class, contentConsumer);
		}
		
		SemanticInfo semanticInfo = getSemanticInfoAnnotation(action);
		Collection<URI> baseSemanticURIs = semanticInfo == null ? Collections.emptyList() : semanticInfo.getIdentifiers().stream().filter(u -> !u.isRelative() && u.isHierarchical()).collect(Collectors.toList());					
		
		Map<String, Object> representations = NcoreActionBuilder.resolveRepresentationLinks(
				action, 
				semanticInfoSource,
				uriResolver, 
				resolutionListener, 
				progressMonitor);
		
		PropertyComputer representationsPropertyComputer = new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T compute(Context ctx, String key, String path, Class<T> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					return (T) computeRepresentation(representations, ctx, key, path, action, baseSemanticURIs, uriResolver, semanticInfoSource);			
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
					return (T) computeSemanticLink(
							ctx, 
							key, 
							path, 
							action, 
							baseSemanticURIs, 
							uriResolver, 
							semanticInfoSource, 
							resolutionListener,  
							progressMonitor);			
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
					URI sRef = computeSemanticReferfence(
							propertyComputerContext, 
							key, 
							path, 
							action, 
							baseSemanticURIs, 
							uriResolver, 
							semanticInfoSource, 
							resolutionListener,  
							progressMonitor);
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
	
	// --- Utility methods
	
	/**
	 * Wraps iterator
	 * @param <T>
	 * @param iterator
	 * @return
	 */
	public static <T,U> Iterator<U> wrap(Iterator<T> iterator, Function<? super T,U> mapper) {
		return new Iterator<U>() {

			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public U next() {
				return mapper.apply(iterator.next());
			}
			
			@Override
			public void remove() {
				iterator.remove();
			}
			
		};
	}

	public static <T> Iterable<T> asIterable(Resource resource, Function<? super EObject, T> mapper) {
		return () -> wrap(resource.getAllContents(), mapper);
	}

	public static <T> Iterable<T> asIterable(ResourceSet resourceSet, Function<? super Notifier, T> mapper) {
		return () -> wrap(resourceSet.getAllContents(), mapper);
	}
	
	/**
	 * @param object
	 * @return Semantic info or self info
	 */
	protected Map.Entry<SemanticInfo, Label> semanticInfo(Notifier object) {
		if (object instanceof Label) {
			Label label = (Label) object;
			SemanticInfo semanticInfo = SemanticInfo.getAnnotation(label);
			if (semanticInfo == null) {
				semanticInfo = new SemanticInfo(label);
			}
			return Map.entry(semanticInfo, (Label) object);
		}
		return null;
	}
	
	/**
	 * Override to return contributors.
	 * @return A collection of {@link SiteGeneratorContributor}s.
	 */
	protected Collection<SiteGeneratorContributor> getContributors() {
		return Collections.emptyList();
	}
	
	/**
	 * Override to load semantic info from external sources. This implementation iterates over the resource set and returns
	 * entries created by selfInfo 
	 * @param resourceSet
	 * @return
	 */
	protected Iterable<Map.Entry<SemanticInfo, ?>> semanticInfoSource(ResourceSet resourceSet) {
		return composeWithSemanticInfo(asIterable(resourceSet, this::semanticInfo));
	}
	
	/**
	 * Override to load semantic info from external sources. This implementation iterates over the resource set and returns
	 * entries created by selfInfo 
	 * @param resourceSet
	 * @return
	 */
	protected Iterable<Map.Entry<SemanticInfo, ?>> semanticInfoSource(Resource actionResource) {
		return composeWithSemanticInfo(asIterable(actionResource, this::semanticInfo));
	}
	
	/**
	 * @return External {@link SemanticInfo}'s for referencing. 
	 */
	protected Iterable<SemanticInfo> getSemanticInfos() {
		return null;
	}
	
	protected Iterable<Entry<SemanticInfo, ?>> composeWithSemanticInfo(Iterable<Entry<SemanticInfo, ?>> semanticInfoSource) {
		Iterable<SemanticInfo> semanticInfos = getSemanticInfos();
		if (semanticInfos == null) {
			return semanticInfoSource;
		}
		return new Iterable<Entry<SemanticInfo, ?>>() {
			
			@Override
			public Iterator<Entry<SemanticInfo, ?>> iterator() {
				return new Iterator<Map.Entry<SemanticInfo,?>>() {
					
					private Iterator<Map.Entry<SemanticInfo,?>> firstIterator = semanticInfoSource.iterator();
					private Iterator<Map.Entry<SemanticInfo,?>> secondIterator = wrap(semanticInfos.iterator(), this::mapToLabel);
					
					private Map.Entry<SemanticInfo,Label> mapToLabel(SemanticInfo semanticInfo) {
						URI location = semanticInfo.getLocation();
						Label label = location == null ? AppFactory.eINSTANCE.createLabel() : AppFactory.eINSTANCE.createLink();
						label.setText(semanticInfo.getName());
						label.setTooltip(semanticInfo.getDescription());
						label.setIcon(semanticInfo.getIcon());

						if (location != null) {
							((Link) label).setLocation(location.toString());
						}
						return Map.entry(semanticInfo, label); 
					}
					
					@Override
					public Entry<SemanticInfo, ?> next() {								
						if (firstIterator != null && firstIterator.hasNext()) {
							
						}
						return (firstIterator.hasNext() ? firstIterator : secondIterator).next();
					}
					
					@Override
					public boolean hasNext() {
						return firstIterator.hasNext() || secondIterator.hasNext();
					}
				};
			}
			
		};
	}
	
	/**
	 * Creates a resolution listener. Used by subclasses.
	 * @param errorConsumer location, error message
	 * @param context
	 * @param progressMonitor
	 * @return
	 */
	protected ResolutionListener createResolutionListener(BiConsumer<String,String> errorConsumer, Context context, ProgressMonitor progressMonitor) {
				
		return new ResolutionListener() {
			/**
			 * Called on resolution of "target-uri" property of a diagram element.
			 * If target is null then resolution was unsuccessful.
			 * @param element
			 * @param action
			 * @param targetUriPropertyValue
			 * @param target
			 */
			public void onTargetURIResolution(
					Action action, 
					org.nasdanika.drawio.ModelElement modelElement, 
					String targetURI, 
					EObject target) {
								
				if (target == null) {
					String location = modelElement == null ? NcoreActionBuilder.actionMarker(action) : modelElement.toString() + " " + modelElement.getMarkers().toString();				
					errorConsumer.accept(location, "Element with URI not found: " + targetURI);
				}
			}

			/**
			 * Called on resoulution of semantic-uuid property.
			 * @param element
			 * @param action
			 * @param semanticUUID
			 * @param semanticModelElement If null then there is no element with a given UUID.
			 * @param semanticModelElementAction If null and the previous argument is not null, then there is a semantic element, but there is not action for it.
			 */
			public void onTargetUUIDResolution(
					Action action, 
					org.nasdanika.drawio.ModelElement modelElement, 
					String semanticUUID, 
					ModelElement semanticModelElement,
					Action semanticModelElementAction) {
				
				if (semanticModelElementAction == null) {
					String location = modelElement == null ? NcoreActionBuilder.actionMarker(action) : modelElement.toString() + " " + modelElement.getMarkers().toString();				
					errorConsumer.accept(location, "Action for semantic UUID not found: " + semanticUUID);
				}
			}

			/**
			 * Called on resolution of action URI
			 * @param action Source action
			 * @param modelElement Source model element if resolution is done for diagram element properties
			 * @param targetURIStr Target URI string resolved relative to action URI's.
			 * @param target Target, null if not found.
			 * @param targetURI Target URI relative to the source action. Null of target is not found or doesn't have a URI.
			 * @param backLinkURI Backlink from the target action to the source action.
			 */
			public void onActionURIResolution(
					Action action, 
					org.nasdanika.drawio.ModelElement modelElement, 
					String targetURIStr, 
					Label target, 
					URI targetURI, 
					URI backLinkURI) {
				
				if (target == null) {
					String location = modelElement == null ? NcoreActionBuilder.actionMarker(action) : modelElement.toString() + " " + modelElement.getMarkers().toString();				
					errorConsumer.accept(location, "Action with URI not found: " + targetURIStr);
				}
			}

			/**
			 * Called on resolution of action UUID
			 * @param action
			 * @param modelElement
			 * @param actionUUID
			 * @param target
			 * @param targetURI
			 * @param backLinkURI
			 */
			public void onActionUUIDResolution(
					Action action, 
					org.nasdanika.drawio.ModelElement modelElement, 
					String actionUUID,
					Label target,
					URI targetURI,
					URI backLinkURI) {
				
				if (target == null) {
					String location = modelElement == null ? NcoreActionBuilder.actionMarker(action) : modelElement.toString() + " " + modelElement.getMarkers().toString();				
					errorConsumer.accept(location, "Action with UUID not found: " + actionUUID);
				}
			}

			/**
			 * Called on resolution of semantic reference or link
			 * @param action
			 * @param targetURIStr
			 * @param target
			 * @param targetURI
			 * @param backLinkURI
			 */
			public void onSemanticReferenceResolution(
					Action action, 
					String targetURIStr, 
					Label target, 
					URI targetURI, 
					URI backLinkURI) {
				
				if (target == null) {
					errorConsumer.accept(NcoreActionBuilder.actionMarker(action), "Unresolved semantic reference: " + targetURIStr);
				}
			}
			
		};		
	}
	
	
}
