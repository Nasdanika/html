package org.nasdanika.html.ecore.gen.tests;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.html.ecore.gen.EcoreLabelProviderAdapterFactory;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;
import org.nasdanika.html.model.app.util.LabelProvider;
import org.nasdanika.html.model.app.util.LabelProvider.Result;
import org.nasdanika.ncore.NcorePackage;

public class TestEcoreLabelBuilders {
	
	@Test
	public void testNcoreDocGen() throws Exception {
		EPackage ncorePackage = NcorePackage.eINSTANCE;
		EcoreLabelProviderAdapterFactory adapterFactory = new EcoreLabelProviderAdapterFactory(null, null, null);
		LabelProvider packageLabelProvider = (LabelProvider) adapterFactory.adapt(ncorePackage, LabelProvider.class);
		Supplier<Result> packageLabelSupplier = packageLabelProvider.asSupplier(null);
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();				
		Consumer<Diagnostic> diagnosticConsumer = System.out::println;
		Result supplierResult = packageLabelSupplier.call(progressMonitor, diagnosticConsumer);
		System.out.println(supplierResult.label());
		System.out.println(supplierResult.registry());		
		
		ResourceSet actionModelsResourceSet = new ResourceSetImpl();
		actionModelsResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		Resource actionModelResource = actionModelsResourceSet.createResource(URI.createFileURI(new File("target\\actions.xml").getCanonicalPath()));		
		actionModelResource.getContents().add(supplierResult.label());
		actionModelResource.save(null);
		
		URI rootActionURI = URI.createURI("classpath://org/nasdanika/html/ecore/tests/actions.yml");		
		URI pageTemplateURI = URI.createURI("classpath://org/nasdanika/html/ecore/tests/page-template.yml");
		
		ActionSiteGenerator actionSiteGenerator = new ActionSiteGenerator() {
			
		};
		
		Map<String, Collection<String>> errors = actionSiteGenerator.generate(
				rootActionURI, 
				pageTemplateURI, 
				"https://docs.nasdanika.org", 
				new File("docs"), 
				new File("target/action-site"), 
				false);
		
		
	int errorCount = 0;
	for (Entry<String, Collection<String>> ee: errors.entrySet()) {
		System.err.println(ee.getKey());
		for (String error: ee.getValue()) {
			System.err.println("\t" + error);
			++errorCount;
		}
	}
	
	System.out.println("There are " + errorCount + " site errors");
		
	}

}
