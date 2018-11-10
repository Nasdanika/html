package org.nasdanika.html.fontawesome.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.fontawesome.Icon;
import org.nasdanika.html.fontawesome.Icon.Size;
import org.nasdanika.html.fontawesome.Icon.Stack;
import org.nasdanika.html.fontawesome.Icon.Style;

/**
 * HTML factory which relies on Bootstrap styles and scripts.
 * @author Pavel
 *
 */
public class DefaultFontAwesomeFactory implements FontAwesomeFactory {
	
	private HTMLFactory htmlFactory;

	public DefaultFontAwesomeFactory(HTMLFactory htmlFactory) {
		this.htmlFactory = htmlFactory;
	}
	
	@Override
	public Icon<Tag> icon(String icon, Style style) {
		return from(icon, style, htmlFactory.tag("i"));
	}
		
	@Override
	public Stack stack() {
		return new Stack() {
			
			private Tag span = htmlFactory.span().addClass("fa-stack");

			@Override
			public Stack icon(HTMLElement<?> icon, IconSize size, boolean inverse) {
				if (size==null) {
					icon.addClass("fa-stack-1x");
				} else {
					switch (size) {
					case x1:
						icon.addClass("fa-stack-1x");
						break;
					case x2:
						icon.addClass("fa-stack-2x");
						break;
					case x3:
						icon.addClass("fa-stack-3x");
						break;
					case x4:
						icon.addClass("fa-stack-4x");
						break;
					case x5:
						icon.addClass("fa-stack-5x");
						break;
					default:
						throw new IllegalArgumentException("Unexpected icon size: "+size);					
					}
				}
				if (inverse) {
					icon.addClass("fa-inverse");
				}
				span.content(icon);
				return this;
			}

			@Override
			public Stack size(Size size) {
				IconImpl.size(size, span);
				return this;
			}

			@Override
			public void close() throws Exception {
				span.close();				
			}

			@Override
			public Stack icon(Icon<?> icon, IconSize size, boolean inverse) {
				return icon(icon.toHTMLElement(), size, inverse);
			}
			
			@Override
			public String toString() {
				return span.toString();
			}
			
		};
	}

	@Override
	public HTMLFactory getHTMLFactory() {
		return htmlFactory;
	}

	@Override
	public <T extends HTMLElement<?>> Icon<T> from(String icon, Style style, T htmlElement) {
		return new IconImpl<T>(icon, style, htmlElement);
	}
	
}
