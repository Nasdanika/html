package org.nasdanika.html.app.factories;

import java.net.URL;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.html.bootstrap.factories.BootstrapLoader;
import org.nasdanika.html.factories.HTMLLoader;

/**
 * Delegates to Loa {@link HTMLLoader}, {@link BootstrapLoader}, and {@link AppLoader} dispatching by a prefix <code>html-</code> for HTML, <code>bootstrap-</code> for Bootstrap, and <code>app-</code> for App.  
 * @author Pavel
 *
 */
public class ComposedLoader implements ObjectLoader {
	
	private org.nasdanika.common.persistence.ObjectLoader chain;

	public ComposedLoader(ObjectLoader chain) {
		this.chain = chain;
	}
	
	public ComposedLoader() {}
	
	private ObjectLoader execLoader = new org.nasdanika.exec.Loader();
	private ObjectLoader appLoader = new AppLoader();
	private ObjectLoader componentLoader = new ComponentLoader();
	private ObjectLoader htmlLoader = new HTMLLoader();
	private ObjectLoader bootstrapLoader = new BootstrapLoader();
	
	private static final String EXEC_PREFIX = "exec-";
	private static final String APP_PREFIX = "app-";
	private static final String HTML_PREFIX = "html-";
	private static final String BOOTSTRAP_PREFIX = "bootstrap-";
	private static final String COMPONENT_PREFIX = "component-";

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (type.startsWith(EXEC_PREFIX)) {
			return execLoader.create(loader, type.substring(EXEC_PREFIX.length()), config, base, progressMonitor, marker);
		}
		if (type.startsWith(APP_PREFIX)) {
			return appLoader.create(loader, type.substring(APP_PREFIX.length()), config, base, progressMonitor, marker);
		}
		if (type.startsWith(COMPONENT_PREFIX)) {
			return componentLoader.create(loader, type.substring(COMPONENT_PREFIX.length()), config, base, progressMonitor, marker);
		}
		if (type.startsWith(HTML_PREFIX)) {
			return htmlLoader.create(loader, type.substring(HTML_PREFIX.length()), config, base, progressMonitor, marker);
		}
		if (type.startsWith(BOOTSTRAP_PREFIX)) {
			return bootstrapLoader.create(loader, type.substring(BOOTSTRAP_PREFIX.length()), config, base, progressMonitor, marker);
		}
		
		if (chain == null) {
			throw new ConfigurationException("Unsupported type: " + type, marker);
		}
		
		return chain.create(loader, type, config, base, progressMonitor, marker);
	}
		
}
