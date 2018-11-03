package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;

public interface Carousel extends BootstrapElement<Tag> {
	
	interface Slide {
		
		Slide active(boolean active);
		
		Slide active();
		
		Slide content(Object... content);
		
		Slide caption(Object... caption);
		
	}
	
	Carousel ride(boolean ride);
	
	Carousel indicatorsBackground(Bootstrap.Color background);
	
	Carousel indicatorsBackground(HTMLElement.HTMLColor background);
	
	Slide slide();
		
}
