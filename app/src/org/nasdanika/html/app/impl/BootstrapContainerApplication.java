package org.nasdanika.html.app.impl;

import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Container.Row.Col;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.bootstrap.Theme;

/**
 * Application which uses Bootstrap container for content layout.
 * @author Pavel Vlasov
 *
 */
public class BootstrapContainerApplication implements Application {

	protected HTMLPage page;
	protected Container container;
	protected Col header;
	protected Col navigationBar;
	protected Col navigationPanel;
	protected Col contentPanel;
	protected Col footer;
	protected Row contentRow;

	/**
	 * Creates bootstrap CDN application
	 */
	public BootstrapContainerApplication() {
		this(BootstrapFactory.INSTANCE, false);
	}

	/**
	 * Creates bootstrap CDN application
	 */
	public BootstrapContainerApplication(boolean fluid) {
		this(BootstrapFactory.INSTANCE, fluid);
	}
	
	/**
	 * Creates bootstrap CDN application
	 */
	public BootstrapContainerApplication(BootstrapFactory factory, boolean fluid) {
		this(factory, Theme.Default, fluid);
	}
	
	/**
	 * Creates bootstrap CDN application
	 */
	public BootstrapContainerApplication(Theme theme, boolean fluid) {
		this(BootstrapFactory.INSTANCE, theme, fluid);
	}
	
	/**
	 * Creates bootstrap CDN application
	 */
	public BootstrapContainerApplication(BootstrapFactory factory, Theme theme, boolean fluid) {
		this(factory, factory.getHTMLFactory().page(), fluid);
		factory.bootstrapCdnHTMLPage(page, theme);
	}		
	
	/**
	 * Creates bootstrap application from bootstrap pre-configured page.
	 */
	public BootstrapContainerApplication(BootstrapFactory factory, HTMLPage page, boolean fluid) {
		this.page = page;
		container = fluid ? factory.fluidContainer() : factory.container();
		page.body(container);
		header = container.row().col();
		navigationBar = container.row().col();
		contentRow = container.row();
		navigationPanel = contentRow.col();
		contentPanel = contentRow.col();
		footer = container.row().col();
		
		configureContainer(container);
		configureHeader(header);
		configureNavigationBar(navigationBar);
		configureContentRow(contentRow);
		configureNavigationPanel(navigationPanel);
		configureConentPanel(contentPanel);
		configureFooter(footer);
	}
		
	/**
	 * Override to configure container. This implementation sets border color to DEFAULt and top margin to 1.
	 * @param container
	 */
	protected void configureContainer(Container container) {
		container.border(Color.DEFAULT).margin().top(Breakpoint.DEFAULT, Size.S1);		
	}
	
	/**
	 * Override to configure header. This implementation sets background color to PRIMARY.
	 * @param header
	 */
	protected void configureHeader(Col header) {
		header.background(Color.PRIMARY);		
		header.width(Breakpoint.DEFAULT, Size.NONE);
	}
	
	/**
	 * Override to configure navigation bar. This implementation sets background color to LIGHT,
	 * text color to dark.
	 * @param navigationBar
	 */
	protected void configureNavigationBar(Col navigationBar) {
		navigationBar.background(Color.LIGHT).text().color(Color.DARK);		
		navigationBar.width(Breakpoint.DEFAULT, Size.NONE);
	}
	
	/**
	 * Override to configure content row. This implementation sets min height to 30em.
	 * @param contentRow
	 */
	protected void configureContentRow(Row contentRow) {
		contentRow.toHTMLElement().style("min-height", "30em");		
	}
	
	/**
	 * Override to configure navigation panel.
	 * This method sets auto-width.
	 * @param navigationPanel
	 */
	protected void configureNavigationPanel(Col navigationPanel) {
		navigationPanel.width(Breakpoint.DEFAULT, Size.AUTO);
	}
	
	/**
	 * Override to configure content panel. This implementation sets left border color to DEFAULT
	 * @param contentPanel
	 */
	protected void configureConentPanel(Col contentPanel) {
		contentPanel.border(Color.DEFAULT, Placement.LEFT);		
		contentPanel.width(Breakpoint.DEFAULT, Size.NONE);
	}
	
	/**
	 * Override to configure footer. 
	 * This implementation sets background color to SECONDARY and text alignment to CENTER.
	 * @param footer
	 */
	protected void configureFooter(Col footer) {
		footer.background(Color.SECONDARY).text().alignment(Alignment.CENTER);
		footer.width(Breakpoint.DEFAULT, Size.NONE);		
	}	

	@Override
	public Application header(Object... content) {
		header.toHTMLElement().content(content);
		return this;
	}

	@Override
	public Application navigationBar(Object... content) {
		navigationBar.toHTMLElement().content(content);
		return this;
	}

	@Override
	public Application navigationPanel(Object... content) {
		navigationPanel.toHTMLElement().content(content);
		return this;
	}

	@Override
	public Application contentPanel(Object... content) {
		this.contentPanel.toHTMLElement().content(content);
		return this;
	}

	@Override
	public Application footer(Object... content) {
		footer.toHTMLElement().content(content);
		return this;
	}

	@Override
	public HTMLPage getHTMLPage() {
		return page;
	}

	@Override
	public Object produce(int indent) {
		return page.produce(indent);
	}
	
	@Override
	public String toString() {
		return page.toString();
	}

}
