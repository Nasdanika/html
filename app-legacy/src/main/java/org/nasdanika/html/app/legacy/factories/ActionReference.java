package org.nasdanika.html.app.factories;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
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
import org.nasdanika.html.app.impl.PathNavigationActionActivator;

/**
 * Not interpolated action reference resolved/loaded at load time.
 * TODO later - interpolated reference resolved/loaded at factory create time.
 * @author Pavel
 *
 */
public class ActionReference implements SupplierFactory<Action>, Marked {
	
	protected SupplierFactory<Action> target;
	private Marker marker;
	// TODO - properties
	
	private static final String TARGET_KEY = "target";
	private static final String PATH_KEY = "path";
	
	private String path; 
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public ActionReference(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		if (config instanceof String) {
			setTarget(loader, base, progressMonitor, marker, (String) config);
		} else if (config instanceof Map) {
			Map<?,?> configMap = (Map<?,?>) config;
			org.nasdanika.common.Util.checkUnsupportedKeys(configMap, TARGET_KEY, PATH_KEY);
			setTarget(loader, base, progressMonitor, marker, org.nasdanika.common.Util.getString(configMap, TARGET_KEY, null));
			path =  org.nasdanika.common.Util.getString(configMap, PATH_KEY, null);
		} else {
			throw new ConfigurationException("Action reference type must be a string or a map, got " + config, marker);
		}
	}

	private void setTarget(ObjectLoader loader, URL base, ProgressMonitor progressMonitor, Marker marker, String configStr) throws MalformedURLException, Exception {
		URL targetURL = configStr.startsWith(Reference.CLASSPATH_URL_PREFIX) ? loader.getClass().getClassLoader().getResource(configStr.substring(Reference.CLASSPATH_URL_PREFIX.length())) : new URL(base, configStr);
		Object loaded = loader.loadYaml(targetURL, progressMonitor);
		SupplierFactory<Object> supplierFactory = org.nasdanika.common.Util.<Object>asSupplierFactory(loaded);
		if (supplierFactory == null) {
			throw new ConfigurationException("Cannot adapt to SupplierFactory: " + loaded, marker);
		}
		target = supplierFactory.then(FunctionFactory.adapter(Action.class));
	}
	
	@Override
	public Supplier<Action> create(Context context) throws Exception {
		String iPath = context.interpolateToString(path);
		Supplier<Action> actionSupplier = target.create(context);
		if (Util.isBlank(iPath)) {
			return actionSupplier;
		}		
		Function<Action,Action> setPath = new Function<Action, Action>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Action reference path";
			}

			@Override
			public Action execute(Action action, ProgressMonitor progressMonitor) throws Exception {
				if (action.getActivator() instanceof PathNavigationActionActivator) {
					((PathNavigationActionActivator) action.getActivator()).getPath().addFirst(iPath);
				}
				
				return action;
			}
		};
		return actionSupplier.then(setPath);
	}		

}
