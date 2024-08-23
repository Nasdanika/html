package org.nasdanika.html.model.app.graph.drawio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Transformer;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.ReflectiveProcessorFactoryProvider;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class DrawioActionGenerator extends Configuration {

	protected CapabilityLoader capabilityLoader;
		
	public DrawioActionGenerator() {
		this(new CapabilityLoader());
	}
	
	public DrawioActionGenerator(CapabilityLoader capabilityLoader) {
		this.capabilityLoader = capabilityLoader;
	}
	
	@SuppressWarnings("unchecked")
	public Supplier<Collection<Label>> createLabelsSupplier(Document document, ProgressMonitor progressMonitor) {
		NopEndpointProcessorConfigFactory<WidgetFactory> processorConfigFactory = new NopEndpointProcessorConfigFactory<WidgetFactory>() {
			
			@Override
			protected boolean isPassThrough(Connection incomingConnection) {
				return false;
			}
			
		};
		
		Transformer<Element,ProcessorConfig> processorConfigTransformer = new Transformer<>(processorConfigFactory);				
		
		Collection<Element> elements = new ArrayList<>();
		Consumer<org.nasdanika.drawio.Element> consumer = org.nasdanika.drawio.Util.withLinkTargets(elements::add, ConnectionBase.SOURCE);
		document.accept(consumer, null);
		Map<Element, ProcessorConfig> configs = processorConfigTransformer.transform(elements, false, progressMonitor);
		
		DrawioProcessorFactory processorFactory = createProcessorFactory();
		ReflectiveProcessorFactoryProvider<WidgetFactory, WidgetFactory, WidgetFactory> rpfp = new ReflectiveProcessorFactoryProvider<>(processorFactory);
		Map<Element, ProcessorInfo<WidgetFactory>> processors = rpfp.getFactory().createProcessors(configs.values(), false, progressMonitor);
		
		processors
			.keySet()
			.stream()
			.filter(ModelElement.class::isInstance)
			.map(ModelElement.class::cast)
			.filter(ModelElement::isTargetLink)
			.map(DrawioActionGenerator::getLinkTargetRecursive)
			.forEach(entry ->  ((LinkTargetProcessor<LinkTarget>) processors.get(entry.getValue()).getProcessor()).addReferrer(entry.getKey()));
		
		System.out.println(processors.size());
		
		DocumentProcessor docProcessor = (DocumentProcessor) processors.get(document).getProcessor();
				
		URI baseURI = URI.createURI("tmp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/");
		docProcessor.resolve(baseURI, progressMonitor);
		
		Supplier<Collection<Label>> labelsSupplier = docProcessor.createLabelsSupplier();
		return labelsSupplier.then(labels -> {
			for (Label label: labels) {
				label.rebase(null, baseURI);
			}
			
			return labels;
		});
	}

	protected DrawioProcessorFactory createProcessorFactory() {
		return new DrawioProcessorFactory(capabilityLoader) {
			
			@Override
			protected String getIconProperty() {
				return DrawioActionGenerator.this.getIconProperty();
			}	
			
			@Override
			protected String getTitleProperty() {
				return DrawioActionGenerator.this.getTitleProperty();
			}	
				
			@Override
			protected String getDocumentationProperty() {
				return DrawioActionGenerator.this.getDocumentationProperty();
			}	
				
			@Override
			protected String getDocRefProperty() {
				return DrawioActionGenerator.this.getDocRefProperty();
			}	
			
			@Override
			protected String getDocFormatProperty() {
				return DrawioActionGenerator.this.getDocFormatProperty();
			}		
			
			/**
			 * Override to implement filtering of representation elements
			 * @param representationElement
			 * @param registry
			 * @param progressMonitor
			 */
			@Override
			protected void filterRepresentationElement(
					ModelElement sourceElement,
					ModelElement representationElement,
					Map<org.nasdanika.drawio.Element, ProcessorInfo<WidgetFactory>> registry,
					ProgressMonitor progressMonitor) {
				
				DrawioActionGenerator.this.filterRepresentationElement(sourceElement, representationElement, registry, progressMonitor);				
			}

			/**
			 * Override to customize viewer.
			 * @return
			 */
			@Override
			protected String getViewer() {
				return DrawioActionGenerator.this.getViewer();
			}
				
			/**
			 * Application base for resolving relative image URL's. 
			 * This implementation returns DEFAULT_APP_BASE. 
			 * Override to customize for different (e.g. intranet) installations.
			 * App sources - https://github.com/jgraph/drawio/tree/dev/src/main/webapp.
			 * For the purposes of serving images and a diagram editor the web app can be deployed as a static site.
			 * It can also be deployed as a Docker container - https://www.drawio.com/blog/diagrams-docker-app, https://hub.docker.com/r/jgraph/drawio 
			 * @return
			 */
			@Override
			protected URI getAppBase() {
				return DrawioActionGenerator.this.getAppBase();
			}
			
			/**
			 * This implementation returns the argument. 
			 * Override to rewrite URL's before conversion to icons. For example, read representations from a file system and convert to data URL's.
			 * @param imageRepr
			 * @return
			 */
			@Override
			protected String rewriteImage(String imageRepr, ProgressMonitor progressMonitor) {
				return DrawioActionGenerator.this.rewriteImage(imageRepr, progressMonitor);
			}
			
			/**
			 * Icon size to scale image representations to
			 * @return
			 */
			protected int getIconSize() {
				return DrawioActionGenerator.this.getIconSize();
			}

			/**
			 * Override to create additional content from a representation (page).
			 * For example, aria for screen readers and AI explaining the diagrams.
			 * This implementation returns an empty collection.
			 * @param representation
			 * @param registry
			 * @param progressMonitor
			 * @return
			 */
			protected Collection<? extends EObject> createRepresentationContent(
					Document representation,
					Map<org.nasdanika.drawio.Element, ProcessorInfo<WidgetFactory>> registry,
					ProgressMonitor progressMonitor) {
				
				return DrawioActionGenerator.this.createRepresentationContent(representation, registry, progressMonitor);
			}
			
			@Override
			protected <T extends WidgetFactory> T filter(
					ProcessorConfig config, 
					T processor,
					BiConsumer<Element, BiConsumer<ProcessorInfo<Object>, ProgressMonitor>> infoProvider,
					ProgressMonitor progressMonitor) {
				return DrawioActionGenerator.this.filter(config, processor, infoProvider, progressMonitor);
			}
			
		};
	}

	private static Map.Entry<ModelElement, LinkTarget> getLinkTargetRecursive(ModelElement source) {				
		// Preventing infinite loops
		HashSet<ModelElement> tracker = new HashSet<ModelElement>();
		ModelElement modelElement = source; 
		while (tracker.add(modelElement) && modelElement.isTargetLink() && modelElement.getLinkTarget() instanceof ModelElement) { 
			modelElement = (ModelElement) modelElement.getLinkTarget();			
		}
		
		if (source != modelElement) {
			return Map.entry(source, modelElement); // Not going to pages
		}
		
		if (source.isTargetLink()) {
			return Map.entry(source, source.getLinkTarget());
		}
		
		return null;
	}
				
}
