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
import org.nasdanika.bank.Bank;
import org.nasdanika.bank.BankPackage;
import org.nasdanika.html.Select;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.Executable;
import org.nasdanika.html.app.Themed;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.ActionApplicationBuilder;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.app.impl.HTMLTableApplication;
import org.nasdanika.html.app.impl.JsTreeNavigationPanelActionApplicationBuilder;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.InputGroup;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.bootstrap.Theme;
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
	
	protected Bank bank;
	protected Action appAction;
	
	@Before
	public void loadModels() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		resourceSet.getPackageRegistry().put(BankPackage.eNS_URI, BankPackage.eINSTANCE);
		URI bankUri = URI.createPlatformPluginURI("org.nasdanika.bank/Nasdanika.bank", false);
		Resource bankResource = resourceSet.getResource(bankUri, true);
		bank = (Bank) bankResource.getContents().iterator().next();
		
		resourceSet.getPackageRegistry().put(AppPackage.eNS_URI, AppPackage.eINSTANCE);
		URI appUri = URI.createPlatformPluginURI("org.nasdanika.html.app.model/NasdanikaBank.app", false);
		Resource appResource = resourceSet.getResource(appUri, true);
						
//		// For testing
//		TreeIterator<EObject> atit = appResource.getAllContents();
//		while (atit.hasNext()) {
//			EObject next = atit.next();
//			if (next instanceof org.nasdanika.html.model.app.Action) {
//				org.nasdanika.html.model.app.Action ma = (org.nasdanika.html.model.app.Action) next;
////				if (ma.getActivator() == null && !"transfer".equals(ma.getId())) {
////					NavigationActionActivator activator = AppFactory.eINSTANCE.createNavigationActionActivator();
////					activator.setUrl((ma.getId() == null ? ma.hashCode() : ma.getId())+".html");
////					ma.setActivator(activator);
////				}
//				if (ma.getActivator() instanceof NavigationActionActivator) {
//					System.out.println(ma.getText() + " -> " + ((NavigationActionActivator) ma.getActivator()).getUrl());
//					for (EAttribute a: ma.getActivator().eClass().getEAllAttributes()) {
//						System.out.println("\t"+a.getName()+" = "+ma.getActivator().eGet(a));
//					}
//				} else if (ma.getActivator() instanceof ScriptActionActivator) {
//					System.out.println(ma.getText() + " => " + ((ScriptActionActivator) ma.getActivator()).getCode());					
//				}
//			}
//		}
//		
//		appResource.save(System.out, null);

		appAction = (Action) appResource.getContents().iterator().next();		
		
	}
	
	@Test
	public void testHTMLApp() throws Exception {
		try (Application app = new HTMLTableApplication()) {
			app.header("Header").navigationBar("Navigation bar").navigationPanel("Navigation panel").content("Content").footer("Footer");
			writeFile("app/html/index.html", app.toString());
		}
	}
	
	@Test
	public void testBootstrapApp() throws Exception {
		try (Application app = new BootstrapContainerApplication(Theme.Litera) {
			
			{
				container.border(Color.DANGER);
				header.border(Color.DANGER).background(Color.PRIMARY);
				navigationBar.border(Color.DANGER);
				navigationPanel.border(Color.DANGER).widthAuto();
				footer.border(Color.DANGER);
				content.border(Color.DANGER);
			}
			
		}) {
			Tag treeContainer = app.getHTMLPage().getFactory().div();
			app.header("Header").navigationBar("Navigation bar").navigationPanel(treeContainer).content("Content").footer("Footer");
			
			JsTreeFactory jsTreeFactory = JsTreeFactory.INSTANCE;
			jsTreeFactory.cdn(app.getHTMLPage());
			
			FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
					
			JsTreeNode rootNode = jsTreeFactory.jsTreeNode();
			rootNode.icon("far fa-user");
			rootNode.text("User");
			
			app.getHTMLPage().body(jsTreeFactory.bind(treeContainer, jsTreeFactory.buildAjaxJsTree("jstree.json", "context-menu.json")));		
			
			writeFile("app/bootstrap/index.html", app.toString());
			
			// JsTree
			JSONArray jsTreeNodes = new JSONArray();
			
			JsTreeNode childNode = jsTreeFactory.jsTreeNode();
			childNode.icon("far fa-user");
			childNode.text("Child");
			childNode.hasChildren();
			childNode.id(jsTreeFactory.getHTMLFactory().nextId());
			jsTreeNodes.put(childNode.toJSON());
			writeFile("app/bootstrap/jstree.json", jsTreeNodes.toString());
			
			// JsTree context menu
			JsTreeContextMenuItem item = jsTreeFactory.jsTreeContextMenuItem();
			item.label("Do it!");
			item.icon("far fa-user");
			item.action("window.location.href='http://www.nasdanika.org'; console.log('hey');");
			
			JSONObject menu = new JSONObject();
			menu.put("do-it", item.toJSON());
			writeFile("app/bootstrap/context-menu.json", menu.toString());
		}
	}
	
	@Test
	public void testActionApplication() throws Exception {
		// Delegating action activator resolution and execution to adapters.
		class ExecutableAdapter extends AdapterImpl implements Executable {
			
			@Override
			public Object execute(ViewGenerator viewGenerator, Map<String, Object> input) {
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
						Action principalAction = appAction.getChildren().get(0);
						if (principalAction instanceof Themed) {
							theme = ((Themed) principalAction).getTheme();
						} else if (appAction instanceof Themed) {
							theme = ((Themed) appAction).getTheme();
						} else {
							theme = Theme.Default;
						}
						
						try (Application app = new BootstrapContainerApplication(theme) {
							
							{
								header.background(Color.PRIMARY);
								navigationBar.background(Color.LIGHT).text().color(Color.DARK);
								
								footer.background(Color.SECONDARY).text().alignment(Alignment.CENTER);
								
								navigationPanel.widthAuto().border(Color.DEFAULT, Placement.RIGHT);
								contentRow.toHTMLElement().style("min-height", "500px");
								container.border(Color.DEFAULT).margin().top(1);

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
							
						}) {							
							for (Entry<String, ApplicationBuilder> be: createApplicationBuilders(principalAction, principalAction.getChildren(), (Action) next).entrySet()) {
								be.getValue().build(app);
								writeFile("app/action/"+be.getKey()+"/"+href, app.toString());
								
							}
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
		ApplicationBuilder appBuilder = new ActionApplicationBuilder(appAction, principalAction, principalAction.getChildren(), selected) {
			@Override
			protected Object generateHeader(ViewGenerator viewGenerator, Object result) {
				return ((Tag) super.generateHeader(viewGenerator, result)).addClass("text-dark", "text-decoration: none");
			}
		};			
		ret.put("link-group", appBuilder);
		
		ApplicationBuilder jsTreeAppBuilder = new JsTreeNavigationPanelActionApplicationBuilder(appAction, principalAction, principalAction.getChildren(), selected) {
			@Override
			protected Object generateHeader(ViewGenerator viewGenerator, Object result) {
				return ((Tag) super.generateHeader(viewGenerator, result)).addClass("text-dark", "text-decoration: none");
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
			public Object execute(ViewGenerator viewGenerator, Map<String, Object> input) {
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
		ViewGenerator viewGenerator = new ViewGeneratorImpl(null);
		resource.getContents().add(action);
		System.out.println(action.execute(viewGenerator));
		System.out.println(action.execute(viewGenerator));
		
		System.out.println(action.getChildren());
	}
	
}
