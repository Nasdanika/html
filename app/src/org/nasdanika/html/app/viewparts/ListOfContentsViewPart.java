package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.OrderedListType;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;

public class ListOfContentsViewPart extends TableOfContentsBaseViewPart {

	private OrderedListType orderedListType;
	private boolean tooltip;

	public ListOfContentsViewPart(
			String role, 
			String header, 
			boolean tooltip, 
			int depth,
			OrderedListType orderedListType) {
		
		super(role, header, depth);
		this.tooltip = tooltip;
		this.orderedListType = orderedListType;		
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		Action action = viewGenerator.get(Action.class);
		if (action == null) {
			return null;
		}
		Tag list = list(viewGenerator, action, 1, orderedListType == OrderedListType.ROTATE ? OrderedListType.NUMBER : orderedListType);
		if (list == null || Util.isBlank(header)) {
			return list;
		}
		int headerLevel = viewGenerator.get(SectionStyle.HEADER_LEVEL, Integer.class, 3);
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);		
		return htmlFactory.div(htmlFactory.tag("H"+headerLevel, header), list);
	}

	/**
	 * 
	 * @param viewGenerator
	 * @param currentAction
	 * @param level
	 * @param levelType
	 * @return List of action children in a given role.
	 */
	protected Tag list(ViewGenerator viewGenerator, Action currentAction, int level, OrderedListType levelType) {
		if (level > depth) {
			return null;
		}
		List<Entry<Label, List<Action>>> groupedChildren = currentAction.getChildrenGroupedByCategory(role);
		if (groupedChildren.isEmpty()) {
			return null;
		}

		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);		
		Tag list = htmlFactory.tag(levelType == null ? TagName.ul : TagName.ol);
		if (levelType != null) {
			list.attribute("type", levelType.code);
		}
		
		for (Entry<Label, List<Action>> ge: groupedChildren) {
			Tag itemsContainer = list;
			if (ge.getKey() != null) {
				Tag categoryItem = htmlFactory.tag(TagName.li);
				list.content(categoryItem);
				categoryItem.content(viewGenerator.label(ge.getKey()));
				Tag categoryList = htmlFactory.tag(levelType == null ? TagName.ul : TagName.ol);
				categoryItem.content(categoryList);
				if (levelType != null) {
					if (orderedListType == OrderedListType.ROTATE) {
						levelType = OrderedListType.values()[(levelType.ordinal() + 1) % (OrderedListType.values().length - 1)];
					}
					list.attribute("type", levelType.code);
				}
				itemsContainer = categoryList;
			}
			
			if (levelType != null && orderedListType == OrderedListType.ROTATE) {
				levelType = OrderedListType.values()[(levelType.ordinal() + 1) % (OrderedListType.values().length - 1)];
			}
			
			for (Action itemAction: ge.getValue()) {
				Tag actionItem = htmlFactory.tag(TagName.li);
				itemsContainer.content(actionItem);
				actionItem.content(viewGenerator.link(itemAction));	
				if (tooltip) {
					String actionDescription = itemAction.getTooltip();
					if (!Util.isBlank(actionDescription)) {
						actionItem.content(" - ", actionDescription);
					}
				}
				Tag subList = list(viewGenerator, itemAction, level + 1, levelType);
				if (subList != null) {
					actionItem.content(subList);
				}
			}
		}
		
		return list;
	}

}
