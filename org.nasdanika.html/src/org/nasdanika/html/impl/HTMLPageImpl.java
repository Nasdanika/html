package org.nasdanika.html.impl;

import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;

public class HTMLPageImpl implements HTMLPage {

	private HTMLFactory factory;
	private Tag html;
	private Tag head;
	private Tag body;
	
	HTMLPageImpl(HTMLFactory factory) {
		this.factory = factory;
		head = factory.tag(TagName.head);
		body = factory.tag(TagName.body);
		html = factory.tag(TagName.html, head, body);
	}
	
	@Override
	public Object produce(int indent) {
		return "<!DOCTYPE html>"+html.produce(indent);
	}
	
	@Override
	public HTMLPage body(Object... content) {
		body.content(content);
		return this;
	}
	
	/**
	 * Adds external script to the header.
	 * @param url
	 */
	public HTMLPage script(Object url) {
		head(factory.tag(TagName.script).attribute("scr", url));
		return this;
	}
	
	/**
	 * Adds stylesheet to the header.
	 * @param url
	 */
	public HTMLPage stylesheet(Object url) {
		head(factory.tag(TagName.link).attribute("rel", "stylesheet").attribute("href", url));
		return this;
	}
	
	/**
	 * Adds content to html head element of the page.
	 * @param content
	 */
	public HTMLPage head(Object... content) {
		head.content(content);
		return this;
	}
	
	/**
	 * Adds page title.
	 * @param title
	 */
	public HTMLPage title(Object title) {
		head(factory.tag(TagName.title, title));
		return this;
	}
	
	@Override
	public String toString() {
		return HTMLElementImpl.stringify(produce(0), 0, factory);
	}
	

}
