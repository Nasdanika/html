package org.nasdanika.html.tests;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Action.Role;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.ApplicationException;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.ActionApplicationBuilder;
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.app.impl.ResourceConsumerStreamAdapter;
import org.nasdanika.html.app.viewparts.ContentPanelViewPart;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.ecore.EcoreDocumentationApplication;
import org.nasdanika.html.ecore.EcoreDocumentationViewGenerator;
import org.nasdanika.html.ecore.EcoreViewActionAdapterFactory;
import org.nasdanika.html.ecore.GenModelResourceSet;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.ViewAction;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeFactory;

public class TestEcore extends HTMLTestBase {
	
	/**
	 * Generates Ecore model documentation.
	 * @throws Exception
	 */
	@Test
	public void testEcoreDocumentation() throws Exception {
		
		ActionImpl rootAction = new ActionImpl();
		rootAction.setText("Model documentation");
		rootAction.setActivator(new NavigationActionActivator() {
			
			@Override
			public String getUrl() {
				return "#content/summary";
			}
		});
		
		ActionImpl principalAction = new ActionImpl();
		principalAction.setParent(rootAction);
		rootAction.getChildren().add(principalAction);
		
		GenModelResourceSet resourceSet = new GenModelResourceSet();
		
		EcoreViewActionAdapterFactory adapterFactory = new EcoreViewActionAdapterFactory(principalAction);
		
		resourceSet.getAdapterFactories().add(adapterFactory);
		
		resourceSet.loadGenModel("urn:org.nasdanika.bank");
				
		Application app = new EcoreDocumentationApplication(Theme.Litera, true);

		JsTreeFactory.INSTANCE.cdn(app.getHTMLPage());
		FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
		
		for (EPackage ePackage: resourceSet.getEPackages()) {			
			principalAction.getChildren().add(EObjectAdaptable.adaptTo(ePackage, ViewAction.class));			
		}
		
		ApplicationBuilder  applicationBuilder = new ActionApplicationBuilder(principalAction.getChildren().get(0)) {
			
			@Override
			protected ViewGenerator createViewGenerator(
					Consumer<?> headContentConsumer,
					Consumer<?> bodyContentConsumer, 
					BiFunction<String, Object, String> resourceConsumer) {
				
				return new EcoreDocumentationViewGenerator(headContentConsumer, bodyContentConsumer, resourceConsumer);
			}
			
		};		
		
		applicationBuilder.build(app);
		
		writeFile("ecore/index.html", app.toString());
		writeFile("ecore/doc/summary", "blah");
		
		for (Action action: principalAction.getChildren()) {
			writeContent(action);
		}
		
	}
	
	protected void writeContent(Action action) throws Exception {
		StringBuilder contentBuilder = new StringBuilder();
		// No head, adding head content to the "body".
		ResourceConsumerStreamAdapter resourceConsumer = new ResourceConsumerStreamAdapter((path, content) -> { 
			try {
				writeFile("ecore/doc/"+path, content); 
				return "doc/"+path;
			} catch (IOException e) {
				throw new ApplicationException(e);
			}
		});
		ViewPart contentPanelViewPart = new ContentPanelViewPart(action, false); // Use adapter?
		ViewGenerator viewGenerator = new EcoreDocumentationViewGenerator(contentBuilder::append, contentBuilder::append, resourceConsumer);
		contentBuilder.append(contentPanelViewPart.generate(viewGenerator));
		writeFile("ecore/doc/"+action.getId()+".html", contentBuilder.toString());		
		for (Action child: action.getChildren()) {
			if (child.isInRole(Role.NAVIGATION)) {
				writeContent(child);
			}
		}
	}
	
}
