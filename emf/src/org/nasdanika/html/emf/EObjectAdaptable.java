package org.nasdanika.html.emf;

import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Adaptable;

/**
 * Bridges {@link Adaptable} and EObject adaptation framework - {@link Adapter}.
 * This class also adapts to {@link AccessController} by delegating to the container and adding containment feature to the 
 * qualifier. E.g. if an object Account is contained by Customer under accounts reference and Account is not adaptable to AccessController
 * then this adaptable would adapt Customer to AccessController and if successful will append 'accounts' suffix to the qualifier. 
 * This behavior allows to bubble-up permissions checks. 
 * @author Pavel
 *
 * @param <T>
 */
public class EObjectAdaptable<T extends EObject> implements Adaptable {
	
	protected T target;

	public EObjectAdaptable(T target) {
		this.target = target;
	}
	
	@Override
	public <A> A adaptTo(Class<A> type) {
		return adaptTo(target, type);
	}
	
	@SuppressWarnings("unchecked")
	public static <A> A adaptTo(EObject target, Class<A> type) {
		if (type.isInstance(target)) {
			return (A) target;
		}
		A adapter = (A) EcoreUtil.getRegisteredAdapter(target, type);
		
		if (adapter == null && AccessController.class == type) {
			// Delegating to the container with containment reference qualifier
			EObject container = target.eContainer();
			if (container != null) {
				EReference containmentReference = target.eContainmentFeature();
				if (containmentReference != null) {
					AccessController cap = adaptTo(container, AccessController.class);
					if (cap != null) {
						return (A) new AccessController() {
							
							@Override
							public boolean hasPermission(String action, String qualifier) {
								String cQualifier;
								if (qualifier == null) {
									cQualifier = qualifier;
								} else {
									String suffix = containmentReference.getName();
									if (containmentReference.isMany()) {
										int pos = ((List<?>) container.eGet(containmentReference)).indexOf(target);
										suffix += "[" + pos + "]";
									}
									if (qualifier.endsWith("/")) {
										cQualifier = qualifier + suffix;
									} else {
										cQualifier = qualifier + "/" + suffix;
									}
								}
								return cap.hasPermission(action, cQualifier);
							}
						};
					}
				}
			}
		}
		return adapter;
		
	}

}
