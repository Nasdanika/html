package org.nasdanika.html.knockout;

import java.util.List;

import org.nasdanika.html.Container;
import org.nasdanika.html.Producer;

public interface KnockoutVirtualElement extends KnockoutControlFlow<KnockoutVirtualElement>, Container<KnockoutVirtualElement>, Producer {
	
	boolean isEmpty();
		
	List<Object> getContent();	
	
}
