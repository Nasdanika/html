package org.nasdanika.html.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Action.Role;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.ApplicationException;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.ActionApplicationBuilder;
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.app.impl.ResourceConsumerStreamAdapter;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.ecore.EcoreDocumentationApplication;
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
		GenModelResourceSet resourceSet = new GenModelResourceSet();
		
		EcoreViewActionAdapterFactory adapterFactory = new EcoreViewActionAdapterFactory();
		
		resourceSet.getAdapterFactories().add(adapterFactory);
		
		resourceSet.loadGenModel("urn:org.nasdanika.bank");
				
		Application app = new EcoreDocumentationApplication(Theme.Litera, true);

		JsTreeFactory.INSTANCE.cdn(app.getHTMLPage());
		FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
		
		List<Action> ePackageViewActions = new ArrayList<>();
		for (EPackage ePackage: resourceSet.getEPackages()) {
			ePackageViewActions.add(EObjectAdaptable.adaptTo(ePackage, ViewAction.class));			
		}
		
		ActionImpl rootAction = new ActionImpl();
		rootAction.setText("Model documentation");
		
		ActionImpl principalAction = new ActionImpl();
		
		ApplicationBuilder  applicationBuilder = new ActionApplicationBuilder(rootAction, principalAction, ePackageViewActions, null);		
		
		applicationBuilder.build(app);
		
		writeFile("ecore/index.html", app.toString());
		
		for (Action action: ePackageViewActions) {
			writeContent(action);
		}
		
	}
	
	protected void writeContent(Action action) throws Exception {
		StringBuilder contentBuilder = new StringBuilder();
		// No head, adding head content to the "body".
		ResourceConsumerStreamAdapter resourceConsumer = new ResourceConsumerStreamAdapter((path, content) -> { 
			try {
				writeFile("ecore/doc/"+path, content); 
				return path;
			} catch (IOException e) {
				throw new ApplicationException(e);
			}
		});
		ViewGenerator viewGenerator = new ViewGeneratorImpl(contentBuilder::append, contentBuilder::append, resourceConsumer);
		contentBuilder.append(action.generate(viewGenerator));
		writeFile("ecore/doc/"+action.getId()+".html", contentBuilder.toString());		
		for (Action child: action.getChildren()) {
			if (child.isInRole(Role.NAVIGATION)) {
				writeContent(child);
			}
		}
	}
	
}
