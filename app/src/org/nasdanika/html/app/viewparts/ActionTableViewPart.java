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
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.Table.TableBody;
import org.nasdanika.html.bootstrap.Table.TableHeader;

/**
 * @author Pavel
 *
 */
public class ActionTableViewPart implements ViewPart {

	private final int headerLevel;
	private final Action activeAction;
	private final List<Entry<Label, List<Action>>> categories;
	private final int sectionLevel;

	public ActionTableViewPart(
			List<Entry<Label, List<Action>>> categories,
			Action activeAction, 
			int sectionLevel,
			int headerLevel) {
		this.headerLevel = headerLevel;
		this.activeAction = activeAction;
		this.categories = categories;
		this.sectionLevel = sectionLevel;		
	}

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
				
				viewGenerator.label(category, hRow.header().toHTMLElement().colspan(2));
			} 
			
			TableBody body = table.body();
			for (Action section: categoryEntry.getValue()) {
				Row sectionRow = body.row();
				if (section.getColor() != null) {
					sectionRow.color(section.getColor());
				}
				viewGenerator.label(section, sectionRow.header().toHTMLElement());
				
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

				sectionRow.cell(contentFragment);
			}													
		}

		return ret;		
	}

}
