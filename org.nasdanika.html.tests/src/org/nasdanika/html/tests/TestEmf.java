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
import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.emf.ComposedAdapterFactory;
import org.nasdanika.html.emf.EClassPropertySource;
import org.nasdanika.html.emf.ENamedElementLabel;
import org.nasdanika.html.emf.EObjectActionApplicationBuilderAdapterFactory;
import org.nasdanika.html.emf.EObjectSingleValuePropertySourceAdapter;
import org.nasdanika.html.emf.EObjectViewActionAdapter;
import org.nasdanika.html.emf.NavigationViewActionActivatorAdapter;
import org.nasdanika.html.emf.SupplierAdapterFactory;
import org.nasdanika.html.emf.ViewAction;
import org.nasdanika.html.emf.ViewActionActivator;


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
		caf.registerAdapterFactory(new SupplierAdapterFactory<Application>(Application.class, this.getClass().getClassLoader(), BootstrapContainerApplication::new));
//		caf.registerAdapterFactory(new BootstrapContainerApplicationAdapterFactory());
		caf.registerAdapterFactory(new EObjectActionApplicationBuilderAdapterFactory());
		caf.registerAdapterFactory(new SupplierAdapterFactory<ViewAction>(ViewAction.class, this.getClass().getClassLoader(), EObjectViewActionAdapter::new));
		caf.registerAdapterFactory(new SupplierAdapterFactory<SingleValuePropertySource>(SingleValuePropertySource.class, this.getClass().getClassLoader(), EObjectSingleValuePropertySourceAdapter::new));
		
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
		ENamedElementLabel<EAttribute> label = new ENamedElementLabel<EAttribute>(BankPackage.Literals.ACCOUNT__PERIOD_START);
		assertEquals("Period start", label.getText());
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
	public void testEObjectPropertySource() {
		System.out.println("--- EObject property sources ---");
		TreeIterator<EObject> tit = bank.eResource().getAllContents();
		while (tit.hasNext()) {
			EObject next = tit.next();
			SingleValuePropertySource ps = (SingleValuePropertySource) EcoreUtil.getRegisteredAdapter(next, SingleValuePropertySource.class);
			assertNotNull(ps);
			System.out.println("\t"+next.eClass().getName());
			for (PropertyDescriptor pd: ps.getPropertyDescriptors()) {
				System.out.println("\t\t"+pd.getText()+" = "+pd.getDisplayValue(((SingleValuePropertySource) ps).getValue()));
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

	
}
