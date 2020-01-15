package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Navs;

public class ActionNavsViewPart  implements ViewPart {
	
	private final int headerLevel;
	private final Action activeAction;
	private final List<Entry<Label, List<Action>>> categories;
	private final int sectionLevel;
	private boolean tabs;
	private boolean card;

	public ActionNavsViewPart(
			List<Entry<Label, List<Action>>> categories,
			Action activeAction, 
			int sectionLevel,
			int headerLevel, 
			boolean card,
			boolean tabs) {
		this.headerLevel = headerLevel;
		this.activeAction = activeAction;
		this.categories = categories;
		this.sectionLevel = sectionLevel;
		this.card = card;
		this.tabs = tabs;			
	}

	@Override
	public Object generate(ViewGenerator viewGen, ProgressMonitor progressMonitor) {		
		Action activeSection = null;
		for (Entry<Label, List<Action>> ce: categories) {
			for (Action section: ce.getValue()) {
				if (org.nasdanika.html.app.impl.Util.equalOrInPath(activeAction, section)) {
					activeSection = section;
				}
			}
		}
		
		ViewGenerator viewGenerator = viewGen.fork();
		viewGenerator.put(SectionStyle.HEADER_LEVEL, headerLevel);

		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		Card theCard = card ? bootstrapFactory.card() : null;  						
		
		Card.Navs navs = card ? theCard.asNavs() : bootstrapFactory.navs();
		if (tabs) {
			navs.tabs();
		} else {
			navs.pills();
		}
		
		for (Entry<Label, List<Action>> categoryEntry: categories) {
			Label category = categoryEntry.getKey(); 
			HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
			if (category == null || Util.isBlank(category.getText())) {
				for (Action section: categoryEntry.getValue()) {
					if (activeSection == null) {
						activeSection = section; // First if null.
					}
					String contentId = "nsd-action-content-" + (section.getId() == null ? htmlFactory.nextId() : section.getId());
					Fragment labelFragment = viewGenerator.labelFragment(section);

					Fragment contentFragment = viewGenerator.get(HTMLFactory.class).fragment();	
					if (section.getId() != null) {
						contentFragment.content(TagName.a.create().attribute("name", section.getId()));						
					}
					
					List<Action> contextChildren = section.getContextChildren();
					if (!contextChildren.isEmpty()) {
						Navs contextNavs = viewGenerator.categorizedLinkNavs(contextChildren, activeAction, null);
						contextNavs.background(Color.LIGHT);
						contentFragment.content(contextNavs);
					}
					
					contentFragment.content(section.generate(viewGenerator, progressMonitor));
					
					ViewPart subSectionsViewPart = section.createSectionsViewPart(activeAction, sectionLevel+1, headerLevel);
					if (subSectionsViewPart != null) {
						contentFragment.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
					}

					viewGenerator.decorate(navs.item(labelFragment, section == activeSection, section.isDisabled(), contentId, contentFragment), section);						
				}
			} else {
				String contentId = "nsd-category-content-" + (category.getId() == null ? htmlFactory.nextId() : category.getId());
				Fragment labelFragment = viewGenerator.labelFragment(category);

				Fragment contentFragment = viewGenerator.get(HTMLFactory.class).fragment();	
				if (category.getId() != null) {
					contentFragment.content(TagName.a.create().attribute("name", category.getId()));						
				}
				
				for (Action section: categoryEntry.getValue()) {
					ActionSectionViewPart sectionViewPart = new ActionSectionViewPart(section, activeAction, sectionLevel + 1, headerLevel + 1);
					contentFragment.content(sectionViewPart.generate(viewGenerator, progressMonitor));
				}							

				viewGenerator.decorate(navs.item(labelFragment, categoryEntry.getValue().contains(activeSection), false, contentId, contentFragment), category);													
			}
		}

		return card ? theCard : navs;
	}
}
