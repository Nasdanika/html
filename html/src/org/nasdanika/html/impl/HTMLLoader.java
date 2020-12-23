package org.nasdanika.html.impl;

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
public class HTMLLoader implements ObjectLoader {
	
	private org.nasdanika.common.ObjectLoader chain;

	public HTMLLoader(ObjectLoader chain) {
		this.chain = chain;
	}
	
	public HTMLLoader() {}	

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			case "page":
				return new HTMLPageFactory(loader, config, base, subMonitor, marker);
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}
		
}
