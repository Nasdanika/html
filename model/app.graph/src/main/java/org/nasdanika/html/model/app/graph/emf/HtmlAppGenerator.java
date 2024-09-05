package org.nasdanika.html.model.app.graph.emf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.capability.emf.EPackageResourceSetContributor;
import org.nasdanika.capability.emf.ResourceSetContributor;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectGraphFactory;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;
import org.nasdanika.graph.processor.emf.EObjectNodeProcessorReflectiveFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.graph.WidgetFactory;

/**
 * Base class for HTML application (site) generation using node processor factory
 * @param <F> Node processor factory type.
 */
public class HtmlAppGenerator {
	
	public interface Factory<T extends HtmlAppGenerator> {
		
		T create(
				Collection<? extends EObject> sources,
				Collection<? extends EObject> references,
				Function<? super EObject, URI> uriResolver,
				Object... nodeProcessorFactories);		
		
	}
	
	protected Collection<? extends EObject> sources;
	protected Object[] nodeProcessorFactories;
	protected Collection<? extends EObject> references;
	protected Function<? super EObject, URI> uriResolver;
	
	public HtmlAppGenerator(
			Collection<? extends EObject> sources,
			Collection<? extends EObject> references,
			Function<? super EObject, URI> uriResolver,
			Object... nodeProcessorFactories) {
		
		this.sources = sources;
		this.nodeProcessorFactories = nodeProcessorFactories;
		this.references = references;
		this.uriResolver = uriResolver;
	}
	
	/**
	 * Loads mapping of {@link EPackage}s to doc URI's and node processor factories from {@link CapabilityLoader}. 
	 * @param sources
	 * @param references
	 * @param capabilityLoader
	 * @param progressMonitor
	 * @return
	 */
	public static HtmlAppGenerator load(
			Collection<EObject> sources,
			Context context, 
			java.util.function.BiFunction<URI, ProgressMonitor, Label> prototypeProvider,			
			Predicate<Object> factoryPredicate,
			Predicate<EPackage> ePackagePredicate,
			Consumer<Diagnostic> diagnosticConsumer,
			ProgressMonitor progressMonitor) {

		return load(
				sources,
				context, 
				prototypeProvider,
				factoryPredicate,
				ePackagePredicate,
				new CapabilityLoader(),
				diagnosticConsumer,
				progressMonitor);
	}	
	
	public static record NodeProcessorFactoryRequirement(
			Predicate<Object> factoryPredicate,
			Context context, 
			java.util.function.BiFunction<URI, ProgressMonitor, Label> prototypeProvider,
			Consumer<Diagnostic> diagnosticConsumer) {
		
	}
	
	/**
	 * Loads mapping of {@link EPackage}s to doc URI's and node processor factories from {@link CapabilityLoader}. 
	 * @param sources
	 * @param references
	 * @param capabilityLoader
	 * @param progressMonitor
	 * @return
	 */
	public static HtmlAppGenerator load(
			Collection<EObject> sources,
			Context context, 
			java.util.function.BiFunction<URI, ProgressMonitor, Label> prototypeProvider,			
			Predicate<Object> factoryPredicate,
			Predicate<EPackage> ePackagePredicate,
			CapabilityLoader capabilityLoader, 
			Consumer<Diagnostic> diagnosticConsumer,
			ProgressMonitor progressMonitor) {

		return load(
				sources,
				context, 
				prototypeProvider,			
				factoryPredicate,
				ePackagePredicate,
				capabilityLoader, 
				diagnosticConsumer,
				HtmlAppGenerator::new,
				progressMonitor);			
		
	}
	
