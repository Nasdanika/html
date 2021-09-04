package org.nasdanika.html.model.app.gen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ListCompoundConsumerFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundConsumerFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumb;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Container.Row.Col;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentPanel;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.bootstrap.Item;
import org.nasdanika.html.model.html.HtmlPackage;

public class ContentPanelConsumerFactoryAdapter extends PagePartConsumerFactoryAdapter<ContentPanel> {

	protected ContentPanelConsumerFactoryAdapter(ContentPanel pagePart) {
		super(pagePart);
	}
	
	/**
	 * Builds the content panel. Creates elements for navigation panels and sections and returns them for subsequent processing by functions which follow. 
	 * @param context
	 * @return
	 */
	private Function<BiSupplier<HTMLElement<?>, Map<EStructuralFeature, Object>>, BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>>> createContentPanelFunction(Context context) {
		return new Function<BiSupplier<HTMLElement<?>,Map<EStructuralFeature, Object>>, BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>>>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Content panel";
			}
			
			@Override
			@SuppressWarnings("unchecked")
			public BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>> execute(BiSupplier<HTMLElement<?>, Map<EStructuralFeature, Object>> input, ProgressMonitor progressMonitor) throws Exception {
				Tag ret = (Tag) input.getFirst();
				
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				Container container = bootstrapFactory.fluidContainer();
				ret.accept(container.toHTMLElement());
				
				List<Object> breadcrumb = (List<Object>) input.getSecond().get(AppPackage.Literals.CONTENT_PANEL__BREADCRUMB);
				if (breadcrumb != null) {
					Breadcrumb bc = bootstrapFactory.breadcrumb();
					for (Object bce: breadcrumb) {
						boolean active = false;
						if (bce instanceof Tag) {
							Object bcd = ((Tag) bce).getData();
							if (bcd instanceof Item) {
								active = ((Item) bcd).isActive();
							}
						}
						bc.item(active, bce);
					}
					bc.toHTMLElement().addClass("nsd-app-content-panel-breadcrumb");
					Row bcRow = container.row();
					bcRow.toHTMLElement().addClass("nsd-app-content-panel-breadcrumb-row");
					bcRow.col(bc);
				}								
				
				Tag title = (Tag) input.getSecond().get(AppPackage.Literals.CONTENT_PANEL__TITLE);
				@SuppressWarnings("unchecked")
				List<Object> items = (List<Object>) input.getSecond().get(AppPackage.Literals.PAGE_PART__ITEMS);
				if (title != null || items != null) {
					Row titleAndItemsRow = container.row();
					titleAndItemsRow.toHTMLElement().addClass("nsd-app-content-panel-title-and-items-row");
					if (title != null) {
						title.addClass("nsd-app-content-panel-title");
						titleAndItemsRow.col(title).width(Breakpoint.DEFAULT, Size.AUTO);
					}
	
					if (items != null) {
		//				ContentPanel semanticElement = getTarget();
						Tag navs = Util.navs(items, context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE));
						navs.addClass("nsd-app-content-panel-navs");
						titleAndItemsRow.col(navs);
					}
				}
				
				Map<EStructuralFeature, HTMLElement<?>> navigationPanels = new LinkedHashMap<>();
				
				Row contentRow = container.row();
				
				contentRow.toHTMLElement().addClass("nsd-app-content-panel-content-row");
				
				// Content nav left
				if (getTarget().getLeftNavigation() != null) {
					Tag leftNavigationColumn = contentRow.col().toHTMLElement();
					leftNavigationColumn.addClass("nsd-app-content-panel-left-navigation");
					navigationPanels.put(AppPackage.Literals.CONTENT_PANEL__LEFT_NAVIGATION, leftNavigationColumn);
				}
				
				Tag contentCol = contentRow.col().toHTMLElement();
				Container contentFloatsAndSectionsContainer = bootstrapFactory.fluidContainer();
				contentCol.accept(contentFloatsAndSectionsContainer);
				Tag ownContentCol = contentFloatsAndSectionsContainer.row().col().toHTMLElement();				

				// Floating navs - left and right
				if (getTarget().getFloatLeftNavigation() != null) {
					Tag floatLeftNavigation = bootstrapFactory.getHTMLFactory().div();
					floatLeftNavigation.addClass("nsd-app-content-panel-float-left-navigation");
					ownContentCol.accept(floatLeftNavigation);
					navigationPanels.put(AppPackage.Literals.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION, floatLeftNavigation);
				}
				if (getTarget().getFloatRightNavigation() != null) {
					Tag floatRightNavigation = bootstrapFactory.getHTMLFactory().div();
					floatRightNavigation.addClass("nsd-app-content-panel-float-right-navigation");
					ownContentCol.accept(floatRightNavigation);
					navigationPanels.put(AppPackage.Literals.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION, floatRightNavigation);
				}
				
				List<Object> content = (List<Object>) input.getSecond().get(HtmlPackage.Literals.HTML_ELEMENT__CONTENT);
				if (content != null) {
					content.forEach(ownContentCol::accept);
				}
				
				// Sections
				List<HTMLElement<?>> sections = new ArrayList<>();
				for (ContentPanel section: getTarget().getSections()) {
					// TODO section style etc. Rows with columns for now.
					
					Tag sectionCol = container.row().col().toHTMLElement();
					sectionCol.setData(section);
					sections.add(sectionCol);
				}				
				
