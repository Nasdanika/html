package org.nasdanika.html.ecore;

import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.html.app.impl.BootstrapContainerRouterApplication;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Theme;

/**
 * Bootstrap container single page application with Backbone router for loading content.
 * @author Pavel
 *
 */
public class EcoreDocumentationApplication extends BootstrapContainerRouterApplication {
	
	/**
	 * Creates bootstrap content routing CDN application
	 */
	public EcoreDocumentationApplication() {
		this(BootstrapFactory.INSTANCE, false);
	}

	/**
	 * Creates bootstrap content routing CDN application
	 */
	public EcoreDocumentationApplication(boolean fluid) {
		this(BootstrapFactory.INSTANCE, fluid);
	}
	
	/**
	 * Creates bootstrap content routing CDN application
	 */
	public EcoreDocumentationApplication(BootstrapFactory factory, boolean fluid) {
		this(factory, Theme.Default, fluid);
	}
	
	/**
	 * Creates bootstrap content routing CDN application
	 */
	public EcoreDocumentationApplication(Theme theme, boolean fluid) {
		this(BootstrapFactory.INSTANCE, theme, fluid);
	}
	
	/**
	 * Creates bootstrap content routing CDN application with an optional initial route.
	 */
	public EcoreDocumentationApplication(BootstrapFactory factory, Theme theme, boolean fluid) {
		super(factory, theme, fluid);
		page.stylesheet(MarkdownHelper.GITHUB_MARKDOWN_CSS_CDN);
	}	
	
	/**
	 * @param factory
	 * @return
	 */
	protected Object getContentRouterCode(BootstrapFactory factory) {
		return factory.getHTMLFactory().interpolate(EcoreDocumentationApplication.class.getResource("content-router.js"), "initial-route", "doc-summary");
	}

}
