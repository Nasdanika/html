package org.nasdanika.html.app;

/**
 * Change in property value.
 * @author Pavel Vlasov
 *
 */
public interface Delta {
	
	/**
	 * Property id.
	 * @return
	 */
	Object getPropertyId();
	
	/**
	 * Original value of the property to detect update conflicts.
	 * @return
	 */
	Object getOriginalValue();
	
	/**
	 * New value of the property.
	 * @return
	 */
	Object getNewValue();

}
