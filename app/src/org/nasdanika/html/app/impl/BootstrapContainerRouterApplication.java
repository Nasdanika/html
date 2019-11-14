package org.nasdanika.html.app.impl;

import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Theme;

/**
 * Bootstrap container single page application with Backbone router for loading content.
 * @author Pavel
 *
 */
public class BootstrapContainerRouterApplication extends BootstrapContainerApplication {
	
	/**
	 * Creates bootstrap content routing CDN application
	 */
	public BootstrapContainerRouterApplication() {
		this(BootstrapFactory.INSTANCE, false);
	}

	/**
	 * Creates bootstrap content routing CDN application
	 */
	public BootstrapContainerRouterApplication(boolean fluid) {
		this(BootstrapFactory.INSTANCE, fluid);
	}
	
	/**
	 * Creates bootstrap content routing CDN application
	 */
	public BootstrapContainerRouterApplication(BootstrapFactory factory, boolean fluid) {
		this(factory, Theme.Default, fluid);
	}
	
	/**
	 * Creates bootstrap content routing CDN application
	 */
	public BootstrapContainerRouterApplication(Theme theme, boolean fluid) {
		this(BootstrapFactory.INSTANCE, theme, fluid);
	}
	
	/**
	 * Creates bootstrap content routing CDN application
	 */
	public BootstrapContainerRouterApplication(BootstrapFactory factory, Theme theme, boolean fluid) {
		this(factory, factory.bootstrapCdnHTMLPage(factory.getHTMLFactory().page(), theme), fluid);
	}	
	
	/**
	 * Creates bootstrap content routing CDN application
	 */
	public BootstrapContainerRouterApplication(BootstrapFactory factory, HTMLPage page, boolean fluid) {
		super(factory, page, fluid);
		page.script("https://underscorejs.org/underscore-min.js");
		page.script("https://backbonejs.org/backbone-min.js");
		page.head(factory.getHTMLFactory().tag(TagName.script, getContentRouterCode(factory))); 		
		contentPanel.toHTMLElement().id("content").content("&nbsp;");
	}	
	
	/**
	 * Returns content router code. This method returns "content-router.js" resource.
	 * Override to customize routing, e.g. invoke an initial route on load.
	 * @param factory
	 * @return
	 */
	protected Object getContentRouterCode(BootstrapFactory factory) {
		return BootstrapContainerRouterApplication.class.getResource("content-router.js");
	}

}
