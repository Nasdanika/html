package org.nasdanika.html.emf.legacy;

import java.io.File;
import java.net.URL;
import java.util.UUID;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.resources.Container;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.html.app.factories.ComposedLoader;

/**
 * Forks the context and injects base URI and diagram generator.
 * Use for contextification.
 * @author Pavel
 *
 */
public class Contextifier implements BiFunction<Context, ProgressMonitor, MutableContext> {
	
	@Override
	public MutableContext apply(Context context, ProgressMonitor progressMonitor) {
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
	
	private String baseURI = "random://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/";
	
	/**
	 * Base URI for relativizing references. This implementation returns random://<random UUID>/<random UUID>/.
	 * @return
	 */
	protected String getBaseURI() {
		return baseURI;
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

	public EObjectLoader createLoader(ResourceSet resourceSet) {
		return new EObjectLoader(new ComposedLoader(), null, resourceSet);
	}
	
}
