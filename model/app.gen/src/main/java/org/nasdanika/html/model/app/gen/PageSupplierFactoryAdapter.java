package org.nasdanika.html.model.app.gen;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundConsumerFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Container.Row.Col;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentPanel;
import org.nasdanika.html.model.app.Footer;
import org.nasdanika.html.model.app.Header;
import org.nasdanika.html.model.app.NavigationBar;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.Page;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.gen.BootstrapElementSupplierFactoryAdapter;

public class PageSupplierFactoryAdapter extends BootstrapElementSupplierFactoryAdapter<Page, BootstrapElement<?,?>> {
	
	public PageSupplierFactoryAdapter(Page page) {
		super(page);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
		
	protected Supplier<BiSupplier<Map<EStructuralFeature, BootstrapElement<?,?>>, HTMLElement<?>>> createContainerSupplier(Context context) {
		return new Supplier<BiSupplier<Map<EStructuralFeature, BootstrapElement<?,?>>, HTMLElement<?>>>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Container";
			}

			@Override
			public BiSupplier<Map<EStructuralFeature, BootstrapElement<?,?>>, HTMLElement<?>> execute(ProgressMonitor progressMonitor) throws Exception {
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				Page semanticElement = (Page) getTarget();
				Container container = semanticElement.isFluid() ? bootstrapFactory.fluidContainer() : bootstrapFactory.container();
				
				Map<EStructuralFeature, BootstrapElement<?,?>> parts = new LinkedHashMap<>();
				
				if (semanticElement.getHeader() != null) {
					Col header = container.row().col();				
					header.toHTMLElement().addClass("nsd-app-header");					
					parts.put(AppPackage.Literals.PAGE__HEADER, header);
				}
				
				if (semanticElement.getNavigationBar() != null) {
					Col navigationBar =  container.row().col();
					navigationBar.toHTMLElement().addClass("nsd-app-navbar");
					parts.put(AppPackage.Literals.PAGE__NAVIGATION_BAR, navigationBar);
				}

				if (semanticElement.getNavigationPanel() != null || semanticElement.getContentPanel() != null) {
					Row contentRow = container.row();
					contentRow.toHTMLElement().addClass("nsd-app-content-row");
					parts.put(AppPackage.Literals.PAGE__CONTENT_ROW_APPEARANCE, contentRow);

					if (semanticElement.getNavigationPanel() != null) {
						Col navigationPanel = contentRow.col();
						navigationPanel.toHTMLElement().addClass("nsd-app-navigation-panel");
						parts.put(AppPackage.Literals.PAGE__NAVIGATION_PANEL, navigationPanel);
					}
				
					if (semanticElement.getContentPanel() != null) {
						Col contentPanel = contentRow.col();
						contentPanel.toHTMLElement().addClass("nsd-app-content-panel");
						parts.put(AppPackage.Literals.PAGE__CONTENT_PANEL, contentPanel);
					}
				}

				if (semanticElement.getFooter() != null) {
					Col footer =  container.row().col();
					footer.toHTMLElement().addClass("nsd-app-footer");
					parts.put(AppPackage.Literals.PAGE__FOOTER, footer);
				}
				
				Tag containerTag = container.toHTMLElement();
				containerTag.addClass("nsd-app-container");
				
				return new BiSupplier<Map<EStructuralFeature,BootstrapElement<?,?>>, HTMLElement<?>>() {

					@Override
					public Map<EStructuralFeature, BootstrapElement<?,?>> getFirst() {
						return parts;
					}

					@Override
					public HTMLElement<?> getSecond() {
						return containerTag;
					}
				};
			}
			
		};
	}
	
	@Override
	public Supplier<HTMLElement<?>> createElementSupplier(Context context) throws Exception {
		MapCompoundConsumerFactory<EStructuralFeature, BootstrapElement<?,?>> partsFactory = new MapCompoundConsumerFactory<>("Page parts");
		Page semanticElement = (Page) getTarget();
		
		Header header = semanticElement.getHeader();
		if (header != null) {
			partsFactory.put(AppPackage.Literals.PAGE__HEADER, EObjectAdaptable.adaptToConsumerFactoryNonNull(header, BootstrapElement.class));			
		}
		NavigationBar navigationBar = semanticElement.getNavigationBar();
		if (navigationBar != null) {
			partsFactory.put(AppPackage.Literals.PAGE__NAVIGATION_BAR, EObjectAdaptable.adaptToConsumerFactoryNonNull(navigationBar, BootstrapElement.class));			
		}
		Appearance conentRowAppearance = semanticElement.getContentRowAppearance();
		if (conentRowAppearance != null) {
			partsFactory.put(AppPackage.Literals.PAGE__CONTENT_ROW_APPEARANCE, EObjectAdaptable.adaptToConsumerFactoryNonNull(conentRowAppearance, BootstrapElement.class));			
		}
		NavigationPanel navigationPanel = semanticElement.getNavigationPanel();
		if (navigationPanel != null) {
			partsFactory.put(AppPackage.Literals.PAGE__NAVIGATION_PANEL, EObjectAdaptable.adaptToConsumerFactoryNonNull(navigationPanel, BootstrapElement.class));			
		}
		ContentPanel contentPanel = semanticElement.getContentPanel();
		if (contentPanel != null) {
			partsFactory.put(AppPackage.Literals.PAGE__CONTENT_PANEL, EObjectAdaptable.adaptToConsumerFactoryNonNull(contentPanel, BootstrapElement.class));			
		}
		Footer footer = semanticElement.getFooter();
		if (footer != null) {
			partsFactory.put(AppPackage.Literals.PAGE__FOOTER, EObjectAdaptable.adaptToConsumerFactoryNonNull(footer, BootstrapElement.class));			
		}	
		
		SupplierFactory<BiSupplier<Map<EStructuralFeature, BootstrapElement<?,?>>, HTMLElement<?>>> containerSupplierFactory = this::createContainerSupplier;		
		FunctionFactory<BiSupplier<Map<EStructuralFeature, BootstrapElement<?,?>>, HTMLElement<?>>, HTMLElement<?>> partsFunctionFactory = partsFactory.asBiSupplierFunctionFactory();
		return containerSupplierFactory.then(partsFunctionFactory).create(context);
	}	
	
	
