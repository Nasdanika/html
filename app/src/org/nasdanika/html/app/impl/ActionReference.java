package org.nasdanika.html.app.impl;

import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.ContextualFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Reference;
import org.nasdanika.html.app.Action;

/**
 * Not interpolated action reference resolved/loaded at load time.
 * TODO later - interpolated reference resolved/loaded at factory create time.
 * @author Pavel
 *
 */
public class ActionReference implements ContextualFactory<Action>, Marked {
	
	protected Object target;
	private Marker marker;
	// TODO - path & properties
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public ActionReference(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof String) {
			String configStr = (String) config;			
			URL targetURL = configStr.startsWith(Reference.CLASSPATH_URL_PREFIX) ? loader.getClass().getClassLoader().getResource(configStr.substring(Reference.CLASSPATH_URL_PREFIX.length())) : new URL(base, configStr);
			target = loader.loadYaml(targetURL, progressMonitor);
			this.marker = marker;
		} else {
			throw new ConfigurationException("Action reference type must be a string", marker);
		}
	}
	
	public ActionReference(Marker marker, Object target) {
		this.marker = marker;
		this.target = target;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Action create(Context context) throws Exception {
		if (target instanceof Action) {
			return (Action) target;
		}
		
		return ((ContextualFactory<Action>) target).create(context);
	}		

}
