package org.nasdanika.html.emf;

import static org.nasdanika.html.app.impl.Util.writeAction;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.Container;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.factories.ComposedLoader;
import org.nasdanika.html.app.viewparts.AdaptiveNavigationPanelViewPart.Style;

/**
 * Abstract class for generating sites from models by adapting them to {@link ViewAction}.
 * @author Pavel
 *
 */
public abstract class AbstractGenerateSiteConsumerFactory implements ConsumerFactory<Action> {
	
	protected Collection<URI> resources;
	protected Container<String> output;
	protected SupplierFactory<? extends Application> applicationSupplierFactory;
	private String baseURI;

	protected AbstractGenerateSiteConsumerFactory(
			Collection<URI> resources,
			SupplierFactory<? extends Application> applicationSupplierFactory,
			Container<String> output) {
		this.resources = resources;
		this.applicationSupplierFactory = applicationSupplierFactory;
		this.output = output;
		baseURI = "random://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/";
	}

	protected AbstractGenerateSiteConsumerFactory(
			Collection<URI> resources, 
			SupplierFactory<? extends Application> applicationSupplierFactory,
			BinaryEntityContainer output) {
		this(resources, applicationSupplierFactory, output.stateAdapter().adapt(Util.INPUT_STREAM_TO_STRING_DECODER, Util.OBJECT_TO_INPUT_STREAM_ENCODER));
	}
	
	protected AbstractGenerateSiteConsumerFactory(
			Collection<URI> resources, 
			SupplierFactory<? extends Application> applicationSupplierFactory,
			File output) {
		this(resources, applicationSupplierFactory, new FileSystemContainer(output));
	}	

	protected AbstractGenerateSiteConsumerFactory(
			URI resource,
			SupplierFactory<? extends Application> applicationSupplierFactory,
			Container<String> output) {
		this(Collections.singleton(resource), applicationSupplierFactory, output);
	}

	protected AbstractGenerateSiteConsumerFactory(
			URI resource, 
			SupplierFactory<? extends Application> applicationSupplierFactory,
			BinaryEntityContainer output) {
		this(Collections.singleton(resource), applicationSupplierFactory, output);
	}
	
	protected AbstractGenerateSiteConsumerFactory(
			URI resource, 
			SupplierFactory<? extends Application> applicationSupplierFactory,
			File output) {
		this(Collections.singleton(resource), applicationSupplierFactory, output);

	}	
	
	/**
	 * Base URI for relativizing references. This implementation returns random://<random UUID>/<random UUID>/.
	 * @return
	 */
	protected String getBaseURI() {
		return baseURI;
	}
	
	/**
	 * Override to customize context. This implementation registers base URI if it is not blank
	 * @param context
	 * @return
	 */
	protected MutableContext forkContext(Context context, ProgressMonitor progressMonitor) {
		MutableContext ret = context.fork();
		String base = getBaseURI();
		if (!Util.isBlank(base)) {
			ret.put(Context.BASE_URI_PROPERTY, base);
		}
		
		DiagramGenerator diagramGenerator = createDiagramGenerator(progressMonitor);
		if (diagramGenerator != null) {
			ret.register(DiagramGenerator.class, diagramGenerator);
		}
		return ret;
	}
	
	
	@Override
	public Consumer<Action> create(Context ctx) throws Exception {
		
		
		return new Consumer<Action>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Generating site";
			}
			
			private MutableContext context;
			private List<EObject> topLevelElements;
			private ResourceSet resourceSet;
			
			/**
			 * Loads resources, checks for unresolved proxies and diagnoses.
			 */
			@SuppressWarnings("unchecked")
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				context = forkContext(ctx, progressMonitor);
				
				// Creating loader
				resourceSet = new ResourceSetImpl() {
					
					Map<URI, EObject> cache = new HashMap<>();
					
					@Override
					public EObject getEObject(URI uri, boolean loadOnDemand) {
						EObject ret = cache.get(uri);
						if (ret != null) {
							return ret;
						}
						
						TreeIterator<Notifier> cit = getAllContents();
						while (cit.hasNext()) {
							Notifier next = cit.next();
							if (next instanceof EObject) {
								EObject nextEObject = (EObject) next;
								if (matchURI(nextEObject, uri)) {
									cache.put(uri, nextEObject);
									ret = nextEObject;
								}
							}
						}

						if (ret != null) {
							return ret;
						}
						
						return super.getEObject(uri, loadOnDemand);
					}
					
				};	
				
				Registry packageRegistry = resourceSet.getPackageRegistry();
				for (EPackage ePackage: getEPackages()) {
					packageRegistry.put(ePackage.getNsURI(), ePackage);
				}
				ObjectLoader loader = createLoader(resourceSet);				
				resourceSet.setResourceFactoryRegistry(createResourceFactoryRegistry(loader, context, progressMonitor));
				
				// Pre-loading
				for (URI uri: resources) {
					resourceSet.getResource(uri, true);
				}
				
				EcoreUtil.resolveAll(resourceSet);
				
				Diagnostician diagnostician = new Diagnostician();
				BasicDiagnostic ret = (BasicDiagnostic) Consumer.super.diagnose(progressMonitor);
				
				TreeIterator<Notifier> cit = resourceSet.getAllContents();
				while (cit.hasNext()) {
					Notifier next = cit.next();
					if (next instanceof EObject) {
						EObject nextEObject = (EObject) next;
						if (nextEObject.eIsProxy()) {							
							ret.add(unresolvedProxyDiagnostic(nextEObject));
						} else {
							for (EReference ref: nextEObject.eClass().getEAllReferences()) {
								Object val = nextEObject.eGet(ref);
								if (val instanceof EObject) {
									if (((EObject) val).eIsProxy()) {
										ret.add(unresolvedProxyDiagnostic((EObject) val));
									}
								} else if (val instanceof Collection) {
									for (EObject ve: (Collection<EObject>) val) {
										if (ve.eIsProxy()) {
											ret.add(unresolvedProxyDiagnostic((EObject) ve));
										}								
									}
								}
							}
						}
					}	
				}
				
