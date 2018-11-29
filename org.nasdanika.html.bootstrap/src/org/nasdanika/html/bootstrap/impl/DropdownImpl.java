package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.Direction;
import org.nasdanika.html.bootstrap.Dropdown;

public class DropdownImpl extends DivWrappingBootstrapElementImpl<Dropdown> implements Dropdown {
	
	protected DropdownMenu menu;

	public DropdownImpl(BootstrapFactory factory, Button<?> button, boolean split, Direction direction) {
		super(factory);
		htmlElement.addClass("btn-group");
		HTMLFactory htmlFactory = getFactory().getHTMLFactory();
		htmlElement.content(button);

		switch (direction) {
		case DOWN:
			// Default behavior
			break;
		case LEFT:
		case RIGHT:
		case UP:
			htmlElement.addClass("drop"+direction.name().toLowerCase());
			break;
		default:
			break;
		
		}
		
		HTMLElement<?> toggleHtmlElement = button.toHTMLElement();
		
		if (split) {
			org.nasdanika.html.Button splitHtmlButton = htmlFactory.button(htmlFactory.span("Toggle Dropdown").addClass("sr-only"))
					.addClass("dropdown-toggle-split");
			Button<?> splitButton = getFactory().button(
					splitHtmlButton, 
					((ButtonImpl<?>) button).getColor(), 
					((ButtonImpl<?>) button).isOutline()); 
			
			toggleHtmlElement = splitHtmlButton;
			htmlElement.content(splitButton);
		}
		
		toggleHtmlElement
			.addClass("dropdown-toggle")
			.attribute("data-toggle", "dropdown")
			.attribute("aria-haspopup", "true")
			.attribute("aria-expanded", "false");

		if (toggleHtmlElement.getId() == null) {
			toggleHtmlElement.id(htmlFactory.nextId());
		}		
		
		menu = new DropdownMenu(factory);
		htmlElement.content(menu.toHTMLElement());
	}

	@Override
	public Tag item(Object href, boolean active, boolean disabled, Object... content) {
		return menu.item(href, active, disabled, content);
	}

	@Override
	public Dropdown header(Object... content) {
		menu.header(content);
		return this;
	}

	@Override
	public Dropdown divider() {
		menu.divider();
		return this;
	}

	@Override
	public Dropdown form(Form form) {
		menu.form(form);
		return this;
	}

}
