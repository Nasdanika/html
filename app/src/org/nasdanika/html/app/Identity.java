package org.nasdanika.html.app;

import java.util.HashMap;
import java.util.Map;

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
		
	/**
	 * Stores action data into a map, which can then be stored as JSON or YAML or some other format.
	 * @return
	 */
	default Map<String, Object> toMap() {
		Map<String, Object> ret = new HashMap<>();
		if (getId() != null) {
			ret.put("id", getId());
		}
		return ret;
	}
	
}
