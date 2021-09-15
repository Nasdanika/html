/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.ActionReference#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getActionReference()
 * @model
 * @generated
 */
public interface ActionReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Action)
	 * @see org.nasdanika.html.model.app.AppPackage#getActionReference_Target()
	 * @model required="true"
	 * @generated
	 */
	Action getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.ActionReference#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Action value);

} // ActionReference
