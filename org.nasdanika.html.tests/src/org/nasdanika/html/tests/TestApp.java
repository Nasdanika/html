package org.nasdanika.html.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Select;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.Executable;
import org.nasdanika.html.app.Themed;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.ActionApplicationBuilder;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.app.impl.HTMLTableApplication;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;
import org.nasdanika.html.app.viewparts.JsTreeNavigationPanelViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.InputGroup;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.bootstrap.Container.Row.Col;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.knockout.KnockoutFactory;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.NavigationActionActivator;


public class TestApp extends HTMLTestBase {
	
	protected Action appAction;
	
	@Before
	public void loadModels() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		resourceSet.getPackageRegistry().put(AppPackage.eNS_URI, AppPackage.eINSTANCE);
		URI appUri = URI.createPlatformPluginURI("org.nasdanika.html.app.model/NasdanikaBank.app", false);
		Resource appResource = resourceSet.getResource(appUri, true);

		appAction = (Action) appResource.getContents().iterator().next();		
		
	}
	
	@Test
	public void testHTMLApp() throws Exception {
		Application app = new HTMLTableApplication();
		app.header("Header").navigationBar("Navigation bar").navigationPanel("Navigation panel").contentPanel("Content").footer("Footer");
		writeFile("app/html/index.html", app.toString());
	}
	
	@Test
	public void testBootstrapApp() throws Exception {
		Application app = new BootstrapContainerApplication(Theme.Litera, false) {
			
			protected void configureContainer(Container container) {
				container.border(Color.DANGER);				
			}
			
			@Override
			protected void configureHeader(Col header) {
				header.border(Color.DANGER).background(Color.PRIMARY);
			}
			
			@Override
			protected void configureNavigationBar(Col navigationBar) {
				navigationBar.border(Color.DANGER);
			}
			
			protected void configureNavigationPanel(Col navigationPanel) {
				navigationPanel.border(Color.DANGER).widthAuto();				
			}
			
			protected void configureConentPanel(Col contentPanel) {
				contentPanel.border(Color.DANGER).toHTMLElement().style("min-height", "25em");				
			}
			
			protected void configureFooter(Col footer) {
				footer.border(Color.DANGER);		
			}
			
		};
		
		Tag treeContainer = app.getHTMLPage().getFactory().div();
		HTMLFactory htmlFactory = HTMLFactory.INSTANCE;
		app
			.header("Header")
			.navigationBar("Navigation bar")
			.navigationPanel(treeContainer)
			.contentPanel(/* htmlFactory.overlay("Content overlay"), */ "Content")
			.footer("Footer");
		
		JsTreeFactory jsTreeFactory = JsTreeFactory.INSTANCE;
		jsTreeFactory.cdn(app.getHTMLPage());
		
		FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
		
		app.getHTMLPage().body(jsTreeFactory.bind(treeContainer, jsTreeFactory.buildAjaxJsTree("node.id == '#' ? 'jstree.json' : 'jstree-' + node.id + '.json'", "'context-menu-' + node.id + '.json'")));		
		
		writeFile("app/bootstrap/index.html", app.toString());
		
		// JsTree
				
		JsTreeNode rootNode = jsTreeFactory.jsTreeNode();
		rootNode.icon("far fa-user");
		rootNode.text("User");
		rootNode.id(htmlFactory.nextId());
		rootNode.hasChildren();
		JSONArray jsTreeRootNodes = new JSONArray();
		jsTreeRootNodes.put(rootNode.toJSON());
		writeFile("app/bootstrap/jstree.json", jsTreeRootNodes.toString());		
		
		JSONArray jsTreeChildNodes = new JSONArray();
		
		JsTreeNode childNode = jsTreeFactory.jsTreeNode();
		childNode.icon("far fa-user");
		childNode.text("Child");
		childNode.id(jsTreeFactory.getHTMLFactory().nextId());
		jsTreeChildNodes.put(childNode.toJSON());
		writeFile("app/bootstrap/jstree-"+rootNode.getId()+".json", jsTreeChildNodes.toString());
		
		// JsTree context menu - the same menu for both nodes.
		JsTreeContextMenuItem item = jsTreeFactory.jsTreeContextMenuItem();
		item.label("Do it!");
		item.icon("far fa-user");
		item.action("window.location.href='http://www.nasdanika.org'; console.log('hey');");
		
		JSONObject menu = new JSONObject();
		menu.put("do-it", item.toJSON());
		writeFile("app/bootstrap/context-menu-"+rootNode.getId()+".json", menu.toString());
		writeFile("app/bootstrap/context-menu-"+childNode.getId()+".json", menu.toString());
	}
	
	@Test
	public void testActionApplication() throws Exception {
		// Delegating action activator resolution and execution to adapters.
		class ExecutableAdapter extends AdapterImpl implements Executable {
			
			@Override
			public Object execute(ViewGenerator viewGenerator) {
				return "Executing "+getTarget()+" "+this;
			}
			
			@Override
			public boolean isAdapterForType(Object type) {
				return Executable.class == type;
			}
			
		}
		
		AdapterFactory af = new AdapterFactoryImpl() {
			
			@Override
			public boolean isFactoryForType(Object type) {
				return Executable.class == type;
			}
			
			@Override
			protected Adapter createAdapter(Notifier target, Object type) {
				if (Executable.class == type) {
					return new ExecutableAdapter();
				}
				return null;
			}
		};
		
		appAction.eResource().getResourceSet().getAdapterFactories().add(af);	
		TreeIterator<EObject> tit = appAction.eResource().getAllContents();
		while (tit.hasNext()) {
			EObject next = tit.next();
			if (next instanceof Action) {
				ActionActivator actionActivator = ((Action) next).getActivator();
				if (actionActivator == null && !"transfer".equals(((Action) next).getId())) {
					actionActivator = (ActionActivator) EcoreUtil.getRegisteredAdapter(next, ActionActivator.class);
				}
				if (actionActivator instanceof NavigationActionActivator) {
					String href = ((NavigationActionActivator) actionActivator).getUrl();
					if (href != null) {
						
						Theme theme;
						Action principalAction = (Action) appAction.getNavigationChildren().get(0);
						if (principalAction instanceof Themed) {
							theme = ((Themed) principalAction).getTheme();
						} else if (appAction instanceof Themed) {
							theme = ((Themed) appAction).getTheme();
						} else {
							theme = Theme.Default;
						}
						
						for (Entry<String, ApplicationBuilder> be: createApplicationBuilders(principalAction, principalAction.getChildren(), (Action) next).entrySet()) {
							Application app = new BootstrapContainerApplication(theme, true) {
																								
								{	
									// Theme select at the bottom for experimentation.
									BootstrapFactory factory = BootstrapFactory.INSTANCE;
									Select select = factory.themeSelect(theme);
									InputGroup selectInputGroup = factory.inputGroup();
									selectInputGroup.prepend("Select Bootstrap theme");
									selectInputGroup.input(select);
									Container themeSelectorContainer = factory.container();
									themeSelectorContainer.row().col(selectInputGroup).margin().top(2);
									getHTMLPage().body(themeSelectorContainer);
													
									FontAwesomeFactory.INSTANCE.cdn(getHTMLPage());
									JsTreeFactory.INSTANCE.cdn(getHTMLPage());
									KnockoutFactory.INSTANCE.cdn(getHTMLPage());
									
								}
								
							};
							be.getValue().build(app);
							writeFile("app/action/"+be.getKey()+"/"+href, app.toString());
						}						
					}
				}
			}
		}	
		
		Map<String, Object> actionMap = appAction.toMap();
		JSONObject actionJson = new JSONObject(actionMap);
		writeFile("app/action/action.json", actionJson.toString(4));
	}	
	
	protected Map<String, ApplicationBuilder> createApplicationBuilders(Action principalAction, List<? extends Action> navigation, Action selected) {
		Map<String, ApplicationBuilder> ret = new HashMap<>();
		ApplicationBuilder appBuilder = new ActionApplicationBuilder(appAction, principalAction, principalAction.getNavigationChildren(), selected) {
			@Override
			protected Object generateHeader(ViewGenerator viewGenerator) {
				return ((Tag) super.generateHeader(viewGenerator)).addClass("text-dark").style().text().decoration().none();
			}
		};			
		ret.put("link-group", appBuilder);
		
		ActionApplicationBuilder jsTreeAppBuilder = new ActionApplicationBuilder(appAction, principalAction, principalAction.getNavigationChildren(), selected) {
			@Override
			protected Object generateHeader(ViewGenerator viewGenerator) {
				return ((Tag) super.generateHeader(viewGenerator)).addClass("text-dark").style().text().decoration().none();
			}
			
			@Override
			protected ViewPart getNavigationPanelViewPart() {
				return new JsTreeNavigationPanelViewPart(navigationPanelActions, activeAction);
			}
		};		
		ret.put("js-tree", jsTreeAppBuilder);
		
		return ret;
	}

	@Test
	public void testAppModel() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		class ExecutableAdapter extends AdapterImpl implements Executable {
						
			@Override
			public Object execute(ViewGenerator viewGenerator) {
				return "Executing "+getTarget()+" "+this;
			}
			
			@Override
			public boolean isAdapterForType(Object type) {
				return Executable.class == type;
			}
			
		}
		AdapterFactory af = new AdapterFactoryImpl() {
			
			@Override
			public boolean isFactoryForType(Object type) {
				return Executable.class == type;
			}
			
			@Override
			protected Adapter createAdapter(Notifier target, Object type) {
				if (Executable.class == type) {
					return new ExecutableAdapter();
				}
				return null;
			}
		};
		resourceSet.getAdapterFactories().add(af);
		Resource resource = resourceSet.createResource(URI.createURI("mem://test.xml")); // Some random URL.
		Action action = AppFactory.eINSTANCE.createAction();
		ViewGenerator viewGenerator = new ViewGeneratorImpl(null, null);
		resource.getContents().add(action);
		System.out.println(action.execute(viewGenerator));
		System.out.println(action.execute(viewGenerator));
		
		System.out.println(action.getChildren());
	}
	
}
