package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.app.viewparts.AdaptiveNavigationPanelViewPart.Style;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Container.Row.Col;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Size;

/**
 * Renders action as header and content.
 * @author Pavel
 *
 */
public class ActionSectionViewPart  implements ViewPart {
	private Action action;
	private Action activeAction;
	private int sectionLevel;
	private int headerLevel;

	public ActionSectionViewPart(Action action, Action activeAction, int sectionLevel, int headerLevel) {
		this.action = action;
		this.activeAction = activeAction;
		this.sectionLevel = sectionLevel;
		this.headerLevel = headerLevel;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		HTMLFactory htmlFactory = viewGenerator.getHTMLFactory();
		Tag sectionDiv = htmlFactory.div();
		sectionDiv.content(ViewPartsUtil.sectionAnchor(action));						
		
		List<Action> contextChildren = action.getContextChildren();
		if (!contextChildren.isEmpty()) {
			Navs navs = viewGenerator.categorizedLinkNavs(contextChildren, activeAction, null);
			navs._float().right();
			sectionDiv.content(navs);
		}
		
		Tag hTag = htmlFactory.tag("H"+Math.min(6, headerLevel));
		viewGenerator.label(action, hTag);
		viewGenerator.decorate(hTag, action);
		if (action.getColor() != null) {
			viewGenerator.getBootstrapFactory().wrap(hTag).background(action.getColor());
		}	
		hTag.content(Util.descriptionModal(viewGenerator, action));
		sectionDiv.content(hTag);
		
		Predicate<? super Action> inlinePredicate = a -> a.getActivator() != null && a.getActivator().inline();
		List<Action> leftPanelActions = action.getChildrenByRole(Action.Role.CONTENT_LEFT);
		List<Action> rightPanelActions = action.getChildrenByRole(Action.Role.CONTENT_RIGHT);		
		if (leftPanelActions.isEmpty() && rightPanelActions.isEmpty()) {
			sectionDiv.content(action.generate(viewGenerator, progressMonitor));			
		} else {
			BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
			Container contentContainer = bootstrapFactory.fluidContainer();

			Row bodyRow = contentContainer.row();
			if (!leftPanelActions.isEmpty()) {
				Col leftPanelCol = bodyRow.col().width(Breakpoint.DEFAULT, Size.AUTO);
				List<Action> notInlineLeftPanelActions = leftPanelActions.stream().filter(inlinePredicate.negate()).collect(Collectors.toList());
				if (!notInlineLeftPanelActions.isEmpty()) {
					AdaptiveNavigationPanelViewPart panelViewPart = new AdaptiveNavigationPanelViewPart(notInlineLeftPanelActions, activeAction, Action.Role.CONTENT_LEFT, Style.AUTO);
					leftPanelCol.content(panelViewPart.generate(viewGenerator, progressMonitor));			 
				}	
				for (Action inlineAction: leftPanelActions.stream().filter(inlinePredicate).collect(Collectors.toList())) {
					leftPanelCol.content(inlineAction.generate(viewGenerator, progressMonitor));
				}
			}
			Col bodyCol = bodyRow.col().width(Breakpoint.DEFAULT, Size.NONE);
			bodyCol.content(action.generate(viewGenerator, progressMonitor));
			
			if (!rightPanelActions.isEmpty()) {
				Col rightPanelCol = bodyRow.col().width(Breakpoint.DEFAULT, Size.AUTO);
				List<Action> notInlineRightPanelActions = rightPanelActions.stream().filter(inlinePredicate.negate()).collect(Collectors.toList());
				if (!notInlineRightPanelActions.isEmpty()) {
					AdaptiveNavigationPanelViewPart panelViewPart = new AdaptiveNavigationPanelViewPart(notInlineRightPanelActions, activeAction, Action.Role.CONTENT_RIGHT, Style.AUTO);
					rightPanelCol.content(panelViewPart.generate(viewGenerator, progressMonitor));			 
				}	
				for (Action inlineAction: rightPanelActions.stream().filter(inlinePredicate).collect(Collectors.toList())) {
					rightPanelCol.content(inlineAction.generate(viewGenerator, progressMonitor));
				}
			}
			
			sectionDiv.content(contentContainer.toHTMLElement());			
		}
		
		ViewPart subSectionsViewPart = action.createSectionsViewPart(activeAction, sectionLevel+1, headerLevel + 1);
		if (subSectionsViewPart != null) {
			sectionDiv.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
		}
		return sectionDiv;
	}
	
}


