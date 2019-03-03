package org.nasdanika.cdo.http;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.emf.cdo.transaction.CDOTransaction;

/**
 * Creates subjects from principals for a request/transaction.
 * @author Pavel
 *
 */
public interface SubjectFactory {
	
	
	Subject createSubject(HttpServletRequest request, CDOTransaction transaction, List<Principal> principals);
	
	

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

///**
// * Principal which denies all access.
// */
//Principal DENY_ALL = new Principal() {
//
//	@Override
//	public AccessDecision authorize(CDOTransaction transaction, Object target, String action, String qualifier, Map<?, ?> context) {
//		return AccessDecision.DENY;
//	}
//	
//};
//
///**
// * Principal which allows all access.
// */
//Principal ALLOW_ALL = new Principal() {
//
//	@Override
//	public AccessDecision authorize(CDOTransaction transaction, Object target, String action, String qualifier, Map<?, ?> context) {
//		return AccessDecision.ALLOW;
//	}
//	
//};
//
///**
// * A composite principal which denies access if any member denies it and allows if at least one member allows. 
// * @param principals
// * @return
// */
//static Principal any(Principal... principals) {
//	return any(Arrays.asList(principals));
//}
//
///**
// * A composite principal which denies access if any member denies it and allows if at least one member allows.
// * If there no members then the decision is to abstain. 
// * @param principals
// * @return
// */
//static Principal any(Iterable<Principal> principals) {
//	return new Principal() {
//
//		@Override
//		public AccessDecision authorize(CDOTransaction transaction, Object target, String action, String qualifier,	Map<?, ?> context) {
//			AccessDecision ret = AccessDecision.ABSTAIN;
//			for (Principal principal: principals) {
//				AccessDecision pad = principal.authorize(transaction, target, action, qualifier, context);
//				if (AccessDecision.DENY == pad) {
//					return AccessDecision.DENY;
//				}
//				if (pad == AccessDecision.ALLOW) {
//					ret = pad;
//				}
//			}
//			return ret;
//		}
//		
//	};
//}
//
///**
// * A composite principal which denies access if any member denies it and allows if all members allow. 
// * @param principals
// * @return
// */
//static Principal all(Principal... principals) {
//	return all(Arrays.asList(principals));
//}
//
///**
// * A composite principal which denies access if any member denies it and allows if all members allow. 
// * If there are no members then the decision is to abstain.
// * @param principals
// * @return
// */
//static Principal all(Iterable<Principal> principals) {
//	return new Principal() {
//
//		@Override
//		public AccessDecision authorize(CDOTransaction transaction, Object target, String action, String qualifier,	Map<?, ?> context) {
//			AccessDecision ret = AccessDecision.ABSTAIN;
//			for (Principal principal: principals) {
//				AccessDecision pad = principal.authorize(transaction, target, action, qualifier, context);
//				if (AccessDecision.ALLOW != pad) {
//					return AccessDecision.DENY;
//				}
//				ret = pad;
//			}
//			return ret;
//		}
//		
//	};
//}

