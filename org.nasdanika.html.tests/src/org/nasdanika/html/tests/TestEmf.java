package org.nasdanika.html.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.PropertySource;
import org.nasdanika.html.emf.BootstrapContainerApplicationAdapterFactory;
import org.nasdanika.html.emf.ComposedAdapterFactory;
import org.nasdanika.html.emf.EClassPropertySource;
import org.nasdanika.html.emf.ENamedElementLabel;
import org.nasdanika.html.emf.EObjectActionAdapterFactory;
import org.nasdanika.html.emf.EObjectActionApplicationBuilderAdapterFactory;


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
		caf.registerAdapterFactory(new BootstrapContainerApplicationAdapterFactory());
		caf.registerAdapterFactory(new EObjectActionApplicationBuilderAdapterFactory());
		caf.registerAdapterFactory(new EObjectActionAdapterFactory());
		resourceSet.getAdapterFactories().add(caf);						
	}
	
	@Test
	public void testENamedElementLabel() {
		ENamedElementLabel<EAttribute> label = new ENamedElementLabel<EAttribute>(BankPackage.Literals.ACCOUNT__PERIOD_START);
		assertEquals("Period start", label.getText());
	}
	
	@Test
	public void testEClasPropertySource() {
		System.out.println("--- Property descriptors ---");
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
		int counter = 0;
		TreeIterator<EObject> tit = bank.eResource().getAllContents();
		while (tit.hasNext()) {
			EObject next = tit.next();
			Application application = (Application) EcoreUtil.getRegisteredAdapter(next, Application.class);
			assertNotNull(application);
			
			ApplicationBuilder applicationBuilder = (ApplicationBuilder) EcoreUtil.getRegisteredAdapter(next, ApplicationBuilder.class);
			assertNotNull(applicationBuilder);
			applicationBuilder.build(application);
			
			writeFile("emf/"+next.eClass().getName()+"-"+(counter++)+".html", application.toString());
		}
	}	

	
}
