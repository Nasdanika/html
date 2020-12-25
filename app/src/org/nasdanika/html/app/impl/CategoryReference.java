package org.nasdanika.html.app.impl;

import java.net.URL;
import java.util.Collection;

import org.nasdanika.common.Context;
import org.nasdanika.common.ContextualFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.exec.Reference;
import org.nasdanika.html.app.Action;

/**
 * Not interpolated category reference resolved/loaded at load time.
 * TODO later - interpolated reference resolved/loaded at factory create time.
 * @author Pavel
 *
 */
public class CategoryReference implements ContextualFactory<Collection<Action>>, Marked {
	
	protected Object target;
	private Marker marker;
	// TODO - path & properties
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public CategoryReference(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof String) {
			String configStr = (String) config;			
			URL targetURL = configStr.startsWith(Reference.CLASSPATH_URL_PREFIX) ? loader.getClass().getClassLoader().getResource(configStr.substring(Reference.CLASSPATH_URL_PREFIX.length())) : new URL(base, configStr);
			target = loader.loadYaml(targetURL, progressMonitor);
			this.marker = marker;
		} else {
			throw new ConfigurationException("Category reference type must be a string", marker);
		}
	}
	
	public CategoryReference(Marker marker, Object target) {
		this.marker = marker;
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Action> create(Context context) throws Exception {
		if (target instanceof Collection) {
			return (Collection<Action>) target;
		}
		
		return ((ContextualFactory<Collection<Action>>) target).create(context);
	}		

}
