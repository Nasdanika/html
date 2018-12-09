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
	public Tag script(Object url) {
		Tag script = factory.tag(TagName.script).attribute("src", url);
		head(script);
		return script;
	}
	
	/**
	 * Adds stylesheet to the header.
	 * @param url
	 */
	public Tag stylesheet(Object url) {
		Tag stylesheet = factory.tag(TagName.link).attribute("rel", "stylesheet").attribute("href", url);
		head(stylesheet);
		return stylesheet;
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
	public Tag title(Object title) {
		Tag ret = factory.tag(TagName.title, title);
		head(ret);
		return ret;
	}
	
	@Override
	public String toString() {
		return HTMLElementImpl.stringify(produce(0));
	}

	@Override
	public HTMLPage lang(Object lang) {
		html.attribute("lang", lang);
		return this;
	}

	@Override
	public HTMLFactory getFactory() {
		return factory;
	}

	@Override
	public void close() throws Exception {
		html.close();		
	}	

}
