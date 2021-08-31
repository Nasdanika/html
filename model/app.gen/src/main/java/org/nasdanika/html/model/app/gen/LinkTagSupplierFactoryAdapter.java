package org.nasdanika.html.model.app.gen;

import java.util.List;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.Event;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.model.app.Link;

public class LinkTagSupplierFactoryAdapter<M extends Link> extends LabelTagSupplierFactoryAdapter<M> {
	
	public LinkTagSupplierFactoryAdapter(M label) {
		super(label);
	}
	
	@Override
	protected Tag createTextAndIconTag(Tag icon, String text, Tag modal, Context context, ProgressMonitor progressMonitor) {
		M semanticElement = getTarget();
		HTMLFactory htmlFactory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);

		Tag anchor = htmlFactory.tag(TagName.a).attribute("href", "#");
		if (icon != null) {
			icon.addClass("nsd-app-label-icon");
			anchor.accept(icon);
		}
		if (!Util.isBlank(text)) {
			anchor.accept(text);
		}
		
		if (!semanticElement.isDisabled()) {
			if (modal != null) {
				if (modal.getId() == null) {
					modal.id(htmlFactory.nextId());
				}
				anchor.attribute("data-toggle", "modal");
				anchor.attribute("data-target", "#" + modal.getId());
	
				@SuppressWarnings("unchecked")
				List<Object> pageBody = context.get(org.nasdanika.html.model.html.gen.PageSupplierFactoryAdapter.PAGE_BODY_PROPERTY, List.class);
				pageBody.add(modal);
			}				
			
			String location = semanticElement.getLocation();
			String confirmation = context.interpolateToString(semanticElement.getConfirmation());
			if (!Util.isBlank(location)) {
				anchor.attribute("href", context.interpolateToString(location));
				if (!Util.isBlank(confirmation)) {
					anchor.on(Event.click, "return confirm('" + confirmation + "');");
				}
			} 
			
			String script = semanticElement.getScript();
			if (!Util.isBlank(script)) {
				String code = context.interpolateToString(script);
				if (!Util.isBlank(confirmation)) {
					code = "if (confirm('" + confirmation + "')) { "+ code +" } return false;";
				}
				anchor.on(Event.click, code);
				anchor.style("cursor", "pointer");
			}
		}
		
		return anchor;
	}

	@Override
	protected SupplierFactory<Tag> getModalFactory() {
		M semanticElement = getTarget();
		org.nasdanika.html.model.bootstrap.Modal modal = semanticElement.getModal();
		SupplierFactory<Tag> modalFactory = modal == null ? SupplierFactory.empty() : EObjectAdaptable.adaptToSupplierFactoryNonNull(modal, Tag.class);
		return modalFactory;
	}
	
}