// --- TODO: TO CSS ---
//	
//	/**
//	 * Override to configure container. This implementation sets border color to DEFAULt and top margin to 1.
//	 * @param container
//	 */
//	protected void configureContainer(Container container) {
//		Consumer<Object> configurator = getConfigurator(Section.Container);
//		if (configurator == null) {
//			container.border(Color.DEFAULT).margin().top(Breakpoint.DEFAULT, Size.S1);		
//		} else {
//			configurator.accept(container);
//		}
//	}
//	
//	/**
//	 * Override to configure header. This implementation sets background color to PRIMARY.
//	 * @param header
//	 */
//	protected void configureHeader(Col header) {
//		Consumer<Object> configurator = getConfigurator(Section.Header);
//		if (configurator == null) {
//			header.width(Breakpoint.DEFAULT, Size.NONE);
//			header.background(Color.PRIMARY);
//		} else {
//			configurator.accept(header);
//		}
//	}
//	
//	/**
//	 * Override to configure navigation bar. This implementation sets background color to LIGHT,
//	 * text color to dark.
//	 * @param navigationBar
//	 */
//	protected void configureNavigationBar(Col navigationBar) {
//		Consumer<Object> configurator = getConfigurator(Section.NavigationBar);
//		if (configurator == null) {
//			navigationBar.background(Color.LIGHT).text().color(Color.DARK);		
//			navigationBar.width(Breakpoint.DEFAULT, Size.NONE);
//		} else {
//			configurator.accept(navigationBar);
//		}
//	}
//	
//	/**
//	 * Override to configure content row. This implementation sets min height to 30em.
//	 * @param contentRow
//	 */
//	protected void configureContentRow(Row contentRow) {
//		Consumer<Object> configurator = getConfigurator(Section.ContentRow);
//		if (configurator == null) {
//			contentRow.toHTMLElement().style("min-height", "30em");		
//		} else {
//			configurator.accept(contentRow);
//		}
//	}
//	
//	/**
//	 * Override to configure navigation panel.
//	 * This method sets auto-width.
//	 * @param navigationPanel
//	 */
//	protected void configureNavigationPanel(Col navigationPanel) {
//		Consumer<Object> configurator = getConfigurator(Section.NavigationPanel);
//		if (configurator == null) {
//			navigationPanel.width(Breakpoint.DEFAULT, Size.AUTO);
//		} else {
//			configurator.accept(navigationPanel);
//		}
//	}
//	
//	/**
//	 * Override to configure content panel. This implementation sets left border color to DEFAULT
//	 * @param contentPanel
//	 */
//	protected void configureConentPanel(Col contentPanel) {
//		Consumer<Object> configurator = getConfigurator(Section.ContentPanel);
//		if (configurator == null) {
//			contentPanel.border(Color.DEFAULT, Placement.LEFT);		
//			contentPanel.width(Breakpoint.DEFAULT, Size.NONE);
//		} else {
//			configurator.accept(contentPanel);
//		}
//	}
//	
//	/**
//	 * Override to configure footer. 
//	 * This implementation sets background color to SECONDARY and text alignment to CENTER.
//	 * @param footer
//	 */
//	protected void configureFooter(Col footer) {
//		Consumer<Object> configurator = getConfigurator(Section.Footer);
//		if (configurator == null) {
//			footer.background(Color.SECONDARY).text().alignment(Alignment.CENTER);
//			footer.width(Breakpoint.DEFAULT, Size.NONE);		
//		} else {
//			configurator.accept(footer);
//		}
//	}	
	
}
