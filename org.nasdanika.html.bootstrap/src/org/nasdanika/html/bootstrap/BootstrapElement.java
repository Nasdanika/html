package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;

public interface BootstrapElement<H extends HTMLElement<?>> {
	
	H toHTMLElement();

}
