package org.nasdanika.html.app;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.viewparts.ActionCardViewPart;
import org.nasdanika.html.app.viewparts.ActionGroupViewPart;
import org.nasdanika.html.app.viewparts.ActionNavsViewPart;
import org.nasdanika.html.app.viewparts.ActionTableViewPart;
import org.nasdanika.html.app.viewparts.CardColumnsContainerViewPart;
import org.nasdanika.html.app.viewparts.CategoryCardViewPart;
import org.nasdanika.html.app.viewparts.CategorySectionViewPart;

/**
 * Defines style of child actions in Section role.
 * @author Pavel
 *
 */
public enum SectionStyle {
	
	/**
	 * Section style is selected based on the section level - Tabs for level 0, ACTION_GROUP for 1, and DEFAULT for the rest.
	 */
	AUTO("Auto") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int headerLevel) {
			switch (level) {
			case 0:
				return TAB.createViewPart(action, activeAction, level, headerLevel);
			case 1:
				return ACTION_GROUP.createViewPart(action, activeAction, level, headerLevel);
			default:
				return DEFAULT.createViewPart(action, activeAction, level, headerLevel);
			}
		}
		
	},
	
	ACTION_GROUP("Action group") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int sectionLevel, int headerLevel) {
			List<Map.Entry<Label, List<Action>>> categories = sectionLevel == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}
			
			return new ActionGroupViewPart(categories, activeAction, sectionLevel, headerLevel);
		}
		
	},	
	
	/**
	 * Sections and categories are generated as cards in card-columns. Number of columns is defined by Action.getSectionColumns() return value.
	 * Context actions are put in a centered footer. 
	 * Categories are created as cards with tabs. In this case context actions are put into the navs at the top of the cards content.
	 * Category/action card headers are rendered as header and consume paragraph level.
	 */
	CARD("Card") {
	
		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int sectionLevel, int headerLevel) {
			List<Map.Entry<Label, List<Action>>> categories = sectionLevel == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}	
			
			CardColumnsContainerViewPart ret = new CardColumnsContainerViewPart(action.getSectionColumns(), null);

			for (Entry<Label, List<Action>> categoryEntry: categories) {
				Label category = categoryEntry.getKey(); 
				
				if (category == null || Util.isBlank(category.getText())) {							
					// Action cards
					for (Action section: categoryEntry.getValue()) {
						ret.getCardViewParts().add(new ActionCardViewPart(section, activeAction, sectionLevel, headerLevel));
					}							
				} else {
					// Category card
					ret.getCardViewParts().add(new CategoryCardViewPart(category, categoryEntry.getValue(), activeAction, sectionLevel, headerLevel));
				}
			}

			return ret;
		}
		
	},		
		
	/**
	 * Sections are generated as card pills.
	 */
	CARD_PILL("Card pill") {
	
		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int sectionLevel, int headerLevel) {
			List<Map.Entry<Label, List<Action>>> categories = sectionLevel == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}			
			
			return new ActionNavsViewPart(categories, activeAction, sectionLevel, headerLevel, true, false);
		}
		
	},
	
	/**
	 * Sections are generated as card tabs.
	 */
	CARD_TAB("Card tab") {
	
		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int sectionLevel, int headerLevel) {
			List<Map.Entry<Label, List<Action>>> categories = sectionLevel == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}			
			
			return new ActionNavsViewPart(categories, activeAction, sectionLevel, headerLevel, true, true);
		}
		
	},
	
	/**
	 * Sections are generated as blocks with Hx headers where x starts with 3 and increases for each additional paragraph level up to H6
	 */
	DEFAULT("Default") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int sectionLevel, int headerLevel) {
			List<Map.Entry<Label, List<Action>>> categories = sectionLevel == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}
			
			return new ViewPart() {
				
				@Override
				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
					Fragment contentFragment = viewGenerator.get(HTMLFactory.class).fragment();
					
					ViewGenerator vg = viewGenerator.fork();
					vg.put(HEADER_LEVEL, headerLevel);
					for (Entry<Label, List<Action>> categoryEntry: categories) {
						CategorySectionViewPart vp = new CategorySectionViewPart(categoryEntry.getKey(), categoryEntry.getValue(), activeAction, sectionLevel, headerLevel);
						contentFragment.content(vp.generate(vg, progressMonitor));
					}

					return contentFragment;
				}
			};
		}
		
	},
		
	/**
	 * Sections are generated as pills.
	 */
	PILL("Pill") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int sectionLevel, int headerLevel) {
			List<Map.Entry<Label, List<Action>>> categories = sectionLevel == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}			
			
			return new ActionNavsViewPart(categories, activeAction, sectionLevel, headerLevel, false, false);
		}
		
	},
	
	/**
	 * Sections are generated as tabs.
	 */
	TAB("Tab") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int sectionLevel, int headerLevel) {
			List<Map.Entry<Label, List<Action>>> categories = sectionLevel == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}			
			
			return new ActionNavsViewPart(categories, activeAction, sectionLevel, headerLevel, false, true);
		}
		
	},
		
	/**
	 * Sections are generated as rows in a table with two columns - header with action label, and content. Categories are displayed in the top row.
	 */
	TABLE("Table") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int sectionLevel, int headerLevel) {
			List<Map.Entry<Label, List<Action>>> categories = sectionLevel == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}
			
			return new ActionTableViewPart(categories, activeAction, sectionLevel, headerLevel);
		}
		
	}
	
	;
	
//	PILL
//	VerticalPill
//	Card
//	Accordion
	
	/**
	 * Context property name for header level.
	 */
	public static final String HEADER_LEVEL = SectionStyle.class.getName() + ":header-level";	
	
	/**
	 * Creates a view part rendering action's sections.
	 * @param section Action containing section actions to be rendered. 
	 * @param activeAction
	 * @param showContextActions
	 * @param level Section nesting level
	 * @return
	 */
	public abstract ViewPart createViewPart(Action action, Action activeAction, int level, int headerLevel);
	
	public final String label;
	
//	private SectionStyle() {
//		this(null);
//	}
	
	private SectionStyle(String label) {		
		this.label = Util.isBlank(label) ? name() : label;
	}	
	
	public static SectionStyle fromLabel(String label) {
		if ("Paragraph".equals(label)) {
			return DEFAULT;
		}
		for (SectionStyle sectionStyle: values()) {
			if (sectionStyle.label.equals(label)) {
				return sectionStyle;
			}
		}
		throw new IllegalArgumentException("No breakpoint value for label "+label);
	}
	
	
}
