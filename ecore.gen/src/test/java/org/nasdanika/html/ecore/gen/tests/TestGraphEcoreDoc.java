package org.nasdanika.html.ecore.gen.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.Util;
import org.nasdanika.graph.processor.IntrospectionLevel;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.ecore.gen.processors.EcoreNodeProcessorFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.URINodeProcessor;
import org.nasdanika.html.model.app.graph.emf.EObjectReflectiveProcessorFactory;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.HtmlPackage;

/**
 * Tests Ecore -> Graph -> Processor -> actions generation
 * @author Pavel
 *
 */
public class TestGraphEcoreDoc {
	
	protected Object eKeyToPathSegment(EAttribute keyAttribute, Object keyValue) {
		return keyValue;
	}	
	
	protected String path(EObjectNode source, EObjectNode target, EReference reference, int index) {
		if (reference.getEKeys().isEmpty() && target.getTarget() instanceof ENamedElement && reference.isUnique()) {
			// TODO - eOperation - hash of signature, copy from the old code.
			
			return ((ENamedElement) target.getTarget()).getName();
		}
		return Util.path(source, target, reference, index, this::eKeyToPathSegment);
	}
	
	@Test
	public void testGraphEcoreDoc() throws IOException {
		List<EPackage> ePackages = Arrays.asList(
//				org.nasdanika.ncore.NcorePackage.eINSTANCE,
//				ExecPackage.eINSTANCE,
//				org.nasdanika.exec.content.ContentPackage.eINSTANCE,
//				org.nasdanika.exec.resources.ResourcesPackage.eINSTANCE,
//				HtmlPackage.eINSTANCE, 
//				BootstrapPackage.eINSTANCE, 
				AppPackage.eINSTANCE);
				
		List<EPackage> topLevelPackages = ePackages.stream().filter(ep -> ep.getESuperPackage() == null).collect(Collectors.toList());
		List<EObjectNode> nodes = Util.load(topLevelPackages, this::path);
		
		Context context = Context.EMPTY_CONTEXT;
		EcoreNodeProcessorFactory ecoreNodeProcessorFactory = new EcoreNodeProcessorFactory(context);
		EObjectReflectiveProcessorFactory eObjectReflectiveProcessorFactory = new EObjectReflectiveProcessorFactory(IntrospectionLevel.ACCESSIBLE, ecoreNodeProcessorFactory);
		
		ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
		org.nasdanika.html.model.app.graph.Registry<URI> registry = eObjectReflectiveProcessorFactory.createProcessors(nodes, progressMonitor);
		
		List<Throwable> failures = registry
				.getProcessorInfoMap()
				.values()
				.stream()
				.flatMap(pi -> pi.getFailures().stream())
				.collect(Collectors.toList());
		
		if (!failures.isEmpty()) {
			NasdanikaException ne = new NasdanikaException("Theres's been " + failures.size() +  " failures during processor creation: " + failures);
			for (Throwable failure: failures) {
				ne.addSuppressed(failure);
			}
			throw ne;
		}						
		
		Map<String,URINodeProcessor> topLevelProcessors = new HashMap<>();
		Collection<Throwable> resolveFailures = new ArrayList<>();
		
		for (EPackage topLevelPackage: topLevelPackages) {
			for (Entry<Element, ProcessorInfo<Object, Registry<URI>>> re: registry.getProcessorInfoMap().entrySet()) {
				Element element = re.getKey();
				if (element instanceof EObjectNode) {
					EObjectNode eObjNode = (EObjectNode) element;
					EObject target = eObjNode.getTarget();
					if (target == topLevelPackage) {
						ProcessorInfo<Object, Registry<URI>> info = re.getValue();
						Object processor = info.getProcessor();
						if (processor instanceof URINodeProcessor) {
							URINodeProcessor uriNodeProcessor = (URINodeProcessor) processor;
							uriNodeProcessor.resolve(URI.createURI("https://docs.nasdanika.org/" + topLevelPackage.getName() + "/"));
							topLevelProcessors.put(topLevelPackage.getName(), uriNodeProcessor);
						}
					}
				}
			}			
		}
		
		if (!resolveFailures.isEmpty()) {
			NasdanikaException ne = new NasdanikaException("Theres's been " + resolveFailures.size() +  " failures during URI resolution: " + resolveFailures);
			for (Throwable failure: resolveFailures) {
				ne.addSuppressed(failure);
			}
			throw ne;
		}								
		
		ResourceSet actionModelsResourceSet = new ResourceSetImpl();
		actionModelsResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		File actionModelsDir = new File("target\\action-models\\");
		actionModelsDir.mkdirs();
		
		Consumer<Diagnostic> diagnosticConsumer = d -> d.dump(System.out, 0);
		for (Entry<String, URINodeProcessor> pe: topLevelProcessors.entrySet()) {
			File output = new File(actionModelsDir, pe.getKey() + ".xmi");
			Resource actionModelResource = actionModelsResourceSet.createResource(URI.createFileURI(output.getAbsolutePath()));
			Collection<Label> labels = pe.getValue().createLabelsSupplier().call(progressMonitor, diagnosticConsumer);
			actionModelResource.getContents().addAll(labels);
			actionModelResource.save(null);
		}		
	}

}
