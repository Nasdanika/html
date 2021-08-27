package org.nasdanika.html.model.app.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Modal;
import org.nasdanika.html.bootstrap.TagBootstrapElement;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.bootstrap.gen.BootstrapElementSupplierFactoryAdapter;

public class LabelSupplierFactoryAdapter<M extends Label> extends BootstrapElementSupplierFactoryAdapter<M, BootstrapElement<?,?>> {
	
	public LabelSupplierFactoryAdapter(M label) {
		super(label);
	}
		
	@Override
	public Supplier<HTMLElement<?>> createElementSupplier(Context context) throws Exception {
		return new Supplier<HTMLElement<?>>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Container";
			}

			@Override
			public HTMLElement<?> execute(ProgressMonitor progressMonitor) throws Exception {
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				Label semanticElement = getTarget();
				
				HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
				Tag container = htmlFactory.span();
				
				String tooltip = semanticElement.getTooltip();
				if (!Util.isBlank(tooltip)) {
					container.attribute("title", tooltip);
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
					
				Object text = text(context, progressMonitor);
				if (text != null) {
					if (color == null) {
						container.accept(text);
					} else {						
						Tag textTag = text instanceof Tag ? (Tag) text : htmlFactory.span(text);
						bootstrapFactory.wrap(textTag).text().color(color);
						container.accept(textTag);
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
					badge.style().margin().left("0.3em"); // TODO - also style-driven
					badge.addClass("nsd-label-notification");
					container.accept(badge);
				}

				String help = context.interpolateToString(semanticElement.getHelp());
				if (!Util.isBlank(help)) {
					Modal descriptionModal = bootstrapFactory.modal();
					descriptionModal.scrollable().size(Breakpoint.LARGE);
					TagBootstrapElement header = descriptionModal.getHeader();
					header.background(Color.SECONDARY);
					Tag headerTag = header.toHTMLElement();
					String questionCircleIcon = "far fa-question-circle";
					Tag modalTitle = htmlFactory.tag(TagName.h5, TagName.span.create().addClass(questionCircleIcon).style().margin().right("0.3em"), context.interpolateToString(semanticElement.getText()));
					headerTag.content(modalTitle);
					
					Button dismisser = bootstrapFactory.getHTMLFactory().button("x").addClass("close");
					headerTag.content(dismisser);
					descriptionModal.bindDismisser(dismisser);					
					descriptionModal.getBody().toHTMLElement().content(help);
					container.accept(descriptionModal);
					
					Tag trigger = bootstrapFactory.getHTMLFactory().tag(TagName.sup).addClass(questionCircleIcon).style("cursor", "pointer");
					if (!Util.isBlank(tooltip)) {
						trigger.attribute("title", tooltip);
					}
					descriptionModal.bindTrigger(trigger);
					container.accept(trigger);
				}
				
				return container;
			}
			
		};
	}
	
	/**
	 * Override in Link to create a link.
	 * @param context
	 * @param progressMonitor
	 * @return
	 */
	protected Object text(Context context, ProgressMonitor progressMonitor) {
		return context.interpolateToString(getTarget().getText());
	}
	
}
