package org.nasdanika.html.model.app.gen;

import static org.nasdanika.common.Util.isBlank;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.mxgraph.io.mxCodec;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.model.mxICell;
import com.mxgraph.util.mxXmlUtils;


public final class Util {
	
	public static final String DATA_NSD_ACTION_UUID_ATTRIBUTE = "data-nsd-action-uuid";

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
	 * @throws Exception 
	 */
	public static void generateSite(
			Label root, 
			org.nasdanika.html.model.bootstrap.Page pageTemplate,
			Container container,
			ActionContentProvider.Factory actionContentProviderFactory,
			PageContentProvider.Factory pageContentProviderFactory,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
	
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
	 * @throws Exception 
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
			ProgressMonitor progressMonitor) throws Exception {
	
		if (context == null) {
			context = Context.EMPTY_CONTEXT;
		}
		if (context.get(Context.BASE_URI_PROPERTY) == null) {
			context = context.fork();
			((MutableContext) context).put(Context.BASE_URI_PROPERTY, URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/"));
		}
		BiFunction<Label, URI, URI> uriResolver = uriResolver(root, context);		
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
	 * @throws Exception 
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
			ProgressMonitor progressMonitor) throws Exception {
		
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
					file.getContents().add(pageContentProvider == null ? bootstrapPage : pageContentProvider.getPageContent(bootstrapPage, baseURI, uriResolver, progressMonitor));
					
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
		
		for (EObject child: resolveActionReferences(activeAction.getChildren())) {
			if (child instanceof Action) {
				generateSite(root, principal, (Action) child, subActionPath, pageTemplate, uriResolver, baseURI, container, actionContentProvider, pageContentProvider, progressMonitor);
			}
		}
				
		if (activeAction instanceof Action) {
			Action theActiveAction = (Action) activeAction;
			for (Action section: theActiveAction.getSections()) {
				generateSite(root, principal, (Action) section, subActionPath, pageTemplate, uriResolver, baseURI, container, actionContentProvider, pageContentProvider, progressMonitor);
			}
			
			for (EObject navigation: resolveActionReferences(theActiveAction.getNavigation())) {
				if (navigation instanceof Action) {
					generateSite(root, principal, (Action) navigation, subActionPath, pageTemplate, uriResolver, baseURI, container, actionContentProvider, pageContentProvider, progressMonitor);
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
			ProgressMonitor progressMonitor) throws Exception {
		
		if (root != null) {
			Label title = createLabel(root, activeAction, uriResolver, null, "header/title", false, false);
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
					if (rac instanceof Action) {
						headerItems.add(createLabel((Action) rac, activeAction, uriResolver, null, "header/navigation", true, false));
					} else {
						headerItems.add(EcoreUtil.copy(rac));
					}
				});
			}
			
			if (root instanceof Action) {
				List<EObject> rootNavigation = resolveActionReferences(((Action) root).getNavigation());
				if (!rootNavigation.isEmpty()) {
					Footer footer = appPage.getFooter();
					if (footer == null) {
						footer = AppFactory.eINSTANCE.createFooter();
						appPage.setFooter(footer);
					}
					EList<EObject> footerItems = footer.getItems();
					rootNavigation.forEach(ran -> {
						if (ran instanceof Action) {
							footerItems.add(createLabel((Action) ran, activeAction, uriResolver, null, "footer/navigation", true, false));
						} else {
							footerItems.add(EcoreUtil.copy(ran));
						}
					});
					
				}
			}
		}
		
		if (principal != null) {
			// Navbar 
			Label brand = createLabel(principal, activeAction, uriResolver, null, "navbar/brand", false, false);
			if (principal instanceof Action) {
				List<EObject> principalNavigation = resolveActionReferences(((Action) principal).getNavigation());
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
							navBarItems.add(createLabel((Action) principalNavigationElement, activeAction, uriResolver, null, "navbar/item", true, false));
						} else {
							navBarItems.add(EcoreUtil.copy(principalNavigationElement));
						}
					});
				}
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
				Function<Label, String> navItemIdProvider = na -> isBlank(na.getId()) ? null : "nsd-app-nav-item-" + na.getId();
				List<EObject> navPanelItems = navPanel.getItems();
				principalChildren.forEach(principalChild -> {
					if (principalChild instanceof Label) {
						navPanelItems.add(createLabel((Label) principalChild, activeAction, uriResolver, navItemIdProvider, "nav-panel", true, true));
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
				contentPanel.getAttributes().put(DATA_NSD_ACTION_UUID_ATTRIBUTE, text);
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
			ProgressMonitor progressMonitor) throws Exception {
		
		if (!rootOrPrincipal) {
			if (path != null && !path.isEmpty()) {
				EList<Label> breadcrumb = contentPanel.getBreadcrumb();
				path.forEach(pathElement -> {
					Label element = createLabel(pathElement, action, uriResolver, null, "content-panel/breadcrumb", false, false);
					if (element != null) {
						breadcrumb.add(element);
					}
				});
				Label tail = createLabel(action, action, uriResolver, null, "content-panel/breadcrumb", false, false);		
				tail.setActive(true);
				breadcrumb.add(tail);
			}
	
			if (!isBlank(action.getText()) || !isBlank(action.getIcon())) {
				Label title = org.nasdanika.common.Util.isBlank(action.getName()) ? AppFactory.eINSTANCE.createLabel() : AppFactory.eINSTANCE.createLink(); 
				configureLabel(action, action, uriResolver, null, "content-panel/title", title, false, false);
				contentPanel.setTitle(title);
			}
					
			List<EObject> navigation = resolveActionReferences(action.getNavigation());
			EList<EObject> navigationItems = contentPanel.getItems();
			navigation.forEach(navigationElement -> {
				if (navigationElement instanceof Label) {
					navigationItems.add(createLabel((Label) navigationElement, action, uriResolver, null, "content-panel/navigation-item", true, false));
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
			return !isBlank(link.getScript()) || link.getModal() != null || !isBlank(link.getName());
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
			boolean inNavPanel) {
		Appearance aa = source.getAppearance();
		if (aa != null) {
			label.setAppearance(aa.effectiveAppearance(appearancePath));
		}

		label.setColor(source.getColor());
		label.setDescription(source.getDescription());
		label.setDisabled(source.isDisabled());
		Modal help = source.getHelp();
		if (help != null) {
//		label.setHelp(value); TODO - make links in help relative.
			throw new UnsupportedOperationException("Help modals not supported yet");
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

		if (source instanceof Action) {
			String sourceUUID = source.getUuid();
			if (!org.nasdanika.common.Util.isBlank(sourceUUID)) {
				Text text = ContentFactory.eINSTANCE.createText();
				text.setContent(sourceUUID);			
				label.getAttributes().put(DATA_NSD_ACTION_UUID_ATTRIBUTE, text);
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
			for (EObject actionChild: source.getChildren()) {
				if (actionChild instanceof ActionReference) {
					actionChild = ((ActionReference) actionChild).getTarget();
				}
				if (actionChild instanceof Action) {
					Action childAction = (Action) actionChild;
					labelChildren.add(createLabel(childAction, activeAction, uriResolver, idProvider, "header/navigation", recursive, inNavPanel));
					
					// Second level - headers, separators.
				} else {
					labelChildren.add(EcoreUtil.copy(actionChild));
				}							
			}			
		}
		
		label.setActive(source instanceof Action && inNavPanel ? isActiveAction((Action) source, activeAction, !hasActiveChildren(label)) : source.isActive());		
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
		URI uri = uriResolver.apply(source, activeActionURI);
		
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
	private static Label createLabel(
			Label source, 
			Label activeAction, 
			BiFunction<Label, URI, URI> uriResolver, 
			Function<Label, String> idProvider, 
			String appearancePath,
			boolean recursive,
			boolean inNavPanel) {
		
		URI activeActionURI = uriResolver.apply(activeAction, null);
		URI uri = uriResolver.apply(source, activeActionURI);
		
		if (isBlank(source.getText()) && isBlank(source.getIcon()) && !recursive) {
			return null;
		}
		Label label = isLink(source, uri) ? AppFactory.eINSTANCE.createLink() : AppFactory.eINSTANCE.createLabel();
		configureLabel(source, activeAction, uriResolver, idProvider, appearancePath, label, recursive, inNavPanel);
				
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
			ProgressMonitor progressMonitor) throws Exception {
		
		if (navigationPanel != null) {
			for (EObject item: resolveActionReferences(navigationPanel.getItems())) {
				if (item instanceof Label) {
					generateSite(root, principal, (Label) item, actionPath, pageTemplate, uriResolver, baseURI, container, actionContentProvider, pageContentProvider, progressMonitor);
				}
			}			
		}
	}
	
	/**
	 * Resolves URI's by traversing containment references from the root Action taking {@link ActionReference} into account.
	 * @param context
	 * @return A function resolving {@link URI} for the argument {@link Action}. Caches results.
	 */
	public static BiFunction<Label,URI,URI> uriResolver(Label root, Context context) {		
		Map<String, URI> cache = new HashMap<>();
		traverse(root, context.get(Context.BASE_URI_PROPERTY, URI.class), context, cache);
		
		return new BiFunction<Label, URI, URI>() {
			
			@Override
			public URI apply(Label label, URI base) {
				URI uri = cache.get(label.getUuid());		
				return base == null || uri == null ? uri : uri.deresolve(base, true, true, true);
			}
		
		};
	}
	
	private static void traverse(Label label, URI base, Context context, Map<String, URI> cache) {
		URI linkURI = compute(label, base, context);
		cache.put(label.getUuid(), linkURI);
		
		for (EObject child: resolveActionReferences(label.getChildren())) {
			if (child instanceof Action) {
				traverse((Action) child, linkURI == null ? base : linkURI, context, cache);
			}
		}
		
		if (label instanceof Action) {
			Action action = (Action) label;
			for (EObject item: resolveActionReferences(action.getNavigation())) {
				if (item instanceof Link) {
					traverse((Link) item, linkURI == null ? base : linkURI, context, cache);
				}
			}
			for (Action section: action.getSections()) {
				traverse(section, linkURI == null ? base : linkURI, context, cache);
			}
			for (Action anonymous: action.getAnonymous()) {
				traverse(anonymous, linkURI == null ? base : linkURI, context, cache);
			}
		}
		
		// TODO - nav panels?
	}

	private static URI compute(Label label, URI base, Context context) {
		if (label instanceof Link) {
			Link link = (Link) label;
			String uriString;
			if (link.eContainmentFeature() == AppPackage.Literals.ACTION__SECTIONS) {
				String aName = context.interpolateToString(link.getLocation());
				if (isBlank(aName)) {
					return null;
				}
				uriString = "#" + aName;
			} else {				
				uriString = context.interpolateToString(link.getLocation());
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
		
		return null;
	}
	
	private static EObject resolveActionReference(EObject obj) {
		return obj instanceof ActionReference ? ((ActionReference) obj).getTarget() : obj;
	}
	
	private static List<EObject> resolveActionReferences(EList<EObject> objs) {
		return objs.stream().map((Function<EObject, EObject>) Util::resolveActionReference).collect(Collectors.toList());
	}
	
	public static JSONObject createSearchDocument(String path, File file) throws IOException {
		return createSearchDocument(path, file, null, null);
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
	public static JSONObject createSearchDocument(String path, File file, Consumer<? super Element> contentConsumer, BiFunction<String, Document, Boolean> processor) throws IOException {
		Document document = Jsoup.parse(file, "UTF-8");
		if (processor != null && processor.apply(path, document)) {
			try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8")) {
				writer.write(document.outerHtml());
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
			traverseMxGraphModel(diagramDiv, cell -> {
				Object cellValue = cell.getValue();
				if (cellValue instanceof org.w3c.dom.Element) {
					String label = ((org.w3c.dom.Element) cellValue).getAttribute("label");
					if (!org.nasdanika.common.Util.isBlank(label)) {
						String labelText = Jsoup.parse(label).text();
						contentText.append(" ").append(labelText);
					}
				} else if (cellValue != null) {
					String text = Jsoup.parse(cellValue.toString()).text();
					contentText.append(" ").append(text);					
				}
			});
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
			String actionUUID = element.attr(DATA_NSD_ACTION_UUID_ATTRIBUTE);
			if (!org.nasdanika.common.Util.isBlank(actionUUID)) {
				searchDocument.put("action-uuid", actionUUID);
				break;
			}
		}
		
		return searchDocument;
	}
	
	/**
	 * Loads {@link mxGraphModel} from mxgraph div.
	 * @param mxGraphDiv
	 * @return
	 */
	public static mxGraphModel loadMxGraphModel(Element mxGraphDiv) {
		String data = mxGraphDiv.attr("data-mxgraph");
		if (data != null) {
			JSONObject jsonData = new JSONObject(data);
			Object xml = jsonData.get("xml");
			if (xml instanceof String) {
				return parseMxGraphModel(xml);
			}
		}
		return null;
	}

	public static mxGraphModel parseMxGraphModel(Object xml) {
		org.w3c.dom.Document xmlDocument = mxXmlUtils.parseXml((String) xml);
		org.w3c.dom.Element mxfileElement = xmlDocument.getDocumentElement(); // mxfile
		org.w3c.dom.Element diagramElement = getChildElement(mxfileElement, "diagram");
		org.w3c.dom.Element mxGraphModelElement = getChildElement(diagramElement, "mxGraphModel");
		if (mxGraphModelElement == null) {
			try {
				String textContent = diagramElement.getTextContent();
				if (!Base64.isBase64(textContent)) {
					throw new NasdanikaException("Compressed diagram is not Base64 encoded");
				}
			    byte[] compressed = Base64.decodeBase64(textContent);
			    byte[] decompressed = inflate(compressed);
			    String decompressedStr = new String(decompressed, StandardCharsets.UTF_8);
			    String decodedStr = URLDecoder.decode(decompressedStr, StandardCharsets.UTF_8.name());
			    org.w3c.dom.Document modelDoc = mxXmlUtils.parseXml(decodedStr);
				mxCodec codec = new mxCodec(modelDoc);
				return Objects.requireNonNull((mxGraphModel) codec.decode(modelDoc.getDocumentElement()), "Graph model is null for " + decodedStr);
			} catch (IOException e) {
				throw new NasdanikaException("Error decoding a drwaio diagram: " + e, e);
			}
		}
		
		mxCodec codec = new mxCodec(xmlDocument);
		org.w3c.dom.Element graphModelElement = Objects.requireNonNull(mxGraphModelElement, "No mxGraphModel element: " + xml);
		return Objects.requireNonNull((mxGraphModel) codec.decode(graphModelElement), "Graph model is null for " + xml);			
	}
	
	private static  byte[] inflate(byte[] content) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ByteArrayInputStream source = new ByteArrayInputStream(content); OutputStream target = new InflaterOutputStream(baos, new Inflater(true))) {
	        byte[] buf = new byte[8192];
	        int length;
	        while ((length = source.read(buf)) > 0) {
	            target.write(buf, 0, length);
	        }
		}
		
		return baos.toByteArray();
	}
	
	private static  byte[] deflate(byte[] content) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ByteArrayInputStream source = new ByteArrayInputStream(content); OutputStream target = new DeflaterOutputStream(baos, new Deflater(Deflater.DEFAULT_COMPRESSION, true))) {
	        byte[] buf = new byte[8192];
	        int length;
	        while ((length = source.read(buf)) > 0) {
	            target.write(buf, 0, length);
	        }
		}
		
		return baos.toByteArray();
	}
	
	public static void traverseMxGraphModel(Element mxGraphDiv, Consumer<mxICell> visitor) {
		mxGraphModel graphModel = loadMxGraphModel(mxGraphDiv);
		if (graphModel != null) {
			Object root = graphModel.getRoot();
			if (root instanceof mxICell) {
				visit((mxICell) root, visitor);
			}
		}		
	}
	
	private static org.w3c.dom.Element getChildElement(org.w3c.dom.Element parent, String name) {
		NodeList children = parent.getChildNodes();
		for (int i = 0; i < children.getLength(); ++i) {
			Node child = children.item(i);
			if (child instanceof org.w3c.dom.Element && ((org.w3c.dom.Element) child).getTagName().equals(name)) {
				return (org.w3c.dom.Element) child;
			}
		}
		return null;
	}
	
	/**
	 * Loads graph model, traverses all cells and then saves and returns the model.
	 * @param spec
	 * @param cellVisitor
	 * @return
	 */
	public static String filterMxGraphModel(String spec, Consumer<mxICell> cellVisitor) throws Exception {
		org.w3c.dom.Document xmlDocument = mxXmlUtils.parseXml(spec);
		org.w3c.dom.Element mxfileElement = xmlDocument.getDocumentElement(); // mxfile
		org.w3c.dom.Element diagramElement = getChildElement(mxfileElement, "diagram");
		org.w3c.dom.Element mxGraphModelElement = getChildElement(diagramElement, "mxGraphModel");
		if (mxGraphModelElement == null) { // Compressed
			String textContent = diagramElement.getTextContent();
			if (!Base64.isBase64(textContent)) {
				throw new NasdanikaException("Compressed diagram is not Base64 encoded");
			}
		    byte[] compressed = Base64.decodeBase64(textContent);
		    byte[] decompressed = inflate(compressed);
		    String decompressedStr = new String(decompressed, StandardCharsets.UTF_8);
		    String decodedStr = URLDecoder.decode(decompressedStr, StandardCharsets.UTF_8.name());
		    org.w3c.dom.Document modelDoc = mxXmlUtils.parseXml(decodedStr);
			mxCodec codec = new mxCodec(modelDoc);
			mxGraphModel graphModel = Objects.requireNonNull((mxGraphModel) codec.decode(modelDoc.getDocumentElement()), "Graph model is null for " + decodedStr);
			Object root = graphModel.getRoot();
			if (root instanceof mxICell) {
				visit((mxICell) root, cellVisitor);
			}
			Node encodedModel = codec.encode(graphModel);
			String encodedModelStr = mxXmlUtils.getXml(encodedModel);
		    String urlEncodedStr = URLEncoder.encode(encodedModelStr, StandardCharsets.UTF_8.name()).replace("+", "%20"); // Hackish replacement of + with %20 for drawio viewer to understand.
		    byte[] reCompressed = deflate(urlEncodedStr.getBytes(StandardCharsets.UTF_8));
		    String reEncoded = Base64.encodeBase64String(reCompressed);
			diagramElement.setTextContent(reEncoded);
			String ret = mxXmlUtils.getXml(xmlDocument);
			return ret;
		}
		
		mxCodec codec = new mxCodec(xmlDocument);
		mxGraphModel graphModel = Objects.requireNonNull((mxGraphModel) codec.decode(mxGraphModelElement), "Graph model is null for " + spec);
		Object root = graphModel.getRoot();
		if (root instanceof mxICell) {
			visit((mxICell) root, cellVisitor);
		}
		Node encodedModel = codec.encode(graphModel);
		diagramElement.replaceChild(encodedModel, mxGraphModelElement);
		return mxXmlUtils.getXml(xmlDocument);			
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
				traverseMxGraphModel(diagramDiv, cell -> {
					Object cellValue = cell.getValue();
					if (cellValue instanceof org.w3c.dom.Element) {
						String link = ((org.w3c.dom.Element) cellValue).getAttribute("link");
						if (!org.nasdanika.common.Util.isBlank(link) && !linkPredicate.test(link)) {
							String label = ((org.w3c.dom.Element) cellValue).getAttribute("label");
							if (org.nasdanika.common.Util.isBlank(label)) {
								errorConsumer.accept("Broken diagram link: " + link);
							} else {
								errorConsumer.accept("Broken diagram link on " + label + ": " + link);											
							}
						}
					}
				});
			}			
		};
	}
	
	private static void visit(mxICell cell, Consumer<mxICell> visitor) {
		visitor.accept(cell);
		for (int i = 0; i < cell.getChildCount(); ++i) {
        	visit(cell.getChildAt(i), visitor);
        }
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
				return namedAnchors.size() + idAnchors.size() == 1;
			} catch (IOException e) {
				throw new NasdanikaException("Error parsing " + targetFile.getAbsolutePath(), e);
			}
			
		};
		
	}

			
}