	/**
	 * Loads mapping of {@link EPackage}s to doc URI's and node processor factories from {@link CapabilityLoader}. 
	 * @param sources
	 * @param references
	 * @param capabilityLoader
	 * @param progressMonitor
	 * @return
	 */
	public static <T extends HtmlAppGenerator> T load(
			Collection<EObject> sources,
			Context context, 
			java.util.function.BiFunction<URI, ProgressMonitor, Label> prototypeProvider,			
			Predicate<Object> factoryPredicate,
			Predicate<EPackage> ePackagePredicate,
			CapabilityLoader capabilityLoader, 
			Consumer<Diagnostic> diagnosticConsumer,
			Factory<T> factory,
			ProgressMonitor progressMonitor) {
		
		Predicate<ResourceSetContributor> contributorPredicate = contributor -> contributor instanceof EPackageResourceSetContributor && (ePackagePredicate == null || ePackagePredicate.test(((EPackageResourceSetContributor) contributor).getEPackage()));		
		Requirement<Predicate<ResourceSetContributor>, ResourceSetContributor> contributorRequirement = ServiceCapabilityFactory.createRequirement(
				ResourceSetContributor.class, 
				null,
				contributorPredicate);

		Map<EPackage, URI> references = new IdentityHashMap<EPackage, URI>();
		for (CapabilityProvider<Object> resourceSetContributorProvider: capabilityLoader.load(contributorRequirement, progressMonitor)) {
			resourceSetContributorProvider.getPublisher().subscribe(contributor -> {
				if (contributor instanceof EPackageResourceSetContributor) {
					EPackageResourceSetContributor ePackageResourceSetContributor = (EPackageResourceSetContributor) contributor;
					URI docURI = ePackageResourceSetContributor.getDocumentationURI();
					if (docURI != null) {
						references.put(ePackageResourceSetContributor.getEPackage(), docURI);
					}
				}
			});
		}
		URI baseURI = URI.createURI("tmp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/");
		Function<? super EObject, URI> uriResolver = eObj -> {
			if (sources.size() == 1) {
				 if (eObj == sources.iterator().next()) {
					 return baseURI;
				 }
			} else {
				Iterator<EObject> sit = sources.iterator();
				for (int idx = 0; sit.hasNext(); ++idx) {
					EObject nextSource = sit.next();
					if (eObj == nextSource) {
						return baseURI.appendSegments(new String[] { String.valueOf(idx), "" });
					}
				}
			}
			
			return references.get(eObj);
		};
				
		List<Object> nodeProcessorFactories = Collections.synchronizedList(new ArrayList<>());
		NodeProcessorFactoryRequirement requirement = new NodeProcessorFactoryRequirement(
				factoryPredicate, 
				context, 
				prototypeProvider, 
				diagnosticConsumer);
		for (CapabilityProvider<Object> nodeProcessorProvider: capabilityLoader.load(requirement, progressMonitor)) {
			nodeProcessorProvider.getPublisher().subscribe(nodeProcessorFactories::add);
		}
		
		return factory.create(
				sources,
				references.keySet(),
				uriResolver,
				nodeProcessorFactories.toArray());
	}
	
	/**
	 * Uses transformer and a reflective node processor factory to generate actions from sources
	 * @param sources Source objects
	 * @param nodeProcessorFactory Node processor factory
	 * @param references Objects which might be referenced by the sources and as such need their URI's resolved for proper linking 
	 * @param uriResolver Resolver of URI's for sources and references
	 * @param diagnosticConsumer Diagnostic consumer
	 * @param progressMonitor Progress monitor
	 * @return A map of source objects to a collection of labels created from those objects
	 */
	public Map<EObject,Collection<Label>> generateHtmlAppModel(Consumer<Diagnostic> diagnosticConsumer, ProgressMonitor progressMonitor) {
		Transformer<EObject,Element> graphFactory = new Transformer<>(createGraphFactory());
		Map<EObject, Element> graph = graphFactory.transform(sources, false, progressMonitor);
		
		Object configFactory = createConfigFactory();				
		
		Transformer<Element,ProcessorConfig> processorConfigTransformer = new Transformer<>(configFactory);				
		Map<Element, ProcessorConfig> configs = processorConfigTransformer.transform(graph.values(), false, progressMonitor);
		
		Object reflectiveFactory = createReflectiveFactory();
		ReflectiveProcessorFactoryProvider<Object, WidgetFactory, WidgetFactory> eObjectReflectiveProcessorFactoryProvider = createReflectiveFactoryProvider(reflectiveFactory);
		Map<Element, ProcessorInfo<Object>> registry = eObjectReflectiveProcessorFactoryProvider.getFactory().createProcessors(configs.values(), false, progressMonitor);
		
		if (references != null) {
			for (EObject reference: references) {
				URI refURI = uriResolver.apply(reference);
				if (refURI != null) {
					for (Entry<Element, ProcessorInfo<Object>> re: registry.entrySet()) {
						Element element = re.getKey();
						if (element instanceof EObjectNode) {
							EObjectNode eObjNode = (EObjectNode) element;
							EObject target = eObjNode.get();
							if (target == reference) {
								ProcessorInfo<Object> info = re.getValue();
								Object processor = info.getProcessor();
								if (processor instanceof WidgetFactory) {
									WidgetFactory widgetFactoryNodeProcessor = (WidgetFactory) processor;
									widgetFactoryNodeProcessor.resolve(refURI, progressMonitor);
								}
							}
						}
					}								
				}
			}
		}
		
		record SourceProcessorRecord(EObject source, URI uri, WidgetFactory widgetFactory) {}
		
		Collection<SourceProcessorRecord> sourceProcessorRecords = new ArrayList<>();
		for (EObject source: sources) {
			URI sourceURI = uriResolver.apply(source);
			for (Entry<Element, ProcessorInfo<Object>> re: registry.entrySet()) {
				Element element = re.getKey();
				if (element instanceof EObjectNode) {
					EObjectNode eObjNode = (EObjectNode) element;
					EObject target = eObjNode.get();
					if (target == source) {
						ProcessorInfo<Object> info = re.getValue();
						Object processor = info.getProcessor();
						if (processor instanceof WidgetFactory) {
							WidgetFactory widgetFactoryNodeProcessor = (WidgetFactory) processor;
							if (sourceURI != null) { 
								widgetFactoryNodeProcessor.resolve(sourceURI, progressMonitor);
							}
							
							sourceProcessorRecords.add(new SourceProcessorRecord(source, sourceURI, widgetFactoryNodeProcessor));
						}
					}
				}
			}						
		}
		
		Map<EObject, Collection<Label>> ret = new LinkedHashMap<>();

		for (SourceProcessorRecord sourceProcessorRecord: sourceProcessorRecords) {
			Collection<Label> labels = sourceProcessorRecord
					.widgetFactory()
					.createLabelsSupplier()
					.call(progressMonitor, diagnosticConsumer);
			
			for (Label label: labels) {
				if (label instanceof Link) {
					Link link = (Link) label;
					String location = link.getLocation();
					if (!org.nasdanika.common.Util.isBlank(location)) {
						URI uri = URI.createURI(location);
						if (sourceProcessorRecord.uri() != null && !uri.isRelative()) {
							link.setLocation("${base-uri}" + uri.deresolve(sourceProcessorRecord.uri(), true, true, true).toString());
						}
					}
				}
			}
			
			ret.put(sourceProcessorRecord.source(), labels);
		}
		
		return ret;
	}

