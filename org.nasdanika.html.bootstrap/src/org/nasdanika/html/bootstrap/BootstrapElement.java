package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Producer;
import org.nasdanika.html.Markup;

public interface BootstrapElement<H extends HTMLElement<?>> extends AutoCloseable, Producer, Markup {
	
	H toHTMLElement();
	
	BootstrapFactory getFactory();

}
