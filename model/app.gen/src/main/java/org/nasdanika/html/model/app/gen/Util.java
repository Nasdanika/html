package org.nasdanika.html.model.app.gen;

import java.util.List;

import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.model.bootstrap.Item;

public final class Util {
	
	// Util
	private Util() {
		throw new UnsupportedOperationException("Utility class, not to be instantiated");
	}

	/**
	 * Creates Bootstrap Navs from a list of items.
	 * @param items
	 * @param bootstrapFactory
	 * @return
	 */
	public static Tag navs(List<Object> items, BootstrapFactory bootstrapFactory) {
		HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
		Tag navs = htmlFactory.tag(TagName.ul).addClass("nav");
		for (Object item: items) {
			Tag li = htmlFactory.tag(TagName.li, item).addClass("nav-item");
			navs.accept(li);
			if (item instanceof Tag) {
				Tag itemTag = (Tag) item;
				if (TagName.a.name().equalsIgnoreCase(itemTag.getTagName())) {
					itemTag.addClass("nav-link");
					Object data = itemTag.getData();
					if (data instanceof Item) {
						Item itemData = (Item) data;
						if (itemData.isActive()) {
							itemTag.addClass("active");
						} else if (itemData.isDisabled()) {
							itemTag.addClass("disabled");
						}
					}
				} else if (TagName.span.name().equalsIgnoreCase(itemTag.getTagName())) {
					itemTag.addClass("navbar-text");
				}
			}
		}
		return navs;
	}
	
	/**
	 * Creates Bootstrap Navs from a list of items.
	 * @param items
	 * @param bootstrapFactory
	 * @return
	 */
	public static Tag navbar(
			Tag brand, 
			List<Object> items,
			Breakpoint expand, 
			boolean dark,
			Color background,
			BootstrapFactory bootstrapFactory) {
		HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
		Tag navbar = htmlFactory.tag(TagName.ul).addClass("navbar", dark ? "navbar-dark" : "navbar-light");
		if (expand != null) {
			navbar.addClass("navbar-expand-" + expand.code);
		}
		if (background != null) {
			navbar.addClass("bg-" + background.code);
		}
		if (brand != null) {
			brand.addClass("navbar-brand");
			navbar.accept(brand);
		}
		String navbarContentId = htmlFactory.nextId();
		Button toggler = htmlFactory.button(htmlFactory.span().addClass("navbar-toggler-icon"));
		toggler
			.addClass("navbar-toggler")
			.attribute("type", "button")
			.attribute("data-toggle", "collapse")
			.attribute("data-target", "#" + navbarContentId)
			.attribute("aria-expanded", "false")
			.attribute("aria-label", "Toggle navigation");
		navbar.accept(toggler);
		
		Tag content = htmlFactory.div().addClass("collapse", "navbar-collapse").id(navbarContentId);
		navbar.accept(content);
		Tag navs = navs(items, bootstrapFactory);
		navs.removeClass("nav").addClass("navbar-nav", "mr-auto");
		content.accept(navs);
		return navbar;
	}
		
}
