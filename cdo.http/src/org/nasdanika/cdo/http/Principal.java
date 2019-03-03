package org.nasdanika.cdo.http;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.cdo.transaction.CDOTransaction;

/**
 * Principal participates in authorization decisions in a context of {@link HttpServletRequest} and {@link CDOTransaction}.
 * This interface extends {@link Repository} to allow principal-specific resolution of object ID's to objects. For example, 
 * a customer principal may use "index" ID for the customer object instead of its CDOID.
 * @author Pavel
 *
 */
public interface Principal extends Repository {
//	
//	Principal DENY_ALL = new Principal() {
//
//		@Override
//		public AccessDecision authorize(
//				HttpServletRequest request, 
//				CDOTransaction transaction, 
//				Object target,
//				String action, 
//				String qualifier, 
//				Map<?, ?> context) {
//			
//			return AccessDecision.DENY;
//		}
//		
//	};
	
	/**
	 * Principal's access decision.
	 * @author Pavel
	 *
	 */
	enum AccessDecision {
		/**
		 * Deny access. 
		 */
		DENY,
		/**
		 * Allow access. 
		 */
		ALLOW,
		/**
		 * Abstain from a decision, proceed to the next principal or use default decision, e.g. DENY. 
		 */
		ABSTAIN
	}
	
	/**
	 * Authorizes to perform action on the target with a given qualifier and context.
	 * @param transaction Access decision is made in the context of {@link CDOTransaction}.
	 * @param target Target object, e.g. customer.
	 * @param action Action to perform, e.g. "transfer-funds"
	 * @param qualifier Action qualifier, e.g. "accounts[0]" - the first customer account.
	 * @param context Context. For funds transfer action context may contain transfer amount.
	 * @return
	 */
	AccessDecision authorize(Object target, String action, String qualifier, Map<?,?> context);
	
	/**
	 * @return URL of the principal home page. It is used by the {@link CDOTransactionServlet} to redirect
	 * from the root URL (empty or / pathInfo()). If the return value is null the servlet responds with 404 Not found for the root URL. 
	 * Home url shall be relative to the servlet URL.
	 */
	default String getHomeUrl() {
		return null;
	}
	
	/**
	 * If this method returns true that indicates that this is the authenticated principal and failed authorization shall result in 403 rather than 401.
	 * Otherwise the subject is unauthenticated, which means that no credentials were presented (guest access). Authorization failure for unauthenticated principal
	 * results in 401 error if getAuthenticationUrl() returns null or a redirect to the authentication url.
	 * No principal shall be created for failed authentications and the {@link CDOTransactionServlet} returns 401.  
	 * @return
	 */
	boolean isAuthenticated();
	
	/**
	 * When authorization fails with authenticated principal {@link CDOTransactionServlet} 
	 * responds with 403 Forbidden. When authorization fails with unauthenticated subjects
	 * the servlet redirects to the URL returned by this method or responds with 401 Unauthorized if 
	 * the returned value is null.
	 * @return Authentication URl, e.g. log-in page, which may be the same as the home URL.
	 */
	default String getAuthenticationUrl() {
		return getHomeUrl();
	}
	
	/**
	 * Principal timestamp is used in if-modified-since processing.
	 * @return
	 */
	long timestamp();
				
}
