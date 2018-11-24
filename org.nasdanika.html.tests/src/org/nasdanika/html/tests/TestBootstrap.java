package org.nasdanika.html.tests;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;
import org.nasdanika.bank.Bank;
import org.nasdanika.bank.BankPackage;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;


public class TestBootstrap extends HTMLTestBase {
	
	//TODO - collect and output to file for checks and demos.
	
//	@Before
//	public void setUp() {
//		System.out.println("Before bootstrap tests in "+(new File(".")).getAbsolutePath());
//	}
	
	
//	@Before
//	public void loadModel() {
//		
//	}
	
	@Test
	public void testAlert() throws Exception {
		writeThemedPage("bootstrap/alert.html", "Bootstrap alert", BootstrapFactory.INSTANCE.alert(Color.INFO, "Alert"));
	}
	
	@Test
	public void testLoadBankModel() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(BankPackage.eNS_URI, BankPackage.eINSTANCE);
		URI uri = URI.createPlatformPluginURI("org.nasdanika.bank/Nasdanika.bank", false);
		Resource resource = resourceSet.getResource(uri, true);
		Bank bank = (Bank) resource.getContents().iterator().next();
		System.out.println(bank);
	}

}
