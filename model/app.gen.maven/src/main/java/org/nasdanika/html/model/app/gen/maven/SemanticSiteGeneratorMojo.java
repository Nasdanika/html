package org.nasdanika.html.model.app.gen.maven;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.gen.SemanticSiteGenerator;

/**
 * Generates semantic site.
 */
@Mojo(name = "generate-semantic-site", defaultPhase = LifecyclePhase.SITE)
public class SemanticSiteGeneratorMojo extends AbstractSemanticGeneratorMojo {

	/**
	 * Directory to output generated site
	 */
	@Parameter(defaultValue = "target/semantic-site")	
	private File outputDirectory;

	/**
	 * Working directory for storing intermediate files
	 */
	@Parameter(defaultValue = "target/semantic-site-work-dir")	
	private File workDirectory;
	
	/**
	 * If true the working directory is cleaned before generation
	 */
	@Parameter	
	private boolean cleanWorkDir;	
		
	/**
	 * URI of the page template. Resolved relative to the project base directory
	 */
	@Parameter(required = true)	
	private String pageTemplate;
	
	/**
	 * Site map domain/URL to output to sitemap.xml. 
	 * Site map is not generated if this parameter is not set.
	 */
	@Parameter	
	private String siteMapDomain;
	
	/**
	 * URL of the Drawio viewer script for rendering Drawio diagrams.
	 * Set if you are hosting your own Drawio site.
	 */
	@Parameter	
	private String drawioViewer;	
	
	/**
	 * Number of known/expected errors. E.g. some blank pages or broken links. Build fails if the number of actual errors reported is different from this parameter. 
	 */
	@Parameter(required = false)	
	private int errors;
    	
	/**
	 * URI of the root action from which to generate a site. Resolved relative to the project base directory.
	 * The root action may reference actions generated from the semantic model using <code>action-resource</code> token.
	 */
	@Parameter	
	private String rootAction;
    
    
	@Override
	protected void execute(Context context, ProgressMonitor progressMonitor) {		
		SemanticSiteGenerator semanticSiteGenerator = createSemanticSiteGenerator(context, progressMonitor);
		
		File baseDir = project.getBasedir();
		URI baseDirURI = URI.createFileURI(baseDir.getAbsolutePath()).appendSegment("");
		
		URI semanticModelURI = URI.createURI(model).resolve(baseDirURI);
		URI pageTemplateURI = URI.createURI(pageTemplate).resolve(baseDirURI);
		URI rootActionURI = org.nasdanika.common.Util.isBlank(rootAction) ? null : URI.createURI(rootAction).resolve(baseDirURI);

		try {
			Map<String, Collection<String>> errors = semanticSiteGenerator.generate(
					semanticModelURI, 
					rootActionURI,
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
