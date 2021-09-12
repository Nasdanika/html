package org.nasdanika.html.model.app.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;

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
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.ResourcesFactory;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Header;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.gen.AppAdapterFactory;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.HtmlPackage;

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
		
		URI baseURI = URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/");
		BiFunction<Action, URI, URI> uriResolver = uriResolver(Context.singleton("base-uri", baseURI));
		
		Container container = ResourcesFactory.eINSTANCE.createContainer();
		container.setName("Actions");
		
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		Resource modelResource = resourceSet.createResource(CONTAINER_MODEL_URI);
		modelResource.getContents().add(container);
		
		String pageTemplateResource = "app/page-template.yml";
		org.nasdanika.html.model.bootstrap.Page pageTemplate = (org.nasdanika.html.model.bootstrap.Page) Objects.requireNonNull(loadObject(pageTemplateResource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + pageTemplateResource);
		
		generate(
				root, 
				(Action) root.getChildren().get(0), 
				root,
				pageTemplate,
				uriResolver, 
				baseURI, 
				container, 
				progressMonitor);
		
		modelResource.save(null);
		
	}
	
	private void generate(
			Action root, 
			Action principal, 
			Action activeAction, 
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			BiFunction<Action, URI, URI> uriResolver, 
			URI baseURI,
			Container container,
			ProgressMonitor progressMonitor) {
		
		if (activeAction.eContainmentFeature() != AppPackage.Literals.ACTION__SECTIONS) {
			URI uri = uriResolver.apply(activeAction, baseURI);				
			if (uri != null && uri.isRelative()) {
				org.nasdanika.exec.resources.File file = container.getFile(uri.toString());
				org.nasdanika.html.model.bootstrap.Page bootstrapPage = EcoreUtil.copy(pageTemplate);
				for (EObject be: bootstrapPage.getBody()) {
					if (be instanceof org.nasdanika.html.model.app.Page) {
						buildAppPage(root, principal, activeAction, (org.nasdanika.html.model.app.Page) be, uriResolver, progressMonitor);						
					}
				}
				file.getContents().add(bootstrapPage);
				
				for (org.nasdanika.exec.resources.Resource res: activeAction.getResources()) {
					((Container) file.eContainer()).getContents().add(EcoreUtil.copy(res));					
				}				
			}
		}
		
		for (EObject child: activeAction.getChildren()) {
			if (child instanceof Action) {
				generate(root, principal, (Action) child, pageTemplate, uriResolver, baseURI, container, progressMonitor);
			}
		}
		
		for (EObject section: activeAction.getSections()) {
			if (section instanceof Action) {
				generate(root, principal, (Action) section, pageTemplate, uriResolver, baseURI, container, progressMonitor);
			}
		}
				
		for (EObject navigation: activeAction.getNavigation()) {
			if (navigation instanceof Action) {
				generate(root, principal, (Action) navigation, pageTemplate, uriResolver, baseURI, container, progressMonitor);
			}
		}
		
		for (EObject anonymous: activeAction.getAnonymous()) {
			if (anonymous instanceof Action) {
				generate(root, principal, (Action) anonymous, pageTemplate, uriResolver, baseURI, container, progressMonitor);
			}
		}
		
		generate(root, principal, activeAction.getFloatLeftNavigation(), pageTemplate, uriResolver, baseURI, container, progressMonitor);
		generate(root, principal, activeAction.getFloatRightNavigation(), pageTemplate, uriResolver, baseURI, container, progressMonitor);
		generate(root, principal, activeAction.getLeftNavigation(), pageTemplate, uriResolver, baseURI, container, progressMonitor);
		generate(root, principal, activeAction.getRightNavigation(), pageTemplate, uriResolver, baseURI, container, progressMonitor);
	}
	
	private void buildAppPage(
			Action root, 
			Action principal, 
			Action activeAction, 
			org.nasdanika.html.model.app.Page appPage,
			BiFunction<Action, URI, URI> uriResolver, 
			ProgressMonitor progressMonitor) {
		
		URI activeActionURI = uriResolver.apply(activeAction, null);
		
		if (root != null) {
			Label title = createLabel(root, uriResolver.apply(root, activeActionURI), root == activeAction, "header/title");
			EList<EObject> rootChildren = root.getChildren();
			if (title != null || rootChildren.size() > 1) {
				// Header
				Header header = appPage.getHeader();
				if (header == null) {
					header = AppFactory.eINSTANCE.createHeader();
					appPage.setHeader(header);
				}
				if (title != null) {
					header.setTitle(title);
				}
				EList<EObject> headerItems = header.getItems();
				rootChildren.listIterator(1).forEachRemaining(rac -> {
					if (rac instanceof Action) {
						Action raca = (Action) rac;
						headerItems.add(createLabel(raca, uriResolver.apply(raca, activeActionURI), raca == activeAction, "header/navigation"));
					} else {
						headerItems.add(rac);
					}
				});
				
			}
			
//			headerNav = root.getChildren().li.size() > 1 ? List.co;null;
			
			
			// Footer - navigation
			
		}
		
		if (principal != null) {
			
		}
		
		
	
	}

	private boolean isLink(Action action, URI uri) {
		return uri != null || !Util.isBlank(action.getScript()) || action.getModal() != null || !Util.isBlank(action.getName());
	}
	
	private void configureLabel(Action action, URI uri, boolean active, String appearancePath, Label label) {
		Appearance aa = action.getAppearance();
		if (aa != null) {
			label.setAppearance(aa.effectiveAppearance(appearancePath));
		}

		label.setActive(active);
		label.setColor(action.getColor());
		label.setDescription(action.getDescription());
		label.setDisabled(action.isDisabled());
//		label.setHelp(value); TODO - make links in help relative.
		label.setIcon(action.getIcon());
//		label.setId(action.getId());
//		label.setNotification(value);
//		label.setOutline(value);
		label.setText(action.getText());
//		label.setTooltip(value);
//		label.getAttributes();
//		label.getChildren();
		
		if (label instanceof Link) {
			configureLink(action, uri, (Link) label);
		}
		
	}
	
	private void configureLink(Action action, URI uri, Link link) {		
		if (uri != null) {
			link.setLocation(uri.toString());
		}
		link.setConfirmation(action.getConfirmation());
//		link.setModal(value); TODO - contextualization of links - relativization of URI's. Token expansion with a property computer? something like ${relative-uri/<uri here>}? 
//		Support of sub-tokens e.g. ${{relative-uri/${token}}} recognizes ${token} as a sub-token to be expanded to result in ${relative-uri/<token value>}. 
		link.setName(action.getName());
		link.setScript(action.getScript());
		link.setTarget(action.getTarget());
		
	}
	
	/**
	 * Creates a {@link Link} from {@link Action} with a relative location for locations.
	 * @param action
	 * @param uri
	 * @param active 
	 * @param appearancePath 
	 * @return
	 */
	private Label createLabel(Action action, URI uri, boolean active, String appearancePath) {
		if (Util.isBlank(action.getText()) && Util.isBlank(action.getIcon())) {
			return null;
		}
		Label label = isLink(action, uri) ? AppFactory.eINSTANCE.createLink() : AppFactory.eINSTANCE.createLabel();
		configureLabel(action, uri, active, appearancePath, label);
				
		return label;
	}
	
	protected void generate(
			Action root, 
			Action principal, 
			NavigationPanel navigationPanel,
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			BiFunction<Action, URI, URI> uriResolver, 
			URI baseURI, 
			Container container,
			ProgressMonitor progressMonitor) {
		
		if (navigationPanel != null) {
			for (EObject item: navigationPanel.getItems()) {
				if (item instanceof Action) {
					generate(root, principal, (Action) item, pageTemplate, uriResolver, baseURI, container, progressMonitor);
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
	
	/**
	 * Generates files from the previously generated resource model.
	 * @throws Exception
	 */
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
