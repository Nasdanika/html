package org.nasdanika.html.model.app.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Objects;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.ResourcesFactory;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.gen.AppAdapterFactory;
import org.nasdanika.html.model.app.gen.Util;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.ncore.NcorePackage;

/**
 * Generation of resource/page model from an action model, optional saving, and then generation of files.
 * @author Pavel
 *
 */
public class TestAction extends TestBase {
	
	private static final URI CONTAINER_MODEL_URI = URI.createFileURI(new File("target/container.xml").getAbsolutePath());			

	/**
	 * Generates a resource model from an action model.
	 * @throws Exception
	 */
	@Test
	public void testGenerateResourceModel() throws Exception {
		Consumer<Diagnostic> diagnosticConsumer = diagnostic -> {
			assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		};
		
		Context modelContext = Context.EMPTY_CONTEXT;
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		String actionsResource = "app/actions.yml";
		Action root = (Action) Objects.requireNonNull(loadObject(actionsResource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + actionsResource);
		dumpToYaml(root);
		
		Container container = ResourcesFactory.eINSTANCE.createContainer();
		container.setName("Actions");
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		Resource modelResource = resourceSet.createResource(CONTAINER_MODEL_URI);
		modelResource.getContents().add(container);
		
		String pageTemplateResource = "app/page-template.yml";
		org.nasdanika.html.model.bootstrap.Page pageTemplate = (org.nasdanika.html.model.bootstrap.Page) Objects.requireNonNull(loadObject(pageTemplateResource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + pageTemplateResource);
		
		Util.generateSite(
				root, 
				pageTemplate,
				container,
				Context.EMPTY_CONTEXT,
				progressMonitor);
		
		modelResource.save(null);
		
	}
	
	/**
	 * Generates files from the previously generated resource model.
	 * @throws Exception
	 */
	@Test
	public void testGenerateContainer() throws Exception {
		// Load model from XMI
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(NcorePackage.eNS_URI, NcorePackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ExecPackage.eNS_URI, ExecPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ContentPackage.eNS_URI, ContentPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ResourcesPackage.eNS_URI, ResourcesPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(HtmlPackage.eNS_URI, HtmlPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(BootstrapPackage.eNS_URI, BootstrapPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(AppPackage.eNS_URI, AppPackage.eINSTANCE);
		
		resourceSet.getAdapterFactories().add(new AppAdapterFactory());
				
		Resource containerResource = resourceSet.getResource(CONTAINER_MODEL_URI, true);

		BinaryEntityContainer container = new FileSystemContainer(new File("target/test-outputs/container"));
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		for (EObject eObject : containerResource.getContents()) {
			Diagnostician diagnostician = new Diagnostician();
			org.eclipse.emf.common.util.Diagnostic diagnostic = diagnostician.validate(eObject);
			assertThat(diagnostic.getSeverity()).isNotEqualTo(org.eclipse.emf.common.util.Diagnostic.ERROR);
			generate(eObject, container, Context.EMPTY_CONTEXT, progressMonitor);
		}		
	}
	
	/**
	 * Generates a resource model from an action model and then generates files from the resource model.
	 * @throws Exception
	 */
	@Test
	public void testGenerateSite() throws Exception {
		long start = System.currentTimeMillis();
		testGenerateResourceModel();
		long grm = System.currentTimeMillis();
		testGenerateContainer();
		long end = System.currentTimeMillis();
		System.out.println((grm - start) + "/" + (end - grm));
	
	}
	
}
