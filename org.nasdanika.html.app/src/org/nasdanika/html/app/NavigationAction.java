package org.nasdanika.html.app;

/**
 * Action which navigates to specified location/URL. 
 * @author Pavel Vlasov
 *
 */
public interface NavigationAction extends Action {
	
	/**
	 * @return Navigation target (URL). If this method returns null then the UI may use id-based action execution. 
	 * For non-action actions, e.g. drop-down buttons or menu nodes used to solely group other nodes use "#".
	 */
	String getHref();

}
