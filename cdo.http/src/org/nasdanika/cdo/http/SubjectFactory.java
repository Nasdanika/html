package org.nasdanika.cdo.http;

import org.eclipse.emf.cdo.transaction.CDOTransaction;

/**
 * Creates subjects from principals and transactions.
 * @author Pavel
 *
 */
public interface SubjectFactory {
	
	
	Subject createSubject(Principal principal, CDOTransaction transaction);
	
	

}


//
///**
// * @return Subject which authorizes if the principal allows.
// */
//default Subject asAllowingSubject() {
//	return new Subject() {
//
//		@Override
//		public boolean authorize(CDOTransaction transaction, Object target, String action, String qualifier, Map<?, ?> context) {
//			return Principal.this.authorize(transaction, target, action, qualifier, context) == AccessDecision.ALLOW;
//		}
//		
//	};
//}
//	
///**
// * @return Subject which authorizes if the principal does not deny, i.e. either allows or abstains.
// */
//default Subject asNotDenyingSubject() {
//	return new Subject() {
//
//		@Override
//		public boolean authorize(CDOTransaction transaction, Object target, String action, String qualifier, Map<?, ?> context) {
//			return Principal.this.authorize(transaction, target, action, qualifier, context) != AccessDecision.DENY;
//		}
//		
//	};
//}
