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

/**
 * Not interpolated category reference resolved/loaded at load time.
 * TODO later - interpolated reference resolved/loaded at factory create time.
 * @author Pavel
 *
 */
public class CategoryReference implements SupplierFactory<Category>, Marked {
	
	protected SupplierFactory<Category> target;
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public CategoryReference(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof String) {
			this.marker = marker;
			String configStr = (String) config;			
			URL targetURL = configStr.startsWith(Reference.CLASSPATH_URL_PREFIX) ? loader.getClass().getClassLoader().getResource(configStr.substring(Reference.CLASSPATH_URL_PREFIX.length())) : new URL(base, configStr);
			Object loaded = loader.loadYaml(targetURL, progressMonitor);
			target = org.nasdanika.common.Util.<Category>asSupplierFactory(loaded);
			if (target == null) {
				throw new ConfigurationException("Cannot adapt to SupplierFactory: " + loaded, marker);
			}
		} else {
			throw new ConfigurationException("Category reference type must be a string", marker);
		}
	}
	
	@Override
	public Supplier<Category> create(Context context) throws Exception {
		return target.create(context);
	}		

}
