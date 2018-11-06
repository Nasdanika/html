package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;

public interface Button<H extends HTMLElement<?>> extends BootstrapElement<H> {
	
	Button<H> large();
	Button<H> large(boolean large);
	
	Button<H> small();
	Button<H> small(boolean small);
	
	Button<H> block();
	Button<H> block(boolean block);
	
	Button<H> active();
	Button<H> active(boolean active);
	
	
	Button<H> disabled();
	
	/**
	 * Sets disabled attribute for buttons, adds disabled class otherwise.
	 * @param disabled
	 * @return
	 */
	Button<H> disabled(boolean disabled);
	
	Button<H> dataToggle();
	
	/**
	 * Sets data-toggle to button.
	 * @param dataToggle
	 * @return
	 */
	Button<H> dataToggle(boolean dataToggle);
	

}
