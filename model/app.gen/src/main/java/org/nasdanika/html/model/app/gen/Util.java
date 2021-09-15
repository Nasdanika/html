package org.nasdanika.html.model.app.gen;

import static org.nasdanika.common.Util.isBlank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
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
import org.nasdanika.html.model.bootstrap.Item;
import org.nasdanika.html.model.bootstrap.Modal;


public final class Util {
	
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
			Action root, 
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			Container container,
			Context context,
			ProgressMonitor progressMonitor) {
	
		Action principal = null;
		EList<EObject> rootChildren = root.getChildren();
		if (rootChildren.size() > 0) {
			EObject firstChild = root.getChildren().get(0);
			if (firstChild instanceof Action) {
				principal = (Action) firstChild;
			}
		}
		
		generateSite(root, principal, root, Collections.emptyList(), pageTemplate, container, context, progressMonitor);
	}
	
	/**
	 * Generates application site from an {@link Action} model using a random base URI and a caching URI resolver created from the argument context with base-uri injected.
	 * @param root Root action to generate header and footer. Can be null.
	 * @param principal Principal action to generate navigation bar and navigation panel. Can be null.
	 * @param activeAction Active action to generate the content panel.
	 * @param actionPath Action path to show in breadcrumbs.
	 * @param pageTemplate Page template.
	 * @param context Context used for uri resolution - interpolation of action locations and names. can be null.
	 * @param container Receiver of generated resources.
	 * @param progressMonitor Progress monitor.
	 */
	public static void generateSite(
			Action root, 
			Action principal, 
			Action activeAction, 
			List<Action> actionPath,
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			Container container,
			Context context,
			ProgressMonitor progressMonitor) {
	
		if (context == null) {
			context = Context.EMPTY_CONTEXT;
		}
		if (context.get(Context.BASE_URI_PROPERTY) == null) {
			context = context.fork();
			((MutableContext) context).put(Context.BASE_URI_PROPERTY, URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/"));
		}
		BiFunction<Action, URI, URI> uriResolver = uriResolver(root, context);		
		generateSite(
				root, 
				principal, 
				activeAction,
				actionPath,
				pageTemplate,
				uriResolver, 
				(URI) context.get(Context.BASE_URI_PROPERTY), 
				container, 
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
			Action root, 
			Action principal, 
			Action activeAction, 
			List<Action> actionPath,
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			BiFunction<Action, URI, URI> uriResolver, 
			URI baseURI,
			Container container,
			ProgressMonitor progressMonitor) {
		
		if (activeAction.eContainmentFeature() != AppPackage.Literals.ACTION__SECTIONS) {
			URI uri = uriResolver.apply(activeAction, baseURI);				
			if (uri != null && uri.isRelative()) {
				org.nasdanika.exec.resources.File file = container.getFile(uri.toString());
				org.nasdanika.html.model.bootstrap.Page bootstrapPage = EcoreUtil.copy(pageTemplate);
				bootstrapPage.setName(activeAction.getText());
				if (bootstrapPage.getBody().isEmpty()) {
					bootstrapPage.getBody().add(AppFactory.eINSTANCE.createPage());
				}
				for (EObject be: bootstrapPage.getBody()) {
					if (be instanceof org.nasdanika.html.model.app.Page) {
						buildAppPage(root, principal, activeAction, actionPath, (org.nasdanika.html.model.app.Page) be, uriResolver, progressMonitor);						
					}
				}
				file.getContents().add(bootstrapPage);
				
				for (org.nasdanika.exec.resources.Resource res: activeAction.getResources()) {
					((Container) file.eContainer()).getContents().add(EcoreUtil.copy(res));					
				}				
			}
		}
		
		List<Action> subActionPath = new ArrayList<>(actionPath);
		if (activeAction != root && activeAction != principal) {
			subActionPath.add(activeAction);
		}
		
		for (EObject child: resolveActionReferences(activeAction.getChildren())) {
			if (child instanceof Action) {
				generateSite(root, principal, (Action) child, subActionPath, pageTemplate, uriResolver, baseURI, container, progressMonitor);
			}
		}
		
		for (Action section: activeAction.getSections()) {
			generateSite(root, principal, (Action) section, subActionPath, pageTemplate, uriResolver, baseURI, container, progressMonitor);
		}
				
		for (EObject navigation: resolveActionReferences(activeAction.getNavigation())) {
			if (navigation instanceof Action) {
				generateSite(root, principal, (Action) navigation, subActionPath, pageTemplate, uriResolver, baseURI, container, progressMonitor);
			}
		}
		
		for (Action anonymous: activeAction.getAnonymous()) {
			generateSite(root, principal, (Action) anonymous, subActionPath, pageTemplate, uriResolver, baseURI, container, progressMonitor);
		}
		
		generateNavigationPanel(root, principal, actionPath, activeAction.getFloatLeftNavigation(), pageTemplate, uriResolver, baseURI, container, progressMonitor);
		generateNavigationPanel(root, principal, actionPath, activeAction.getFloatRightNavigation(), pageTemplate, uriResolver, baseURI, container, progressMonitor);
		generateNavigationPanel(root, principal, actionPath, activeAction.getLeftNavigation(), pageTemplate, uriResolver, baseURI, container, progressMonitor);
		generateNavigationPanel(root, principal, actionPath, activeAction.getRightNavigation(), pageTemplate, uriResolver, baseURI, container, progressMonitor);
	}
	
	private static void buildAppPage(
			Action root, 
			Action principal, 
			Action activeAction, 
			List<Action> actionPath,
			org.nasdanika.html.model.app.Page appPage,
			BiFunction<Action, URI, URI> uriResolver, 
			ProgressMonitor progressMonitor) {
		
		if (root != null) {
			Label title = createLabel(root, activeAction, uriResolver, null, "header/title", false);
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
				List<EObject> headerItems = resolveActionReferences(header.getItems());
				rootChildren.listIterator(1).forEachRemaining(rac -> {
					if (rac instanceof Action) {
						headerItems.add(createLabel((Action) rac, activeAction, uriResolver, null, "header/navigation", true));
					} else {
						headerItems.add(EcoreUtil.copy(rac));
					}
				});
			}
			
			List<EObject> rootNavigation = resolveActionReferences(root.getNavigation());
			if (!rootNavigation.isEmpty()) {
				Footer footer = appPage.getFooter();
				if (footer == null) {
					footer = AppFactory.eINSTANCE.createFooter();
					appPage.setFooter(footer);
				}
				EList<EObject> footerItems = footer.getItems();
				rootNavigation.forEach(ran -> {
					if (ran instanceof Action) {
						footerItems.add(createLabel((Action) ran, activeAction, uriResolver, null, "footer/navigation", true));
					} else {
						footerItems.add(EcoreUtil.copy(ran));
					}
				});
				
			}
		}
		
		if (principal != null) {
			// Navbar 
			Label brand = createLabel(principal, activeAction, uriResolver, null, "navbar/brand", false);
			List<EObject> principalNavigation = resolveActionReferences(principal.getNavigation());
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
					if (principalNavigationElement instanceof Action) {
						navBarItems.add(createLabel((Action) principalNavigationElement, activeAction, uriResolver, null, "navbar/item", true));
					} else {
						navBarItems.add(EcoreUtil.copy(principalNavigationElement));
					}
				});
			}
			
			// Navigation panel
			List<EObject> principalChildren = resolveActionReferences(principal.getChildren());
			if (!principalChildren.isEmpty()) {
				NavigationPanel navPanel = appPage.getNavigationPanel();
				if (navPanel == null) {
					navPanel = AppFactory.eINSTANCE.createNavigationPanel();
					appPage.setNavigationPanel(navPanel);
				}
				if (isBlank(navPanel.getId())) {
					navPanel.setId(principal.getId() + "-navigation-panel");
				}
				Function<Action, String> navItemIdProvider = na -> isBlank(na.getId()) ? null : "nsd-app-nav-item-" + na.getId();
				EList<EObject> navPanelItems = navPanel.getItems();
				principalChildren.forEach(principalChild -> {
					if (principalChild instanceof Action) {
						navPanelItems.add(createLabel((Action) principalChild, activeAction, uriResolver, navItemIdProvider, "nav-panel", true));
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
		
		buildContentPanel(activeAction, actionPath, activeAction == root || activeAction == principal, contentPanel, uriResolver, progressMonitor);	
	}
	
	private static void buildContentPanel(
			Action action, 
			List<Action> path,
			boolean rootOrPrincipal,
			ContentPanel contentPanel,
			BiFunction<Action, URI, URI> uriResolver, 
			ProgressMonitor progressMonitor) {
		
		if (!rootOrPrincipal) {
			if (path != null && !path.isEmpty()) {
				EList<Label> breadcrumb = contentPanel.getBreadcrumb();
				path.forEach(pathElement -> {
					Label element = createLabel(pathElement, action, uriResolver, null, "content-panel/breadcrumb", false);
					if (element != null) {
						breadcrumb.add(element);
					}
				});
				Label tail = createLabel(action, action, uriResolver, null, "content-panel/breadcrumb", false);		
				tail.setActive(true);
				breadcrumb.add(tail);
			}
	
			if (!isBlank(action.getText()) || !isBlank(action.getIcon())) {
				Label title = AppFactory.eINSTANCE.createLabel(); 
				configureLabel(action, action, uriResolver, null, "content-panel/title", title, false);
				contentPanel.setTitle(title);
			}
					
			List<EObject> navigation = resolveActionReferences(action.getNavigation());
			EList<EObject> navigationItems = contentPanel.getItems();
			navigation.forEach(navigationElement -> {
				if (navigationElement instanceof Action) {
					navigationItems.add(createLabel((Action) navigationElement, action, uriResolver, null, "content-panel/navigation-item", true));
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
			buildContentPanel(section, null, false, sectionPanel, uriResolver, progressMonitor);
		}

		contentPanel.setFloatLeftNavigation(createNavigationPanel(action.getFloatLeftNavigation()));
		contentPanel.setFloatRightNavigation(createNavigationPanel(action.getFloatRightNavigation()));
		contentPanel.setLeftNavigation(createNavigationPanel(action.getLeftNavigation()));		
		contentPanel.setRightNavigation(createNavigationPanel(action.getRightNavigation()));
		
		contentPanel.getContent().addAll(EcoreUtil.copyAll(action.getContent()));
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

	private static boolean isLink(Action action, URI uri) {		
		return uri != null || !isBlank(action.getScript()) || action.getModal() != null || !isBlank(action.getName());
	}
	
	private static void configureLabel(
			Action action, 
			Action activeAction, 
			BiFunction<Action, URI, URI> uriResolver, 
			Function<Action, String> idProvider, 
			String appearancePath, 
			Label label, 
			boolean recursive) {
		Appearance aa = action.getAppearance();
		if (aa != null) {
			label.setAppearance(aa.effectiveAppearance(appearancePath));
		}

		label.setActive(activeAction == action);
		label.setColor(action.getColor());
		label.setDescription(action.getDescription());
		label.setDisabled(action.isDisabled());
		Modal help = action.getHelp();
		if (help != null) {
//		label.setHelp(value); TODO - make links in help relative.
			throw new UnsupportedOperationException("Help modals not supported yet");
		}
		label.setIcon(action.getIcon());
		if (idProvider != null) {
			label.setId(idProvider.apply(action));
		}
//		label.setId(action.getId());		
		label.setNotification(action.getNotification());
		label.setOutline(action.isOutline());
		label.setText(action.getText());
		label.setTooltip(action.getTooltip());
//		label.getAttributes();
		
		if (label instanceof Link) {
			configureLink(action, activeAction, uriResolver, (Link) label);
		}
		
		if (recursive) {
			EList<EObject> labelChildren = label.getChildren();
			for (EObject actionChild: action.getChildren()) {
				if (actionChild instanceof Action) {
					Action childAction = (Action) actionChild;
					labelChildren.add(createLabel(childAction, activeAction, uriResolver, idProvider, "header/navigation", recursive));
					
					// Second level - headers, separators.
				} else {
					labelChildren.add(EcoreUtil.copy(actionChild));
				}							
			}			
		}
	}
	
	private static void configureLink(
			Action action, 
			Action activeAction, 
			BiFunction<Action, URI, URI> uriResolver, 
			Link link) {
		
		URI activeActionURI = uriResolver.apply(activeAction, null);
		URI uri = uriResolver.apply(action, activeActionURI);
		
		if (uri != null) {
			link.setLocation(uri.toString());
		}
		link.setConfirmation(action.getConfirmation());
		if (action.getModal() != null) {
//		link.setModal(value); TODO - contextualization of links - relativization of URI's. Token expansion with a property computer? something like ${relative-uri/<uri here>}? 
//		Support of sub-tokens e.g. ${{relative-uri/${token}}} recognizes ${token} as a sub-token to be expanded to result in ${relative-uri/<token value>}.
			throw new UnsupportedOperationException("Modals are not supported yet");
		}
		if (action.isModalActivator()) {
			throw new UnsupportedOperationException("Modals are not supported yet");
		}
		if (action.isInline()) {
			throw new UnsupportedOperationException("Inline actions are not supported yet");			
		}
		link.setName(action.getName());
		link.setScript(action.getScript());
		link.setTarget(action.getTarget());
		
	}
	
	/**
	 * Creates a {@link Link} from {@link Action} with a relative location for locations.
	 * @param action
	 * @param uri
	 * @param active 
	 * @param appearancePath 
	 * @return
	 */
	private static Label createLabel(
			Action action, 
			Action activeAction, 
			BiFunction<Action, URI, URI> uriResolver, 
			Function<Action, String> idProvider, 
			String appearancePath,
			boolean recursive) {
		
		URI activeActionURI = uriResolver.apply(activeAction, null);
		URI uri = uriResolver.apply(action, activeActionURI);
		
		if (isBlank(action.getText()) && isBlank(action.getIcon()) && !recursive) {
			return null;
		}
		Label label = isLink(action, uri) ? AppFactory.eINSTANCE.createLink() : AppFactory.eINSTANCE.createLabel();
		configureLabel(action, activeAction, uriResolver, idProvider, appearancePath, label, recursive);
				
		return label;
	}
	
	private static void generateNavigationPanel(
			Action root, 
			Action principal, 
			List<Action> actionPath,
			NavigationPanel navigationPanel,
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			BiFunction<Action, URI, URI> uriResolver, 
			URI baseURI, 
			Container container,
			ProgressMonitor progressMonitor) {
		
		if (navigationPanel != null) {
			for (EObject item: resolveActionReferences(navigationPanel.getItems())) {
				if (item instanceof Action) {
					generateSite(root, principal, (Action) item, actionPath, pageTemplate, uriResolver, baseURI, container, progressMonitor);
				}
			}			
		}
	}
	
	/**
	 * Resolves URI's by traversing containment references from root taking {@link ActionReference} into account.
	 * @param context
	 * @return A function resolving {@link URI} for the argument {@link Action}. Caches results.
	 */
	public static BiFunction<Action,URI,URI> uriResolver(Action root, Context context) {
		Map<Action, URI> cache = new HashMap<>();
		traverse(root, context.get(Context.BASE_URI_PROPERTY, URI.class), context, cache);
		
		return new BiFunction<Action, URI, URI>() {
			
			@Override
			public URI apply(Action action, URI base) {
				URI uri = cache.get(action);
				return base == null || uri == null ? uri : uri.deresolve(base, true, true, true);
			}
			
		};
	}
	
	private static void traverse(Action action, URI base, Context context, Map<Action, URI> cache) {
		URI actionURI = compute(action, base, context);
		cache.put(action, actionURI);
		for (EObject child: resolveActionReferences(action.getChildren())) {
			if (child instanceof Action) {
				traverse((Action) child, actionURI, context, cache);
			}
		}
		for (EObject item: resolveActionReferences(action.getNavigation())) {
			if (item instanceof Action) {
				traverse((Action) item, actionURI, context, cache);
			}
		}
		for (Action section: action.getSections()) {
			traverse(section, actionURI, context, cache);
		}
		for (Action anonymous: action.getAnonymous()) {
			traverse(anonymous, actionURI, context, cache);
		}
		
		// TODO - nav panels?
	}

	private static URI compute(Action action, URI base, Context context) {
		String uriString;
		if (action.eContainmentFeature() == AppPackage.Literals.ACTION__SECTIONS) {
			String aName = context.interpolateToString(action.getLocation());
			if (isBlank(aName)) {
				return null;
			}
			uriString = "#" + aName;
		} else {				
			uriString = context.interpolateToString(action.getLocation());
			if (isBlank(uriString)) {
				return null;
			}
		}
		URI uri = URI.createURI(uriString);
		if (uri.isRelative() && base != null) {
			return uri.resolve(base);
		}
		return uri;
	}
	
	
	
	private static EObject resolveActionReference(EObject obj) {
		return obj instanceof ActionReference ? ((ActionReference) obj).getTarget() : obj;
	}
	
	private static List<EObject> resolveActionReferences(EList<EObject> objs) {
		return objs.stream().map((Function<EObject, EObject>) Util::resolveActionReference).collect(Collectors.toList());
	}
			
}
