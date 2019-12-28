package org.nasdanika.html.app;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Table.TableBody;
import org.nasdanika.html.bootstrap.Table.TableHeader;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

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
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			switch (level) {
			case 0:
				return TAB.createViewPart(action, activeAction, level, paragraphLevel);
			case 1:
				return ACTION_GROUP.createViewPart(action, activeAction, level, paragraphLevel);
			default:
				return DEFAULT.createViewPart(action, activeAction, level, paragraphLevel);
			}
		}
		
	},
	
	ACTION_GROUP("Action group") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Map.Entry<Label, List<Action>>> categories = level == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}
			
			return new ViewPart() {
				
				@Override
				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
					Action activeSection = null;
					for (Entry<Label, List<Action>> ce: categories) {
						for (Action section: ce.getValue()) {
							if (org.nasdanika.html.app.impl.Util.equalOrInPath(activeAction, section)) {
								activeSection = section;
							}
						}
					}
					
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
								
								ViewPart subSectionsViewPart = section.createSectionsViewPart(activeAction, level+1, paragraphLevel);
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
								contentFragment.content(sectionParagraph(section, activeAction, level + 1, paragraphLevel + 1, viewGenerator, progressMonitor));
							}							
	
							viewGenerator.decorate(actionGroup.contentAction(labelFragment, categoryEntry.getValue().contains(activeSection), false, category.getColor(), contentId, contentFragment), category);													
						}
					}

					return actionGroup.asContainer(true).margin().top(Breakpoint.DEFAULT, Size.S1).toBootstrapElement();
				}
			};
		}
		
	},	
	
	/**
	 * Sections and categories are generated as cards in card-columns. Number of columns is defined by section-card-column-count context property. Defaults to 3.
	 * Context actions are put in a centered footer. 
	 * Categories are created as cards with tabs. In this case context actions are put into the navs at the top of the cards content.
	 * Category/action card headers are rendered as header and consume paragraph level.
	 */
	CARD("Card") {
	
		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Map.Entry<Label, List<Action>>> categories = level == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}			
			
			return new ViewPart() {

				@Override
				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
					Action activeSection = null;
					for (Entry<Label, List<Action>> ce: categories) {
						for (Action section: ce.getValue()) {
							if (org.nasdanika.html.app.impl.Util.equalOrInPath(activeAction, section)) {
								activeSection = section;
							}
						}
					}
					
					BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
					
					HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
					Tag ret = htmlFactory.div();
					ret.addClass("card-columns");
					int columnCount = action.getSectionColumns();
					if (columnCount > 0 && columnCount != 3) {
						ret.style("column-count", columnCount);
					}
					
					for (Entry<Label, List<Action>> categoryEntry: categories) {
						Label category = categoryEntry.getKey(); 
						
						if (category == null || Util.isBlank(category.getText())) {							
							// Action cards
							for (Action section: categoryEntry.getValue()) {
								Card card = bootstrapFactory.card();
								ret.content(card);
								
								Tag hTag = htmlFactory.tag("H"+Math.min(6, paragraphLevel), viewGenerator.labelFragment(section));
								hTag.addClass("card-header");
								if (section.getColor() != null) {
									card.border(section.getColor());
									TagBootstrapElement wrapped = bootstrapFactory.wrap(hTag);
									wrapped.background(section.getColor());
									wrapped.border(section.getColor());
								}
								card.toHTMLElement().content(hTag);
								
								TagBootstrapElement body = card.getBody();
								Tag bodyHtmlElement = body.toHTMLElement();
								bodyHtmlElement.content(section.generate(viewGenerator, progressMonitor));
								
								ViewPart subSectionsViewPart = section.createSectionsViewPart(activeAction, level+1, paragraphLevel+1);
								if (subSectionsViewPart != null) {
									bodyHtmlElement.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
								}

								List<Action> contextChildren = section.getContextChildren();
								if (!contextChildren.isEmpty()) {
									Navs contextNavs = viewGenerator.categorizedLinkNavs(contextChildren, activeAction, null);
//									contextNavs.background(Color.LIGHT);
									TagBootstrapElement footer = card.getFooter();
									footer.toHTMLElement().content(contextNavs);
								}
							}							
						} else {
							// Category card
							Card card = bootstrapFactory.card();
							ret.content(card);
							
							Tag hTag = htmlFactory.tag("H"+Math.min(6, paragraphLevel), viewGenerator.labelFragment(category));
							hTag.addClass("card-header");
							card.toHTMLElement().content(hTag);
							
							if (category.getColor() != null) {
								card.border(category.getColor());
								TagBootstrapElement wrapped = bootstrapFactory.wrap(hTag);
								wrapped.background(category.getColor());
								wrapped.border(category.getColor());
							}
							
							org.nasdanika.html.bootstrap.Card.Navs navs = card.asNavs();
							navs.tabs();
							
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
								
								ViewPart subSectionsViewPart = section.createSectionsViewPart(activeAction, level+1, paragraphLevel + 1);
								if (subSectionsViewPart != null) {
									contentFragment.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
								}

								viewGenerator.decorate(navs.item(labelFragment, section == activeSection, section.isDisabled(), contentId, contentFragment), section);						
							}
						}
					}

					return ret;
				}
				
			};
		}
		
	},		
		
	/**
	 * Sections are generated as card pills.
	 */
	CARD_PILL("Card pill") {
	
		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Map.Entry<Label, List<Action>>> categories = level == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}			
			
			return new NavsViewPart(paragraphLevel, activeAction, categories, level, true, false);
		}
		
	},
	
	/**
	 * Sections are generated as card tabs.
	 */
	CARD_TAB("Card tab") {
	
		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Map.Entry<Label, List<Action>>> categories = level == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}			
			
			return new NavsViewPart(paragraphLevel, activeAction, categories, level, true, true);
		}
		
	},
	
	/**
	 * Sections are generated as blocks with Hx headers where x starts with 3 and increases for each additional paragraph level up to H6
	 */
	DEFAULT("Default") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Map.Entry<Label, List<Action>>> categories = level == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}
			
			return new ViewPart() {
				
				@Override
				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
					Fragment contentFragment = viewGenerator.get(HTMLFactory.class).fragment();
					
					for (Entry<Label, List<Action>> categoryEntry: categories) {
						Label category = categoryEntry.getKey();
						if (category == null || Util.isBlank(category.getText())) {
							if (category != null) {
								contentFragment.content(TagName.hr.create());
							}
							for (Action section: categoryEntry.getValue()) {						
								contentFragment.content(sectionParagraph(section, activeAction, level, paragraphLevel, viewGenerator, progressMonitor));
							}							
						} else {
							HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
							if (category.getId() != null) {
								contentFragment.content(TagName.a.create().attribute("name", category.getId()));						
							}
							
							Tag hTag = htmlFactory.tag("H"+Math.min(6, paragraphLevel), viewGenerator.labelFragment(category));
							viewGenerator.decorate(hTag, category);
							if (category.getColor() != null) {
								viewGenerator.get(BootstrapFactory.class).wrap(hTag).background(category.getColor());
							}
							
							contentFragment.content(hTag);

							for (Action section: categoryEntry.getValue()) {
								contentFragment.content(sectionParagraph(section, activeAction, level + 1, paragraphLevel + 1, viewGenerator, progressMonitor));
							}							
						}
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
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Map.Entry<Label, List<Action>>> categories = level == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}			
			
			return new NavsViewPart(paragraphLevel, activeAction, categories, level, false, false);
		}
		
	},
	
	/**
	 * Sections are generated as tabs.
	 */
	TAB("Tab") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Map.Entry<Label, List<Action>>> categories = level == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}			
			
			return new NavsViewPart(paragraphLevel, activeAction, categories, level, false, true);
		}
		
	},
		
	/**
	 * Sections are generated as rows in a table with two columns - header with action label, and content. Categories are displayed in the top row.
	 */
	TABLE("Table") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Map.Entry<Label, List<Action>>> categories = level == 0 ? action.getChildrenGroupedByCategory(Action.Role.SECTION) : action.getChildrenGroupedByCategory(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (categories.isEmpty()) {
				return null;
			}
			
			return new ViewPart() {
				
				@Override
				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
					Fragment ret = viewGenerator.get(HTMLFactory.class).fragment();
					
					for (Entry<Label, List<Action>> categoryEntry: categories) {
						Label category = categoryEntry.getKey();
						
						org.nasdanika.html.bootstrap.Table table = viewGenerator.get(BootstrapFactory.class).table().bordered();
						ret.content(table);
												
						if (category != null && !Util.isBlank(category.getText())) {
							TableHeader header = table.header();
							Row hRow = header.row();
							if (category.getColor() == null) {
								header.light();
							} else {
								hRow.color(category.getColor());
							}
							
							hRow.header(viewGenerator.labelFragment(category)).toHTMLElement().colspan(2);
						} 
						
						TableBody body = table.body();
						for (Action section: categoryEntry.getValue()) {
							Row sectionRow = body.row();
							if (section.getColor() != null) {
								sectionRow.color(section.getColor());
							}
							sectionRow.header(viewGenerator.labelFragment(section));
							
							Fragment contentFragment = viewGenerator.get(HTMLFactory.class).fragment();	
							if (section.getId() != null) {
								contentFragment.content(TagName.a.create().attribute("name", section.getId()));						
							}
							
							List<Action> contextChildren = section.getContextChildren();
							if (!contextChildren.isEmpty()) {								
								Navs navs = viewGenerator.categorizedLinkNavs(contextChildren, activeAction, null);
								navs.background(Color.LIGHT);
								contentFragment.content(navs);
								
//								NavigationBarViewPart nbvp = new NavigationBarViewPart(contextChildren, activeAction);
//								contentFragment.content(nbvp.generate(viewGenerator, progressMonitor));
							}
							
							contentFragment.content(section.generate(viewGenerator, progressMonitor));
							
							ViewPart subSectionsViewPart = section.createSectionsViewPart(activeAction, level+1, paragraphLevel);
							if (subSectionsViewPart != null) {
								contentFragment.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
							}
	
							sectionRow.cell(contentFragment);
						}													
					}

					return ret;
				}
			};
		}
		
	}
	
	;
	
