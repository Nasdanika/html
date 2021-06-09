package org.nasdanika.html.app.viewparts;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.OrderedListType;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionRegistry;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;

/**
 * Generates a list of actions. Categories of root actions are ignored. 
 * @author Pavel
 *
 */
public class ListOfActionsViewPart extends ListOfContentsViewPart {

	private List<?> actions;

	public ListOfActionsViewPart(
			List<?> actions,
			Object header, 
			boolean tooltip, 
			int depth,
			OrderedListType orderedListType) {
		
		super(null, header, tooltip, depth, orderedListType);
		this.actions = actions;
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		ViewGenerator noDecoratorViewGenerator = viewGenerator.forkNoDecorator();
		Tag list = list(noDecoratorViewGenerator, null, 1, orderedListType == OrderedListType.ROTATE ? OrderedListType.NUMBER : orderedListType);
		Object headerVal = noDecoratorViewGenerator.processViewPart(header, progressMonitor);
		if (list == null || headerVal == null || Util.isBlank(headerVal.toString())) {
			return list;
		}
		int headerLevel = noDecoratorViewGenerator.get(SectionStyle.HEADER_LEVEL, Integer.class, 3);
		HTMLFactory htmlFactory = noDecoratorViewGenerator.getHTMLFactory();		
		return htmlFactory.div(htmlFactory.tag("H"+headerLevel, headerVal), list);
	}	
	
	@Override
	protected Collection<Entry<Label, List<Action>>> getGroupedActions(ViewGenerator viewGenerator, Action currentAction) {		
		if (currentAction == null) {
			
			Function<Object, Action> mapper = o -> {
				if (o instanceof String) {
					ActionRegistry registry = viewGenerator.get(ActionRegistry.class);
					if (registry == null) {
						throw new IllegalStateException("ActionRegistry service is not present in the view generator- cannot find actions by their ids's");
					}
					return registry.get((String) o);
				}
				if (o instanceof Action) {
					return (Action) o;
				}
				throw new IllegalArgumentException("Actions collection shall contain either Strings - action ID's or actions. Got: " + o);
			};
			// Configuration strict/lenient? Filtering behavior is more flexible - may list all possible things and generate actual links only to those present.
			return Collections.singletonMap((Label) null, actions.stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList())).entrySet(); 
		}
		return super.getGroupedActions(viewGenerator, currentAction);
	}

}
