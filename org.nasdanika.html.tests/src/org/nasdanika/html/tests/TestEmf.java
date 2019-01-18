package org.nasdanika.html.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
import org.nasdanika.bank.Customer;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.ApplicationFactory;
import org.nasdanika.html.app.Identity;
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
import org.nasdanika.html.emf.InstanceAdapterFactory;
import org.nasdanika.html.emf.NavigationPanelViewPart;
import org.nasdanika.html.emf.NavigationViewActionActivatorAdapter;
import org.nasdanika.html.emf.SupplierAdapterFactory;
import org.nasdanika.html.emf.ViewAction;
import org.nasdanika.html.emf.ViewActionActivator;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.tests.adapters.customer.BankViewAction;
import org.nasdanika.html.tests.adapters.customer.CustomerViewAction;


public class TestEmf extends HTMLTestBase {
	
	protected Bank bank;
	private ComposedAdapterFactory composedAdapterFactory;
	
	@Before
	public void loadModels() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		resourceSet.getPackageRegistry().put(BankPackage.eNS_URI, BankPackage.eINSTANCE);
		URI bankUri = URI.createPlatformPluginURI("org.nasdanika.bank/Nasdanika.bank", false);
		Resource bankResource = resourceSet.getResource(bankUri, true);
		bank = (Bank) bankResource.getContents().iterator().next();
		
		composedAdapterFactory = new ComposedAdapterFactory();
		class BootstrapContainerApplicationFactory implements ApplicationFactory {

			@Override
			public Application createApplication() {
				Application app =  new BootstrapContainerApplication();
				JsTreeFactory.INSTANCE.cdn(app.getHTMLPage());
				return app;
			}
			
		}
		composedAdapterFactory.registerAdapterFactory(new SupplierAdapterFactory<ApplicationFactory>(ApplicationFactory.class, this.getClass().getClassLoader(), BootstrapContainerApplicationFactory::new));
		composedAdapterFactory.registerAdapterFactory(new EObjectActionApplicationBuilderAdapterFactory());
		composedAdapterFactory.registerAdapterFactory(new FunctionAdapterFactory<ViewAction, EObject>(ViewAction.class, this.getClass().getClassLoader(), EObjectViewAction::new));
		
		// Identity
		Map<EObject, String> idMap = new HashMap<>();
		Function<EObject, Identity> identityManager = new Function<EObject, Identity>() {

			@Override
			public Identity apply(EObject eObject) {
				return () -> idMap.computeIfAbsent(eObject, eObj -> eObj.eClass().getName()+"-"+idMap.size()) ;
			}
			
		};
		composedAdapterFactory.registerAdapterFactory(new FunctionAdapterFactory<Identity, EObject>(Identity.class, this.getClass().getClassLoader(), identityManager));
		
		// View action activator
		class MapNavigationViewActionActivatorAdapter extends NavigationViewActionActivatorAdapter {

			@Override
			public String getUrl() {
				Identity identity = (Identity) EcoreUtil.getRegisteredAdapter((EObject) getTarget(), Identity.class);
				return identity == null ? null : identity.getId()+".html";
			}
			
		}
		composedAdapterFactory.registerAdapterFactory(new SupplierAdapterFactory<ViewActionActivator>(ViewActionActivator.class, this.getClass().getClassLoader(), MapNavigationViewActionActivatorAdapter::new));
		
		resourceSet.getAdapterFactories().add(composedAdapterFactory);						
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
			Application application = ((ApplicationFactory) EcoreUtil.getRegisteredAdapter(next, ApplicationFactory.class)).createApplication();
			assertNotNull(application);
			
			ApplicationBuilder applicationBuilder = (ApplicationBuilder) EcoreUtil.getRegisteredAdapter(next, ApplicationBuilder.class);
			assertNotNull(applicationBuilder);
			applicationBuilder.build(application);
			
			NavigationActionActivator activator = (NavigationActionActivator) EcoreUtil.getRegisteredAdapter(next, ViewActionActivator.class);
			writeFile("emf/bank/"+activator.getUrl(), application.toString());
		}
	}
	
	/**
	 * Generates a customer view of the bank.
	 * @throws Exception
	 */
	@Test
	public void testCustomerApplication() throws Exception {
		// Registering customer-view specific adapters.
		Function<EObject, Identity> identityManager = eObj -> () -> "index";
		composedAdapterFactory.registerAdapterFactory(new FunctionAdapterFactory<Identity, EObject>(Identity.class, this.getClass().getClassLoader(), identityManager), BankPackage.Literals.CUSTOMER);
		composedAdapterFactory.registerAdapterFactory(new FunctionAdapterFactory<ViewAction, Customer>(ViewAction.class, this.getClass().getClassLoader(), CustomerViewAction::new), BankPackage.Literals.CUSTOMER);
		
		// Bank view adapter factory is aware of the context customer
		Customer[] contextCustomer = {null};
		composedAdapterFactory.registerAdapterFactory(
				new FunctionAdapterFactory<ViewAction, Bank>(
						ViewAction.class, 
						this.getClass().getClassLoader(), 
						bank -> new BankViewAction(bank, contextCustomer[0])), 
				BankPackage.Literals.BANK);	
		
		// Bank Navigation panel view part adapter - rendering nothing.
		composedAdapterFactory.registerAdapterFactory(
				new InstanceAdapterFactory<NavigationPanelViewPart>(
						NavigationPanelViewPart.class, 
						this.getClass().getClassLoader(), 
						viewGenerator -> ""), 
				BankPackage.Literals.BANK);
		
		for (Customer customer: bank.getCustomers()) {
			contextCustomer[0] = customer;
			
			TreeIterator<EObject> tit = bank.eResource().getAllContents();
			while (tit.hasNext()) {
				EObject next = tit.next();
				Application application = ((ApplicationFactory) EcoreUtil.getRegisteredAdapter(next, ApplicationFactory.class)).createApplication();
				assertNotNull(application);
				
				ApplicationBuilder applicationBuilder = (ApplicationBuilder) EcoreUtil.getRegisteredAdapter(next, ApplicationBuilder.class);
				assertNotNull(applicationBuilder);
				applicationBuilder.build(application);
				
				NavigationActionActivator activator = (NavigationActionActivator) EcoreUtil.getRegisteredAdapter(next, ViewActionActivator.class);
				writeFile("emf/customer/"+customer.getName().toLowerCase().replace(' ', '-')+"/"+activator.getUrl(), application.toString());
			}
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
