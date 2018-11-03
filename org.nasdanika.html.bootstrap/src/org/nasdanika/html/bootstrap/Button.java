package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Container;

public interface Button extends BootstrapElement<org.nasdanika.html.Button>, Dropdown<Button>, Container<Button> {
		
	Button style(Bootstrap.Style style);
	
	Button size(Bootstrap.Size size);
	
	Button block(boolean block);
	
	Button block();
	
	Button active(boolean active);
	
	Button active();
	
	Button disabled(boolean disabled);
	
	Button disabled();
	
	Button split(boolean split);
	
	Button split();
	
	Button dropup(boolean dropup);
	
	Button dropup();

}
