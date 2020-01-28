package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.ListIterator;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumb;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Container.Row.Col;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Size;

/**
 * Constructs content view part as nested sections. 
 * @author Pavel Vlasov
 *
 */
public class ContentPanelViewPart implements ViewPart {

	private Action activeAction;

	/**
	 * 
	 * @param activeAction
	 * @param input
	 */
	public ContentPanelViewPart(Action activeAction) {
		this.activeAction = activeAction;
	}
		
	/**
	 * Returns the last action from the action path or the action itself which is not a section action.
	 * last non-section is computed after the last context action.
	 * @return
	 */
	protected Action lastNonSection() {
		Action ret = activeAction;
		while (ret != null && isEffectiveSection(ret)) {
			ret = ret.getParent();
		}
		return ret;
	}	
	
	/**
	 * 
	 * @return true if action role is section or navigation and parent is effective section.
	 */
	protected boolean isEffectiveSection(Action action) {
		if (action == null) {
			return false;
		}
		if (action.isInRole(Action.Role.SECTION)) {
			return true;
		}
		if (action.isInRole(Action.Role.NAVIGATION)) {
			return isEffectiveSection(action.getParent());
		}
		return false;		
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
//		Fragment ret = viewGenerator.get(HTMLFactory.class).fragment();
//		boolean isContext = activeAction.isInRole(Action.Role.CONTEXT) || (activeAction.getParent() != null && activeAction.getParent().isInRole(Action.Role.CONTEXT));
		
		Action lastNonSection = /* isContext ? activeAction : */ lastNonSection();
		List<Action> lastNonSectionPath = lastNonSection.getPath();
		
		// Do not show breadcrumbs and title in the principal path, show otherwise
		boolean showBreadcrumbs = true;
		boolean showTitle = true;
		int breadcrumbsOffset = 1;
		
		if (lastNonSectionPath.size() == 0) {
			// Root
			showBreadcrumbs = false;
			showTitle = false;
		} else {
			Action root = lastNonSectionPath.get(0);
			if (lastNonSectionPath.size() == 1) {
				if (root.getNavigationChildren().indexOf(lastNonSection) == 0) {
					showBreadcrumbs = false;
					showTitle = false;
				}
			} else {
				Action firstChild = lastNonSectionPath.get(1);
				if (root.getNavigationChildren().indexOf(firstChild) == 0) {
					// Principal path
					showBreadcrumbs = lastNonSectionPath.size() > getMinBreadcrumbsDepth() - getBreadcrumbsOffset();
					breadcrumbsOffset = getBreadcrumbsOffset();
					
					showTitle = lastNonSectionPath.size() > getMinTitleDepth() - getBreadcrumbsOffset();
				}
			}
		}
				
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		String classPrefix = "nsd-app-content-panel-";
		Container contentContainer = bootstrapFactory.fluidContainer();
		
		if (showBreadcrumbs) {
			// Breadcrumb
			Breadcrumb breadcrumb = viewGenerator.get(BootstrapFactory.class).breadcrumb();
			breadcrumb.margin().top(Breakpoint.DEFAULT, Size.S1);

			contentContainer
				.row()
				.col(breadcrumb)
				.width(Breakpoint.DEFAULT, Size.NONE)
				.toHTMLElement()
				.addClass(classPrefix+"breadcrumb");
			
			ListIterator<Action> tit = lastNonSectionPath.listIterator(Math.min(lastNonSectionPath.size(), breadcrumbsOffset));
			ViewGenerator breadcrumbViewGenerator = viewGenerator.fork();
			breadcrumbViewGenerator.put(Decorator.SELECTOR_KEY, "content-panel.breadcrumb");
			while (tit.hasNext()) {
				breadcrumb.item(false, breadcrumbViewGenerator.link(tit.next()));
			}		
			breadcrumb.item(true, breadcrumbViewGenerator.label(lastNonSection));
		}
		
		if (showTitle) {			
			Col titleCol = contentContainer.row().col().width(Breakpoint.DEFAULT, Size.NONE);
			titleCol.toHTMLElement().addClass(classPrefix+"header-col");
			
			// Context actions navs floating right
			List<Action> contextChildren = lastNonSection.getContextChildren();
			if (!contextChildren.isEmpty()) {
				Navs navs = viewGenerator.categorizedLinkNavs(contextChildren, activeAction, null);
				navs._float().right();
				titleCol.content(navs);
			}
			
			// Page title, doesn't make much sense to show it for the root or principal actions - it would duplicate the header or the nav bar. 
			ViewGenerator titleViewGenerator = viewGenerator.fork();
			titleViewGenerator.put(Decorator.SELECTOR_KEY, "content-panel.title");			
			titleCol.content(titleViewGenerator.label(lastNonSection, titleViewGenerator.get(HTMLFactory.class).tag(TagName.h2)).addClass(classPrefix+"header"));
		}
		
		Row bodyRow = contentContainer.row();
		bodyRow.toHTMLElement().addClass(classPrefix+"body-row");
		
		List<Action> leftPanelActions = lastNonSection.getChildrenByRole(Action.Role.CONTENT_LEFT);
		if (!leftPanelActions.isEmpty()) {
			AdaptiveNavigationPanelViewPart panelViewPart = new AdaptiveNavigationPanelViewPart(leftPanelActions, activeAction);
			Col leftPanelCol = bodyRow.col().width(Breakpoint.DEFAULT, Size.AUTO);
			leftPanelCol.toHTMLElement().addClass(classPrefix+"left-nav");
			leftPanelCol.content(panelViewPart.generate(viewGenerator, progressMonitor));			 
		}	
		
//		List<Action> navigationPanelActions = getNavigationPanelActions();
//		return navigationPanelActions == null || navigationPanelActions.isEmpty() ? (vg, progressMonitor) -> null : new AdaptiveNavigationPanelViewPart(navigationPanelActions, getActiveAction());

		
		Col bodyCol = bodyRow.col().width(Breakpoint.DEFAULT, Size.NONE);
		bodyCol.toHTMLElement().addClass(classPrefix+"body");
		bodyCol.content(lastNonSection.generate(viewGenerator, progressMonitor));
		
		ViewPart sectionsViewPart = lastNonSection.createSectionsViewPart(activeAction, 0, 3);
		if (sectionsViewPart != null) {
			bodyCol.content(sectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
		}
				
		List<Action> rightPanelActions = lastNonSection.getChildrenByRole(Action.Role.CONTENT_RIGHT);
		if (!rightPanelActions.isEmpty()) {
			AdaptiveNavigationPanelViewPart panelViewPart = new AdaptiveNavigationPanelViewPart(rightPanelActions, activeAction);
			Col rightPanelCol = bodyRow.col().width(Breakpoint.DEFAULT, Size.AUTO);
			rightPanelCol.toHTMLElement().addClass(classPrefix+"right-nav");
			rightPanelCol.content(panelViewPart.generate(viewGenerator, progressMonitor));			 
		}	
		
		return contentContainer;
	}	
	
	/**
	 * Minimum action depth in the tree at which breadcrumbs are displayed. 
	 * This implementation returns 4, which means that the root, principal, and first level navigation
	 * actions are displayed without breadcrumbs. 
	 * @return
	 */
	protected int getMinBreadcrumbsDepth() {
		return 4;
	}
	
	/**
	 * Offset in breadcrumbs. This method returns 2 meaning that the root and principal action are not displayed in the breadcrumbs.
	 * Override for situations when navigation actions are not children of the principal action.
	 * @return
	 */
	protected int getBreadcrumbsOffset() {
		return 2;
	}
	
	/**
	 * Minimum action depth in the tree at which content title is displayed. 
	 * This implementation returns 3, which means that the root and principal actions content is
	 * displayed without a title.
	 * @return
	 */
	protected int getMinTitleDepth() {
		return 3;
	}
	
}
