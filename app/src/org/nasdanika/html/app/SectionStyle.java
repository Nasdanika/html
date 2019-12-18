package org.nasdanika.html.app;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Size;

/**
 * Defines style of child actions in Section role.
 * @author Pavel
 *
 */
public enum SectionStyle {
	
	/**
	 * Section style is selected based on the section level - Tabs for level 0, ActionGroup for 1, and Paragraph for the rest.
	 */
	Auto {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			switch (level) {
			case 0:
				return Tab.createViewPart(action, activeAction, level, paragraphLevel);
			case 1:
				return ActionGroup.createViewPart(action, activeAction, level, paragraphLevel);
			default:
				return Paragraph.createViewPart(action, activeAction, level, paragraphLevel);
			}
		}
		
	},

	/**
	 * Sections are generated as blocks with Hx headers where x starts with 3 and increases for each additional paragraph level up to H6
	 */
	Paragraph {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Action> sections = level == 0 ? action.getChildrenByRole(Action.Role.SECTION) : action.getChildrenByRole(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (sections.isEmpty()) {
				return null;
			}
			
			return new ViewPart() {
				
				@Override
				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
										
					Action activeSection = null;
					for (Action section: sections) {
						if (org.nasdanika.html.app.impl.Util.equalOrInPath(activeAction, section)) {
							activeSection = section;
						}
					}										
					
					HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
					Fragment contentFragment = htmlFactory.fragment();

					for (Action section: sections) {
						if (activeSection == null) {
							activeSection = section; // First if null.
						}
						
						Tag sectionDiv = htmlFactory.div();
						if (action.getId() != null) {
							sectionDiv.content(TagName.a.create().attribute("name", action.getId()));						
						}
						
						List<Action> contextChildren = section.getContextChildren();
						if (!contextChildren.isEmpty()) {
							Navs navs = viewGenerator.categorizedLinkNavs(contextChildren, activeAction, null);
							navs._float().right();
							sectionDiv.content(navs);
						}
						
						Fragment labelFragment = viewGenerator.labelFragment(section);
						Tag hTag = htmlFactory.tag("H"+Math.min(6, paragraphLevel), labelFragment);
						viewGenerator.decorate(hTag, section);
						sectionDiv.content(hTag);
						
						sectionDiv.content(section.generate(viewGenerator, progressMonitor));
						
						ViewPart subSectionsViewPart = section.createSectionsViewPart(activeAction, level+1, paragraphLevel + 1);
						if (subSectionsViewPart != null) {
							sectionDiv.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
						}
						contentFragment.content(sectionDiv);
					}

					return contentFragment;
				}
			};
		}
		
	},
	
	/**
	 * Sections are generated as tabs.
	 */
	Tab {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Action> sections = level == 0 ? action.getChildrenByRole(Action.Role.SECTION) : action.getChildrenByRole(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (sections.isEmpty()) {
				return null;
			}			
			
			return new ViewPart() {
				
				@Override
				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
					Action activeSection = null;
					for (Action section: sections) {
						if (org.nasdanika.html.app.impl.Util.equalOrInPath(activeAction, section)) {
							activeSection = section;
						}
					}										
					
					Navs tabs = viewGenerator.get(BootstrapFactory.class).navs().tabs();
					for (Action section: sections) {
						if (activeSection == null) {
							activeSection = section; // First if null.
						}
						String contentId = section.getId() == null ? null : "nsd-action-content-"+section.getId();
						Fragment labelFragment = viewGenerator.labelFragment(section);

						Fragment contentFragment = viewGenerator.get(HTMLFactory.class).fragment();	
						if (action.getId() != null) {
							contentFragment.content(TagName.a.create().attribute("name", action.getId()));						
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

						viewGenerator.decorate(tabs.item(labelFragment, section == activeSection, section.isDisabled(), contentId, contentFragment), section);						
					}

					return tabs;
				}
			};
		}
		
	},
	
//	for (Action subSection: subSections) {
//		
//		Object subSectionContent = new SectionViewPart(subSection, activeAction, true, level + 1).generate(viewGenerator, progressMonitor);
//		
//		if (sectionsContainer instanceof ActionGroup) {
//			Tag ca = ((ActionGroup) sectionsContainer).contentAction(labelFragment, subSection == activeSubSection, subSection.isDisabled(), subSection.getColor(), contentId, subSectionContent);
//			viewGenerator.decorate(ca, subSection);
//		} else if (sectionsContainer instanceof Navs) {
//			viewGenerator.decorate(((Navs) sectionsContainer).item(labelFragment, subSection == activeSubSection, subSection.isDisabled(), contentId, subSectionContent), subSection);
//		} else {
//			sectionsContainer.item(labelFragment, subSectionContent); // TODO - decorate
//		}
//	}
		
	ActionGroup("Action group") {

		@Override
		public ViewPart createViewPart(Action action, Action activeAction, int level, int paragraphLevel) {
			List<Action> sections = level == 0 ? action.getChildrenByRole(Action.Role.SECTION) : action.getChildrenByRole(Action.Role.SECTION, Action.Role.NAVIGATION);
			if (sections.isEmpty()) {
				return null;
			}			
			
			return new ViewPart() {
				
				@Override
				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
					Action activeSection = null;
					for (Action section: sections) {
						if (org.nasdanika.html.app.impl.Util.equalOrInPath(activeAction, section)) {
							activeSection = section;
						}
					}										
					
					ActionGroup actionGroup = viewGenerator.get(BootstrapFactory.class).actionGroup(false);

					for (Action section: sections) {
						if (activeSection == null) {
							activeSection = section; // First if null.
						}
						String contentId = section.getId() == null ? null : "nsd-action-content-"+section.getId();
						Fragment labelFragment = viewGenerator.labelFragment(section);

						Fragment contentFragment = viewGenerator.get(HTMLFactory.class).fragment();	
						if (action.getId() != null) {
							contentFragment.content(TagName.a.create().attribute("name", action.getId()));						
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

					return actionGroup.asContainer(true).margin().top(Breakpoint.DEFAULT, Size.S1).toBootstrapElement();
				}
			};
		}
		
	};
//	Pill
//	VerticalPill
//	Card
//	Accordion
	
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
	
	private SectionStyle() {
		this(null);
	}
	
	private SectionStyle(String label) {		
		this.label = Util.isBlank(label) ? name() : label;
	}	
	
	public static SectionStyle fromLabel(String label) {
		for (SectionStyle sectionStyle: values()) {
			if (sectionStyle.label.equals(label)) {
				return sectionStyle;
			}
		}
		throw new IllegalArgumentException("No breakpoint value for label "+label);
	}	
	
}
