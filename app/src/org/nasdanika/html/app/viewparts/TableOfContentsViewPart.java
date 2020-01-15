package org.nasdanika.html.app.viewparts;

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

	public TableOfContentsViewPart(
			String role, 
			String header, 
			boolean descriptions, 
			int depth) {
		
		super(role, header, depth);
		this.descriptions = descriptions;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		Action action = viewGenerator.get(Action.class);
		if (action == null) {
			return null;
		}
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		Table table = bootstrapFactory.table();
		table.bordered(bordered);
		table.borderless(borderless);
		table.dark(dark);
		table.hover(hover);
		table.small(small);
		table.striped(striped);
		rows(viewGenerator, action, 1, table);
		if (Util.isBlank(header)) {
			return table;
		}
		int headerLevel = viewGenerator.get(SectionStyle.HEADER_LEVEL, Integer.class, 3);
		HTMLFactory htmlFactory = viewGenerator.get(HTMLFactory.class);		
		return htmlFactory.div(htmlFactory.tag("H"+headerLevel, header), table);
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
		List<Entry<Label, List<Action>>> groupedChildren = currentAction.getChildrenGroupedByCategory(role);
		if (groupedChildren.isEmpty()) {
			return;
		}
		
		for (Entry<Label, List<Action>> ge: groupedChildren) {
			if (ge.getKey() != null) {
				Row categoryRow = table.row();
				Tag categoryLabel = viewGenerator.label(ge.getKey());
				if (level > 1) {
					categoryLabel.style("margin-left", ((level - 1) * 2) + "em");
				}
				Cell categoryCell = categoryRow.cell(categoryLabel);
				if (descriptions) {
					categoryCell.toHTMLElement().colspan(2);
				}
				++level;
			}
			
			for (Action rowAction: ge.getValue()) {
				Row actionRow = table.row();
				Tag actionLink = viewGenerator.link(rowAction);
				if (level > 1) {
					actionLink.style("margin-left", ((level - 1) * 2) + "em");
				}
				actionRow.cell(actionLink);
				if (descriptions) {
					actionRow.cell(rowAction.getDescription());
				}
				rows(viewGenerator, rowAction, level + 1, table);
			}
		}
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
