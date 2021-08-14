package org.nasdanika.html.model.app.gen;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.CompoundConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Container.Row.Col;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Page;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;

public class PageSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<Tag> {
	
	public PageSupplierFactoryAdapter(Page page) {
		setTarget(page);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	protected Function<Map<EStructuralFeature, java.util.function.Consumer<BootstrapElement<?,?>>>, Tag> createContainerFunction(Context context) {
		return new Function<Map<EStructuralFeature, java.util.function.Consumer<BootstrapElement<?,?>>>, Tag>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "HTML Page";
			}
			
			@Override
			public Tag execute(Map<EStructuralFeature, java.util.function.Consumer<BootstrapElement<?,?>>> inputs, ProgressMonitor progressMonitor) throws Exception {
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				Page semanticElement = (Page) getTarget();
				Container container = semanticElement.isFluid() ? bootstrapFactory.fluidContainer() : bootstrapFactory.container();
				if (inputs.containsKey(BootstrapPackage.Literals.BOOTSTRAP_ELEMENT__APPEARANCE)) {
					inputs.get(BootstrapPackage.Literals.BOOTSTRAP_ELEMENT__APPEARANCE).accept(container);
				}
				
				if (inputs.containsKey(AppPackage.Literals.PAGE__HEADER)) {
					Col header = container.row().col();				
					header.toHTMLElement().addClass("nsd-app-header");
					inputs.get(AppPackage.Literals.PAGE__HEADER).accept(header);
				}
				
				if (inputs.containsKey(AppPackage.Literals.PAGE__NAVIGATION_BAR)) {
					Col navigationBar =  container.row().col();
					navigationBar.toHTMLElement().addClass("nsd-app-navbar");
					inputs.get(AppPackage.Literals.PAGE__NAVIGATION_BAR).accept(navigationBar);
				}

				if (inputs.containsKey(AppPackage.Literals.PAGE__NAVIGATION_PANEL) || inputs.containsKey(AppPackage.Literals.PAGE__CONTENT_PANEL)) {
					Row contentRow = container.row();
					contentRow.toHTMLElement().addClass("nsd-app-content-row");
					if (inputs.containsKey(AppPackage.Literals.PAGE__CONTENT_ROW_APPEARANCE)) {
						inputs.get(AppPackage.Literals.PAGE__CONTENT_ROW_APPEARANCE).accept(contentRow);
					}

					if (inputs.containsKey(AppPackage.Literals.PAGE__NAVIGATION_PANEL)) {
						Col navigationPanel = inputs.containsKey(AppPackage.Literals.PAGE__NAVIGATION_PANEL) ? contentRow.col() : null;
						navigationPanel.toHTMLElement().addClass("nsd-app-navigation-panel");
						inputs.get(AppPackage.Literals.PAGE__NAVIGATION_PANEL).accept(navigationPanel);
					}
				
					if (inputs.containsKey(AppPackage.Literals.PAGE__CONTENT_PANEL)) {
						Col contentPanel = inputs.containsKey(AppPackage.Literals.PAGE__CONTENT_PANEL) ? contentRow.col() : null;
						contentPanel.toHTMLElement().addClass("nsd-app-content-panel");
						inputs.get(AppPackage.Literals.PAGE__CONTENT_PANEL).accept(contentPanel);
					}
				}

				if (inputs.containsKey(AppPackage.Literals.PAGE__FOOTER)) {
					Col footer =  container.row().col();
					footer.toHTMLElement().addClass("nsd-app-footer");
					inputs.get(AppPackage.Literals.PAGE__FOOTER).accept(footer);
				}
				
				Tag ret = container.toHTMLElement();
				ret.addClass("nsd-app-container");
				return ret;
			}
		};
		
	}
	
	@Override
	public Supplier<Tag> create(Context context) throws Exception {
		Page page = (Page) getTarget();
		ListCompoundSupplierFactory<Object> headFactory = new ListCompoundSupplierFactory<>("Head");
		for (EObject he: page.getHead()) {
			headFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(he, Object.class), "Cannot to adapt to SupplierFactory: " + he));
		}		

		ListCompoundSupplierFactory<Object> bodyFactory = new ListCompoundSupplierFactory<>("Body");
		for (EObject be: page.getBody()) {
			bodyFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(be, Object.class), "Cannot to adapt to SupplierFactory: " + be));
		}		
		
		CompoundConsumerFactory<HTMLPage> buildFactory = new CompoundConsumerFactory<>("Builders");
		for (EObject be: page.getBuilders()) {
			buildFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(be, HTMLPage.class), "Cannot to adapt to ConsumerFactory: " + be));
		}		
		
		return headFactory.then(bodyFactory.asFunctionFactory()).then(this::createPageFunction).then(buildFactory.asFunctionFactory()).create(context);
	}	
	
// ---
	
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


	/**
	 * Creates bootstrap application from bootstrap pre-configured page.
	 */
	public BootstrapContainerApplication(
			BootstrapFactory factory, 
			HTMLPage page, 
			boolean fluid,
			Function<Section,Consumer<Object>> sectionConfigurators) {
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
