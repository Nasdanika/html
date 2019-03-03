package org.nasdanika.cdo.http;

import java.util.Map;

import org.eclipse.emf.cdo.transaction.CDOTransaction;

/**
 * Subject represents {@link Principal}s arriving at a decision whether to allow or deny access.
 * Subject is created in a context of request/transaction. 
 * @author Pavel
 *
 */
public interface Subject {

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
	 * Home url shall be relative to the servlet URL.
	 */
	default String getHomeUrl() {
		return null;
	}
	
	/**
	 * If this method returns true that indicates that this is the athenticated subject and failed authorization shall result in 403 rather than 401.
	 * Otherwise the subject is unauthenticated, which means that no credentials were presented (guest access). Authorization failure for unauthenticated subject
	 * results in 401 error if getAuthenticationUrl() returns null or a redirect to the authentication url.
	 * No subject shall be created for failed authentications and the {@link CDOTransactionServlet} returns 401.  
	 * @return
	 */
	boolean isAuthenticated();
	
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
