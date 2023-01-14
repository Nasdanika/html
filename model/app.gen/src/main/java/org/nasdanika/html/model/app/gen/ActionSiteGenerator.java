package org.nasdanika.html.model.app.gen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.ActionReference;
import org.nasdanika.html.model.app.Label;

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
			Map<EObject, Label> registry,
			URI rootActionURI,
			URI pageTemplateURI,
			URI resourceURI, 
			String containerName,
			File resourceWorkDir,
			Context context, 
			ProgressMonitor progressMonitor) throws IOException {
		
		Action root;
		if (rootActionURI == null) {
			Context rootActionContext = context.compose(Context.singleton("action-resource", actionResource.getURI().toString()));
			ResourceSet rootActionResourceSet = createResourceSet(rootActionContext, progressMonitor);
			root = (Action) rootActionResourceSet.getEObject(rootActionURI, true);
		} else {
			root = (Action) actionResource.getContents().get(0);
		}
		
		org.nasdanika.html.model.bootstrap.Page pageTemplate = (org.nasdanika.html.model.bootstrap.Page) actionResource.getResourceSet().getEObject(pageTemplateURI, true);
		
		return generateResourceModel(root, registry, pageTemplate, resourceURI, containerName, resourceWorkDir, context, progressMonitor);
	}	
	
	protected MutableContext createContext(ProgressMonitor progressMonitor) {
		return Context.EMPTY_CONTEXT.fork();
	}

	protected PrintStreamProgressMonitor createProgressMonitor() {
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
				MutableContext context = createContext(progressMonitor);		
				context.put("model-name", modelName);
				
				ResourceSet rootActionResourceSet = createResourceSet(context, progressMonitor);
				Action root = (Action) rootActionResourceSet.getEObject(rootActionURI, true);	
				
				Map<EObject,Label> registry = new HashMap<>();
				buildRegistry(root, registry);
				
				org.nasdanika.html.model.bootstrap.Page pageTemplate = (org.nasdanika.html.model.bootstrap.Page) rootActionResourceSet.getEObject(pageTemplateURI, true);
				
				URI resourceModelsURI = URI.createFileURI(resourceModelsDir.getAbsolutePath() + "/");	
				URI resourceURI = URI.createURI(modelName + ".xml").resolve(resourceModelsURI);
				Resource resourceModel = generateResourceModel(
						root, 
						registry, 
						pageTemplate,
						resourceURI, 
						modelName, 
						resourceModelsDir,
						context, 
						progressMonitor.split("Generating resource model", 1));
				
	
				// Cleanup docs, keep CNAME, favicon.ico, and images directory. Copy from target/model-doc/site/nasdanika
				Predicate<String> cleanPredicate = path -> {
					return !"CNAME".equals(path) && !"favicon.ico".equals(path) && !path.startsWith("images/");
				};
				
				return generateContainer(
						resourceModel,
						siteWorkDir,
						outputDir,
						cleanPredicate,
						siteMapDomain,
						modelName, 
						context, 
						progressMonitor.split("Generating container", 1));
			}
		} finally {
			if (cleanWorkDir) {
				org.nasdanika.common.Util.delete(workDir);
			}
		}
	}

	protected void buildRegistry(Action action, Map<EObject, Label> registry) {
		if (!registry.containsKey(action)) {
			registry.put(action, action);
			TreeIterator<EObject> ait = action.eAllContents();
			while (ait.hasNext()) {
				EObject next = ait.next();
				if (next instanceof Label) {
					registry.put(next, (Label) next);
				}
				if (next instanceof ActionReference) {
					Action refTarget = ((ActionReference) next).getTarget();
					registry.put(next, null);
					buildRegistry(refTarget, registry);
				}
			}
		}
	}
	
}
