package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapElement;
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
		
		menu = new DropdownMenu(factory, factory.wrap(toggleHtmlElement));
		htmlElement.content(menu.toHTMLElement());
	}
	
	@Override
	public Dropdown item(HTMLElement<?> item, boolean active, boolean disabled) {
		menu.item(item, active, disabled);
		return this;
	}

	@Override
	public Tag header(Object... content) {
		return menu.header(content);
	}

	@Override
	public Tag divider() {
		return menu.divider();
	}

	@Override
	public Dropdown form(Form form) {
		menu.form(form);
		return this;
	}

	@Override
	public BootstrapElement<?, ?> getToggle() {
		return menu.getToggle();
	}
	
}
