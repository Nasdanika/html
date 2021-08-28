package org.nasdanika.html.model.app.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.Event;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.model.app.Link;

public class LinkSupplierFactoryAdapter<M extends Link> extends LabelSupplierFactoryAdapter<M> {
	
	public LinkSupplierFactoryAdapter(M label) {
		super(label);
	}
	
	@Override
	protected Supplier<Tag> createTextSupplier(Context context) throws Exception {
		Function<Tag,Tag> textFunction = new Function<Tag, Tag>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Link text";
			}

			@Override
			public Tag execute(Tag modal, ProgressMonitor progressMonitor) throws Exception {
				M semanticElement = getTarget();
				HTMLFactory htmlFactory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
				String text = context.interpolateToString(semanticElement.getText());
				if (semanticElement.isDisabled()) {
					return htmlFactory.span(text);					
				}
				Tag anchor = htmlFactory.tag(TagName.a, text).attribute("href", "#");
				if (modal != null) {
					if (modal.getId() == null) {
						modal.id(htmlFactory.nextId());
					}
					anchor.attribute("data-toggle", "modal");
					anchor.attribute("data-target", "#" + modal.getId());
					return htmlFactory.span(modal, anchor);
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
				
				return anchor;
			}
			
		};
				
		M semanticElement = getTarget();
		org.nasdanika.html.model.bootstrap.Modal modal = semanticElement.getModal();
		SupplierFactory<Tag> modalFactory = modal == null ? SupplierFactory.empty() : EObjectAdaptable.adaptToSupplierFactoryNonNull(modal, Tag.class);
		return modalFactory.create(context).then(textFunction);
	}
	
}
