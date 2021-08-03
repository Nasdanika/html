package org.nasdanika.html.app.impl;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.resources.Container;
import org.nasdanika.html.Button;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.Categorized;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.viewparts.AdaptiveNavigationPanelViewPart.Style;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Modal;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

public final class Util {

	private Util() {
		// Utility
	}
		
	/**
	 * Compares objects by id and then using equals.
	 * @param l1
	 * @param l2
	 * @return
	 */
	 public static <T extends Label> boolean equal(T l1, T l2) {
		if (l1 == l2) {
			return true;
		}
		if (l1 == null || l2 == null) {
			return false;
		}
		if (l1.getId() != null && l1.getId().equals(l2.getId())) {
			return true;
		}
		return l1.equals(l2);
	}
	 
	/**
	 * Groups items by category. Categories are listed in the order in appearance. Uncategorized entries (null category) 
	 * are listed before any categories.
	 * @param items
	 * @return
	 */
	public static <T extends Categorized> List<Map.Entry<Label, List<T>>> groupByCategory(Iterable<T> items) {
		List<T> uncategorized = new ArrayList<>();
		List<Map.Entry<Label, List<T>>> ret = new ArrayList<>();
		I: for (T item: items) {
			if (item != null) {
				Label category = item.getCategory();
				if (category == null) {
					uncategorized.add(item);
				} else {
					for (Entry<Label, List<T>> e: ret) {
						if (equal(e.getKey(), category)) {
							e.getValue().add(item);
							continue I;
						}
					}
					List<T> ci = new ArrayList<>();
					ci.add(item);
					ret.add(new AbstractMap.SimpleEntry<Label, List<T>>(category, ci));
				}
			}
		}		
		if (!uncategorized.isEmpty()) {
			ret.add(0, new AbstractMap.SimpleEntry<Label, List<T>>(null, uncategorized));
		}
		return ret;
	}
	
	public static  boolean contains(Collection<? extends Action> collection, Action action) {
		for (Action e: collection) {
			if (equal(e, action)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the parameter action is equal to the active action or is in its path.
	 * @param a
	 * @return
	 */
	public static boolean equalOrInPath(Action activeAction, Action a) {
		return activeAction != null && (equal(activeAction, a) || contains(activeAction.getPath(), a));
	}
	
	public static boolean isBlank(String str) {
		return org.nasdanika.common.Util.isBlank(str);
	}

	/**
	 * @param viewGenerator
	 * @param label
	 * @return A trigger tag to activate description modal or null if there is no description or it is the same as tooltip.
	 */
	public static Tag descriptionModal(ViewGenerator viewGenerator, Label label) {
		return descriptionModal(viewGenerator, label, viewGenerator.getBodyContentConsumer());
	}
	
	/**
	 * @param viewGenerator
	 * @param label
	 * @return A trigger tag to activate description modal or null if there is no description or it is the same as tooltip.
	 */
	public static Tag descriptionModal(ViewGenerator viewGenerator, Label label, Consumer<? super Modal> modalConsumer) {		
		// Description modal
		String description = label.getDescription();
		String tooltip = label.getTooltip();
		if (modalConsumer != null && !Util.isBlank(description) && !Jsoup.parse(description).text().equals(tooltip)) {
			BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
			Modal descriptionModal = bootstrapFactory.modal();
			descriptionModal.scrollable().size(Breakpoint.LARGE);
			modalConsumer.accept(descriptionModal);
			TagBootstrapElement header = descriptionModal.getHeader();
			header.background(Color.SECONDARY);
			Tag headerTag = header.toHTMLElement();
			String questionCircleIcon = "far fa-question-circle";
			Tag modalTitle = viewGenerator.getHTMLFactory().tag(TagName.h5, TagName.span.create().addClass(questionCircleIcon).style().margin().right("0.3em"), label.getText());
			headerTag.content(modalTitle);
			Button dismisser = bootstrapFactory.getHTMLFactory().button("x").addClass("close");
			headerTag.content(dismisser);
			descriptionModal.bindDismisser(dismisser);
			
			Tag trigger = bootstrapFactory.getHTMLFactory().tag(TagName.sup).addClass(questionCircleIcon).style("cursor", "pointer");
			if (!Util.isBlank(tooltip)) {
				trigger.attribute("title", tooltip);
			}
			descriptionModal.bindTrigger(trigger);
			
			descriptionModal.getBody().toHTMLElement().content(description);
			return trigger;
		}
		return null;
	}
	
	/**
	 * Writes action application to a container.
	 * @param applicationSupplierFactory
	 * @param context
	 * @param base
	 * @param root
	 * @param principal
	 * @param active
	 * @param monitor
	 * @throws Exception
	 */
	public static void writeAction(
			Action root, 
			Action principal, 
			Action active, 
			String base,
			Container<String> container,
			Context context, 
			Style navigationPanelStyle,
			SupplierFactory<? extends Application> applicationSupplierFactory,
			ProgressMonitor monitor) throws Exception {
		
		if (active != null) {
			MutableContext actionContext = context.fork();		
			if (!active.isEmpty() && active.getActivator() instanceof NavigationActionActivator) {
				NavigationActionActivator activator = (NavigationActionActivator) active.getActivator();
				String url = activator.getUrl(null);
				if (url != null && url.startsWith(base) && !url.contains("#")) {	
					actionContext.put(Context.BASE_URI_PROPERTY, url);
					actionContext.put("page-title", active.getText());
					ApplicationBuilder builder = new ActionApplicationBuilder(actionContext, root, principal, active) {
						
						@Override
						protected Style getNavigationPanelStyle() {
							return navigationPanelStyle == null ? super.getNavigationPanelStyle() : navigationPanelStyle;
						}
						
					};
					Application app = org.nasdanika.common.Util.call(applicationSupplierFactory.create(actionContext), monitor, null);
					builder.build(app, monitor);
	
					container.put(url.substring(base.length()), app.toString(), monitor);
				}			
			}		
			for (Action child: active.getChildren()) {
				writeAction(root, principal, child, base, container, actionContext, navigationPanelStyle, applicationSupplierFactory, monitor);
			}
		}
	}	

}
