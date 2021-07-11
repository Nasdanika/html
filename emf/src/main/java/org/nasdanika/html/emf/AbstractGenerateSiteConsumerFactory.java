package org.nasdanika.html.emf;

import static org.nasdanika.html.app.impl.Util.writeAction;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryRegistryImpl;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.Container;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.emf.persistence.YamlLoadingExecutionParticipant;
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

	private final class RootActionConsumer extends YamlLoadingExecutionParticipant implements Consumer<Action> {

		private final Context ctx;

		private RootActionConsumer(Context ctx) {
			this.ctx = ctx;
		}
		
		@Override
		protected MutableContext createContext(ProgressMonitor progressMonitor) {
			return AbstractGenerateSiteConsumerFactory.this.forkContext(ctx, progressMonitor);
		}

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Generating site";
		}

		@Override
		public void execute(Action rootAction, ProgressMonitor progressMonitor) throws Exception {
			Action principal;
			List<EObject> topLevelElements = roots.stream().filter(AbstractGenerateSiteConsumerFactory.this::isTopLevelElement).collect(Collectors.toList());
			if (topLevelElements != null && topLevelElements.size() == 1) {
				resourceSet.getAdapterFactories().add(createAdapterFactory(rootAction, context, diagnosticMap));
				principal = ViewAction.adaptToViewActionNonNull(topLevelElements.get(0));
			} else {
				principal = createPricipalAction(rootAction, topLevelElements);
				resourceSet.getAdapterFactories().add(createAdapterFactory(principal, context, diagnosticMap));
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

		@Override
		protected Collection<EPackage> getEPackages() {
			return AbstractGenerateSiteConsumerFactory.this.getEPackages();
		}

		@Override
		protected Collection<URI> getResources() {
			return resources;
		}
		
		protected boolean matchURI(EObject obj, URI uri) {
			return AbstractGenerateSiteConsumerFactory.this.matchURI(obj, uri);
		};
		
		@Override
		protected ObjectLoader createLoader(ResourceSet resourceSet) {
			return AbstractGenerateSiteConsumerFactory.this.createLoader(resourceSet);
		}
	}

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
		if (context.get(Context.BASE_URI_PROPERTY) == null) {
			String base = getBaseURI();
			if (!Util.isBlank(base)) {
				ret.put(Context.BASE_URI_PROPERTY, base);
			}
		}
		
		DiagramGenerator diagramGenerator = createDiagramGenerator(progressMonitor);
		if (diagramGenerator != null) {
			ret.register(DiagramGenerator.class, diagramGenerator);
		}
		return ret;
	}
		
	@Override
	public Consumer<Action> create(Context ctx) throws Exception {
		return new RootActionConsumer(ctx);
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
	protected abstract AdapterFactory createAdapterFactory(Action parent, Context context, Map<EObject, org.eclipse.emf.common.util.Diagnostic> diagnosticMap);

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

//	/**
//	 * Diagnoses the {@link ResourceSet}. This implementation finds unresolved proxies and reports them
//	 * with {@link Status} FAIL by calling unresolvedProxyDiagnostic().
//	 * @param resourceSet
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	protected BasicDiagnostic diagnose(ResourceSet resourceSet) {
//		BasicDiagnostic ret = new BasicDiagnostic(Status.SUCCESS, "Diagnostic of " + resourceSet, resourceSet);
//		
//		TreeIterator<Notifier> cit = resourceSet.getAllContents();
//		while (cit.hasNext()) {
//			Notifier next = cit.next();
//			if (next instanceof EObject) {
//				EObject nextEObject = (EObject) next;
//				if (nextEObject.eIsProxy()) {							
//					ret.add(unresolvedProxyDiagnostic(nextEObject, null, null));
//				} else {
//					for (EReference ref: nextEObject.eClass().getEAllReferences()) {
//						Object val = nextEObject.eGet(ref);
//						if (val instanceof EObject) {
//							if (((EObject) val).eIsProxy()) {
//								ret.add(unresolvedProxyDiagnostic((EObject) val, ref, nextEObject));
//							}
//						} else if (val instanceof Collection) {
//							for (EObject ve: (Collection<EObject>) val) {
//								if (ve.eIsProxy()) {
//									ret.add(unresolvedProxyDiagnostic((EObject) ve, ref, nextEObject));
//								}								
//							}
//						}
//					}
//				}
//			}	
//		}
//		return ret;
//	}
//	
//	/**
//	 * Reports unresolved proxies
//	 * @param source Unresolved proxy
//	 * @param containmentReference Reference containing the proxy. Can be null.
//	 * @param container Container of unresolved proxy. Can be null. 
//	 * @return
//	 */
//	protected Diagnostic unresolvedProxyDiagnostic(EObject source, EReference containmentReference, EObject container) {
//		Marked marked = EObjectAdaptable.adaptTo(source, Marked.class);
//		Marker marker = marked == null ? null : marked.getMarker();
//		if (marker == null) {
//			return new BasicDiagnostic(Status.FAIL, "Unresolved proxy: " + source, source, containmentReference, container);
//		}
//	
//		return new BasicDiagnostic(Status.FAIL, "Unresolved proxy at " + marker + ": " + source, source, marker, containmentReference, container);		
//	}
	
}
