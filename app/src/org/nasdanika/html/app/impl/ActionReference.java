package org.nasdanika.html.app.impl;

import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.exec.Reference;
import org.nasdanika.html.app.Action;

/**
 * Not interpolated action reference resolved/loaded at load time.
 * TODO later - interpolated reference resolved/loaded at factory create time.
 * @author Pavel
 *
 */
public class ActionReference implements SupplierFactory<Action>, Marked {
	
	protected SupplierFactory<Action> target;
	private Marker marker;
	// TODO - path & properties
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public ActionReference(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof String) {
			this.marker = marker;
			String configStr = (String) config;			
			URL targetURL = configStr.startsWith(Reference.CLASSPATH_URL_PREFIX) ? loader.getClass().getClassLoader().getResource(configStr.substring(Reference.CLASSPATH_URL_PREFIX.length())) : new URL(base, configStr);
			Object loaded = loader.loadYaml(targetURL, progressMonitor);
			target = org.nasdanika.common.Util.<Action>asSupplierFactory(loaded);
			if (target == null) {
				throw new ConfigurationException("Cannot adapt to SupplierFactory: " + loaded, marker);
			}
		} else {
			throw new ConfigurationException("Action reference type must be a string", marker);
		}
	}
	
	@Override
	public Supplier<Action> create(Context context) throws Exception {
		return target.create(context);
	}		

}
