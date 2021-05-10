package org.nasdanika.html.emf;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.OrderedListType;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.viewparts.ListOfActionsViewPart;

/**
 * Marker interface for "view" (as opposed to "edit") actions.
 * @author Pavel Vlasov
 *
 */
public interface ViewAction<T extends EObject> extends Action {

	static <T extends EObject> ViewAction<T> adaptToViewActionNonNull(T obj) {
		if (obj.eIsProxy()) {
			throw new ConfigurationException("Unresolved proxy " + obj);			
		}
		@SuppressWarnings("unchecked")
		ViewAction<T> ret = EObjectAdaptable.adaptTo(obj, ViewAction.class); 
		if (ret == null) {
			Marked marked = EObjectAdaptable.adaptTo(obj, Marked.class);
			throw new ConfigurationException("ViewAction adapter not found for " + obj, marked);
		}
		
		return ret;
	}
	
	/**
	 * @return The underlying {@link EObject}.
	 */
	T getSemanticElement();

	static List<Action> adaptToViewActionNonNull(Collection<? extends EObject> c) {
		return c.stream().map(ViewAction::adaptToViewActionNonNull).collect(Collectors.toList());
	}

	static List<Action> adaptToViewActionNonNullSorted(Collection<? extends EObject> c) {
		return c.stream().map(ViewAction::adaptToViewActionNonNull).sorted((a,b) -> a.getText().compareTo(b.getText())).collect(Collectors.toList());
	}

	static Object listOfViewActions(Collection<? extends EObject> elements, Object header, boolean sort, boolean tooltip, int depth) { 
		if (elements.isEmpty()) {
			return null;
		}
		return new ListOfActionsViewPart(adaptToViewActionNonNull(elements), header, tooltip, depth, OrderedListType.ROTATE);
	}

	static Object listOfViewActionsSorted(Collection<? extends EObject> elements, Object header, boolean sort, boolean tooltip, int depth) { 
		if (elements.isEmpty()) {
			return null;
		}
		return new ListOfActionsViewPart(adaptToViewActionNonNullSorted(elements), header, tooltip, depth, OrderedListType.ROTATE);
	}
	
}
