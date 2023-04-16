package org.nasdanika.html.ecore.gen.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.Util;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.ecore.gen.processors.EPackageNodeProcessor;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.Processor;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.URINodeProcessor;
import org.nasdanika.html.model.app.graph.emf.EObjectProcessorFactory;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.HtmlPackage;

/**
 * Tests Ecore -> Graph -> Processor -> actions generation
 * @author Pavel
 *
 */
public class TestGraphEcoreDoc {
	
	@Test
	public void testGraphEcoreDoc() {
		List<EPackage> ePackages = Arrays.asList(
				org.nasdanika.ncore.NcorePackage.eINSTANCE,
				ExecPackage.eINSTANCE,
				org.nasdanika.exec.content.ContentPackage.eINSTANCE,
				org.nasdanika.exec.resources.ResourcesPackage.eINSTANCE,
				HtmlPackage.eINSTANCE, 
				BootstrapPackage.eINSTANCE, 
				AppPackage.eINSTANCE);
				
		List<EPackage> topLevelPackages = ePackages.stream().filter(ep -> ep.getESuperPackage() == null).collect(Collectors.toList());
		List<EObjectNode> nodes = Util.load(topLevelPackages);
		
		EObjectProcessorFactory processorFactory = new EObjectProcessorFactory() {
			
			@Override
			protected org.nasdanika.html.model.app.graph.URINodeProcessor createNodeProcessor(
					org.nasdanika.graph.processor.NodeProcessorConfig<org.nasdanika.html.model.app.graph.Processor,org.nasdanika.html.model.app.graph.LabelFactory,org.nasdanika.html.model.app.graph.LabelFactory,org.nasdanika.html.model.app.graph.Registry<URI>> config, 
					ProgressMonitor progressMonitor) {
				
				Node node = config.getElement();
				if (node instanceof EObjectNode) {
					EObjectNode eObjectNode = (EObjectNode) node;
					EObject target = eObjectNode.getTarget();
					if (target instanceof EPackage) {
						return new EPackageNodeProcessor(config, getContext(), progressMonitor);
					}
				}								
				
				return super.createNodeProcessor(config, progressMonitor);
			};
			
		};
		ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
		org.nasdanika.html.model.app.graph.Registry<URI> registry = processorFactory.createProcessors(nodes, progressMonitor);
		
		List<URINodeProcessor> topLevelProcessors = new ArrayList<>();
		
		for (EPackage topLevelPackage: topLevelPackages) {
			for (Entry<Element, ProcessorInfo<Processor, Registry<URI>>> re: registry.getProcessorInfoMap().entrySet()) {
				Element element = re.getKey();
				if (element instanceof EObjectNode) {
					EObjectNode eObjNode = (EObjectNode) element;
					EObject target = eObjNode.getTarget();
					if (target == topLevelPackage) {
						ProcessorInfo<Processor, Registry<URI>> info = re.getValue();
						Processor processor = info.getProcessor();
						if (processor instanceof URINodeProcessor) {
							URINodeProcessor uriNodeProcessor = (URINodeProcessor) processor;
							uriNodeProcessor.resolveURI(URI.createURI("https://docs.nasdanika.org/" + topLevelPackage.getName() + "/"));
							topLevelProcessors.add(uriNodeProcessor);
						}
					}
				}
			}			
		}
		Consumer<Diagnostic> diagnosticConsumer = d -> d.dump(System.out, 0);
		for (URINodeProcessor processor: topLevelProcessors) {
			Collection<Label> labels = processor.call(progressMonitor, diagnosticConsumer);
			System.out.println(labels);
		}
		
	}

}
