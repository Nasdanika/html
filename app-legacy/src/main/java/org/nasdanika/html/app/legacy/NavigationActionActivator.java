package org.nasdanika.html.app;

/**
 * Action activator which navigates to specified location/URL. 
 * @author Pavel Vlasov
 *
 */
public interface NavigationActionActivator extends ActionActivator {
	
	/**
	 * @param base If not null then the action URL is relativized against the base.
	 * @return Navigation target (URL). If this method returns null then the UI may use id-based action execution. 
	 * For non-action actions, e.g. drop-down buttons or menu nodes used to solely group other nodes use "#".
	 */
	String getUrl(String base);

}
