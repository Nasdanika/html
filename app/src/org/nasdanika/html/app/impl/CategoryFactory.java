package org.nasdanika.html.app.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ContextualFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;

public class CategoryFactory implements ContextualFactory<List<Action>>, Marked {
		
	private Marker marker;
	private LabelFactory labelFactory;
	private List<Object> actions = new ArrayList<>();
	
	private static final String ACTIONS_KEY = "actions";
	// TODO - path.
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	@SuppressWarnings("unchecked")
	public CategoryFactory(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			labelFactory = new LabelFactory(loader, config, base, progressMonitor, marker) {
				
				@Override
				protected Collection<String> getSupportedKeys() {
					Collection<String> supportedKeys = super.getSupportedKeys();
					supportedKeys.add(ACTIONS_KEY);
					return supportedKeys;
				}
				
			};
			Map<String,Object> configMap = (Map<String,Object>) config;
			for (Object actionSpec: Util.getCollection(configMap, ACTIONS_KEY, Collections.emptyList())) {
				actions.add(loader.load(actionSpec, base, progressMonitor));
			}
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}		
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Action> create(Context context) throws Exception {
		List<Action> ret = new ArrayList<>();
		Label category = labelFactory.create(context);
		for (Object a: actions) {
			Action action;
			if (a instanceof Action) {
				action = (Action) a;
			} else {
				action = ((ContextualFactory<Action>) a).create(context);
			}
			((ActionImpl) action).setCategory(category);
			ret.add(action);
		}
		return ret;
	}
	
	

}
