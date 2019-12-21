package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

public class CardImpl extends WrappingBootstrapElementImpl<Tag, Card> implements Card {

	private TagBootstrapElement header;
	private TagBootstrapElement body;
	private TagBootstrapElement footer;
	private CardNavsImpl navs;

	public CardImpl(BootstrapFactory factory) {
		super(factory, factory.getHTMLFactory().nonEmptyDiv());
		htmlElement.addClass("card");
	}

	@Override
	public TagBootstrapElement getHeader() {
		if (header == null) {
			header = getFactory().wrap(getFactory().getHTMLFactory().nonEmptyDiv());
			header.toHTMLElement().addClass("card-header");
			htmlElement.content(header.toHTMLElement());								
		}
		return header;
	}

	@Override
	public TagBootstrapElement getBody() {
		if (body == null) {
			body = getFactory().wrap(getFactory().getHTMLFactory().nonEmptyDiv());
			body.toHTMLElement().addClass("card-body");
			htmlElement.content(body.toHTMLElement());					
		}
		return body;
	}

	@Override
	public TagBootstrapElement getFooter() {
		if (footer == null) {
			footer = getFactory().wrap(getFactory().getHTMLFactory().nonEmptyDiv());
			footer.toHTMLElement().addClass("card-footer");
			htmlElement.content(footer.toHTMLElement());
		}
		return footer;
	}

	@Override
	public Navs asNavs() {
		if (navs == null) {
			navs = new CardNavsImpl(this);
		}
		return navs;
	}

}
