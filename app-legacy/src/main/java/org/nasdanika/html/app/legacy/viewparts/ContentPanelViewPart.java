package org.nasdanika.html.app.viewparts;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.viewparts.AdaptiveNavigationPanelViewPart.Style;
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
	
	private static final String CLASS_PREFIX = "nsd-app-content-panel-";	

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
//		Fragment ret = viewGenerator.getHTMLFactory().fragment();
//		boolean isContext = activeAction.isInRole(Action.Role.CONTEXT) || (activeAction.getParent() != null && activeAction.getParent().isInRole(Action.Role.CONTEXT));
		
		Action lastNonSection = /* isContext ? activeAction : */ lastNonSection();
		List<Action> lastNonSectionPath = lastNonSection == null ? Collections.emptyList() : lastNonSection.getPath();
		
		// Do not show breadcrumbs and title in the principal path, show otherwise
		boolean showBreadcrumb = true;
		boolean showTitle = true;
		int breadcrumbsOffset = 1;
		
		if (lastNonSectionPath.size() == 0) {
			// Root
			showBreadcrumb = false;
			showTitle = false;
		} else {
			Action root = lastNonSectionPath.get(0);
			List<Action> navigationChildren = root.getNavigationChildren();
			if (lastNonSectionPath.size() == 1) {
				if (navigationChildren.indexOf(lastNonSection) == 0) {
					showBreadcrumb = false;
					showTitle = false;
				}
			} else {
				Action firstChild = lastNonSectionPath.get(1);
				if (navigationChildren.indexOf(firstChild) == 0) {
					// Principal path
					showBreadcrumb = lastNonSectionPath.size() > getMinBreadcrumbsDepth() - getBreadcrumbsOffset();
					breadcrumbsOffset = getBreadcrumbsOffset();
					
					showTitle = lastNonSectionPath.size() > getMinTitleDepth() - getBreadcrumbsOffset();
				}
			}
		}
				
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		Container contentContainer = bootstrapFactory.fluidContainer();
		
		if (showBreadcrumb) {
			// Breadcrumb
			ListIterator<Action> bit = lastNonSectionPath.listIterator(Math.min(lastNonSectionPath.size(), breadcrumbsOffset));
			ViewGenerator breadcrumbViewGenerator = createBreadcrumbViewGenerator(viewGenerator);
			Breadcrumb breadcrumb = createBreadcrumb(breadcrumbViewGenerator, contentContainer);			
			while (bit.hasNext()) {				
				Action breadcrumbItem = bit.next();
				Label bic = breadcrumbItem.getCategory();
				if (bic != null) {
					breadcrumb.item(false, breadcrumbViewGenerator.label(bic));					
				}
				breadcrumb.item(false, breadcrumbItem.getActivator() == null ? breadcrumbViewGenerator.label(breadcrumbItem) : breadcrumbViewGenerator.link(breadcrumbItem));
			}		
			Label bic = lastNonSection.getCategory();
			if (bic != null) {
				breadcrumb.item(false, breadcrumbViewGenerator.label(bic));					
			}
			breadcrumb.item(true, breadcrumbViewGenerator.label(lastNonSection));
		}
		
		if (showTitle) {			
			if (!showBreadcrumb && lastNonSection.getCategory() != null && !Util.isBlank(lastNonSection.getCategory().getText())) {
				// Title category breadcrumb
				ViewGenerator breadcrumbViewGenerator = createBreadcrumbViewGenerator(viewGenerator);
				createBreadcrumb(breadcrumbViewGenerator, contentContainer).item(false, breadcrumbViewGenerator.label(lastNonSection.getCategory()));					
			}			
						
			Col titleCol = contentContainer.row().col().width(Breakpoint.DEFAULT, Size.NONE);
			titleCol.toHTMLElement().addClass(CLASS_PREFIX+"header-col");
			
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
			Tag titleHeader = titleViewGenerator.getHTMLFactory().tag(TagName.h2);
			titleCol.content(titleViewGenerator.label(lastNonSection, titleHeader).addClass(CLASS_PREFIX+"header"));
			
			titleHeader.content(org.nasdanika.html.app.impl.Util.descriptionModal(viewGenerator, lastNonSection));
		}
		
		Row bodyRow = contentContainer.row();
		bodyRow.toHTMLElement().addClass(CLASS_PREFIX+"body-row");
		
		Predicate<? super Action> inlinePredicate = a -> a.getActivator() != null && a.getActivator().inline();
		List<Action> leftPanelActions = lastNonSection == null ? Collections.emptyList() : lastNonSection.getChildrenByRole(Action.Role.CONTENT_LEFT);
		if (!leftPanelActions.isEmpty()) {
			Col leftPanelCol = bodyRow.col().width(Breakpoint.DEFAULT, Size.AUTO);
			leftPanelCol.toHTMLElement().addClass(CLASS_PREFIX+"left-nav");
			List<Action> notInlineLeftPanelActions = leftPanelActions.stream().filter(inlinePredicate.negate()).collect(Collectors.toList());
			if (!notInlineLeftPanelActions.isEmpty()) {
				AdaptiveNavigationPanelViewPart panelViewPart = new AdaptiveNavigationPanelViewPart(notInlineLeftPanelActions, activeAction, Action.Role.CONTENT_LEFT, getLeftPanelStyle());
				leftPanelCol.content(panelViewPart.generate(viewGenerator, progressMonitor));			 
			}	
			for (Action inlineAction: leftPanelActions.stream().filter(inlinePredicate).collect(Collectors.toList())) {
				leftPanelCol.content(inlineAction.generate(viewGenerator, progressMonitor));
			}
		}
		
