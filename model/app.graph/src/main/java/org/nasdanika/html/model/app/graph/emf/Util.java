package org.nasdanika.html.model.app.graph.emf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
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
import org.nasdanika.graph.processor.emf.EObjectNodeProcessorReflectiveFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public final class Util {
	
	// Singleton
	private Util() {
		
	}
	
	// --- Multiple sources and URI resolver ---

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
	public static Map<EObject,Collection<Label>> generateActionModel(
			Collection<? extends EObject> sources,
			Object nodeProcessorFactory,
			Collection<? extends EObject> references,
			Function<? super EObject, URI> uriResolver,
			Consumer<Diagnostic> diagnosticConsumer,
			ProgressMonitor progressMonitor) {
		Transformer<EObject,Element> graphFactory = new Transformer<>(new EObjectGraphFactory());
		Map<EObject, Element> graph = graphFactory.transform(sources, false, progressMonitor);
		
		NopEndpointProcessorConfigFactory<WidgetFactory> configFactory = new NopEndpointProcessorConfigFactory<>() {
			
			@Override
			protected boolean isPassThrough(Connection connection) {
				return false;
			}
			
		};				
		
		Transformer<Element,ProcessorConfig> processorConfigTransformer = new Transformer<>(configFactory);				
		Map<Element, ProcessorConfig> configs = processorConfigTransformer.transform(graph.values(), false, progressMonitor);
		
		EObjectNodeProcessorReflectiveFactory<WidgetFactory, WidgetFactory> eObjectNodeProcessorReflectiveFactory = new EObjectNodeProcessorReflectiveFactory<>(nodeProcessorFactory);
		EObjectReflectiveProcessorFactoryProvider eObjectReflectiveProcessorFactoryProvider = new EObjectReflectiveProcessorFactoryProvider(eObjectNodeProcessorReflectiveFactory);
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
	public static void generateActionModel(
			Collection<? extends EObject> sources,
			Object nodeProcessorFactory,
			Collection<? extends EObject> references,
			Function<? super EObject, URI> uriResolver,
			Consumer<Diagnostic> diagnosticConsumer,
			URI actionModelResourceURI,
			ProgressMonitor progressMonitor) throws IOException {
	
		Map<EObject, Collection<Label>> labelMap = generateActionModel(
				sources, 
				nodeProcessorFactory, 
				references, 
				uriResolver, 
				diagnosticConsumer, 
				progressMonitor);
		
		saveLabelMap(labelMap, actionModelResourceURI);
	}
	
	public static void saveLabelMap(Map<EObject, Collection<Label>> labelMap, URI actionModelResoureURI) throws IOException {
		ResourceSet actionModelsResourceSet = new ResourceSetImpl();
		actionModelsResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		Resource actionModelResource = actionModelsResourceSet.createResource(actionModelResoureURI);
		labelMap
			.values()
			.stream()
			.flatMap(Collection::stream)
			.forEach(actionModelResource.getContents()::add);
		
		actionModelResource.save(null);
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
	public static void generateActionModel(
			Collection<? extends EObject> sources,
			Object nodeProcessorFactory,
			Collection<? extends EObject> references,
			Function<? super EObject, URI> uriResolver,
			Consumer<Diagnostic> diagnosticConsumer,
			File actionModelFile,
			ProgressMonitor progressMonitor) throws IOException {
		
		generateActionModel(
				sources, 
				nodeProcessorFactory, 
				references, 
				uriResolver, 
				diagnosticConsumer, 
				URI.createFileURI(actionModelFile.getCanonicalFile().getAbsolutePath()), progressMonitor);
	}
	
	// --- Single source and base URI ---	
	
	/**
	 * Generates actions for a single source with a baseURI
	 * @param source
	 * @param baseURI Base URI. Can be null. If null, a random URI is generated. Use base URI if you want to generate relative links to referenced objects. 
	 * @param nodeProcessorFactory
	 * @param references
	 * @param diagnosticConsumer
	 * @param progressMonitor
	 * @return
	 */
	public static Map<EObject,Collection<Label>> generateActionModel(
			EObject source,
			URI baseURI,
			Object nodeProcessorFactory,
			Map<? extends EObject, URI> references,
			Consumer<Diagnostic> diagnosticConsumer,
			ProgressMonitor progressMonitor) {
		
		URI theBaseURI = baseURI == null ? URI.createURI("tmp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/") : baseURI;
		
		return generateActionModel(
				Collections.singleton(source), 
				nodeProcessorFactory, 
				references.keySet(), 
				eObj -> {
					if (eObj == source) {
						return theBaseURI;
					}
					return references.get(eObj);
				}, 
				diagnosticConsumer, 
				progressMonitor);
		
	}
	
	/**
	 * Generates actions for a single source and saves to a resource identified by URI
	 * @param source
	 * @param baseURI
	 * @param nodeProcessorFactory
	 * @param references
	 * @param diagnosticConsumer
	 * @param actionModelResourceURI Resource URI
	 * @param progressMonitor
	 * @throws IOException
	 */
	public static void generateActionModel(
			EObject source,
			URI baseURI,
			Object nodeProcessorFactory,
			Map<? extends EObject, URI> references,
			Consumer<Diagnostic> diagnosticConsumer,
			URI actionModelResourceURI,
			ProgressMonitor progressMonitor) throws IOException {
	
		Map<EObject, Collection<Label>> labelMap = generateActionModel(
				source,
				baseURI,
				nodeProcessorFactory, 
				references, 
				diagnosticConsumer, 
				progressMonitor);
		
		saveLabelMap(labelMap, actionModelResourceURI);
	}
	
	/**
	 * Generates actions for a single source and saves to a file
	 * @param source
	 * @param baseURI
	 * @param nodeProcessorFactory
	 * @param references
	 * @param diagnosticConsumer
	 * @param actionModelFile
	 * @param progressMonitor
	 * @throws IOException
	 */
	public static void generateActionModel(
			EObject source,
			URI baseURI,
			Object nodeProcessorFactory,
			Map<? extends EObject, URI> references,
			Consumer<Diagnostic> diagnosticConsumer,
			File actionModelFile,
			ProgressMonitor progressMonitor) throws IOException {
		
		generateActionModel(
				source,
				baseURI,
				nodeProcessorFactory, 
				references, 
				diagnosticConsumer, 
				URI.createFileURI(actionModelFile.getCanonicalFile().getAbsolutePath()), progressMonitor);
	}
	
	
	// --- Single source ---	
	
	/**
	 * Generates actions for a single source with a random base URI.
	 * @param source
	 * @param nodeProcessorFactory
	 * @param references
	 * @param diagnosticConsumer
	 * @param progressMonitor
	 * @return
	 */
	public static Map<EObject,Collection<Label>> generateActionModel(
			EObject source,
			Object nodeProcessorFactory,
			Map<? extends EObject, URI> references,
			Consumer<Diagnostic> diagnosticConsumer,
			ProgressMonitor progressMonitor) {
		
		return generateActionModel(
				source, 
				null,
				nodeProcessorFactory, 
				references, 
				diagnosticConsumer,
				progressMonitor);
		
	}	

	/**
	 * 	 * Generates actions for a single source with a random base URI and saves to a resource at provided URI.
	 * @param source
	 * @param nodeProcessorFactory
	 * @param references
	 * @param diagnosticConsumer
	 * @param actionModelResourceURI Action resource URI
	 * @param progressMonitor
	 * @throws IOException
	 */
	public static void generateActionModel(
			EObject source,
			Object nodeProcessorFactory,
			Map<? extends EObject, URI> references,
			Consumer<Diagnostic> diagnosticConsumer,
			URI actionModelResourceURI,
			ProgressMonitor progressMonitor) throws IOException {
	
		generateActionModel(
				source,
				null,
				nodeProcessorFactory, 
				references, 
				diagnosticConsumer,
				actionModelResourceURI,
				progressMonitor);
	}

	/**
	 * Generates actions for a single source with a random base URI and saves to a file.
	 * @param source
	 * @param nodeProcessorFactory
	 * @param references
	 * @param diagnosticConsumer
	 * @param actionModelFile
	 * @param progressMonitor
	 * @throws IOException
	 */
	public static void generateActionModel(
			EObject source,
			Object nodeProcessorFactory,
			Map<? extends EObject, URI> references,
			Consumer<Diagnostic> diagnosticConsumer,
			File actionModelFile,
			ProgressMonitor progressMonitor) throws IOException {
		
		generateActionModel(
				source,
				null,
				nodeProcessorFactory, 
				references, 
				diagnosticConsumer, 
				URI.createFileURI(actionModelFile.getCanonicalFile().getAbsolutePath()), 
				progressMonitor);
	}
		
}
