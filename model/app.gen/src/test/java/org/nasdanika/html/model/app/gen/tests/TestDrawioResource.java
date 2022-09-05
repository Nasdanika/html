package org.nasdanika.html.model.app.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.emf.persistence.GitMarkerFactory;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.ResourcesFactory;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.drawio.ResourceFactory;
import org.nasdanika.html.model.app.gen.AppAdapterFactory;
import org.nasdanika.html.model.app.gen.Util;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.resources.BinaryEntityContainer;
import org.nasdanika.resources.FileSystemContainer;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;


/**
 * Generation of resource/page model from an action model, optional saving, and then generation of files.
 * @author Pavel
 *
 */
public class TestDrawioResource extends TestBase {
	
//    - app-action:
//        location: ${base-uri}search.html
//        icon: fas fa-search
//        text: Search
//        content:
//          content-resource: search.html

	
	private static final URI CONTAINER_MODEL_URI = URI.createFileURI(new File("target/drawio.xml").getAbsolutePath());			

	/**
	 * Generates a resource model from an action model.
	 * @throws Exception
	 */
	public void testGenerateResourceModel() throws Exception {
		Context modelContext = Context.EMPTY_CONTEXT;
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();

		ResourceSet resourceSet = new ResourceSetImpl();
		Registry packageRegistry = resourceSet.getPackageRegistry();
				
		packageRegistry.put(BootstrapPackage.eINSTANCE.getNsURI(), BootstrapPackage.eINSTANCE);
		packageRegistry.put(AppPackage.eINSTANCE.getNsURI(), AppPackage.eINSTANCE); 
		packageRegistry.put(HtmlPackage.eINSTANCE.getNsURI(), HtmlPackage.eINSTANCE); 
		packageRegistry.put(ExecPackage.eINSTANCE.getNsURI(), ExecPackage.eINSTANCE); 
		packageRegistry.put(ContentPackage.eINSTANCE.getNsURI(), ContentPackage.eINSTANCE); 
		packageRegistry.put(ResourcesPackage.eINSTANCE.getNsURI(), ResourcesPackage.eINSTANCE); 
		packageRegistry.put(NcorePackage.eINSTANCE.getNsURI(), NcorePackage.eINSTANCE); 
		
		Consumer<Diagnostic> diagnosticConsumer = diagnostic -> {
			assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		};
		
		String actionsResource = "app/drawio-root-action.yml";
		Action documentActionPrototype = (Action) Objects.requireNonNull(loadObject(actionsResource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + actionsResource);
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("drawio", new ResourceFactory(ConnectionBase.SOURCE, resourceSet) {
			
			@Override
			protected Action createDocumentAction(Document document) {
				return EcoreUtil.copy(documentActionPrototype);
			}
			
		});
		
		EObjectLoader eObjectLoader = new EObjectLoader(null, null, resourceSet);
		eObjectLoader.setMarkerFactory(new GitMarkerFactory());
		Resource.Factory appYamlResourceFactory = new YamlResourceFactory(eObjectLoader, modelContext, progressMonitor);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("yml", appYamlResourceFactory);
		
		File modelFile = new File("src/test/resources/org/nasdanika/html/model/app/gen/tests/app/aws-containers.drawio");
		assertThat(modelFile.isFile());
		Resource modelResource = resourceSet.getResource(URI.createFileURI(modelFile.getCanonicalPath()), true);
		
		Action root = (Action) modelResource.getContents().get(0);
		
		Resource dumpRes = new XMIResourceFactoryImpl().createResource(URI.createURI("temp://blah"));
		dumpRes.getContents().add(EcoreUtil.copy(root));
		dumpRes.save(System.out, null);
		
		Container container = ResourcesFactory.eINSTANCE.createContainer();
		container.setName("Drawio");
		
		ResourceSet containerResourceSet = new ResourceSetImpl();
		containerResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		Resource containerResource = containerResourceSet.createResource(CONTAINER_MODEL_URI);
		containerResource.getContents().add(container);
		
		String pageTemplateResource = "app/page-template.yml";
		org.nasdanika.html.model.bootstrap.Page pageTemplate = (org.nasdanika.html.model.bootstrap.Page) Objects.requireNonNull(loadObject(pageTemplateResource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + pageTemplateResource);
		
		Util.generateSite(
				root, 
				pageTemplate,
				container,
				null,
				null,
				Context.EMPTY_CONTEXT,
				progressMonitor);
		
		containerResource.save(null);		
	}
	
	/**
	 * Generates files from the previously generated resource model.
	 * @throws Exception
	 */
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

		File siteDir = new File("target/test-outputs/container");
		BinaryEntityContainer container = new FileSystemContainer(siteDir);
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		for (EObject eObject : containerResource.getContents()) {
			Diagnostician diagnostician = new Diagnostician();
			org.eclipse.emf.common.util.Diagnostic diagnostic = diagnostician.validate(eObject);
			assertThat(diagnostic.getSeverity()).isNotEqualTo(org.eclipse.emf.common.util.Diagnostic.ERROR);
			generate(eObject, container, Context.EMPTY_CONTEXT, progressMonitor);
		}	
		
		generateSitemapAndSearch(new File(siteDir, "Drawio"));
	}
	
	/**
	 * 
	 * @param siteDir
	 * @return Number of broken links.
	 * @throws IOException
	 */
	private void generateSitemapAndSearch(File siteDir) throws IOException {
		AtomicInteger problems = new AtomicInteger();
		
		// Site map and search index
		JSONObject searchDocuments = new JSONObject();		
		String domain = "https://docs.nasdanika.org";
		WebSitemapGenerator wsg = new WebSitemapGenerator(domain, siteDir);
		BiConsumer<File, String> listener = new BiConsumer<File, String>() {
			
			@Override
			public void accept(File file, String path) {
				if (path.endsWith(".html")) {
					try {
						WebSitemapUrl url = new WebSitemapUrl.Options(domain + "/" + path)
							    .lastMod(new Date(file.lastModified())).changeFreq(ChangeFreq.WEEKLY).build();
						wsg.addUrl(url); 
					} catch (MalformedURLException e) {
						throw new NasdanikaException(e);
					}
					
					// Excluding search.html and aggregator pages which contain information present elsewhere
					try {	
						Predicate<String> predicate = org.nasdanika.html.model.app.gen.Util.createRelativeLinkPredicate(file, siteDir);						
						Consumer<? super Element> inspector = org.nasdanika.html.model.app.gen.Util.createInspector(predicate, error -> {
							System.err.println("[" + path +"] " + error);
							problems.incrementAndGet();
						});
						JSONObject searchDocument = org.nasdanika.html.model.app.gen.Util.createSearchDocument(path, file, inspector, null);
						if (searchDocument != null) {
							searchDocuments.put(path, searchDocument);
						}
					} catch (IOException e) {
						throw new NasdanikaException(e);
					}
				}
			}
		};
		org.nasdanika.common.Util.walk(null, listener, siteDir.listFiles());
		wsg.write();	

		try (FileWriter writer = new FileWriter(new File(siteDir, "search-documents.js"))) {
			writer.write("var searchDocuments = " + searchDocuments);
		}
		
		if (problems.get() > 0) {
			fail("There are broken links: " + problems.get());
		};
	}
	
	/**
	 * Generates a resource model from an action model and then generates files from the resource model.
	 * @throws Exception
	 */
	@Test
//	@Ignore("Fails command line build due to not finding services from the core drawio module. Potential solution is here: https://stackoverflow.com/questions/54087050/java-9-serviceloader-doesnt-load-test-implementation-from-test-sources-module")
	public void testGenerateSite() throws Exception {
		long start = System.currentTimeMillis();
		testGenerateResourceModel();
		long grm = System.currentTimeMillis();
		testGenerateContainer();
		long end = System.currentTimeMillis();
		System.out.println((grm - start) + "/" + (end - grm));	
	}
	
}