				if (getTarget().getRightNavigation() != null) {
					Tag rightNavigationColumn = contentRow.col().toHTMLElement();
					rightNavigationColumn.addClass("nsd-app-content-panel-right-navigation");
					navigationPanels.put(AppPackage.Literals.CONTENT_PANEL__RIGHT_NAVIGATION, rightNavigationColumn);
				}
				
				return new BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>>() {

					// Navigation panels map
					@Override
					public Map<EStructuralFeature, HTMLElement<?>> getFirst() {
						return navigationPanels;
					}

					// Sections and content panel
					@Override
					public BiSupplier<List<HTMLElement<?>>, HTMLElement<?>> getSecond() {
						return new BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>() {

							@Override
							public List<HTMLElement<?>> getFirst() {
								return sections;
							}

							@Override
							public HTMLElement<?> getSecond() {
								return ret;
							}
							
						};
					}
					
				};
			}
		};
	}
	
	@Override
	protected List<EObject> getContent() {
		return Collections.emptyList();
	}
	
	/**
	 * Adapts items to suppliers, builds {@link Navs} from items and adds them to the header.
	 */
	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) throws Exception {
		ContentPanel semanticElement = getTarget();
		MapCompoundSupplierFactory<EStructuralFeature, Object> featuresSupplierFactory = new MapCompoundSupplierFactory<>("Features");				
		
		EList<EObject> content = semanticElement.getContent();
		if (!content.isEmpty()) {
			featuresSupplierFactory.put(HtmlPackage.Literals.HTML_ELEMENT__CONTENT, new ListCompoundSupplierFactory<>("Content", EObjectAdaptable.adaptToSupplierFactoryNonNull(content, Object.class)));
		}
		
		EList<EObject> items = semanticElement.getItems();
		if (!items.isEmpty()) {
			featuresSupplierFactory.put(AppPackage.Literals.PAGE_PART__ITEMS, new ListCompoundSupplierFactory<>("Navigation items", EObjectAdaptable.adaptToSupplierFactoryNonNull(items, Object.class)));
		}
		
		Label title = semanticElement.getTitle();
		if (title != null) {
			featuresSupplierFactory.put(AppPackage.Literals.CONTENT_PANEL__TITLE, EObjectAdaptable.adaptToSupplierFactoryNonNull(title, Object.class));
		}
		
		EList<Label> breadcrumb = semanticElement.getBreadcrumb();
		if (!breadcrumb.isEmpty()) {
			featuresSupplierFactory.put(AppPackage.Literals.CONTENT_PANEL__BREADCRUMB, new ListCompoundSupplierFactory<>("Breadcrumb", EObjectAdaptable.adaptToSupplierFactoryNonNull(breadcrumb, Object.class)));
		}
				
		Function<HTMLElement<?>, BiSupplier<HTMLElement<?>, Map<EStructuralFeature, Object>>> featureSuppliersFunction = featuresSupplierFactory.<HTMLElement<?>>asFunctionFactory().create(context);				
		Function<HTMLElement<?>, BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>>> contentPanelFunction = super.createConfigureFunction(context).then(featureSuppliersFunction).then(createContentPanelFunction(context));
		
		// Navigation panels and sections
		MapCompoundConsumerFactory<EStructuralFeature, HTMLElement<?>> navigationPanelsFactory = new MapCompoundConsumerFactory<>("Navigation panels");
		
		NavigationPanel floatLeftNavigation = semanticElement.getFloatLeftNavigation();
		if (floatLeftNavigation != null) {
			navigationPanelsFactory.put(AppPackage.Literals.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION, EObjectAdaptable.adaptToConsumerFactoryNonNull(floatLeftNavigation, HTMLElement.class));			
		}
		NavigationPanel leftNavigation = semanticElement.getLeftNavigation();
		if (leftNavigation != null) {
			navigationPanelsFactory.put(AppPackage.Literals.CONTENT_PANEL__LEFT_NAVIGATION, EObjectAdaptable.adaptToConsumerFactoryNonNull(leftNavigation, HTMLElement.class));			
		}
		NavigationPanel floatRightNavigation = semanticElement.getFloatRightNavigation();
		if (floatRightNavigation != null) {
			navigationPanelsFactory.put(AppPackage.Literals.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION, EObjectAdaptable.adaptToConsumerFactoryNonNull(floatRightNavigation, HTMLElement.class));			
		}
		NavigationPanel rightNavigation = semanticElement.getRightNavigation();
		if (rightNavigation != null) {
			navigationPanelsFactory.put(AppPackage.Literals.CONTENT_PANEL__RIGHT_NAVIGATION, EObjectAdaptable.adaptToConsumerFactoryNonNull(rightNavigation, HTMLElement.class));			
		}
		
		FunctionFactory<BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>>, BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>> navigationPanelsFunctionFactory = navigationPanelsFactory.asBiSupplierFunctionFactory();
		Function<HTMLElement<?>, BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>> contentPanelWithNavigationsFunction = contentPanelFunction.then(navigationPanelsFunctionFactory.create(context));

		// Sections
		ListCompoundConsumerFactory<HTMLElement<?>> sectionsFactory = new ListCompoundConsumerFactory<>("Sections", EObjectAdaptable.adaptToConsumerFactoryNonNull(semanticElement.getSections(), HTMLElement.class));
		Function<BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>, HTMLElement<?>> sectionsFunction = sectionsFactory.<HTMLElement<?>>asBiSupplierFunctionFactory().create(context);
		Function<HTMLElement<?>, HTMLElement<?>> ret = contentPanelWithNavigationsFunction.then(sectionsFunction);
		
		return ret;
	}	

}
