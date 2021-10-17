package org.nasdanika.html.app.impl;

import java.util.function.Consumer;
import java.util.function.Function;

import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Container.Row.Col;
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
	
	/**
	 * Application section
	 * @author Pavel
	 *
	 */
	public enum Section {
		Container,
		Header,
		NavigationBar,
		ContentRow,
		NavigationPanel,
		ContentPanel,
		Footer
	}	

	protected HTMLPage page;
	protected Container container;
	protected Col header;
	protected Col navigationBar;
	protected Col navigationPanel;
	protected Col contentPanel;
	protected Col footer;
	protected Row contentRow;
	protected Function<Section, Consumer<Object>> sectionConfigurators;

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
		this(factory, factory.getHTMLFactory().page(), fluid,  null);
		factory.bootstrapCdnHTMLPage(page, theme);
	}		
	
	/**
	 * Creates bootstrap application from bootstrap pre-configured page.
	 */
	public BootstrapContainerApplication(
			BootstrapFactory factory, 
			HTMLPage page, 
			boolean fluid,
			Function<Section,Consumer<Object>> sectionConfigurators) {
		this.page = page;
		container = fluid ? factory.fluidContainer() : factory.container();
		page.body(container);
		header = container.row().col();
		navigationBar = container.row().col();
		contentRow = container.row();
		navigationPanel = contentRow.col();
		contentPanel = contentRow.col();
		footer = container.row().col();
		
		// Nasdanika classes
		container.toHTMLElement().addClass("nsd-app-container");
		header.toHTMLElement().addClass("nsd-app-header");
		navigationBar.toHTMLElement().addClass("nsd-app-navbar");
		contentRow.toHTMLElement().addClass("nsd-app-content-row");
		navigationPanel.toHTMLElement().addClass("nsd-app-navigation-panel");
		contentPanel.toHTMLElement().addClass("nsd-app-content-panel");
		footer.toHTMLElement().addClass("nsd-app-footer");
		
		this.sectionConfigurators = sectionConfigurators;
		
		// Configuration
		configureContainer(container);
		configureHeader(header);
		configureNavigationBar(navigationBar);
		configureContentRow(contentRow);
		configureNavigationPanel(navigationPanel);
		configureConentPanel(contentPanel);
		configureFooter(footer);
	}
	
	private Consumer<Object> getConfigurator(Section section) {
		return sectionConfigurators == null ? null : sectionConfigurators.apply(section);
	}
		
	/**
	 * Override to configure container. This implementation sets border color to DEFAULt and top margin to 1.
	 * @param container
	 */
	protected void configureContainer(Container container) {
		Consumer<Object> configurator = getConfigurator(Section.Container);
		if (configurator == null) {
			container.border(Color.DEFAULT).margin().top(Breakpoint.DEFAULT, Size.S1);		
		} else {
			configurator.accept(container);
		}
	}
	
	/**
	 * Override to configure header. This implementation sets background color to PRIMARY.
	 * @param header
	 */
	protected void configureHeader(Col header) {
		Consumer<Object> configurator = getConfigurator(Section.Header);
		if (configurator == null) {
			header.width(Breakpoint.DEFAULT, Size.NONE);
			header.background(Color.PRIMARY);
		} else {
			configurator.accept(header);
		}
	}
	
	/**
	 * Override to configure navigation bar. This implementation sets background color to LIGHT,
	 * text color to dark.
	 * @param navigationBar
	 */
	protected void configureNavigationBar(Col navigationBar) {
		Consumer<Object> configurator = getConfigurator(Section.NavigationBar);
		if (configurator == null) {
			navigationBar.background(Color.LIGHT).text().color(Color.DARK);		
			navigationBar.width(Breakpoint.DEFAULT, Size.NONE);
		} else {
			configurator.accept(navigationBar);
		}
	}
	
	/**
	 * Override to configure content row. This implementation sets min height to 30em.
	 * @param contentRow
	 */
	protected void configureContentRow(Row contentRow) {
		Consumer<Object> configurator = getConfigurator(Section.ContentRow);
		if (configurator == null) {
			contentRow.toHTMLElement().style("min-height", "30em");		
		} else {
			configurator.accept(contentRow);
		}
	}
	
	/**
	 * Override to configure navigation panel.
	 * This method sets auto-width.
	 * @param navigationPanel
	 */
	protected void configureNavigationPanel(Col navigationPanel) {
		Consumer<Object> configurator = getConfigurator(Section.NavigationPanel);
		if (configurator == null) {
			navigationPanel.width(Breakpoint.DEFAULT, Size.AUTO);
		} else {
			configurator.accept(navigationPanel);
		}
	}
	
	/**
	 * Override to configure content panel. This implementation sets left border color to DEFAULT
	 * @param contentPanel
	 */
	protected void configureConentPanel(Col contentPanel) {
		Consumer<Object> configurator = getConfigurator(Section.ContentPanel);
		if (configurator == null) {
			contentPanel.border(Color.DEFAULT, Placement.LEFT);		
			contentPanel.width(Breakpoint.DEFAULT, Size.NONE);
		} else {
			configurator.accept(contentPanel);
		}
	}
	
	/**
	 * Override to configure footer. 
	 * This implementation sets background color to SECONDARY and text alignment to CENTER.
	 * @param footer
	 */
	protected void configureFooter(Col footer) {
		Consumer<Object> configurator = getConfigurator(Section.Footer);
		if (configurator == null) {
			footer.background(Color.SECONDARY).text().alignment(Alignment.CENTER);
			footer.width(Breakpoint.DEFAULT, Size.NONE);		
		} else {
			configurator.accept(footer);
		}
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
