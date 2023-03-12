package org.nasdanika.html.model.app.gen.maven;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.DiagramGeneratorImpl;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Document;
import org.nasdanika.html.emf.RepresentationProcessor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.gen.SemanticSiteGenerator;
import org.nasdanika.html.model.app.gen.SiteGeneratorContributor;
import org.nasdanika.maven.AbstractCommandMojo;
import org.nasdanika.ncore.util.SemanticInfo;
import org.nasdanika.ncore.util.SemanticRegistry;

/**
 * Base class for Mojos performing different semantic generations - XMI, action, site.
 */
public abstract class AbstractSemanticGeneratorMojo extends AbstractCommandMojo {
		
	/**
	 * Pluggable diagram generators. 
	 */
    @Parameter
    private List<DiagramGenerator> diagramGenerators;
        	
	/**
	 * URL of the Drawio viewer script for rendering Drawio diagrams.
	 * Set if you are hosting your own Drawio site.
	 */
	@Parameter	
	private String drawioViewer;	    
	
	/**
	 * URI of the semantic model to load. Resolved relative to the project base directory
	 */
	@Parameter(required = true)	
	protected String model;
	
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
	
	protected List<SiteGeneratorContributor> getContributors() {
		return contributors == null ? Collections.emptyList() : contributors;
	}
    
	protected SemanticSiteGenerator createSemanticSiteGenerator(Context context, ProgressMonitor progressMonitor) {		
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
		
		return new SemanticSiteGenerator() {
			
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
				
				RepresentationProcessor representationProcessor = new RepresentationProcessor() {
					
					@Override
					public Document processDrawioRepresentation(
							Document document, 
							Action action,
							Function<URI, EObject> semanticLinkResolver,
							org.nasdanika.html.emf.EObjectActionResolver.Context context,
							ProgressMonitor progressMonitor) {
						
						for (SiteGeneratorContributor contributor: getContributors()) {
							document = contributor.processDrawioRepresentation(document, action, semanticLinkResolver, context, progressMonitor);
						}
						
						return document;
					}
					
				};
				
				return context
						.compose(Context.singleton(DiagramGenerator.class, diagramGenerator))
						.compose(Context.singleton(RepresentationProcessor.class, representationProcessor))
						.compose(super.createContext(progressMonitor));
			}
			
			@Override
			protected ProgressMonitor createProgressMonitor() {
				return progressMonitor;
			}
			
			@Override
			protected Collection<SiteGeneratorContributor> getContributors() {
				Collection<SiteGeneratorContributor> allContributors = new ArrayList<>(super.getContributors());
				allContributors.addAll(AbstractSemanticGeneratorMojo.this.getContributors());
				return allContributors;
			}
						
		};
	}

}