				if (ret.getStatus() != Status.FAIL) {				
					topLevelElements = new ArrayList<>();
					Map<Class<Context>, MutableContext> diagnosticContext = Collections.singletonMap(Context.class, context);
					for (URI uri: resources) {
						Resource engineeringResource = resourceSet.getResource(uri, true);
						for (EObject e: engineeringResource.getContents()) {
							ret.add(EmfUtil.wrap(diagnostician.validate(e, diagnosticContext)));
							
							if (isTopLevelElement(e)) {
								topLevelElements.add(e);
							}
						}
					}
				}
				
				return ret;
			}
			
			@Override
			public void execute(Action rootAction, ProgressMonitor progressMonitor) throws Exception {
				Action principal;
				if (topLevelElements.size() == 1) {
					resourceSet.getAdapterFactories().add(createAdapterFactory(rootAction, context));
					principal = ViewAction.adaptToViewActionNonNull(topLevelElements.get(0));
				} else {
					principal = createPricipalAction(rootAction, topLevelElements);
					resourceSet.getAdapterFactories().add(createAdapterFactory(principal, context));
				}
				rootAction.getChildren().add(principal);

				writeAction(
						rootAction, 
						principal, 
						rootAction, 
						context.getString(Context.BASE_URI_PROPERTY), 
						output, 
						context, 
						getNavigationPanelStyle(), 
						applicationSupplierFactory, 
						progressMonitor);
			}
			
		};
	}
		
	protected Diagnostic unresolvedProxyDiagnostic(EObject source) {
		Marked marked = EObjectAdaptable.adaptTo(source, Marked.class);
		Marker marker = marked == null ? null : marked.getMarker();
		if (marker == null) {
			return new BasicDiagnostic(Status.FAIL, "Unresolved proxy: " + source, source);
		}

		return new BasicDiagnostic(Status.FAIL, "Unresolved proxy at " + marker + ": " + source, source, marker);		
	}
	
	protected Style getNavigationPanelStyle() {
		return Style.AUTO;
	}
	
	protected DiagramGenerator createDiagramGenerator(ProgressMonitor progressMonitor) {
		URL diagramServerURL = getDiagramServerURL();
		DiagramGenerator ret = diagramServerURL == null ? DiagramGenerator.INSTANCE : DiagramGenerator.createClient(diagramServerURL);
		
		Container<String> cacheContainer = getDiagramCacheContainer();
		return cacheContainer == null ? ret : ret.cachingDiagramGenerator(cacheContainer, progressMonitor);
	}
	
	/**
	 * If this method returns non-null then a client diagram generator is created.
	 * @return
	 */
	protected URL getDiagramServerURL() {
		return null;
	}
	
	protected Container<String> getDiagramCacheContainer() {
		File cacheDir = getDiagramCacheDirectory();
		if (cacheDir == null) {
			return null;
		}
		
		FileSystemContainer cache = new FileSystemContainer(cacheDir);
		return cache.stateAdapter().adapt(Util.INPUT_STREAM_TO_STRING_DECODER, Util.OBJECT_TO_INPUT_STREAM_ENCODER);
	}
	
	/**
	 * If this method returns non-null then the returned directory is used to cache generated diagrams.
	 * @return
	 */
	protected File getDiagramCacheDirectory() {
		return null;
	}

	protected EObjectLoader createLoader(ResourceSet resourceSet) {
		return new EObjectLoader(new ComposedLoader(), null, resourceSet);
	}

	protected Resource.Factory.Registry createResourceFactoryRegistry(ObjectLoader loader, Context context, ProgressMonitor progressMonitor) {
		Resource.Factory.Registry resourceFactoryRegistry = new ResourceFactoryRegistryImpl();				
		resourceFactoryRegistry.getExtensionToFactoryMap().put("yml", new YamlResourceFactory(loader, context, progressMonitor));
		return resourceFactoryRegistry;
	}
	
	// --- Abstract methods to implement ---

	/**
	 * Creates an adapter factory for {@link ViewAction} adapters.
	 * @param rootAction
	 * @param context
	 * @return
	 */
	protected abstract AdapterFactory createAdapterFactory(Action parent, Context context);

	/**
	 * @param eObj element from {@link Resource}.getContents().
	 * @return true if the argument shall be adapted to {@link ViewAction} to be added to the action hierarchy. 
	 */
	protected abstract boolean isTopLevelElement(EObject eObj);
	
	/**
	 * Matches {@link URI} to {@link EObject}. One object may match more than one URI. E.g. a person may match their e-mail URI and
	 * an organization may match its web address URI.
	 * @param eObj Model element
	 * @param uri URI to match
	 * @return true if the URI identifies the object. 
	 */
	protected abstract boolean matchURI(EObject eObj, URI uri);
	
	/**
	 * @return {@link EPackage}s to register with the {@link ResourceSet}.
	 */
	protected abstract Collection<EPackage> getEPackages();

	/**
	 * If there is more than one top-level element this method is called to create a grouping principal action.
	 * If there is only one top level element then that element becomes a principal action.
	 * @param rootAction
	 * @param topLevelElements
	 * @return
	 */
	protected abstract Action createPricipalAction(Action rootAction, Collection<EObject> topLevelElements);
	
}
