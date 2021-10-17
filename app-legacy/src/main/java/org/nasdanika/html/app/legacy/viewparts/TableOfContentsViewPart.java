package org.nasdanika.html.app.viewparts;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;

public class TableOfContentsViewPart extends TableOfContentsBaseViewPart {

	private boolean descriptions;
	private boolean bordered;
	private boolean borderless;
	private boolean dark;
	private boolean hover;
	private boolean small;
	private boolean striped;
	
	private boolean tooltips;	

	public TableOfContentsViewPart(
			String role, 
			Object header, 
			boolean descriptions, 
			boolean tooltips,
			int depth) {
		
		super(role, header, depth);
		this.descriptions = descriptions;
		this.tooltips = tooltips;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		ViewGenerator noDecoratorViewGenerator = viewGenerator.forkNoDecorator();
		Action action = noDecoratorViewGenerator.get(Action.class);
		if (action == null) {
			return null;
		}
		Table table = createTable(noDecoratorViewGenerator);
		rows(noDecoratorViewGenerator, action, 1, table);
		Object headerVal = noDecoratorViewGenerator.processViewPart(header, progressMonitor);
		if (headerVal == null || Util.isBlank(headerVal.toString())) {
			return table;
		}
		int headerLevel = noDecoratorViewGenerator.get(SectionStyle.HEADER_LEVEL, Integer.class, 3);
		HTMLFactory htmlFactory = noDecoratorViewGenerator.getHTMLFactory();		
		return htmlFactory.div(htmlFactory.tag("H"+headerLevel, headerVal), table);
	}

	protected Table createTable(ViewGenerator noDecoratorViewGenerator) {
		BootstrapFactory bootstrapFactory = noDecoratorViewGenerator.getBootstrapFactory();
		Table table = bootstrapFactory.table();
		table.bordered(bordered);
		table.borderless(borderless);
		table.dark(dark);
		table.hover(hover);
		table.small(small);
		table.striped(striped);
		return table;
	}

	/**
	 * 
	 * @param viewGenerator
	 * @param currentAction
	 * @param level
	 * @param levelType
	 * @return List of action children in a given role.
	 */
	protected void rows(ViewGenerator viewGenerator, Action currentAction, int level, Table table) {
		if (level > depth) {
			return;
		}
		Collection<Entry<Label, List<Action>>> groupedActions = getGroupedActions(viewGenerator, currentAction);
		if (groupedActions.isEmpty()) {
			return;
		}
		
		for (Entry<Label, List<Action>> ge: groupedActions) {
			int effectiveLevel = level;
			if (ge.getKey() != null) {
				Row categoryRow = table.row();
				Tag categoryLabel = viewGenerator.label(ge.getKey());
				if (effectiveLevel > 1) {
					categoryLabel.style("margin-left", ((effectiveLevel - 1) * 2) + "em");
				}
				Cell categoryCell = categoryRow.cell(categoryLabel);
				if (descriptions || tooltips) {
					categoryCell.toHTMLElement().colspan(2);
				}
				++effectiveLevel;
			}
			
			for (Action rowAction: ge.getValue()) {
				Row actionRow = table.row();
				Tag actionLink = viewGenerator.link(rowAction);
				if (effectiveLevel > 1) {
					actionLink.style("margin-left", ((effectiveLevel - 1) * 2) + "em");
				}
				actionRow.cell(actionLink);
				if (descriptions) {
					actionRow.cell(rowAction.getDescription());
				} else if (tooltips) {
					actionRow.cell(rowAction.getTooltip());
				}
				rows(viewGenerator, rowAction, effectiveLevel + 1, table);
			}
		}
	}

	protected Collection<Entry<Label, List<Action>>> getGroupedActions(ViewGenerator viewGenerator, Action currentAction) {
		return currentAction.getChildrenGroupedByCategory(role);
	}

	public void setBordered(boolean bordered) {
		this.bordered = bordered;		
	}

	public void setBorderless(boolean borderless) {
		this.borderless = borderless;		
	}

	public void setDark(boolean dark) {
		this.dark = dark;		
	}

	public void setHover(boolean hover) {
		this.hover = hover;		
	}

	public void setSmall(boolean small) {
		this.small = small;		
	}

	public void setStriped(boolean striped) {
		this.striped = striped;		
	}

}
