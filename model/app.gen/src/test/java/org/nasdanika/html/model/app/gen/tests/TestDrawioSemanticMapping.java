package org.nasdanika.html.model.app.gen.tests;

import java.io.File;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.emf.persistence.YamlLoadingDrawioResourceFactory;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.NcoreResourceSet;


public class TestDrawioSemanticMapping extends TestBase {
	
	@Test
	public void testYamlLoadingDrawioResourceFactory() throws Exception {
		Context context = Context.EMPTY_CONTEXT;
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		// Load model from XMI
		ResourceSet resourceSet = new NcoreResourceSet();
		Map<String, Object> extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		EObjectLoader loader = new EObjectLoader(null, null, resourceSet);
		// TODO - diagram injection using a builder feature or ConsumerFactory attribute
		
		
		YamlResourceFactory yamlResourceFactory = new YamlResourceFactory(loader, context, progressMonitor);
		extensionToFactoryMap.put("yml", yamlResourceFactory);

		YamlLoadingDrawioResourceFactory<ModelElement> yamlLoadingDrawioResourceFactory = new YamlLoadingDrawioResourceFactory<>(loader, context, progressMonitor) {
			
			protected void configureSemanticElement(
					org.nasdanika.graph.processor.ProcessorConfig<ModelElement> config, 
					ModelElement semanticElement, 
					Resource resource, 
					ProgressMonitor progressMonitor) {
				
				if (config.getElement() instanceof org.nasdanika.drawio.ModelElement) {
					semanticElement.setDescription(((org.nasdanika.drawio.ModelElement) config.getElement()).getTooltip());
				}
				if (semanticElement instanceof NamedElement && config.getElement() instanceof org.nasdanika.drawio.ModelElement) {
					((NamedElement) semanticElement).setName(((org.nasdanika.drawio.ModelElement) config.getElement()).getLabel()); // Jsoup plain text?
				}
			};			
			
		};		
		extensionToFactoryMap.put("drawio", yamlLoadingDrawioResourceFactory);
		
		resourceSet.getPackageRegistry().put(NcorePackage.eNS_URI, NcorePackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(ExecPackage.eNS_URI, ExecPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(ContentPackage.eNS_URI, ContentPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(ResourcesPackage.eNS_URI, ResourcesPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(HtmlPackage.eNS_URI, HtmlPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(BootstrapPackage.eNS_URI, BootstrapPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(AppPackage.eNS_URI, AppPackage.eINSTANCE);
		
//		resourceSet.getAdapterFactories().add(new AppAdapterFactory());
		
		File semanticMappingDiagramFile = new File("test-semantic-mapping/semantic-mapping.drawio");		
		URI semanticMappingDrawioResourceURI = URI.createFileURI(semanticMappingDiagramFile.getCanonicalPath());		
		Resource semanticMappingDrawioResource = resourceSet.getResource(semanticMappingDrawioResourceURI, true);
		EList<EObject> contents = semanticMappingDrawioResource.getContents();
		System.out.println(contents.size());
		EObject first = contents.get(0);
		System.out.println(first);
	}
	
}
