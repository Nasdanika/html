package org.nasdanika.html.app.factories;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.exec.Reference;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.impl.Category;
import org.nasdanika.html.app.impl.PathNavigationActionActivator;

/**
 * Not interpolated category reference resolved/loaded at load time.
 * TODO later - interpolated reference resolved/loaded at factory create time.
 * @author Pavel
 *
 */
public class CategoryReference implements SupplierFactory<Category>, Marked {
	
	protected SupplierFactory<Category> target;
	private Marker marker;
	
	private static final String TARGET_KEY = "target";
	private static final String PATH_KEY = "path";
	
	private String path; 
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public CategoryReference(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		if (config instanceof String) {
			setTarget(loader, base, progressMonitor, marker, (String) config);
		} else if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			org.nasdanika.common.Util.checkUnsupportedKeys(configMap, TARGET_KEY, PATH_KEY);
			setTarget(loader, base, progressMonitor, marker, org.nasdanika.common.Util.getString(configMap, TARGET_KEY, null));
			path =  org.nasdanika.common.Util.getString(configMap, PATH_KEY, null);
		} else {
			throw new ConfigurationException("Category reference type must be a string or a map, got " + config, marker);
		}
	}

	private void setTarget(ObjectLoader loader, URL base, ProgressMonitor progressMonitor, Marker marker, String configStr) throws MalformedURLException, Exception {
		URL targetURL = configStr.startsWith(Reference.CLASSPATH_URL_PREFIX) ? loader.getClass().getClassLoader().getResource(configStr.substring(Reference.CLASSPATH_URL_PREFIX.length())) : new URL(base, configStr);
		Object loaded = loader.loadYaml(targetURL, progressMonitor);
		target = org.nasdanika.common.Util.<Category>asSupplierFactory(loaded);
		if (target == null) {
			throw new ConfigurationException("Cannot adapt to SupplierFactory: " + loaded, marker);
		}
	}
	
	@Override
	public Supplier<Category> create(Context context) throws Exception {
		String iPath = context.interpolateToString(path);
		Supplier<Category> categorySupplier = target.create(context);
		if (Util.isBlank(iPath)) {
			return categorySupplier;
		}		
		Function<Category, Category> setPath = new Function<Category, Category>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Action reference path";
			}

			@Override
			public Category execute(Category category, ProgressMonitor progressMonitor) throws Exception {
				for (Action action: category.getActions()) {
					if (action.getActivator() instanceof PathNavigationActionActivator) {
						((PathNavigationActionActivator) action.getActivator()).getPath().addFirst(iPath);
					}
				}
				
				return category;
			}
		};
		return categorySupplier.then(setPath);
	}		

}
