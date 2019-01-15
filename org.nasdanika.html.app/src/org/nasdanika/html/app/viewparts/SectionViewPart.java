package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.NamedItemsContainer;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.Navs;

/**
 * Generates section.
 * 
 * TODO - Use Ajax if section action has {@link NavigationActionActivator}. 
 * 
 * @author Pavel Vlasov
 *
 */
public class SectionViewPart implements ViewPart {

	protected Action section;
	protected Action activeAction;
	protected int level;
	private Map<String, Object> input;
	private boolean showContextActions;
	
	// TODO - constructor taking level from Util.sectionLevel()

	public SectionViewPart(Action section, Action activeAction, Map<String, Object> input, boolean showContextActions, int level) {
		this.section = section;
		this.activeAction = activeAction;
		this.input = input;
		this.showContextActions = showContextActions;
		this.level = level;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator) {
		Fragment ret = viewGenerator.getHTMLFactory().fragment();
		// Process viewPart as in ViewGenerator ???
		ret.content(section.execute(viewGenerator, input));

		// Context actions		
		if (showContextActions) {
			List<? extends Action> contextActions = section.getContextChildren();
			if (!contextActions.isEmpty()) {			
				ButtonToolbar buttonToolbar = viewGenerator.buttonToolbar(contextActions);
				buttonToolbar.margin().top(1).bottom(1);
				ret.content(buttonToolbar);			
			}
		}
				
		// Sections
		List<? extends Action> subSections = section.getSectionChildren();
		if (subSections != null && !subSections.isEmpty()) {
			NamedItemsContainer sectionsContainer = createSectionsContainer(viewGenerator, ret, level);
			Action activeSubSection = null;
			for (Action subSection: subSections) {
				if (Util.equalOrInPath(activeAction, subSection)) {
					activeSubSection = subSection;
				}
			}
			for (Action subSection: subSections) {
				if (activeSubSection == null) {
					activeSubSection = subSection; // First if null.
				}
				
				Object subSectionContent = new SectionViewPart(subSection, activeAction, input, true, level + 1).generate(viewGenerator);
				
				String contentId = subSection.getId() == null ? null : "nsd-action-content-"+subSection.getId();
				Fragment labelFragment = viewGenerator.labelFragment(subSection);
				if (sectionsContainer instanceof ActionGroup) {
					((ActionGroup) sectionsContainer).contentAction(labelFragment, subSection == activeSubSection, subSection.isDisabled(), subSection.getColor(), contentId, subSectionContent);				
				} else if (sectionsContainer instanceof Navs) {
					((Navs) sectionsContainer).item(labelFragment, subSection == activeSubSection, subSection.isDisabled(), contentId, subSectionContent);
				} else {
					sectionsContainer.item(labelFragment, subSectionContent);
				}
			}
		}
		return ret;
	}
	
	/**
	 * Creates section container for the specified level.
	 * This implementation creates tabs for level 0, content action group for level 1, and header containers for levels 2 and above starting 
	 * with header 3 going up to 6. 
	 * @param viewGenerator
	 * @param bodyContentConsumer
	 * @param level
	 * @return Section container which is already added to the content consumer.
	 */
	protected NamedItemsContainer createSectionsContainer(ViewGenerator viewGenerator, Consumer<Object> contentConsumer, int level) {
		switch (level) {
		case 0:
			Navs tabs = viewGenerator.getBootstrapFactory().tabs();
			contentConsumer.accept(tabs);
			return tabs;
		case 1:
			ActionGroup actionGroup = BootstrapFactory.INSTANCE.actionGroup(false);
			contentConsumer.accept(actionGroup.asContainer().margin().top(1).toBootstrapElement());
			return actionGroup;
		case 2:
			NamedItemsContainer ret = viewGenerator.getHTMLFactory().tagNamedItemsContainer(TagName.h3);
			contentConsumer.accept(ret);
			return ret;
		case 3:
			ret = viewGenerator.getHTMLFactory().tagNamedItemsContainer(TagName.h4);
			contentConsumer.accept(ret);
			return ret;
		case 4:
			ret = viewGenerator.getHTMLFactory().tagNamedItemsContainer(TagName.h5);
			contentConsumer.accept(ret);
			return ret;
		default:
			ret = viewGenerator.getHTMLFactory().tagNamedItemsContainer(TagName.h6);
			contentConsumer.accept(ret);
			return ret;
		}
	}

}