package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.Card;

public class CardNavsImpl implements Card.Navs {
	
	private static final String NAV_TABS = "nav-tabs";
	private static final String NAV_PILLS = "nav-pills";
	
	private static final String CARD_HEADER_TABS = "card-header-tabs";
	private static final String CARD_HEADER_PILLS = "card-header-pills";
	
	private Tag contentDiv;
	private CardImpl card;
	private Tag navsDiv;

	protected CardNavsImpl(CardImpl card) {
		this.card = card;
		
		navsDiv = card.getFactory().getHTMLFactory().nonEmptyDiv();
		navsDiv.addClass("nav");
		card.getHeader().toHTMLElement().content(navsDiv);
		
		contentDiv = card.getFactory().getHTMLFactory().nonEmptyDiv().addClass("tab-content");
		card.getBody().toHTMLElement().content(contentDiv);
	}

	@Override
	public Card.Navs item(Object name, boolean active, boolean disabled, Object contentId, Object... content) {
		if (contentId == null) {
			contentId = "card-nav-"+card.getFactory().getHTMLFactory().nextId();
		}
		Tag navLink = card.getFactory().getHTMLFactory().link("#"+contentId, name)
				.addClass("nav-item", "nav-link")
				.addClassConditional(active, "active")
				.attribute("data-toggle", "tab")
				.addClassConditional(disabled, "disabled")
				.attribute("role", "tab");
		navsDiv.content(navLink);

		Tag cDiv = card.getFactory().getHTMLFactory().nonEmptyDiv(content)
				.id(contentId)
				.attribute("role", "tabpanel")
				.addClass("tab-pane", "fade")
				.addClassConditional(active, "show", "active");
		
		if (!cDiv.isEmpty()) {
			navsDiv.attribute("role", "tablist");
		}
				
		contentDiv.content(cDiv);		
		return this;
	}
	
//	@Override
//	public Dropdown dropdown(boolean active, Object... name) {
//		HTMLFactory htmlFactory = card.getFactory().getHTMLFactory();
//		Tag toggle = htmlFactory.link("#", name)
//				.addClass("nav-link", "dropdown-toggle")
//				.attribute("role", "button")
//				.attribute("data-toggle", "dropdown");		
//		DropdownMenu menu = new DropdownMenu(card.getFactory());
//		Tag li = htmlFactory.tag(TagName.li, toggle, menu.toHTMLElement())
//				.addClass("nav-item", "dropdown")
//				.addClassConditional(active, "active");
//		navsDiv.content(li); 
//		return menu;
//	}	
	
	@Override
	public String toString() {
		return card.toString();
	}

	@Override
	public Card.Navs tabs(boolean tabs) {
		navsDiv.addClassConditional(tabs, NAV_TABS);
		navsDiv.addClassConditional(tabs, CARD_HEADER_TABS);
		return this;
	}

	@Override
	public Card.Navs pills(boolean pills) {
		navsDiv.addClassConditional(pills, NAV_PILLS);
		navsDiv.addClassConditional(pills, CARD_HEADER_PILLS);
		return this;
	}

}
