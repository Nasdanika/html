package org.nasdanika.html.model.app.gen.cli;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.cli.DelegatingCommand;
import org.nasdanika.cli.ModuleVersionProvider;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.model.app.gen.AppSiteGenerator;
import org.nasdanika.html.model.app.util.AppDrawioResourceFactory;
import org.nasdanika.html.model.app.util.LabelSupplier;
import org.springframework.util.AntPathMatcher;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Generates HTML site",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "site")
@ParentCommands(LabelSupplier.class)
public class SiteGeneratorCommand extends DelegatingCommand {
	
	@Parameters(
			index =  "0",	
			arity = "1",
			description = "Output directory relative to the base directory")
	private String output;
	
	@ParentCommand
	LabelSupplier actionSupplier;	
		
	@Option(
			names = {"-e", "--exclude"},
			arity = "*",
			description = {
					"Output directory clean excludes",
					"Ant pattern"
				})
	private String[] excludes;
	
	@Option(
			names = {"-i", "--include"},
			arity = "*",
			description = {
					"Output directory clean includes",
					"Ant pattern"
				})
	private String[] includes;
	
	@Option(names = {"-m", "--domain"}, description = "Sitemap domain")
	private String domian;
	
	@Option(names = {"-w", "--work-dir"}, description = "Working directory")
	private File workDir;
	
	@Option(names = {"-b", "--base-dir"}, description = "Base directory")
	private File baseDir;
		
	@Option(
			names = {"-l", "--clean"},
			negatable = true,
			description = {
					"Clean working directory",
					"defaults to ${DEFAULT-VALUE}"
				}, 
			defaultValue = "true")
	private boolean clean;
	
	@Option(
			names = {"-T", "--page-template"},
			description = {
					"Page template URI relative ",
					"to the current directory"
				})
	private String pageTemplate;
		
	@Option(
			names = {"-F", "--page-template-file"},
			description = {
					"Page template file relative ",
					"to the current directory"
				})
	private File pageTemplateFile;
	
	@Option(
			names = {"-r", "--errors"},	
			description = {
				"Expected number of page errors",
				"-1 for any (not fail on errors)",
				"default is 0"	
			})
	private int pageErrors;

	@Override
	protected SupplierFactory<Integer> getSupplierFactory() {
		return ctx -> new org.nasdanika.common.Supplier<Integer>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Site generator";
			}

			@Override
			public Integer execute(ProgressMonitor progressMonitor) {
				try {
					return generate(ctx, progressMonitor);
				} catch (IOException | DiagnosticException e) {
					throw new NasdanikaException(e);
				}				
			}
			
		};
	}
	
	protected int generate(Context context, ProgressMonitor progressMonitor) throws IOException, DiagnosticException {
		
		AppSiteGenerator actionSiteGenerator = new AppSiteGenerator() {
			
			@Override
			protected ResourceSet createResourceSet(Context context, ProgressMonitor progressMonitor) {
				ResourceSet resourceSet = super.createResourceSet(context, progressMonitor);
				resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("drawio", new AppDrawioResourceFactory(uri -> resourceSet.getEObject(uri, true)));
				// TODO - URI handlers from capability loader. E.g. Maven, GitLab, ...
				return resourceSet;
			}
			
			@Override
			protected boolean isDeleteOutputPath(String path) {
				if (includes != null) {
					boolean matched = false;
					for (String include: includes) {
						AntPathMatcher matcher = new AntPathMatcher();
						if (matcher.match(include, path)) {
							matched = true;
							break;
						}
					}
					if (!matched) {
						return false;
					}
				}
				
				if (excludes != null) {
					for (String exclude: excludes) {
						AntPathMatcher matcher = new AntPathMatcher();
						if (matcher.match(exclude, path)) {
							return false;
						}
					}					
				}
				
				return true;
			}	
			
			@Override
			protected ProgressMonitor createProgressMonitor() {
				return progressMonitor.split("Generating site", 1);
			}
			
		};
		
		if (baseDir == null) {
			baseDir = new File(".");
		}
		URI contextURI = URI.createFileURI(baseDir.getCanonicalPath()).appendSegment("");
		URI pageTemplateURI;
		if (!Util.isBlank(pageTemplate)) {
			pageTemplateURI = URI.createURI(pageTemplate).resolve(contextURI);			
		} else if (pageTemplateFile != null) {
			pageTemplateURI = URI.createFileURI(pageTemplateFile.getAbsolutePath());						
		} else {
			pageTemplateURI = Theme.Cerulean.pageTemplateCdnURI;			
		}
		if (!pageTemplateURI.hasFragment()) {
			pageTemplateURI = pageTemplateURI.appendFragment("/");
		}
				
		URI outputURI = URI.createFileURI(output).resolve(contextURI);
		File outputDir = new File(outputURI.toFileString());
		
		Map<String, Collection<String>> errors = actionSiteGenerator.generate(
				actionSupplier.getEObject(progressMonitor), 
				pageTemplateURI, 
				domian, 
				outputDir,  
				workDir == null ? Files.createTempDirectory("app-site-workdir-").toFile() : workDir, 
				clean);
				
		int errorCount = 0;
		for (Entry<String, Collection<String>> ee: errors.entrySet()) {
			progressMonitor.worked(Status.ERROR, 1, ee.getKey(), ee.getValue());
			errorCount += ee.getValue().size();
		}				
		progressMonitor.worked(Status.ERROR, 1, "There are " + errorCount + " page errors");
		return Math.abs(errors.size() - errorCount);
	}
	
}
