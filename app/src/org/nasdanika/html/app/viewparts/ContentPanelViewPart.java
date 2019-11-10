package org.nasdanika.html.app.viewparts;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumbs;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Size;

/**
 * Constructs content view part as nested sections. 
 * @author Pavel Vlasov
 *
 */
public class ContentPanelViewPart implements ViewPart {

	private Action activeAction;
	private boolean showContextActions;

	/**
	 * 
	 * @param activeAction
	 * @param input
	 * @param showConextActions Set false for principal actions because their context actions are already shown in the navbar.
	 */
	public ContentPanelViewPart(Action activeAction, boolean showConextActions) {
		this.activeAction = activeAction;
		this.showContextActions = false;
	}
		
	/**
	 * Returns the last action from the action path or the action itself which is not a section action.
	 * @return
	 */
	protected Action lastNonSection() {
		List<Action> fullPath = new ArrayList<>(activeAction.getPath());
		fullPath.add(activeAction);
		for (int i = 0; i < fullPath.size() - 1; ++i) {
			Action currentAction = fullPath.get(i);
			if (Util.contains(currentAction.getSectionChildren(), fullPath.get(i + 1))) {
				return currentAction;
			}
		}
		return activeAction;
	}	

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		Fragment ret = viewGenerator.get(HTMLFactory.class).fragment();
		Action lastNonSection = lastNonSection();
		List<Action> lastNonSectionPath = lastNonSection.getPath();
		if (lastNonSectionPath.size() > getMinBreadcrumbsDepth() - getBreadcrumbsOffset()) {
			// Breadcrumbs
			Breadcrumbs breadcrumbs = viewGenerator.get(BootstrapFactory.class).breadcrums();
			breadcrumbs.margin().top(DeviceSize.EXTRA_SMALL, Size.S1);
			ret.content(breadcrumbs);
			ListIterator<Action> tit = lastNonSectionPath.listIterator(Math.min(lastNonSectionPath.size(), getBreadcrumbsOffset()));
			while (tit.hasNext()) {
				breadcrumbs.item(false, viewGenerator.link(tit.next()));
			}		
			breadcrumbs.item(true, viewGenerator.label(lastNonSection));
		}
		
		if (lastNonSectionPath.size() > getMinTitleDepth() - getBreadcrumbsOffset()) {
			// Page title, doesn't make much sense to show it for the root or principal actions - it would duplicate the header or the nav bar. 
			ret.content(viewGenerator.label(lastNonSection, viewGenerator.get(HTMLFactory.class).tag(TagName.h2)));			
		}
		
		ret.content(new SectionViewPart(lastNonSection, activeAction, showContextActions, 0).generate(viewGenerator, progressMonitor));		
		return ret;
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