	protected EObjectReflectiveProcessorFactoryProvider createReflectiveFactoryProvider(Object reflectiveFactory) {
		return new EObjectReflectiveProcessorFactoryProvider(reflectiveFactory);
	}

	protected EObjectNodeProcessorReflectiveFactory<Object, Object> createReflectiveFactory() {
		return new EObjectNodeProcessorReflectiveFactory<>(nodeProcessorFactories);
	}

	protected Object createConfigFactory() {
		return new NopEndpointProcessorConfigFactory<>() {
			
			@Override
			protected boolean isPassThrough(Connection connection) {
				return false;
			}
			
		};
	}

	protected Object createGraphFactory() {
		return new EObjectGraphFactory();
	}

	/**
	 * Calls generateActionModel() and saves the returned label map to a resource at the provided URI
	 * @param sources
	 * @param nodeProcessorFactory
	 * @param references
	 * @param uriResolver
	 * @param diagnosticConsumer
	 * @param actionModelResourceURI Resource URI
	 * @param progressMonitor
	 * @throws IOException
	 */
	public void generateHtmlAppModel(
			Consumer<Diagnostic> diagnosticConsumer,
			URI actionModelResourceURI,
			ProgressMonitor progressMonitor) throws IOException {
	
		Map<EObject, Collection<Label>> labelMap = generateHtmlAppModel(
				diagnosticConsumer, 
				progressMonitor);
		
		saveLabelMap(labelMap, actionModelResourceURI);
	}
	
	public static void saveLabels(Iterable<Label> labels, URI actionModelResoureURI) throws IOException {
		ResourceSet actionModelsResourceSet = new ResourceSetImpl();
		actionModelsResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		Resource actionModelResource = actionModelsResourceSet.createResource(actionModelResoureURI);
		labels.forEach(actionModelResource.getContents()::add);
		
		actionModelResource.save(null);
	}	
	
	public static void saveLabelMap(Map<EObject, Collection<Label>> labelMap, URI actionModelResoureURI) throws IOException {
		List<Label> labels = labelMap
			.values()
			.stream()
			.flatMap(Collection::stream)
			.toList();
		saveLabels(labels, actionModelResoureURI);
	}	

	/**
	 * Saves generated actions to a file
	 * @param sources
	 * @param nodeProcessorFactory
	 * @param references
	 * @param uriResolver
	 * @param diagnosticConsumer
	 * @param actionModelFile Resource file
	 * @param progressMonitor
	 * @throws IOException
	 */
	public void generateHtmlAppModel(
			Consumer<Diagnostic> diagnosticConsumer,
			File actionModelFile,
			ProgressMonitor progressMonitor) throws IOException {
		
		generateHtmlAppModel(
				diagnosticConsumer, 
				URI.createFileURI(actionModelFile.getCanonicalFile().getAbsolutePath()), progressMonitor);
	}
		
}
