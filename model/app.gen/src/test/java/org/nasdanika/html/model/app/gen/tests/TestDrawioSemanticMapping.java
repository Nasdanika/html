package org.nasdanika.html.model.app.gen.tests;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.DrawioEObjectFactory;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.emf.persistence.NcoreYamlSupplier;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.emf.persistence.YamlLoadingDrawioResourceFactory;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.gen.AppAdapterFactory;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.NcoreResourceSet;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.MarkerImpl;
import org.yaml.snakeyaml.error.MarkedYAMLException;


public class TestDrawioSemanticMapping extends TestBase {
	
	@Test
	public void testDrawioEObjectFactory() throws Exception {
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

		YamlLoadingDrawioResourceFactory yamlLoadingDrawioResourceFactory = new YamlLoadingDrawioResourceFactory(loader, context, progressMonitor);
		extensionToFactoryMap.put("yml", yamlResourceFactory);
	
		
		
		resourceSet.getPackageRegistry().put(NcorePackage.eNS_URI, NcorePackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(ExecPackage.eNS_URI, ExecPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(ContentPackage.eNS_URI, ContentPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(ResourcesPackage.eNS_URI, ResourcesPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(HtmlPackage.eNS_URI, HtmlPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(BootstrapPackage.eNS_URI, BootstrapPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(AppPackage.eNS_URI, AppPackage.eINSTANCE);
		
//		resourceSet.getAdapterFactories().add(new AppAdapterFactory());
		
		
		
		// ResourceSet
		ResourceSet resourceSet = new NcoreResourceSet();
		
		// TODO - configure - add EPackages and factories.
		
		// EObjectLoader
		EObjectLoader loader = new EObjectLoader(resourceSet);
		
		Document document = Document.load(getClass().getResource("app/semantic-mapping.drawio"));
		
		
		DrawioEObjectFactory<EObject> drawioEObjectFactory = new DrawioEObjectFactory<>() {
			
			@Override
			protected EObject load(String spec, URI specBase, ProgressMonitor progressMonitor) {
				try {
					Object data = loader.loadYaml(inputStream, getURI(), progressMonitor);
					if (data instanceof Collection) {
						getContents().addAll((Collection<EObject>) data);
					} else {
						java.util.function.Consumer<Diagnostic> diagnosticConsumer = diagnostic -> {
							if (diagnostic.getStatus() == Status.FAIL || diagnostic.getStatus() == Status.ERROR) {
								System.err.println("***********************");
								System.err.println("*      Diagnostic     *");
								System.err.println("***********************");
								diagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
							}
							if (diagnostic.getStatus() != Status.SUCCESS) {
								throw new DiagnosticException(diagnostic);
							};
						};
						if (data instanceof SupplierFactory) {
							EObject eObject = Util.call(((SupplierFactory<EObject>) data).create(context), progressMonitor, diagnosticConsumer);
							getContents().add(eObject);
						} else if (data instanceof EObject) {
							getContents().add((EObject) data);
						} else {
							throw new IOException("Not an instance of EObject: " + data);
						}
					}
				} catch (MarkedYAMLException e) {
					throw new ConfigurationException(e.getMessage(), e, new MarkerImpl(getURI().toString(), e.getProblemMark()));
				} catch (RuntimeException | IOException e) {
					throw e;
				} catch (Exception e) {
					throw new NasdanikaException(e);
				}
			}
									
			@Override
			protected EObject load(URI specURI, ProgressMonitor progressMonitor) {
				
				try {
					return Objects.requireNonNull(org.nasdanika.common.Util.call(new NcoreYamlSupplier(specURI, context), progressMonitor, diagnosticConsumer), "Loaded null from " + specURI);
				} catch (DiagnosticException e) {
					System.err.println("******************************");
					System.err.println("*      Diagnostic failed     *");
					System.err.println("******************************");
					e.getDiagnostic().dump(System.err, 4, Status.FAIL);
					throw e;
				}		
			}
									
			@Override
			protected URI getBaseURI() {
				return document.getURI();
			}
			
			@Override
			protected EObject createSemanticElement(ProcessorConfig<EObject> config, ProgressMonitor progressMonitor) {
				EObject semanticElement = super.createSemanticElement(config, progressMonitor);
				if (semanticElement instanceof ModelElement && config.getElement() instanceof org.nasdanika.drawio.ModelElement) {
					((ModelElement) semanticElement).setDescription(((org.nasdanika.drawio.ModelElement) config.getElement()).getTooltip());
				}
				if (semanticElement instanceof NamedElement && config.getElement() instanceof org.nasdanika.drawio.ModelElement) {
					((NamedElement) semanticElement).setName(((org.nasdanika.drawio.ModelElement) config.getElement()).getLabel()); // Jsoup plain text?
				}
				return semanticElement;
			}
			
		};
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();		
		Map<org.nasdanika.graph.Element, ProcessorInfo<EObject>> registry = drawioEObjectFactory.createProcessors(progressMonitor, document);
		System.out.println(registry.size());
	}
	
}
