package org.nasdanika.html.model.app.gen;

import static org.nasdanika.common.Util.isBlank;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.emf.persistence.GitMarkerFactory;
import org.nasdanika.emf.persistence.MarkerFactory;
import org.nasdanika.emf.persistence.NcoreDrawioResourceFactory;
import org.nasdanika.emf.persistence.TextResourceFactory;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.content.Text;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.emf.SemanticProcessor;
import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.emf.NcoreActionBuilder;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.ActionReference;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentPanel;
import org.nasdanika.html.model.app.Footer;
import org.nasdanika.html.model.app.Header;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.NavigationBar;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Item;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.NcoreResourceSet;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.persistence.ObjectLoaderResourceFactory;
import org.nasdanika.resources.BinaryEntityContainer;
import org.xml.sax.SAXException;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

public final class Util {
	
	public static final String DATA_NSD_LABEL_UUID_ATTRIBUTE = "data-nsd-label-uuid";

	// Util
	private Util() {
		throw new UnsupportedOperationException("Utility class, not to be instantiated");
	}

	/**
	 * Creates Bootstrap Navs from a list of items.
	 * @param items
	 * @param bootstrapFactory
	 * @return
	 */
	public static Tag navs(List<Object> items, BootstrapFactory bootstrapFactory) {
		HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
		Tag navs = htmlFactory.tag(TagName.ul).addClass("nav");
		for (Object item: items) {
			Tag li = htmlFactory.tag(TagName.li, item).addClass("nav-item");
			navs.accept(li);
			if (item instanceof Tag) {
				Tag itemTag = (Tag) item;
				if (TagName.a.name().equalsIgnoreCase(itemTag.getTagName())) {
					itemTag.addClass("nav-link");
					Object data = itemTag.getData();
					if (data instanceof Item) {
						Item itemData = (Item) data;
						if (itemData.isActive()) {
							itemTag.addClass("active");
						} else if (itemData.isDisabled()) {
							itemTag.addClass("disabled");
						}
					}
				} else if (TagName.span.name().equalsIgnoreCase(itemTag.getTagName())) {
					itemTag.addClass("navbar-text");
				}
			}
		}
		return navs;
	}
	
	/**
	 * Creates Bootstrap Navs from a list of items.
	 * @param items
	 * @param bootstrapFactory
	 * @return
	 */
	public static Tag navbar(
			Tag brand, 
			List<Object> items,
			Breakpoint expand, 
			boolean dark,
			Color background,
			BootstrapFactory bootstrapFactory) {
		HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
		Tag navbar = htmlFactory.tag(TagName.ul).addClass("navbar", dark ? "navbar-dark" : "navbar-light");
		if (expand != null) {
			navbar.addClass("navbar-expand-" + expand.code);
		}
		if (background != null) {
			navbar.addClass("bg-" + background.code);
		}
		if (brand != null) {
			brand.addClass("navbar-brand");
			navbar.accept(brand);
		}
		String navbarContentId = htmlFactory.nextId();
		Button toggler = htmlFactory.button(htmlFactory.span().addClass("navbar-toggler-icon"));
		toggler
			.addClass("navbar-toggler")
			.attribute("type", "button")
			.attribute("data-toggle", "collapse")
			.attribute("data-target", "#" + navbarContentId)
			.attribute("aria-expanded", "false")
			.attribute("aria-label", "Toggle navigation");
		navbar.accept(toggler);
		
		Tag content = htmlFactory.div().addClass("collapse", "navbar-collapse").id(navbarContentId);
		navbar.accept(content);
		if (items != null && !items.isEmpty()) {
			Tag navs = navs(items, bootstrapFactory);
			navs.removeClass("nav").addClass("navbar-nav", "mr-auto");
			content.accept(navs);
		}
		return navbar;
	}
	
	/**
	 * Generates application site from an {@link Action} model root. 
	 * @param root Root action to generate header and footer. Can be null.
	 * @param pageTemplate Page template.
	 * @param context Context used for uri resolution - interpolation of action locations and names. can be null.
	 * @param container Receiver of generated resources.
	 * @param progressMonitor Progress monitor.
	 */
	public static void generateSite(
			Label root, 
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			Container container,
			ActionContentProvider.Factory actionContentProviderFactory,
			PageContentProvider.Factory pageContentProviderFactory,
			Context context,
			ProgressMonitor progressMonitor) {
	
		Label principal = null;
		EList<EObject> rootChildren = root.getChildren();
		if (rootChildren.size() > 0) {
			EObject firstChild = root.getChildren().get(0);
			if (firstChild instanceof Action) {
				principal = (Action) firstChild;
			} else if (firstChild instanceof ActionReference) {
				principal = ((ActionReference) firstChild).getTarget();
			}
		}
		
		generateSite(root, principal, root, Collections.emptyList(), pageTemplate, container, actionContentProviderFactory, pageContentProviderFactory, context, progressMonitor);
	}
	