//	PILL
//	VerticalPill
//	CARD
//	Accordion
	
	private class NavsViewPart implements ViewPart {
		private final int paragraphLevel;
		private final Action activeAction;
		private final List<Entry<Label, List<Action>>> categories;
		private final int level;
		private boolean tabs;
		private boolean card;

		private NavsViewPart(
				int paragraphLevel, 
				Action activeAction, 
				List<Entry<Label, List<Action>>> categories,
				int level,
				boolean card,
				boolean tabs) {
			this.paragraphLevel = paragraphLevel;
			this.activeAction = activeAction;
			this.categories = categories;
			this.level = level;
			this.card = card;
			this.tabs = tabs;			
		}

		@Override
		public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
			Action activeSection = null;
			for (Entry<Label, List<Action>> ce: categories) {
				for (Action section: ce.getValue()) {
					if (org.nasdanika.html.app.impl.Util.equalOrInPath(activeAction, section)) {
						activeSection = section;
					}
				}
			}
			
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
						
						ViewPart subSectionsViewPart = section.createSectionsViewPart(activeAction, level+1, paragraphLevel);
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
						contentFragment.content(sectionParagraph(section, activeAction, level + 1, paragraphLevel + 1, viewGenerator, progressMonitor));
					}							

					viewGenerator.decorate(navs.item(labelFragment, categoryEntry.getValue().contains(activeSection), false, contentId, contentFragment), category);													
				}
			}

			return card ? theCard : navs;
		}
	}

	/**
	 * Creates a view part rendering action's sections.
	 * @param section Action containing section actions to be rendered. 
	 * @param activeAction
	 * @param showContextActions
	 * @param level Section nesting level
	 * @return
	 */
	public abstract ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel);
	
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

	/**
	 * Generates a section div for an action with Hx header and content.
	 * @param section
	 * @param activeAction
	 * @param level
	 * @param paragraphLevel
	 * @param viewGenerator
	 * @param progressMonitor
	 * @return
	 */
	Tag sectionParagraph(
			Action section, 
			Action activeAction, 
			int level, 
			int paragraphLevel,
			ViewGenerator viewGenerator, 
			ProgressMonitor progressMonitor) {
		
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
		Tag sectionDiv = htmlFactory.div();
		if (section.getId() != null) {
			sectionDiv.content(TagName.a.create().attribute("name", section.getId()));						
		}
		
		List<Action> contextChildren = section.getContextChildren();
		if (!contextChildren.isEmpty()) {
			Navs navs = viewGenerator.categorizedLinkNavs(contextChildren, activeAction, null);
			navs._float().right();
			sectionDiv.content(navs);
		}
		
		Tag hTag = htmlFactory.tag("H"+Math.min(6, paragraphLevel), viewGenerator.labelFragment(section));
		viewGenerator.decorate(hTag, section);
		if (section.getColor() != null) {
			viewGenerator.get(BootstrapFactory.class).wrap(hTag).background(section.getColor());
		}		
		sectionDiv.content(hTag);
		
		sectionDiv.content(section.generate(viewGenerator, progressMonitor));
		
		ViewPart subSectionsViewPart = section.createSectionsViewPart(activeAction, level+1, paragraphLevel + 1);
		if (subSectionsViewPart != null) {
			sectionDiv.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
		}
		return sectionDiv;
	}
	
	
}
