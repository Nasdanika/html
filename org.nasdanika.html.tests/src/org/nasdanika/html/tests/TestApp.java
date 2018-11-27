package org.nasdanika.html.tests;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.nasdanika.bank.Bank;
import org.nasdanika.bank.BankPackage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.Executable;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;


public class TestApp extends HTMLTestBase {
	
	protected Bank bank;
	
	@Before
	public void loadModels() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(BankPackage.eNS_URI, BankPackage.eINSTANCE);
		URI uri = URI.createPlatformPluginURI("org.nasdanika.bank/Nasdanika.bank", false);
		Resource resource = resourceSet.getResource(uri, true);
		bank = (Bank) resource.getContents().iterator().next();
	}
	
	@Test
	public void testApp() throws Exception {
		try (Application app = new BootstrapContainerApplication(Theme.Litera) {
			
			{
				container.border(Color.DANGER);
				header.border(Color.DANGER).background(Color.PRIMARY);
				navigation.border(Color.DANGER);
				leftPanel.border(Color.DANGER).widthAuto();
				footer.border(Color.DANGER);
				content.border(Color.DANGER);
			}
			
		}) {
			Tag treeContainer = app.getHTMLPage().getFactory().div();
			app.header("header").navigation("navigation").leftPanel(treeContainer).content("content").footer("footer");
			
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
	public void testAppModel() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		class ExecutableAdapter extends AdapterImpl implements Executable {
			
			@Override
			public Object execute() {
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
		resource.getContents().add(action);
		System.out.println(action.execute());
		System.out.println(action.execute());
		
		System.out.println(action.getChildren());
	}
	
}
