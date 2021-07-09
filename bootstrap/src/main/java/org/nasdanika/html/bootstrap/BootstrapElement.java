package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Markup;
import org.nasdanika.html.Producer;

public interface BootstrapElement<H extends HTMLElement<?>, B extends BootstrapElement<H,?>> extends Producer, Markup {
	
	H toHTMLElement();
	
	BootstrapFactory getFactory();
	
	/**
	 * Adds border classes
	 * @param color Optional color, may be null.
	 * @param placement Optionial placements, all borders if none a specified.
	 * @return
	 */
	B border(Color color, Placement... placement);
	
	B background(Color color);
	
	Text<B> text();
	
	Spacing<B> margin();
	
	Spacing<B> padding();
	
	Float<B> _float();

}
