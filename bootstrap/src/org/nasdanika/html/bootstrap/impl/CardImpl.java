package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

public class CardImpl extends WrappingBootstrapElementImpl<Tag, Card> implements Card {

	private TagBootstrapElement header;
	private TagBootstrapElement body;
	private TagBootstrapElement footer;

	public CardImpl(BootstrapFactory factory) {
		super(factory, factory.getHTMLFactory().nonEmptyDiv());
		htmlElement.addClass("card");
		
		header = factory.wrap(factory.getHTMLFactory().nonEmptyDiv());
		header.toHTMLElement().addClass("card-header");
		htmlElement.content(header.toHTMLElement());
				
		body = factory.wrap(factory.getHTMLFactory().nonEmptyDiv());
		body.toHTMLElement().addClass("card-body");
		htmlElement.content(body.toHTMLElement());
		
		footer = factory.wrap(factory.getHTMLFactory().nonEmptyDiv());
		footer.toHTMLElement().addClass("card-footer");
		htmlElement.content(footer.toHTMLElement());
		
	}

	@Override
	public TagBootstrapElement getHeader() {
		return header;
	}

	@Override
	public TagBootstrapElement getBody() {
		return body;
	}

	@Override
	public TagBootstrapElement getFooter() {
		return footer;
	}

}
