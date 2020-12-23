package org.nasdanika.html.app.impl;

import java.net.URL;

import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;

/**
 * Loader of labels and actions. 
 * @author Pavel
 *
 */
public class AppLoader implements ObjectLoader {
	
	private org.nasdanika.common.ObjectLoader chain;

	public AppLoader(ObjectLoader chain) {
		this.chain = chain;
	}
	
	public AppLoader() {	}	

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			// General
			case "label":
				return new LabelFactory(loader, config, base, subMonitor, marker);
			case "action":
				return new ActionFactory(loader, config, base, subMonitor, marker);
			case "category":
				return new CategoryFactory(loader, config, base, subMonitor, marker);
			case "action-reference":
				return new ActionReference(loader, config, base, subMonitor, marker);
			case "category-reference":
				return new CategoryReference(loader, config, base, subMonitor, marker);
			case "application":
				return new BootstrapContainerApplicationFactory(loader, config, base, subMonitor, marker);
				
			// TODO - appearance	
			
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}
		
}
