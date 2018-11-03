package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;

public interface Tabs extends BootstrapElement<Tag>, NamedItemsContainer<HTMLElement<?>> {
	
	Tabs justified();
	
	Tabs justified(boolean justified);
	
}
