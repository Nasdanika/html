package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.Container.Row.Col;

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
			
			public Col widthAuto();

			public Col widthAuto(DeviceSize deviceSize);
			
		}
		
		Col col(Object... content);
		
	}
	
	Row row();
	
}
