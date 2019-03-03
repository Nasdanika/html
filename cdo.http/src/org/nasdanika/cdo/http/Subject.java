package org.nasdanika.cdo.http;

import java.util.Map;

import org.eclipse.emf.cdo.transaction.CDOTransaction;

/**
 * Subject represents one or more {@link Principal}s arriving at a decision whether to allow or deny access.
 * An example of such principals may be a user principal and group principals for groups the user is part of 
 * @author Pavel
 *
 */
public interface Subject {

	/**
	 * Returns false for all authorization requests. It can be used to indicate that the authorization
	 * information was present in the request but was not accepted.  
	 */
	Subject UNAUTHORIZED_SUBJECT = new Subject() {

		@Override
		public boolean authorize(Object target, String action, String qualifier, Map<?, ?> context) {
			return false;
		}
		
	};
	
	/**
	 * Authorizes to perform action on the target with a given qualifier and context.
	 * @param transaction Access decision is made in the context of {@link CDOTransaction}.
	 * @param target Target object, e.g. customer.
	 * @param action Action to perform, e.g. "transfer-funds"
	 * @param qualifier Action qualifier, e.g. "accounts[0]" - the first customer account.
	 * @param context Context. For funds transfer action context may contain transfer amount.
	 * @return
	 */
	boolean authorize(Object target, String action, String qualifier, Map<?,?> context);
	
	/**
	 * @return URL of the subject home page. It is used by the {@link CDOTransactionServlet} to redirect
	 * from the root URL (empty or / pathInfo()). If the return value is null the servlet responds with 404 Not found for the root URL. 
	 */
	default String getHomeUrl() {
		return null;
	}
	
	/**
	 * Authenticates the subject. 
	 * @return
	 */
	default boolean authenticate() {
		return true;
	}
	
	/**
	 * Indicates that the subject contains credentials and shall be authenticated before it 
	 * @return
	 */
	default boolean isAuthenticate() {
		return true;
	}
	
	/**
	 * When authorization fails with authenticated subjects {@link CDOTransactionServlet} 
	 * responds with 403 Forbidden. When authorization fails with unauthenticated subjects
	 * the servlet redirects to the URL returned by this method or responds with 401 Unauthorized if 
	 * the returned value is null.
	 * @return Authentication URl, e.g. log-in page, which may be the same as the home URL.
	 */
	default String getAuthenticationUrl() {
		return getHomeUrl();
	}
	
			
}
