package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

/**
 * Bootstrap container.
 * @author Pavel Vlasov
 *
 */
public interface Container extends BootstrapElement<Tag,Container> {
	
	interface Row extends BootstrapElement<Tag,Row> {
		
		interface Col extends BootstrapElement<Tag,Col>, org.nasdanika.html.Container<Col> {
			
			Col width(DeviceSize deviceSize, Size width);
			
		}
		
		Col col(Object... content);
		
	}
	
	Row row();
	
}
