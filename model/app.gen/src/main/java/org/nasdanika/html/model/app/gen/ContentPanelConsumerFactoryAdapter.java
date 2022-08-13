package org.nasdanika.html.model.app.gen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.notify.AdapterFactory;
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
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.RowContainer.Row.Cell;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumb;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Table.TableBody;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentPanel;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.html.model.bootstrap.Item;
import org.nasdanika.html.model.html.HtmlPackage;

public class ContentPanelConsumerFactoryAdapter extends PagePartConsumerFactoryAdapter<ContentPanel> {

	private static final String TITLE_CONSUMER_KEY = "title-consumer";

	protected ContentPanelConsumerFactoryAdapter(ContentPanel pagePart, AdapterFactory adapterFactory) {
		super(pagePart, adapterFactory);
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
			public BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, BiSupplier<List<HTMLElement<?>>, HTMLElement<?>>> execute(BiSupplier<HTMLElement<?>, Map<EStructuralFeature, Object>> input, ProgressMonitor progressMonitor) {
				HTMLElement<?> ret = (HTMLElement<?>) input.getFirst();
				
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				Map<EStructuralFeature, HTMLElement<?>> navigationPanels = new LinkedHashMap<>();
				
				// Floating navs - left and right
				ContentPanel semanticElement = getTarget();
				if (semanticElement.getFloatLeftNavigation() != null) {
					Tag floatLeftNavigation = bootstrapFactory.getHTMLFactory().div();
					floatLeftNavigation.addClass("nsd-app-content-panel-float-left-navigation");
					((java.util.function.Consumer<Object>) ret).accept(floatLeftNavigation);
					navigationPanels.put(AppPackage.Literals.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION, floatLeftNavigation);
				}
				if (semanticElement.getFloatRightNavigation() != null) {
					Tag floatRightNavigation = bootstrapFactory.getHTMLFactory().div();
					floatRightNavigation.addClass("nsd-app-content-panel-float-right-navigation");
					((java.util.function.Consumer<Object>) ret).accept(floatRightNavigation);
					navigationPanels.put(AppPackage.Literals.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION, floatRightNavigation);
				}
								
				Container container = bootstrapFactory.fluidContainer();
				((java.util.function.Consumer<Object>) ret).accept(container.toHTMLElement());
				
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
				
				SectionStyle effectiveSemanticContainerSectionStyle = SectionStyle.HEADER;
				EObject semanticContainer = semanticElement.eContainer();
				if (semanticContainer instanceof ContentPanel && semanticElement.eContainmentFeature() == AppPackage.Literals.CONTENT_PANEL__SECTIONS) {
					effectiveSemanticContainerSectionStyle = effectiveSectionStyle((ContentPanel) semanticContainer);
				}
				
				Tag title = (Tag) input.getSecond().get(AppPackage.Literals.CONTENT_PANEL__TITLE);
				List<Object> items = (List<Object>) input.getSecond().get(AppPackage.Literals.PAGE_PART__ITEMS);
				if (title != null || items != null) {
					Row titleAndItemsRow = container.row();
					titleAndItemsRow.toHTMLElement().addClass("nsd-app-content-panel-title-and-items-row");
					boolean withTitle = effectiveSemanticContainerSectionStyle == SectionStyle.HEADER && title != null;
					if (withTitle) {
						title.addClass("nsd-app-content-panel-title");
						Tag titleHeader = bootstrapFactory.getHTMLFactory().tag("H" + Math.min(headerDepth(semanticElement) + 1, 6), title);
						titleAndItemsRow.col(titleHeader).width(Breakpoint.DEFAULT, Size.AUTO);
					}
	
					if (items != null) {
		//				ContentPanel semanticElement = getTarget();
						Tag navs = Util.navs(items, context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE));
						if (withTitle) {
							navs.addClass("nsd-app-content-panel-navs");
						}
						titleAndItemsRow.col(navs);
					}
				}
				
				// Passing the title to title consumer stored in data
				if (effectiveSemanticContainerSectionStyle != SectionStyle.HEADER) {
					BiConsumer<ContentPanel, Tag> titleConsumer = (BiConsumer<ContentPanel, Tag>) ret.getData(TITLE_CONSUMER_KEY);
					if (titleConsumer != null) {
						titleConsumer.accept(semanticElement, title);
					}
				}
				
				Row contentRow = container.row();				
				contentRow.toHTMLElement().addClass("nsd-app-content-panel-content-row");
				
				// Content nav left
				if (semanticElement.getLeftNavigation() != null) {
					Tag leftNavigationColumn = contentRow.col().toHTMLElement();
					leftNavigationColumn.addClass("nsd-app-content-panel-left-navigation");
					navigationPanels.put(AppPackage.Literals.CONTENT_PANEL__LEFT_NAVIGATION, leftNavigationColumn);
				}
				
				Tag contentCol = contentRow.col().toHTMLElement();
				Container contentFloatsAndSectionsContainer = bootstrapFactory.fluidContainer();
				contentCol.accept(contentFloatsAndSectionsContainer);
				Tag ownContentCol = contentFloatsAndSectionsContainer.row().col().toHTMLElement();				
				
				List<Object> content = (List<Object>) input.getSecond().get(HtmlPackage.Literals.HTML_ELEMENT__CONTENT);
				if (content != null) {
					content.forEach(ownContentCol::accept);
				}
				
				
				List<HTMLElement<?>> sections;				
				if (semanticElement.getSections().isEmpty()) {
					sections = Collections.emptyList();
				} else {
					// Creating containers for sections
					SectionStyle effectivSectionStyle = effectiveSectionStyle(semanticElement);
					switch (effectivSectionStyle) {
					case ACTION_GROUP:
						sections = actionGroupSections(contentFloatsAndSectionsContainer, bootstrapFactory);				
						break;
					case CARD:
						sections = cardSections(contentFloatsAndSectionsContainer, bootstrapFactory);				
						break;
					case CARD_PILL:
						sections = cardPillSections(contentFloatsAndSectionsContainer, bootstrapFactory);				
						break;
					case CARD_TAB:
						sections = cardTabSections(contentFloatsAndSectionsContainer, bootstrapFactory);				
						break;
					case HEADER:
						// Cols in rows
						sections = headerSections(contentFloatsAndSectionsContainer);				
						break;
					case PILL:
						sections = tabSections(contentFloatsAndSectionsContainer, true, bootstrapFactory);				
						break;
					case TAB:
						sections = tabSections(contentFloatsAndSectionsContainer, false, bootstrapFactory);				
						break;
					case TABLE:
						sections = tableSections(contentFloatsAndSectionsContainer, bootstrapFactory);				
						break;
					default:
						throw new UnsupportedOperationException("Unsupported section style: " + effectivSectionStyle);
					}
				}
				
				if (semanticElement.getRightNavigation() != null) {
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

	private List<HTMLElement<?>> tabSections(Container contentFloatsAndSectionsContainer, boolean pills, BootstrapFactory bootstrapFactory) {
		Row sectionRow = contentFloatsAndSectionsContainer.row();
		sectionRow.toHTMLElement().addClass("nsd-app-content-panel-section-row");
		Tag tabsCol = sectionRow.col().toHTMLElement();
		HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
		Tag navList = htmlFactory.tag(TagName.ul).addClass("nav").addClass(pills ? "nav-pills" : "nav-tabs");
		tabsCol.accept(navList);
		Tag contentDiv = htmlFactory.div().id(htmlFactory.nextId()).addClass("tab-content");
		tabsCol.accept(contentDiv);
		List<HTMLElement<?>> sections = new ArrayList<>();
		
		for (ContentPanel section: getTarget().getSections()) {
			Tag sectionContentContainer = htmlFactory.div().addClass("tab-pane", "fade").id(htmlFactory.nextId());
			Label sTitle = section.getTitle();
			boolean isActive = sTitle != null && sTitle.isActive();
			if (isActive) {
				sectionContentContainer.addClass("show", "active");
			}
			contentDiv.accept(sectionContentContainer);
			sections.add(sectionContentContainer);
			BiConsumer<ContentPanel, Tag> titleConsumer = (sectionSemanticElement, title) -> {
				navList.accept(htmlFactory.tag(TagName.li, title).addClass("nav-item"));
				Object sectionContentContainerId = sectionContentContainer.getId();
				title
					.addClass("nav-link")
					.addClassConditional(isActive, "active")
					.id(sectionContentContainerId + "-tab")
					.attribute("data-toggle", "tab")
					.attribute("href", "#" + sectionContentContainerId)
					.attribute("role", "tab")
					.attribute("aria-controls", sectionContentContainerId)
					.attribute("aria-selected", String.valueOf(isActive));
			};
			sectionContentContainer.setData(TITLE_CONSUMER_KEY, titleConsumer);
		}
		return sections;
	}
	
	private List<HTMLElement<?>> actionGroupSections(Container contentFloatsAndSectionsContainer, BootstrapFactory bootstrapFactory) {
		Row sectionRow = contentFloatsAndSectionsContainer.row();
		sectionRow.toHTMLElement().addClass("nsd-app-content-panel-section-row", "nsd-app-content-panel-section-action-group");
		HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
		Tag actionList = htmlFactory.div().addClass("list-group", "list-group-flush").attribute("role",  "tablist");
		sectionRow.col().width(Breakpoint.DEFAULT, Size.AUTO).toHTMLElement().accept(actionList);
		Tag contentDiv = htmlFactory.div().addClass("tab-content");
		sectionRow.col().toHTMLElement().accept(contentDiv);
		List<HTMLElement<?>> sections = new ArrayList<>();
		
		boolean active = true;
		for (ContentPanel section: getTarget().getSections()) {
			Tag sectionContentContainer = htmlFactory.div().addClass("tab-pane").id(htmlFactory.nextId());
			if (active) {
				sectionContentContainer.addClass("active");
			}
			boolean isActive = active;
			contentDiv.accept(sectionContentContainer);
			sections.add(sectionContentContainer);
			BiConsumer<ContentPanel, Tag> titleConsumer = (sectionSemanticElement, title) -> {
				actionList.accept(title);
				Object sectionContentContainerId = sectionContentContainer.getId();
				title
					.addClass("list-group-item", "list-group-item-action")
					.addClassConditional(isActive, "active")
					.id(sectionContentContainerId + "-tab")
					.attribute("data-toggle", "list")
					.attribute("href", "#" + sectionContentContainerId)
					.attribute("role", "tab");
			};
			sectionContentContainer.setData(TITLE_CONSUMER_KEY, titleConsumer);
			sectionContentContainer.setData(section);
			active = false;
		}
		return sections;
	}
	
	private List<HTMLElement<?>> headerSections(Container contentFloatsAndSectionsContainer) {
		List<HTMLElement<?>> sections = new ArrayList<>();
		for (ContentPanel section: getTarget().getSections()) {
			Tag sectionCol = contentFloatsAndSectionsContainer.row().col().toHTMLElement();
			sectionCol.setData(section);
			sections.add(sectionCol);
		}
		return sections;
	}
	
	private List<HTMLElement<?>> tableSections(Container contentFloatsAndSectionsContainer, BootstrapFactory bootstrapFactory) {
		Row sectionRow = contentFloatsAndSectionsContainer.row();
		sectionRow.toHTMLElement().addClass("nsd-app-content-panel-section-row", "nsd-app-content-panel-section-table");
		Table table = bootstrapFactory.table().bordered();
		sectionRow.col(table);
		List<HTMLElement<?>> sections = new ArrayList<>();

		TableBody body = table.body();
		for (ContentPanel section: getTarget().getSections()) {
			org.nasdanika.html.bootstrap.RowContainer.Row row = body.row();
			Cell titleCell = row.cell().toHTMLElement();
			Cell contentCell = row.cell().toHTMLElement();
			contentCell.setData(section);
			sections.add(contentCell);
			BiConsumer<ContentPanel, Tag> titleConsumer = (sectionSemanticElement, title) -> {
				titleCell.accept(title);
			};
			contentCell.setData(TITLE_CONSUMER_KEY, titleConsumer);
		}
		return sections;
	}
	
	private List<HTMLElement<?>> cardTabSections(Container contentFloatsAndSectionsContainer, BootstrapFactory bootstrapFactory) {
		throw new UnsupportedOperationException();
	}

	private List<HTMLElement<?>> cardPillSections(Container contentFloatsAndSectionsContainer, BootstrapFactory bootstrapFactory) {
		throw new UnsupportedOperationException();
	}

	private List<HTMLElement<?>> cardSections(Container contentFloatsAndSectionsContainer, BootstrapFactory bootstrapFactory) {
		throw new UnsupportedOperationException();
	}

	private static SectionStyle effectiveSectionStyle(ContentPanel semanticElement) {
		SectionStyle style = semanticElement.getSectionStyle();
		if (style == null) {
			style = SectionStyle.AUTO;
		}
		
		if (style == SectionStyle.AUTO) {
			switch (depth(semanticElement)) {
			case 0:
				return SectionStyle.TAB;
			case 1:
				return SectionStyle.ACTION_GROUP;
			default:
				return SectionStyle.HEADER;
			}
		}
		
		return style;
	}
	
	private static int depth(ContentPanel semanticElement) {
		Object semanticContainer = semanticElement.eContainer();
		if (semanticContainer instanceof ContentPanel && semanticElement.eContainmentFeature() == AppPackage.Literals.CONTENT_PANEL__SECTIONS) {
			return depth((ContentPanel) semanticContainer) + 1;
		}
		return 0;
	}
	
	private static int headerDepth(ContentPanel semanticElement) {
		Object semanticContainer = semanticElement.eContainer();
		if (semanticContainer instanceof ContentPanel && semanticElement.eContainmentFeature() == AppPackage.Literals.CONTENT_PANEL__SECTIONS) {
			ContentPanel containerPanel = (ContentPanel) semanticContainer;
			int containerHeaderDepth = headerDepth(containerPanel);
			return effectiveSectionStyle(containerPanel) == SectionStyle.HEADER ? containerHeaderDepth + 1 : containerHeaderDepth;
		}
		return 0;
	}
	
	@Override
	protected List<EObject> getContent() {
		return Collections.emptyList();
	}
	
	/**
	 * Adapts items to suppliers, builds {@link Navs} from items and adds them to the header.
	 */
	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) {
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
