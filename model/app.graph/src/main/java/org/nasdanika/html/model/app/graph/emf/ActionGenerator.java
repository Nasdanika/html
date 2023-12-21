package org.nasdanika.html.model.app.graph.emf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
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
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;
import org.nasdanika.graph.processor.emf.EObjectNodeProcessorReflectiveFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.graph.WidgetFactory;

/**
 * Base class for action generation using node processor fatory
 * @param <F> Node processor factory type.
 */
public class ActionGenerator<F> {
	
	protected Collection<? extends EObject> sources;
	protected F nodeProcessorFactory;
	protected Collection<? extends EObject> references;
	protected Function<? super EObject, URI> uriResolver;
	
	public ActionGenerator(
			Collection<? extends EObject> sources,
			F nodeProcessorFactory, 
			Collection<? extends EObject> references,
			Function<? super EObject, URI> uriResolver) {
		
		this.sources = sources;
		this.nodeProcessorFactory = nodeProcessorFactory;
		this.references = references;
		this.uriResolver = uriResolver;
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
	public Map<EObject,Collection<Label>> generateActionModel(Consumer<Diagnostic> diagnosticConsumer, ProgressMonitor progressMonitor) {
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
		return new EObjectNodeProcessorReflectiveFactory<>(nodeProcessorFactory);
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
	public void generateActionModel(
			Consumer<Diagnostic> diagnosticConsumer,
			URI actionModelResourceURI,
			ProgressMonitor progressMonitor) throws IOException {
	
		Map<EObject, Collection<Label>> labelMap = generateActionModel(
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
	public void generateActionModel(
			Consumer<Diagnostic> diagnosticConsumer,
			File actionModelFile,
			ProgressMonitor progressMonitor) throws IOException {
		
		generateActionModel(
				diagnosticConsumer, 
				URI.createFileURI(actionModelFile.getCanonicalFile().getAbsolutePath()), progressMonitor);
	}
		
}
