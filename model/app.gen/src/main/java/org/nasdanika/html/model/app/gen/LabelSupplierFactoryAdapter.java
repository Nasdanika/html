package org.nasdanika.html.model.app.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.bootstrap.gen.BootstrapElementSupplierFactoryAdapter;

public class LabelSupplierFactoryAdapter<M extends Label> extends BootstrapElementSupplierFactoryAdapter<M, BootstrapElement<?,?>> {
	
	public LabelSupplierFactoryAdapter(M label) {
		super(label);
	}
		
	@Override
	public Supplier<HTMLElement<?>> createHTMLElementSupplier(Context context) throws Exception {
		Function<Tag, HTMLElement<?>> labelFunction = new Function<Tag, HTMLElement<?>>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Label";
			}

			@Override
			public HTMLElement<?> execute(Tag help, ProgressMonitor progressMonitor) throws Exception {
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				Label semanticElement = getTarget();
				
				HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
				Tag container = htmlFactory.span();
				
				String tooltip = semanticElement.getTooltip();
				if (!Util.isBlank(tooltip)) {
					container.attribute("title", tooltip);
				}
				
				if (help != null) {
					container.accept(help);
				}
				
				String icon = semanticElement.getIcon();
				Color color = semanticElement.getColor();
				if (!Util.isBlank(icon)) {
					Tag iconTag;
					if (icon.contains("/")) {
						// Image
						iconTag = htmlFactory.tag(TagName.img).attribute("src", icon); //.style().height("1em");
					} else {
						// Class
						iconTag = htmlFactory.span().addClass(icon);
						if (color != null) {
							bootstrapFactory.wrap(iconTag).text().color(color);
						}						
					}
					iconTag.addClass("nsd-label-icon");
					container.accept(iconTag);
				}		
					
				Tag text = text(context, progressMonitor);
				if (text != null) {
					if (color == null) {
						container.accept(text);
					} else {						
						bootstrapFactory.wrap(text).text().color(color);
						container.accept(text);
					}
				}	
				
				if (!Util.isBlank(semanticElement.getNotification())) {
					Color notificationColor = Color.PRIMARY;
					if (color == Color.PRIMARY) {
						notificationColor = Color.SECONDARY;
					} else if (color == Color.DANGER) {
						notificationColor = Color.DANGER;				
					} else if (color == Color.WARNING) {
						notificationColor = Color.WARNING;				
					} 
					Tag badge = bootstrapFactory.badge(true, notificationColor, semanticElement.getNotification());
					badge.addClass("nsd-label-notification");
					container.accept(badge);
				}

				if (help != null) {
					Tag trigger = bootstrapFactory.getHTMLFactory().tag(TagName.sup).addClass("far fa-question-circle", "nsd-label-help").style("cursor", "pointer");
					if (!Util.isBlank(tooltip)) {
						trigger.attribute("title", tooltip);
					}
					if (help.getId() == null) {
						help.id(htmlFactory.nextId());
					}
					trigger.attribute("data-toggle", "modal");
					trigger.attribute("data-target", "#" + help.getId());
					container.accept(trigger);
				}
				
				return container;
			}
			
		};
		
		M semanticElement = getTarget();
		org.nasdanika.html.model.bootstrap.Modal help = semanticElement.getHelp();
		SupplierFactory<Tag> helpFactory = help == null ? SupplierFactory.empty() : EObjectAdaptable.adaptToSupplierFactory(help, Tag.class);
		
		return helpFactory.create(context).then(labelFunction);
	}
	
	/**
	 * Override in Link to create a link.
	 * @param context
	 * @param progressMonitor
	 * @return
	 */
	protected Tag text(Context context, ProgressMonitor progressMonitor) {
		HTMLFactory htmlFactory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
		return htmlFactory.span(context.interpolateToString(getTarget().getText()));
	}
	
}
