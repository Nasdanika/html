package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Container;
import org.nasdanika.html.Tag;

public interface Well extends BootstrapElement<Tag>, Container<Well> {
	
	Well small();
	
	Well large();

}
