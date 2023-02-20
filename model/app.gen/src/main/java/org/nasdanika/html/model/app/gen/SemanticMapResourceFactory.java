package org.nasdanika.html.model.app.gen;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.json.JSONObject;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;

/**
 * Loads semantic map JSON or YAML as resources.
 * @author Pavel
 */
public class SemanticMapResourceFactory extends ResourceFactoryImpl {
	
	private class SemanticMapResource extends ResourceImpl {
		
		SemanticMapResource(URI uri) {
			super(uri);
		}
		
		/**
		 * URL decodes the opaque part and loads data from the decoded URL as YAML or JSON
		 */
		@SuppressWarnings("unchecked")
		@Override
		public void load(java.util.Map<?,?> options) throws IOException {
			String opaquePart = getURI().opaquePart();
			String uriStr = URLDecoder.decode(opaquePart, StandardCharsets.UTF_8.name());
			URI semanticMapURI = URI.createURI(uriStr);
			String lastSegment = semanticMapURI.lastSegment().toLowerCase();
			Map<String, Object> semanticMap;
			if (lastSegment.endsWith(".yml") || lastSegment.endsWith(".yaml")) {
				Object yamlObj = DefaultConverter.INSTANCE.loadYAML(semanticMapURI);
				if (yamlObj instanceof Map) {
					semanticMap = (Map<String, Object>) yamlObj;						
				} else {
					throw new IllegalArgumentException("Not a YAML map: " + semanticMapURI); 
				}
			} else {
				JSONObject jsonObject = DefaultConverter.INSTANCE.loadJSONObject(semanticMapURI);
				semanticMap = jsonObject.toMap();
			}
			
			Map<URI, Link> groupedByLocation = new HashMap<>();
			
			for (Entry<String, Object> se: semanticMap.entrySet()) {
				Map<String, Object> value = (Map<String, Object>) se.getValue();
				String location = (String) value.get("location");
				if (org.nasdanika.common.Util.isBlank(location)) {
					Label label = AppFactory.eINSTANCE.createLabel();
					label.setText((String) value.get("text"));
					label.setIcon((String) value.get("icon"));
					label.setTooltip((String) value.get("tooltip"));
					label.getUris().add(se.getKey());
					getContents().add(label);
				} else {
					URI locationURI = URI.createURI(location).resolve(semanticMapURI);
					Link link = groupedByLocation.get(locationURI);
					if (link == null) {
						link = AppFactory.eINSTANCE.createLink();
						link.setText((String) value.get("text"));
						link.setIcon((String) value.get("icon"));
						link.setTooltip((String) value.get("tooltip"));
						link.setLocation(locationURI.toString());
						getContents().add(link);
					}
					link.getUris().add(se.getKey());
				}
			}
		}
		
	}
	
	@Override
	public Resource createResource(URI uri) {
		return new SemanticMapResource(uri);
	}

}
