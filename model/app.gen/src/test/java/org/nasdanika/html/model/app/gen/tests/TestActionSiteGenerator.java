package org.nasdanika.html.model.app.gen.tests;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;
import org.nasdanika.html.model.app.gen.SemanticMapResourceFactory;
import org.nasdanika.ncore.ModelElement;

public class TestActionSiteGenerator {
	
	@Test
	public void testActionSiteGenerator() throws IOException, DiagnosticException {
		URI semanticMapURI = URI.createURI("https://docs.nasdanika.org/demo-action-site/semantic-map.json");				
		
		ActionSiteGenerator actionSiteGenerator = new ActionSiteGenerator() {
			
//			Map<ModelElement, Label> semanticMap = new LinkedHashMap<>();			
			
			@Override
			protected ResourceSet createResourceSet(Context context, ProgressMonitor progressMonitor) {
				ResourceSet resourceSet = super.createResourceSet(context, progressMonitor);
				SemanticMapResourceFactory smrf = new SemanticMapResourceFactory() {
					@Override
					protected void onLoad(Map<ModelElement, Label> resourceSemanticMap, Resource resource) {
						super.onLoad(resourceSemanticMap, resource);
						semanticMap.putAll(resourceSemanticMap);
					}
				};
				resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("semantic-map", smrf);				
				try {
					URI sMapURI = URI.createURI("semantic-map:" + URLEncoder.encode(semanticMapURI.toString(), StandardCharsets.UTF_8.name()));
					resourceSet.getResource(sMapURI, true);
				} catch (UnsupportedEncodingException e) {
					throw new NasdanikaException(e);
				}
				
				return resourceSet;
			}			
			
//			@Override
//			protected void buildRegistry(Action action, Map<EObject, Label> registry) {
//				registry.putAll(semanticMap);
//				super.buildRegistry(action, registry);
//			}
//			
//			@Override
//			protected boolean isSemanticInfoLink(Link link) {
//				return semanticMap.values().contains(link);
//			}
			
		};
		
		URI rootActionURI = URI.createURI(getClass().getResource("app/actions.yml").toString());
		URI pageTemplateURI = URI.createURI(getClass().getResource("app/page-template.yml").toString());
		Map<String, Collection<String>> errors = actionSiteGenerator.generate(
				rootActionURI, 
				pageTemplateURI, 
				"https://nasdanika.org", 
				new File("target/action-site-generator"), 
				new File("target/action-site-generator-work-dir"), 
				false);
				
		int errorCount = 0;
		for (Entry<String, Collection<String>> ee: errors.entrySet()) {
			System.err.println(ee.getKey());
			for (String error: ee.getValue()) {
				System.err.println("\t" + error);
				++errorCount;
			}
		}
		
		System.out.println("There are " + errorCount + " site errors");
		
//		if (!errors.isEmpty()) {
//			fail("There are problems with pages: " + errorCount);
//		}		
	}

}
