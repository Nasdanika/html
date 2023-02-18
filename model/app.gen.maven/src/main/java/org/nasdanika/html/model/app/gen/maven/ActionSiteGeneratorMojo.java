package org.nasdanika.html.model.app.gen.maven;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.DiagramGeneratorImpl;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;
import org.nasdanika.maven.AbstractCommandMojo;

/**
 * Generates action site.
 */
@Mojo(name = "generate-action-site", defaultPhase = LifecyclePhase.SITE)
public class ActionSiteGeneratorMojo extends AbstractCommandMojo {

	/**
	 * Directory to output generated site
	 */
	@Parameter(defaultValue = "target/action-site")	
	private File outputDirectory;

	/**
	 * Working directory for storing intermediate files
	 */
	@Parameter(defaultValue = "target/action-site-work-dir")	
	private File workDirectory;
	
	/**
	 * If true the working directory is cleaned before generation
	 */
	@Parameter()	
	private boolean cleanWorkDir;	
	
	/**
	 * URI of the action from which to generate a site. Resolved relative to the project base directory
	 */
	@Parameter(required = true)	
	private String action;
		
	/**
	 * URI of the page template. Resolved relative to the project base directory
	 */
	@Parameter(required = true)	
	private String pageTemplate;
	
	/**
	 * Site map domain/URL to output to sitemap.xml. 
	 * Site map is not generated if this parameter is not set.
	 */
	@Parameter()	
	private String siteMapDomain;
	
	/**
	 * URL of the Drawio viewer script for rendering Drawio diagrams.
	 * Set if you are hosting your own Drawio site.
	 */
	@Parameter()	
	private String drawioViewer;	
	
	/**
	 * Number of known/expected errors. E.g. some blank pages or broken links. Build fails if the number of actual errors reported is different from this parameter. 
	 */
	@Parameter(required = false)	
	private int errors;
	
	/**
	 * Pluggable diagram generators.
	 */
    @Parameter()
    private List<DiagramGenerator> diagramGenerators;
	
	@Override
	protected void execute(Context context, ProgressMonitor progressMonitor) {
		
		ActionSiteGenerator actionSiteGenerator = new ActionSiteGenerator() {
			
			@Override
			protected Context createContext(ProgressMonitor progressMonitor) {
				DiagramGenerator diagramGenerator;
				if (Util.isBlank(drawioViewer)) {
					diagramGenerator = DiagramGenerator.INSTANCE;
				} else {
					diagramGenerator = new DiagramGeneratorImpl() {
						
						protected String getDrawioViewer() {
							return drawioViewer;
						};
						
					};
				}
				if (diagramGenerators != null) {
					for (DiagramGenerator dg: diagramGenerators) {
						diagramGenerator = dg.compose(diagramGenerator);
					}
				}
				return context.compose(Context.singleton(DiagramGenerator.class, diagramGenerator)).compose(super.createContext(progressMonitor));
			}
			
			@Override
			protected ProgressMonitor createProgressMonitor() {
				return progressMonitor;
			}
			
		};
		
		File baseDir = project.getBasedir();
		URI baseDirURI = URI.createFileURI(baseDir.getAbsolutePath()).appendSegment("");
		
		URI actionURI = URI.createURI(action).resolve(baseDirURI);
		URI pageTemplateURI = URI.createURI(pageTemplate).resolve(baseDirURI);

		try {
			Map<String, Collection<String>> errors = actionSiteGenerator.generate(
					actionURI, 
					pageTemplateURI, 
					siteMapDomain, 
					outputDirectory, 
					workDirectory, 
					cleanWorkDir);
			
			int errorCount = 0;			
			for (Entry<String, Collection<String>> ee: errors.entrySet()) {
				getLog().error(ee.getKey());
				for (String error: ee.getValue()) {
					++errorCount;
					getLog().error("\t" + error);
				}
			}
			if (errorCount != this.errors) {
				String message = "There are " + errorCount + " site errors";
				getLog().error(message);
				throw new NasdanikaException(message);
			}
		} catch (IOException | DiagnosticException ex) {
			throw new NasdanikaException(ex);
		}
	}

}
