package org.nasdanika.html.app.viewparts;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionRegistry;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.Table;

public class TableOfActionsViewPart extends TableOfContentsViewPart {

	private List<String> actionIds;

	public TableOfActionsViewPart(
			List<String> actionIds,
			Object header, 
			boolean descriptions, 
			boolean tooltips,
			int depth) {
		
		super(null, header, descriptions, tooltips, depth);
		this.actionIds = actionIds;
	}
		
	@Override
	protected Collection<Entry<Label, List<Action>>> getGroupedActions(ViewGenerator viewGenerator, Action currentAction) {		
		if (currentAction == null) {
			ActionRegistry registry = viewGenerator.get(ActionRegistry.class);
			if (registry == null) {
				throw new IllegalStateException("ActionRegistry service is not present in the view generator- cannot find actions by their ids's");
			}
			
			// Configuration strict/lenient? Filtering behavior is more flexible - may list all possible things and generate actual links only to those present.
			return Collections.singletonMap((Label) null, actionIds.stream().map(registry::get).filter(Objects::nonNull).collect(Collectors.toList())).entrySet(); 
		}
		return super.getGroupedActions(viewGenerator, currentAction);
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		ViewGenerator noDecoratorViewGenerator = viewGenerator.forkNoDecorator();
		Table table = createTable(noDecoratorViewGenerator);
		rows(noDecoratorViewGenerator, null, 1, table);
		Object headerVal = noDecoratorViewGenerator.processViewPart(header, progressMonitor);
		if (headerVal == null || Util.isBlank(headerVal.toString())) {
			return table;
		}
		int headerLevel = noDecoratorViewGenerator.get(SectionStyle.HEADER_LEVEL, Integer.class, 3);
		HTMLFactory htmlFactory = noDecoratorViewGenerator.getHTMLFactory();		
		return htmlFactory.div(htmlFactory.tag("H"+headerLevel, headerVal), table);
	}

}
