package org.nasdanika.html.app.factories;

import org.nasdanika.common.persistence.DispatchingLoader;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.html.bootstrap.factories.BootstrapLoader;
import org.nasdanika.html.factories.HTMLLoader;

/**
 * Delegates to Loa {@link HTMLLoader}, {@link BootstrapLoader}, and {@link AppLoader} dispatching by a prefix <code>html-</code> for HTML, <code>bootstrap-</code> for Bootstrap, and <code>app-</code> for App.  
 * @author Pavel
 *
 */
public class ComposedLoader extends DispatchingLoader {
	
	public ComposedLoader(ObjectLoader chain) {
		super(chain);		
		register("exec-", new org.nasdanika.exec.Loader());
		register("app-", new AppLoader()); 
		register("html-", new HTMLLoader());
		register("bootstrap-", new BootstrapLoader());
		register("component-", new ComponentLoader());
	}
	
	public ComposedLoader() {
		this(null);
	}
		
}
