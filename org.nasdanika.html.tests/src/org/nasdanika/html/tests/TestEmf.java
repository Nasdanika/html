package org.nasdanika.html.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.nasdanika.bank.Bank;
import org.nasdanika.bank.BankPackage;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.PropertySource;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.emf.ComposedAdapterFactory;
import org.nasdanika.html.emf.EClassPropertySource;
import org.nasdanika.html.emf.ENamedElementLabel;
import org.nasdanika.html.emf.EObjectActionApplicationBuilderAdapterFactory;
import org.nasdanika.html.emf.EObjectViewAction;
import org.nasdanika.html.emf.EStructuralFeatureLabel;
import org.nasdanika.html.emf.FunctionAdapterFactory;
import org.nasdanika.html.emf.NavigationViewActionActivatorAdapter;
import org.nasdanika.html.emf.SupplierAdapterFactory;
import org.nasdanika.html.emf.ViewAction;
import org.nasdanika.html.emf.ViewActionActivator;
import org.nasdanika.html.jstree.JsTreeFactory;


public class TestEmf extends HTMLTestBase {
	
	protected Bank bank;
	
	@Before
	public void loadModels() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		resourceSet.getPackageRegistry().put(BankPackage.eNS_URI, BankPackage.eINSTANCE);
		URI bankUri = URI.createPlatformPluginURI("org.nasdanika.bank/Nasdanika.bank", false);
		Resource bankResource = resourceSet.getResource(bankUri, true);
		bank = (Bank) bankResource.getContents().iterator().next();
		
		ComposedAdapterFactory caf = new ComposedAdapterFactory();
		caf.registerAdapterFactory(new SupplierAdapterFactory<Application>(Application.class, this.getClass().getClassLoader(), () -> {
			Application app =  new BootstrapContainerApplication();
			JsTreeFactory.INSTANCE.cdn(app.getHTMLPage());
			return app;
		}));
		caf.registerAdapterFactory(new EObjectActionApplicationBuilderAdapterFactory());
		caf.registerAdapterFactory(new FunctionAdapterFactory<ViewAction, EObject>(ViewAction.class, this.getClass().getClassLoader(), EObjectViewAction::new));
		
		// View action activator
		Map<EObject, String> urlMap = new HashMap<>();
		class MapNavigationViewActionActivatorAdapter extends NavigationViewActionActivatorAdapter {

			@Override
			public String getUrl() {
				return urlMap.computeIfAbsent((EObject) getTarget(), eObj -> eObj.eClass().getName()+"-"+urlMap.size()+".html") ;
			}
			
		}
		caf.registerAdapterFactory(new SupplierAdapterFactory<ViewActionActivator>(ViewActionActivator.class, this.getClass().getClassLoader(), MapNavigationViewActionActivatorAdapter::new));
		
		resourceSet.getAdapterFactories().add(caf);						
	}
	
	@Test
	public void testENamedElementLabel() {
		ENamedElementLabel<EAttribute> label = new EStructuralFeatureLabel<EAttribute>(BankPackage.Literals.ACCOUNT__PERIOD_START);
		assertEquals("Period start", label.getText());
		System.out.println("Account period start label id: "+label.getId());
	}
	
	@Test
	public void testEClasPropertySource() {
		System.out.println("--- EClass property descriptors ---");
		for (EClassifier ec: BankPackage.eINSTANCE.getEClassifiers()) {			
			if (ec instanceof EClass) {
				System.out.println("\t"+ec.getName());
				PropertySource ps = new EClassPropertySource((EClass) ec, null);
				for (PropertyDescriptor pd: ps.getPropertyDescriptors()) {
					System.out.println("\t\t"+pd.getText());
				}				
			}
		}
		System.out.println("---");
	}
		
	@Test
	public void testBankApplication() throws Exception {
		TreeIterator<EObject> tit = bank.eResource().getAllContents();
		while (tit.hasNext()) {
			EObject next = tit.next();
			Application application = (Application) EcoreUtil.getRegisteredAdapter(next, Application.class);
			assertNotNull(application);
			
			ApplicationBuilder applicationBuilder = (ApplicationBuilder) EcoreUtil.getRegisteredAdapter(next, ApplicationBuilder.class);
			assertNotNull(applicationBuilder);
			applicationBuilder.build(application);
			
			NavigationActionActivator activator = (NavigationActionActivator) EcoreUtil.getRegisteredAdapter(next, ViewActionActivator.class);
			writeFile("emf/"+activator.getUrl(), application.toString());
		}
	}	

//	/**
//	 * Testing navigation children hierarchy to resolve stack overflow error.
//	 * @throws Exception
//	 */
//	@Test
//	public void testNavigationChildren() throws Exception {
//		TreeIterator<EObject> tit = bank.eResource().getAllContents();
//		while (tit.hasNext()) {
//			System.out.println("---");
//			EObject next = tit.next();			
//			Action action = (Action) EcoreUtil.getRegisteredAdapter(next, ViewAction.class);
//			navigationTree(action, 0);
//		}
//	}	
//	
//	private void navigationTree(Action action, int indent) {
//		for (int i = 0; i < indent; ++i) {
//			System.out.print("  ");
//		}
//		System.out.print(action.getText());
//		if (action.getActivator() instanceof NavigationActionActivator) {
//			System.out.print(" "+ ((NavigationActionActivator) action.getActivator()).getUrl());
//		}
//		System.out.println();
//		for (Action nc: action.getNavigationChildren()) {
//			navigationTree(nc, indent+1);
//		}		
//	}
	
}