//		List<Action> navigationPanelActions = getNavigationPanelActions();
//		return navigationPanelActions == null || navigationPanelActions.isEmpty() ? (vg, progressMonitor) -> null : new AdaptiveNavigationPanelViewPart(navigationPanelActions, getActiveAction());
		
		Col bodyCol = bodyRow.col().width(Breakpoint.DEFAULT, Size.NONE);
		bodyCol.toHTMLElement().addClass(CLASS_PREFIX+"body");
		if (lastNonSection != null) {
			bodyCol.content(lastNonSection.generate(viewGenerator, progressMonitor));
		}
		
		ViewPart sectionsViewPart = lastNonSection.createSectionsViewPart(activeAction, 0, 3);
		if (sectionsViewPart != null) {
			bodyCol.content(sectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
		}
				
		List<Action> rightPanelActions = lastNonSection.getChildrenByRole(Action.Role.CONTENT_RIGHT);
		if (!rightPanelActions.isEmpty()) {
			Col rightPanelCol = bodyRow.col().width(Breakpoint.DEFAULT, Size.AUTO);
			rightPanelCol.toHTMLElement().addClass(CLASS_PREFIX+"right-nav");
			List<Action> notInlineRightPanelActions = rightPanelActions.stream().filter(inlinePredicate.negate()).collect(Collectors.toList());
			if (!notInlineRightPanelActions.isEmpty()) {
				AdaptiveNavigationPanelViewPart panelViewPart = new AdaptiveNavigationPanelViewPart(notInlineRightPanelActions, activeAction, Action.Role.CONTENT_RIGHT, getRightPanelStyle());
				rightPanelCol.content(panelViewPart.generate(viewGenerator, progressMonitor));			 
			}	
			for (Action inlineAction: rightPanelActions.stream().filter(inlinePredicate).collect(Collectors.toList())) {
				rightPanelCol.content(inlineAction.generate(viewGenerator, progressMonitor));
			}
		}
		
		return contentContainer;
	}

	protected Style getLeftPanelStyle() {
		return Style.AUTO;
	}

	protected Style getRightPanelStyle() {
		return Style.AUTO;
	}

	protected ViewGenerator createBreadcrumbViewGenerator(ViewGenerator viewGenerator) {
		ViewGenerator breadcrumbViewGenerator = viewGenerator.fork();
		breadcrumbViewGenerator.put(Decorator.SELECTOR_KEY, "content-panel.breadcrumb");
		return breadcrumbViewGenerator;
	}

	protected Breadcrumb createBreadcrumb(ViewGenerator viewGenerator, Container contentContainer) {
		Breadcrumb breadcrumb = viewGenerator.getBootstrapFactory().breadcrumb();
		breadcrumb.margin().top(Breakpoint.DEFAULT, Size.S1);

		contentContainer
			.row()
			.col(breadcrumb)
			.width(Breakpoint.DEFAULT, Size.NONE)
			.toHTMLElement()
			.addClass(CLASS_PREFIX+"breadcrumb");
		return breadcrumb;
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
