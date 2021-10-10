package org.nasdanika.html.flow.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.ReconcileAction;
import org.nasdanika.exec.resources.ResourcesFactory;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.flow.Package;
import org.nasdanika.html.emf.EObjectActionResolver;
import org.nasdanika.html.flow.FlowActionProviderAdapterFactory;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.gen.AppAdapterFactory;
import org.nasdanika.html.model.app.gen.Util;
import org.nasdanika.html.model.app.util.ActionProvider;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.ncore.util.NcoreResourceSet;

/**
 * Tests of agile flows.
 * @author Pavel
 *
 */
public class TestAgileDocGen extends TestBase {
	
	private static final URI CONTAINER_MODELS_URI = URI.createFileURI(new File("target/model-doc/containers/").getAbsolutePath());				
	
	protected void generateActionModel(String name, ProgressMonitor progressMonitor) throws Exception {	
		load(
				"agile/" + name + ".yml", 
				obj -> {
					org.nasdanika.flow.Package core = (org.nasdanika.flow.Package) obj;					
					Package instance = core.create();
					ResourceSet resourceSet = new NcoreResourceSet();
					resourceSet.getAdapterFactories().add(new FlowActionProviderAdapterFactory(Context.EMPTY_CONTEXT));
					resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(org.eclipse.emf.ecore.resource.Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
					org.eclipse.emf.ecore.resource.Resource resource = resourceSet.createResource(URI.createURI("mem://xxx/" + name + "-instance.xml"));
					resource.getContents().add(instance);
					
					ResourceSet actionModelsResourceSet = new ResourceSetImpl();
					actionModelsResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(org.eclipse.emf.ecore.resource.Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
					
					File modelDocActionsDir = new File("target/model-doc/actions").getAbsoluteFile();
//					delete(modelDocActionsDir);
					modelDocActionsDir.mkdirs();

					File output = new File(modelDocActionsDir, name + ".xml");
					
					org.eclipse.emf.ecore.resource.Resource actionModelResource = actionModelsResourceSet.createResource(URI.createFileURI(output.getAbsolutePath()));
					
					try {
						Map<EObject,Action> registry = new HashMap<>();
						Action rootAction = EObjectAdaptable.adaptTo(instance, ActionProvider.class).execute(registry::put, progressMonitor);
						Adapter resolver = EcoreUtil.getExistingAdapter(rootAction, EObjectActionResolver.class);
						if (resolver instanceof EObjectActionResolver) {
							((EObjectActionResolver) resolver).execute(registry::get, progressMonitor);
						}
						actionModelResource.getContents().add(rootAction);

						actionModelResource.save(null);
					} catch (Exception e) {
						throw new NasdanikaException(e);
					}
					
//					generateFlowDiagram((Flow) instance.getActivities().get("development"), "core");		
					
				},
				diagnostic -> {
					if (diagnostic.getStatus() == Status.FAIL || diagnostic.getStatus() == Status.ERROR) {
						System.err.println("***********************");
						System.err.println("*      Diagnostic     *");
						System.err.println("***********************");
						diagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
					}
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				},
				progressMonitor);		
	}

//	private void generateFlowDiagram(Flow flow, String name) {
//		FlowStateDiagramGenerator flowStateDiagramGenerator = new FlowStateDiagramGenerator() {
//		
//			@Override
//			protected String getFlowElementLocation(String key, FlowElement<?> flowElement) {
//				return "https://www.nasdanika.org/" + key + ".html";
//			}
//			
//		};
//		
//		Diagram diagram = flowStateDiagramGenerator.generateFlowDiagram(flow);
//		
//		Generator generator = new Generator();
//		
//		String spec = generator.generateSpec(diagram);
//		System.out.println(spec);
//		File outputDir = new File("target/diagrams");
//		outputDir.mkdirs();
//		try {
//			Files.write(new File(outputDir, name + ".html").toPath(), generator.generateUmlDiagram(diagram).getBytes(StandardCharsets.UTF_8));
//		} catch (Exception e) {
//			throw new NasdanikaException(e);
//		}
//	}
//	
//	private DiagramGenerator createDiagramGenerator(ProgressMonitor progressMonitor) {
//		FileSystemContainer output = new FileSystemContainer(new File("target\\diagram-cache"));
//		
//		BiFunction<String,InputStream,String> decoder = (path, state) -> DefaultConverter.INSTANCE.convert(state, String.class);
//		BiFunction<String,String,InputStream> encoder = (path, state) -> DefaultConverter.INSTANCE.convert(state, InputStream.class);
//		return DiagramGenerator.INSTANCE.cachingDiagramGenerator(output.stateAdapter().adapt(decoder, encoder), progressMonitor);
//	}
	
	/**
	 * Generates a resource model from an action model.
	 * @throws Exception
	 */
	public void generateResourceModel(String name, ProgressMonitor progressMonitor) throws Exception {
		Consumer<Diagnostic> diagnosticConsumer = diagnostic -> {
			if (diagnostic.getStatus() == Status.FAIL || diagnostic.getStatus() == Status.ERROR) {
				System.err.println("***********************");
				System.err.println("*      Diagnostic     *");
				System.err.println("***********************");
				diagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
			}
			assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		};
		
		Context modelContext = Context.singleton("model-name", name);
		String actionsResource = "actions.yml";
		Action root = (Action) Objects.requireNonNull(loadObject(actionsResource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + actionsResource);
		
		Container container = ResourcesFactory.eINSTANCE.createContainer();
		container.setName(name);
		container.setReconcileAction(ReconcileAction.OVERWRITE);
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		Resource modelResource = resourceSet.createResource(URI.createURI(name + ".xml").resolve(CONTAINER_MODELS_URI));
		modelResource.getContents().add(container);
		
		String pageTemplateResource = "page-template.yml";
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
	public void generateContainer(String name, ProgressMonitor progressMonitor) throws Exception {
		ResourceSet resourceSet = createResourceSet();
		
		resourceSet.getAdapterFactories().add(new AppAdapterFactory());
				
		Resource containerResource = resourceSet.getResource(URI.createURI(name + ".xml").resolve(CONTAINER_MODELS_URI), true);
	
		BinaryEntityContainer container = new FileSystemContainer(new File("target/model-doc/site"));
		for (EObject eObject : containerResource.getContents()) {
			Diagnostician diagnostician = new Diagnostician();
			org.eclipse.emf.common.util.Diagnostic diagnostic = diagnostician.validate(eObject);
			assertThat(diagnostic.getSeverity()).isNotEqualTo(org.eclipse.emf.common.util.Diagnostic.ERROR);
			generate(eObject, container, Context.EMPTY_CONTEXT, progressMonitor);
		}		
	}
	
	protected ResourceSet createResourceSet() {
		// Load model from XMI
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
	
		resourceSet.getPackageRegistry().put(ExecPackage.eNS_URI, ExecPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ContentPackage.eNS_URI, ContentPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ResourcesPackage.eNS_URI, ResourcesPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(HtmlPackage.eNS_URI, HtmlPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(BootstrapPackage.eNS_URI, BootstrapPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(AppPackage.eNS_URI, AppPackage.eINSTANCE);
//		resourceSet.getPackageRegistry().put(FlowPackage.eNS_URI, FlowPackage.eINSTANCE);
		return resourceSet;
	}
	
	/**
	 * Generates a resource model from an action model and then generates files from the resource model.
	 * @throws Exception
	 */
	@Test
	public void generateCoreSite() throws Exception {
		String name = "core";
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		generateActionModel(name, progressMonitor);
		generateResourceModel(name, progressMonitor);
		generateContainer(name, progressMonitor);
	}	
	
}
