package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Navs;

public class ActionGroupImpl extends DivWrappingBootstrapElementImpl<ActionGroup> implements ActionGroup {

	private Tag contentDiv;
	
	protected ActionGroupImpl(BootstrapFactory factory, boolean flush) {
		super(factory);
		htmlElement.addClass("list-group").addClassConditional(flush, "list-group-flush");
		
		contentDiv = factory.getHTMLFactory().nonEmptyDiv().addClass("tab-content");		
	}

	@Override
	public boolean isEmpty() {
		return htmlElement.isEmpty();
	}
	
	@Override
	public void item(Object name, Object content) {
		contentAction(name, isEmpty(), false, Color.DEFAULT, null, content);		
	}

	@Override
	public Tag action(boolean active, boolean disabled, Color color, Object href, Object... name) {
		Tag a = configureLink(getFactory().getHTMLFactory().tag(TagName.a, name).attribute("href", href), active, disabled, color);
		htmlElement.content(a);
		return a;
	}
	
	protected Tag configureLink(Tag a, boolean active, boolean disabled, Color color) {
		boolean hasColor = color != null && color.code != null;
		return a.addClass("list-group-item", "list-group-action")				
				.addClassConditional(active, "active")
				.addClassConditional(disabled, "disabled")
				.addClassConditional(hasColor, hasColor ? "list-group-item-"+color.code : null);
	}

	@Override
	public Tag contentAction(
			Object name, 
			boolean active, 
			boolean disabled, 
			Color color, 
			Object contentId,
			Object... content) {
		htmlElement.attribute("role", "tablist");
		if (contentId == null) {
			contentId = "list-group-action-"+getFactory().getHTMLFactory().nextId();
		}
		Tag a = configureLink(getFactory().getHTMLFactory().tag(TagName.a, name).attribute("href", "#"+contentId), active, disabled, color);
		a.attribute("data-toggle", "list").attribute("role", "tab");
		htmlElement.content(a);
		
		Tag cDiv = getFactory().getHTMLFactory().nonEmptyDiv(content)
				.id(contentId)
				.attribute("role", "tabpanel")
				.addClass("tab-pane", "fade")
				.addClassConditional(active, "show", "active");				
				
		contentDiv.content(cDiv);				
		
		return a;
	}

	@Override
	public Navs asNavs() {
		class NavsAdapter extends WrappingBootstrapElementImpl<Tag,Navs> implements Navs {

			protected NavsAdapter() {
				super(ActionGroupImpl.this.getFactory(), ActionGroupImpl.this.htmlElement);
			}

			@Override
			public void item(Object name, Object content) {
				ActionGroupImpl.this.item(name, content);
			}

			@Override
			public boolean isEmpty() {
				return ActionGroupImpl.this.isEmpty();
			}

			@Override
			public Navs item(Object name, boolean active, boolean disabled, Object contentId, Object... content) {
				ActionGroupImpl.this.contentAction(name, active, disabled, Color.DEFAULT, contentId, content);
				return this;
			}

			@Override
			public Tag getContentDiv() {
				return ActionGroupImpl.this.contentDiv;
			}
		
			
		}
		return new NavsAdapter();
	}

	@Override
	public Tag getContentDiv() {
		return contentDiv;
	}

	@Override
	public Container asContainer() {
		Container ret = getFactory().container();
		Row row = ret.row();
		row.col(htmlElement).widthAuto();
		row.col(contentDiv);
		return ret;
	}
	
}
