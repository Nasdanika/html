package org.nasdanika.html.model.app.gen.maven;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.json.JSONObject;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.DiagramGeneratorImpl;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;
import org.nasdanika.html.model.app.gen.SemanticSiteGenerator;
import org.nasdanika.maven.AbstractCommandMojo;
import org.nasdanika.ncore.ModelElement;

/**
 * Base class for Mojos performing different semantic generations - XMI, action, site.
 */
public abstract class AbstractSemanticGeneratorMojo extends AbstractCommandMojo {
	
	/**
	 * URI of the semantic model to load. Resolved relative to the project base directory
	 */
	@Parameter(required = true)	
	private String model;

	protected SemanticSiteGenerator createSemanticSiteGenerator(Context context, ProgressMonitor progressMonitor) {		
		return new SemanticSiteGenerator() {
			
			// Create site generator with collecting contributors
			
			
			// TODO - action provider adapter factories
			
			// TODO - resource set configuration - package registration, adapter factories, e.g. semantic map adapter factory
			
			// TODO - process semantic model - inlining/embedding
			
			// TODO - process action model - for completeness
			
			
		};		
		
	}
	
	// TODO - contributors and a getter to override to inject more in subclasses
	

}
