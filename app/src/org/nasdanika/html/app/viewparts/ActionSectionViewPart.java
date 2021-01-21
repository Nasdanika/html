package org.nasdanika.html.app.viewparts;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Navs;

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
		
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
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
			viewGenerator.get(BootstrapFactory.class).wrap(hTag).background(action.getColor());
		}	
		hTag.content(Util.descriptionModal(viewGenerator, action));
		sectionDiv.content(hTag);
		
		sectionDiv.content(action.generate(viewGenerator, progressMonitor));
		
		ViewPart subSectionsViewPart = action.createSectionsViewPart(activeAction, sectionLevel+1, headerLevel + 1);
		if (subSectionsViewPart != null) {
			sectionDiv.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
		}
		return sectionDiv;
	}

}
