package org.nasdanika.html.tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.junit.Test;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.impl.ActionApplicationBuilder;
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.app.impl.BootstrapContainerRouterApplication;
import org.nasdanika.html.bootstrap.Theme;
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
				
		Application app = new BootstrapContainerRouterApplication(Theme.Litera, true);

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
		
	}
	
}
