package org.nasdanika.html.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.nasdanika.common.Adaptable;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;

public class HTMLPageImpl implements HTMLPage, Adaptable {

	private HTMLFactory factory;
	private Fragment prolog;
	private Tag html;
	private Tag head;
	private Tag body;
	private Fragment epilog;
	
	HTMLPageImpl(HTMLFactory factory) {
		this.factory = factory;
		prolog = factory.fragment();
		head = factory.tag(TagName.head);
		body = factory.tag(TagName.body);
		html = factory.tag(TagName.html, head, body);
		epilog = factory.fragment();
	}
	
	@Override
	public Object produce(int indent) {
		return factory.fragment(
				prolog.isEmpty() ? "<!DOCTYPE html>" : prolog.produce(indent), 
				html.produce(indent), 
				epilog.produce(indent));
	}

	@Override
	public HTMLPage prolog(Object... content) {
		prolog.content(content);
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
	
	@Override
	public HTMLPage body(Object... content) {
		body.content(content);
		return this;
	}

	@Override
	public HTMLPage epilog(Object... content) {
		epilog.content(content);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public <TT> TT adaptTo(Class<TT> type) {
		if (type == InputStream.class) {
			String str = toString();
			return (TT) new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
		}
		return Adaptable.super.adaptTo(type);
	}

}
