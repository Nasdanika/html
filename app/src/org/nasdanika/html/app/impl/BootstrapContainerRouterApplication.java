package org.nasdanika.html.app.impl;

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
		this(factory, Theme.Default, fluid, null);
	}
	
	/**
	 * Creates bootstrap content routing CDN application
	 */
	public BootstrapContainerRouterApplication(Theme theme, boolean fluid) {
		this(BootstrapFactory.INSTANCE, theme, fluid, null);
	}
	
	/**
	 * Creates bootstrap content routing CDN application with an optional initial route.
	 */
	public BootstrapContainerRouterApplication(BootstrapFactory factory, Theme theme, boolean fluid, String initialRoute) {
		super(factory, factory.getHTMLFactory().page(), fluid);
		factory.bootstrapCdnHTMLPage(page, theme);
		page.script("https://underscorejs.org/underscore-min.js");
		page.script("https://backbonejs.org/backbone-min.js");
		page.head(factory.getHTMLFactory().tag(
				TagName.script, 
				factory.getHTMLFactory().interpolate(
						BootstrapContainerRouterApplication.class.getResource("content-router.js"), 
						"initial-route", 
						initialRoute == null ? "undefined" : "\""+initialRoute+"\"")));
		
		contentPanel.toHTMLElement().id("content").content("&nbsp;");
	}		

}
