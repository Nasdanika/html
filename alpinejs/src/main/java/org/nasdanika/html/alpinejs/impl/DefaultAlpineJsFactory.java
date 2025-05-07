package org.nasdanika.html.alpinejs.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.alpinejs.AlpineJs;
import org.nasdanika.html.alpinejs.AlpineJsFactory;

public class DefaultAlpineJsFactory implements AlpineJsFactory {
	
	private HTMLFactory htmlFactory;

	public DefaultAlpineJsFactory(HTMLFactory htmlFactory) {
		this.htmlFactory = htmlFactory;
	}
	
	public DefaultAlpineJsFactory() {
		this(HTMLFactory.INSTANCE);
	}
	
	@Override
	public HTMLFactory getHTMLFactory() {
		return htmlFactory;
	}
	
	@Override
	public <H extends HTMLElement<?>> AlpineJs<H> from(H htmlElement) {
		return new AlpineJsImpl<H>(this, htmlElement);
	}

	@Override
	public <P extends HTMLPage> P cdn(P page) {
		page.script("https://cdn.jsdelivr.net/npm/alpinejs@3.x.x/dist/cdn.min.js").attribute("defer", true);
		return page;
	}
	
	protected Tag createTemplateTag(HTMLElement<?> contents) {
		return htmlFactory.tag("template", contents);
	}

	@Override
	public Tag teleport(Object selector, HTMLElement<?> element) {
		return createTemplateTag(element).attribute("x-teleport", selector);
	}

	@Override
	public Tag _for(Object expression, HTMLElement<?> element) {
		return createTemplateTag(element).attribute("x-for", expression);
	}

	@Override
	public Tag _if(Object condition, HTMLElement<?> element) {
		return createTemplateTag(element).attribute("x-if", condition);
	}

}
