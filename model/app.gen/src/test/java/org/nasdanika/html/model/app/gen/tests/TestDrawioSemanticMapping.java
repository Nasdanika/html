package org.nasdanika.html.model.app.gen.tests;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.drawio.Document;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.emf.persistence.YamlLoadingDrawioResourceFactory;
import org.nasdanika.emf.persistence.YamlLoadingDrawioResource;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.LinkedResourcesAdapter;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Period;
import org.nasdanika.ncore.util.NcoreResourceSet;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.ObjectLoader;


public class TestDrawioSemanticMapping extends TestBase {
	
	@SuppressWarnings("unchecked")
	protected void configure(EObject eObject, URI base, ResourceSet resourceSet, Context context, ProgressMonitor progressMonitor) {
		if (eObject instanceof Period) {
			Period period = (Period) eObject;
			String description = period.getDescription();
			
			// For testing, use content-type annotation later - resource-uri content type
			if (description != null && description.endsWith(".drawio")) {
				URI resourceURI = URI.createURI(description);
				if (resourceURI.isRelative() && base != null && !base.isRelative()) {
					resourceURI = resourceURI.resolve(base);
				}
				Resource linkedResource = resourceSet.getResource(resourceURI, true);
				if (linkedResource instanceof YamlLoadingDrawioResource) {
					((YamlLoadingDrawioResource<EObject>) linkedResource).setParent(eObject);
				}
				
				// Injection of diagram document. TODO - handle pages.
				ProcessorConfig<?> resourceProcessorConfig = (ProcessorConfig<?>) EcoreUtil.getRegisteredAdapter(linkedResource, ProcessorConfig.class);
				if (resourceProcessorConfig != null) {
					Element element = resourceProcessorConfig.getElement();
					if (element instanceof Document) {
						try {
							String docStr = ((Document) element).save(true);
							((Period) eObject).setDescription(docStr);
						} catch (TransformerException | IOException e) {
							throw new NasdanikaException(e);
						}
					}
				}				
				
				// Adding to linked resources adapter for setting children in createSemanticElement				
				LinkedResourcesAdapter linkedResourcesAdapter = (LinkedResourcesAdapter) EcoreUtil.getRegisteredAdapter(eObject, LinkedResourcesAdapter.class);
				if (linkedResourcesAdapter == null) {
					linkedResourcesAdapter = new LinkedResourcesAdapter();
					eObject.eAdapters().add(linkedResourcesAdapter);
				}
				linkedResourcesAdapter.getLinkedResources().add(linkedResource);
			}
		}
	}
	
	@Test
	public void testYamlLoadingDrawioResourceFactory() throws Exception {
		Context context = Context.EMPTY_CONTEXT;
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		// Load model from XMI
		ResourceSet resourceSet = new NcoreResourceSet();
		Map<String, Object> extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		EObjectLoader loader = new EObjectLoader(null, null, resourceSet) {
			
			@Override
			public Object create(
					ObjectLoader loader, 
					EClass eClass, 
					Object config, 
					URI base,
					ProgressMonitor progressMonitor, 
					List<? extends Marker> markers,
					BiFunction<EClass, ENamedElement, String> keyProvider, 
					Function<EClass, EObject> constructor) {				
				Object ret = super.create(loader, eClass, config, base, progressMonitor, markers, keyProvider, constructor);
				if (ret instanceof SupplierFactory) {
					ConsumerFactory<EObject> cf =  ctx -> new Consumer<EObject>() {

						@Override
						public double size() {
							return 1;
						}

						@Override
						public String name() {
							return "Configurator";
						}

						@Override
						public void execute(EObject arg, ProgressMonitor progressMonitor) {
							configure(arg, base, getResourceSet(), ctx, progressMonitor);
						}
						
					};
					ret = ((SupplierFactory<EObject>) ret).then(cf.asFunctionFactory());
				}
				return ret;
			}
			
		};
		
		YamlResourceFactory yamlResourceFactory = new YamlResourceFactory(loader, context, progressMonitor);
		extensionToFactoryMap.put("yml", yamlResourceFactory);

		YamlLoadingDrawioResourceFactory<ModelElement> yamlLoadingDrawioResourceFactory = new YamlLoadingDrawioResourceFactory<>(loader, context, progressMonitor) {
			
			protected void configureSemanticElement(
					org.nasdanika.graph.processor.ProcessorConfig<ModelElement> config, 
					ModelElement semanticElement, 
					Resource resource, 
					ProgressMonitor progressMonitor) {
				
//				if (config.getElement() instanceof org.nasdanika.drawio.ModelElement) {
//					semanticElement.setDescription(((org.nasdanika.drawio.ModelElement) config.getElement()).getTooltip());
//				}
//				if (semanticElement instanceof NamedElement && config.getElement() instanceof org.nasdanika.drawio.ModelElement) {
//					((NamedElement) semanticElement).setName(((org.nasdanika.drawio.ModelElement) config.getElement()).getLabel()); // Jsoup plain text?
//				}
				
				// TODO - graph adapter, semantic-uuid property  
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
		URI semanticMappingDrawioResourceURI = URI.createFileURI(semanticMappingDiagramFile.getCanonicalPath());//.appendFragment("Page-1");		
		Resource semanticMappingDrawioResource = resourceSet.getResource(semanticMappingDrawioResourceURI, true);
		EList<EObject> contents = semanticMappingDrawioResource.getContents();
		System.out.println(contents.size());
		EObject first = contents.get(0);
		System.out.println(first);
		
	}
	
}
