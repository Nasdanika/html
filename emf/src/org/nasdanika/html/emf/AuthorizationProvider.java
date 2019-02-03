package org.nasdanika.html.emf;

/**
 * Interface to adapt to for authorization decisions.
 * @author Pavel Vlasov
 *
 */
public interface AuthorizationProvider {
	
	/**
	 * Authorization provider which allows all actions.
	 */
	AuthorizationProvider ALLOW_ALL = new AuthorizationProvider() {
		
		@Override
		public boolean authorize(String action, String qualifier) {
			return true;
		}
		
	};
	
	/**
	 * Authorization provider which denies all actions.
	 */
	AuthorizationProvider DENY_ALL = new AuthorizationProvider() {
		
		@Override
		public boolean authorize(String action, String qualifier) {
			return false;
		}
		
	};	
	
	/**
	 * Authorizes an action for a given qualifier.
	 * @param action
	 * @param qualifier
	 * @return
	 */
	boolean authorize(String action, String qualifier);
	
	/**
	 * Authorizes "read" action
	 * @param qualifier
	 * @return
	 */
	default boolean authorizeRead(String qualifier) {
		return authorize("read", qualifier);
	}
	
	
	/**
	 * Authorizes "create" action
	 * @param qualifier
	 * @return
	 */
	default boolean authorizeCreate(String qualifier) {
		return authorize("create", qualifier);
	}
	
	/**
	 * Authorizes "delete" action
	 * @param qualifier
	 * @return
	 */
	default boolean authorizeDelete(String qualifier) {
		return authorize("delete", qualifier);
	}
	
	/**
	 * Authorizes "update" action
	 * @param qualifier
	 * @return
	 */
	default boolean authorizeUpdate(String qualifier) {
		return authorize("update", qualifier);
	}
	
	/**
	 * Authorizes "execute" action
	 * @param qualifier
	 * @param context
	 * @return
	 */
	default boolean authorizeExecute(String qualifier) {
		return authorize("execute", qualifier);
	}

}
