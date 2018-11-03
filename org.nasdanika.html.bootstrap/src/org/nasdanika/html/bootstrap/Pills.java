package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;

public interface Pills extends BootstrapElement<Tag>, NamedItemsContainer<HTMLElement<?>> {
	
	Pills stacked();
	
	Pills stacked(boolean stacked);
		
	Pills justified();
	
	Pills justified(boolean justified);	
	
	/**
	 * Width of the pills column.
	 * @param deviceSize
	 * @param width
	 * @return
	 */
	Pills pillsWidth(Bootstrap.DeviceSize deviceSize, int width);	
	
}
