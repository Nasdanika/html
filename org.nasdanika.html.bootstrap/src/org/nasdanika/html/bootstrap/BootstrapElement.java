package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Markup;
import org.nasdanika.html.Producer;

public interface BootstrapElement<H extends HTMLElement<?>> extends AutoCloseable, Producer, Markup {
	
	H toHTMLElement();
	
	BootstrapFactory getFactory();

}
