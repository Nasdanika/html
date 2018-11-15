package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

/**
 * Bootstrap container.
 * @author Pavel Vlasov
 *
 */
public interface Container extends BootstrapElement<Tag,Container> {
	
	interface Row extends BootstrapElement<Tag,Row> {
		
		interface Col extends BootstrapElement<Tag,Col> {
			
			Col width(int width);
			
			Col width(DeviceSize deviceSize, int width);
			
		}
		
		Col col(Object... content);
		
	}
	
	Row row();
	
}
