package org.nasdanika.html.tests;

import org.junit.Test;
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
		dump("bootstrap/alert.html", "Bootstrap alert", BootstrapFactory.INSTANCE.alert(Color.INFO, "Alert"));
	}

}
