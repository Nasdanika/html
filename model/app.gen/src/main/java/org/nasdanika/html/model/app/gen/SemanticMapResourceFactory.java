package org.nasdanika.html.model.app.gen;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.ncore.ModelElement;

/**
 * Loads semantic map JSON or YAML as resources.
 * @author Pavel
 */
public class SemanticMapResourceFactory extends ResourceFactoryImpl {
	
	/**
	 * Override to pass semantic mapping to URI resolver
	 * @param semanticMap
	 */
	protected void onLoad(Map<ModelElement, Label> semanticMap, Resource resource) {
		
	}
	
	private class SemanticMapResource extends ResourceImpl {
		
		SemanticMapResource(URI uri) {
			super(uri);
		}
		
		/**
		 * URL decodes the opaque part and loads data from the decoded URL as YAML or JSON
		 */
		@Override
		public void load(java.util.Map<?,?> options) throws IOException {
			String opaquePart = getURI().opaquePart();
			String uriStr = URLDecoder.decode(opaquePart, StandardCharsets.UTF_8.name());
			URI semanticMapURI = URI.createURI(uriStr);
			Map<ModelElement, Label> semanticMap = Util.loadSemanticMap(semanticMapURI, resourceSet);
			onLoad(semanticMap, this);
			getContents().addAll(semanticMap.keySet());
		}
		
	}
	
	@Override
	public Resource createResource(URI uri) {
		return new SemanticMapResource(uri);
	}

}
