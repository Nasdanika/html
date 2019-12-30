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

/**
 * Generates a card from action and its section children.
 * @author Pavel
 *
 */
public class CategorySectionViewPart implements ViewPart {

	private Label category;
	private List<Action> categoryActions;
	private Action activeAction;
	private int sectionLevel;
	private int headerLevel;
	Action activeSection = null;

	/**
	 * 
	 * @param action Action to generate card from 
	 * @param sectionLevel section level is used if section style is AUTO.
	 * @param headerLevel Header level. 
	 */
	public CategorySectionViewPart(
			Label category, 
			List<Action> categoryActions, 
			Action activeAction, 
			int sectionLevel, 
			int headerLevel) {
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
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);
		Fragment ret = htmlFactory.fragment();
		
		int asl = sectionLevel;
		int ahl = headerLevel;
		
		if (category != null) {
			if (category.getId() != null) {
				ret.content(TagName.a.create().attribute("name", category.getId()));						
			}
			
			Tag hTag = htmlFactory.tag("H"+Math.min(6, headerLevel));
			viewGenerator.label(category, hTag);
			viewGenerator.decorate(hTag, category);
			if (category.getColor() != null) {
				viewGenerator.get(BootstrapFactory.class).wrap(hTag).background(category.getColor());
			}
			ret.content(hTag);
			
			++asl;
			++ahl;
		}		

		for (Action section: categoryActions) {
			ActionSectionViewPart sectionViewPart = new ActionSectionViewPart(section, activeAction, asl, ahl);
			ret.content(sectionViewPart.generate(viewGenerator, progressMonitor));
		}							
		
		return ret;
	}

}
