package org.nasdanika.html.model.app.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
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
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.content.Text;
import org.nasdanika.exec.gen.ExecutionParticpantAdapterFactory;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.ResourcesFactory;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.gen.AppAdapterFactory;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.gen.BootstrapAdapterFactory;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.gen.HtmlAdapterFactory;

/**
 * Generation of resource/page model from an action model, optional saving, and then generation of files.
 * @author Pavel
 *
 */
public class TestAction extends TestBase {
	
	private static final URI CONTAINER_MODEL_URI = URI.createFileURI(new File("target/container.xml").getAbsolutePath());			
		
	@Test
	public void testGenerateResourceModel() throws Exception {
		Consumer<Diagnostic> diagnosticConsumer = diagnostic -> {
			assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		};
		
		Context modelContext = Context.EMPTY_CONTEXT;
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		String resource = "app/actions.yml";
		Action root = (Action) Objects.requireNonNull(loadObject(resource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + resource);
		dumpToYaml(root);
		
		URI baseURI = URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/");
		BiFunction<Action, URI, URI> uriResolver = uriResolver(Context.singleton("base-uri", baseURI));
		
		Container container = ResourcesFactory.eINSTANCE.createContainer();
		container.setName("Actions");
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		Resource modelResource = resourceSet.createResource(CONTAINER_MODEL_URI);
		modelResource.getContents().add(container);
		
		generate(root, (Action) root.getChildren().get(0), root, uriResolver, baseURI, container, progressMonitor);
		
		modelResource.save(null);
		
	}
	
	private void generate(
			Action root, 
			Action principal, 
			Action activeAction, 
			BiFunction<Action, URI, URI> uriResolver, 
			URI baseURI,
			Container container,
			ProgressMonitor progressMonitor) {
		
		if (activeAction.eContainmentFeature() != AppPackage.Literals.ACTION__SECTIONS) {
			URI uri = uriResolver.apply(activeAction, baseURI);				
			if (uri != null && uri.isRelative()) {
				org.nasdanika.exec.resources.File file = container.getFile(uri.toString());
				Text text = ContentFactory.eINSTANCE.createText();
				text.setContent(uri.toString());
				file.getContents().add(text);
				
				for (org.nasdanika.exec.resources.Resource res: activeAction.getResources()) {
					((Container) file.eContainer()).getContents().add(EcoreUtil.copy(res));					
				}				
			}
		}
		
		for (EObject child: activeAction.getChildren()) {
			if (child instanceof Action) {
				generate(root, principal, (Action) child, uriResolver, baseURI, container, progressMonitor);
			}
		}
		
		for (EObject section: activeAction.getSections()) {
			if (section instanceof Action) {
				generate(root, principal, (Action) section, uriResolver, baseURI, container, progressMonitor);
			}
		}
				
		for (EObject navigation: activeAction.getNavigation()) {
			if (navigation instanceof Action) {
				generate(root, principal, (Action) navigation, uriResolver, baseURI, container, progressMonitor);
			}
		}
		
		for (EObject anonymous: activeAction.getAnonymous()) {
			if (anonymous instanceof Action) {
				generate(root, principal, (Action) anonymous, uriResolver, baseURI, container, progressMonitor);
			}
		}
		
		generate(root, principal, activeAction.getFloatLeftNavigation(), uriResolver, baseURI, container, progressMonitor);
		generate(root, principal, activeAction.getFloatRightNavigation(), uriResolver, baseURI, container, progressMonitor);
		generate(root, principal, activeAction.getLeftNavigation(), uriResolver, baseURI, container, progressMonitor);
		generate(root, principal, activeAction.getRightNavigation(), uriResolver, baseURI, container, progressMonitor);
	}

	protected void generate(
			Action root, 
			Action principal, 
			NavigationPanel navigationPanel,
			BiFunction<Action, URI, URI> uriResolver, 
			URI baseURI, 
			Container container,
			ProgressMonitor progressMonitor) {
		
		if (navigationPanel != null) {
			for (EObject item: navigationPanel.getItems()) {
				if (item instanceof Action) {
					generate(root, principal, (Action) item, uriResolver, baseURI, container, progressMonitor);
				}
			}			
		}
	}
	
	/**
	 * @param context
	 * @return A function resolving {@link URI} for the argument {@link Action}. Caches results.
	 */
	private BiFunction<Action,URI,URI> uriResolver(Context context) {		
		return new BiFunction<Action, URI, URI>() {
			Map<Action, URI> cache = new HashMap<>();
			
			@Override
			public URI apply(Action action, URI base) {
				URI uri = cache.computeIfAbsent(action, this::compute);
				return base == null ? uri : uri.deresolve(base);
			}
			
			private URI compute(Action action) {
				String uriString = context.interpolateToString(action.getLocation());
				if (Util.isBlank(uriString)) {
					return null;
				}
				if (action.eContainmentFeature() == AppPackage.Literals.ACTION__SECTIONS) {
					uriString = "#" + uriString;
				}
				URI uri = URI.createURI(uriString);
				if (uri.isRelative()) {
					URI base = getAncestorURI(action);				
					if (base != null) {					
						return uri.resolve(base);
					}
				}
				return uri;
			}
			
			private URI getAncestorURI(Action action) {
				for (EObject ancestor = action.eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
					if (ancestor instanceof Action) {
						URI ancestorURI = apply((Action) ancestor, null);
						if (ancestorURI != null) {
							return ancestorURI;
						}
					}
				}		
				return null;
			}
			
		};
	}
	
	@Test
	public void testGenerateContainer() throws Exception {
		// Load model from XMI
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		resourceSet.getPackageRegistry().put(ExecPackage.eNS_URI, ExecPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ContentPackage.eNS_URI, ContentPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ResourcesPackage.eNS_URI, ResourcesPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(HtmlPackage.eNS_URI, HtmlPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(BootstrapPackage.eNS_URI, BootstrapPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(AppPackage.eNS_URI, AppPackage.eINSTANCE);
		
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
		adapterFactory.registerAdapterFactory(new ExecutionParticpantAdapterFactory());
		adapterFactory.registerAdapterFactory(new HtmlAdapterFactory());
		adapterFactory.registerAdapterFactory(new BootstrapAdapterFactory());
		adapterFactory.registerAdapterFactory(new AppAdapterFactory());		
		
		resourceSet.getAdapterFactories().add(adapterFactory);
				
		// Demand load resource for this file.
		Resource containerResource = resourceSet.getResource(CONTAINER_MODEL_URI, true);
				

		BinaryEntityContainer container = new FileSystemContainer(new File("target/test-outputs/container"));
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		for (EObject eObject : containerResource.getContents()) {
			Diagnostician diagnostician = new Diagnostician();
			org.eclipse.emf.common.util.Diagnostic diagnostic = diagnostician.validate(eObject);
			assertThat(diagnostic.getSeverity()).isNotEqualTo(org.eclipse.emf.common.util.Diagnostic.ERROR);
			generate(eObject, container, Context.EMPTY_CONTEXT, progressMonitor);
		}		
		
//		BinaryEntityContainer myContainer = container.getContainer("my-container", progressMonitor);
//		assertThat(myContainer).isNotNull();
//		assertThat(myContainer.exists(progressMonitor)).isTrue();
//		
//		BinaryEntity file = myContainer.get("simple.html", progressMonitor);
//		assertThat(file).isNotNull();
//		assertThat(file.exists(progressMonitor)).isTrue();
	}

	
}
