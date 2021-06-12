package org.nasdanika.html.app;

/**
 * Something with unique ID.
 * @author Pavel Vlasov
 */
public interface Identity {
	
	/**
	 * Application element id.  
	 * The same application element (label) may be rendered as different UI elements. When it comes to actions the UI may be structured to dispatch actions invocations to a function
	 * and pass elements or action id to it. In "object-oriented" UI's action id may be formed from object id, action, and optional qualifier. E.g. L3-view-accounts.
	 * 
	 * Ids may be used as map keys and as such shall properly implement hashCode() and equals().
	 * @return
	 */
	Object getId();		
	
}
