package org.nasdanika.html.emf;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.app.Action;

/**
 * Marker interface for "view" (as opposed to "edit") actions.
 * @author Pavel Vlasov
 *
 */
public interface ViewAction extends Action {

	static ViewAction adaptToViewActionNonNull(EObject obj) {
		if (obj.eIsProxy()) {
			throw new ConfigurationException("Unresolved proxy " + obj);			
		}
		ViewAction ret = EObjectAdaptable.adaptTo(obj, ViewAction.class); 
		if (ret == null) {
			Marked marked = EObjectAdaptable.adaptTo(obj, Marked.class);
			throw new ConfigurationException("ViewAction adapter not found for " + obj, marked);
		}
		
		return ret;
	}

	static List<Action> adaptToViewActionNonNull(Collection<? extends EObject> c) {
		return c.stream().map(ViewAction::adaptToViewActionNonNull).collect(Collectors.toList());
	}

	static List<Action> adaptToViewActionNonNullSorted(Collection<? extends EObject> c) {
		return c.stream().map(ViewAction::adaptToViewActionNonNull).sorted((a,b) -> a.getText().compareTo(b.getText())).collect(Collectors.toList());
	}

}
