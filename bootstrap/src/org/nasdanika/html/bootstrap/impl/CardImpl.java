package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

public class CardImpl extends WrappingBootstrapElementImpl<Tag, Card> implements Card {

	private TagBootstrapElement title;
	private TagBootstrapElement body;
	private TagBootstrapElement footer;

	public CardImpl(BootstrapFactory factory) {
		super(factory, factory.getHTMLFactory().nonEmptyDiv());
		htmlElement.addClass("card");
		
		title = factory.wrap(factory.getHTMLFactory().nonEmptyDiv());
		title.toHTMLElement().addClass("card-header");
		htmlElement.content(title.toHTMLElement());
				
		body = factory.wrap(factory.getHTMLFactory().nonEmptyDiv());
		body.toHTMLElement().addClass("card-body");
		htmlElement.content(body.toHTMLElement());
		
		footer = factory.wrap(factory.getHTMLFactory().nonEmptyDiv());
		footer.toHTMLElement().addClass("card-footer");
		htmlElement.content(footer.toHTMLElement());
		
	}

	@Override
	public TagBootstrapElement getTitle() {
		return title;
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
