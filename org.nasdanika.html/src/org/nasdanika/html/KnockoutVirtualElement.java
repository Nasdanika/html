package org.nasdanika.html;

import java.util.List;

public interface KnockoutVirtualElement extends KnockoutControlFlow<KnockoutVirtualElement>, Container<KnockoutVirtualElement>, Producer {
	
	boolean isEmpty();
		
	List<Object> getContent();	
	
}
