package org.nasdanika.cdo.http;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.eclipse.emf.cdo.transaction.CDOTransaction;

/**
 * Principal participates in authorization decisions.
 * Principals are context-independent and can be passed between transactions, e.g. be stored in {@link HttpSession} or application context (e.g. token principals).
 * @author Pavel
 *
 */
public interface Principal {
	
	Principal DENY_ALL = new Principal() {

		@Override
		public AccessDecision authorize(
				HttpServletRequest request, 
				CDOTransaction transaction, 
				Object target,
				String action, 
				String qualifier, 
				Map<?, ?> context) {
			
			return AccessDecision.DENY;
		}
		
	};
	
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
	AccessDecision authorize(HttpServletRequest request, CDOTransaction transaction, Object target, String action, String qualifier, Map<?,?> context);
				
}
