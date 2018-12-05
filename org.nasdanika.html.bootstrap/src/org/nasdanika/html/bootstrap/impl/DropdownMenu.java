package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Dropdown;

/**
 * Handles dropdown menu container
 * @author Pavel Vlasov
 *
 */
public class DropdownMenu extends DivWrappingBootstrapElementImpl<Dropdown> implements Dropdown {
	
	public DropdownMenu(BootstrapFactory factory) {
		super(factory);
		htmlElement.addClass("dropdown-menu");
	}

	@Override
	public Dropdown item(HTMLElement<?> item, boolean active, boolean disabled) {
		item
			.addClass("dropdown-item")
			.addClassConditional(active, "active")
			.addClassConditional(disabled, "disabled");
		htmlElement.content(item);
		return this;
	}

	@Override
	public Dropdown header(Object... content) {
		htmlElement.content(getFactory().getHTMLFactory().tag(TagName.h6, content).addClass("dropdown-header"));
		return this;
	}

	@Override
	public Dropdown divider() {
		htmlElement.content(getFactory().getHTMLFactory().div().addClass("dropdown-divider"));
		return this;
	}

	@Override
	public Dropdown form(Form form) {
		htmlElement.content(form);
		return this;
	}

}