	/**
	 * Generates application site from an {@link Action} model using a random base URI and a caching URI resolver created from the argument context with base-uri injected.
	 * @param root Root action to generate header and footer. Can be null.
	 * @param principal Principal action to generate navigation bar and navigation panel. Can be null.
	 * @param activeAction Active action to generate the content panel if instance of action.
	 * @param actionPath Action path to show in breadcrumbs.
	 * @param pageTemplate Page template.
	 * @param context Context used for uri resolution - interpolation of action locations and names. can be null.
	 * @param container Receiver of generated resources.
	 * @param progressMonitor Progress monitor.
	 */
	public static void generateSite(
			Label root, 
			Label principal, 
			Label activeAction, 
			List<Label> actionPath,
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			Container container,
			ActionContentProvider.Factory contentProviderFactory,
			PageContentProvider.Factory pageProviderFactory,
			Context context,
			ProgressMonitor progressMonitor) {
	
		if (context == null) {
			context = Context.EMPTY_CONTEXT;
		}
		if (activeAction instanceof Action) {
			context = Context.singleton(Action.class, (Action) activeAction).compose(context);
		}
		if (context.get(Context.BASE_URI_PROPERTY) == null) {
        	context = Context.singleton(Context.BASE_URI_PROPERTY, URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/")).compose(context);
		}
		BiFunction<Label, URI, URI> uriResolver = org.nasdanika.html.model.app.util.Util.uriResolver(root, context);		
		generateSite(
				root, 
				principal, 
				activeAction,
				actionPath,
				pageTemplate,
				uriResolver, 
				(URI) context.get(Context.BASE_URI_PROPERTY), 
				container, 
				contentProviderFactory == null ? null : contentProviderFactory.create(context),
				pageProviderFactory == null ? null : pageProviderFactory.create(context),						
				progressMonitor);
	}
	
	/**
	 * Generates application site from an {@link Action} model
	 * @param root Root action to generate header and footer. Can be null.
	 * @param principal Principal action to generate navigation bar and navigation panel. Can be null.
	 * @param activeAction Active action to generate the content panel.
	 * @param pageTemplate Page template.
	 * @param uriResolver Resolves {@link Action} to {@link URI}.
	 * @param baseURI Base URI for resolution, specifically to create relative URI's.
	 * @param container Receiver of generated resources.
	 * @param progressMonitor Progress monitor.
	 */
	public static void generateSite(
			Label root, 
			Label principal, 
			Label activeAction, 
			List<Label> actionPath,
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			BiFunction<Label, URI, URI> uriResolver, 
			URI baseURI,
			Container container,
			ActionContentProvider actionContentProvider,	
			PageContentProvider pageContentProvider,
			ProgressMonitor progressMonitor) {
		
		if (activeAction instanceof Action && activeAction.eContainmentFeature() != AppPackage.Literals.ACTION__SECTIONS) {
			URI uri = uriResolver.apply(activeAction, baseURI);				
			if (uri != null && uri.isRelative()) {
				org.nasdanika.exec.resources.File file = container.getFile(uri.toString());
				if (file != null) {
					org.nasdanika.html.model.bootstrap.Page bootstrapPage = EcoreUtil.copy(pageTemplate);
					bootstrapPage.setName(activeAction.getText());
					if (bootstrapPage.getBody().isEmpty()) {
						bootstrapPage.getBody().add(AppFactory.eINSTANCE.createPage());
					}
					for (EObject be: bootstrapPage.getBody()) {
						if (be instanceof org.nasdanika.html.model.app.Page) {
							buildAppPage(root, principal, (Action) activeAction, actionPath, (org.nasdanika.html.model.app.Page) be, uriResolver, actionContentProvider, progressMonitor);						
						}
					}
					if (pageContentProvider == null) {
						file.getContents().add(bootstrapPage);
					} else {
						file.getContents().addAll(pageContentProvider.getPageContent(bootstrapPage, baseURI, uriResolver, progressMonitor));						
					}
					
					if (activeAction instanceof Action) {
						for (org.nasdanika.exec.resources.Resource res: ((Action) activeAction).getResources()) {
							((Container) file.eContainer()).getContents().add(EcoreUtil.copy(res));					
						}
					}
				}
			}
		}
		
		List<Label> subActionPath = new ArrayList<>(actionPath);
		if (activeAction != root && activeAction != principal) {
			subActionPath.add(activeAction);
		}
		
		for (EObject child: org.nasdanika.html.model.app.util.Util.resolveActionReferences(activeAction.getChildren())) {
			if (child instanceof Label) {
				generateSite(root, principal, (Label) child, subActionPath, pageTemplate, uriResolver, baseURI, container, actionContentProvider, pageContentProvider, progressMonitor);
			}
		}
				
		if (activeAction instanceof Action) {
			Action theActiveAction = (Action) activeAction;
			for (Action section: theActiveAction.getSections()) {
				generateSite(root, principal, (Action) section, subActionPath, pageTemplate, uriResolver, baseURI, container, actionContentProvider, pageContentProvider, progressMonitor);
			}
			
			for (EObject navigation: org.nasdanika.html.model.app.util.Util.resolveActionReferences(theActiveAction.getNavigation())) {
				if (navigation instanceof Label) {
					generateSite(root, principal, (Label) navigation, subActionPath, pageTemplate, uriResolver, baseURI, container, actionContentProvider, pageContentProvider, progressMonitor);
				}
			}
			
			for (Action anonymous: theActiveAction.getAnonymous()) {
				generateSite(root, principal, anonymous, subActionPath, pageTemplate, uriResolver, baseURI, container, actionContentProvider, pageContentProvider, progressMonitor);
			}
			
			generateNavigationPanel(root, principal, actionPath, theActiveAction.getFloatLeftNavigation(), pageTemplate, uriResolver, baseURI, container, actionContentProvider,  pageContentProvider,progressMonitor);
			generateNavigationPanel(root, principal, actionPath, theActiveAction.getFloatRightNavigation(), pageTemplate, uriResolver, baseURI, container, actionContentProvider,  pageContentProvider,progressMonitor);
			generateNavigationPanel(root, principal, actionPath, theActiveAction.getLeftNavigation(), pageTemplate, uriResolver, baseURI, container, actionContentProvider,  pageContentProvider,progressMonitor);
			generateNavigationPanel(root, principal, actionPath, theActiveAction.getRightNavigation(), pageTemplate, uriResolver, baseURI, container, actionContentProvider,  pageContentProvider,progressMonitor);
		}
	}
	
	private static void buildAppPage(
			Label root, 
			Label principal, 
			Action activeAction, 
			List<Label> actionPath,
			org.nasdanika.html.model.app.Page appPage,
			BiFunction<Label, URI, URI> uriResolver, 
			ActionContentProvider contentProvider,			
			ProgressMonitor progressMonitor) {
		
		if (root != null) {
			Label title = createLabel(root, activeAction, uriResolver, null, "header/title", false, false, false);
			EList<EObject> rootChildren = root.getChildren();
			if (title != null || rootChildren.size() > 1) {
				// Header
				Header header = appPage.getHeader();
				if (header == null) {
					header = AppFactory.eINSTANCE.createHeader();
					appPage.setHeader(header);
				}
				if (title != null) {
					header.setTitle(title);
				}
				List<EObject> headerItems = header.getItems();
				rootChildren.listIterator(1).forEachRemaining(rac -> {
					if (rac instanceof Label) {
						headerItems.add(createLabel((Label) rac, activeAction, uriResolver, null, "header/navigation", true, false, false));
					} else {
						headerItems.add(EcoreUtil.copy(rac));
					}
				});
			}
			
			if (root instanceof Action) {
				List<EObject> rootNavigation = org.nasdanika.html.model.app.util.Util.resolveActionReferences(((Action) root).getNavigation());
				if (!rootNavigation.isEmpty()) {
					Footer footer = appPage.getFooter();
					if (footer == null) {
						footer = AppFactory.eINSTANCE.createFooter();
						appPage.setFooter(footer);
					}
					EList<EObject> footerItems = footer.getItems();
					rootNavigation.forEach(ran -> {
						if (ran instanceof Label) {
							footerItems.add(createLabel((Label) ran, activeAction, uriResolver, null, "footer/navigation", true, false, false));
						} else {
							footerItems.add(EcoreUtil.copy(ran));
						}
					});
					
				}
			}
		}
		
		if (principal != null) {
			// Navbar 
			Label brand = createLabel(principal, activeAction, uriResolver, null, "navbar/brand", false, false, false);
			if (principal instanceof Action) {
				List<EObject> principalNavigation = org.nasdanika.html.model.app.util.Util.resolveActionReferences(((Action) principal).getNavigation());
				if (brand != null || !principalNavigation.isEmpty()) {
					NavigationBar navBar = appPage.getNavigationBar();
					if (navBar == null) {
						navBar = AppFactory.eINSTANCE.createNavigationBar();
						appPage.setNavigationBar(navBar);
					}
					if (brand != null) {
						navBar.setBrand(brand);
					}
					EList<EObject> navBarItems = navBar.getItems();
					principalNavigation.forEach(principalNavigationElement -> {
						if (principalNavigationElement instanceof Label) {
							navBarItems.add(createLabel((Label) principalNavigationElement, activeAction, uriResolver, null, "navbar/item", true, false, false));
						} else {
							navBarItems.add(EcoreUtil.copy(principalNavigationElement));
						}
					});
				}
			}
			
			// Navigation panel
			List<EObject> principalChildren = org.nasdanika.html.model.app.util.Util.resolveActionReferences(principal.getChildren());
			if (!principalChildren.isEmpty()) {
				NavigationPanel navPanel = appPage.getNavigationPanel();
				if (navPanel == null) {
					navPanel = AppFactory.eINSTANCE.createNavigationPanel();
					appPage.setNavigationPanel(navPanel);
				}
				if (isBlank(navPanel.getId())) {
					navPanel.setId(principal.getId() + "-navigation-panel");
				}
				Function<Label, String> navItemIdProvider = na -> isBlank(na.getId()) ? null : "nsd-app-nav-item-" + na.getId();
				List<EObject> navPanelItems = navPanel.getItems();
				principalChildren.forEach(principalChild -> {
					if (principalChild instanceof Label) {
						navPanelItems.add(createLabel((Label) principalChild, activeAction, uriResolver, navItemIdProvider, "nav-panel", true, true, false));
					} else {
						navPanelItems.add(EcoreUtil.copy(principalChild));
					}
				});
				
			}
		}
		
		// Content panel
		ContentPanel contentPanel = appPage.getContentPanel();
		if (contentPanel == null) {
			contentPanel = AppFactory.eINSTANCE.createContentPanel();
			appPage.setContentPanel(contentPanel);
		}
		if  (activeAction != null) {
			String activeActionUUID = activeAction.getUuid();
			if (!org.nasdanika.common.Util.isBlank(activeActionUUID)) {
				Text text = ContentFactory.eINSTANCE.createText();
				text.setContent(activeActionUUID);
				contentPanel.getAttributes().put(DATA_NSD_LABEL_UUID_ATTRIBUTE, text);
			}
		}
		
		buildContentPanel(activeAction, actionPath, activeAction == root || activeAction == principal, contentPanel, uriResolver, contentProvider, progressMonitor);	
	}
	
	private static void buildContentPanel(
			Action action, 
			List<Label> path,
			boolean rootOrPrincipal,
			ContentPanel contentPanel,
			BiFunction<Label, URI, URI> uriResolver, 
			ActionContentProvider contentProvider,
			ProgressMonitor progressMonitor) {
		
		if (!rootOrPrincipal) {
			if (path != null && !path.isEmpty()) {
				EList<Label> breadcrumb = contentPanel.getBreadcrumb();
				path.forEach(pathElement -> {
					Label element = createLabel(pathElement, action, uriResolver, null, "content-panel/breadcrumb", false, false, false);
					if (element != null) {
						breadcrumb.add(element);
					}
				});
				Label tail = createLabel(action, action, uriResolver, null, "content-panel/breadcrumb", false, false, false);		
				tail.setActive(true);
				breadcrumb.add(tail);
			}
	
			if (!isBlank(action.getText()) || !isBlank(action.getIcon())) {
				Label title = org.nasdanika.common.Util.isBlank(action.getName()) ? AppFactory.eINSTANCE.createLabel() : AppFactory.eINSTANCE.createLink(); 
				configureLabel(action, action, uriResolver, null, "content-panel/title", title, false, false, true);
				contentPanel.setTitle(title);
			}
					
			List<EObject> navigation = org.nasdanika.html.model.app.util.Util.resolveActionReferences(action.getNavigation());
			EList<EObject> navigationItems = contentPanel.getItems();
			navigation.forEach(navigationElement -> {
				if (navigationElement instanceof Label) {
					navigationItems.add(createLabel((Label) navigationElement, action, uriResolver, null, "content-panel/navigation-item", true, false, false));
				} else {
					navigationItems.add(EcoreUtil.copy(navigationElement));
				}
			});
		}
		
		int sectionColumns = action.getSectionColumns();
		if (sectionColumns > 0) {
			contentPanel.setSectionColumns(sectionColumns);
		}
		SectionStyle sectionStyle = action.getSectionStyle();
		if (sectionStyle != null) {
			contentPanel.setSectionStyle(sectionStyle);
		}
		
		EList<ContentPanel> cpSections = contentPanel.getSections();
		for (Action section: action.getSections()) {
			ContentPanel sectionPanel = AppFactory.eINSTANCE.createContentPanel();
			cpSections.add(sectionPanel);
			buildContentPanel(section, null, false, sectionPanel, uriResolver, contentProvider, progressMonitor);
		}

		contentPanel.setFloatLeftNavigation(createNavigationPanel(action.getFloatLeftNavigation()));
		contentPanel.setFloatRightNavigation(createNavigationPanel(action.getFloatRightNavigation()));
		contentPanel.setLeftNavigation(createNavigationPanel(action.getLeftNavigation()));		
		contentPanel.setRightNavigation(createNavigationPanel(action.getRightNavigation()));
		
		contentPanel.getContent().addAll(contentProvider == null ? EcoreUtil.copyAll(action.getContent()) : contentProvider.getActionContent(action, uriResolver, progressMonitor));
		
		List<URI> actionURIs = NcoreUtil.getIdentifiers(action);
		if (!actionURIs.isEmpty()) {
			String urisStr = String.join(" ", actionURIs.stream().map(Object::toString).collect(Collectors.toList()));
			contentPanel.getAttributes().put("data-nsd-action-uris", NcoreUtil.wrapString(urisStr));
		}
	}
	
	/**
	 * Creates a copy of a panel if not null and replaces action references with links.
	 * @param panel
	 * @return
	 */
	private static NavigationPanel createNavigationPanel(NavigationPanel panel) {
		if (panel == null) {
			return null;
		}
		NavigationPanel ret = EcoreUtil.copy(panel);
		ListIterator<EObject> iit = ret.getItems().listIterator();
		while (iit.hasNext()) {
			EObject next = iit.next();
			if (next instanceof ActionReference) {
				iit.set(EcoreUtil.copy(((ActionReference) next).getTarget()));
			}
		}
		return ret;
	}

	private static boolean isLink(Label label, URI uri) {
		if (uri != null) {
			return true;
		}
		
		if (label instanceof Link) {
			Link link = (Link) label;
			return !isBlank(link.getLocation()) || !isBlank(link.getScript()) || link.getModal() != null || !isBlank(link.getName());
		}
		
		return false;
	}
	
	/**
	 * @param action
	 * @param activeAction
	 * @return true if this action is active or one of its anonymous or navigation descendants is active.
	 */
	private static boolean isActiveAction(Action action, Label activeAction, boolean inspectChildren) {
		if (action == activeAction) {
			return true;
		}
		
		for (Action ac: action.getAnonymous()) {
			if (isActiveAction(ac, activeAction, inspectChildren)) {
				return true;
			}
		}
		
		for (Action ac: action.getSections()) {
			if (isActiveAction(ac, activeAction, inspectChildren)) {
				return true;
			}
		}		
		
		for (EObject nc: action.getNavigation()) {
			if (nc instanceof Action && isActiveAction((Action) nc, activeAction, inspectChildren)) {
				return true;
			}
		}
		
		if (inspectChildren) {
			for (EObject child: action.getChildren()) {
				if (child instanceof Action && isActiveAction((Action) child, activeAction, inspectChildren)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private static void configureLabel(
			Label source, 
			Label activeAction, 
			BiFunction<Label, URI, URI> uriResolver, 
			Function<Label, String> idProvider, 
			String appearancePath, 
			Label label, 
			boolean recursive,
			boolean inNavPanel,
			boolean decorate) {
		Appearance aa = source.getAppearance();
		if (aa != null) {
			label.setAppearance(aa.effectiveAppearance(appearancePath));
		}

		label.setColor(source.getColor());
		label.setDescription(source.getDescription());
		label.setDisabled(source.isDisabled());
		Label decorator = source.getDecorator();
		if (decorator != null && decorate) {
			label.setDecorator(EcoreUtil.copy(decorator));
		}
		label.setIcon(source.getIcon());
		if (idProvider != null) {
			label.setId(idProvider.apply(source));
		}
//		label.setId(action.getId());		
		label.setNotification(source.getNotification());
		label.setOutline(source.isOutline());
		label.setText(source.getText());
		label.setTooltip(source.getTooltip());

		Action effectiveSourceAction = null;
		if (source instanceof Action) {
			effectiveSourceAction = (Action) source;
		} else if (source instanceof Link) {
			effectiveSourceAction = ((Link) source).getAction();
		}				
				
		if (effectiveSourceAction != null) {
			String sourceUUID = effectiveSourceAction.getUuid();
			if (!org.nasdanika.common.Util.isBlank(sourceUUID)) {
				Text text = ContentFactory.eINSTANCE.createText();
				text.setContent(sourceUUID);			
				label.getAttributes().put(DATA_NSD_LABEL_UUID_ATTRIBUTE, text);
			}
		}
		
		for (Entry<String, EObject> ae: source.getAttributes().entrySet()) {		
			label.getAttributes().put(ae.getKey(), EcoreUtil.copy(ae.getValue()));
		}
		
		if (label instanceof Link) {
			configureLink(source, activeAction, uriResolver, (Link) label);
		}
		
		if (recursive) {
			EList<EObject> labelChildren = label.getChildren();
			for (EObject sourceChild: source.getChildren()) {
				if (sourceChild instanceof ActionReference) {
					sourceChild = ((ActionReference) sourceChild).getTarget();
				}
				if (sourceChild instanceof Label) {
					Label childLabel = (Label) sourceChild;
					labelChildren.add(createLabel(childLabel, activeAction, uriResolver, idProvider, "header/navigation", recursive, inNavPanel, false));
					
					// Second level - headers, separators.
				} else {
					labelChildren.add(EcoreUtil.copy(sourceChild));
				}							
			}			
		}
		
		label.setActive(effectiveSourceAction != null && inNavPanel ? isActiveAction(effectiveSourceAction, activeAction, !hasActiveChildren(label)) && !hasActiveChildren(source, activeAction) : source.isActive());		
	}
	
	private static boolean hasActiveChildren(Label label, Label activeAction) {
		for (EObject child: label.getChildren()) { 
			if (child instanceof Label) { 
				Label cl = (Label) child; 
				if (cl instanceof Link) {
					Action linkAction = ((Link) cl).getAction();
					if (linkAction != null && isActiveAction(linkAction, activeAction, true)) {
						return true;
					}
					if (hasActiveChildren(cl, activeAction)) {
						return true;
					}
				}
			}
		}
		return false;
	}	
	
	private static boolean hasActiveChildren(Label label) {
		for (EObject child: label.getChildren()) {
			if (child instanceof Label) {
				Label cl = (Label) child;
				if (cl.isActive() || hasActiveChildren(cl)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static void configureLink(
			Label source, 
			Label activeAction, 
			BiFunction<Label, URI, URI> uriResolver, 
			Link target) {
		
		URI activeActionURI = uriResolver.apply(activeAction, null);
		Label uriSource = source;
		if (uriSource instanceof Link) {
			Action linkAction = ((Link) uriSource).getAction();
			if (linkAction != null) {
				uriSource = linkAction;
			}
		}
		URI uri = uriResolver.apply(uriSource, activeActionURI);		
		
		if (uri != null) {
			target.setLocation(uri.toString());
		}
		if (source instanceof Link) {
			Link sourceLink = (Link) source;
			target.setConfirmation(sourceLink.getConfirmation());
			if (sourceLink.getModal() != null) {
	//		link.setModal(value); TODO - contextualization of links - relativization of URI's. Token expansion with a property computer? something like ${relative-uri/<uri here>}? 
	//		Support of sub-tokens e.g. ${{relative-uri/${token}}} recognizes ${token} as a sub-token to be expanded to result in ${relative-uri/<token value>}.
				throw new UnsupportedOperationException("Modals are not supported yet");
			}
			target.setName(sourceLink.getName());
			target.setScript(sourceLink.getScript());
			target.setTarget(sourceLink.getTarget());	
			if (org.nasdanika.common.Util.isBlank(target.getLocation())) {
				target.setLocation(sourceLink.getLocation());
			}
		}
		if (source instanceof Action) {
			Action action = (Action) source;
			if (action.isModalActivator()) {
				throw new UnsupportedOperationException("Modals are not supported yet");
			}
			if (action.isInline()) {
				throw new UnsupportedOperationException("Inline actions are not supported yet");			
			}
		}
	}
	
	/**
	 * Creates a {@link Link} from {@link Action} with a relative location for locations.
	 * @param source
	 * @param uri
	 * @param active 
	 * @param appearancePath 
	 * @return
	 */
	public static Label createLabel(
			Label source, 
			Label activeAction, 
			BiFunction<Label, URI, URI> uriResolver, 
			Function<Label, String> idProvider, 
			String appearancePath,
			boolean recursive,
			boolean inNavPanel,
			boolean decorate) {
		
		URI activeActionURI = uriResolver.apply(activeAction, null);
		
		Label uriSource = source;
		if (uriSource instanceof Link) {
			Action linkAction = ((Link) uriSource).getAction();
			if (linkAction != null) {
				uriSource = linkAction;
			}
		}
		URI uri = uriResolver.apply(uriSource, activeActionURI);		
		
		if (isBlank(source.getText()) && isBlank(source.getIcon()) && !recursive) {
			return null;
		}
		Label label = source != activeAction && isLink(source, uri) ? AppFactory.eINSTANCE.createLink() : AppFactory.eINSTANCE.createLabel();
		configureLabel(source, activeAction, uriResolver, idProvider, appearancePath, label, recursive, inNavPanel, decorate);
				
		return label;
	}
	
	private static void generateNavigationPanel(
			Label root, 
			Label principal, 
			List<Label> actionPath,
			NavigationPanel navigationPanel,
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			BiFunction<Label, URI, URI> uriResolver, 
			URI baseURI, 
			Container container,
			ActionContentProvider actionContentProvider,
			PageContentProvider pageContentProvider,
			ProgressMonitor progressMonitor) {
		
		if (navigationPanel != null) {
			for (EObject item: org.nasdanika.html.model.app.util.Util.resolveActionReferences(navigationPanel.getItems())) {
				if (item instanceof Label) {
					generateSite(root, principal, (Label) item, actionPath, pageTemplate, uriResolver, baseURI, container, actionContentProvider, pageContentProvider, progressMonitor);
				}
			}			
		}
	}
	
	public static JSONObject createSearchDocument(String path, File file, Consumer<Exception> errorConsumer) throws IOException {
		return createSearchDocument(path, file, null, null, errorConsumer);
	}
	
	/**
	 * For Nasdanika App pages extracts page title, breadcrumbs and content text into a JSONObject to add to a collector and then
	 * to use for constructing search indices. 
	 * @param path
	 * @param file
	 * @param contentConsumer Consumer of content elements which can be used to analyze page contents, e.g. find dead links. Can be null.  
	 * @param processor If not null the document is passed to the processor. If the processor returns true that means that the document was manipulated by the processor and shall be saved to the source file.
	 * @return Search document object for non-empty Nasdanika App pages, null otherwise.
	 * @throws IOException
	 */
	public static JSONObject createSearchDocument(
			String path, 
			File file, 
			Consumer<? super Element> contentConsumer, 
			BiFunction<String, Document, Boolean> processor,
			Consumer<Exception> errorConsumer) throws IOException {
		
		Document document = Jsoup.parse(file, "UTF-8");
		if (processor != null && processor.apply(path, document)) {
			Document.OutputSettings outputSettings = new Document.OutputSettings();
			outputSettings.prettyPrint(false);
			document.outputSettings(outputSettings);
			try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
				writer.write(document.html());
			}
		}
		Elements contentPanelQuery = document.select("body > div > div.row.nsd-app-content-row > div.col.nsd-app-content-panel");							                                              
		if (contentPanelQuery.isEmpty()) {
			return null;
		}
		Elements contentQuery = contentPanelQuery.select("div > div.row.nsd-app-content-panel-content-row");
		if (contentQuery.isEmpty()) {
			return null;
		}
		if (contentConsumer != null) {
			contentQuery.forEach(contentConsumer);
		}
		
		StringBuilder contentText = new StringBuilder(contentQuery.text());
		
		// Drawio diagrams labels
		for (Element diagramDiv: contentQuery.select("div.mxgraph")) {
			try {
				traverseDrawio(diagramDiv, element -> {
					if (element instanceof ModelElement) {
						ModelElement me = (ModelElement) element;
						String meLabel = me.getLabel();
						if (!org.nasdanika.common.Util.isBlank(meLabel)) {
							String labelText = Jsoup.parse(meLabel).text();
							contentText.append(" ").append(labelText);											
						}
						String meTooltip = me.getTooltip();
						if (!org.nasdanika.common.Util.isBlank(meTooltip)) {
							String tooltipText = Jsoup.parse(meTooltip).text();
							contentText.append(" ").append(tooltipText);											
						}
					}
				}, null);
			} catch (Exception e) {
				if (errorConsumer == null) {
					e.printStackTrace();
				} else {
					errorConsumer.accept(e);
				}
			}
		}
		
		if (org.nasdanika.common.Util.isBlank(contentText.toString())) {
			return null;
		}
		
		JSONObject searchDocument = new JSONObject();
		searchDocument.put("content", StringEscapeUtils.escapeHtml4(contentText.toString()));
		Elements titleQuery = contentPanelQuery.select("div > div.row.nsd-app-content-panel-title-and-items-row > div.col-auto > h1");
		if (titleQuery.size() == 1) {
			searchDocument.put("title", StringEscapeUtils.escapeHtml4(titleQuery.get(0).text()));
		} else {
			searchDocument.put("title", document.title());
		}
		Elements breadcrumbQuery = contentPanelQuery.select("div > div.row.nsd-app-content-panel-breadcrumb-row > div > nav > ol > li");
		if (breadcrumbQuery.size() > 0) {
			searchDocument.put("path", String.join("/", breadcrumbQuery.stream().map(e -> StringEscapeUtils.escapeHtml4(e.text())).collect(Collectors.toList())));
		}
		for (Element element: contentPanelQuery) {
			String actionUUID = element.attr(DATA_NSD_LABEL_UUID_ATTRIBUTE);
			if (!org.nasdanika.common.Util.isBlank(actionUUID)) {
				searchDocument.put(NcoreActionBuilder.ACTION_UUID_KEY, actionUUID);
				break;
			}
		}
		
		return searchDocument;
	}
	
	/**
	 * Loads {@link mxGraphModel} from mxgraph div.
	 * @param mxGraphDiv
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public static org.nasdanika.drawio.Document loadDrawioDocument(Element mxGraphDiv) throws ParserConfigurationException, SAXException, IOException {
		String data = mxGraphDiv.attr("data-mxgraph");
		if (data != null) {
			JSONObject jsonData = new JSONObject(data);
			Object xml = jsonData.get("xml");
			if (xml instanceof String) {
				return org.nasdanika.drawio.Document.load((String) xml, null);
			}
		}
		return null;
	}

	public static void traverseDrawio(Element mxGraphDiv, Consumer<org.nasdanika.drawio.Element> visitor, ConnectionBase connectionBase) throws ParserConfigurationException, SAXException, IOException {
		org.nasdanika.drawio.Document document = loadDrawioDocument(mxGraphDiv);
		if (document != null) {
			document.accept(visitor, connectionBase);
		}		
	}
		
	/**
	 * Loads document, traverses all elements and then saves and returns the document.
	 * @param spec
	 * @param visitor
	 * @param connectionBase
	 * @param compress
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws TransformerException 
	 */
	public static String filterDrawio(
			String spec, 
			Consumer<org.nasdanika.drawio.Element> visitor, 
			ConnectionBase connectionBase, 
			Boolean compress) throws ParserConfigurationException, SAXException, IOException, TransformerException {
		
		org.nasdanika.drawio.Document document = org.nasdanika.drawio.Document.load(spec, null);
		if (document != null) {
			document.accept(visitor, connectionBase);
		}	
		return document.save(compress);
	}
	
	/**
	 * Creates an inspector consumer which finds and reports broken links, missing images and elements with nsd-error class.
	 * @param file
	 * @param path
	 * @param siteDir
	 * @param errorConsumer
	 * @return
	 */
	public static Consumer<? super Element> createInspector(Predicate<String> linkPredicate, Consumer<String> errorConsumer) {		
		return element -> {
			Elements anchors = element.getElementsByTag("a");
			for (Element anchor: anchors) {
				String target = anchor.attr("href");
				if (target != null) {
					String id = anchor.attr("id");
					boolean selfReferencing = id != null && target.equals("#" + id); // Ignoring header tags generated by markdown.
					if (!selfReferencing && !linkPredicate.test(target)) {
						errorConsumer.accept("Broken link: " + anchor);
					}
				}
			}
			
			// Images
			Elements images = element.getElementsByTag("img");
			for (Element img: images) {
				String src = img.attr("src");
				if (src != null && !linkPredicate.test(src)) {
					errorConsumer.accept("Missing image: " + img);
				}
			}
			
			// NSD errors
			Elements errors = element.select("div.nsd-error");
			for (Element error: errors) {
				errorConsumer.accept("Error: " + error.text());
			}
			
			// Image maps
			for (Element area: element.select("area")) {
				String target = area.attr("href");
				if (target != null && !linkPredicate.test(target)) {
					errorConsumer.accept("Broken link: " + area);					
				}
			}
			
			// Drawio diagrams
			for (Element diagramDiv: element.select("div.mxgraph")) {
				try {
					traverseDrawio(diagramDiv, docElement -> {
						if (docElement instanceof org.nasdanika.drawio.ModelElement) {
							org.nasdanika.drawio.ModelElement modelElement = (org.nasdanika.drawio.ModelElement) docElement;
							String link = modelElement.getLink();
							if (!org.nasdanika.common.Util.isBlank(link) && !linkPredicate.test(link)) {
								String label = modelElement.getLabel();
								if (org.nasdanika.common.Util.isBlank(label)) {
									errorConsumer.accept("Broken diagram link: " + link);
								} else {
									errorConsumer.accept("Broken diagram link on " + label + ": " + link);											
								}
							}
						}
					}, null);
				} catch (Exception e) {
					errorConsumer.accept("Error traversing drawio " + diagramDiv + ": " + e);
				}
			}			
		};
	}

	/**
	 * Creates a predicate checking for links relative to the argument file in the argument directory. 
	 * @param file Base file
	 * @param dir Directory within which check for relative links
	 * @return
	 */
	public static Predicate<String> createRelativeLinkPredicate(File file, File dir) {
		return target -> {
			File targetFile;
			String fragment;
			if (target.startsWith("#")) {
				targetFile = file;
				fragment = target;
			} else {
				int hashIdx = target.indexOf("#");
				if (hashIdx == -1) {
					fragment = null;
				} else {
					fragment = target.substring(hashIdx);
					target = target.substring(0, hashIdx);
				}
				if (target.contains(":") || target.contains("?")) { // Absolute or with a query string, not checking
					return true;
				}
				targetFile = new File(file.getParentFile(), target);
				for (File parent = targetFile.getParentFile(); parent != null; parent = parent.getParentFile()) {
					if (parent.equals(dir)) {
						if (!targetFile.exists()) {
							return false;
						}
					}
				}
			}
			if (fragment == null || fragment.trim().length() == 0) {
				return true;
			}
			
			try {
				Document document = Jsoup.parse(targetFile, StandardCharsets.UTF_8.name());
				Elements namedAnchors = document.select("[name='" + fragment.substring(1) + "']");
				Elements idAnchors = document.select("[id='" + fragment.substring(1) + "']");
				return namedAnchors.size() + idAnchors.size() > 0; // Ignoring duplicate fragment anchors
			} catch (IOException e) {
				throw new NasdanikaException("Error parsing " + targetFile.getAbsolutePath(), e);
			}
			
		};
		
	}

	/**
	 * Inserts search json and search script tags into the document.
	 * @param path
	 * @param doc
	 * @return
	 */
	public static boolean configureSearch(
			String path, 
			Document doc,
			String searchDocumentsPath,
			Supplier<InputStream> searchScriptSupplier) {
		Element head = doc.head();
		URI base = URI.createURI("temp://" + UUID.randomUUID() + "/");
		URI searchScriptURI = URI.createURI(searchDocumentsPath).resolve(base);
		URI thisURI = URI.createURI(path).resolve(base);
		URI relativeSearchScriptURI = searchScriptURI.deresolve(thisURI, true, true, true);
		head.append(System.lineSeparator() + "<script src=\"" + relativeSearchScriptURI + "\"></script>" + System.lineSeparator());
		head.append(System.lineSeparator() + "<script src=\"https://unpkg.com/lunr/lunr.js\"></script>" + System.lineSeparator());
				
		try (InputStream in = searchScriptSupplier.get()) {
			head.append(System.lineSeparator() + "<script>" + System.lineSeparator() + DefaultConverter.INSTANCE.toString(in) + System.lineSeparator() + "</script>" + System.lineSeparator());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * Inserts search json and search script tags into the document. Uses search.js resource for the search script.
	 * @param path
	 * @param doc
	 * @return
	 */
	public static boolean configureSearch(
			String path, 
			Document doc,
			String searchDocumentsPath) {
		return configureSearch(
				path, 
				doc, 
				searchDocumentsPath, 
				() -> Util.class.getResourceAsStream("search.js"));
	}
	
	/**
	 * Generates sitemap and search index for lunrjs by scanning files in a directory.
	 * @param dir Directory to scan
	 * @param base URI prefix for file paths with or without dangling /  
	 * @throws IOException
	 */
	public static JSONObject generateSitemapAndSearch(
			File dir,
			String domain,
			BiPredicate<File, String> siteMapPredicate,
			ChangeFreq changeFrequency,
			BiPredicate<File, String> searchPredicate,
			BiConsumer<String,String> errorConsumer,
			BiFunction<String, Document, Boolean> searchConfigurator) throws IOException {
		
		// Site map and search index
		JSONObject searchDocuments = new JSONObject();
		
		WebSitemapGenerator wsg = org.nasdanika.common.Util.isBlank(domain) ? null : new WebSitemapGenerator(domain, dir);
		boolean[] isSiteMapEmpty = { true };
		BiConsumer<File, String> listener = new BiConsumer<File, String>() {
			
			@Override
			public void accept(File file, String path) {
				if (wsg != null && (siteMapPredicate == null || siteMapPredicate.test(file, path))) {
					try {
						WebSitemapUrl url = new WebSitemapUrl.Options(domain + "/" + path).lastMod(new Date(file.lastModified())).changeFreq(changeFrequency).build();
						wsg.addUrl(url); 
						isSiteMapEmpty[0] = false;
					} catch (MalformedURLException e) {
						throw new NasdanikaException(e);
					}
				}
				
				if (searchPredicate.test(file, path)) {
					try {
						Predicate<String> predicate = createRelativeLinkPredicate(file, dir);						
						Consumer<? super Element> inspector = createInspector(predicate, error -> errorConsumer.accept(path, error));
						
						JSONObject searchDocument = createSearchDocument(
								path, 
								file, 
								inspector, 
								searchConfigurator, 
								e -> errorConsumer.accept(path, "Error creating search document: " + e));
						if (searchDocument == null) {
							errorConsumer.accept(path, "Blank page");
						} else {
							searchDocuments.put(path, searchDocument);
						}
					} catch (IOException e) {
						throw new NasdanikaException(e);
					}
				}
			}
		};
		org.nasdanika.common.Util.walk(null, listener, dir.listFiles());
		if (!isSiteMapEmpty[0]) {
			wsg.write();
		}
		return searchDocuments;
	}
	
	public static JSONObject generateSitemapAndSearch(
			File dir,
			String domain,
			BiPredicate<File, String> siteMapPredicate,
			ChangeFreq changeFrequency,
			BiPredicate<File, String> searchPredicate,
			BiConsumer<String,String> errorConsumer,
			String searchDocumentsPath,
			Supplier<InputStream> searchScriptSupplier) throws IOException {
		
		return generateSitemapAndSearch(
				dir, 
				domain, 
				siteMapPredicate, 
				changeFrequency, 
				searchPredicate, 
				errorConsumer, 
				(path, doc) -> configureSearch(path, doc, searchDocumentsPath, searchScriptSupplier));
	}
		
	/**
	 * Generates sitemap and search using search.js resource.
	 * @param dir
	 * @param domain
	 * @param siteMapPredicate
	 * @param changeFrequency
	 * @param searchPredicate
	 * @param errorConsumer
	 * @param searchDocumentsPath
	 * @throws IOException
	 */
	public static JSONObject generateSitemapAndSearch(
			File dir,
			String domain,
			BiPredicate<File, String> siteMapPredicate,
			ChangeFreq changeFrequency,
			BiPredicate<File, String> searchPredicate,
			BiConsumer<String,String> errorConsumer,
			String searchDocumentsPath) throws IOException {
		
		return generateSitemapAndSearch(
				dir, 
				domain, 
				siteMapPredicate, 
				changeFrequency, 
				searchPredicate, 
				errorConsumer, 
				(path, doc) -> configureSearch(path, doc, searchDocumentsPath));
	}
	
	public static void generateSitemapAndSearch(
			File dir,
			String domain,
			BiPredicate<File, String> siteMapPredicate,
			ChangeFreq changeFrequency,
			BiPredicate<File, String> searchPredicate,
			BiConsumer<String,String> errorConsumer) throws IOException {				

		String searchDocumentsPath = "search-documents.js";
		JSONObject searchDocuments = generateSitemapAndSearch(dir, domain, siteMapPredicate, changeFrequency, searchPredicate, errorConsumer, searchDocumentsPath);		
		try (FileWriter writer = new FileWriter(new File(dir, searchDocumentsPath))) {
			writer.write("var searchDocuments = " + searchDocuments);
		}
	}
	
	public interface SemanticElementFactory {

		Collection<EObject> createSemanticElements(
				ProcessorConfig<SemanticProcessor<EObject>> config,
				ProgressMonitor progressMonitor,
				BiFunction<ProcessorConfig<SemanticProcessor<EObject>>, ProgressMonitor, Collection<EObject>> defaultFactory);
	}
	
	/**
	 * Can be passed to createResourceSet to customize drawio semantic elements.
	 * @author Pavel
	 *
	 */
	public interface SemanticElementConfigurator {
		
		Collection<EObject> configureSemanticElements(
				URI uri,
				ProcessorConfig<SemanticProcessor<EObject>> config, 
				Collection<EObject> semanticElements, 
				ProgressMonitor progressMonitor);
		
	}
	
	/**
	 * If semantic element is an {@link Action} and there are C4 properties, configured the action with those properties. 
	 * @param uri
	 * @param config
	 * @param semanticElements
	 * @param progressMonitor
	 * @return
	 */
	public static Collection<EObject> configureC4Actions(
			URI uri,
			ProcessorConfig<SemanticProcessor<EObject>> config, 
			Collection<EObject> semanticElements, 
			ProgressMonitor progressMonitor) {
	
		if (semanticElements == null) {
			return semanticElements;
		}
		
		for (EObject semanticElement: semanticElements) {
			if (semanticElement instanceof Label) {
				Label label = (Label) semanticElement;
				org.nasdanika.graph.Element element = config.getElement();
				if (element instanceof ModelElement) {
					ModelElement modelElement = (ModelElement) element;
					if (org.nasdanika.common.Util.isBlank(label.getText())) {
						label.setText(modelElement.getProperty("c4Name"));
						if (org.nasdanika.common.Util.isBlank(label.getText())) {
							label.setText(modelElement.getProperty("c4Type"));
						}
					}
					if (org.nasdanika.common.Util.isBlank(label.getTooltip())) {
						label.setTooltip(modelElement.getProperty("c4Description"));						
					}
					if (org.nasdanika.common.Util.isBlank(label.getIcon())) {
						String c4Type = modelElement.getProperty("c4Type");
						if (c4Type != null) {
							switch (c4Type) {
							case "Database":
								label.setIcon("fas fa-database");
								break;
							case "Person":
								label.setIcon("fas fa-user");
							}
						}
					}
				}
			}
		}
		
		return semanticElements;
	} 

	/**
	 * Creates {@link NcoreResourceSet} with {@link XMIResourceFactoryImpl}, {@link ObjectLoaderResourceFactory} handling yml, json extensions and data scheme.
	 * {@link NcoreDrawioResourceFactory} for drawio extension. Uses {@link GitMarkerFactory} for {@link EObjectLoader}.
	 * Registers {@link AppAdapterFactory}.
	 * Registers Ncore, Exec, and HTML packages.
	 * @param progressMonitor
	 * @return
	 */	
	public static ResourceSet createResourceSet(Context context, ProgressMonitor progressMonitor) {
		return createResourceSet(context, progressMonitor, null, Util::configureC4Actions);
	}
	
	public static ResourceSet createResourceSet(
			Context context, 
			ProgressMonitor progressMonitor, 
			SemanticElementFactory semanticElementFactory,
			SemanticElementConfigurator semanticElementConfigurator) {
		
		ResourceSet resourceSet = new NcoreResourceSet();
		
		resourceSet.getURIConverter().getURIHandlers().add(0, new URIHandlerImpl() {

			@Override
			public boolean canHandle(URI uri) {
				return uri != null && org.nasdanika.common.Util.CLASSPATH_SCHEME.equals(uri.scheme());
			}

			@Override
			public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
				return DefaultConverter.INSTANCE.toInputStream(uri);
			}
			
		});
		
		EObjectLoader eObjectLoader = new EObjectLoader(null, null, resourceSet);
		GitMarkerFactory markerFactory = new GitMarkerFactory();
		eObjectLoader.setMarkerFactory(markerFactory);
		Resource.Factory objectLoaderResourceFactory = new ObjectLoaderResourceFactory() { 
			
			@Override
			protected org.nasdanika.persistence.ObjectLoader getObjectLoader(Resource resource) {
				return eObjectLoader;
			}
			
			@Override
			protected Context getContext(Resource resource) {
				return context == null ? Context.EMPTY_CONTEXT : context;
			}
			
		};
		Map<String, Object> extensionToFactoryMap = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put("yml", objectLoaderResourceFactory);
		extensionToFactoryMap.put("json", objectLoaderResourceFactory);
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("data", objectLoaderResourceFactory);
		
		NcoreDrawioResourceFactory<EObject> ncoreDrawioResourceFactory = new NcoreDrawioResourceFactory<EObject>() {
			
			@Override
			protected ResourceSet getResourceSet() {
				return resourceSet;
			}
			
			@Override
			protected ProgressMonitor getProgressMonitor(URI uri) {
				return progressMonitor;
			}
			
			@Override
			protected MarkerFactory getMarkerFactory() {
				return markerFactory;
			}
			
			@Override
			protected Collection<EObject> createSemanticElements(
					ProcessorConfig<SemanticProcessor<EObject>> config,
					ProgressMonitor progressMonitor,
					BiFunction<ProcessorConfig<SemanticProcessor<EObject>>, ProgressMonitor, Collection<EObject>> defaultFactory) {
				
				return semanticElementFactory == null ? super.createSemanticElements(config, progressMonitor, defaultFactory) : semanticElementFactory.createSemanticElements(config, progressMonitor, defaultFactory);
			}
			
			@Override
			protected Collection<EObject> configureSemanticElements(
					URI uri,
					ProcessorConfig<SemanticProcessor<EObject>> config, Collection<EObject> semanticElements,
					ProgressMonitor progressMonitor) {
				Collection<EObject> configuredSemanticElements = super.configureSemanticElements(uri, config, semanticElements, progressMonitor);				
				return semanticElementConfigurator == null ? configuredSemanticElements : semanticElementConfigurator.configureSemanticElements(uri, config, configuredSemanticElements, progressMonitor);
			}
			
		};
		
		extensionToFactoryMap.put("drawio", ncoreDrawioResourceFactory);		

		// For handling textual representations
		TextResourceFactory textResourceFactory = new TextResourceFactory();
		extensionToFactoryMap.put("txt", textResourceFactory);		
		extensionToFactoryMap.put("puml", textResourceFactory);								
		extensionToFactoryMap.put("mermaid", textResourceFactory);		
		
		extensionToFactoryMap.put(org.eclipse.emf.ecore.resource.Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		resourceSet.getPackageRegistry().put(NcorePackage.eNS_URI, NcorePackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ExecPackage.eNS_URI, ExecPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ContentPackage.eNS_URI, ContentPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(ResourcesPackage.eNS_URI, ResourcesPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(HtmlPackage.eNS_URI, HtmlPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(BootstrapPackage.eNS_URI, BootstrapPackage.eINSTANCE);
		resourceSet.getPackageRegistry().put(AppPackage.eNS_URI, AppPackage.eINSTANCE);
		 
		resourceSet.getAdapterFactories().add(new AppAdapterFactory());
		
		return resourceSet;
	}
	
	/**
	 * Generates files (binary entities) from a resource model .
	 * @throws org.eclipse.emf.common.util.DiagnosticException 
	 * @throws Exception
	 */
	public static void generateContainer(
			URI resourceModelURI, 
			BinaryEntityContainer container, 
			Context context, 
			ProgressMonitor progressMonitor) throws org.eclipse.emf.common.util.DiagnosticException {
		
		ResourceSet resourceSet = createResourceSet(context, progressMonitor);		
		resourceSet.getAdapterFactories().add(new AppAdapterFactory());				
		Resource containerResource = resourceSet.getResource(resourceModelURI, true);
		generateContainer(containerResource, container, context, progressMonitor);
	}
		
	/**
	 * Generates files (binary entities) from a resource model .
	 * @throws org.eclipse.emf.common.util.DiagnosticException 
	 * @throws Exception
	 */
	public static void generateContainer(
			Resource containerResource, 
			BinaryEntityContainer container, 
			Context context, 
			ProgressMonitor progressMonitor) throws org.eclipse.emf.common.util.DiagnosticException {
		
		for (EObject eObject : containerResource.getContents()) {
			generateContainer(eObject, container, context, progressMonitor);
		}
	}		
		
	/**
	 * Generates files (binary entities) from a resource model .
	 * @throws org.eclipse.emf.common.util.DiagnosticException 
	 * @throws Exception
	 */
	public static void generateContainer(
			EObject eObj, 
			BinaryEntityContainer container, 
			Context context, 
			ProgressMonitor progressMonitor) throws org.eclipse.emf.common.util.DiagnosticException {
		
		Diagnostician diagnostician = new Diagnostician();
		org.eclipse.emf.common.util.Diagnostic diagnostic = diagnostician.validate(eObj);
		if (diagnostic.getSeverity() == org.eclipse.emf.common.util.Diagnostic.ERROR) {
			throw new org.eclipse.emf.common.util.DiagnosticException(diagnostic);
		};
		// Diagnosing loaded resources. 
		try {
			ConsumerFactory<BinaryEntityContainer> consumerFactory = Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(eObj, BinaryEntityContainer.class), "Cannot adapt to ConsumerFactory");
			Diagnostic callDiagnostic = org.nasdanika.common.Util.call(consumerFactory.create(context), container, progressMonitor);
			if (callDiagnostic.getStatus() == Status.FAIL || callDiagnostic.getStatus() == Status.ERROR) {
				System.err.println("***********************");
				System.err.println("*      Diagnostic     *");
				System.err.println("***********************");
				callDiagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
			}
			if (callDiagnostic.getStatus() != Status.SUCCESS) {
				throw new DiagnosticException(callDiagnostic);
			};
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}
	}
	
	/**
	 * Loads a semantic map (EObject -> Label) from JSON or YAML resource. The resource is considered to by YAML if its URI ends with .yml or .yaml case insensitive. 
	 * @param uri Semantic map URI. Link locations in the map are resolved relative to this URI.
	 * @param factory Factory for creating key {@link EObject}s if map entries have <code>type</code> key. Keys must implement {@link org.nasdanika.ncore.ModelElement} so URI's can be injected into them.
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public static Map<org.nasdanika.ncore.ModelElement, Label> loadSemanticMap(URI uri, BiFunction<String,String,org.nasdanika.ncore.ModelElement> factory) throws IOException {
		String lastSegment = uri.lastSegment().toLowerCase();
		Map<String, Object> semanticMap;
		if (lastSegment.endsWith(".yml") || lastSegment.endsWith(".yaml")) {
			Object yamlObj = DefaultConverter.INSTANCE.loadYAML(uri);
			if (yamlObj instanceof Map) {
				semanticMap = (Map<String, Object>) yamlObj;						
			} else {
				throw new IllegalArgumentException("Not a YAML map at " + uri); 
			}
		} else {
			JSONObject jsonObject = DefaultConverter.INSTANCE.loadJSONObject(uri);
			semanticMap = jsonObject.toMap();
		}
		
		Map<URI, org.nasdanika.ncore.ModelElement> groupedByLocation = new HashMap<>();
		Map<org.nasdanika.ncore.ModelElement, Label> ret = new LinkedHashMap<>();
		for (Entry<String, Object> se: semanticMap.entrySet()) {
			Map<String, Object> value = (Map<String, Object>) se.getValue();
			Map<String,String> type = (Map<String, String>) value.get("type");
			org.nasdanika.ncore.ModelElement semanticElement = null;
			if (type != null) {
				semanticElement = factory.apply(type.get("ns-uri"), type.get("name"));
			}
			String location = (String) value.get("location");
			if (org.nasdanika.common.Util.isBlank(location)) {
				Label label = AppFactory.eINSTANCE.createLabel();
				label.setText((String) value.get("text"));
				label.setIcon((String) value.get("icon"));
				label.setTooltip((String) value.get("tooltip"));
				if (semanticElement == null) {
					semanticElement = label;
				}
				semanticElement.getUris().add(se.getKey());
				ret.put(semanticElement, label);
			} else {
				URI locationURI = URI.createURI(location).resolve(uri);
				org.nasdanika.ncore.ModelElement semEl = groupedByLocation.get(uri);
				if (semEl == null) {
					Link link = AppFactory.eINSTANCE.createLink();
					link = AppFactory.eINSTANCE.createLink();
					link.setText((String) value.get("text"));
					link.setIcon((String) value.get("icon"));
					link.setTooltip((String) value.get("tooltip"));
					link.setLocation(locationURI.toString());
					if (semanticElement == null) {
						semanticElement = link;
					}
					ret.put(semanticElement, link);
					groupedByLocation.put(locationURI, semanticElement);
					semEl = semanticElement;
				}
				semEl.getUris().add(se.getKey());
			}
		}
		
		return ret;
	}
	
	/**
	 * Loads a semantic map (EObject -> Label) from JSON or YAML resource. The resource is considered to by YAML if its URI ends with .yml or .yaml case insensitive. 
	 * @param uri Semantic map URI. Link locations in the map are resolved relative to this URI.
	 * @param resouceSet Resource set to use the package registry to create semantic elements.
	 * @return
	 * @throws IOException 
	 */
	public static Map<org.nasdanika.ncore.ModelElement, Label> loadSemanticMap(URI uri, ResourceSet resourceSet) throws IOException {
		return loadSemanticMap(uri, (nsURI, name) -> createModelElement(resourceSet, nsURI, name));
	}
	
	private static org.nasdanika.ncore.ModelElement createModelElement(ResourceSet resourceSet, String nsURI, String name) {
		if (resourceSet == null) {
			return null;
		}
		
		Registry packageRegisry = resourceSet.getPackageRegistry();
		EPackage ePkg = packageRegisry.getEPackage(nsURI);
		if (ePkg == null) {
			throw new IllegalArgumentException("EPackage not found for namespace URI: " + nsURI);
		}
		
		EClassifier eClassifier = ePkg.getEClassifier(name);
		if (eClassifier instanceof EClass) {
			return (org.nasdanika.ncore.ModelElement) ePkg.getEFactoryInstance().create((EClass) eClassifier);
		}		
		
		throw new IllegalArgumentException("EClass '" + name + "' not found for namespace URI: " + nsURI);
	}
	
	// --- Processing of HTML files, e.g. injection of backlinks

	/**
	 * Processes HTML documents 
	 * @author Pavel
	 *
	 */
	public interface HTMLProcessor {
		
		default boolean isHTML(File file, String path) {
			return file != null && file.isFile() && path != null && (path.toLowerCase().endsWith(".html") || path.toLowerCase().endsWith(".htm")); 
		}

		/**
		 * Processed HTML document and returns true if the document was modified and shall be saved.
		 * @param file
		 * @param path
		 * @param document
		 * @return
		 */
		boolean process(File file, String path, Document document);
		
	}	

	public static void processHTML(HTMLProcessor processor, File... files) {
		BiConsumer<File, String> listener = new BiConsumer<File, String>() {
			
			@Override
			public void accept(File file, String path) {
				if (processor.isHTML(file, path)) {
					try {
						Document document = Jsoup.parse(file, "UTF-8");		
						if (processor.process(file, path, document)) {
							Document.OutputSettings outputSettings = new Document.OutputSettings();
							outputSettings.prettyPrint(false);
							document.outputSettings(outputSettings);
							try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
								writer.write(document.html());
							}
						}
					} catch (IOException e) {
						throw new NasdanikaException("Error processing HTML file " + path + " " + file.getAbsolutePath() + " " + e, e);
					}
				}
			}
		};
		org.nasdanika.common.Util.walk(null, listener, files);		
	}
				
}
