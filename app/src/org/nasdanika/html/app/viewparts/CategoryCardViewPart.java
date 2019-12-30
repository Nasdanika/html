package org.nasdanika.html.app.viewparts;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

/**
 * Generates a card from action and its section children.
 * @author Pavel
 *
 */
public class CategoryCardViewPart implements ViewPart {

	private Label category;
	private List<Action> categoryActions;
	private Action activeAction;
	private int sectionLevel;
	private int headerLevel;
	Action activeSection = null;

	/**
	 * Category can be null.
	 * @param action Action to generate card from 
	 * @param sectionLevel section level is used if section style is AUTO.
	 * @param headerLevel Header level. 
	 */
	public CategoryCardViewPart(Label category, List<Action> categoryActions, Action activeAction, int sectionLevel, int headerLevel) {
		this.category = category;
		this.categoryActions = categoryActions;
		this.activeAction = activeAction;
		this.sectionLevel = sectionLevel;
		this.headerLevel = headerLevel;
				
		for (Action section: categoryActions) {
			if (org.nasdanika.html.app.impl.Util.equalOrInPath(activeAction, section)) {
				activeSection = section;
			}
		}		
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
		Card card = bootstrapFactory.card();
		if (category != null) {
			Tag hTag = htmlFactory.tag("H"+Math.min(6, headerLevel));
			viewGenerator.label(category, hTag);
			hTag.addClass("card-header");
			card.toHTMLElement().content(hTag);
			
			if (category.getColor() != null) {
				card.border(category.getColor());
				TagBootstrapElement wrapped = bootstrapFactory.wrap(hTag);
				wrapped.background(category.getColor());
				wrapped.border(category.getColor());
			}
		}
		
		org.nasdanika.html.bootstrap.Card.Navs navs = card.asNavs();
		navs.tabs();
		
		for (Action section: categoryActions) {
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
			
			ViewPart subSectionsViewPart = section.createSectionsViewPart(activeAction, sectionLevel+1, headerLevel + 1);
			if (subSectionsViewPart != null) {
				contentFragment.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
			}

			viewGenerator.decorate(navs.item(labelFragment, section == activeSection, section.isDisabled(), contentId, contentFragment), section);
		}
		return card;
	}

}
