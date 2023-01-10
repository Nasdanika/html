package org.nasdanika.html.model.app.gen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.emf.persistence.NcoreObjectLoaderSupplier;
import org.nasdanika.html.emf.ActionProviderAdapterFactory;
import org.nasdanika.html.emf.EObjectActionResolver;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.util.ActionProvider;

/**
 * Generates a web site from a semantic model.
 * @author Pavel
 *
 */
public class SemanticSiteGenerator extends SiteGenerator {
		
	/**
	 * Creates a resource set for loading the semantic model. 
	 * Override to customize, e.g. add a resource factory specific for the semantic model.
	 * @param progressMonitor
	 * @return
	 */
	protected ResourceSet createSemanticModelResourceSet(Context context, ProgressMonitor progressMonitor) {
		return createResourceSet(context, progressMonitor);
	}
		
	/**
	 * Loads a model using {@link NcoreObjectLoaderSupplier} which supports YAML, JSON, and. Optionally creates a copy and stores to XMI.
	 * @param uri Model URI
	 * @param copyURI Copy URI. If not null, the loaded model is copied and saved to this URI.
	 * @param context
	 * @param progressMonitor
	 * @return Loaded model
	 * @throws Exception
	 */
	protected Resource loadSemanticModel(
			URI uri,
			URI copyURI,
			Context context, 
			ProgressMonitor progressMonitor) throws IOException {		
		ResourceSet resourceSet = Util.createResourceSet(context, progressMonitor);		
		Resource resource = resourceSet.getResource(uri, true);

		if (copyURI == null) {
			return resource;
		}
		
		Resource copyResource = resourceSet.createResource(copyURI);
		copyResource.getContents().addAll(EcoreUtil.copyAll(resource.getContents()));
		copyResource.save(null);
		return copyResource;
	}
		
	/**
	 * Adapts the semantic model to an action model.
	 * @throws org.eclipse.emf.common.util.DiagnosticException 
	 * @throws IOException 
	 * @throws Exception
	 */
	
