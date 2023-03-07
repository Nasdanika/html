package org.nasdanika.html.model.app.gen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.ncore.util.SemanticInfo;

/**
 * Generates a web site from an action model as a semantic model.
 * @author Pavel
 *
 */
public class ActionSiteGenerator extends SiteGenerator {
	
	/**
	 * Generates a resource model from an action model.
	 * @throws IOException 
	 * @throws Exception
	 */
	protected Resource generateResourceModel(
			Resource actionResource, 
			URI rootActionURI,
			URI pageTemplateURI,
			URI resourceURI, 
			String containerName,
			File resourceWorkDir,
			BiConsumer<String, String> representationLinkResolutionErrorConsumer,						
			Context context, 
			ProgressMonitor progressMonitor) throws IOException {
		
		Action root;
		if (rootActionURI == null) {
			Context rootActionContext = context.compose(Context.singleton("action-resource", actionResource.getURI().toString()));
			ResourceSet rootActionResourceSet = createActionModelResourceSet(rootActionContext, progressMonitor);
			root = (Action) rootActionResourceSet.getEObject(rootActionURI, true);
		} else {
			root = (Action) actionResource.getContents().get(0);
		}
		
		org.nasdanika.html.model.bootstrap.Page pageTemplate = (org.nasdanika.html.model.bootstrap.Page) actionResource.getResourceSet().getEObject(pageTemplateURI, true);
		
		return generateResourceModel(root, semanticInfoSource(actionResource.getResourceSet()), pageTemplate, resourceURI, containerName, resourceWorkDir, representationLinkResolutionErrorConsumer, context, progressMonitor);
	}	
	
	protected Context createContext(ProgressMonitor progressMonitor) {
		return Context.EMPTY_CONTEXT;
	}

	protected ProgressMonitor createProgressMonitor() {
		return new PrintStreamProgressMonitor();
	}	
			
	public Map<String, Collection<String>> generate(
		URI rootActionURI,
		URI pageTemplateURI,
		String siteMapDomain,
		File outputDir,
		File workDir,
		boolean cleanWorkDir) throws IOException, org.eclipse.emf.common.util.DiagnosticException  {
		
		String modelName = rootActionURI.lastSegment();
		if (org.nasdanika.common.Util.isBlank(modelName)) {
			modelName = "action-model";
		}
		if (workDir == null) {
			workDir = Files.createTempDirectory(modelName).toFile();
			cleanWorkDir = true;
		}
		
		if (!workDir.isDirectory()) {
			if (!workDir.mkdirs()) {
				throw new IOException("Cannot create a working directory: " + workDir.getAbsolutePath());
			}
		}
		
		try {
			File resourceModelsDir = new File(workDir, "resources");
			File siteWorkDir = new File(workDir, "site");			
			
			org.nasdanika.common.Util.delete(resourceModelsDir);
			org.nasdanika.common.Util.delete(siteWorkDir);
			
			resourceModelsDir.mkdirs();
	
			try (ProgressMonitor progressMonitor = createProgressMonitor()) {				
				Context context = Context.singleton("model-name", modelName).compose(createContext(progressMonitor));		
				
				ResourceSet rootActionResourceSet = createActionModelResourceSet(context, progressMonitor);
				Action root = (Action) rootActionResourceSet.getEObject(rootActionURI, true);	
				
				org.nasdanika.html.model.bootstrap.Page pageTemplate = (org.nasdanika.html.model.bootstrap.Page) rootActionResourceSet.getEObject(pageTemplateURI, true);
				
				URI resourceModelsURI = URI.createFileURI(resourceModelsDir.getAbsolutePath() + "/");	
				URI resourceURI = URI.createURI(modelName + ".xml").resolve(resourceModelsURI);
				
				Map<String, Collection<String>> errors = new TreeMap<>();
				
				Resource resourceModel = generateResourceModel(
						root, 
						semanticInfoSource(rootActionResourceSet), 
						pageTemplate,
						resourceURI, 
						modelName, 
						resourceModelsDir,
						(location, error) ->  {
							errors.computeIfAbsent(location, p -> new ArrayList<>()).add(error);
						},
						context, 
						progressMonitor.split("Generating resource model", 1));
				
				Map<String, Collection<String>> containerErrors = generateContainer(
						resourceModel,
						siteWorkDir,
						outputDir,
						this::isDeleteOutputPath,
						siteMapDomain,
						modelName, 
						context, 
						progressMonitor.split("Generating container", 1));
				
				containerErrors.forEach((path, pathErrors) -> errors.computeIfAbsent(path, p -> new ArrayList<>()).addAll(pathErrors));
				return errors;
			}
		} finally {
			if (cleanWorkDir) {
				org.nasdanika.common.Util.delete(workDir);
			}
		}
	}
	
	/**
	 * Clean predicate for the output directory. This implementation returns true. 
	 * Override to return false for paths which shall not be cleaned (deleted).
	 * @param path File/directory path in the output directory.
	 * @return
	 */
	protected boolean isDeleteOutputPath(String path) {
		return true;
	}	
	
	/**
	 * Action is its own semantic element.
	 */
	@Override
	protected SemanticInfo getSemanticInfoAnnotation(Action action) {
		SemanticInfo semanticInfo = super.getSemanticInfoAnnotation(action);
		SemanticInfo.getAnnotation(action);
		if (semanticInfo == null) {
			semanticInfo = new SemanticInfo(action);
		}
		return semanticInfo;
	}
	
}
