package org.nasdanika.html.app.viewparts;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.Breadcrumbs;

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
	 * @param showConextActions Set false for principal actions because their context actions are alredy shown in the navbar.
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
	public Object generate(ViewGenerator viewGenerator) {
		Fragment ret = viewGenerator.getHTMLFactory().fragment();
		Action lastNonSection = lastNonSection();
		if (lastNonSection.getPath().size() > getMinBreadcrumbsDepth() - 2) {
			// Breadcrumbs
			Breadcrumbs breadcrumbs = viewGenerator.getBootstrapFactory().breadcrums();
			breadcrumbs.margin().top(1);
			ret.content(breadcrumbs);
			ListIterator<Action> tit = lastNonSection.getPath().listIterator(2);
			while (tit.hasNext()) {
				breadcrumbs.item(false, viewGenerator.link(tit.next()));
			}		
			breadcrumbs.item(true, viewGenerator.label(lastNonSection));
		}
		
		if (lastNonSection.getPath().size() > getMinTitleDepth() - 2) {
			// Page title, doesn't make much sense to show it for the root or principal actions - it would duplicate the header or the nav bar. 
			ret.content(viewGenerator.label(lastNonSection, viewGenerator.getHTMLFactory().tag(TagName.h2)));			
		}
		
		ret.content(new SectionViewPart(lastNonSection, activeAction, showContextActions, 0).generate(viewGenerator));		
		return ret;
	}	
	
	/**
	 * Minimum action depth in the tree at which breadcrumbs are displayed. 
	 * This implementation returns 4, which means that the root, principlal, and first level navigation
	 * actions are displayed without breadcrumbs. 
	 * @return
	 */
	protected int getMinBreadcrumbsDepth() {
		return 4;
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
