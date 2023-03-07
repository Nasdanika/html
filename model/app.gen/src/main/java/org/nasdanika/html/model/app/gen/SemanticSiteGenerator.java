package org.nasdanika.html.model.app.gen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.ComposeableAdapterFactory;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.emf.persistence.NcoreObjectLoaderSupplier;
import org.nasdanika.html.emf.ActionProviderAdapterFactory;
import org.nasdanika.html.emf.EObjectActionResolver;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.util.ActionProvider;

/**
 * Generates site content from a semantic model.
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
		ResourceSet resourceSet = createResourceSet(context, progressMonitor);
		for (SiteGeneratorContributor contributor: getContributors()) {
			contributor.configureSemanticModelResourceSet(resourceSet, context, progressMonitor);
		}
		return resourceSet;
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
	public Resource loadSemanticModel(
			URI uri,
			URI copyURI,
			Context context, 
			ProgressMonitor progressMonitor) throws IOException {		
		ResourceSet resourceSet = createSemanticModelResourceSet(context, progressMonitor);		
		Resource resource = resourceSet.getResource(uri, true);

		for (SiteGeneratorContributor contributor: getContributors()) {
			contributor.processSemanticModel(resource, context, progressMonitor);
		}
		
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
	public Resource generateActionModel(
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
		
		for (org.eclipse.emf.common.notify.AdapterFactory adapterFactory: createActionProviderAdapterFactories(context, instanceDiagnostic, progressMonitor)) {
			semanticModelResourceSet.getAdapterFactories().add(adapterFactory);
		}
		
		ResourceSet actionModelsResourceSet = createActionModelResourceSet(context, progressMonitor);
		
		org.eclipse.emf.ecore.resource.Resource actionModelResource = actionModelsResourceSet.createResource(actionModelURI);
		
		Map<EObject,Label> registry = new HashMap<>();
		for (EObject semanticElement: semanticModelResource.getContents()) {
			Action action = EObjectAdaptable.adaptTo(semanticElement, ActionProvider.class).execute(registry::put, progressMonitor);
			Context uriResolverContext = Context.singleton(Context.BASE_URI_PROPERTY, URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/"));
			BiFunction<Label, URI, URI> uriResolver = org.nasdanika.html.model.app.util.Util.uriResolver(action, uriResolverContext);
			Adapter resolver = EcoreUtil.getExistingAdapter(action, EObjectActionResolver.class);
			if (resolver instanceof EObjectActionResolver) {														
				org.nasdanika.html.emf.EObjectActionResolver.Context resolverContext = new org.nasdanika.html.emf.EObjectActionResolver.Context() {
		
					@Override
					public URI resolve(Action action, URI base) {
						return uriResolver.apply(action, base);
					}

					@Override
					public Action getAction(Predicate<EObject> semanticElementPredicate) {
						for (Entry<EObject, Label> re: registry.entrySet()) {
							if (semanticElementPredicate.test(re.getKey())) {
								Label label = re.getValue();
								return label instanceof Action ? (Action) label : null;
							}						
						}
						return null;
					}
					
				};
				((EObjectActionResolver) resolver).execute(resolverContext, progressMonitor);
			}
			actionModelResource.getContents().add(action);
		}
		
		for (SiteGeneratorContributor contributor: getContributors()) {
			contributor.processActionModel(actionModelResource, context, progressMonitor);
		}
		
		actionModelResource.save(null);
		
		return actionModelResource;
	}

	protected List<org.eclipse.emf.common.notify.AdapterFactory> createActionProviderAdapterFactories(
			Context context, org.eclipse.emf.common.util.Diagnostic instanceDiagnostic, 
			ProgressMonitor progressMonitor) {
		
		List<org.eclipse.emf.common.notify.AdapterFactory> adapterFactories = new ArrayList<>();
		adapterFactories.add(new ActionProviderAdapterFactory(context, instanceDiagnostic));
		
		for (SiteGeneratorContributor contributor: getContributors()) {
			adapterFactories.addAll(contributor.createActionProviderAdapterFactories(context, instanceDiagnostic, progressMonitor));
		}
		
		boolean isComposed = false;
		ComposedAdapterFactory composedActionAdapterFactory = new ComposedAdapterFactory();
		
		Iterator<AdapterFactory> afit = adapterFactories.iterator();
		while (afit.hasNext()) {
			AdapterFactory af = afit.next();
			if (af instanceof ComposeableAdapterFactory) {
				composedActionAdapterFactory.registerAdapterFactory((ComposeableAdapterFactory) af);
				isComposed = true;
				afit.remove();
			}
		}
		
		if (isComposed) {
			adapterFactories.add(0, composedActionAdapterFactory);
		}

		return adapterFactories;
	}	
		
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
			root = (Action) actionResource.getContents().get(0);
		} else {
			Context rootActionContext = context.compose(Context.singleton("action-resource", actionResource.getURI().toString()));
			ResourceSet rootActionResourceSet = createActionModelResourceSet(rootActionContext, progressMonitor);
			root = (Action) rootActionResourceSet.getEObject(rootActionURI, true);
		}
		
		org.nasdanika.html.model.bootstrap.Page pageTemplate = (org.nasdanika.html.model.bootstrap.Page) actionResource.getResourceSet().getEObject(pageTemplateURI, true);
		
		return generateResourceModel(root, semanticInfoSource(actionResource), pageTemplate, resourceURI, containerName, resourceWorkDir, representationLinkResolutionErrorConsumer, context, progressMonitor);
	}		
	
	protected MutableContext createContext(ProgressMonitor progressMonitor) {
		return Context.EMPTY_CONTEXT.fork();
	}

	protected PrintStreamProgressMonitor createProgressMonitor() {
		return new PrintStreamProgressMonitor();
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
	
			try (ProgressMonitor progressMonitor = createProgressMonitor()) {				
				MutableContext context = createContext(progressMonitor);		
				context.put("model-name", modelName);
				
				URI modelsURI = URI.createFileURI(modelsDir.getAbsolutePath() + "/");							
				URI copyURI = URI.createURI(modelName + ".xml").resolve(modelsURI);		
				Resource semanticModelResource = loadSemanticModel(semanticModelURI, copyURI, context, progressMonitor.split("Loading semantic model", 1));
				
				URI actionModelsURI = URI.createFileURI(actionModelsDir.getAbsolutePath() + "/");	
				URI actionModelURI = URI.createURI(modelName + ".xml").resolve(actionModelsURI);
				Resource actionResource = generateActionModel(semanticModelResource, actionModelURI, context, progressMonitor.split("Generating action model", 1));
				
				URI resourceModelsURI = URI.createFileURI(resourceModelsDir.getAbsolutePath() + "/");	
				URI resourceURI = URI.createURI(modelName + ".xml").resolve(resourceModelsURI);
				
				Map<String, Collection<String>> errors = new TreeMap<>();

				Resource resourceModel = generateResourceModel(
						actionResource, 
						rootActionURI,
						pageTemplateURI,
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
	
}
