package org.nasdanika.html.app.factories;

import java.net.URL;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Loader of components such as lists and tables of contents. 
 * @author Pavel
 *
 */
public class ComponentLoader implements ObjectLoader {
	
	private org.nasdanika.common.persistence.ObjectLoader chain;

	public ComponentLoader(ObjectLoader chain) {
		this.chain = chain;
	}
	
	public ComponentLoader() {}	

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			case "list-of-contents":
				ListOfContentsSupplierFactory listOfContentsSupplierFactory = new ListOfContentsSupplierFactory();
				listOfContentsSupplierFactory.load(loader, config, base, subMonitor, marker);
				return listOfContentsSupplierFactory;
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}
		
}
