package org.nasdanika.html.impl;

import org.nasdanika.html.Accordion;
import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;
import org.nasdanika.html.UIElement;

class AccordionImpl extends UIElementImpl<Accordion> implements	Accordion {

	private class Item extends UIElementImpl<Item> {
		
		Item(Object title, Object[] itemContent, Object location, Bootstrap.Style style, boolean initial, Object id) {
			super(AccordionImpl.this.factory, "div");
			addClass("panel");
			addClass("panel-"+effectiveStyle(style).name().toLowerCase());
			if (id==null) {
				id = factory.nextId()+"_collapse";
			}
			
			Tag titleLink = factory.tag(TagName.a, title)
					.attribute("data-toggle", "collapse")
					.attribute("data-parent", "#"+AccordionImpl.this.getId())
					.attribute("href", "#"+id+"_collapse")
					.attribute("aria-expanded", initial)
					.attribute("aria-controls", id+"_collapse");
			
			Tag titleHeader = factory.tag(TagName.h4, titleLink).addClass("panel-title");
			this.content.add(factory.div(titleHeader).addClass("panel-heading").attribute("role", "tab").id(id+"_heading"));
			
			Tag body = factory.div(itemContent)
					.id(id+"_panel_body")
					.addClass("panel-body");
			
			Tag contentDiv = factory.div(body)
					.id(id+"_collapse")
					.addClass("panel-collapse")
					.addClass("collapse");
			
			if (initial) {
				contentDiv.addClass("in");
			}
			this.content.add(contentDiv);
			
			if (location!=null) {
				content.add(factory.tag(TagName.script, "nsdLoad(\"#"+id+"_panel_body\", \""+location+"\");"));
			}					
		}
		
		private Bootstrap.Style effectiveStyle(Bootstrap.Style style) {
			if (style!=null) {
				return style;
			}
			
			if (AccordionImpl.this.style!=null) {
				return AccordionImpl.this.style;
			}
			
			return Bootstrap.Style.DEFAULT;
		}
		
	}
	
	private Bootstrap.Style style;

	public AccordionImpl(HTMLFactory factory) {
		super(factory, "div");
		id(factory.nextId()+"_accordion");
		addClass("panel-group");
		attribute("role", "tablist");
	}
	
	@Override
	public UIElement<?> item(Object title, Bootstrap.Style style, boolean initial, Object id, Object itemContent) {
		Item item = new Item(title, new Object[] {itemContent}, null, style, initial, id);
		this.content.add(item);
		return item;
	}
		
	@Override
	public Accordion style(org.nasdanika.html.Bootstrap.Style style) {
		this.style = style;
		return this;
	}

	@Override
	public UIElement<?> item(Object name, Object itemContent) {
		Item item = new Item(name, new Object[] {itemContent}, null, style, this.content.isEmpty(), null);
		this.content.add(item);
		return item;
	}

	@Override
	public UIElement<?> ajaxItem(Object name, Object location) {
		return ajaxItem(name, null, null, location);
	}

	@Override
	public boolean isEmpty() {
		return content.isEmpty();
	}

	@Override
	public UIElement<?> ajaxItem(Object title,	Bootstrap.Style style, Object id, Object location) {
		Item item = new Item(title, null, location, style, this.content.isEmpty(), id);
		this.content.add(item);
		return item;
	}

}
