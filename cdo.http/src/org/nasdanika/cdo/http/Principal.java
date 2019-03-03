package org.nasdanika.cdo.http;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.eclipse.emf.cdo.transaction.CDOTransaction;

/**
 * Principal participates in granting access as part of a {@link Subject} in a context of {@link CDOTransaction}.
 * Principals are context-independent and can be passed between transactions, e.g. be stored in {@link HttpSession} or application context (e.g. token principals).
 * @author Pavel
 *
 */
public interface Principal {
	
	/**
	 * Principal which denies all access.
	 */
	Principal DENY_ALL = new Principal() {

		@Override
		public AccessDecision authorize(CDOTransaction transaction, Object target, String action, String qualifier, Map<?, ?> context) {
			return AccessDecision.DENY;
		}
		
	};

	/**
	 * Principal which allows all access.
	 */
	Principal ALLOW_ALL = new Principal() {

		@Override
		public AccessDecision authorize(CDOTransaction transaction, Object target, String action, String qualifier, Map<?, ?> context) {
			return AccessDecision.ALLOW;
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
	AccessDecision authorize(CDOTransaction transaction, Object target, String action, String qualifier, Map<?,?> context);
			
	/**
	 * A composite principal which denies access if any member denies it and allows if at least one member allows. 
	 * @param principals
	 * @return
	 */
	static Principal any(Principal... principals) {
		return any(Arrays.asList(principals));
	}

	/**
	 * A composite principal which denies access if any member denies it and allows if at least one member allows.
	 * If there no members then the decision is to abstain. 
	 * @param principals
	 * @return
	 */
	static Principal any(Iterable<Principal> principals) {
		return new Principal() {

			@Override
			public AccessDecision authorize(CDOTransaction transaction, Object target, String action, String qualifier,	Map<?, ?> context) {
				AccessDecision ret = AccessDecision.ABSTAIN;
				for (Principal principal: principals) {
					AccessDecision pad = principal.authorize(transaction, target, action, qualifier, context);
					if (AccessDecision.DENY == pad) {
						return AccessDecision.DENY;
					}
					if (pad == AccessDecision.ALLOW) {
						ret = pad;
					}
				}
				return ret;
			}
			
		};
	}

	/**
	 * A composite principal which denies access if any member denies it and allows if all members allow. 
	 * @param principals
	 * @return
	 */
	static Principal all(Principal... principals) {
		return all(Arrays.asList(principals));
	}

	/**
	 * A composite principal which denies access if any member denies it and allows if all members allow. 
	 * If there are no members then the decision is to abstain.
	 * @param principals
	 * @return
	 */
	static Principal all(Iterable<Principal> principals) {
		return new Principal() {

			@Override
			public AccessDecision authorize(CDOTransaction transaction, Object target, String action, String qualifier,	Map<?, ?> context) {
				AccessDecision ret = AccessDecision.ABSTAIN;
				for (Principal principal: principals) {
					AccessDecision pad = principal.authorize(transaction, target, action, qualifier, context);
					if (AccessDecision.ALLOW != pad) {
						return AccessDecision.DENY;
					}
					ret = pad;
				}
				return ret;
			}
			
		};
	}
	
	
}
