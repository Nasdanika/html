package org.nasdanika.html.model.app.gen.maven;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

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
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;
import org.nasdanika.html.model.app.gen.SiteGeneratorContributor;
import org.nasdanika.maven.AbstractCommandMojo;
import org.nasdanika.ncore.util.SemanticInfo;
import org.nasdanika.ncore.util.SemanticRegistry;

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
	@Parameter	
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
	 * Pluggable diagram generators. 
	 */
    @Parameter
    private List<DiagramGenerator> diagramGenerators;
    
	/**
	 * Contributors to site generation.
	 */
    @Parameter
    private List<SiteGeneratorContributor> contributors;
        
    /**
     * URL's of JSON resources with information about external semantic elements. Such JSON resources are created as part of site generation. 
     * They are named semantic-info.json
     * Semantic infos are used to link model elements to externally defined element by URI. It is similar to how Java modules require other modules and then 
     * classes in that module may reference elements from the required modules by their fully qualified names.
     * 
     * Semantic info may be created programmatically using {@link SemanticInfo} and {@link SemanticRegistry} classes.
     */
    @Parameter
    private List<String> semanticInfos;
	
	@Override
	protected void execute(Context context, ProgressMonitor progressMonitor) {		
		ActionSiteGenerator actionSiteGenerator = createActionSiteGenerator(context, progressMonitor);
		
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

	protected ActionSiteGenerator createActionSiteGenerator(Context context, ProgressMonitor progressMonitor) {				
		File baseDir = project.getBasedir();
		URI baseDirURI = URI.createFileURI(baseDir.getAbsolutePath()).appendSegment("");		
		SemanticRegistry semanticRegistry = new SemanticRegistry();		
		if (semanticInfos != null) {
			for (String smLocation: semanticInfos) {			
				URI semanticInfoURI = URI.createURI(smLocation).resolve(baseDirURI);
				try {
					semanticRegistry.load(new URL(semanticInfoURI.toString()));
				} catch (IOException e) {
					String message = "Could not load semantic info from " + semanticInfoURI;
					getLog().error(message, e);
					throw new NasdanikaException(message, e);
				}
			}
		}
		
		return new ActionSiteGenerator() {
			
			@Override
			protected Iterable<SemanticInfo> getSemanticInfos() {
				return semanticRegistry
					.stream()
					.filter(SemanticInfo.class::isInstance)
					.map(SemanticInfo.class::cast)
					.collect(Collectors.toList());
			}
			
			@Override
			protected boolean isSemanticInfoLink(Link link) {
				if (link == null || Util.isBlank(link.getLocation())) {
					return false;
				}
				String linkLocation = link.getLocation();
				return semanticRegistry
					.stream()
					.filter(SemanticInfo.class::isInstance)
					.map(SemanticInfo.class::cast)
					.map(SemanticInfo::getLocation)
					.filter(Objects::nonNull)
					.map(Object::toString)
					.filter(linkLocation::equals)
					.findFirst()
					.isPresent();
			}			
			
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
			
			@Override
			protected Collection<SiteGeneratorContributor> getContributors() {
				Collection<SiteGeneratorContributor> allContributors = new ArrayList<>(super.getContributors());
				if (ActionSiteGeneratorMojo.this.contributors != null) {
					allContributors.addAll(ActionSiteGeneratorMojo.this.contributors);
				}
				return allContributors;
			}
						
		};
	}

}
