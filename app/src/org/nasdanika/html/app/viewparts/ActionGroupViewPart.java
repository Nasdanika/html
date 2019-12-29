package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Size;

/**
 * Generates a card from action and its section children.
 * @author Pavel
 *
 */
public class ActionGroupViewPart implements ViewPart {

	private final int headerLevel;
	private final Action activeAction;
	private final List<Entry<Label, List<Action>>> categories;
	private final int sectionLevel;
	private Action activeSection;

	public ActionGroupViewPart(
			List<Entry<Label, List<Action>>> categories,
			Action activeAction, 
			int sectionLevel,
			int headerLevel) {
		this.headerLevel = headerLevel;
		this.activeAction = activeAction;
		this.categories = categories;
		this.sectionLevel = sectionLevel;
		
		for (Entry<Label, List<Action>> ce: categories) {
			for (Action section: ce.getValue()) {
				if (org.nasdanika.html.app.impl.Util.equalOrInPath(activeAction, section)) {
					activeSection = section;
				}
			}
		}
		
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		ActionGroup actionGroup = viewGenerator.get(BootstrapFactory.class).actionGroup(false);
		
		for (Entry<Label, List<Action>> categoryEntry: categories) {
			Label category = categoryEntry.getKey(); 
			if (category == null || Util.isBlank(category.getText())) {
				for (Action section: categoryEntry.getValue()) {
					if (activeSection == null) {
						activeSection = section; // First if null.
					}
					String contentId = section.getId() == null ? null : "nsd-action-content-"+section.getId();
					Fragment labelFragment = viewGenerator.labelFragment(section);

					Fragment contentFragment = viewGenerator.get(HTMLFactory.class).fragment();	
					if (section.getId() != null) {
						contentFragment.content(TagName.a.create().attribute("name", section.getId()));						
					}
					
					List<Action> contextChildren = section.getContextChildren();
					if (!contextChildren.isEmpty()) {
						Navs navs = viewGenerator.categorizedLinkNavs(contextChildren, activeAction, null);
						navs.background(Color.LIGHT);
						contentFragment.content(navs);
					}
					
					contentFragment.content(section.generate(viewGenerator, progressMonitor));
					
					ViewPart subSectionsViewPart = section.createSectionsViewPart(activeAction, sectionLevel+1, headerLevel);
					if (subSectionsViewPart != null) {
						contentFragment.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
					}
					
					Tag ca = actionGroup.contentAction(labelFragment, section == activeSection, section.isDisabled(), section.getColor(), contentId, contentFragment);
					viewGenerator.decorate(ca, section);
				}
			} else {
				String contentId = category.getId() == null ? null : "nsd-category-content-"+category.getId();
				Fragment labelFragment = viewGenerator.labelFragment(category);

				Fragment contentFragment = viewGenerator.get(HTMLFactory.class).fragment();	
				if (category.getId() != null) {
					contentFragment.content(TagName.a.create().attribute("name", category.getId()));						
				}
				
				for (Action section: categoryEntry.getValue()) {
					ActionSectionViewPart sectionViewPart = new ActionSectionViewPart(section, activeAction, sectionLevel + 1, headerLevel + 1);
					contentFragment.content(sectionViewPart.generate(viewGenerator, progressMonitor));
				}							

				viewGenerator.decorate(actionGroup.contentAction(labelFragment, categoryEntry.getValue().contains(activeSection), false, category.getColor(), contentId, contentFragment), category);													
			}
		}

		return actionGroup.asContainer(true).margin().top(Breakpoint.DEFAULT, Size.S1).toBootstrapElement();		
	}

}
