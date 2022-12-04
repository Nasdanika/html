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
import org.nasdanika.emf.persistence.NcoreDrawioResourceFactory;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.NcoreResourceSet;
import org.nasdanika.persistence.ObjectLoader;
import org.nasdanika.persistence.ObjectLoaderResourceFactory;

public class TestDrawioSemanticMapping extends TestBase {
	
	@Test
	public void testObjectLoaderDrawioResourceFactory() throws Exception {
		Context context = Context.EMPTY_CONTEXT;
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		// Load model from XMI
		ResourceSet resourceSet = new NcoreResourceSet();
		Map<String, Object> extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		EObjectLoader loader = new EObjectLoader(null, null, resourceSet);
		
		ObjectLoaderResourceFactory objectLoaderResourceFactory = new ObjectLoaderResourceFactory() {
			
			@Override
			protected ObjectLoader getObjectLoader(Resource resource) {
				return loader;
			}
			
			@Override
			protected Context getContext(Resource resource) {
				return context;
			}
			
			@Override
			protected ProgressMonitor getProgressMonitor(Resource resource) {
				return progressMonitor;
			}
			
		};
		
		extensionToFactoryMap.put("yml", objectLoaderResourceFactory);
		extensionToFactoryMap.put("json", objectLoaderResourceFactory);		
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("data", objectLoaderResourceFactory);

		NcoreDrawioResourceFactory<ModelElement> ncoreDrawioResourceFactory = new NcoreDrawioResourceFactory<>() {

			@Override
			protected ResourceSet getResourceSet() {
				return resourceSet;
			}

			@Override
			protected ProgressMonitor getProgressMonitor(URI uri) {
				return progressMonitor.split("Loading " + uri, 1);
			}
			
		};
		
		extensionToFactoryMap.put("drawio", ncoreDrawioResourceFactory);
		
		
		
		resourceSet.getPackageRegistry().put(NcorePackage.eNS_URI, NcorePackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(ExecPackage.eNS_URI, ExecPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(ContentPackage.eNS_URI, ContentPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(ResourcesPackage.eNS_URI, ResourcesPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(HtmlPackage.eNS_URI, HtmlPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(BootstrapPackage.eNS_URI, BootstrapPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(AppPackage.eNS_URI, AppPackage.eINSTANCE);
		
//		resourceSet.getAdapterFactories().add(new AppAdapterFactory());
		
		File semanticMappingDiagramFile = new File("test-semantic-mapping/semantic-mapping.drawio");		
		URI semanticMappingDrawioResourceURI = URI.createFileURI(semanticMappingDiagramFile.getCanonicalPath());//.appendFragment("Page-1");		
		Resource semanticMappingDrawioResource = resourceSet.getResource(semanticMappingDrawioResourceURI, true);
		EList<EObject> contents = semanticMappingDrawioResource.getContents();
		System.out.println(contents.size());
		EObject first = contents.get(0);
		String uriFragment = semanticMappingDrawioResource.getURIFragment(first);
		System.out.println(first + " " + uriFragment);		
	}
	
}
