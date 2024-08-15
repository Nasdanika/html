package org.nasdanika.html.model.app.graph.tests;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
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
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.drawio.DocumentProcessor;
import org.nasdanika.html.model.app.graph.drawio.DrawioProcessorFactory;
import org.nasdanika.html.model.app.graph.drawio.LinkTargetProcessor;

public class TestDrawioActionSiteGenerator {
	
	@SuppressWarnings("unchecked")
	@Test
//	@Disabled
	public void testGenerateDrawioActionSite() throws Exception {
		Document document = Document.load(getClass().getResource("app/aws.drawio")); 
		
		NopEndpointProcessorConfigFactory<WidgetFactory> processorConfigFactory = new NopEndpointProcessorConfigFactory<WidgetFactory>() {
			
			@Override
			protected boolean isPassThrough(Connection incomingConnection) {
				return false;
			}
			
		};
		Transformer<Element,ProcessorConfig> processorConfigTransformer = new Transformer<>(processorConfigFactory);				
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		Collection<Element> elements = new ArrayList<>();
		Consumer<org.nasdanika.drawio.Element> consumer = org.nasdanika.drawio.Util.withLinkTargets(elements::add, ConnectionBase.SOURCE);
		document.accept(consumer, null);
		Map<Element, ProcessorConfig> configs = processorConfigTransformer.transform(elements, false, progressMonitor);
		
		DrawioProcessorFactory processorFactory = new DrawioProcessorFactory();
		ReflectiveProcessorFactoryProvider<WidgetFactory, WidgetFactory, WidgetFactory> rpfp = new ReflectiveProcessorFactoryProvider<>(processorFactory);
		Map<Element, ProcessorInfo<WidgetFactory>> processors = rpfp.getFactory().createProcessors(configs.values(), false, progressMonitor);
		
		processors
			.keySet()
			.stream()
			.filter(ModelElement.class::isInstance)
			.map(ModelElement.class::cast)
			.filter(ModelElement::isTargetLink)
			.forEach(source -> ((LinkTargetProcessor<LinkTarget>) processors.get(source.getLinkTarget()).getProcessor()).referrers.add(source));
		
		System.out.println(processors.size());
		
		DocumentProcessor docProcessor = (DocumentProcessor) processors.get(document).getProcessor();
				
		URI baseURI = URI.createURI("tmp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/");
		docProcessor.resolve(baseURI, progressMonitor);
		
		Supplier<Collection<Label>> labelSupplier = docProcessor.createLabelsSupplier();
		Consumer<Diagnostic> diagnosticConsumer = d -> d.dump(System.out, 0);
		Collection<Label> labels = labelSupplier.call(progressMonitor, diagnosticConsumer);
		
		Action rootAction = AppFactory.eINSTANCE.createAction();
		rootAction.setText("Nasdanika");
		rootAction.getChildren().addAll(labels);
		
		String siteMapDomain = "https://family.models.nasdanika.org/demos/simple";		
		ActionSiteGenerator actionSiteGenerator = new ActionSiteGenerator();		
		
		Map<String, Collection<String>> errors = actionSiteGenerator.generate(
				rootAction, 
				Theme.Cerulean.pageTemplateCdnURI, 
				siteMapDomain, 
				new File("target/drawio-action-site"), 
				new File("target/doc-site-work-dir"), 
				true);
				
		int errorCount = 0;
		for (Entry<String, Collection<String>> ee: errors.entrySet()) {
			System.err.println(ee.getKey());
			for (String error: ee.getValue()) {
				System.err.println("\t" + error);
				++errorCount;
			}
		}
		
		System.out.println(errorCount);
	}

}
