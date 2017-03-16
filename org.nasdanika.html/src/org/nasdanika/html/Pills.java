package org.nasdanika.html;

public interface Pills extends UIElement<Pills>, NamedItemsContainer<UIElement<?>, Pills> {
	
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
	
	Pills item(Object name, Object content, boolean active);

	Pills ajaxItem(Object name, Object location, boolean active);	
	
}
