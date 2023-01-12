package org.nasdanika.html.model.app.gen.tests;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;

public class TestActionSiteGenerator {
	
	@Test
	public void testActionSiteGenerator() throws IOException, DiagnosticException {
		ActionSiteGenerator actionSiteGenerator = new ActionSiteGenerator();
		
		URI rootActionURI = URI.createURI(getClass().getResource("app/actions.yml").toString());
		URI pageTemplateURI = URI.createURI(getClass().getResource("app/page-template.yml").toString());
		Map<String, Collection<String>> errors = actionSiteGenerator.generate(
				rootActionURI, 
				pageTemplateURI, 
				"https://nasdanika.org", 
				new File("target/action-site-generator"), 
				new File("target/action-site-generator-work-dir"), 
				false);
		
		System.out.println(errors);
	}

}
