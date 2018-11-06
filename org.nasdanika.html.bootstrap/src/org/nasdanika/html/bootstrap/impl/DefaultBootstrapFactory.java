package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumbs;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.Color;

/**
 * HTML factory which relies on Bootstrap styles and scripts.
 * @author Pavel
 *
 */
public class DefaultBootstrapFactory implements BootstrapFactory {
	
	private HTMLFactory htmlFactory;

	public DefaultBootstrapFactory(HTMLFactory htmlFactory) {
		this.htmlFactory = htmlFactory;
	}
	
	@Override
	public HTMLFactory getHTMLFactory() {
		return htmlFactory;
	}

	@Override
	public Tag alert(Color color, Object... content) {
		return getHTMLFactory().div(content)
				.addClass("alert")
				.addClass("alert-"+color.code)
				.attribute("role", "alert");
	}

	@Override
	public Tag badge(boolean pill, Color color, Object... content) {
		Tag ret = getHTMLFactory().span(content)
				.addClass("badge")
				.addClass("badge-"+color.code)
				.addClassConditional(pill, "badge-pill");
		return ret;
	}

	@Override
	public Tag badge(boolean pill, Object... content) {
		Tag ret = getHTMLFactory().span(content)
				.addClass("badge")
				.addClassConditional(pill, "badge-pill");
		return ret;
	}

	@Override
	public Tag badgeLink(Object href, boolean pill, Color color, Object... content) {
		Tag ret = getHTMLFactory().link(href, content)
				.addClass("badge")
				.addClass("badge-"+color.code)
				.addClassConditional(pill, "badge-pill");
		return ret;
	}

	@Override
	public Breadcrumbs breadcrums() {
		return new BreadcrumbsImpl(this);
	}

	@Override
	public <H extends HTMLElement<?>> Button<H> button(H htmlElement, Color color, boolean outline) {
		return new ButtonImpl(this, htmlElement, color, outline);
	}
	
}
