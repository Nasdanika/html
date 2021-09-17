package org.nasdanika.html.emf;

import java.util.List;
import java.util.Optional;

import javax.lang.model.element.TypeElement;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.html.model.app.Action;

public class EObjectActionProviderAdapter<T extends EObject> extends AdapterImpl {

	public EObjectActionProviderAdapter(T target) {
		setTarget(target);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getTarget() {
		return (T) super.getTarget();
	}
	
	public enum Classifier {
		
		/**
		 * Default action for the literal.
		 */
		DEFAULT,
		
		/**
		 * Properties table.
		 */
		PROPERTIES,
		
		/**
		 * For {@link TypeElement} literals create an action for each of (return) value elements.
		 */
		ELEMENTS,
		
		/**
		 * For {@link TypeElement} literals create an action for each of (return) value elements and then sort results alphabetically by text.
		 */
		ELEMENTS_SORTED,
		
		/**
		 * Child actions for the member, e.g. a section with action list
		 */
		ACTIONS,
				
		/**
		 * No classifier. Used to suppress inherited classifier via configuration.
		 */
		NONE;
		
		public final String LITERAL;
		
		private Classifier() {
			this.LITERAL = name().toLowerCase().replace('_', '-');
		}
		
	}	
		
	// subClassifier and vararg arguments?
	/**
	 * @param literal Part of the target to return a list of actions for. {@link EClass} for self, {@link EStructuralFeature} such as {@link EAttribute} or {@link EReference}, or {@link EOperation}.
	 * @param classifier
	 * @return A list of actions parts of the target or the target itself. 
	 */
	public List<Action> getActions(EModelElement literal, Classifier classifier) throws Exception {
		if (literal instanceof EClass) {
			return getEClassActions((EClass) literal, classifier);
		}
		if (literal instanceof ETypedElement) {
			return getTypedElementActions((ETypedElement) literal, getTypedElementValue((ETypedElement) literal), classifier);
		}
		throw new UnsupportedOperationException("Unsupported literal: " + literal);
	}
	
	protected List<Action> getEClassActions(EClass literal, Classifier classifier) throws Exception  {
		throw new UnsupportedOperationException();
	}

	private List<Action> getTypedElementActions(ETypedElement literal, Object typedElementValue, Classifier classifier) throws Exception  {
		// Further dispatching.
		throw new UnsupportedOperationException();
	}

	private Object getTypedElementValue(ETypedElement typedElement) throws Exception  {
		if (typedElement instanceof EStructuralFeature) {
			return getTarget().eGet((EStructuralFeature) typedElement);
		}
		
		EOperation eOp = (EOperation) typedElement;
		
		// A hack to get to the container.
		if (eOp == EcorePackage.Literals.EOBJECT___ECONTAINER) {
			return getTarget().eContainer();
		}
		
		EList<EParameter> params = eOp.getEParameters();
		if (params.isEmpty()) {
			return getTarget().eInvoke(eOp, ECollections.emptyEList());
		}
		return getTarget().eInvoke(eOp, bind(eOp));
	}
	
	protected EList<Object> bind(EOperation eOperation) throws Exception {
		throw new UnsupportedOperationException();
	}

	private Optional<Action> defaultAction;
	
	public Action getAction() throws Exception {
		if (defaultAction == null) {
			List<Action> actions = getActions(getTarget().eClass(), Classifier.DEFAULT);
			
			if (actions == null || actions.isEmpty()) {
				defaultAction = Optional.empty();
			} else if (actions.size() == 1) {
				defaultAction = Optional.of(actions.get(0));
			}
			
			throw new UnsupportedOperationException("Multiple default actions are not supported");
		}
		return defaultAction.orElse(null);
	}

}
