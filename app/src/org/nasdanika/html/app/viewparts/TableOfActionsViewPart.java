package org.nasdanika.html.app.viewparts;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.Table;

public class TableOfActionsViewPart extends TableOfContentsViewPart {

	private List<Action> actions;

	public TableOfActionsViewPart(
			List<Action> actions,
			String header, 
			boolean descriptions, 
			boolean tooltips,
			int depth) {
		
		super(null, header, descriptions, tooltips, depth);
		this.actions = actions;
	}
		
	@Override
	protected Collection<Entry<Label, List<Action>>> getGroupedActions(Action currentAction) {
		return currentAction == null ? Collections.singletonMap((Label) null, actions).entrySet() : super.getGroupedActions(currentAction);
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		ViewGenerator noDecoratorViewGenerator = viewGenerator.forkNoDecorator();
		Table table = createTable(noDecoratorViewGenerator);
		rows(noDecoratorViewGenerator, null, 1, table);
		if (Util.isBlank(header)) {
			return table;
		}
		int headerLevel = noDecoratorViewGenerator.get(SectionStyle.HEADER_LEVEL, Integer.class, 3);
		HTMLFactory htmlFactory = noDecoratorViewGenerator.get(HTMLFactory.class);		
		return htmlFactory.div(htmlFactory.tag("H"+headerLevel, header), table);
	}

}
