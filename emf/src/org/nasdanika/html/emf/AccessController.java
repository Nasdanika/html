package org.nasdanika.html.emf;

/**
 * Interface to adapt to for access decisions.
 * @author Pavel Vlasov
 *
 */
public interface AccessController {
	
	/**
	 * Access controller which permits all actions.
	 */
	AccessController ALLOW_ALL = new AccessController() {
		
		@Override
		public boolean hasPermission(String action, String qualifier) {
			return true;
		}
		
	};
	
	/**
	 * Access controller which permits nothing.
	 */
	AccessController DENY_ALL = new AccessController() {
		
		@Override
		public boolean hasPermission(String action, String qualifier) {
			return false;
		}
		
	};	
	
	/**
	 * Checks for a permission to perform an action for a given qualifier.
	 * @param action
	 * @param qualifier
	 * @return
	 */
	boolean hasPermission(String action, String qualifier);
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canRead(String qualifier) {
		return hasPermission("read", qualifier);
	}
	
	
	/**
	 * Checks "create" permission
	 * @param qualifier
	 * @return
	 */
	default boolean canCreate(String qualifier) {
		return hasPermission("create", qualifier);
	}
	
	/**
	 * Checks "delete" permission
	 * @param qualifier
	 * @return
	 */
	default boolean canDelete(String qualifier) {
		return hasPermission("delete", qualifier);
	}
	
	/**
	 * Checks "update" permission
	 * @param qualifier
	 * @return
	 */
	default boolean canUpdate(String qualifier) {
		return hasPermission("update", qualifier);
	}
	
	/**
	 * Checks "execute" permission
	 * @param qualifier
	 * @param context
	 * @return
	 */
	default boolean canExecute(String qualifier) {
		return hasPermission("execute", qualifier);
	}

}
