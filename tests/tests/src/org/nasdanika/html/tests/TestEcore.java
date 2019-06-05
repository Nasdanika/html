package org.nasdanika.html.tests;

import java.io.File;

import org.junit.Test;
import org.nasdanika.html.ecore.EcoreDocumentationGenerator;

public class TestEcore extends HTMLTestBase {
	
	/**
	 * Generates Ecore model documentation.
	 * @throws Exception
	 */
	@Test
	public void testEcoreDocumentation() {		
		EcoreDocumentationGenerator generator = new EcoreDocumentationGenerator("Nasdanika Bank Model", null);
		generator.loadGenModel("urn:org.nasdanika.bank");
		generator.generate(new File("target/test-dumps/ecore"));
		
	}
	
}