	protected BiSupplier<Resource, Map<EObject,Action>>  generateActionModel(
			Resource semanticModelResource,
			URI actionModelURI,
			Context context, 
			ProgressMonitor progressMonitor) throws org.eclipse.emf.common.util.DiagnosticException, IOException {
		ResourceSet semanticModelResourceSet = semanticModelResource.getResourceSet();
		org.eclipse.emf.common.util.Diagnostic instanceDiagnostic = org.nasdanika.emf.EmfUtil.resolveClearCacheAndDiagnose(semanticModelResourceSet, context);
		int severity = instanceDiagnostic.getSeverity();
		if (severity != org.eclipse.emf.common.util.Diagnostic.OK) {
			EmfUtil.dumpDiagnostic(instanceDiagnostic, 2, System.err);
			throw new org.eclipse.emf.common.util.DiagnosticException(instanceDiagnostic);
		}
		
		semanticModelResourceSet.getAdapterFactories().add(new ActionProviderAdapterFactory(context) {
			
			private void collect(Notifier target, org.eclipse.emf.common.util.Diagnostic source, Collection<org.eclipse.emf.common.util.Diagnostic> accumulator) {
				List<?> data = source.getData();
				if (source.getChildren().isEmpty()
						&& source.getSeverity() > org.eclipse.emf.common.util.Diagnostic.OK 
						&& data != null 
						&& data.size() == 1 
						&& data.get(0) == target) {
					accumulator.add(source);
				}
				for (org.eclipse.emf.common.util.Diagnostic child: source.getChildren()) {
					collect(target, child, accumulator);
				}
			}
			
			protected Collection<org.eclipse.emf.common.util.Diagnostic> getDiagnostic(Notifier target) {
				Collection<org.eclipse.emf.common.util.Diagnostic> ret = new ArrayList<>();
				collect(target, instanceDiagnostic, ret);
				return ret;
			}
			
			private void collect(Notifier target, EStructuralFeature feature, org.eclipse.emf.common.util.Diagnostic source, Collection<org.eclipse.emf.common.util.Diagnostic> accumulator) {
				List<?> data = source.getData();
				if (source.getChildren().isEmpty() 
						&& source.getSeverity() > org.eclipse.emf.common.util.Diagnostic.OK 
						&& data != null 
						&& data.size() > 1 
						&& data.get(0) == target 
						&& data.get(1) == feature) {
					accumulator.add(source);
				}
				for (org.eclipse.emf.common.util.Diagnostic child: source.getChildren()) {
					collect(target, feature, child, accumulator);
				}
			}

			protected Collection<org.eclipse.emf.common.util.Diagnostic> getFeatureDiagnostic(Notifier target, EStructuralFeature feature) {
				Collection<org.eclipse.emf.common.util.Diagnostic> ret = new ArrayList<>();
				collect(target, feature, instanceDiagnostic, ret);
				return ret;
			}
			
		});
		
		ResourceSet actionModelsResourceSet = createResourceSet(context, progressMonitor);
		
		org.eclipse.emf.ecore.resource.Resource actionModelResource = actionModelsResourceSet.createResource(actionModelURI);
		
		Map<EObject,Action> registry = new HashMap<>();
		for (EObject semanticElement: semanticModelResource.getContents()) {
			Action action = EObjectAdaptable.adaptTo(semanticElement, ActionProvider.class).execute(registry::put, progressMonitor);
			Context uriResolverContext = Context.singleton(Context.BASE_URI_PROPERTY, URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/"));
			BiFunction<Label, URI, URI> uriResolver = org.nasdanika.html.model.app.util.Util.uriResolver(action, uriResolverContext);
			Adapter resolver = EcoreUtil.getExistingAdapter(action, EObjectActionResolver.class);
			if (resolver instanceof EObjectActionResolver) {														
				org.nasdanika.html.emf.EObjectActionResolver.Context resolverContext = new org.nasdanika.html.emf.EObjectActionResolver.Context() {
	
					@Override
					public Action getAction(EObject semanticElement) {
						return registry.get(semanticElement);
					}
	
					@Override
					public URI resolve(Action action, URI base) {
						return uriResolver.apply(action, base);
					}
					
				};
				((EObjectActionResolver) resolver).execute(resolverContext, progressMonitor);
			}
			actionModelResource.getContents().add(action);
		}
		actionModelResource.save(null);
		
		return BiSupplier.of(actionModelResource, registry);
	}	
	
	public Map<String, Collection<String>> generate(
		URI semanticModelURI,
		URI rootActionURI,
		URI pageTemplateURI,
		String siteMapDomain,
		File outputDir,
		File workDir,
		boolean cleanWorkDir) throws IOException, org.eclipse.emf.common.util.DiagnosticException  {
		
		String modelName = semanticModelURI.lastSegment();
		if (org.nasdanika.common.Util.isBlank(modelName)) {
			modelName = "semantic-model";
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
			File modelsDir = new File(workDir, "models");
			File actionModelsDir = new File(workDir, "actions");
			File resourceModelsDir = new File(workDir, "resources");
			File siteWorkDir = new File(workDir, "site");			
			
			org.nasdanika.common.Util.delete(modelsDir);
			org.nasdanika.common.Util.delete(actionModelsDir);
			org.nasdanika.common.Util.delete(resourceModelsDir);
			org.nasdanika.common.Util.delete(siteWorkDir);
			
			modelsDir.mkdirs();
			actionModelsDir.mkdirs();
			resourceModelsDir.mkdirs();
			
			MutableContext context = Context.EMPTY_CONTEXT.fork();		
			context.put("model-name", modelName);
	
			try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {						
				URI modelsURI = URI.createFileURI(modelsDir.getAbsolutePath() + "/");							
				URI copyURI = URI.createURI(modelName + ".xml").resolve(modelsURI);		
				Resource semanticModelResource = loadSemanticModel(semanticModelURI, copyURI, context, progressMonitor.split("Loading semantic model", 1));
				
				URI actionModelsURI = URI.createFileURI(actionModelsDir.getAbsolutePath() + "/");	
				URI actionModelURI = URI.createURI(modelName + ".xml").resolve(actionModelsURI);
				BiSupplier<Resource, Map<EObject, Action>> rootActionAndRegistry = generateActionModel(semanticModelResource, actionModelURI, context, progressMonitor.split("Generating action model", 1));
				
				URI resourceModelsURI = URI.createFileURI(resourceModelsDir.getAbsolutePath() + "/");	
				URI resourceURI = URI.createURI(modelName + ".xml").resolve(resourceModelsURI);
				Resource resourceModel = generateResourceModel(
						rootActionAndRegistry.getFirst(), 
						rootActionAndRegistry.getSecond(), 
						rootActionURI,
						pageTemplateURI,
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
	

}
